package gatebass.utils;

import gatebass.fxml.warning_window.Fxml_Warning_Window;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author reza
 */
public class UtilsMsg {

    public static boolean show(String msg, String title, boolean okIsShow, Stage thisStage) {
        UtilsStage<Fxml_Warning_Window> utilsStage = new UtilsStage(Fxml_Warning_Window.class, title, Modality.APPLICATION_MODAL, new Stage());
        utilsStage.t.set_msg_txt(msg);
        utilsStage.t.ok.setVisible(okIsShow);
        if (okIsShow) {
            utilsStage.t.ok.setDefaultButton(true);
            utilsStage.t.cancel.setCancelButton(true);
        } else {
            utilsStage.t.cancel.setDefaultButton(true);
        }
        utilsStage.t.show_And_Wait();
        return utilsStage.t.okClick;
    }
}
