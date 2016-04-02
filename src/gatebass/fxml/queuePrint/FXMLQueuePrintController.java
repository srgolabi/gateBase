/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.queuePrint;

import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.gateBass.FXMLGateBassController;
import gatebass.utils.ParentControl;
import gatebass.utils.UtilsStage;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class FXMLQueuePrintController extends ParentControl {

    public interface Action {
        void delete(WorkHistory wh_d);
    }
    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vBox;

    private Action action;

    public void set_action_property(Action action) {
        this.action = action;
    }

    List<FXMLGateBassController> list;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        list = new ArrayList<>();
    }

    public void set_value(List<WorkHistory> work_list) {
        for (WorkHistory wh : work_list) {
            UtilsStage utilsStage = new UtilsStage("gateBass/FXMLGateBass.fxml", "", Modality.APPLICATION_MODAL, thisStage.getOwner());
            FXMLGateBassController controller = utilsStage.getLoader().getController();
            controller.delete.setOnAction((ActionEvent event) -> {
                action.delete(wh);
            });
            controller.set_value(wh);
            vBox.getChildren().add(utilsStage.page);
            list.add(controller);
        }
    }
}
