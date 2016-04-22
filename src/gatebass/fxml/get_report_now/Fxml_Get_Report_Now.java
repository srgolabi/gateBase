/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.get_report_now;

import gatebass.GateBass;
import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Companies;
import gatebass.utils.ParentControl;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Get_Report_Now extends ParentControl {

    @FXML
    private Label individual_total_sum_1;
    @FXML
    private Label individual_active_sum_1;
    @FXML
    private Label individual_de_active_sum_1;

    @FXML
    private Label car_total_sum_1;
    @FXML
    private Label car_active_sum_1;
    @FXML
    private Label car_de_active_sum_1;

    @FXML
    private Label temporary_total_sum;
    @FXML
    private Label temporary_active_sum;
    @FXML
    private Label temporary_de_active_sum;

    @FXML
    public StackPane root;

    @FXML
    private VBox company_name;
    @FXML
    private VBox individual_total;
    @FXML
    private VBox individual_active;
    @FXML
    private VBox individual_de_active;

    @FXML
    private VBox carf_total;
    @FXML
    private VBox car_active;
    @FXML
    private VBox car_de_active;

    @FXML
    private Label individual_total_sum;
    @FXML
    private Label individual_active_sum;
    @FXML
    private Label individual_de_active_sum;

    @FXML
    private Label carf_total_sum;
    @FXML
    private Label car_active_sum;
    @FXML
    private Label car_de_active_sum;

    String query_individual;
    String query_car;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        individual_total_sum_1.textProperty().bind(individual_total_sum.textProperty());
        individual_active_sum_1.textProperty().bind(individual_active_sum.textProperty());
        individual_de_active_sum_1.textProperty().bind(individual_de_active_sum.textProperty());
        car_total_sum_1.textProperty().bind(carf_total_sum.textProperty());
        car_active_sum_1.textProperty().bind(car_active_sum.textProperty());
        car_de_active_sum_1.textProperty().bind(car_de_active_sum.textProperty());

        set_style(
                "-fx-border-color: #000000; -fx-border-width: 0 0 1 0; -fx-max-width: infinity; -fx-font-family: 'B Yekan'; -fx-min-height: 30; -fx-alignment: center;",
                individual_total_sum_1, individual_active_sum_1, individual_de_active_sum_1,
                car_total_sum_1, car_active_sum_1, car_de_active_sum_1);

        set_style(
                " -fx-max-width: infinity; -fx-font-family: 'B Yekan'; -fx-min-height: 30; -fx-alignment: center;",
                temporary_total_sum, temporary_active_sum, temporary_de_active_sum);

        query_individual
                = "SELECT individuals.id , individuals.card_id , individuals.first_name || ' ' || individuals.last_name full_name, workhistory_j.car_history_id,\n"
                + "count(individualReplica_J.Individual_id)  replica_count , count(individualWarning_J.Individual_id) warning_count , individuals.father_first_name , individuals.national_id ,\n"
                + "individuals.id_number , history_J1.date soldiery_start_info , history_J2.date soldiery_end_info , individuals.soldiery_id , individuals.soldiery_location , individuals.soldiery_unit , individuals.soldiery_exempt ,\n"
                + "history_J3.date birth_day_info , individuals.birth_state , individuals.issued , individuals.serial_number ,individuals.field_of_study || ' - ' || individuals.academic_degree study_info ,\n"
                + "individuals.mobile || ' - ' || individuals.phone_number phone_info, individuals.dependants , individuals.series_id , individuals.state_address || ' - ' || individuals.city_address || ' - ' || individuals.street_address address ,\n"
                + "individuals.postal_code , individuals.nationality , individuals.din || ' - ' || individuals.religion religion_info , workhistory_j.employment_date , workhistory_j.card_issued_date , workhistory_j.card_expiration_date , workhistory_j.card_delivery_date , GROUP_CONCAT('  ' || workhistory_j.company_fa || '  ') company_info ,\n"
                + "individuals.criminal_records , individuals.veteran_status , individuals.comments , individuals.picture_address , individuals.have_soe_pishine , individuals.filesPatch , individuals.logs \n"
                + "FROM individuals \n"
                + "LEFT OUTER JOIN history history_J1 ON individuals.soldiery_start_date = history_J1.id\n"
                + "LEFT OUTER JOIN history history_J2 ON individuals.soldiery_end_date = history_J2.id\n"
                + "LEFT OUTER JOIN history history_J3 ON individuals.birth_day = history_J3.id\n"
                + "LEFT OUTER JOIN \n"
                + "(SELECT individualReplica.* FROM individualReplica\n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = individualReplica.history_id\n"
                + ") individualReplica_J ON individualReplica_J.Individual_id =  individuals.id\n"
                + "LEFT OUTER JOIN \n"
                + "(SELECT individualWarning.* FROM individualWarning\n"
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
                + "WHERE WHERE_SEARCH_QUERY\n"
                + "GROUP BY workhistory_j.id\n"
                + "ORDER BY card_id desc";

        query_car
                = "SELECT cars.id , cars.card_id , cars.car_name , cars.shasi_number , cars.model , cars.comments , cars.logs , carhistory_j.driver_name ,\n"
                + "count(individualReplica_J.car_id)  replica_count , GROUP_CONCAT('  ' || carhistory_j.company_fa || '  ') company_info ,\n"
                + "carhistory_j.bimeh_date , carhistory_j.card_expiration_date , carhistory_j.card_issued_date , carhistory_j.card_delivery_date , carhistory_j.certificate_date ,\n"
                + "carhistory_j.workHistory_id\n"
                + "FROM cars\n"
                + "LEFT OUTER JOIN\n"
                + "(SELECT individualReplica.* FROM individualReplica\n"
                + " LEFT OUTER JOIN history history_j1 ON history_j1.id = individualReplica.history_id\n"
                + ") individualReplica_J ON individualReplica_J.car_id =  cars.id\n"
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
                + "WHERE WHERE_SEARCH_QUERY\n"
                + "GROUP BY carhistory_j.id\n"
                + "ORDER BY card_id desc";
    }

    public void set_value() {

        List<Companies> companies = GateBass.databaseHelper.companiesDao.rawResults("SELECT * FROM companies WHERE active = 1 AND is_deleted = 0");
        for (Companies c : companies) {

            Label company_name = new Label(c.getCompany_fa());
            company_name.setStyle("-fx-border-color: #000000; -fx-border-width: 0 1 1 0; -fx-max-width: infinity; -fx-font-family: 'B Yekan'; -fx-font-size: 9.1; -fx-min-height: 22;");

            this.company_name.getChildren().add(company_name);

            String query_base = "workhistory_j.companies_id = " + c.getId() + " AND gate_type != 0";
            create_individual(individual_total, query_base, "");
            create_individual(individual_active, query_base, " AND card_delivery_date is null");
            create_individual(individual_de_active, query_base, " AND card_delivery_date is not null");

            query_base = "carhistory_j.companies_id = " + c.getId();
            create_car(carf_total, query_base, "");
            create_car(car_active, query_base, " AND card_delivery_date is null");
            create_car(car_de_active, query_base, " AND card_delivery_date is not null");
        }

        sum(individual_total, individual_total_sum);
        sum(individual_active, individual_active_sum);
        sum(individual_de_active, individual_de_active_sum);
        sum(carf_total, carf_total_sum);
        sum(car_active, car_active_sum);
        sum(car_de_active, car_de_active_sum);

        temporary_total_sum.setText(databaseHelper.individuals_jDao.rawResults(query_individual.replace("WHERE_SEARCH_QUERY", "gate_type = 0")).size() + "");
        temporary_active_sum.setText(databaseHelper.individuals_jDao.rawResults(query_individual.replace("WHERE_SEARCH_QUERY", "gate_type = 0  AND card_delivery_date is null")).size() + "");
        temporary_de_active_sum.setText(databaseHelper.individuals_jDao.rawResults(query_individual.replace("WHERE_SEARCH_QUERY", "gate_type = 0  AND card_delivery_date is not null")).size() + "");

    }

    private void create_individual(VBox vb, String query_base, String query_temp) {
        Label label = new Label(databaseHelper.individuals_jDao.rawResults(query_individual.replace("WHERE_SEARCH_QUERY", query_base + query_temp)).size() + "");
        label.setStyle("-fx-border-color: #000000; -fx-border-width: 0 1 1 0; -fx-max-width: infinity; -fx-font-family: 'B Yekan'; -fx-font-size: 9.1; -fx-min-height: 22; -fx-alignment: center;");
        vb.getChildren().add(label);
    }

    private void create_car(VBox vb, String query_base, String query_temp) {
        Label label = new Label(databaseHelper.cars_jDao.rawResults(query_car.replace("WHERE_SEARCH_QUERY", query_base + query_temp)).size() + "");
        label.setStyle("-fx-border-color: #000000; -fx-border-width: 0 1 1 0; -fx-max-width: infinity; -fx-font-family: 'B Yekan'; -fx-font-size: 9.1; -fx-min-height: 22; -fx-alignment: center;");
        vb.getChildren().add(label);
    }

    private void sum(VBox vBox, Label label) {
        int sum = 0;
        for (Node node : (vBox.getChildren())) {
            sum = sum + Integer.parseInt(((Label) node).getText());
        }
        label.setText(sum + "");
        label.setStyle("-fx-border-color: #000000; -fx-border-width: 0 1 1 0; -fx-max-width: infinity; -fx-background-color:  #FFB74D; -fx-min-height: 22; -fx-alignment: center;");
    }

    private void set_style(String style, Label... labels) {
        for (Label l : labels) {
            l.setStyle(style);
        }
    }
}
