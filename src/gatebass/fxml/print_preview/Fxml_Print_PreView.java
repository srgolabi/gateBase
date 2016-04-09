/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.print_preview;

import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.gateBassTemporary.FXMLGateBassTemporary;
import gatebass.fxml.gate_bass_car.Fxml_Gate_Bass_Car;
import gatebass.fxml.queuePrint.FXMLQueuePrintController;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.ParentControl;
import gatebass.utils.TextFiledLimited;
import gatebass.utils.UtilsStage;
import gatebass.utils.exel.UtilsStage2;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Print_PreView extends ParentControl {

    @FXML
    private VBox container;
    @FXML
    private MyButtonFont print;

    @FXML
    private MyButtonFont print_count_increase;
    @FXML
    private MyButtonFont print_count_decrease;
    @FXML
    private TextField print_count;
    @FXML
    private Button printer_selecter;
    @FXML
    private MyButtonFont printer_selecter_down_icon;
    @FXML
    private RadioButton print_all;
    @FXML
    private RadioButton print_current;
    @FXML
    private TextField print_selecttion_page;
    @FXML
    private Label paper_size;
    @FXML
    private RadioButton page_orientation_PORTRAIT;
    @FXML
    private RadioButton page_orientation_LANDSCAPE;

    @FXML
    private MyButtonFont next_page;
    @FXML
    private MyButtonFont back_page;
    @FXML
    private TextField page_number;
    @FXML
    private Label page_total;
    @FXML
    private TableView<Printer> tableView_printers;
    @FXML
    private Button delete_all;

    private Printer active_printer;

    private List<WorkHistory> work_list;
    private int gate_count = 0;
    private int gate_page_total = 0;

    private PageLayout layout_default;
    private PageLayout layout_gate;
    private PageLayout layout_temporary;
    private PageLayout layout_car;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        print.init("print", 30);
        next_page.init("right_open", 13);
        back_page.init("left_open", 13);
        print_count_increase.init("up_open", 9, "table-button");
        print_count_decrease.init("down_open", 9, "table-button");
        printer_selecter_down_icon.init("down_open", 10, "table-button");
        printer_selecter_down_icon.setPrefHeight(30);

        print_all.disableProperty().bind(print_selecttion_page.textProperty().isEmpty().not());
        print_current.disableProperty().bind(print_all.disableProperty());

        TextFiledLimited.set_Number_Length_Limit(print_count, 3);
        TextFiledLimited.set_Number_Limit(print_selecttion_page);
        TextFiledLimited.set_Number_Limit(page_number);

        print_count_increase.setOnAction((ActionEvent event) -> {
            set_print_count(1);
        });
        print_count_decrease.setOnAction((ActionEvent event) -> {
            set_print_count(-1);
        });
        print_count.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            set_print_count(0);
        });

        printer_selecter_down_icon.setOnAction((ActionEvent event) -> {
            tableView_printers.setVisible(!tableView_printers.isVisible());
        });
        printer_selecter.setOnAction((ActionEvent event) -> {
            tableView_printers.setVisible(!tableView_printers.isVisible());
        });
        TableColumn tableColumn = tableView_printers.getColumns().get(0);
        tableColumn.setCellFactory((Object param) -> new TableCell<String, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setTextFill(Color.valueOf("#2e7a8c"));
                setText(item);
            }

        });
        tableView_printers.setRowFactory((TableView<Printer> param) -> {
            TableRow<Printer> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                active_printer = row.getItem();
                printer_selecter.setText(row.getItem().getName());
                tableView_printers.setVisible(false);
            });
            return row;
        });

        page_number.setOnKeyTyped((KeyEvent event) -> {
            int page_num = getINT(page_number);
            if (page_num < 1 || page_num > getINT(page_total)) {
                event.consume();
            }
        });
        next_page.setOnAction((ActionEvent event) -> {
            set_page_number(1);
        });
        back_page.setOnAction((ActionEvent event) -> {
            set_page_number(-1);
        });
        active_printer = Printer.getDefaultPrinter();
        printer_selecter.setText(active_printer.getName());
        tableView_printers.setItems(FXCollections.observableArrayList(Printer.getAllPrinters()));
        layout_gate = active_printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
        layout_temporary = active_printer.createPageLayout(Paper.A5, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
        layout_car = active_printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
        layout_default = layout_gate;

        page_orientation_PORTRAIT.setOnAction((ActionEvent event) -> {
            on_change_paper_parametr(PageOrientation.PORTRAIT);
        });
        page_orientation_LANDSCAPE.setOnAction((ActionEvent event) -> {
            on_change_paper_parametr(PageOrientation.LANDSCAPE);
        });

        print.setOnAction((ActionEvent event) -> {
            if (print_all.isSelected() && !print_all.isDisable()) {
                page_number.setText("0");
                for (int i = 0; i < getINT(page_total); i++) {
                    set_page_number(1);
                    print_page();
                }
            } else if (print_current.isSelected() && !print_current.isDisable()) {
                print_page();
            } else if (!print_selecttion_page.getText().isEmpty()) {
                if (getINT(print_selecttion_page) <= getINT(page_total)) {
                    page_number.setText((getINT(print_selecttion_page) - 1) + "");
                    set_page_number(1);
                } else {
                    return;
                }
            } else {
                return;

            }

        });

        delete_all.setOnAction((ActionEvent event) -> {
            work_list.clear();
            gatebass.GateBass.work_list.clear();
            thisStage.close();
        });

    }

    private void print_page() {
        PrinterJob printerJob = PrinterJob.createPrinterJob(active_printer);
        if (printerJob != null) {
            try {
                printerJob.getJobSettings().setPageLayout(layout_default);
                Boolean succes = printerJob.printPage(container);
                if (succes) {
                    printerJob.endJob();
                }
            } catch (Exception e) {
            }
        }
    }

    private void on_change_paper_parametr(PageOrientation po) {
        if (layout_default == layout_gate) {
            layout_gate = active_printer.createPageLayout(layout_gate.getPaper(), po, Printer.MarginType.EQUAL);
        } else if (layout_default == layout_temporary) {
            layout_temporary = active_printer.createPageLayout(layout_temporary.getPaper(), po, Printer.MarginType.EQUAL);
        } else if (layout_default == layout_car) {
            layout_car = active_printer.createPageLayout(layout_car.getPaper(), po, Printer.MarginType.EQUAL);
        }
    }

    private void set_print_count(int n) {
        if (print_count.getText().isEmpty()) {
            print_count.setText("1");
        }
        int t = getINT(print_count);
        if ((n == -1 && t > 1) || (n == 1 && t < 999)) {
            print_count.setText((t + n) + "");
        }
    }

    private void set_page_number(int n) {
        if (page_number.getText().isEmpty()) {
            page_number.setText("1");
        }
        int total_page = getINT(page_total);
        int page_num = getINT(page_number);
        if ((n == -1 && page_num > 1) || (n == 1 && page_num < total_page)) {
            page_number.setText((page_num + n) + "");
            go_to();
        }
    }

    public void set_value(List<WorkHistory> work_list) {
        this.work_list = work_list;
        int temporary_count = 0;
        List<WorkHistory> wh_temp1 = new ArrayList<>();
        List<WorkHistory> wh_temp2 = new ArrayList<>();
        for (WorkHistory wh : work_list) {
            if (wh.is_TEMPORARY_TYPE() || wh.is_CAR_TYPE()) {
                temporary_count++;
                wh_temp2.add(wh);
            } else {
                wh_temp1.add(wh);
            }
        }
        work_list.clear();
        work_list.addAll(wh_temp1);
        work_list.addAll(wh_temp2);
        gate_count = work_list.size() - temporary_count;
        gate_page_total = gate_count == 0 ? 0 : (gate_count - 1) / 4 + 1;
        page_total.setText(gate_page_total + temporary_count + "");
        if (getINT(page_total) < getINT(page_number)) {
            page_number.setText(page_total.getText());
        }
        go_to();
    }

    private void go_to() {
        if (getINT(page_number) <= gate_page_total) {
            set_gate_value();
        } else {
            set_temporary_value();
        }
    }

    private void set_gate_value() {
        layout_default = layout_gate;
        set_layout_parametr();
        UtilsStage utilsStage = new UtilsStage("queuePrint/FXMLQueuePrint.fxml", "", Modality.APPLICATION_MODAL, thisStage.getOwner());
        FXMLQueuePrintController controller = utilsStage.getLoader().getController();
        controller.setStage(utilsStage.getStage());
        int page_num = getINT(page_number) * 4;
        int end_page = getINT(page_number) == gate_page_total ? page_num - gate_count : 0;
        controller.set_value(work_list.subList(page_num - 4, page_num - end_page));
        controller.set_action_property((WorkHistory wh_d) -> {
            work_list.remove(wh_d);
            set_value(work_list);
        });
        container.getChildren().clear();
        container.getChildren().add(utilsStage.page);
    }

    private void set_temporary_value() {
        if (work_list.get(work_list.size() - (getINT(page_total) - getINT(page_number)) - 1).is_CAR_TYPE()) {
            set_car_value();
            return;
        }
        layout_default = layout_temporary;
        set_layout_parametr();
        UtilsStage utilsStage = new UtilsStage("gateBassTemporary/FXMLGateBassTemporary.fxml", "", Modality.APPLICATION_MODAL, thisStage.getOwner());
        FXMLGateBassTemporary controller = utilsStage.getLoader().getController();
        controller.setStage(utilsStage.getStage());
        controller.set_value(work_list.get(work_list.size() - (getINT(page_total) - getINT(page_number)) - 1));

        controller.delete.setOnAction((ActionEvent event) -> {
            work_list.remove(work_list.get(work_list.size() - (getINT(page_total) - getINT(page_number)) - 1));
            set_value(work_list);
        });
        container.getChildren().clear();
        container.getChildren().add(utilsStage.page);
    }

    private void set_car_value() {
        layout_default = layout_car;
        set_layout_parametr();
        UtilsStage2<Fxml_Gate_Bass_Car> utilsStage = new UtilsStage2(Fxml_Gate_Bass_Car.class, "", Modality.APPLICATION_MODAL, thisStage);
        utilsStage.t.set_value(work_list.get(work_list.size() - (getINT(page_total) - getINT(page_number)) - 1));
        utilsStage.t.delete.setOnAction((ActionEvent event) -> {
            work_list.remove(work_list.get(work_list.size() - (getINT(page_total) - getINT(page_number)) - 1));
            set_value(work_list);
        });
        container.getChildren().clear();
        container.getChildren().add(utilsStage.t.parent);
    }

    private void set_layout_parametr() {
        paper_size.setText(layout_default.getPaper().getName());
        if (layout_default.getPageOrientation() == PageOrientation.LANDSCAPE) {
            page_orientation_LANDSCAPE.setSelected(true);
        } else {
            page_orientation_PORTRAIT.setSelected(true);
        }
    }

    private int getINT(TextField tf) {
        return Integer.parseInt(tf.getText());
    }

    private int getINT(Label tf) {
        return Integer.parseInt(tf.getText());
    }

}
