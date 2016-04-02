package gatebass.fxml.get_report;

import gatebass.myControl.tableView.*;
import gatebass.myControl.MyButtonFont;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import gatebass.fxml.get_report.Fxml_Get_Report.Query_Base;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 *
 * @author reza
 */
public class QueryColumnTable extends Temmp<Query_Base, TableColumn<Query_Base, Query_Base>, TableCell<Query_Base, Query_Base>> {

    public interface OnEdit<Query_Base> {

        void edit(Query_Base qb, int index);
    }

    public void setOnEdit(OnEdit<Query_Base> oe) {
        onEdit = oe;
    }

    public OnEdit<Query_Base> onEdit;

    public QueryColumnTable() {
    }

    @Override
    public TableCell<Query_Base, Query_Base> call(TableColumn<Query_Base, Query_Base> param) {
        TableCell<Query_Base, Query_Base> cell = new TableCell<Query_Base, Query_Base>() {
            @Override
            protected void updateItem(Query_Base item, boolean empty) {
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

                MenuItem m1 = new MenuItem(") +");
                m1.setOnAction((ActionEvent event) -> {
                    item.open_bracket.set("(" + item.open_bracket.get());
                });
                MenuItem m2 = new MenuItem(") -");
                m2.disableProperty().bind(item.open_bracket.isEmpty());
                m2.setOnAction((ActionEvent event) -> {
                    item.open_bracket.set(item.open_bracket.get().substring(1));
                });
                MenuItem m3 = new MenuItem("+ (");
                m3.setOnAction((ActionEvent event) -> {
                    item.close_bracket.set(item.close_bracket.get() + ")");
                });
                MenuItem m4 = new MenuItem("- (");
                m4.disableProperty().bind(item.close_bracket.isEmpty());
                m4.setOnAction((ActionEvent event) -> {
                    item.close_bracket.set(item.close_bracket.get().substring(1));
                });
                ContextMenu cm = new ContextMenu(m1, m2, m3, m4);
                setContextMenu(cm);

                final HBox hbox = new HBox(0);
                hbox.setAlignment(Pos.CENTER_LEFT);

                Button AndOr = new Button();
                AndOr.getStyleClass().add("table-button");
                AndOr.textProperty().bind(item.and_or);
                AndOr.visibleProperty().bind(item.and_or.isEmpty().not());
                AndOr.setOnAction((ActionEvent event) -> {
                    item.and_or.set(item.and_or.get().equals("و  ") ? "یا  " : "و  ");
                });

                if (getIndex() == 0) {
                    item.and_or.set("");
                } else if (item.and_or.get().isEmpty()) {
                    item.and_or.set("و  ");
                }

                MyButtonFont edit = new MyButtonFont("pencil", 14, "table-button");
                edit.setOnAction((ActionEvent event) -> {
                    onEdit.edit(item, getIndex());
                });

                MyButtonFont fileDelete = new MyButtonFont("trash", 14, "table-button");
                fileDelete.setOnAction((ActionEvent event) -> {
                    this.getTableView().getItems().remove(getIndex());
                });

                Label title = new Label();
                title.textProperty().bind(item.and_or.concat(item.open_bracket).concat(item.table_title).concat(item.close_bracket));
                HBox.setHgrow(title, Priority.ALWAYS);
                title.setMaxWidth(Integer.MAX_VALUE);
                title.setAlignment(Pos.CENTER);
                title.setStyle(title.getStyle() + "-fx-font-size : 13;");

                final VBox Vbox = new VBox(0);
                MyButtonFont fileUp = new MyButtonFont("up_open", 7, "table-button");

                MyButtonFont fileDown = new MyButtonFont("down_open", 7, "table-button");

                Vbox.setAlignment(Pos.CENTER);
                Vbox.getChildren().addAll(fileUp, fileDown);

                fileDown.setOnAction((ActionEvent event) -> {
                    if (getIndex() < getTableView().getItems().size() - 1) {
                        Query_Base temp = getTableView().getItems().get(getIndex());
                        getTableView().getItems().set(getIndex(), getTableView().getItems().get(getIndex() + 1));
                        getTableView().getItems().set(getIndex() + 1, temp);
                    }
                });
                fileUp.setOnAction((ActionEvent event) -> {
                    if (getIndex() > 0) {
                        Query_Base temp = getTableView().getItems().get(getIndex());
                        getTableView().getItems().set(getIndex(), getTableView().getItems().get(getIndex() - 1));
                        getTableView().getItems().set(getIndex() - 1, temp);
                    }
                });
                fileDown.disableProperty().bind(indexProperty().lessThan(getTableView().getItems().size() - 1).not());
                fileUp.disableProperty().bind(indexProperty().greaterThan(0).not());
                hbox.getChildren().addAll(AndOr, title, edit, fileDelete, Vbox);

                setOnMouseClicked((MouseEvent event) -> {
                    if (event.getClickCount() >= 2) {
                    }
                });

                setGraphic(hbox);

            }
        };
        return cell;
    }
}
