package gatebass.dataBase.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "individuals")
public class Individuals {

    public static short ALARM_STATE_NORMAL = 0, TEMP_TYPE = 0;
    public static short ALARM_STATE_KEEP = 1, STAF_TYPE = 1;
    public static short ALARM_STATE_CHEKED = 2, CONTRACTOR_TYPE = 2;

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String card_id;

    @DatabaseField(defaultValue = "")
    private String first_name;

    @DatabaseField(defaultValue = "")
    private String last_name;

    @DatabaseField(defaultValue = "")
    private String last_name_ENG;

    @DatabaseField(defaultValue = "")
    private String first_name_ENG;

    @DatabaseField(defaultValue = "")
    private String father_first_name;

    @DatabaseField(unique = true)
    private String national_id;

    @DatabaseField(defaultValue = "")
    private String id_number;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "soldiery_start_date")
    private History soldiery_start_date;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "soldiery_end_date")
    private History soldiery_end_date;

    @DatabaseField(defaultValue = "")
    private String soldiery_id;

    @DatabaseField(defaultValue = "")
    private String soldiery_location;

    @DatabaseField(defaultValue = "")
    private String soldiery_unit;

    @DatabaseField(defaultValue = "")
    private String soldiery_exempt;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "birth_day")
    private History birth_day;

    @DatabaseField(defaultValue = "")
    private String birth_state;

    @DatabaseField(defaultValue = "")
    private String issued;

    @DatabaseField(defaultValue = "")
    private String serial_number;

    @DatabaseField(defaultValue = "")
    private String field_of_study;

    @DatabaseField(defaultValue = "")
    private String academic_degree;

    @DatabaseField(defaultValue = "")
    private String mobile;

    @DatabaseField(defaultValue = "0")
    private Integer dependants;

    @DatabaseField(defaultValue = "")
    private String series_id;

    @DatabaseField(defaultValue = "")
    private String state_address;

    @DatabaseField(defaultValue = "")
    private String city_address;

    @DatabaseField(defaultValue = "")
    private String postal_code;

    @DatabaseField(defaultValue = "")
    private String phone_number;

    @DatabaseField(defaultValue = "")
    private String street_address;

    @DatabaseField(defaultValue = "")
    private String nationality;

    @DatabaseField(defaultValue = "")
    private String religion;

    @DatabaseField(defaultValue = "")
    private String din;

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

    @DatabaseField(defaultValue = "0")
    private Short alarm_state;

    @DatabaseField(defaultValue = "0")
    private Short gate_type;

    @DatabaseField(defaultValue = "")
    private String logs;

    public Individuals() {
    }

    public Individuals(Integer id) {
        this.id = id;
    }

// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
    public Integer getId() {
        return id;
    }

    public Short getGate_type() {
        return gate_type;
    }

    public void setGate_type(Short gate_type) {
        this.gate_type = gate_type;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setHave_soe_pishine(boolean have_soe_pishine) {
        this.have_soe_pishine = have_soe_pishine;
    }

    public boolean isHave_soe_pishine() {
        return have_soe_pishine;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getLast_name_ENG() {
        return last_name_ENG;
    }

    public String getFirst_name_ENG() {
        return first_name_ENG;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public void setDin(String din) {
        this.din = din;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setAlarm_state(Short alarm_state) {
        this.alarm_state = alarm_state;
    }

    public Short getAlarm_state() {
        return alarm_state;
    }

    public String getDin() {
        return din;
    }

    public String getLogs() {
        return logs;
    }

    public void setPicture_address(String picture_address) {
        this.picture_address = picture_address;
    }

    public String getPicture_address() {
        return picture_address;
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

    public History getSoldiery_start_date() {
        return soldiery_start_date;
    }

    public History getSoldiery_end_date() {
        return soldiery_end_date;
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

    public String getSoldiery_exempt() {
        return soldiery_exempt;
    }

    public History getBirth_day() {
        return birth_day;
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

    public String getField_of_study() {
        return field_of_study;
    }

    public String getAcademic_degree() {
        return academic_degree;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getDependants() {
        return dependants;
    }

    public String getSeries_id() {
        return series_id;
    }

    public String getState_address() {
        return state_address;
    }

    public String getCity_address() {
        return city_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getNationality() {
        return nationality;
    }

    public String getReligion() {
        return religion;
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

    public String getFilesPatch() {
        return filesPatch;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setLast_name_ENG(String last_name_ENG) {
        this.last_name_ENG = last_name_ENG;
    }

    public void setFirst_name_ENG(String first_name_ENG) {
        this.first_name_ENG = first_name_ENG;
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

    public void setSoldiery_start_date(History soldiery_start_date) {
        this.soldiery_start_date = soldiery_start_date;
    }

    public void setSoldiery_end_date(History soldiery_end_date) {
        this.soldiery_end_date = soldiery_end_date;
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

    public void setBirth_day(History birth_day) {
        this.birth_day = birth_day;
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

    public void setField_of_study(String field_of_study) {
        this.field_of_study = field_of_study;
    }

    public void setAcademic_degree(String academic_degree) {
        this.academic_degree = academic_degree;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDependants(Integer dependants) {
        this.dependants = dependants;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public void setState_address(String state_address) {
        this.state_address = state_address;
    }

    public void setCity_address(String city_address) {
        this.city_address = city_address;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setReligion(String religion) {
        this.religion = religion;
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

    public void setFilesPatch(String filesPatch) {
        this.filesPatch = filesPatch;
    }

}
