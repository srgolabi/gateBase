package gatebass.fxml.car_insert;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.Individuals;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.MyTime;
import gatebass.utils.TextFiledLimited;
import gatebass.utils.UtilsStage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    public Simple_Search(Stage stage, TextField shasi_number_search, TextField car_name_search, TextField color_search, TextField model_search, TextField pellak_search, TextArea comments_search, Label searchResault) {
        this.thisStage = stage;
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

            sub_query = sub_query + create_sub_query(
                    shasi_number_search, car_name_search, pellak_search,
                    color_search, model_search, comments_search);

            if (sub_query.isEmpty()) {
                UtilsStage.showMsg("موردی جهت جست و جو وجود ندارد", "هشدار", false, thisStage);
                return;
            }
            String query = "SELECT cars.* FROM cars\n"
                    + "WHERE sub_query ".replace("sub_query", sub_query.substring(0, sub_query.length() - 4))
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
                    shasi_number_search.requestFocus();
                }
            });
        });
    }

    private String create_sub_query(TextInputControl... textFields) {
        String sub_query = "";
        for (TextInputControl tf : textFields) {
            if (!tf.getText().isEmpty()) {
                sub_query = sub_query + tf.getId() + " LIKE '%" + tf.getText() + "%' AND ";
            }
        }
        return sub_query;
    }

    private String create_sub_query(String[][] fields, MyTime... myTimes) {
        String sub_query = "";
        int i = 0;
        for (MyTime mt : myTimes) {
            if (!mt.isDamage(fields[i][1] + " به درستی پر نشده.", thisStage)) {
                if (mt.isFull()) {
                    sub_query = sub_query + fields[i][0] + " = '" + mt.getDate() + "' AND ";
                }
            } else {
                return "error";
            }
            i++;
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
        TextFiledLimited.set_empty_textField(
                shasi_number_search, car_name_search,color_search,model_search, pellak_search,
                comments_search);
    }
}
