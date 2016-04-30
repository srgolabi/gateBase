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
@DatabaseTable(tableName = "individualWarning")
public class IndividualWarning {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "history_id")
    private History history_id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "workHistory_id")
    private WorkHistory workHistory_id;

    @DatabaseField(defaultValue = "")
    private String description;

    public IndividualWarning() {
    }

    public IndividualWarning(History history_id, String description) {
        this.history_id = history_id;
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

    public History getHistory_id() {
        return history_id;
    }

    public WorkHistory getWorkHistory_id() {
        return workHistory_id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHistory_id(History history_id) {
        this.history_id = history_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkHistory_id(WorkHistory workHistory_id) {
        this.workHistory_id = workHistory_id;
    }

}
