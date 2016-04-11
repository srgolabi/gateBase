package gatebass;

import gatebass.dataBase.DatabaseHelper;
import gatebass.dataBase.tables.Manage;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.Users;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.login.Fxml_Login;
import gatebass.fxml.main.Fxml_Main;
import gatebass.register.InitActUser;
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
import javafx.stage.WindowEvent;

/**
 *
 * @author SR.Golabi
 */
public class GateBass extends Application {

    public static DatabaseHelper databaseHelper;
    public static Users users;
    public static Register register;
    public static InitActUser actUser;
    public static String version = "1.0.0";
//    public static String server = "\\\\DANESHJOO\\$Latter$\\";
    public static String server = "";
    public static List<WorkHistory> work_list = new ArrayList<>();

    private void importFromExcel() {
//        POIExcelReader poiExample = new POIExcelReader();
//        String xlsPath = "d://test//Gatepass.xls";
//        poiExample.compnaiesFromExcel(xlsPath);
//        poiExample.historyFromExcel(xlsPath);
//        poiExample.displayFromExcel(xlsPath);
//        poiExample.worksFromExcel(xlsPath);
//        xlsPath = "d://test//Gatepass-Cars.xls";
//        poiExample.compnaiesFromExcel2(xlsPath);
//        poiExample.historyFromExcel2(xlsPath);
//        poiExample.displayFromExcel2(xlsPath);
//        poiExample.worksFromExcel2(xlsPath);
//        xlsPath = "d://test//temporary.xls";
//        poiExample.historyFromExcel3(xlsPath);
//        poiExample.displayFromExcel3(xlsPath);
//        poiExample.displayFromExcel35(xlsPath);
    }

    @Override
    public void start(Stage stage) throws Exception {
        actUser = new InitActUser();
        databaseHelper = new DatabaseHelper();
        if (databaseHelper.usersDao.getAll().isEmpty()) {
//            Users user_temp = new Users("adminGolabi", "@dm!ng00l@b!", "", "مدیر سیستم");
            Users user_temp = new Users("adminGolabi", "123", "", "مدیر سیستم");
            user_temp.setAdmin(true);
            databaseHelper.usersDao.createOrUpdate(user_temp);
            databaseHelper.manageDao.createOrUpdate(new Manage(1, "card_id_count", "932349"));
            databaseHelper.manageDao.createOrUpdate(new Manage(2, "card_id_count_car", "50"));
            databaseHelper.manageDao.createOrUpdate(new Manage(3, "company_folder_count", "1"));

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

        importFromExcel();
        register = new Register();
//        register.writeEncrype("piamoit", "2015111820160220111111110");
//        onCloseApp();
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
    }

    public void showLoginStage(Stage stage) {
        UtilsStage<Fxml_Login> fXML_Login = new UtilsStage(Fxml_Login.class, "", Modality.APPLICATION_MODAL, stage);
        fXML_Login.t.show_And_Wait();
        if (fXML_Login.t.isAccess) {
            this.users = fXML_Login.t.usersTemp;
            showMainStage(stage);
        } else {
            onCloseApp();
        }
    }

    public void showMainStage(Stage stage) {
        UtilsStage<Fxml_Main> fXML_Main = new UtilsStage(Fxml_Main.class, "", Modality.NONE, stage);
        fXML_Main.t.thisStage.show();
        fXML_Main.t.thisStage.setOnCloseRequest((WindowEvent event) -> {
            onCloseApp();
        });
    }

    public static void onCloseApp() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
