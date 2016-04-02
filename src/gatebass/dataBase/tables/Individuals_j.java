package gatebass.dataBase.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "individuals_j")
public class Individuals_j {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(unique = true)
    private String card_id;

    @DatabaseField(defaultValue = "")
    private String first_name;

    @DatabaseField(defaultValue = "")
    private String last_name;

    @DatabaseField(defaultValue = "0")
    private Integer replica_count;

    @DatabaseField(defaultValue = "0")
    private Integer warning_count;

    @DatabaseField(defaultValue = "")
    private String father_first_name;

    @DatabaseField(unique = true)
    private String national_id;

    @DatabaseField(defaultValue = "")
    private String id_number;

    @DatabaseField(defaultValue = "")
    private String soldiery_start_info;

    @DatabaseField(defaultValue = "")
    private String soldiery_end_info;

    @DatabaseField(defaultValue = "")
    private String soldiery_id;

    @DatabaseField(defaultValue = "")
    private String soldiery_location;

    @DatabaseField(defaultValue = "")
    private String soldiery_unit;

    @DatabaseField(defaultValue = "")
    private String soldiery_exempt;

    @DatabaseField(defaultValue = "")
    private String birth_day_info;

    @DatabaseField(defaultValue = "")
    private String birth_state;

    @DatabaseField(defaultValue = "")
    private String issued;

    @DatabaseField(defaultValue = "")
    private String serial_number;

    @DatabaseField(defaultValue = "")
    private String study_info;

    @DatabaseField(defaultValue = "")
    private String phone_info;

    @DatabaseField(defaultValue = "0")
    private Integer dependants;

    @DatabaseField(defaultValue = "")
    private String series_id;

    @DatabaseField(defaultValue = "")
    private String address;

    @DatabaseField(defaultValue = "")
    private String postal_code;

    @DatabaseField(defaultValue = "")
    private String nationality;

    @DatabaseField(defaultValue = "")
    private String religion_info;

    @DatabaseField(defaultValue = "")
    private String employment_date;

    @DatabaseField(defaultValue = "")
    private String card_issued_date;

    @DatabaseField(defaultValue = "")
    private String card_expiration_date;

    @DatabaseField(defaultValue = "")
    private String card_delivery_date;

    @DatabaseField(defaultValue = "")
    private String company_info;

    @DatabaseField(defaultValue = "")
    private String criminal_records;

    @DatabaseField(defaultValue = "1")
    private Integer veteran_status;

    @DatabaseField(defaultValue = "")
    private String comments;

    @DatabaseField
    private String picture_address;

    @DatabaseField(defaultValue = "false")
    private boolean have_soe_pishine;

    @DatabaseField(defaultValue = "") //year + week of the year + national id
    private String filesPatch;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "car_history_id")
    private CarHistory car_history_id;

    @DatabaseField(defaultValue = "")
    private String logs;

    public Boolean gethave_comments() {
        return !comments.isEmpty();
    }

    public Boolean gethave_picture() {
        return !picture_address.isEmpty();
    }

    public Boolean gethave_warning() {
        return warning_count > 0;
    }

    public Boolean gethave_replica() {
        return replica_count > 0;
    }

    public String getveteran_info() {
        if (veteran_status == 2) {
            return "معاف";
        } else if (veteran_status == 1) {
            return "نامعلوم";
        } else {
            return (soldiery_start_info == null ? "" : "اعزام : date".replace("date", soldiery_start_info)) + (soldiery_end_info == null ? "" : "خاتمه : date".replace("date", soldiery_end_info));
        }
    }

// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
    public Integer getId() {
        return id;
    }

    public String getCard_id() {
        return card_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFather_first_name() {
        return father_first_name;
    }

    public String getNational_id() {
        return national_id;
    }

    public String getId_number() {
        return id_number;
    }

    public String getSoldiery_id() {
        return soldiery_id;
    }

    public String getSoldiery_location() {
        return soldiery_location;
    }

    public String getSoldiery_unit() {
        return soldiery_unit;
    }

    public Integer getReplica_count() {
        return replica_count;
    }

    public Integer getWarning_count() {
        return warning_count;
    }

    public String getSoldiery_exempt() {
        return soldiery_exempt;
    }

    public String getBirth_state() {
        return birth_state;
    }

    public String getIssued() {
        return issued;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public Integer getDependants() {
        return dependants;
    }

    public String getSeries_id() {
        return series_id;
    }

    public String getAddress() {
        return address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getNationality() {
        return nationality;
    }

    public String getEmployment_date() {
        return employment_date;
    }

    public String getCard_issued_date() {
        return card_issued_date;
    }

    public String getCard_expiration_date() {
        return card_expiration_date;
    }

    public String getCard_delivery_date() {
        return card_delivery_date;
    }

    public String getCriminal_records() {
        return criminal_records;
    }

    public Integer getVeteran_status() {
        return veteran_status;
    }

    public String getComments() {
        return comments;
    }

    public String getPicture_address() {
        return picture_address;
    }

    public boolean isHave_soe_pishine() {
        return have_soe_pishine;
    }

    public String getFilesPatch() {
        return filesPatch;
    }

    public String getLogs() {
        return logs;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setReplica_count(Integer replica_count) {
        this.replica_count = replica_count;
    }

    public void setWarning_count(Integer warning_count) {
        this.warning_count = warning_count;
    }

    public void setFather_first_name(String father_first_name) {
        this.father_first_name = father_first_name;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public void setSoldiery_id(String soldiery_id) {
        this.soldiery_id = soldiery_id;
    }

    public void setSoldiery_location(String soldiery_location) {
        this.soldiery_location = soldiery_location;
    }

    public void setSoldiery_unit(String soldiery_unit) {
        this.soldiery_unit = soldiery_unit;
    }

    public void setSoldiery_exempt(String soldiery_exempt) {
        this.soldiery_exempt = soldiery_exempt;
    }

    public void setBirth_state(String birth_state) {
        this.birth_state = birth_state;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public void setDependants(Integer dependants) {
        this.dependants = dependants;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setEmployment_date(String employment_date) {
        this.employment_date = employment_date;
    }

    public void setCard_issued_date(String card_issued_date) {
        this.card_issued_date = card_issued_date;
    }

    public void setCard_expiration_date(String card_expiration_date) {
        this.card_expiration_date = card_expiration_date;
    }

    public void setCard_delivery_date(String card_delivery_date) {
        this.card_delivery_date = card_delivery_date;
    }

    public void setCriminal_records(String criminal_records) {
        this.criminal_records = criminal_records;
    }

    public void setVeteran_status(Integer veteran_status) {
        this.veteran_status = veteran_status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPicture_address(String picture_address) {
        this.picture_address = picture_address;
    }

    public void setHave_soe_pishine(boolean have_soe_pishine) {
        this.have_soe_pishine = have_soe_pishine;
    }

    public void setFilesPatch(String filesPatch) {
        this.filesPatch = filesPatch;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public void setSoldiery_start_info(String soldiery_start_info) {
        this.soldiery_start_info = soldiery_start_info;
    }

    public void setSoldiery_end_info(String soldiery_end_info) {
        this.soldiery_end_info = soldiery_end_info;
    }

    public void setBirth_day_info(String birth_day_info) {
        this.birth_day_info = birth_day_info;
    }

    public void setStudy_info(String study_info) {
        this.study_info = study_info;
    }

    public void setPhone_info(String phone_info) {
        this.phone_info = phone_info;
    }

    public void setReligion_info(String religion_info) {
        this.religion_info = religion_info;
    }

    public void setCompany_info(String company_info) {
        this.company_info = company_info;
    }

    public String getSoldiery_start_info() {
        return soldiery_start_info;
    }

    public String getSoldiery_end_info() {
        return soldiery_end_info;
    }

    public String getBirth_day_info() {
        return birth_day_info;
    }

    public String getStudy_info() {
        return study_info;
    }

    public String getPhone_info() {
        return phone_info;
    }

    public String getReligion_info() {
        return religion_info;
    }

    public String getCompany_info() {
        return company_info;
    }

}
