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
@DatabaseTable(tableName = "individualReplica")
public class IndividualReplica extends Base {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "history_id")
    private History history_id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "workHistory_id")
    private WorkHistory workHistory_id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "carHistory_id")
    private CarHistory carHistory_id;

    @DatabaseField(defaultValue = "0")
    private String mablagh;

    @DatabaseField(defaultValue = "")
    private String description;

    public IndividualReplica() {
    }

    public IndividualReplica(History history_id, String mablagh, String description) {
        this.history_id = history_id;
        this.mablagh = mablagh;
        this.description = description;
    }

    public String getTarikh() {
        return this.getHistory_id().getDate();
    }

    /*
     ***************************************************************************
     ***************************************************************************
     ***************************************************************************
     */
    public Integer getId() {
        return id;
    }

    public String getMablagh() {
        return mablagh;
    }

    public void setHistory_id(History history_id) {
        this.history_id = history_id;
    }

    public History getHistory_id() {
        return history_id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkHistory getWorkHistory_id() {
        return workHistory_id;
    }

    public CarHistory getCarHistory_id() {
        return carHistory_id;
    }

    public void setWorkHistory_id(WorkHistory WorkHistory_id) {
        this.workHistory_id = WorkHistory_id;
    }

    public void setCarHistory_id(CarHistory CarHistory_id) {
        this.carHistory_id = CarHistory_id;
    }

    public void setMablagh(String mablagh) {
        this.mablagh = mablagh;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
