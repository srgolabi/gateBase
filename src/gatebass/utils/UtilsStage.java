package gatebass.utils;

import gatebass.GateBass;
import gatebass.fxml.warning.FXMLWarningController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        UtilsStage utilsStage = new UtilsStage("warning/FXMLWarning.fxml", title, thisStage.getOwner());
        FXMLWarningController controller = utilsStage.getLoader().getController();
        controller.init(utilsStage.getStage(), msg);
        controller.ok.setVisible(okIsShow);
        if (okIsShow) {
            controller.ok.setDefaultButton(true);
            controller.cancel.setCancelButton(true);
        } else {
            controller.cancel.setDefaultButton(true);
        }

        utilsStage.getStage().showAndWait();
        return controller.okClick;
    }
}
