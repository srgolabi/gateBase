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
@DatabaseTable(tableName = "carHistory")
public class CarHistory {

    public boolean driver_is_edited = false;

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "car_id")
    private Cars car_id;

    @DatabaseField(defaultValue = "")  //22-س-666-32 (ایران)
    private String pellak;

    @DatabaseField(defaultValue = "")
    private String color;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "workHistory_id")
    private WorkHistory workHistory_id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "companies_id")
    private Companies companies;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "bimeh_date_id")
    private History bimehDateId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "card_issued_date_id")
    private History cardIssuedDateId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "card_expiration_date_id")
    private History cardExpirationDateId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "certificate_date_id")
    private History certificateDateId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "card_delivery_date_id")
    private History cardDeliveryDateId;

    @DatabaseField(defaultValue = "")
    private String logs;

    public String getSherkat() {
        return companies == null ? "" : companies.getCompany_fa();
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

    public String getTahvil() {
        if (cardDeliveryDateId == null) {
            return "";
        }
        return cardDeliveryDateId.getDate();
    }

    public String getSalamt() {
        if (certificateDateId == null) {
            return "";
        }
        return certificateDateId.getDate();
    }

    public String getDriver_name() {
        if (workHistory_id != null) {
            return workHistory_id.getIndividual().getFirst_name() + " " + workHistory_id.getIndividual().getLast_name();
        }
        return "";
    }
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkHistory getWorkHistory_id() {
        return workHistory_id;
    }

    public void setWorkHistory_id(WorkHistory workHistory_id) {
        this.workHistory_id = workHistory_id;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }

    public void setCardIssuedDateId(History cardIssuedDateId) {
        this.cardIssuedDateId = cardIssuedDateId;
    }

    public void setCardExpirationDateId(History cardExpirationDateId) {
        this.cardExpirationDateId = cardExpirationDateId;
    }

    public void setCardDeliveryDateId(History cardDeliveryDate) {
        this.cardDeliveryDateId = cardDeliveryDate;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public Integer getId() {
        return id;
    }

    public Cars getCar_id() {
        return car_id;
    }

    public void setCar_id(Cars car_id) {
        this.car_id = car_id;
    }

    public Companies getCompanies() {
        return companies;
    }

    public History getCardIssuedDateId() {
        return cardIssuedDateId;
    }

    public History getCardExpirationDateId() {
        return cardExpirationDateId;
    }

    public History getCardDeliveryDateId() {
        return cardDeliveryDateId;
    }

    public String getLogs() {
        return logs;
    }

    public History getCertificateDateId() {
        return certificateDateId;
    }

    public void setCertificateDateId(History certificateDateId) {
        this.certificateDateId = certificateDateId;
    }

    public void setBimehDateId(History bimehDateId) {
        this.bimehDateId = bimehDateId;
    }

    public History getBimehDateId() {
        return bimehDateId;
    }

    public String getPellak() {
        return pellak;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPellak(String pellak) {
        this.pellak = pellak;
    }

}
