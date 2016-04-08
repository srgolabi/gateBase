/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.get_report;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Base;
import gatebass.dataBase.tables.Companies;
import gatebass.dataBase.tables.Permission;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.MenuTableInit;
import gatebass.myControl.tableView.MyColumnTable;
import gatebass.utils.MyTime;
import gatebass.utils.ParentControl;
import gatebass.utils.PersianCalendar;
import gatebass.utils.TextFiledLimited;
import gatebass.utils.UtilsStage;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Get_Report extends ParentControl {

    @FXML
    private StackPane main_page;
    @FXML
    private CheckBox valid_card;

    @FXML
    private RadioButton report_type_individual;
    @FXML
    private RadioButton report_type_car;
    @FXML
    private RadioButton report_type_individual_list;
    @FXML
    private RadioButton report_type_car_list;

    @FXML
    private StackPane fields_page_individual;
    @FXML
    private TextField fields_text_individual;
    @FXML
    private MyButtonFont fields_text_clear_individual;
    @FXML
    private MyButtonFont fields_show_list_individual;
    @FXML
    private StackPane fields_page_car;
    @FXML
    private TextField fields_text_car;
    @FXML
    private MyButtonFont fields_text_clear_car;
    @FXML
    private MyButtonFont fields_show_list_car;

    @FXML
    private HBox company_page;
    @FXML
    private TextField company_text;
    @FXML
    private MyButtonFont company_text_clear;
    @FXML
    private MyButtonFont company_show_list;

    @FXML
    private HBox number_page_root;
    @FXML
    private RadioButton math_number_less_than;
    @FXML
    private RadioButton math_number_less_than_equals;
    @FXML
    private RadioButton math_number_equals;
    @FXML
    private RadioButton math_number_greater_than;
    @FXML
    private RadioButton math_number_greater_than_equals;
    @FXML
    private TextField number_text;
    @FXML
    private VBox number_page;

    @FXML
    private HBox date_page;
    @FXML
    private TextField date_day;
    @FXML
    private TextField date_month;
    @FXML
    private TextField date_year;

    @FXML
    private HBox string_page_root;
    @FXML
    private HBox other_operator_page;
    @FXML
    private RadioButton other_operator_first;
    @FXML
    private RadioButton other_operator_second;
    @FXML
    private VBox string_page;
    @FXML
    private TextField string_text;

    @FXML
    private HBox veteran_status_page;
    @FXML
    private RadioButton veteran_payan_khedmat;
    @FXML
    private RadioButton veteran_namalom;
    @FXML
    private RadioButton veteran_moaf;

    @FXML
    private TableView<Query_Base> query_table;
    @FXML
    private TableColumn<Query_Base, Query_Base> query_table_column;
    @FXML
    private TableView<Query_Base> field_table_individual;
    @FXML
    private TableView<Query_Base> field_table_car;
    @FXML
    private TableView<Companies> company_table;

    @FXML
    private MyButtonFont close_edit;
    @FXML
    private MyButtonFont add_query_to_table;
    @FXML
    private MyButtonFont delete_all_query;
    @FXML
    private MyButtonFont done;

    private MyTime date_time;

    private Query_Base selected_field;
    private IntegerProperty selected_item_for_edit = new SimpleIntegerProperty(-1);

    public String query_for_search;

    private List<Query_Base> query_list_individual;
    private List<Query_Base> query_list_car;

    private static String prefix_type = "";

    @Override
    public void setStage(Stage s) {
        super.setStage(s);

        query_list_car = new ArrayList<>();
        query_list_individual = new ArrayList<>();

        close_edit.init("ccw", 18);
        add_query_to_table.init("doc_new", 18);
        delete_all_query.init("trash", 18);
        done.init("level_down", 18);

        close_edit.visibleProperty().bind(selected_item_for_edit.greaterThan(-1));

        close_edit.setOnAction((ActionEvent event) -> {
            selected_item_for_edit.set(-1);
        });
        selected_item_for_edit.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() == -1) {
                add_query_to_table.changeText("doc_new");
            } else {
                add_query_to_table.changeText("edit");
            }
        });

        String query_individual
                = "SELECT individuals.id , individuals.card_id , individuals.first_name , individuals.last_name , workhistory_j.car_history_id,\n"
                + "count(individualReplica_J.Individual_id)  replica_count , count(individualWarning_J.Individual_id) warning_count , individuals.father_first_name , individuals.national_id ,\n"
                + "individuals.id_number , history_J1.date soldiery_start_info , history_J2.date soldiery_end_info , individuals.soldiery_id , individuals.soldiery_location , individuals.soldiery_unit , individuals.soldiery_exempt ,\n"
                + "history_J3.date birth_day_info , individuals.birth_state , individuals.issued , individuals.serial_number ,individuals.field_of_study || ' - ' || individuals.academic_degree study_info ,\n"
                + "individuals.mobile || ' - ' || individuals.phone_number phone_info, individuals.dependants , individuals.series_id , individuals.state_address || ' - ' || individuals.city_address || ' - ' || individuals.street_address address ,\n"
                + "individuals.postal_code , individuals.nationality , individuals.din || ' - ' || individuals.religion religion_info , workhistory_j.employment_date , workhistory_j.card_issued_date , workhistory_j.card_expiration_date , workhistory_j.card_delivery_date , GROUP_CONCAT(DISTINCT workhistory_j.company_fa) company_info ,\n"
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
                + " GROUP_BY_QUERY\n"
                + ") workhistory_j ON workhistory_j.individuals_id = individuals.id\n"
                + "WHERE_VALID_CARD_QUERY WHERE_SEARCH_QUERY\n"
                + "GROUP BY individuals.id\n"
                + "HAVING_SEARCH_QUERY";

        String query_car
                = "SELECT cars.id , cars.card_id , cars.car_name , cars.shasi_number , cars.model , cars.comments , cars.logs , carhistory_j.driver_name ,\n"
                + "count(individualReplica_J.Individual_id)  replica_count , GROUP_CONCAT(DISTINCT carhistory_j.company_fa) company_info ,\n"
                + "carhistory_j.bimeh_date , carhistory_j.card_expiration_date , carhistory_j.card_issued_date , carhistory_j.card_void_date , carhistory_j.certificate_date ,\n"
                + "carhistory_j.workHistory_id\n"
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
                + "  ) driver_info ON driver_info.id = carHistory.workHistory_id\n"
                + " GROUP_BY_QUERY\n"
                + ") carhistory_j ON carhistory_j.car_id = cars.id\n"
                + "WHERE_VALID_CARD_QUERY WHERE_SEARCH_QUERY\n"
                + "GROUP BY cars.id"
                + "HAVING_SEARCH_QUERY";

        TextFiledLimited.setEnterFocuse(date_day, date_month, date_year, date_year);
        date_time = new MyTime(date_year, date_month, date_day);

        company_show_list.init("down_dir", 16);
        company_text_clear.init("cancel", 16);
        String query = "SELECT * FROM companies WHERE active = 1 AND is_deleted = 0 ORDER BY company_fa ASC";
        MenuTableInit.companiesInit(query, company_text, company_table, company_show_list, company_text_clear);

        fields_table_individual_init();
        fields_table_car_init();
        query_table_init();

        selected_field = new Query_Base();

        report_type_individual.setDisable(!Permission.isAcces(Permission.INDIVIDUAL_GETREPORT));
        report_type_car.setDisable(!Permission.isAcces(Permission.CAR_GETREPORT));
        report_type_individual_list.setDisable(report_type_individual.isDisable());
        report_type_car_list.setDisable(report_type_car.isDisable());

        fields_page_individual.visibleProperty().bind(report_type_individual.selectedProperty());
        fields_page_car.visibleProperty().bind(report_type_car.selectedProperty());

        main_page.disableProperty().bind(report_type_individual_list.selectedProperty().or(report_type_car_list.selectedProperty()));
        add_query_to_table.disableProperty().bind(main_page.disableProperty());
        delete_all_query.disableProperty().bind(main_page.disableProperty());
        done.disableProperty().bind(Bindings.isEmpty(query_table.itemsProperty().get()).and(main_page.disableProperty().not()));

        fields_text_individual.setText("شماره ملی");
        fields_text_individual.setText("");
        fields_text_car.setText("");
        company_page.visibleProperty().bind(selected_field.field_type.isEqualTo("company"));
        string_page_root.visibleProperty().bind(selected_field.field_type.isEqualTo("string").or(selected_field.field_type.isEqualTo("haveOrNot")));
        date_page.visibleProperty().bind(selected_field.field_type.isEqualTo("date"));
        number_page_root.visibleProperty().bind(selected_field.field_type.isEqualTo("date").or(selected_field.field_type.isEqualTo("int")));
        number_page.visibleProperty().bind(selected_field.field_type.isEqualTo("int"));
        string_page.visibleProperty().bind(selected_field.field_type.isEqualTo("string"));
        other_operator_page.visibleProperty().bind(selected_field.field_type.isEqualTo("string").or(selected_field.field_type.isEqualTo("haveOrNot")));
        veteran_status_page.visibleProperty().bind(selected_field.field_type.isEqualTo("veteran_status"));
        other_operator_first.textProperty().bind(selected_field.other_operator_first);
        other_operator_second.textProperty().bind(selected_field.other_operator_second);

        report_type_car.setOnAction((ActionEvent event) -> {
            String temp = fields_text_car.getText();
            fields_text_car.setText("");
            fields_text_car.setText(temp);
            query_list_individual = new ArrayList<>(query_table.getItems());
            query_table.getItems().setAll(query_list_car);
        });

        report_type_individual.setOnAction((ActionEvent event) -> {
            System.out.println("asdasdsdd");
            String temp = fields_text_individual.getText();
            fields_text_individual.setText("");
            fields_text_individual.setText(temp);
            query_list_car = new ArrayList<>(query_table.getItems());
            query_table.getItems().setAll(query_list_individual);
        });

        add_query_to_table.setOnAction((ActionEvent event) -> {

            Query_Base temp = new Query_Base();
            temp.copy_Query_Base(selected_field);
            String OPERATOR = "", VALUE = "";
            switch (temp.field_type.get()) {
                case "date":
                    if (date_time.isDamage("تاریخ به درستی پر نشده.", thisStage)) {
                        return;
                    } else if (!date_time.isFull()) {
                        UtilsStage.showMsg("تاریخ به درستی پر نشده.", "خطا", false, thisStage);
                        return;
                    }
                    temp.value = date_time.getDate();
                    temp.operator.set(((RadioButton) math_number_less_than.getToggleGroup().getSelectedToggle()).getText() + " 'value'");
                    VALUE = "13" + date_time.getDate();
                    OPERATOR = temp.operator.get().contains(">") ? "بعد از" : temp.operator.get().contains("<") ? "قبل از" : "تاریخ";
                    break;
                case "haveOrNot":
                    temp.value = other_operator_first.isSelected() ? "1" : "0";
                    temp.operator.set(other_operator_first.isSelected() ? "= 'value'" : "!= 'value'");
                    OPERATOR = other_operator_first.isSelected() ? "دارای" : "فاقد";
                    break;
                case "string":
                    if (string_text.getText().isEmpty()) {
                        UtilsStage.showMsg("فیلد مقدار را پر کنید.", "خطا", false, thisStage);
                        string_text.requestFocus();
                        return;
                    }
                    temp.value = string_text.getText();
                    temp.operator.set(other_operator_first.isSelected() ? "LIKE '%value%'" : "LIKE 'value'");
                    VALUE = string_text.getText();
                    OPERATOR = other_operator_first.isSelected() ? "شامل" : "برابر";
                    break;
                case "int":
                    if (number_text.getText().isEmpty()) {
                        UtilsStage.showMsg("فیلد تعداد را پر کنید.", "خطا", false, thisStage);
                        number_text.requestFocus();
                        return;
                    }
                    temp.value = number_text.getText();
                    temp.operator.set(((RadioButton) math_number_less_than.getToggleGroup().getSelectedToggle()).getText() + " 'value'");
                    VALUE = number_text.getText();
                    OPERATOR = temp.operator.get().contains(">") ? "بیشتر از" : temp.operator.get().contains("<") ? "کمتر از" : "برابر";
                    break;
                case "company":
                    Companies c = databaseHelper.companiesDao.getFirst("company_fa", company_text.getText());
                    if (company_text.getText().isEmpty()) {
                        UtilsStage.showMsg("فیلد نام شرکت را پر کنید.", "خطا", false, thisStage);
                        company_text.requestFocus();
                        return;
                    } else if (c == null) {
                        UtilsStage.showMsg("فیلد نام شرکت به بدرستی پر نشده.", "خطا", false, thisStage);
                        company_text.requestFocus();
                        return;
                    } else {
                        temp.value = c.getId() + "";
                        temp.operator.set("= 'value'");
                        VALUE = c.getCompany_fa();
                    }
                    break;
                case "veteran_status":
                    temp.value = veteran_payan_khedmat.isSelected() ? "0" : veteran_namalom.isSelected() ? "1" : "2";
                    temp.operator.set("= 'value'");
                    OPERATOR = temp.value.equals("0") ? "دارای کارت پایان خدمت می باشند." : temp.value.equals("1") ? "از خدمت معاف شده اند." : "وضعیت خدمتی نامعلوم داردند.";
                    break;

            }
            temp.operator.set(temp.culomn_query.get() + " " + temp.operator.get().replace("value", temp.value));
            temp.table_title.set(temp.table_title_fix.get().replace("OPERATOR", OPERATOR).replace("VALUE", VALUE));

            if (selected_item_for_edit.get() != -1) {
                query_table.getItems().set(selected_item_for_edit.get(), temp);
                selected_item_for_edit.set(-1);
            } else {
                query_table.getItems().add(temp);
            }

        });

        done.setOnAction((ActionEvent event) -> {
            PersianCalendar pc = new PersianCalendar();
            query_for_search = query_individual.replace("GROUP_BY_QUERY", valid_card.isSelected() ? "" : "");
            if (main_page.isDisable()) {
                if (report_type_individual_list.isSelected()) {
                    query_for_search = query_for_search.replace("WHERE_SEARCH_QUERY", "").replace("HAVING_SEARCH_QUERY", "");

                } else if (report_type_car_list.isSelected()) {
                    query_for_search = query_for_search.replace("WHERE_SEARCH_QUERY", "").replace("HAVING_SEARCH_QUERY", "");
                }
                query_for_search = query_for_search.replace("WHERE_VALID_CARD_QUERY", valid_card.isSelected()
                        ? "WHERE card_expiration_date >= " + "'" + pc.year2dig() + "/" + pc.month() + "/" + pc.day() + "'"
                        : "");
            } else {
                String where_q = "";
                String having_q = "";
                for (Query_Base qb : query_table.getItems()) {
                    if (qb.field_type.get().equals("int")) {
                        having_q = having_q + (qb.and_or.get().equals("و  ") ? " AND " : qb.and_or.get().equals("یا  ") ? " OR " : "") + qb.open_bracket.get() + qb.operator.get() + qb.close_bracket.get();
                    } else {
                        where_q = where_q + (qb.and_or.get().equals("و  ") ? " AND " : qb.and_or.get().equals("یا  ") ? " OR " : "") + qb.open_bracket.get() + qb.operator.get() + qb.close_bracket.get();
                    }
                }

                query_for_search = query_for_search.replace("WHERE_VALID_CARD_QUERY", valid_card.isSelected()
                        ? "WHERE card_expiration_date >= " + "'" + pc.year() + "/" + pc.month() + "/" + pc.day() + "' AND "
                        : "WHERE ");

                if (!having_q.isEmpty()) {
                    having_q = "HAVING " + having_q;
                }
                if (report_type_individual.isSelected()) {
                    query_for_search = query_for_search.replace("WHERE_SEARCH_QUERY", where_q).replace("HAVING_SEARCH_QUERY", having_q);
                } else if (report_type_car.isSelected()) {
                    query_for_search = query_car.replace("WHERE_SEARCH_QUERY", where_q).replace("HAVING_SEARCH_QUERY", having_q);
                }
            }

            thisStage.close();
        });
    }

    public void fields_table_individual_init() {
        prefix_type = "افرادی که ";
        List<Query_Base> list_field_individual = new ArrayList<>();
        list_field_individual.add(new Query_Base("شماره ملی", "string", "national_id"));
        list_field_individual.add(new Query_Base("نام", "string", "first_name"));
        list_field_individual.add(new Query_Base("نام خانوادگی", "string", "last_name"));
        list_field_individual.add(new Query_Base("نام پدر", "string", "father_first_name"));
        list_field_individual.add(new Query_Base("تاریخ تولد", "date", "birth_day_info"));
        list_field_individual.add(new Query_Base("محل صدور", "string", "issued"));
        list_field_individual.add(new Query_Base("استان محل تولد", "string", "birth_state"));
        list_field_individual.add(new Query_Base("اطلاعات تحصیلی", "string", "study_info"));
        list_field_individual.add(new Query_Base("تابعیت", "string", "nationality"));
        list_field_individual.add(new Query_Base("مذهب", "string", "religion_info"));
        list_field_individual.add(new Query_Base("وضعیت خدمت", "solidary", "veteran_status"));
        list_field_individual.add(new Query_Base("تاریخ اعزام به خدمت", "date", "soldiery_start_info"));
        list_field_individual.add(new Query_Base("تاریخ خاتمه خدمت", "date", "soldiery_end_info"));
        list_field_individual.add(new Query_Base("یگان خدمتی", "string", "soldiery_unit"));
        list_field_individual.add(new Query_Base("محل خدمت", "string", "soldiery_location"));
        list_field_individual.add(new Query_Base("آدرس", "string", "address"));
        list_field_individual.add(new Query_Base("متن توضیحات", "string", "individuals.comments"));
        list_field_individual.add(new Query_Base("توضیحات", "haveOrNot", "individuals.comments"));
        list_field_individual.add(new Query_Base("گواهی عدم سوء پیشینه", "haveOrNot", "have_soe_pishine"));
        list_field_individual.add(new Query_Base("عکس", "haveOrNot", "picture_address"));
        list_field_individual.add(new Query_Base("تاریخ استخدام", "date", "workhistory_j.employment_date"));
        list_field_individual.add(new Query_Base("تاریخ صدور کارت تردد", "date", "workhistory_j.card_issued_date"));
        list_field_individual.add(new Query_Base("تاریخ انقضاء کارت تردد", "date", "workhistory_j.card_expiration_date"));
//        list_field.add(new Query_Base("راننده", "haveOrNot"));
        list_field_individual.add(new Query_Base("کارتهای صادره برای شرکت", "company", "workhistory_j.companies_id"));
//        list_field.add(new Query_Base("وضعیت کارت", "haveOrNot"));
        list_field_individual.add(new Query_Base("اخطار", "haveOrNot", "warning_count"));
        list_field_individual.add(new Query_Base("تعداد اخطار", "int", "warning_count"));
        list_field_individual.add(new Query_Base("المثنی", "haveOrNot", "replica_count"));
        list_field_individual.add(new Query_Base("تعداد المثنی", "int", "replica_count"));

        fields_show_list_individual.init("down_dir", 16);
        fields_text_clear_individual.init("cancel", 16);
        MyColumnTable<Query_Base> removeButtonCellFactory = new MyColumnTable<>(null, Cursor.DEFAULT);
        removeButtonCellFactory.setOnAddToMenu((Query_Base s) -> {
            fields_text_individual.setText(s.getField_name());
        });
        FilteredList<Query_Base> filteredlist = removeButtonCellFactory.init(
                field_table_individual, fields_text_individual, list_field_individual, fields_show_list_individual, fields_text_clear_individual);

        fields_text_individual.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredlist.setPredicate(s -> (newValue == null || newValue.length() == 0) ? true : s.getField_name().contains(newValue));
            if (!filteredlist.isEmpty()) {
                fields_text_individual.setStyle("-fx-text-fill: " + (field_table_individual.getItems().get(0).getField_name().equals(fields_text_individual.getText()) ? "#2e7a8c;" : "red;"));
            } else {
                fields_text_individual.setStyle("-fx-text-fill: red;");
            }
            if (!fields_text_individual.getStyle().equals("-fx-text-fill: red;")) {
                selected_field.copy_Query_Base(field_table_individual.getItems().get(0));
            }
            field_table_individual.setPrefHeight(
                    field_table_individual.getFixedCellSize() * (field_table_individual.getItems().size() > 5 ? 5 : field_table_individual.getItems().size()) + 4
            );
        });
        field_table_individual.getSelectionModel().select(0);
    }

    public void fields_table_car_init() {
        prefix_type = "خودروهایی که ";
        List<Query_Base> list_field_car = new ArrayList<>();
        list_field_car.add(new Query_Base("شماره کارت گیت باس", "string", "card_id"));
        list_field_car.add(new Query_Base("شماره شاسی", "string", "shasi_number"));
        list_field_car.add(new Query_Base("نام خودرو", "string", "car_name"));
        list_field_car.add(new Query_Base("نام راننده", "string", ""));
        list_field_car.add(new Query_Base("رنگ", "string", "carhistory_j.color"));
        list_field_car.add(new Query_Base("مدل", "string", "model"));
        list_field_car.add(new Query_Base("پلاک", "string", "carhistory_j.pellak"));
        list_field_car.add(new Query_Base("تاریخ صدور کارت تردد", "date", "carhistory_j.card_issued_date"));
        list_field_car.add(new Query_Base("تاریخ انقضاء کارت تردد", "date", "carhistory_j.card_expiration_date"));
        list_field_car.add(new Query_Base("تاریخ ابطال کارت تردد", "date", "carhistory_j.card_void_date"));
        list_field_car.add(new Query_Base("تاریخ اعتبار بیمه", "date", "carhistory_j.bimeh_date"));
        list_field_car.add(new Query_Base("تاریخ سلامت / certificate", "date", "carhistory_j.certificate_date"));
        list_field_car.add(new Query_Base("کارتهای صادره برای شرکت", "company", "carhistory_j.companies_id"));
        list_field_car.add(new Query_Base("المثنی", "haveOrNot", "replica_count"));
        list_field_car.add(new Query_Base("تعداد المثنی", "int", "replica_count"));

        fields_show_list_car.init("down_dir", 16);
        fields_text_clear_car.init("cancel", 16);
        MyColumnTable<Query_Base> removeButtonCellFactory = new MyColumnTable<>(null, Cursor.DEFAULT);
        removeButtonCellFactory.setOnAddToMenu((Query_Base s) -> {
            fields_text_car.setText(s.getField_name());
        });
        FilteredList<Query_Base> filteredlist = removeButtonCellFactory.init(
                field_table_car, fields_text_car, list_field_car, fields_show_list_car, fields_text_clear_car);

        fields_text_car.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredlist.setPredicate(s -> (newValue == null || newValue.length() == 0) ? true : s.getField_name().contains(newValue));
            if (!filteredlist.isEmpty()) {
                fields_text_car.setStyle("-fx-text-fill: " + (field_table_car.getItems().get(0).getField_name().equals(fields_text_car.getText()) ? "#2e7a8c;" : "red;"));
            } else {
                fields_text_car.setStyle("-fx-text-fill: red;");
            }
            if (!fields_text_car.getStyle().equals("-fx-text-fill: red;")) {
                selected_field.copy_Query_Base(field_table_car.getItems().get(0));
            }
            field_table_car.setPrefHeight(
                    field_table_car.getFixedCellSize() * (field_table_car.getItems().size() > 5 ? 5 : field_table_car.getItems().size()) + 4
            );
        });
        field_table_car.getSelectionModel().select(0);
    }

    private void query_table_init() {

        QueryColumnTable removeButtonCellFactory = new QueryColumnTable();
        query_table_column.setCellFactory(removeButtonCellFactory);
        removeButtonCellFactory.setOnEdit((Query_Base qb, int index) -> {
            selected_item_for_edit.set(index);
            selected_field.copy_Query_Base(qb);
            if (fields_page_individual.isVisible()) {
                fields_text_individual.setText(qb.field_name);
            } else {
                fields_text_car.setText(qb.field_name);
            }
            switch (selected_field.field_type.get()) {
                case "date":
                    String[] arr = selected_field.value.split("/");
                    date_year.setText(arr[0]);
                    date_month.setText(arr[1]);
                    date_day.setText(arr[2]);
                    math_number_equals.getToggleGroup().selectToggle(
                            selected_field.value.equals(">") ? math_number_greater_than
                            : selected_field.value.equals(">=") ? math_number_greater_than_equals
                            : selected_field.value.equals("<") ? math_number_less_than
                            : selected_field.value.equals("=<") ? math_number_less_than_equals : math_number_equals
                    );
                    break;
                case "haveOrNot":
                    other_operator_first.getToggleGroup().selectToggle(
                            selected_field.value.equals("1") ? other_operator_first : other_operator_second);
                    break;
                case "string":
                    string_text.setText(selected_field.value);
                    other_operator_first.getToggleGroup().selectToggle(
                            selected_field.operator.get().contains("%") ? other_operator_first : other_operator_second);
                    break;
                case "int":
                    number_text.setText(selected_field.value);
                    math_number_equals.getToggleGroup().selectToggle(
                            selected_field.value.equals(">") ? math_number_greater_than
                            : selected_field.value.equals(">=") ? math_number_greater_than_equals
                            : selected_field.value.equals("<") ? math_number_less_than
                            : selected_field.value.equals("=<") ? math_number_less_than_equals : math_number_equals
                    );
                    break;
                case "company":
                    company_text.setText(selected_field.value);
                    break;
                case "veteran_status":
                    veteran_moaf.getToggleGroup().selectToggle(
                            selected_field.value.equals("0") ? veteran_payan_khedmat
                            : selected_field.value.equals("1") ? veteran_namalom : veteran_moaf);
                    break;

            }
        });
        query_table.setRowFactory((TableView<Query_Base> param) -> {
            TableRow<Query_Base> row = new TableRow<>();
            row.disableProperty().bind(selected_item_for_edit.isNotEqualTo(-1).and(selected_item_for_edit.isNotEqualTo(row.getIndex())));
            return row;
        });
        query_table.getItems().addListener((ListChangeListener.Change<? extends Query_Base> c) -> {
            selected_item_for_edit.set(-1);
            if (query_table.getItems().size() > 1 && query_table.getItems().size() < 6) {
                thisStage.setHeight(query_table.getItems().size() * 32 + 242);
            }
        });
    }

    public class Query_Base extends Base {

        public String value;

        public String field_name;
        public StringProperty culomn_query;
        public StringProperty operator;
        public StringProperty open_bracket;
        public StringProperty close_bracket;
        public StringProperty field_type;
        public StringProperty table_title_fix;
        public StringProperty table_title;
        public StringProperty other_operator_first;
        public StringProperty other_operator_second;
        public StringProperty and_or;

        public Query_Base() {

            this.culomn_query = new SimpleStringProperty("");
            this.operator = new SimpleStringProperty("");
            this.open_bracket = new SimpleStringProperty("");
            this.close_bracket = new SimpleStringProperty("");
            this.field_type = new SimpleStringProperty("");
            this.table_title = new SimpleStringProperty("");
            this.table_title_fix = new SimpleStringProperty("");
            this.other_operator_first = new SimpleStringProperty("");
            this.other_operator_second = new SimpleStringProperty("");
            this.and_or = new SimpleStringProperty("");
        }

        public void copy_Query_Base(Query_Base query_Base) {
            this.value = query_Base.value;
            this.field_name = query_Base.field_name;
            this.culomn_query.set(query_Base.culomn_query.get());
            this.operator.set(query_Base.operator.get());
            this.open_bracket.set(query_Base.open_bracket.get());
            this.close_bracket.set(query_Base.close_bracket.get());
            this.field_type.set(query_Base.field_type.get());
            this.table_title.set(query_Base.table_title.get());
            this.table_title_fix.set(query_Base.table_title_fix.get());
            this.other_operator_first.set(query_Base.other_operator_first.get());
            this.other_operator_second.set(query_Base.other_operator_second.get());
            this.and_or.set(query_Base.and_or.get());
        }

        public Query_Base(String field_name, String field_type, String culomn_query) {
            this();
            this.field_name = field_name;

            switch (field_type) {
                case "string":
                case "int":
                case "date":
                    this.table_title_fix.set(prefix_type + field_name + " آنها " + "OPERATOR VALUE " + "است.");
                    this.other_operator_first.set("شامل");
                    this.other_operator_second.set("برابر");
                    break;
                case "haveOrNot":
                    this.other_operator_first.set("دارد");
                    this.other_operator_second.set("ندارد");
                    this.table_title_fix.set(prefix_type + "OPERATOR " + field_name + " می باشند.");
                    break;
                case "company":
                    this.table_title_fix.set("کارتهایی که برای شرکت VALUE صادر شده است.");
                    break;
                case "veteran_status":
                    this.table_title_fix.set("آنهایی که " + "OPERATOR");
                    break;
            }
            this.table_title = new SimpleStringProperty(table_title_fix.get());
            this.culomn_query.set(culomn_query);
            this.field_type.set(field_type);
        }

        public Query_Base(String field_name, String field_type, String culomn_query, boolean valid_card) {
            this(field_name, field_type, culomn_query);
        }

//        public Query_Base(String field_name, String field_type, String other_operator_first, String other_operator_second) {
//            this();
//            this.field_name = field_name;
//            this.field_type.set(field_type);
//            this.other_operator_first.set(other_operator_first);
//            this.other_operator_second.set(other_operator_second);
//        }
        public String getField_name() {
            return field_name;
        }

        public Query_Base getThisFile() {
            return this;
        }
    }
}
