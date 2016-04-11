package gatebass.fxml.login;

import static gatebass.GateBass.databaseHelper;
import static gatebass.GateBass.register;
import gatebass.dataBase.tables.Users;
import gatebass.utils.ParentControl;
import gatebass.utils.UtilsMsg;
import java.sql.SQLException;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Login extends ParentControl {

    public boolean isAccess = false;
    public Users usersTemp;

    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private Button okButton;
    @FXML
    private CheckBox remember;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        okButton.disableProperty().bind(userName.textProperty().isEmpty().or(password.textProperty().isEmpty()));

        userName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (oldValue) {
                if (!userName.getText().equals("") && userName.getEffect() != null) {
                    userName.setEffect(null);
                }
            }
        });

        password.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (oldValue) {
                if (!password.getText().equals("") && password.getEffect() != null) {
                    password.setEffect(null);
                }
            }
        });
    }

    @FXML
    public void actionOk() throws SQLException {
        List<Users> userses = databaseHelper.usersDao.rawResults(
                "SELECT * FROM users WHERE UPPER(username) LIKE 'VALUE'".replace("VALUE", userName.getText().toUpperCase()));
        if (userses.isEmpty()) {
            UtilsMsg.show("نام کاربری صحیح نمی باشد.", "هشدار", false, thisStage);
            userName.requestFocus();
            return;
        }

        if (!userses.get(0).getPassword().equals(password.getText())) {
            UtilsMsg.show("رمز عبور صحیح نمی باشد.", "اخطار", false, thisStage);
            password.requestFocus();
            return;
        }

        if (!userses.get(0).getActive()) {
            UtilsMsg.show("این نام کاربری غیرفعال شده است.", "اخطار", false, thisStage);
            return;
        }
        register.userID = userses.get(0).getId();
        usersTemp = userses.get(0);
        register.checkLogin(remember.isSelected());
        isAccess = true;
        thisStage.close();
    }

    @FXML
    public void actionCancel() {
        thisStage.close();
    }
}
