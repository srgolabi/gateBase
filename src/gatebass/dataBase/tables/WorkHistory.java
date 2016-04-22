/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.dataBase.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "workhistory")
public class WorkHistory {

    public static Short TEMPORARY = 0;
    public static Short CONTRACTOR = 1;
    public static Short EMPLOYER = 2;
    public static Short CAR = 3;

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(defaultValue = "0")
    private Short gate_type;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "individuals_id")
    private Individuals individualsId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "companies_id")
    private Companies companies;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "employment_date_id")
    private History employmentDateId;

    @DatabaseField
    private String jobTitle;

    @DatabaseField
    private String jobTitleENG;

    @DatabaseField
    private String jobPhoneNumber;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "card_issued_date_id")
    private History cardIssuedDateId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "card_expiration_date_id")
    private History cardExpirationDateId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "card_delivery_date_id")
    private History cardDeliveryDate;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "car_history_id")
    private CarHistory car_history_id;

    @DatabaseField
    private String comments;

    @DatabaseField(defaultValue = "")
    private String logs;

    public WorkHistory() {
    }

    public WorkHistory(WorkHistory wh) {
        this.id = wh.getId();
        this.individualsId = wh.getIndividual();
        this.companies = wh.getCompanies();
        this.employmentDateId = wh.getEmploymentDateId();
        this.jobTitle = wh.getJobTitle();
        this.jobTitleENG = wh.getJobTitleENG();
        this.jobPhoneNumber = wh.getJobPhoneNumber();
        this.cardIssuedDateId = wh.getCardIssuedDateId();
        this.cardExpirationDateId = wh.getCardExpirationDateId();
        this.cardDeliveryDate = wh.getCardDeliveryDate();
        this.car_history_id = wh.getCar_history_id();
        this.comments = wh.getComments();
        this.logs = wh.getLogs();
        this.gate_type = wh.getGate_type();

    }
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------

    public void setShould(Companies companies, String jobTitle, String jobTitleENG) {
        this.companies = companies;
        this.jobTitle = jobTitle;
        this.jobTitleENG = jobTitleENG;
    }

    public String getLogs() {
        return logs;
    }

    public void setGate_type(Short gate_type) {
        this.gate_type = gate_type;
    }

    public Short getGate_type() {
        return gate_type;
    }

    public Companies getCompanies() {
        return companies;
    }

    public History getEmploymentDateId() {
        return employmentDateId;
    }

    public History getCardIssuedDateId() {
        return cardIssuedDateId;
    }

    public History getCardExpirationDateId() {
        return cardExpirationDateId;
    }

    public History getCardDeliveryDate() {
        return cardDeliveryDate;
    }

    public String getSherkat() {
        return companies.getCompany_fa();
    }

    public String getEstekhdam() {
        if (employmentDateId == null) {
            return "";
        }
        return employmentDateId.getDate();
    }

    public String getTahvil() {
        if (cardDeliveryDate == null) {
            return "";
        }
        return cardDeliveryDate.getDate();
    }

    public String getSodor() {
        if (cardIssuedDateId == null) {
            return "";
        }
        return cardIssuedDateId.getDate();
    }

    public String getEngheza() {
        if (cardExpirationDateId == null) {
            return "";
        }
        return cardExpirationDateId.getDate();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobTitleENG() {
        return jobTitleENG;
    }

    public String getJobPhoneNumber() {
        return jobPhoneNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setIndividualsId(Individuals individualsId) {
        this.individualsId = individualsId;
    }

    public void setEmploymentDateId(History employmentDateId) {
        this.employmentDateId = employmentDateId;
    }

    public void setJobPhoneNumber(String jobPhoneNumber) {
        this.jobPhoneNumber = jobPhoneNumber;
    }

    public void setCardIssuedDateId(History cardIssuedDateId) {
        this.cardIssuedDateId = cardIssuedDateId;
    }

    public void setCardExpirationDateId(History cardExpirationDateId) {
        this.cardExpirationDateId = cardExpirationDateId;
    }

    public void setCardDeliveryDate(History cardDeliveryDate) {
        this.cardDeliveryDate = cardDeliveryDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Individuals getIndividualsId() {
        return individualsId;
    }

    public CarHistory getCar_history_id() {
        return car_history_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCar_history_id(CarHistory car_history_id) {
        this.car_history_id = car_history_id;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobTitleENG(String jobTitleENG) {
        this.jobTitleENG = jobTitleENG;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public Integer getId() {
        return id;
    }

    public Individuals getIndividual() {
        return individualsId;
    }

//    public void set_TYPE(String TYPE) {
//        this.GATEBASS_TYPE = TYPE;
//    }
    public boolean is_TEMPORARY_TYPE() {
        return this.gate_type.equals(TEMPORARY);
    }

    public boolean is_CAR_TYPE() {
        return this.gate_type.equals(CAR);
    }

    public boolean is_EMPLOYER_TYPE() {
        return this.gate_type.equals(EMPLOYER);
    }

}
