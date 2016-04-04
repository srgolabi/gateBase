package gatebass.fxml.warning_window;

import gatebass.utils.ParentControl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Warning_Window extends ParentControl{

    @FXML
    private Label label;
    @FXML
    public Button cancel;
    @FXML
    public Button ok;

    public boolean okClick = false;

    
    public void set_msg_txt(String txt) {
        label.setText(txt);
    }

    @FXML
    private void okClick() {
        okClick = true;
        thisStage.close();
    }

    @FXML
    private void cancelClick() {
        thisStage.close();
    }
}
