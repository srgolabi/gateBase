package gatebass;

import gatebass.dataBase.DatabaseHelper;
import gatebass.dataBase.tables.Manage;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.Users;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.get_report.Fxml_Get_Report;
import gatebass.fxml.login.Fxml_Login;
import gatebass.fxml.main.Fxml_Main;
import gatebass.fxml.print_preview.Fxml_Print_PreView;
import gatebass.register.InitActUser;
import gatebass.register.Register;
import gatebass.utils.UtilsStage;
import gatebass.utils.exel.POIExcelReader;
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

    }

    @Override
    public void start(Stage stage) throws Exception {
        actUser = new InitActUser();
        databaseHelper = new DatabaseHelper();
        if (databaseHelper.usersDao.getAll().isEmpty()) {
            databaseHelper.usersDao.createOrUpdate(new Users("golabi", "123", "", "رضا"));
            databaseHelper.manageDao.createOrUpdate(new Manage(1, "card_id_count", "2157"));
            databaseHelper.manageDao.createOrUpdate(new Manage(1, "card_id_count_car", "50"));
            databaseHelper.manageDao.createOrUpdate(new Manage(2, "company_folder_count", "1"));

            List<Permission> permissions = new ArrayList<>();
            Permission pLetter = new Permission(Permission.INDIVIDUAL, "INDIVIDUAL", "سیستم اطلاعات فردی", 50, null);
            {
                databaseHelper.permissionDao.createOrUpdate(pLetter);
                permissions.add(pLetter);
            }
            permissions.add(new Permission(Permission.INDIVIDUAL_INSERT, "INDIVIDUAL_INSERT", "ثبت مشخصات فردی", 100, pLetter));
//            permissions.add(new Permission(Permission.LETTER_SEND, "LETTER_SEND", "ارجاع مکاتبات", 150, pLetter));
            permissions.add(new Permission(Permission.INDIVIDUAL_EDIT, "INDIVIDUAL_EDIT", "اصلاح مشخصات فردی", 200, pLetter));
//            permissions.add(new Permission(Permission.LETTER_VIEW_ALL, "LETTER_VIEW_ALL", "مشاهده کلیه مکاتبات", 300, pLetter));
//            permissions.add(new Permission(Permission.LETTER_VIEW_GROUP, "LETTER_VIEW_GROUP", "مشاهده مکاتبات گروه", true, 350, pLetter));
//            permissions.add(new Permission(Permission.LETTER_VIEW_PERSON, "LETTER_VIEW_PERSON", "مشاهده مکاتبات شخصی", true, 400, pLetter));
            permissions.add(new Permission(Permission.INDIVIDUAL_GETREPORT, "INDIVIDUAL_GETREPORT", "گزارش گیری فردی", 500, pLetter));

            Permission pSetting = new Permission(Permission.SETTING, "SETTING", "تنظیمات کاربری", 350, null);
            {
                databaseHelper.permissionDao.createOrUpdate(pSetting);
                permissions.add(pSetting);
            }
            permissions.add(new Permission(Permission.USER_VIEW, "USER_VIEW", "مشاهده کاربران", 300, pSetting));
            permissions.add(new Permission(Permission.USER_INSERT, "USER_INSERT", "ثبت کاربر", 400, pSetting));
            permissions.add(new Permission(Permission.CHANGE_PASS, "CHANGE_PASS", "تغییر رمز عبور", 500, pSetting));

//            Permission pGROUPS = new Permission(Permission.GROUPS, "GROUPS", "گروه ها", 550, null);
//            {
//                databaseHelper.permissionDao.createOrUpdate(pGROUPS);
//                permissions.add(pGROUPS);
//            }
//            permissions.add(new Permission(Permission.GROUP_VIEW, "GROUP_VIEW", "مشاهده گروه ها", 600, pGROUPS));
//            permissions.add(new Permission(Permission.GROUP_INSERT, "GROUP_INSERT", "ثبت گروه", 700, pGROUPS));
//            permissions.add(new Permission(Permission.GROUP_REMOVE, "GROUP_REMOVE", "حذف گروه", 800, pGROUPS));
//            permissions.add(new Permission(Permission.GROUP_USER, "GROUP_USER", "تعریف کاربران گروه", 1000, pGROUPS));
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
            UtilsStage.showMsg("نرم افزار غیرفعال شده است.\nبا پشتیبان نرم افزار تماس حاصل نمایید.", "هشدار", false, stage);
            onCloseApp();
        }
    }

    public void showLoginStage(Stage stage) {
        UtilsStage utilsStage = new UtilsStage(Fxml_Login.class, "", Modality.APPLICATION_MODAL, stage.getOwner());
        Fxml_Login fXML_Login = utilsStage.getLoader().getController();
        fXML_Login.setStage(utilsStage.getStage());
        fXML_Login.show_And_Wait();
        if (fXML_Login.isAccess) {
            this.users = fXML_Login.usersTemp;
            showMainStage(stage);
        } else {
            onCloseApp();
        }
    }

    public void showMainStage(Stage stage) {
        UtilsStage utilsStage = new UtilsStage(Fxml_Main.class, "", Modality.NONE, stage.getOwner());
//        UtilsStage utilsStage = new UtilsStage("main/FXMLMain.fxml", "", Modality.NONE, stage.getOwner());
        Fxml_Main fXML_Main = utilsStage.getLoader().getController();
        fXML_Main.setStage(utilsStage.getStage());
        utilsStage.getStage().show();
        utilsStage.getStage().setOnCloseRequest((WindowEvent event) -> {
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
