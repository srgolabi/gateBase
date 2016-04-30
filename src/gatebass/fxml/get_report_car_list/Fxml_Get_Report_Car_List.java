/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.get_report_car_list;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.CarHistory;
import gatebass.dataBase.tables.Cars_j;
import gatebass.dataBase.tables.Permission;
import gatebass.utils.Get_Report_Parent;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Get_Report_Car_List extends Get_Report_Parent<Cars_j, CarHistory> {

    @Override
    public void setStage(Stage s) {
        edit.setDisable(!Permission.isAcces(Permission.CAR_INSERT));

        super.setStage(s);

        edit.setOnAction((ActionEvent event) -> {
            my_action.get(databaseHelper.carDao.queryForId(tableView.getSelectionModel().getSelectedItem().getId()), true);
        });

        review.setOnAction((ActionEvent event) -> {
            my_action.get(databaseHelper.carDao.queryForId(tableView.getSelectionModel().getSelectedItem().getId()), false);
        });
    }

    @Override
    public void set_Query(String query) {
        t_List = FXCollections.observableArrayList(databaseHelper.cars_jDao.rawResults(query));
        super.set_Query(query);
    }

    @Override
    public void under_table_update(Integer id) {
        work_table.getItems().setAll(databaseHelper.carsHistoryDao.getAll("car_id", id));
        replica_Table.getItems().setAll(databaseHelper.individualReplicaDao.getAll("car_id", id));
    }
}
