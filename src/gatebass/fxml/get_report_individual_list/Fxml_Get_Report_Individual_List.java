/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.get_report_individual_list;

import gatebass.utils.Get_Report_Parent;
import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Individuals_j;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.WorkHistory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Get_Report_Individual_List extends Get_Report_Parent<Individuals_j, WorkHistory> {

    @Override
    public void setStage(Stage s) {
        edit.setDisable(!Permission.isAcces(Permission.INDIVIDUAL_INSERT));

        super.setStage(s);

        edit.setOnAction((ActionEvent event) -> {
            my_action.get(databaseHelper.individualsDao.queryForId(tableView.getSelectionModel().getSelectedItem().getId()), true);
        });

        review.setOnAction((ActionEvent event) -> {
            my_action.get(databaseHelper.individualsDao.queryForId(tableView.getSelectionModel().getSelectedItem().getId()), false);
        });
    }

    @Override
    public void set_Query(String query) {
        t_List = FXCollections.observableArrayList(databaseHelper.individuals_jDao.rawResults(query));
        super.set_Query(query);
    }

    @Override
    public void under_table_update(Integer id) {
        work_table.getItems().setAll(databaseHelper.workHistoryDao.getAll("individuals_id", id));

        replica_Table.getItems().setAll(databaseHelper.individualReplicaDao.rawResults(
                "SELECT individualReplica.* from individualReplica\n"
                + "LEFT OUTER JOIN workhistory workhistory_j1 ON workhistory_j1.id = individualReplica.workHistory_id\n"
                + "where workhistory_j1.individuals_id = " + id
        ));

        warning_Table.getItems().setAll(databaseHelper.individualWarningDao.rawResults(
                "SELECT individualWarning.* from individualWarning\n"
                + "LEFT OUTER JOIN workhistory workhistory_j1 ON workhistory_j1.id = individualWarning.workHistory_id\n"
                + "where workhistory_j1.individuals_id = " + id
        ));
    }
}
