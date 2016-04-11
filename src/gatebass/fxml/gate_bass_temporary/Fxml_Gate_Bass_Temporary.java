/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.gate_bass_temporary;

import static gatebass.GateBass.server;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.ParentControl;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Gate_Bass_Temporary extends ParentControl {

    @FXML
    public HBox root;
    @FXML
    private Label date;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label national_number;
    @FXML
    private Label job_tittle;
    @FXML
    private Label contractor;
    @FXML
    private Label expire;
    @FXML
    private HBox pic;
    @FXML
    public MyButtonFont delete;

    public void set_value(WorkHistory wh) {
        delete.init("trash", 12);
        Individuals iv = wh.getIndividual();
        date.setText(wh.getSodor());
        id.setText(iv.getCard_id());
        name.setText(iv.getFirst_name() + " " + iv.getLast_name());
        national_number.setText(iv.getNational_id());
        job_tittle.setText(wh.getJobTitle());
        contractor.setText(wh.getSherkat());
        expire.setText(wh.getEngheza());
        if (iv.getPicture_address() != null) {
            File imageFile = new File(server + iv.getFilesPatch() + iv.getPicture_address());
            pic.setStyle("-fx-background-image: url('" + server + imageFile.toURI().toString() + "'); -fx-background-repeat: stretch; -fx-background-size: stretch; -fx-background-position: center center; -fx-border-color: #000000;");
        }
    }

}
