package gatebass.fxml.individual_search;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Individuals;
import static gatebass.fxml.individual_insert.Fxml_Individual_Insert.BEDONE_KART;
import static gatebass.fxml.individual_insert.Fxml_Individual_Insert.MOAF;
import static gatebass.fxml.individual_insert.Fxml_Individual_Insert.PAYAN_KHEDMAT;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.MyTime;
import gatebass.utils.ParentControl;
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
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Individual_Search extends ParentControl {

    public interface OnAction {

        void ok(Individuals l);
    }

    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField national_id;
    @FXML
    private TextField father_first_name;
    @FXML
    private TextField first_name_ENG;
    @FXML
    private TextField last_name_ENG;
    @FXML
    private TextField id_number;
    @FXML
    private TextField serial_number;
    @FXML
    private TextField birth_dayy;
    @FXML
    private TextField birth_month;
    @FXML
    private TextField birth_year;
    @FXML
    private TextField issued;
    @FXML
    private TextField birth_state;
    @FXML
    private TextField series_id;
    @FXML
    private TextField field_of_study;
    @FXML
    private TextField academic_degree;
    @FXML
    private TextField mobile;
    @FXML
    private TextField criminal_records;
    @FXML
    private TextField nationality;
    @FXML
    private TextField religion;
    @FXML
    private TextField din;
    @FXML
    private RadioButton payan_khedmat;
    @FXML
    private RadioButton bedon_kart;
    @FXML
    private RadioButton moaf;
    @FXML
    private TextField soldiery_start_day;
    @FXML
    private TextField soldiery_start_month;
    @FXML
    private TextField soldiery_start_year;
    @FXML
    private TextField soldiery_end_day;
    @FXML
    private TextField soldiery_end_month;
    @FXML
    private TextField soldiery_end_year;
    @FXML
    private TextField soldiery_id;
    @FXML
    private TextField soldiery_location;
    @FXML
    private TextField soldiery_unit;
    @FXML
    private TextField soldiery_exempt;
    @FXML
    private TextField state_address;
    @FXML
    private TextField city_address;
    @FXML
    private TextField postal_code;
    @FXML
    private TextField phone_number;
    @FXML
    private TextField street_address;
    @FXML
    private TextArea individualComments;

    private MyTime birth_day;
    private MyTime soldiery_start_date;
    private MyTime soldiery_end_date;

    private Label searchResault;

    private OnAction onAction;
    private ListProperty<Individuals> searchList = new SimpleListProperty<>();
    private IntegerProperty idSearchProperty = new SimpleIntegerProperty(0);

    public void setControls(Label searchResault, MyButtonFont searchNext, MyButtonFont searchBack, MyButtonFont first, MyButtonFont end, Button submit, Node searchPane) {

        thisStage.getScene().addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            switch (event.getCode()) {
                case N:
                    if (!event.isControlDown()) {
                        break;
                    }
                case F7:
                    if (searchPane.isVisible()) {
                        clear();
                    }
                    break;
            }
        });

        searchList = new SimpleListProperty<>();

        first_name.requestFocus();

        birth_day = new MyTime(birth_year, birth_month, birth_dayy);
        soldiery_start_date = new MyTime(soldiery_start_year, soldiery_start_month, soldiery_start_day);
        soldiery_end_date = new MyTime(soldiery_end_year, soldiery_end_month, soldiery_end_day);

        TextFiledLimited.setEnterFocuse(
                first_name, last_name, national_id, father_first_name, first_name_ENG, last_name_ENG,
                id_number, serial_number, birth_dayy, birth_month, birth_year, issued, birth_state,
                series_id, field_of_study, academic_degree, mobile, criminal_records, nationality,
                religion, din, payan_khedmat, bedon_kart, moaf, soldiery_start_day,
                soldiery_start_month, soldiery_start_year, soldiery_end_day, soldiery_end_month,
                soldiery_end_year, soldiery_id, soldiery_location, soldiery_unit, soldiery_exempt,
                state_address, city_address, street_address, postal_code, phone_number, individualComments, submit
        );
        TextFiledLimited.set_Number_Length_Limit(national_id, 10);
        TextFiledLimited.set_Number_Length_Limit(serial_number, 6);
        TextFiledLimited.set_Number_Length_Limit(mobile, 11);

        this.searchResault = searchResault;

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
            if (payan_khedmat.isSelected() || moaf.isSelected() || bedon_kart.isSelected()) {
                sub_query = "veteran_status = '" + (payan_khedmat.isSelected() ? PAYAN_KHEDMAT : bedon_kart.isSelected() ? BEDONE_KART : MOAF) + "' AND ";
            }
            if (!individualComments.getText().isEmpty()) {
                sub_query = sub_query + "comments LIKE '%" + individualComments.getText() + "%' AND ";
            }
            sub_query = sub_query + create_sub_query(first_name, last_name, national_id, father_first_name,
                    first_name_ENG, last_name_ENG, id_number, serial_number, issued, birth_state,
                    series_id, field_of_study, academic_degree, criminal_records, soldiery_id,
                    soldiery_location, soldiery_unit, soldiery_exempt, state_address, city_address,
                    postal_code, phone_number, street_address);
            String sub_query_b = create_sub_query(
                    new String[][]{{"birth_day", "تاریخ تولد"}, {"soldiery_start_date", "تاریخ اعزام به خدمت"}, {"soldiery_end_date", "تاریخ خاتمه خدمت"}},
                    birth_day, soldiery_start_date, soldiery_end_date);
            if (sub_query_b.equals("error")) {
                return;
            }
            sub_query = sub_query + sub_query_b;
            if (sub_query.isEmpty()) {
                UtilsStage.showMsg("موردی جهت جست و جو وجود ندارد", "هشدار", false, thisStage);
                return;
            }
            String query = "SELECT individuals.* FROM individuals\n"
                    + "WHERE sub_query ".replace("sub_query", sub_query.substring(0, sub_query.length() - 4))
                    + "GROUP BY individuals.id ORDER BY individuals.card_id ASC";
            ObservableList<Individuals> observableList = null;
            observableList = FXCollections.observableArrayList(databaseHelper.individualsDao.rawResults(query));
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
                    first_name.requestFocus();
                }
            });
        });
    }

    private String create_sub_query(TextField... textFields) {
        String sub_query = "";
        for (TextField tf : textFields) {
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
        payan_khedmat.setSelected(false);
        bedon_kart.setSelected(false);
        moaf.setSelected(false);
        individualComments.setText("");
        TextFiledLimited.set_empty_textField(first_name, last_name, national_id, father_first_name,
                first_name_ENG, last_name_ENG, id_number, serial_number, issued, birth_state,
                series_id, field_of_study, academic_degree, criminal_records, soldiery_id,
                soldiery_location, soldiery_unit, soldiery_exempt, state_address, city_address,
                postal_code, phone_number, street_address);
        MyTime.set_empty_myTime(birth_day, soldiery_start_date, soldiery_end_date);
    }
}
