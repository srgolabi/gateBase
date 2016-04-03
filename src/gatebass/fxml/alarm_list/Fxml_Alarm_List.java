/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.alarm_list;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.Individuals;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.ParentControl;
import gatebass.utils.PersianCalendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Alarm_List extends ParentControl {

    public interface Get_Object {

        void car(Cars car, boolean is_edit_mode);

        void individual(Individuals individuals, boolean is_edit_mode);
    }

    private Get_Object get_Object;

    public void set_On_Get_Car(Get_Object go) {
        this.get_Object = go;
    }

    @FXML
    private MyButtonFont edit;
    @FXML
    private MyButtonFont review;
    @FXML
    private MyButtonFont export_to_excel;
    @FXML
    private Label sum;

    @FXML
    private TableView<Gate_Alarm> tableView;
    @FXML
    private TableColumn<Gate_Alarm, Gate_Alarm> button_column;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);

        button_column.setCellFactory(new Callback<TableColumn<Gate_Alarm, Gate_Alarm>, TableCell<Gate_Alarm, Gate_Alarm>>() {

            @Override
            public TableCell<Gate_Alarm, Gate_Alarm> call(TableColumn<Gate_Alarm, Gate_Alarm> param) {
                TableCell<Gate_Alarm, Gate_Alarm> cell = new TableCell<Gate_Alarm, Gate_Alarm>() {
                    @Override
                    protected void updateItem(Gate_Alarm item, boolean empty) {
                        if (item == getItem()) {
                            return;
                        }
                        super.updateItem(item, empty);
                        if (item == null) {
                            setStyle(null);
                            setText(null);
                            setGraphic(null);
                            return;
                        }

                        final HBox hbox = new HBox(0);
                        hbox.setAlignment(Pos.CENTER);

                        CheckBox checkBox = new CheckBox();
                        
                        item.is_cheked.bind(checkBox.selectedProperty());

                        MyButtonFont fileDownlaod = new MyButtonFont("download", 14, "table-button");

                        fileDownlaod.setOnAction((javafx.event.ActionEvent event) -> {

                        });

                        MyButtonFont fileDelete = new MyButtonFont("trash", 14, "table-button");

                        fileDelete.setOnAction((javafx.event.ActionEvent event) -> {

                        });

                        hbox.getChildren().addAll(checkBox, fileDelete, fileDownlaod);

                        setGraphic(hbox);

                    }
                };
                return cell;
            }
        });

        edit.init("pencil", 15);
        review.init("eye", 15);
        export_to_excel.init("export", 15);

        edit.setOnAction((ActionEvent event) -> {
            on_action(true);
        });

        review.setOnAction((ActionEvent event) -> {
            on_action(false);
        });

        String query_individual
                = "SELECT individuals.id ids, individuals.card_id , '>' || individuals.first_name || ' ' || individuals.last_name || '>' || ' ' || '< شماره ملی : ' || individuals.national_id || '<' datail , 'individual' alarm_type , 'ALARM_INFO' alrm_info\n"
                + "FROM individuals \n"
                + "LEFT OUTER JOIN history history_J1 ON individuals.soldiery_start_date = history_J1.id\n"
                + "LEFT OUTER JOIN history history_J2 ON individuals.soldiery_end_date = history_J2.id\n"
                + "LEFT OUTER JOIN history history_J3 ON individuals.birth_day = history_J3.id\n"
                + "LEFT OUTER JOIN \n"
                + "(SELECT individualReplica.* , history_j1.date Replica_date FROM individualReplica\n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = individualReplica.history_id\n"
                + ") individualReplica_J ON individualReplica_J.Individual_id =  individuals.id\n"
                + "LEFT OUTER JOIN \n"
                + "(SELECT individualWarning.* , history_j1.date warning_date FROM individualWarning\n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = individualWarning.history_id\n"
                + ") individualWarning_J ON individualWarning_J.Individual_id =  individuals.id\n"
                + "LEFT OUTER JOIN \n"
                + "(SELECT workhistory.* , history_j1.date employment_date, history_j2.date card_issued_date , history_j3.date card_expiration_date , history_j4.date card_delivery_date , companies_j.company_fa  FROM workhistory \n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = workhistory.employment_date_id\n"
                + " LEFT OUTER JOIN history history_j2 ON history_j2.id = workhistory.card_issued_date_id\n"
                + " LEFT OUTER JOIN history history_j3 ON history_j3.id = workhistory.card_expiration_date_id\n"
                + " LEFT OUTER JOIN history history_j4 ON history_j4.id = workhistory.card_delivery_date_id\n"
                + " LEFT OUTER JOIN companies companies_j ON companies_j.id = workhistory.companies_id\n"
                + ") workhistory_j ON workhistory_j.individuals_id = individuals.id\n"
                + "WHERE individuals.alarm_state = 0 AND WHERE_SEARCH_QUERY\n"
                + "GROUP BY individuals.id";
        PersianCalendar pc = new PersianCalendar();
//        String sub_query = pc.year().substring(2) + "/" + pc.month() + "/" + pc.day();
        String sub_query = "94/07/30";

        add_car_alarm(
                query_individual,
                "workhistory_j.card_expiration_date = '" + sub_query + "'",
                "expiration");

        query_individual = "SELECT cars.id ids , cars.card_id , '>' || cars.car_name || '>' || ' ' || '< شماره شاسی : ' || cars.shasi_number || '<' datail , 'car' alarm_type , 'ALARM_INFO' alrm_info\n"
                + "FROM cars\n"
                + "LEFT OUTER JOIN\n"
                + "(SELECT individualReplica.* FROM individualReplica\n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = individualReplica.history_id\n"
                + ") individualReplica_J ON individualReplica_J.Individual_id =  cars.id\n"
                + "LEFT OUTER JOIN\n"
                + "(SELECT carHistory.* , history_j1.date bimeh_date, history_j2.date card_expiration_date , history_j3.date card_issued_date , history_j4.date card_void_date , history_j5.date certificate_date , companies_j.company_fa , driver_info.first_name || ' ' || driver_info.last_name driver_name FROM carHistory\n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = carHistory.bimeh_date_id\n"
                + " LEFT OUTER JOIN history history_j2 ON history_j2.id = carHistory.card_expiration_date_id\n"
                + " LEFT OUTER JOIN history history_j3 ON history_j3.id = carHistory.card_issued_date_id\n"
                + " LEFT OUTER JOIN history history_j4 ON history_j4.id = carHistory.card_void_date_id\n"
                + " LEFT OUTER JOIN history history_j5 ON history_j5.id = carHistory.certificate_date_id\n"
                + " LEFT OUTER JOIN companies companies_j ON companies_j.id = carHistory.companies_id\n"
                + " LEFT OUTER JOIN\n"
                + " (SELECT workhistory.* , individuals_j1.first_name , individuals_j1.last_name FROM workhistory\n"
                + "  LEFT OUTER JOIN individuals individuals_j1 ON workhistory.individuals_id = individuals_j1.id \n"
                + " ) driver_info ON driver_info.id = carHistory.workHistory_id\n"
                + ") carhistory_j ON carhistory_j.car_id = cars.id\n"
                + "WHERE cars.alarm_state = 0 AND WHERE_SEARCH_QUERY\n"
                + "GROUP BY cars.id";

        add_car_alarm(
                query_individual,
                "carhistory_j.card_expiration_date = '" + sub_query + "'",
                "expiration");

        add_car_alarm(
                query_individual,
                "carhistory_j.certificate_date = '" + sub_query + "'",
                "certificate");

        add_car_alarm(
                query_individual,
                "carhistory_j.bimeh_date = '" + sub_query + "'",
                "bimeh");

        sum.setText(tableView.getItems().size() + "");
    }

    private void on_action(boolean bool) {
        if (tableView.getSelectionModel().getSelectedItem().getAlarm_type().equals("car")) {
            Cars cars = databaseHelper.carDao.queryForId(tableView.getSelectionModel().getSelectedItem().getIds());
            cars.setAlarm_state(true);
            get_Object.car(cars, bool);
        } else {
            Individuals individuals = databaseHelper.individualsDao.queryForId(tableView.getSelectionModel().getSelectedItem().getIds());
            individuals.setAlarm_state(true);
            get_Object.individual(databaseHelper.individualsDao.queryForId(tableView.getSelectionModel().getSelectedItem().getIds()), bool);
        }
    }

    private void add_car_alarm(String query, String sub_query, String alarm_info) {
        tableView.getItems().addAll(
                databaseHelper.gate_AlarmDao.rawResults(query.replace("ALARM_INFO", alarm_info).replace("WHERE_SEARCH_QUERY", sub_query))
        );
    }

}
