package gatebass.utils;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author reza
 */
public class ParentControl implements Initializable {

    public BooleanProperty isClosed = new SimpleBooleanProperty(false);
    public Stage thisStage;

    public void setStage(Stage s) {
        this.thisStage = s;
    }

    public void show_Front_Or_Wait() {
        thisStage.sizeToScene();
        if (!thisStage.isShowing()) {
            show_And_Wait();
        } else {
            show_On_Front();
        }
    }

    public void show_On_Front() {
        thisStage.toFront();
        thisStage.setIconified(false);
    }

    public void show_And_Wait() {
        thisStage.showAndWait();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
