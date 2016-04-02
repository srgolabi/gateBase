package gatebass.dataBase;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.CarHistory;
import gatebass.dataBase.tables.Cars_j;
import gatebass.dataBase.tables.Companies;
import gatebass.dataBase.tables.History;
import gatebass.dataBase.tables.IndividualFile;
import gatebass.dataBase.tables.IndividualReplica;
import gatebass.dataBase.tables.IndividualWarning;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.Individuals_j;
import gatebass.dataBase.tables.Manage;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.UserGroup;
import gatebass.dataBase.tables.UserPermission;
import gatebass.dataBase.tables.Users;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.alarm_list.Gate_Alarm;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author reza
 */
public class DatabaseHelper {

    public BaseRepo<Individuals_j, Integer> individuals_jDao;
    public BaseRepo<Cars_j, Integer> cars_jDao;
    public BaseRepo<Gate_Alarm, Integer> gate_AlarmDao;

    public BaseRepo<Cars, Integer> carDao;
    public BaseRepo<CarHistory, Integer> carsHistoryDao;
    public BaseRepo<Companies, Integer> companiesDao;
    public BaseRepo<History, Integer> historyDao;
    public BaseRepo<Individuals, Integer> individualsDao;
    public BaseRepo<Users, Integer> usersDao;
    public BaseRepo<UserGroup, Integer> userGroupDao;
    public BaseRepo<Permission, Integer> permissionDao;
    public BaseRepo<UserPermission, Integer> userPermissionDao;
    public BaseRepo<WorkHistory, Integer> workHistoryDao;
    public BaseRepo<Manage, Integer> manageDao;

    public BaseRepo<IndividualReplica, Integer> individualReplicaDao;
    public BaseRepo<IndividualWarning, Integer> individualWarningDao;
    public BaseRepo<IndividualFile, Integer> individualFileDao;

    public ConnectionSource connectionDb;

    public DatabaseHelper() {
        try {
            String gateBassDBurl = "jdbc:sqlite:gatebass.db";

            connectionDb = new JdbcConnectionSource(gateBassDBurl);
            individuals_jDao = new BaseRepo<>(connectionDb, Individuals_j.class, false);
            cars_jDao = new BaseRepo<>(connectionDb, Cars_j.class, false);
            gate_AlarmDao = new BaseRepo<>(connectionDb, Gate_Alarm.class, false);

            carDao = new BaseRepo<>(connectionDb, Cars.class);
            carsHistoryDao = new BaseRepo<>(connectionDb, CarHistory.class);
            companiesDao = new BaseRepo<>(connectionDb, Companies.class);
            historyDao = new BaseRepo<>(connectionDb, History.class);
            individualsDao = new BaseRepo<>(connectionDb, Individuals.class);
            usersDao = new BaseRepo<>(connectionDb, Users.class);
            userGroupDao = new BaseRepo<>(connectionDb, UserGroup.class);
            permissionDao = new BaseRepo<>(connectionDb, Permission.class);
            workHistoryDao = new BaseRepo<>(connectionDb, WorkHistory.class);
            manageDao = new BaseRepo<>(connectionDb, Manage.class);
            individualReplicaDao = new BaseRepo<>(connectionDb, IndividualReplica.class);
            individualWarningDao = new BaseRepo<>(connectionDb, IndividualWarning.class);
            individualFileDao = new BaseRepo<>(connectionDb, IndividualFile.class);
            userPermissionDao = new BaseRepo<>(connectionDb, UserPermission.class);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
