package gatebass.dataBase.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "cars_j")
public class Cars_j {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(unique = true)
    private String card_id;

    @DatabaseField(defaultValue = "")
    private String car_name;

    @DatabaseField(defaultValue = "")
    private String shasi_number;

    @DatabaseField(defaultValue = "")
    private String model;

    @DatabaseField(defaultValue = "")
    private String comments;

    @DatabaseField(defaultValue = "")
    private String logs;

    @DatabaseField(defaultValue = "")
    private String driver_name;

    @DatabaseField(defaultValue = "0")
    private Integer replica_count;

    @DatabaseField(defaultValue = "")
    private String company_info;

    @DatabaseField(defaultValue = "")
    private String bimeh_date;

    @DatabaseField(defaultValue = "")
    private String card_expiration_date;

    @DatabaseField(defaultValue = "")
    private String card_issued_date;

    @DatabaseField(defaultValue = "")
    private String card_delivery_date;

    @DatabaseField(defaultValue = "")
    private String certificate_date;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "workHistory_id")
    private WorkHistory workHistory_id;

// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
    public Integer getId() {
        return id;
    }

    public String getCard_id() {
        return card_id;
    }

    public String getShasi_number() {
        return shasi_number;
    }

    public String getCar_name() {
        return car_name;
    }

    public String getModel() {
        return model;
    }

    public String getComments() {
        return comments;
    }

    public String getLogs() {
        return logs;
    }

    public Integer getReplica_count() {
        return replica_count;
    }

    public String getCompany_info() {
        return company_info;
    }

    public String getBimeh_date() {
        return bimeh_date;
    }

    public String getCard_expiration_date() {
        return card_expiration_date;
    }

    public String getCard_issued_date() {
        return card_issued_date;
    }

    public String getCard_delivery_date() {
        return card_delivery_date;
    }

    public WorkHistory getWorkHistory_id() {
        return workHistory_id;
    }

    public String getCertificate_date() {
        return certificate_date;
    }

    public WorkHistory getWork_history_id() {
        return workHistory_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

}
