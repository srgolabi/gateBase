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
@DatabaseTable(tableName = "individualFile")
public class IndividualFile {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(defaultValue = "")
    private String address;

    @DatabaseField(defaultValue = "")
    private String name;

    @DatabaseField(defaultValue = "")
    private String title;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "individual_id")
    private Individuals individual_id;
    
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "car_id")
    private Cars car_id;

    @DatabaseField(defaultValue = "0")
    private int sortOrder;

    public IndividualFile() {
    }

    public IndividualFile(String address) {
        this.address = address;
    }

    public IndividualFile getThisFile() {
        return this;
    }

    /*
     ***************************************************************************
     ***************************************************************************
     ***************************************************************************
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndividual_id(Individuals individual_id) {
        this.individual_id = individual_id;
    }

    public Individuals getIndividual_id() {
        return individual_id;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
