/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.gate_bass;

import static gatebass.GateBass.server;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.ParentControl;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Gate_Bass extends ParentControl {

    @FXML
    public HBox root;
    @FXML
    private Label first_name;
    @FXML
    private Label last_name;
    @FXML
    private Label job_title;
    @FXML
    private Label company;
    @FXML
    private Label card_id;
    @FXML
    private Label date_expire;
    @FXML
    public Label tarikh_engheza;
    @FXML
    private Label date_issude;
    @FXML
    private Label date_issude_title;
    @FXML
    private Label national_id;
    @FXML
    private Label postal_code;
    @FXML
    private VBox pic;
    @FXML
    public MyButtonFont delete;

    @FXML
    public Arc circle_up;
    @FXML
    public Arc circle_down;
    @FXML
    public HBox down_card;

    public void set_value(WorkHistory wh) {
        Individuals iv = wh.getIndividual();
        String[] colors;
        if (wh.is_EMPLOYER_TYPE()) {
            colors = new String[]{"#77933c", "#d7e4bd", "#77933c", "#ffff00"};
        } else {
            date_issude.setVisible(false);
            date_issude_title.setVisible(false);
            colors = new String[]{"#1e90ff", "#70bef2", "#1e90ff", "#ffff00"};
        }
        circle_up.setFill(Color.valueOf(colors[0]));
        circle_down.setFill(Color.valueOf(colors[1]));
        down_card.setStyle("-fx-background-color: " + colors[2] + circle_up.getStyle());
        tarikh_engheza.setTextFill(Color.valueOf(colors[3]));
        date_issude_title.setTextFill(Color.valueOf(colors[3]));
        delete.init("trash", 12);
        first_name.setText(iv.getFirst_name());
        last_name.setText(iv.getLast_name());
        job_title.setText(wh.getJobTitle());
        company.setText(wh.getSherkat());
        card_id.setText(iv.getCard_id());
        date_expire.setText("13" + wh.getEngheza());
        date_issude.setText("13" + wh.getSodor());
        national_id.setText(iv.getNational_id());
        postal_code.setText(iv.getPostal_code());
        if (iv.getPicture_address() != null) {
            File imageFile = new File(server + iv.getFilesPatch() + iv.getPicture_address());
            pic.setStyle("-fx-background-image: url('" + imageFile.toURI().toString() + "'); -fx-background-repeat: stretch; -fx-background-size: stretch; -fx-background-position: center center; -fx-border-color:  #2e7a8c;");
        }
    }
}
