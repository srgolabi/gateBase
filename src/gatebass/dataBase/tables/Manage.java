package gatebass.dataBase.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import static gatebass.GateBass.databaseHelper;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "manage")
public class Manage {

    public static String VERSION_NUMBER = "VERSION_NUMBER";
    public static String SHOULD_UPDATE = "SHOULD_UPDATE";

    public static String get_value(String key) {
        return databaseHelper.manageDao.getFirst("key", key).getValue();
    }

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Integer id;

    @DatabaseField
    private String key;

    @DatabaseField
    private String value;

    public Manage() {
    }

    public Manage(Integer id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public Manage(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
