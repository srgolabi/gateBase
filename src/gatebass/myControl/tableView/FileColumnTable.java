package gatebass.myControl.tableView;

import static gatebass.GateBass.server;
import gatebass.dataBase.tables.IndividualFile;
import gatebass.myControl.MyButtonFont;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author reza
 */
public class FileColumnTable extends Temmp<IndividualFile, TableColumn<IndividualFile, IndividualFile>, TableCell<IndividualFile, IndividualFile>> {

    private boolean editable;

    public FileColumnTable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public TableCell<IndividualFile, IndividualFile> call(TableColumn<IndividualFile, IndividualFile> param) {
        TableCell<IndividualFile, IndividualFile> cell = new TableCell<IndividualFile, IndividualFile>() {
            @Override
            protected void updateItem(IndividualFile item, boolean empty) {
                if (item == getItem()) {
                    return;
                }
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle(null);
                    setText(null);
                    setGraphic(null);
                    return;
                }

                final HBox hbox = new HBox(0);
                hbox.setAlignment(Pos.CENTER_LEFT);

                MyButtonFont fileDownlaod = new MyButtonFont("download", 14, "table-button");

                fileDownlaod.setOnAction((javafx.event.ActionEvent event) -> {
                    FileChooser fileChooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Files (*." + getTypeFile(item.getAddress()) + ")", "*." + getTypeFile(item.getAddress()));
                    fileChooser.getExtensionFilters().add(extFilter);
                    File file = fileChooser.showSaveDialog(this.getScene().getWindow());
                    if (file == null) {
                        return;
                    }
                    try {
                        FileUtils.copyFile(new File((item.getId() == null ? "" : server) + item.getAddress()), file);
                    } catch (IOException ex) {
                        Logger.getLogger(FileColumnTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                FilterMenuButton fileView = new FilterMenuButton(getTypeFile(item.getAddress()) + "-button");
                fileView.setOnAction((ActionEvent event) -> {
                    runFile(item.getAddress(), item.getId() != null);
                });

                if (editable) {
                    MyButtonFont fileDelete = new MyButtonFont("trash", 14, "table-button");

                    fileDelete.setOnAction((javafx.event.ActionEvent event) -> {
                        if (delete != null) {
                            delete.add(this.getTableView().getItems().get(this.getIndex()));
                        }
                        this.getTableView().getItems().remove(getIndex());
                    });

                    TextField text = new TextField(item.getTitle());
                    text.setAlignment(Pos.CENTER);
                    text.setStyle(text.getStyle() + "-fx-font-size : 12;");
                    text.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                        item.setTitle(text.getText());
                    });

                    HBox.setHgrow(text, Priority.ALWAYS);
                    final VBox Vbox = new VBox(0);
                    MyButtonFont fileUp = new MyButtonFont("up_open", 7, "table-button");

                    MyButtonFont fileDown = new MyButtonFont("down_open", 7, "table-button");

                    Vbox.setAlignment(Pos.CENTER);
                    Vbox.getChildren().addAll(fileUp, fileDown);

                    fileDown.setOnAction((ActionEvent event) -> {
                        if (getIndex() < getTableView().getItems().size() - 1) {
                            IndividualFile temp = getTableView().getItems().get(getIndex());
                            getTableView().getItems().set(getIndex(), getTableView().getItems().get(getIndex() + 1));
                            getTableView().getItems().set(getIndex() + 1, temp);
                        }
                    });
                    fileUp.setOnAction((ActionEvent event) -> {
                        if (getIndex() > 0) {
                            IndividualFile temp = getTableView().getItems().get(getIndex());
                            getTableView().getItems().set(getIndex(), getTableView().getItems().get(getIndex() - 1));
                            getTableView().getItems().set(getIndex() - 1, temp);
                        }
                    });

                    hbox.getChildren().addAll(fileDelete, fileDownlaod, text, fileView, Vbox);
                } else {
//                    HBox.setMargin(fileView, new Insets(0, 20, 0, 0));
                    Label title = new Label(item.getTitle());
                    HBox.setHgrow(title, Priority.ALWAYS);
                    title.setMaxWidth(Integer.MAX_VALUE);
                    title.setAlignment(Pos.CENTER);
                    title.setStyle(title.getStyle() + "-fx-font-size : 12;");
                    hbox.getChildren().addAll(fileDownlaod, fileView, title);
                }

                setOnMouseClicked((MouseEvent event) -> {
                    if (event.getClickCount() >= 2) {
                        runFile(item.getAddress(), item.getId() != null);
                    }
                });

                setGraphic(hbox);

            }
        };
        return cell;
    }

    public String getTypeFile(String items) {
        try {
            return items.substring(items.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return null;
        }
    }

    public void runFile(String pach, boolean b) {
        try {
            if (b) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + server + pach);
            } else {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pach);
            }

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }
}
