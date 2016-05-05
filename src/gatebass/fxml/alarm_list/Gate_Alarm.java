/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.alarm_list;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "gate_alarm")
public class Gate_Alarm {

    public BooleanProperty is_cheked = new SimpleBooleanProperty(false);
    public StringProperty icon = new SimpleStringProperty("\uE802");

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private Integer ids;

    @DatabaseField
    private String card_id;

    @DatabaseField
    private String alarm_type;

    @DatabaseField
    private String datail;

    @DatabaseField
    private String company_fa;

    @DatabaseField(defaultValue = "0")
    private Short alarm_stat;

    public Gate_Alarm getThis() {
        icon.set(alarm_stat == 0 ? "\uE802" : "\uE803");
        return this;
    }

    public Integer getIds() {
        return ids;
    }

    public String getCard_id() {
        return card_id;
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public String getDatail() {
        return datail;
    }

    public Integer getId() {
        return id;
    }

    public Short getAlarm_state() {
        return alarm_stat;
    }

    public void setAlarm_stat(Short alarm_stat) {
        this.alarm_stat = alarm_stat;
    }

    public String getCompany_fa() {
        return company_fa;
    }

}
