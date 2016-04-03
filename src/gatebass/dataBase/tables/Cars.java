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
 * @author SR.Golabi
 */
@DatabaseTable(tableName = "cars")
public class Cars {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String card_id;

    @DatabaseField(unique = true)
    private String shasi_number;

    @DatabaseField(defaultValue = "")
    private String car_name;

    @DatabaseField(defaultValue = "")
    private String model;

    @DatabaseField(defaultValue = "")
    private String comments;

    @DatabaseField(defaultValue = "false")
    private Short alarm_state;
    
    @DatabaseField(defaultValue = "")
    private String logs;

    @DatabaseField(defaultValue = "") //year + week of the year + shasi_number + "_c"
    private String filesPatch;

    public Cars() {
    }

// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------
    public Integer getId() {
        return id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public String getCard_id() {
        return card_id;
    }

    public String getShasi_number() {
        return shasi_number;
    }

    public void setAlarm_state(Short alarm_state) {
        this.alarm_state = alarm_state;
    }

    public Short getAlarm_state() {
        return alarm_state;
    }

    public String getCar_name() {
        return car_name;
    }

    public String getComments() {
        return comments;
    }

    public String getLogs() {
        return logs;
    }

    public String getFilesPatch() {
        return filesPatch;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public void setShasi_number(String shasi_number) {
        this.shasi_number = shasi_number;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public void setFilesPatch(String filesPatch) {
        this.filesPatch = filesPatch;
    }

}
