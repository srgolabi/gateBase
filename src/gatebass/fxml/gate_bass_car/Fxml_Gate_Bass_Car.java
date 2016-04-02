/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.gate_bass_car;

import gatebass.dataBase.tables.Cars;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Hamid Reza daneshjoo
 */
public class Fxml_Gate_Bass_Car implements Initializable {

    @FXML
    private Label id;
    @FXML
    private Label contractor;
    @FXML
    private Label car_name;
    @FXML
    private Label name;
    @FXML
    private Label pellak;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     *
     * @param ct
     */
    public void init(Cars ct) {
//        contractor.setText(ct.getCompany());
//        car_name.setText(ct.getCar_name());
//        name.setText(ct.getFirst_name() + " " + ct.getLast_name());
//        date.setText(getDate(ct.getIssunce_year(), ct.getIssunce_month(), ct.getIssunce_day()));
//        pellak.setText(
//                "ایران " + ct.getPellak_1() + " - " +
//                ct.getPellak_4() + " " + ct.getPellak_3() + " " + ct.getPellak_2()
//        );
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
