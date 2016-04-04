/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.gate_bass_car;

import gatebass.dataBase.tables.WorkHistory;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.ParentControl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author SR.GOLABI
 */
public class Fxml_Gate_Bass_Car extends ParentControl {

    @FXML
    public VBox parent;
    @FXML
    private Label id;
    @FXML
    private Label contractor;
    @FXML
    private Label car_name;
    @FXML
    private Label driver_name;
    @FXML
    private Label pellak;
    @FXML
    private Label date;
    @FXML
    public MyButtonFont delete;

    public void set_value(WorkHistory wh) {

//        delete.init("trash", 12);
        id.setText(wh.getCar_history_id().getCar_id().getCard_id());
        contractor.setText(wh.getCar_history_id().getSherkat());
        car_name.setText(wh.getCar_history_id().getCar_id().getCar_name());
        driver_name.setText(wh.getCar_history_id().getDriver_name());
        pellak.setText(wh.getCar_history_id().getPellak());
        date.setText(wh.getCar_history_id().getEngheza());
    }

}
