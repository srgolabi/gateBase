/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.alarm_list;

import gatebass.GateBass;
import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.History;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.AGTPFont;
import gatebass.utils.MyTime;
import gatebass.utils.ParentControl;
import gatebass.utils.UtilsMsg;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Alarm_List extends ParentControl {

    @FXML
    private MyButtonFont refresh;
    @FXML
    private MyButtonFont edit;
    @FXML
    private MyButtonFont review;
    @FXML
    private MyButtonFont export_to_excel;
    @FXML
    public Label sum;

    @FXML
    private CheckBox all_check;
    @FXML
    private MyButtonFont all_delete;
    @FXML
    private MyButtonFont all_keep;

    @FXML
    private TableView<Gate_Alarm> tableView;
    @FXML
    private TableColumn<Gate_Alarm, Gate_Alarm> button_column;
    @FXML
    private TableColumn<Gate_Alarm, String> type_column;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        refresh.init("ccw", 15);
        edit.init("pencil", 15);
        review.init("eye", 15);
        export_to_excel.init("export", 15);

        all_delete.init("trash", 15);
        all_keep.init("circle_thin", 15);
        sum.textProperty().bind(Bindings.size(tableView.itemsProperty().get()).asString());

        button_column.setCellFactory((TableColumn<Gate_Alarm, Gate_Alarm> param) -> {
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

                    checkBox.selectedProperty().bindBidirectional(item.is_cheked);

                    MyButtonFont alarm_keep = new MyButtonFont("circle_thin", 14, "table-button");

                    alarm_keep.setOnAction((javafx.event.ActionEvent event) -> {
                        set_alaem_state(item, item.getAlarm_state() == 0 ? Individuals.ALARM_STATE_KEEP : Individuals.ALARM_STATE_NORMAL);
                        item.icon.set(item.icon.get().equals("\uE802") ? "\uE803" : "\uE802");
                    });
                    alarm_keep.textProperty().bindBidirectional(item.icon);

                    MyButtonFont alarm_delete = new MyButtonFont("trash", 14, "table-button");
                    alarm_delete.setOnAction((javafx.event.ActionEvent event) -> {
                        set_alaem_state(item, Individuals.ALARM_STATE_CHEKED);
                        this.getTableView().getItems().remove(getIndex());
                    });

                    hbox.getChildren().addAll(checkBox, alarm_delete, alarm_keep);

                    setGraphic(hbox);

                }
            };
            return cell;
        });

        type_column.setCellFactory((TableColumn<Gate_Alarm, String> param) -> {
            TableCell<Gate_Alarm, String> cell = new TableCell<Gate_Alarm, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null) {
                        setStyle(null);
                        setText(null);
                        setGraphic(null);
                        return;
                    }
                    Font font = Font.loadFont(GateBass.class.getResource("resourse/agtp_font.ttf").toExternalForm(), 14);
                    this.setFont(font);

                    this.setText(AGTPFont.Icons.valueOf(item.equals("car") ? "truck" : "user").getIcon());
                    setStyle("-fx-text-fill : " + (item.equals("temporary") ? "#ff0000;" : "#000000;"));
                    setTextAlignment(TextAlignment.CENTER);
                    setAlignment(Pos.CENTER);
                }
            };
            return cell;
        });

        all_check.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            for (Gate_Alarm ga : tableView.getItems()) {
                ga.is_cheked.set(newValue);
            }
        });

        refresh.setOnAction((ActionEvent event) -> {
            refresh_table();
        });
        edit.setOnAction((ActionEvent event) -> {
            on_action(true);
        });

        review.setOnAction((ActionEvent event) -> {
            on_action(false);
        });

        all_delete.setOnAction((ActionEvent event) -> {
            set_alarm_state_groups(Individuals.ALARM_STATE_CHEKED);
            tableView.getItems().clear();
        });
        all_keep.setOnAction((ActionEvent event) -> {
            set_alarm_state_groups(all_keep.getText().equals("\uE802") ? Individuals.ALARM_STATE_KEEP : Individuals.ALARM_STATE_NORMAL);
            all_keep.setText(all_keep.getText().equals("\uE802") ? "\uE803" : "\uE802");
        });

        refresh_table();
    }

    @Override
    public void show_And_Wait() {
        refresh_table();
        super.show_And_Wait();
    }

    private void set_alaem_state(Gate_Alarm item, Short alarm_state) {
        item.setAlarm_stat(alarm_state);
        if (item.getAlarm_type().equals("car")) {
            Cars cars = databaseHelper.carDao.queryForId(item.getIds());
            cars.setAlarm_state(alarm_state);
            databaseHelper.carDao.createOrUpdate(cars);
        } else {
            Individuals individuals = databaseHelper.individualsDao.queryForId(item.getIds());
            individuals.setAlarm_state(alarm_state);
            databaseHelper.individualsDao.createOrUpdate(individuals);
        }
    }

    private void set_alarm_state_groups(Short alarm_state) {
        List<Individuals> individuals_del = new ArrayList<>();
        List<Cars> cars_del = new ArrayList<>();
        for (Gate_Alarm ga : tableView.getItems()) {
            if (ga.is_cheked.get()) {
                ga.icon.set(alarm_state == Individuals.ALARM_STATE_KEEP ? "\uE803" : "\uE802");
                if (ga.getAlarm_type().equals("car")) {
                    Cars cars = databaseHelper.carDao.queryForId(ga.getIds());
                    cars.setAlarm_state(alarm_state);
                    cars_del.add(cars);
                } else {
                    Individuals individuals = databaseHelper.individualsDao.queryForId(ga.getIds());
                    individuals.setAlarm_state(alarm_state);
                    individuals_del.add(individuals);
                }
            }
        }
        try {
            databaseHelper.carDao.insertList(cars_del);
            databaseHelper.individualsDao.insertList(individuals_del);
        } catch (SQLException ex) {
            Logger.getLogger(Fxml_Alarm_List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void on_action(boolean editable) {
        if (tableView.getSelectionModel().getFocusedIndex() == -1) {
            return;
        }
        Gate_Alarm ga = tableView.getSelectionModel().getSelectedItem();
        if (ga.getAlarm_type().equals("car")) {
            if (!editable || (editable && Permission.isAcces(Permission.CAR_INSERT))) {
                my_action.get(databaseHelper.carDao.queryForId(ga.getIds()), editable);
                return;
            }
        } else if (!editable || (editable && Permission.isAcces(Permission.INDIVIDUAL_INSERT))) {
            my_action.get(databaseHelper.individualsDao.queryForId(ga.getIds()), editable);
            return;
        }
        UtilsMsg.show("دسترسی شما محدود می باشد.", "هشدار", false, thisStage);
    }

    private void add_car_alarm(String query, String sub_query, String alarm_info) {
        tableView.getItems().addAll(
                databaseHelper.gate_AlarmDao.rawResults(query.replace("ALARM_INFO", alarm_info).replace("WHERE_SEARCH_QUERY", sub_query))
        );
    }

    private void refresh_table() {
        set_deliver_date_for_temporary();

        tableView.getItems().clear();

        String query_base
                = "FROM individuals \n"
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
                + ") workhistory_j ON workhistory_j.individuals_id = individuals.id\n";

        String sub_query = MyTime.get_Now();

        String query_individual
                = "SELECT individuals.id ids, individuals.card_id , '>' || individuals.first_name || ' ' || individuals.last_name || '>' || ' ' || '< شماره ملی : ' || individuals.national_id || '<' datail , 'individual' alarm_type , 'ALARM_INFO' alrm_info , individuals.alarm_state alarm_stat\n"
                + query_base
                + "WHERE (( individuals.alarm_state = 0 AND card_delivery_date IS NULL AND WHERE_SEARCH_QUERY ) OR individuals.alarm_state = 1) AND gate_type != 0\n";

        add_car_alarm(
                query_individual,
                "card_expiration_date = '" + sub_query + "'",
                "expiration");

        query_individual
                = "SELECT cars.id ids , cars.card_id , '>' || cars.car_name || '>' || ' ' || '< شماره شاسی : ' || cars.shasi_number || '<' datail , 'car' alarm_type , 'ALARM_INFO' alrm_info , cars.alarm_state alarm_stat\n"
                + "FROM cars\n"
                + "LEFT OUTER JOIN\n"
                + "(SELECT individualReplica.* FROM individualReplica\n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = individualReplica.history_id\n"
                + ") individualReplica_J ON individualReplica_J.Individual_id =  cars.id\n"
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
                + " ) driver_info ON driver_info.id = carHistory.workHistory_id\n"
                + ") carhistory_j ON carhistory_j.car_id = cars.id\n"
                + "WHERE ( cars.alarm_state = 0 AND card_delivery_date IS NULL AND WHERE_SEARCH_QUERY ) OR cars.alarm_state = 1\n";

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

    }

    private void set_deliver_date_for_temporary() {
        String query_base
                = "SELECT workhistory.* FROM workhistory\n"
                + "LEFT OUTER JOIN history history_j1 ON history_j1.id = workhistory.employment_date_id\n"
                + "LEFT OUTER JOIN history history_j2 ON history_j2.id = workhistory.card_issued_date_id\n"
                + "LEFT OUTER JOIN history history_j3 ON history_j3.id = workhistory.card_expiration_date_id\n"
                + "LEFT OUTER JOIN history history_j4 ON history_j4.id = workhistory.card_delivery_date_id\n"
                + "LEFT OUTER JOIN companies companies_j ON companies_j.id = workhistory.companies_id\n"
                + "WHERE SEARCH_QUERY AND history_j4.date is null AND gate_type = 0\n".replace("SEARCH_QUERY", "history_j3.date <= '" + MyTime.get_Now() + "'");

        List<WorkHistory> workHistorys = databaseHelper.workHistoryDao.rawResults(query_base);
        History h_now = new MyTime().writeAndGetNow();
        for (WorkHistory wh : workHistorys) {
            wh.setCardDeliveryDate(h_now);
        }
        try {
            databaseHelper.workHistoryDao.insertList(workHistorys);
        } catch (SQLException ex) {
            Logger.getLogger(Fxml_Alarm_List.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
