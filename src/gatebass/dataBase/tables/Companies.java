package gatebass.dataBase.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "companies")
public class Companies extends Base {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String folder_name;

    @DatabaseField
    private String company_en;

    @DatabaseField
    private String company_fa;

    @DatabaseField
    private String summary;

    @DatabaseField(defaultValue = "true")
    private Boolean active;

    @DatabaseField(defaultValue = "false")
    private Boolean is_deleted;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "logo_id")
    private IndividualFile logo_id;

    public Companies() {
    }

    public Companies getThis() {
        return this;
    }

    public Companies(int id, String folder_name, String company_fa, String company_en) {
        this.id = id;
        this.folder_name = folder_name;
        this.company_en = company_en;
        this.company_fa = company_fa;
    }

    public Companies(String folder_name, String company_fa, String company_en, boolean isActive) {
        this.active = isActive;
        this.folder_name = folder_name;
        this.company_en = company_en;
        this.company_fa = company_fa;
    }

    @Override
    public String getCulomnValue() {
        return company_fa;
    }

    @Override
    public String getToolTipValue() {
        return company_fa;
    }

// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
    public void setId(Integer id) {
        this.id = id;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public void setCompany_en(String company_en) {
        this.company_en = company_en;
    }

    public void setCompany_fa(String company_fa) {
        this.company_fa = company_fa;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public String getCompany_en() {
        return company_en;
    }

    public String getCompany_fa() {
        return company_fa;
    }

    public String getSummary() {
        return summary;
    }
}
