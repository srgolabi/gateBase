package gatebass.fxml.user_change_pass;

import static gatebass.GateBass.databaseHelper;
import static gatebass.GateBass.users;
import gatebass.utils.ParentControl;
import gatebass.utils.TextFiledLimited;
import gatebass.utils.UtilsMsg;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_User_Change_Pass extends ParentControl {

    @FXML
    public TextField pass;
    @FXML
    private TextField passAgain;
    @FXML
    private Button okButton;
    @FXML
    private Button closeButton;

    public boolean okClicked = false;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        okButton.disableProperty().bind(pass.textProperty().isEmpty().or(passAgain.textProperty().isEmpty()));
        TextFiledLimited.setEnterFocuse(pass, passAgain, okButton, closeButton);
    }

    @FXML
    private void close() {
        thisStage.close();
    }

    @FXML
    private void ok() {
        if (!pass.getText().equals(passAgain.getText())) {
            UtilsMsg.show("رمزعبور با تکرار آن مطابق نیست.", "اخطار", false, thisStage);
            return;
        }
        okClicked = true;
        thisStage.close();
        users.setPassword(pass.getText());
        databaseHelper.usersDao.createOrUpdate(users);
        UtilsMsg.show("رمز عبور با موفقیت تغییر یافت.", "", false, thisStage);
    }
}
