package gatebass.fxml.car_insert;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Cars;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.TextFiledLimited;
import gatebass.utils.UtilsMsg;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Simple_Search {

    public interface OnAction {

        void ok(Cars c);
    }

    public Stage thisStage;

    public TextField card_id_search;
    private TextField shasi_number_search;
    private TextField car_name_search;
    private TextField color_search;
    private TextField model_search;
    private TextField pellak_search;
    private TextArea comments_search;
    private Label searchResault;

    private OnAction onAction;
    private ListProperty<Cars> searchList = new SimpleListProperty<>();
    private IntegerProperty idSearchProperty = new SimpleIntegerProperty(0);

    public Simple_Search(Stage stage, TextField card_id_search, TextField shasi_number_search, TextField car_name_search, TextField color_search, TextField model_search, TextField pellak_search, TextArea comments_search, Label searchResault) {
        this.thisStage = stage;
        this.card_id_search = card_id_search;
        this.shasi_number_search = shasi_number_search;
        this.car_name_search = car_name_search;
        this.color_search = color_search;
        this.model_search = model_search;
        this.pellak_search = pellak_search;
        this.comments_search = comments_search;
        this.searchResault = searchResault;
    }

    public void setControls(MyButtonFont searchNext, MyButtonFont searchBack, MyButtonFont first, MyButtonFont end, Button submit, Node searchPane) {

        searchList = new SimpleListProperty<>();

        searchBack.init("left_dir", 15);
        searchNext.init("right_dir", 15);
        first.init("to_start", 15);
        end.init("to_end", 15);

        end.disableProperty().bind(searchNext.disableProperty());
        first.disableProperty().bind(searchBack.disableProperty());
        end.setOnAction((ActionEvent event) -> {
            idSearchProperty.set(searchList.getSize() - 1);
            moving();
        });
        first.setOnAction((ActionEvent event) -> {
            idSearchProperty.set(0);
            moving();
        });
        searchNext.setOnAction((ActionEvent event) -> {
            idSearchProperty.set(idSearchProperty.get() + 1);
            moving();
        });
        searchBack.setOnAction((ActionEvent event) -> {
            idSearchProperty.set(idSearchProperty.get() - 1);
            moving();
        });

        submit.setOnAction((ActionEvent event) -> {
            String sub_query = "";

            sub_query = sub_query + create_sub_query(card_id_search,
                    shasi_number_search, car_name_search, pellak_search,
                    color_search, model_search, comments_search);

            if (sub_query.isEmpty()) {
                UtilsMsg.show("موردی جهت جست و جو وجود ندارد", "هشدار", false, thisStage);
                return;
            }

            String query
                    = "SELECT cars.* FROM cars\n"
                    + "LEFT OUTER JOIN\n"
                    + "(SELECT individualReplica.* FROM individualReplica\n"
                    + " LEFT OUTER JOIN history history_j1 ON history_j1.id = individualReplica.history_id\n"
                    + ") individualReplica_J ON individualReplica_J.car_id = cars.id\n"
                    + "LEFT OUTER JOIN\n"
                    + "(SELECT carHistory.* , history_j1.date bimeh_date, history_j2.date card_expiration_date , history_j3.date card_issued_date , history_j4.date card_delivery_date , history_j5.date certificate_date , companies_j.company_fa , driver_info.first_name || ' ' || driver_info.last_name driver_name FROM carHistory\n"
                    + " LEFT OUTER JOIN history history_j1 ON history_j1.id = carHistory.bimeh_date_id\n"
                    + " LEFT OUTER JOIN history history_j2 ON history_j2.id = carHistory.card_expiration_date_id\n"
                    + " LEFT OUTER JOIN history history_j3 ON history_j3.id = carHistory.card_issued_date_id\n"
                    + " LEFT OUTER JOIN history history_j4 ON history_j4.id = carHistory.card_delivery_date_id\n"
                    + " LEFT OUTER JOIN history history_j5 ON history_j5.id = carHistory.certificate_date_id\n"
                    + " LEFT OUTER JOIN companies companies_j ON companies_j.id = carHistory.companies_id\n"
                    + " LEFT OUTER JOIN\n"
                    + " (SELECT workhistory.* , individuals_j1.first_name , individuals_j1.last_name FROM workhistory\n"
                    + "  LEFT OUTER JOIN individuals individuals_j1 ON workhistory.individuals_id = individuals_j1.id \n"
                    + "  ) driver_info ON driver_info.id = carHistory.workHistory_id\n"
                    + ") carhistory_j ON carhistory_j.car_id = cars.id\n"
                    + "WHERE sub_query \n".replace("sub_query", sub_query.substring(0, sub_query.length() - 4))
                    + "GROUP BY cars.id ORDER BY cars.card_id ASC";

            ObservableList<Cars> observableList = null;
            observableList = FXCollections.observableArrayList(databaseHelper.carDao.rawResults(query));
            searchList = new SimpleListProperty<>(observableList);
            searchBack.disableProperty().bind(searchList.sizeProperty().lessThan(2).or(idSearchProperty.lessThan(1)));
            searchNext.disableProperty().bind(searchList.sizeProperty().lessThan(2).or(idSearchProperty.isEqualTo(searchList.getSize() - 1)));
            if (searchList.isEmpty()) {
                searchResault.setText("موردی یافت نشد");
                this.onAction.ok(null);
            } else {
                idSearchProperty.set(0);
                moving();
            }
            searchPane.setVisible(false);
            searchPane.visibleProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    clear();
                    card_id_search.requestFocus();
                }
            });
        });
    }

    private String create_sub_query(TextInputControl... textFields) {
        String sub_query = "";
        for (TextInputControl tf : textFields) {
            if (!tf.getText().isEmpty()) {
                sub_query = sub_query + tf.getId().replace("_search", "") + " LIKE '%" + tf.getText() + "%' AND ";
            }
        }
        return sub_query;
    }

    private void moving() {
        this.onAction.ok(searchList.get(idSearchProperty.get()));
        searchResault.setText(idSearchProperty.get() + 1 + " از " + searchList.size());
    }

    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }

    public void clear() {
        TextFiledLimited.set_empty_textField(card_id_search,
                shasi_number_search, car_name_search, color_search, model_search, pellak_search,
                comments_search);
    }
}
