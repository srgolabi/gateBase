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

/**
 *
 * @author reza
 */
@DatabaseTable(tableName = "gate_alarm")
public class Gate_Alarm {

    public boolean is_cheked;
    
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
    private String alrm_info;

    public Gate_Alarm getThis() {
        is_cheked = new SimpleBooleanProperty(false);
        return this;
    }

    public int getIds() {
        return ids;
    }

    public String getCard_id() {
        return card_id;
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public String getAlarm_type_text() {
        return alarm_type.equals("car") ? "خودرو" : "فرد";
    }

    public String getDatail() {
        return datail;
    }

    public String getAlrm_info() {
        if (alrm_info.equals("expiration")) {
            return "تاریخ انقضاء";
        } else if (alrm_info.equals("certificate")) {
            return "تاریخ سلامت";
        } else if (alrm_info.equals("bimeh")) {
            return "تاریخ بیمه";
        }
        return "";
    }

}
