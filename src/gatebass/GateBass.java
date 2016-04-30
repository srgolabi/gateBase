package gatebass;

import gatebass.dataBase.DatabaseHelper;
import gatebass.dataBase.tables.Manage;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.Users;
import gatebass.fxml.login.Fxml_Login;
import gatebass.fxml.main.Fxml_Main;
import gatebass.fxml.splash_screen.Fxml_Splash_Screen;
import gatebass.register.Register;
import gatebass.utils.UtilsMsg;
import gatebass.utils.exel.POIExcelReader;
import gatebass.utils.UtilsStage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author SR.Golabi
 */
public class GateBass extends Application {

    public static DatabaseHelper databaseHelper;
    public static Users users;
//    public static InitActUser actUser;
    public static String version = "1.0.4";
    public static String server = "\\\\DANESHJOO\\$GatePass$\\";
//    public static String server = "";

    UtilsStage<Fxml_Splash_Screen> fxml_Splash_Screen;

    private void importFromExcel() {
        POIExcelReader poiExample = new POIExcelReader();
        String xlsPath = "d://test//Gatepass.xls";
        poiExample.compnaiesFromExcel(xlsPath);
        poiExample.historyFromExcel(xlsPath);
        poiExample.displayFromExcel(xlsPath);
        poiExample.worksFromExcel(xlsPath);
        xlsPath = "d://test//Gatepass-Cars.xls";
        poiExample.compnaiesFromExcel2(xlsPath);
        poiExample.historyFromExcel2(xlsPath);
        poiExample.displayFromExcel2(xlsPath);
        poiExample.worksFromExcel2(xlsPath);
        xlsPath = "d://test//temporary.xls";
        poiExample.historyFromExcel3(xlsPath);
        poiExample.displayFromExcel3(xlsPath);
        poiExample.displayFromExcel35(xlsPath);
        xlsPath = "d://test//replica.xls";
        poiExample.history_replica(xlsPath);
        poiExample.replica_FromExcel(xlsPath);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        actUser = new InitActUser();
        fxml_Splash_Screen = new UtilsStage(Fxml_Splash_Screen.class, "", Modality.WINDOW_MODAL, new Stage());
        fxml_Splash_Screen.t.thisStage.initStyle(StageStyle.UNDECORATED);
        fxml_Splash_Screen.t.thisStage.setOnCloseRequest((WindowEvent event) -> {
            if (fxml_Splash_Screen.t.exit_app) {
                onCloseApp();
            }
        });
        fxml_Splash_Screen.t.thisStage.show();

        Platform.runLater(() -> {
            databaseHelper = new DatabaseHelper();

//            init_first_run();

            if (!Manage.get_value(Manage.VERSION_NUMBER).equals(version)) {
                if (Manage.get_value(Manage.SHOULD_UPDATE).contains("true")) {
                    UtilsMsg.show("نرم افزار بروزرسانی شده است.\nنسخه جدید آن را نصب کنید.", "هشدار", false, stage);
                    onCloseApp();
                } else {
                    UtilsMsg.show("نسخه جدید نرم افزار آماده می باشد", "هشدار", false, stage);
                }
            }

//            {
//                List<Individuals> individualses = databaseHelper.individualsDao.rawResults(
//                        "SELECT * FROM individuals ORDER BY card_id ASC"
//                );
//                int j = 1;
//                int k = 1;
//                List<Individuals> l1 = new ArrayList<>();
//                List<Long> l2 = new ArrayList<>();
//                for (int i = 0; i < individualses.size() - 1; i++) {
////                    List<Individuals> ll = databaseHelper.individualsDao.rawResults("SELECT * FROM individuals WHERE card_id = 'VALUE1' AND national_id != 'VALUE2'".replace("VALUE2", individualses.get(i).getNational_id()).replace("VALUE1", individualses.get(i).getCard_id()));
////                    if (!ll.isEmpty()) {
////                        System.out.println(k++ + ",Duplicate," + individualses.get(i).getNational_id() + "," + individualses.get(i).getCard_id());
////                        l1.add(individualses.get(i).getId() > individualses.get(i + 1).getId() ? individualses.get(i) : individualses.get(i + 1));
////                    }
//                    if (Long.parseLong(individualses.get(i).getCard_id()) == Long.parseLong(individualses.get(i + 1).getCard_id())) {
//                        System.out.println(k++ + ",Duplicate," + individualses.get(i).getNational_id() + "," + individualses.get(i).getCard_id());
//                        l1.add(individualses.get(i).getId() > individualses.get(i + 1).getId() ? individualses.get(i) : individualses.get(i + 1));
//                        continue;
//                    }
//
//                    if (Long.parseLong(individualses.get(i).getCard_id()) + 1 != Long.parseLong(individualses.get(i + 1).getCard_id())) {
//                        System.out.println(j++ + ",Gap," + individualses.get(i).getNational_id() + "," + individualses.get(i).getCard_id());
//                        l2.add(Long.parseLong(individualses.get(i).getCard_id()) + 1);
//                    }
//                }
//
////                for (int i = 0; i < l2.size(); i++) {
////                    System.out.println(i + ",Change," + l1.get(i).getNational_id() + ",from," + l1.get(i).getCard_id() + ",to," + l2.get(i));
////                    l1.get(i).setCard_id(l2.get(i) + "");
////                }
////
////                try {
////                    databaseHelper.individualsDao.insertList(l1);
////                } catch (SQLException ex) {
////                    Logger.getLogger(GateBass.class.getName()).log(Level.SEVERE, null, ex);
////                }
////                {
////                    Long l = Long.parseLong(Manage.get_value("card_id_count"));
////                    for (Individuals ind : l1) {
////                        System.out.println(j++ + ",Change," + ind.getNational_id() + ",from," + ind.getCard_id() + ",to," + l);
////                        ind.setCard_id(l + "");
////                        l++;
////                    }
////                    databaseHelper.manageDao.createOrUpdate(new Manage("card_id_count", l + ""));
////                    try {
////                        databaseHelper.individualsDao.insertList(l1);
////                    } catch (SQLException ex) {
////                        Logger.getLogger(GateBass.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////                }
//                onCloseApp();
//            }

            Register register = new Register();
//            register.writeEncrype("piamoit", "2015111820160220111111110");
//            onCloseApp();
            fxml_Splash_Screen.t.exit_app = false;
            if (register.checkRegister()) {
                if (register.showLoginStage) {
                    showLoginStage(stage);
                } else {
                    users = databaseHelper.usersDao.queryForId(register.userID);
                    showMainStage(stage);
                }
            } else {
                register.writeEncrype("piamoit", "asdfghjklkiuytre");
                UtilsMsg.show("نرم افزار غیرفعال شده است.\nبا پشتیبان نرم افزار تماس حاصل نمایید.", "هشدار", false, stage);
                onCloseApp();
            }
        });
    }

    private void init_first_run() {

        if (databaseHelper.usersDao.getAll().isEmpty()) {

            List<Permission> permissions = new ArrayList<>();
            Permission pLetter = new Permission(Permission.INDIVIDUAL, "INDIVIDUAL", "سیستم اطلاعات فردی", 50, null);
            {
                databaseHelper.permissionDao.createOrUpdate(pLetter);
                permissions.add(pLetter);
            }
            permissions.add(new Permission(Permission.INDIVIDUAL_INSERT, "INDIVIDUAL_INSERT", "ثبت مشخصات", 10, pLetter));
            permissions.add(new Permission(Permission.INDIVIDUAL_WORK_INSERT, "INDIVIDUAL_WORK_INSERT", "ثبت سوابق", 20, pLetter));
            permissions.add(new Permission(Permission.INDIVIDUAL_GETREPORT, "INDIVIDUAL_GETREPORT", "گزارش گیری", 30, pLetter));

            Permission pCar = new Permission(Permission.CAR, "CAR", "سیستم اطلاعات خودرویی", 50, null);
            {
                databaseHelper.permissionDao.createOrUpdate(pCar);
                permissions.add(pCar);
            }
            permissions.add(new Permission(Permission.CAR_INSERT, "CAR_INSERT", "ثبت مشخصات", 10, pCar));
            permissions.add(new Permission(Permission.CAR_WORK_INSERT, "CAR_WORK_INSERT", "ثبت سوابق", 20, pCar));
            permissions.add(new Permission(Permission.CAR_GETREPORT, "CAR_GETREPORT", "گزارش گیری", 30, pCar));

            Permission pSetting = new Permission(Permission.SETTING, "SETTING", "تنظیمات کاربری", 100, null);
            {
                databaseHelper.permissionDao.createOrUpdate(pSetting);
                permissions.add(pSetting);
            }
            permissions.add(new Permission(Permission.USER_VIEW, "USER_VIEW", "مشاهده حسابها", 10, pSetting));
            permissions.add(new Permission(Permission.USER_INSERT, "USER_INSERT", "ثبت حساب", 20, pSetting));
            permissions.add(new Permission(Permission.CHANGE_PASS, "CHANGE_PASS", "تغییر رمز عبور", 30, pSetting));
            permissions.add(new Permission(Permission.COMPANY_VIEW, "COMPANY_VIEW", "مشاهده لیست شرکتها", 40, pSetting));
            permissions.add(new Permission(Permission.COMPANY_INSERT, "COMPANY_INSERT", "ثبت شرکت", 50, pSetting));

            try {
                databaseHelper.permissionDao.insertList(permissions);
            } catch (SQLException ex) {
                Logger.getLogger(GateBass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        importFromExcel();
    }

    public void showLoginStage(Stage stage) {
        UtilsStage<Fxml_Login> fXML_Login = new UtilsStage(Fxml_Login.class, "", Modality.APPLICATION_MODAL, stage);
        fXML_Login.t.set_My_Action((Users t, boolean b) -> {
            Register register = new Register();
            register.userID = t.getId();
            register.checkLogin(b);
            this.users = t;
            showMainStage(stage);
        });
        fxml_Splash_Screen.t.thisStage.close();
        fXML_Login.t.show_And_Wait();
    }

    public void showMainStage(Stage stage) {
        UtilsStage<Fxml_Main> fXML_Main = new UtilsStage(Fxml_Main.class, "دادبان", Modality.NONE, stage);
        fXML_Main.t.thisStage.show();
        fXML_Main.t.thisStage.setOnCloseRequest((WindowEvent event) -> {
            onCloseApp();
        });
        fxml_Splash_Screen.t.thisStage.close();
    }

    public static void onCloseApp() {
        Platform.exit();
        System.exit(0);
        Runtime.getRuntime().exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
