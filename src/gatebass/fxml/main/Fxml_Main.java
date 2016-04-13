package gatebass.fxml.main;

import static gatebass.GateBass.onCloseApp;
import static gatebass.GateBass.users;
import static gatebass.GateBass.work_list;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.alarm_list.Fxml_Alarm_List;
import gatebass.fxml.car_insert.Fxml_Car_Insert;
import gatebass.fxml.user_change_pass.Fxml_User_Change_Pass;
import gatebass.fxml.manage_company.Fxml_Manage_Company;
import gatebass.fxml.get_report.Fxml_Get_Report;
import gatebass.fxml.get_report_car_list.Fxml_Get_Report_Car_List;
import gatebass.fxml.get_report_individual_list.Fxml_Get_Report_Individual_List;
import gatebass.fxml.login.Fxml_Login;
import gatebass.fxml.individual_insert.Fxml_Individual_Insert;
import gatebass.fxml.print_preview.Fxml_Print_PreView;
import gatebass.fxml.user_manage.Fxml_User_Manage;
import gatebass.myControl.MyButtonFont;
import gatebass.register.Register;
import gatebass.utils.ParentControl;
import gatebass.utils.PersianCalendar;
import gatebass.utils.UtilsMsg;
import gatebass.utils.UtilsStage;
import java.util.Calendar;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author SR.Golabi
 */
public class Fxml_Main extends ParentControl {

    @FXML
    private Button individual_insert;
    @FXML
    private Button car_insert;
    @FXML
    private Label print_count;
    @FXML
    private MyButtonFont show_print_view;
    @FXML
    private Button report;
    @FXML
    private Button alarm_list;
    @FXML
    private Button exit;

    @FXML
    private Label timeAnimation;
    @FXML
    private Label logo;

    @FXML
    private MenuItem changeUserPass;
    @FXML
    private MenuItem changeUser;
    @FXML
    private MenuItem manageUser;
    @FXML
    private MenuItem manageCompanies;

    private boolean times = true;

    UtilsStage<Fxml_Individual_Insert> fxml_Individual_Insert;
    UtilsStage<Fxml_Get_Report> fxml_Get_Report;
    UtilsStage<Fxml_Get_Report_Individual_List> fxml_Get_Report_Individual_List;
    UtilsStage<Fxml_Get_Report_Car_List> fxml_Get_Report_Car_List;
    UtilsStage<Fxml_Car_Insert> fxml_Car_Insert;
    UtilsStage<Fxml_Alarm_List> fxml_Alarm_List;
    private static UtilsStage<Fxml_Print_PreView> fxml_Print_PreView;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        show_print_view.init("print", 22);

        manageUser.setDisable(!Permission.isAcces(Permission.USER_VIEW) || !Permission.isAcces(Permission.USER_INSERT));
        manageCompanies.setDisable(!Permission.isAcces(Permission.COMPANY_VIEW) || !Permission.isAcces(Permission.COMPANY_INSERT));
        changeUserPass.setDisable(!Permission.isAcces(Permission.CHANGE_PASS));
        print_count.visibleProperty().bind(work_list.sizeProperty().greaterThan(0));
        print_count.textProperty().bind(work_list.sizeProperty().asString());

        fxml_Print_PreView = new UtilsStage(Fxml_Print_PreView.class, true, "چاپ", Modality.APPLICATION_MODAL, thisStage.getOwner());

        fxml_Get_Report = new UtilsStage(Fxml_Get_Report.class, "گزارش گیری", Modality.APPLICATION_MODAL, thisStage);

        fxml_Individual_Insert = new UtilsStage(Fxml_Individual_Insert.class, "ثبت افراد جدید", Modality.NONE, thisStage);
        fxml_Individual_Insert.t.set_My_Action((Cars t, boolean b) -> {
            fxml_Car_Insert.t.editable.set(b);
            fxml_Car_Insert.t.car = t;
            fxml_Car_Insert.t.loadCars();
            fxml_Car_Insert.t.show_Front_Or_Wait();
        });

        fxml_Get_Report_Individual_List = new UtilsStage(Fxml_Get_Report_Individual_List.class, true, "لیست افراد", Modality.NONE, thisStage.getOwner());
        fxml_Get_Report_Individual_List.t.set_My_Action((Individuals t, boolean b) -> {
            fxml_Individual_Insert.t.editable.set(b);
            fxml_Individual_Insert.t.individual = t;
            fxml_Individual_Insert.t.loadIndividual();
            fxml_Individual_Insert.t.show_Front_Or_Wait();
        });

        fxml_Get_Report_Car_List = new UtilsStage(Fxml_Get_Report_Car_List.class, true, "لیست خودروها", Modality.NONE, thisStage.getOwner());
        fxml_Get_Report_Car_List.t.set_My_Action((Cars t, boolean b) -> {
            fxml_Car_Insert.t.editable.set(b);
            fxml_Car_Insert.t.car = t;
            fxml_Car_Insert.t.loadCars();
            fxml_Car_Insert.t.show_Front_Or_Wait();
        });

        fxml_Car_Insert = new UtilsStage(Fxml_Car_Insert.class, "سیستم اطلاعات خودرویی", Modality.NONE, thisStage);
        fxml_Car_Insert.t.set_My_Action((Individuals t, boolean b) -> {
            fxml_Individual_Insert.t.editable.set(b);
            fxml_Individual_Insert.t.individual = t;
            fxml_Individual_Insert.t.loadIndividual();
            fxml_Individual_Insert.t.show_Front_Or_Wait();
        });

        fxml_Alarm_List = new UtilsStage(Fxml_Alarm_List.class, "لیست هشدارها", Modality.APPLICATION_MODAL, thisStage);
        fxml_Alarm_List.t.set_On_Get_Car(new Fxml_Alarm_List.Get_Object() {

            @Override
            public void car(Cars car, boolean is_edit_mode) {
                fxml_Car_Insert.t.editable.set(is_edit_mode);
                fxml_Car_Insert.t.car = car;
                fxml_Car_Insert.t.loadCars();
                fxml_Car_Insert.t.show_Front_Or_Wait();
            }

            @Override
            public void individual(Individuals individuals, boolean is_edit_mode) {
                fxml_Individual_Insert.t.editable.set(is_edit_mode);
                fxml_Individual_Insert.t.individual = individuals;
                fxml_Individual_Insert.t.loadIndividual();
                fxml_Individual_Insert.t.show_Front_Or_Wait();
            }

            @Override
            public void get_size(String txt) {
                alarm_list.setText(txt);
            }
        });

        manageCompanies.setOnAction((ActionEvent event) -> {
            UtilsStage utilsStage = new UtilsStage(Fxml_Manage_Company.class, "شرکتها", Modality.APPLICATION_MODAL, thisStage);
            utilsStage.t.show_And_Wait();
        });

        individual_insert.setOnAction((ActionEvent event) -> {
            if (Permission.isAcces_WithMSG(Permission.INDIVIDUAL_INSERT)) {
                fxml_Individual_Insert.t.editable.set(true);
                fxml_Individual_Insert.t.show_Front_Or_Wait();
            }
        });

        report.setOnAction((ActionEvent event) -> {
            if (!Permission.isAcces(Permission.CAR_GETREPORT)) {
                if (!Permission.isAcces_WithMSG(Permission.INDIVIDUAL_GETREPORT)) {
                    return;
                }
            }
            fxml_Get_Report.t.query_for_search = null;
            fxml_Get_Report.t.show_Front_Or_Wait();
            if (fxml_Get_Report.t.query_for_search != null) {
                if (fxml_Get_Report.t.query_for_search.startsWith("SELECT individuals.id")) {
                    fxml_Get_Report_Individual_List.t.set_Query(fxml_Get_Report.t.query_for_search);
                    fxml_Get_Report_Individual_List.t.show_Front_Or_Wait();
                } else {
                    fxml_Get_Report_Car_List.t.set_Query(fxml_Get_Report.t.query_for_search);
                    fxml_Get_Report_Car_List.t.show_Front_Or_Wait();
                }
            }
        });

        car_insert.setOnAction((ActionEvent event) -> {
            if (Permission.isAcces_WithMSG(Permission.CAR_INSERT)) {
                fxml_Car_Insert.t.editable.set(true);
                fxml_Car_Insert.t.show_Front_Or_Wait();
            }
        });

        alarm_list.visibleProperty().bind(alarm_list.textProperty().isEmpty());

        alarm_list.setOnAction((ActionEvent event) -> {
            fxml_Alarm_List.t.show_Front_Or_Wait();
        });

        changeUserPass.setOnAction((ActionEvent event) -> {
            UtilsStage utilsStage = new UtilsStage(Fxml_User_Change_Pass.class, "تغییر رمز عبور", Modality.APPLICATION_MODAL, thisStage);
            utilsStage.t.show_And_Wait();
        });

        changeUser.setOnAction((ActionEvent event) -> {
            thisStage.close();
            Register register = new Register();
            register.exitUser();
            UtilsStage<Fxml_Login> utilsStage = new UtilsStage(Fxml_Login.class, "ورود", Modality.APPLICATION_MODAL, thisStage);
            utilsStage.t.show_And_Wait();
            if (utilsStage.t.isAccess) {
                users = utilsStage.t.usersTemp;
                showMainStage(thisStage);
            } else {
                onCloseApp();
            }
        });

        changeUserPass.setOnAction((ActionEvent event) -> {
            UtilsStage utilsStage = new UtilsStage(Fxml_User_Change_Pass.class, "تغییر رمز عبور", Modality.APPLICATION_MODAL, thisStage);
            utilsStage.t.show_And_Wait();
        });

        manageUser.setOnAction((ActionEvent event) -> {
            UtilsStage utilsStage = new UtilsStage(Fxml_User_Manage.class, "مدیریت حساب ها", Modality.APPLICATION_MODAL, thisStage);
            utilsStage.t.show_And_Wait();
        });

        show_print_view.setOnAction((ActionEvent event) -> {
            if (work_list.isEmpty()) {
                UtilsMsg.show("موردی جهت چاپ وجود ندارد", "هشدار", false, thisStage);
            } else {
                show_print_preView(work_list);
            }
        });

        bindToTime();
    }

    public static void show_print_preView(List<WorkHistory> work_list) {
        fxml_Print_PreView.t.set_value(work_list);
        fxml_Print_PreView.t.show_Front_Or_Wait();
    }

    private void bindToTime() {
        PersianCalendar pc = new PersianCalendar();
        timeAnimation.setOnMouseClicked((MouseEvent event) -> {
            times = !times;
            if (times) {
                timeAnimation.setStyle("-fx-font-family:'B Yekan';");
                timeAnimation.setText(pc.nameOFdayFa() + " ، " + pc.get(Calendar.DAY_OF_MONTH) + " " + pc.nameOfMonthFA() + " ، " + pc.year() + "    (" + pc.year() + "/" + pc.month() + "/" + pc.day() + ")");
            } else {
                timeAnimation.setStyle("-fx-font-family:'Arial';");
                timeAnimation.setText("(" + pc.getNowMilladiDate() + ")     " + pc.nameOFdayEn() + " ، " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " " + pc.nameOfMonthEN() + " ، " + Calendar.getInstance().get(Calendar.YEAR));
            }
        });
        timeAnimation.setStyle("-fx-font-family:'B Yekan';");
        timeAnimation.setText(pc.nameOFdayFa() + " ، " + pc.get(Calendar.DAY_OF_MONTH) + " " + pc.nameOfMonthFA() + " ، " + pc.year() + "    (" + pc.year() + "/" + pc.month() + "/" + pc.day() + ")");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), (ActionEvent actionEvent) -> {
                    times = !times;
                    if (times) {
                        timeAnimation.setStyle("-fx-font-family:'B Yekan';");
                        timeAnimation.setText(pc.nameOFdayFa() + " ، " + pc.get(Calendar.DAY_OF_MONTH) + " " + pc.nameOfMonthFA() + " ، " + pc.year() + "    (" + pc.year() + "/" + pc.month() + "/" + pc.day() + ")");
                    } else {
                        timeAnimation.setStyle("-fx-font-family:'Arial';");
                        timeAnimation.setText("(" + pc.getNowMilladiDate() + ")     " + pc.nameOFdayEn() + " ، " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " " + pc.nameOfMonthEN() + " ، " + Calendar.getInstance().get(Calendar.YEAR));
                    }
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void showMainStage(Stage stage) {
        UtilsStage<Fxml_Main> fXML_Main = new UtilsStage(Fxml_Main.class, "", Modality.NONE, stage);
        fXML_Main.t.thisStage.show();
        fXML_Main.t.thisStage.setOnCloseRequest((WindowEvent event) -> {
            onCloseApp();
        });
    }

    @FXML
    private void exit() {
        onCloseApp();
    }

}
