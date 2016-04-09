package gatebass.dataBase.tables;

import com.almasb.java.ui.fx.FXDialog;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import static gatebass.GateBass.databaseHelper;
import static gatebass.GateBass.users;
import gatebass.utils.UtilsStage;
import javafx.stage.Stage;
import javax.rmi.CORBA.Util;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "permission")
public class Permission {

    public static int INDIVIDUAL = 10;
    public static int INDIVIDUAL_INSERT = 11;
    public static int INDIVIDUAL_WORK_INSERT = 12;
    public static int INDIVIDUAL_GETREPORT = 13;

    public static int CAR = 100;
    public static int CAR_INSERT = 101;
    public static int CAR_WORK_INSERT = 102;
    public static int CAR_GETREPORT = 103;

    public static int SETTING = 1;
    public static int USER_INSERT = 2;
    public static int USER_VIEW = 3;
    public static int CHANGE_PASS = 4;
    public static int COMPANY_INSERT = 5;
    public static int COMPANY_VIEW = 6;

    //  17
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Integer id;

    @DatabaseField(defaultValue = "", unique = true)
    private String name;

    @DatabaseField(defaultValue = "")
    private String title;

    @DatabaseField(defaultValue = "")
    private String log_title;

    @DatabaseField(defaultValue = "eye")
    private String icon;

    @DatabaseField(defaultValue = "#000000")
    private String iconColor;

    @DatabaseField(defaultValue = "false")
    private Boolean defaultValue;

    @DatabaseField(index = true, foreign = true, foreignAutoRefresh = true, columnName = "parent")
    private Permission parent;

    @DatabaseField
    private int sort_index;

    public Permission() {
    }

    public Permission(Integer id, String name, String title, int sort_index, Permission parent) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.sort_index = sort_index;
        this.parent = parent;
    }

    public Permission(String name, String title, String log_title, Permission parent, int sort_index) {
        this.name = name;
        this.title = title;
        this.log_title = log_title;
        this.parent = parent;
        this.sort_index = sort_index;
    }

    public Permission(Integer id, String name, String title, Boolean defaultValue, int sort_index, Permission parent) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.defaultValue = defaultValue;
        this.sort_index = sort_index;
        this.parent = parent;
    }

    public static boolean isAcces(int permissionId) {
        return !databaseHelper.userPermissionDao.rawResults(
                "SELECT * FROM userPermission WHERE user_id = " + users.getId() + " AND state = 1 AND permission_id = " + permissionId
        ).isEmpty() || users.getAdmin();
    }

    public static boolean isAcces_WithMSG(int permissionId) {
        boolean b = !databaseHelper.userPermissionDao.rawResults(
                "SELECT * FROM userPermission WHERE user_id = " + users.getId() + " AND state = 1 AND permission_id = " + permissionId
        ).isEmpty() || users.getAdmin();
        if (!b) {
            UtilsStage.showMsg("دسترسی شما محدود می باشد.", "هشدار", false, new Stage());
        }
        return b;
    }
    /*
     ***************************************************************************
     ***************************************************************************
     ***************************************************************************
     */

    public Integer getId() {
        return id;
    }

    public void setLog_title(String log_title) {
        this.log_title = log_title;
    }

    public String getLog_title() {
        return log_title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public String getIcon() {
        return icon;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getDefaultValue() {
        if (defaultValue == null) {
            return false;
        }
        return defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getSort_index() {
        return sort_index;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSort_index(int sort_index) {
        this.sort_index = sort_index;
    }

}
