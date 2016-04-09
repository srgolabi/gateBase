package gatebass.utils;

import gatebass.GateBass;
import gatebass.fxml.warning_window.Fxml_Warning_Window;
import gatebass.utils.exel.UtilsStage2;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author reza
 */
public class UtilsStage {

    private Stage stage;
    private FXMLLoader loader;
    public Parent page;

    public UtilsStage(String fxml, boolean resizable, String title, Modality modality, Window owner) {
        try {
            loader = new FXMLLoader(GateBass.class.getResource("fxml/" + fxml));
            page = (Parent) loader.load();
            stage = new Stage();
            stage.setResizable(resizable);
            stage.setTitle(title);
            stage.initModality(modality);
            stage.initOwner(owner);
            stage.sizeToScene();
            stage.getIcons().add(new Image(GateBass.class.getResourceAsStream("resourse/app_icon.png")));
            Scene scene = new Scene(page);
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(UtilsStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UtilsStage(String fxml, String title, Modality modality, Window owner) {
        this(fxml, false, title, modality, owner);
    }

    public UtilsStage(String fxml, String title, Window owner) {
        this(fxml, false, title, Modality.APPLICATION_MODAL, owner);
    }

    public UtilsStage(Class clazz, String title, Modality modality, Window owner) {
        this(clazz.getSimpleName().toLowerCase().replaceFirst("fxml_", "") + "/" + clazz.getSimpleName() + ".fxml", false, title, modality, owner);
    }

    public UtilsStage(Class clazz, boolean resizable, String title, Modality modality, Window owner) {
        this(clazz.getSimpleName().toLowerCase().replaceFirst("fxml_", "") + "/" + clazz.getSimpleName() + ".fxml", resizable, title, modality, owner);
    }

    public Stage getStage() {
        return stage;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public static boolean showMsg(String msg, String title, boolean okIsShow, Stage thisStage) {
        UtilsStage2<Fxml_Warning_Window> utilsStage = new UtilsStage2(Fxml_Warning_Window.class, title, Modality.APPLICATION_MODAL, new Stage());
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
