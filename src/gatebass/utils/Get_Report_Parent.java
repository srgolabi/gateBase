/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.utils;

import static gatebass.GateBass.users;
import gatebass.dataBase.tables.Base_j;
import gatebass.dataBase.tables.IndividualReplica;
import gatebass.dataBase.tables.IndividualWarning;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.ParentControl;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.google.jhsheets.filtered.FilteredTableView;
import org.google.jhsheets.filtered.operators.BooleanOperator;
import org.google.jhsheets.filtered.operators.NumberOperator;
import org.google.jhsheets.filtered.operators.StringOperator;
import org.google.jhsheets.filtered.tablecolumn.AbstractFilterableTableColumn;
import org.google.jhsheets.filtered.tablecolumn.ColumnFilterEvent;
import org.google.jhsheets.filtered.tablecolumn.FilterableBooleanTableColumn;
import org.google.jhsheets.filtered.tablecolumn.FilterableIntegerTableColumn;
import org.google.jhsheets.filtered.tablecolumn.FilterableLongTableColumn;
import org.google.jhsheets.filtered.tablecolumn.FilterableStringTableColumn;

/**
 * FXML Controller class
 *
 * @author reza
 * @param <T>
 * @param <Y>
 */
public class Get_Report_Parent<T extends Base_j, Y> extends ParentControl {

    @FXML
    public MyButtonFont edit;
    @FXML
    public MyButtonFont review;
    @FXML
    private MyButtonFont export_to_excel;
    @FXML
    private MyButtonFont download_file;

    @FXML
    private Label userName;
    @FXML
    private Label sum;

    @FXML
    public FilteredTableView<T> tableView;
    @FXML
    public TableView<Y> work_table;
    @FXML
    public TableView<IndividualWarning> warning_Table;
    @FXML
    public TableView<IndividualReplica> replica_Table;
    @FXML
    private TextField textField;

    public ObservableList<T> t_List;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);

        thisStage.getScene().setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case ENTER:
                    if (edit.isDisable()) {
                        review.getOnAction().handle(null);
                    } else {
                        edit.getOnAction().handle(null);
                    }
                    break;
            }
        });

        review.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        if (!edit.isDisabled()) {
            edit.disableProperty().bind(review.disableProperty());
        }
        download_file.disableProperty().bind(review.disableProperty());

        edit.init("pencil", 15);
        review.init("eye", 15);
        download_file.init("download", 15);
        export_to_excel.init("export", 15);

        download_file.setOnAction((ActionEvent event) -> {
//            Printer printer = Printer.getDefaultPrinter();
//            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);
//
//            double scaleX = pageLayout.getPrintableWidth() / tableView.getBoundsInParent().getWidth();
//            double scaleY = pageLayout.getPrintableHeight() / tableView.getBoundsInParent().getHeight();
//            tableView.getTransforms().add(new Scale(scaleX, scaleY));
//
//            PrinterJob printerJob = PrinterJob.createPrinterJob();
//            if (printerJob.showPrintDialog(s) && printerJob.printPage(pageLayout, tableView)) {
//                printerJob.endJob();
//                System.out.println("printed");
//            }
//            FileChooser fileChooser = new FileChooser();
//            File file = fileChooser.showSaveDialog(thisStage);
//            if (file == null) {
//                return;
//            }
//            try {
//                file.mkdirs();
//                List<IndividualFile> files_list = databaseHelper.individualFileDao.getAll("individual_id", databaseHelper.individualsDao.queryForId(tableView.getSelectionModel().getSelectedItem().getId()));
//                for (IndividualFile f : files_list) {
//                    FileUtils.copyFileToDirectory(new File(f.getAddress()), file);
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(Fxml_Get_Report_Individual_List.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });

        export_to_excel.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("انتقال به اکسل");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Excel(*.xls)", "*.xls"));
            File file = fileChooser.showSaveDialog(thisStage);
            if (file == null) {
                return;
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(file);
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet worksheet = workbook.createSheet("گزارش");
                int cellCount = 1;
                HSSFRow row = worksheet.createRow(0);
                row.createCell(0).setCellValue("ردیف");
                for (TableColumn tc : tableView.getColumns()) {
                    if (tc.isVisible()) {
                        HSSFCell cell = row.createCell(cellCount);
                        cell.setCellValue(tc.getText() + "");
                        cellCount++;
                    }
                }
                int rowCount = 1;
                for (T lf : tableView.getItems()) {
                    row = worksheet.createRow(rowCount);
                    cellCount = 1;
                    row.createCell(0).setCellValue(rowCount + "");

                    for (TableColumn tc : tableView.getColumns()) {
                        if (tc.isVisible()) {
                            HSSFCell cell = row.createCell(cellCount);
                            try {
                                cell.setCellValue(tc.getCellObservableValue(rowCount - 1).getValue() + "");
                            } catch (Exception e) {
                            }
                            cellCount++;
                        }
                    }
                    rowCount++;
                }

                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        userName.setText("کاربر : " + users.getName_fa());
        sum.textProperty().bind(Bindings.size(tableView.itemsProperty().get()).asString());
        tableView.setFixedCellSize(35d);

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            tableFiltered(textFiltered(newValue));
        });

        tableView.addEventHandler(ColumnFilterEvent.FILTER_CHANGED_EVENT, (ColumnFilterEvent event) -> {
            tableFiltered(textFiltered(textField.getText()));
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends T> observable, T oldValue, T newValue) -> {
            if (newValue != null) {
                under_table_update(newValue.getId());
            } else {
                replica_Table.getItems().clear();
                work_table.getItems().clear();
                warning_Table.getItems().clear();
            }
        });

        tableView.setRowFactory((TableView<T> param) -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                if (row.getIndex() == tableView.getSelectionModel().getSelectedIndex() && event.getClickCount() >= 2) {
                    if (edit.isDisable()) {
                        review.getOnAction().handle(null);
                    } else {
                        edit.getOnAction().handle(null);
                    }
                }
            });
            return row;
        });
    }

    @Override
    public void show_And_Wait() {
        Platform.runLater(() -> {
            thisStage.setMaximized(true);
        });
        super.show_And_Wait();
    }

    @Override
    public void on_close() {
        super.on_close();
        thisStage.setMaximized(false);
    }

    public void set_Query(String query) {
        tableView.getItems().setAll(t_List);
    }

    private ObservableList<T> textFiltered(String newValue) {
        final List<T> remove = new ArrayList<>();
        final ObservableList<T> newData = FXCollections.concat(t_List);
        if (!newValue.trim().equals("")) {
            newData.stream().filter((item) -> (!item.get_filter_text().contains(newValue))
            ).forEach((item) -> {
                remove.add(item);
            });
            newData.removeAll(remove);
        }
        return newData;
    }

    private void tableFiltered(ObservableList<T> newData) {

        for (AbstractFilterableTableColumn columns : tableView.getFilteredColumns()) {
            if (columns instanceof FilterableIntegerTableColumn) {
                filterIntColumn(newData, (FilterableIntegerTableColumn) columns);
            } else if (columns instanceof FilterableStringTableColumn) {
                filterStringColumn(newData, (FilterableStringTableColumn) columns);
            } else if (columns instanceof FilterableLongTableColumn) {
                filterDateColumn(newData, (FilterableLongTableColumn) columns);
            } else if (columns instanceof FilterableStringTableColumn) {
                filterBoolColumn(newData, (FilterableBooleanTableColumn) columns);
            }
        }
        tableView.getItems().setAll(newData);
    }

    private void filterIntColumn(ObservableList<T> newData, FilterableIntegerTableColumn<T, Integer> column) {
        final List<T> remove = new ArrayList<>();
        for (NumberOperator<Integer> filter : column.getFilters()) {
            if (filter.getValue() == null) {
                continue;
            }
            for (T item : newData) {
                int itemV;
                try {
                    itemV = Integer.parseInt(column.getCellData(item) + "");
                } catch (Exception e) {
                    remove.add(item);
                    continue;
                }
                if (filter.getType() == NumberOperator.Type.EQUALS) {
                    if (!Objects.equals(itemV, filter.getValue())) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.NOTEQUALS) {
                    if (Objects.equals(itemV, filter.getValue())) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.GREATERTHAN) {
                    if (itemV <= filter.getValue()) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.GREATERTHANEQUALS) {
                    if (itemV < filter.getValue()) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.LESSTHAN) {
                    if (itemV >= filter.getValue()) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.LESSTHANEQUALS) {
                    if (itemV > filter.getValue()) {
                        remove.add(item);
                    }
                }
            }
            newData.removeAll(remove);
        }
    }

    private void filterStringColumn(ObservableList<T> newData, FilterableStringTableColumn<T, Integer> column) {
        final List<T> remove = new ArrayList<>();
        for (StringOperator filter : column.getFilters()) {
            for (T item : newData) {
                String itemV = column.getCellData(item) + "";

                if (filter.getType() == StringOperator.Type.EQUALS) {
                    if (!itemV.equals(filter.getValue())) {
                        remove.add(item);
                    }
                } else if (filter.getType() == StringOperator.Type.NOTEQUALS) {
                    if (itemV.equals(filter.getValue())) {
                        remove.add(item);
                    }
                } else if (filter.getType() == StringOperator.Type.CONTAINS) {
                    if (!itemV.contains(filter.getValue())) {
                        remove.add(item);
                    }
                } else if (filter.getType() == StringOperator.Type.STARTSWITH) {
                    if (!itemV.startsWith(filter.getValue())) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.ENDSWITH) {
                    if (!itemV.endsWith(filter.getValue())) {
                        remove.add(item);
                    }
                }
            }
        }
        newData.removeAll(remove);
    }

    private void filterDateColumn(ObservableList<T> newData, FilterableLongTableColumn<T, Integer> column) {
        final List<T> remove = new ArrayList<>();
        for (NumberOperator<Long> filter : column.getFilters()) {
            try {
                filter.getValue().toString();
            } catch (Exception e) {
                continue;
            }
            for (T item : newData) {
                long itemV;
                try {
                    itemV = Long.parseLong(column.getCellData(item) + "");
                } catch (Exception e) {
                    remove.add(item);
                    continue;
                }
                long filterV = Long.parseLong(filter.getValue().toString().replace("/", ""));
                if (filter.getType() == NumberOperator.Type.EQUALS) {
                    if (!Objects.equals(itemV, filterV)) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.NOTEQUALS) {
                    if (Objects.equals(itemV, filterV)) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.GREATERTHAN) {
                    if (itemV <= filterV) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.GREATERTHANEQUALS) {
                    if (itemV < filterV) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.LESSTHAN) {
                    if (itemV >= filterV) {
                        remove.add(item);
                    }
                } else if (filter.getType() == NumberOperator.Type.LESSTHANEQUALS) {
                    if (itemV > filterV) {
                        remove.add(item);
                    }
                }
            }
        }
        newData.removeAll(remove);
    }

    private void filterBoolColumn(ObservableList<T> newData, FilterableBooleanTableColumn<T, Integer> column) {
        final List<T> remove = new ArrayList<>();
        for (BooleanOperator filter : column.getFilters()) {
            for (T item : newData) {

                boolean itemV;
                try {
                    itemV = Boolean.parseBoolean(column.getCellData(item) + "");
                } catch (Exception e) {
                    remove.add(item);
                    continue;
                }

                if (filter.getType() == BooleanOperator.Type.TRUE) {
                    if (!itemV) {
                        remove.add(item);
                    }
                } else if (filter.getType() == BooleanOperator.Type.FALSE) {
                    if (itemV) {
                        remove.add(item);
                    }
                }
            }
        }
        newData.removeAll(remove);
    }

    public void under_table_update(Integer id) {
    }

}
