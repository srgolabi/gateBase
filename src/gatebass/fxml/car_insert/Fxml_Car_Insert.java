/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.car_insert;

import static gatebass.GateBass.databaseHelper;
import static gatebass.GateBass.server;
import static gatebass.GateBass.work_list;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.CarHistory;
import gatebass.dataBase.tables.Companies;
import gatebass.dataBase.tables.History;
import gatebass.dataBase.tables.IndividualFile;
import gatebass.dataBase.tables.IndividualReplica;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.Manage;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.WorkHistory;
import gatebass.fxml.individual_insert.Fxml_Individual_Insert;
import static gatebass.fxml.main.Fxml_Main.show_print_preView;
import gatebass.myControl.MyButtonFont;
import gatebass.myControl.tableView.FileColumnTable;
import gatebass.utils.ErrorCheck;
import gatebass.utils.MenuTableInit;
import gatebass.utils.MyTime;
import gatebass.utils.ParentControl;
import gatebass.utils.PersianCalendar;
import gatebass.utils.TextFiledLimited;
import gatebass.utils.UtilsStage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.TAB;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Hamid Reza daneshjoo
 */
public class Fxml_Car_Insert extends ParentControl {

    @FXML
    private MyButtonFont print_view;
    @FXML
    private Label card_number;
    @FXML
    private TabPane tabPane;

    @FXML
    private HBox button_page;
    @FXML
    private Button insert_individual;
    @FXML
    private Button new_individual;
    @FXML
    private MyButtonFont search;

    @FXML
    private TextField shasi_number;
    @FXML
    private TextField car_name;
    @FXML
    private TextField model;
    @FXML
    private TableView<IndividualFile> fileSelected;
    @FXML
    private TableColumn<IndividualFile, IndividualFile> fileColumnButton;
    @FXML
    private MyButtonFont add_files;
    @FXML
    private TextArea comments;

    @FXML
    private HBox work_page;
    @FXML
    private Button work_back;
    @FXML
    private Button work_submit;
    @FXML
    private HBox work_button_page;
    @FXML
    private MyButtonFont work_insert;
    @FXML
    private MyButtonFont work_view;
    @FXML
    private MyButtonFont work_edit;
    @FXML
    private MyButtonFont add_to_print;
    @FXML
    private TableView<CarHistory> work_table;
    @FXML
    private TextField pellak;
    @FXML
    private TextField color;
    @FXML
    private TextField driver_info;
    @FXML
    private MyButtonFont driver_info_button;
    @FXML
    private TextField comppany;
    @FXML
    private TextField issued_day;
    @FXML
    private TextField issued_month;
    @FXML
    private TextField issued_year;
    @FXML
    private TextField expire_day;
    @FXML
    private TextField expire_month;
    @FXML
    private TextField expire_year;
    @FXML
    private TextField certificate_day;
    @FXML
    private TextField certificate_month;
    @FXML
    private TextField certificate_year;
    @FXML
    private TextField bimeh_day;
    @FXML
    private TextField bimeh_month;
    @FXML
    private TextField bimeh_year;
    @FXML
    private TextField void_day;
    @FXML
    private TextField void_month;
    @FXML
    private TextField void_year;
    @FXML
    private TableView<Companies> companiesMenu;

    @FXML
    private HBox replica_page;
    @FXML
    private TextField replica_day;
    @FXML
    private TextField replica_month;
    @FXML
    private TextField replica_year;
    @FXML
    private TextField replica_mablagh;
    @FXML
    private TextField replica_sharh;
    @FXML
    private MyButtonFont replica_insert;
    @FXML
    private MyButtonFont replica_edit;
    @FXML
    private TableView<IndividualReplica> replica_Table;

    @FXML
    private HBox searchPane;
    @FXML
    private HBox search_page_buttons;
    @FXML
    private VBox search_page_controls;
    @FXML
    private TextField shasi_number_search;
    @FXML
    private TextField car_name_search;
    @FXML
    private TextField color_search;
    @FXML
    private TextField model_search;
    @FXML
    private TextField pellak_search;
    @FXML
    private TextArea comments_search;
    @FXML
    public MyButtonFont search_Next;
    @FXML
    public MyButtonFont search_Back;
    @FXML
    public MyButtonFont search_first;
    @FXML
    public MyButtonFont search_end;
    @FXML
    private Button search_close;
    @FXML
    private Button search_submit;
    @FXML
    private Button search_close_stage;
    @FXML
    private Label search_Resault;

    @FXML
    private VBox dark_background;
    @FXML
    private VBox driver_page;
    @FXML
    private TableView<WorkHistory> driver_history_table;
    @FXML
    private Button driver_back;
    @FXML
    private Button driver_submit;

    private MyTime replica_date;
    private MyTime certificate_date;
    private MyTime card_issued_date;
    private MyTime card_expiration_date;
    private MyTime card_void_date;
    private MyTime bimeh_date;

    public Cars car;
    private IndividualReplica individualReplica_iw;
    private CarHistory carsHistory_iw;

    private List<IndividualFile> deleteFiles;
    public boolean editMode = false;
    public BooleanProperty history_editMode = new SimpleBooleanProperty(false);

    public BooleanProperty editable = new SimpleBooleanProperty(false);

    private Simple_Search simple_Search;

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        thisStage.getScene().addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            switch (event.getCode()) {
                case ESCAPE:
                    if (searchPane.isVisible()) {
                        searchPane.setVisible(false);
                    } else if (driver_page.isVisible()) {
                        driver_page.setVisible(false);
                    } else if (replica_page.isVisible()) {
                        replica_page.setVisible(false);
                    } else if (work_page.isVisible()) {
                        work_page.setVisible(false);
                    }
                    break;
                case F:
                    if (event.isControlDown()) {
                        searchPane.setVisible(true);
                    }
                    break;
                case N:
                    if (!event.isControlDown()) {
                        break;
                    }
                case F7:
                    if (work_page.isVisible()) {
                        clear_work();
                    } else if (searchPane.isVisible()) {
                        simple_Search.clear();
                    } else {
                        clear();
                    }
                    break;
                case S:
                    if (!event.isControlDown()) {
                        break;
                    }
                case F9:
                case F8:
                    if (searchPane.isVisible()) {
                        search_submit.getOnAction().handle(null);
                        if (event.getCode().equals(KeyCode.F9) || (event.getCode().equals(KeyCode.S) && event.isShiftDown())) {
                            simple_Search.clear();
                        }
                    } else if (driver_page.isVisible()) {
                        if (!event.getCode().equals(KeyCode.F9)) {
                            driver_submit.getOnAction().handle(null);
                        }
                        break;
                    } else if (work_page.isVisible()) {
                        work_submit.getOnAction().handle(null);
                        if (event.getCode().equals(KeyCode.F9) || (event.getCode().equals(KeyCode.S) && event.isShiftDown())) {
                            clear_work();
                        }
                    } else {
                        insert_individual.getOnAction().handle(null);
                        if (event.getCode().equals(KeyCode.F9) || (event.getCode().equals(KeyCode.S) && event.isShiftDown())) {
                            clear();
                        }
                    }
                    break;
                case LEFT:
                    if (!event.isControlDown()) {
                        break;
                    }
                case PAGE_DOWN:
                    if (!search_Back.isDisable() && search_page_controls.isVisible()) {
                        search_Back.getOnAction().handle(null);
                    }
                    break;
                case RIGHT:
                    if (!event.isControlDown()) {
                        break;
                    }
                case PAGE_UP:
                    if (!search_Next.isDisable() && search_page_controls.isVisible()) {
                        search_Next.getOnAction().handle(null);
                    }
                    break;
                case END:
                    if (!search_end.isDisable() && search_page_controls.isVisible()) {
                        search_end.getOnAction().handle(null);
                    }
                    break;
                case HOME:
                    if (!search_first.isDisable() && search_page_controls.isVisible()) {
                        search_first.getOnAction().handle(null);
                    }
                    break;
            }
        });

        print_view.init("print", 15);
        print_view.visibleProperty().bind(searchPane.visibleProperty().not().and(work_page.visibleProperty().not()));

        print_view.setOnAction((ActionEvent event) -> {
            if (work_list.isEmpty()) {
                UtilsStage.showMsg("موردی جهت چاپ وجود ندارد", "هشدار", false, thisStage);
            } else {
                show_print_preView(work_list);
            }
        });

        file_table_init();

        car = new Cars();

        button_page.visibleProperty().bind(work_button_page.visibleProperty().not().and(search_page_buttons.visibleProperty().not()));

        TextFiledLimited.setEnterFocuse(
                shasi_number, car_name, model, comments, add_files
        );

        TextFiledLimited.set_editable_textField(editable,
                shasi_number, car_name, model, comments, pellak, color, comppany
        );

        TextFiledLimited.set_Number_Limit(shasi_number);
        TextFiledLimited.set_Number_Length_Limit(model, 4);

        setUp_Work_Page();
        setUp_Replica_Page();
        setUp_Search_Page();
        setUp_driver_Page();

        tabPane.visibleProperty().bind(searchPane.visibleProperty().not().and(work_page.visibleProperty().not()));

        add_files.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isShiftDown() && event.getCode() == TAB) {
                comments.requestFocus();
                event.consume();
                return;
            }
            if (event.isAltDown() && event.getCode() == TAB) {
                event.consume();
            } else if (event.getCode() == ENTER || event.getCode() == TAB) {
                tabPane.getSelectionModel().select(1);
                work_insert.requestFocus();
                event.consume();
            }
        });
        add_to_print.setOnAction((ActionEvent event) -> {
            
            WorkHistory wh = new WorkHistory();
            wh.setCar_history_id(work_table.getSelectionModel().getSelectedItem());
            wh.set_TYPE(WorkHistory.CAR);
            work_list.add(wh);
            System.out.println("gatebass.f == " + work_list.size());
        });
        work_insert.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isShiftDown() && event.getCode() == TAB) {
                tabPane.getSelectionModel().select(0);
                add_files.requestFocus();
                event.consume();
                return;
            }
            if (event.isAltDown() && event.getCode() == TAB) {
                event.consume();
            } else if ((event.getCode() == ENTER || event.getCode() == TAB) && !work_edit.isDisable()) {
                work_edit.requestFocus();
                event.consume();
            }
        });

        shasi_number.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (shasi_number.getText().isEmpty() || !thisStage.isFocused() || newValue) {
                return;
            }
            if (!editMode) {
                Cars temp = databaseHelper.carDao.getFirst("shasi_number", shasi_number.getText());
                if (temp != null) {
                    if (UtilsStage.showMsg("خودرویی با این شماره شاسی قبلا ثبت شده است. اکنون اطلاعات آن بارگذاری شود؟", "هشدار", true, thisStage)) {
                        car = temp;
                        loadCars();
                    } else {
                        shasi_number.requestFocus();
                    }
                }
            }
        });

        insert_individual.setOnAction((ActionEvent event) -> {
            try {
                if (insert()) {
                    UtilsStage.showMsg(editMode ? "تغییرات با موفقیت ثبت گردید." : "اطلاعات با موفقیت در سیستم ثبت شد.", "هشدار", false, thisStage);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fxml_Individual_Insert.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        new_individual.setOnAction((ActionEvent event) -> {
            clear();
        });

        editable.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            work_insert.setDisable(!Permission.isAcces(Permission.CAR_WORK_INSERT) || !newValue);
        });

        Platform.runLater(shasi_number::requestFocus);
    }

    private void file_table_init() {
        add_files.init("plus", 16);
        add_files.disableProperty().bind(editable.not());
        add_files.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            List<File> fileList = fileChooser.showOpenMultipleDialog(thisStage);
            if (fileList != null) {
                for (File ff : fileList) {
                    fileSelected.getItems().add(new IndividualFile(ff.getAbsolutePath()));
                }
            }
        });
        fileSelected.setOnDragOver((DragEvent event1) -> {
            Dragboard db = event1.getDragboard();
            if (db.hasFiles() && editable.get()) {
                event1.acceptTransferModes(TransferMode.LINK);
            } else {
                event1.consume();
            }
        });
        fileSelected.setOnDragDropped((DragEvent event1) -> {
            Dragboard db = event1.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                for (File ff : db.getFiles()) {
                    fileSelected.getItems().add(new IndividualFile(ff.getAbsolutePath()));
                }
            }
            event1.setDropCompleted(success);
            event1.consume();
        });
        deleteFiles = new ArrayList<>();
        FileColumnTable removeButtonCellFactory = new FileColumnTable(true);
        fileColumnButton.setCellFactory(removeButtonCellFactory);
        removeButtonCellFactory.setOnDelete((IndividualFile s) -> {
            if (s.getId() != null) {
                deleteFiles.add(s);
            }
        });
    }

    public void loadCars() {

        editMode = true;
        shasi_number.setText(car.getShasi_number());
        car_name.setText(car.getCar_name());
        model.setText(car.getModel());
        comments.setText(car.getComments());
        card_number.setText(car.getCard_id());

        fileSelected.getItems().clear();
        List<IndividualFile> fileList = databaseHelper.individualFileDao.getAll("car_id", car);
        if (fileList != null) {
            fileSelected.getItems().addAll(fileList);
        }

        work_table.getItems().clear();
        List<CarHistory> worksList = databaseHelper.carsHistoryDao.getAll("car_id", car);
        if (worksList != null) {
            work_table.getItems().addAll(worksList);
        }

        replica_Table.getItems().clear();
        List<IndividualReplica> replicaList = databaseHelper.individualReplicaDao.getAll("car_id", car);
        if (replicaList != null) {
            replica_Table.getItems().addAll(replicaList);
        }
    }

    public void clear() {
        car = new Cars();
        editMode = false;
        TextFiledLimited.set_empty_textField(shasi_number, car_name, model, comments);
        card_number.setText("----");
        fileSelected.getItems().clear();
        work_table.getItems().clear();
        replica_Table.getItems().clear();
        clear_work();
    }

    private boolean insert() throws SQLException {
        ErrorCheck errorCheck = new ErrorCheck("شماره شاسی");
        if (errorCheck.checked(false, "خطا", thisStage, shasi_number) != -1) {
            return false;
        }
        if (!shasi_number.getText().equals(car.getShasi_number())) {
            Cars temp = databaseHelper.carDao.getFirst("shasi_number", shasi_number.getText());
            if (temp != null) {
                UtilsStage.showMsg("خودرویی با این شماره شاسی قبلا ثبت شده.", "هشدار", true, thisStage);
                shasi_number.requestFocus();
            }
        }

        if (car.getFilesPatch() == null) {
            String split = FileSystems.getDefault().getSeparator();
            PersianCalendar persianCalendar = new PersianCalendar();
            car.setFilesPatch("data" + split + persianCalendar.year() + split + persianCalendar.get(Calendar.WEEK_OF_YEAR) + split + shasi_number.getText() + "_c" + split);
        }
        car.setShasi_number(shasi_number.getText());
        car.setCar_name(car_name.getText());
        car.setModel(model.getText());
        car.setComments(comments.getText());

        if (!editMode) {
            Manage manage = databaseHelper.manageDao.getFirst("key", "card_id_count_car");
            car.setCard_id(manage.getValue());
            manage.setValue((Integer.parseInt(manage.getValue()) + 1) + "");
            databaseHelper.manageDao.createOrUpdate(manage);
        }

        databaseHelper.carDao.createOrUpdate(car);

        for (IndividualFile f : deleteFiles) {
            new File(f.getAddress()).delete();
        }
        databaseHelper.individualFileDao.delete(deleteFiles);
        deleteFiles = new ArrayList<>();
        int j = 0;
        String pre_address = server + car.getFilesPatch() + car.getShasi_number();
        for (IndividualFile f : fileSelected.getItems()) {
            String type = getFileExtension(f.getAddress());
            if (new File(car.getFilesPatch()).listFiles() != null) {
                for (File fi : new File(car.getFilesPatch()).listFiles()) {
                    if (fi.getAbsolutePath().contains("-pic.")) {
                        continue;
                    }
                    if (new File(pre_address + "-(" + j + ")" + type).exists()) {
                        j++;
                    } else {
                        break;
                    }
                }
            }
            String fileName2 = car.getShasi_number() + "-(" + j + ")";
            if (f.getId() == null) {
                copyImageFile(f.getAddress(), server + car.getFilesPatch(), fileName2);
                f.setAddress(car.getFilesPatch() + fileName2 + getFileExtension(f.getAddress()));
            } else {
                String ttemp = car.getFilesPatch() + car.getShasi_number() + "-(" + j + ")" + type;
                new File(f.getAddress()).renameTo(new File(server + ttemp));
                f.setAddress(ttemp);
            }
        }
        fileSelected.refresh();
        databaseHelper.individualFileDao.insertList(fileSelected.getItems());

        for (CarHistory wh : work_table.getItems()) {
            wh.setCar_id(car);
            databaseHelper.carsHistoryDao.createOrUpdate(wh);
            if (wh.getWorkHistory_id() != null) {
                WorkHistory workHistory_temp = wh.getWorkHistory_id();
                workHistory_temp.setCar_history_id(wh);
                databaseHelper.workHistoryDao.createOrUpdate(workHistory_temp);
            }
        }
        editMode = true;
        return true;
    }

    public void copyImageFile(String fileaddress, String dir, String name) {
        File srcFile = new File(fileaddress);
        File destFile = new File(dir + name + getFileExtension(fileaddress));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException ex) {
            System.out.println("MY ERROR :: " + ex.getMessage());
            Logger.getLogger(Fxml_Individual_Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getFileExtension(String fileaddress) {
        try {
            return fileaddress.substring(fileaddress.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

    private void setUp_Work_Page() {
        driver_info_button.init("search", 15);
        work_insert.init("plus", 15);
        work_edit.init("pencil", 15);
        work_view.init("eye", 15);
        add_to_print.init("newspaper", "عبور موقت", 15);

        TextFiledLimited.set_Number_Length_Limit(driver_info, 10);

        add_to_print.disableProperty().bind(work_edit.disableProperty());
        work_view.disableProperty().bind(work_table.getSelectionModel().selectedIndexProperty().isEqualTo(-1));
        work_insert.setDisable(!Permission.isAcces(Permission.CAR_WORK_INSERT) || !editable.get());
        work_submit.visibleProperty().bind(history_editMode);
        driver_info.setEditable(editable.get());
        System.out.println("driver_info.setEditable = " + driver_info.isEditable());
        driver_info_button.visibleProperty().bind(editable);
        work_button_page.visibleProperty().bind(work_page.visibleProperty());

        work_edit.disableProperty().bind(work_view.disableProperty().or(editable.not()));

        card_issued_date = new MyTime(issued_year, issued_month, issued_day);
        card_void_date = new MyTime(void_year, void_month, void_day);
        bimeh_date = new MyTime(bimeh_year, bimeh_month, bimeh_day);
        certificate_date = new MyTime(certificate_year, certificate_month, certificate_day);
        card_expiration_date = new MyTime(expire_year, expire_month, expire_day);

        TextFiledLimited.setEnterFocuse(
                comppany, bimeh_day, bimeh_month, bimeh_year, certificate_day, certificate_month,
                certificate_year, issued_day, issued_month, issued_year, expire_day, expire_month,
                expire_year, void_day, void_month, void_year, work_submit, work_back
        );

        work_table.setRowFactory((TableView<CarHistory> tableView) -> {
            final TableRow<CarHistory> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem gateMenuItem = new MenuItem("گیت باس");
            final MenuItem editMenuItem = new MenuItem("تغییر");
            final MenuItem printMenuItem = new MenuItem("پرینت");
            gateMenuItem.setOnAction((ActionEvent event) -> {
            });
            contextMenu.getItems().addAll(gateMenuItem, editMenuItem, printMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });

        String query = "SELECT * FROM companies WHERE active = 1 AND is_deleted = 0 ORDER BY company_fa ASC";
        MenuTableInit.companiesInit(query, comppany, companiesMenu);

        driver_info_button.setOnAction((ActionEvent event) -> {
            if (driver_info.isEditable()) {
                if (driver_info.getText().isEmpty()) {
                    return;
                }
                Individuals idl = databaseHelper.individualsDao.getFirst("national_id", driver_info.getText());
                if (idl == null) {
                    UtilsStage.showMsg("فردی با این شماره ملی ثبت نگردیده است.", "هشدار", false, thisStage);
                    return;
                }
                driver_history_table.getItems().setAll(databaseHelper.workHistoryDao.getAll("individuals_id", idl));
                driver_page.setVisible(true);
            } else {
                driver_info.setEditable(true);
                driver_info.setText("");
                carsHistory_iw.setWorkHistory_id(null);
                driver_info_button.changeText("search");
            }
        });

        work_insert.setOnAction((ActionEvent event) -> {
            history_editMode.set(false);
            carsHistory_iw = new CarHistory();
            work_page.setVisible(true);
        });

        work_edit.setOnAction((ActionEvent event) -> {
            history_editMode.set(true);
            carsHistory_iw = work_table.getSelectionModel().getSelectedItem();
            load_work();
            work_page.setVisible(true);
        });

        work_view.setOnAction((ActionEvent event) -> {
            work_view.setUserData(editable.get());
            work_edit.getOnAction().handle(event);
            history_editMode.set(false);
            editable.set(false);
        });

        work_submit.setOnAction((ActionEvent event) -> {

            if (comppany.getText().trim().isEmpty()) {
                UtilsStage.showMsg("فیلد شرکت را باید پر کنید.", "اخطار", false, thisStage);
                comppany.requestFocus();
                return;
            }

            if (comppany.getStyle().contains("red")) {
                UtilsStage.showMsg("فیلد شرکت بدرستی پر نگردیده", "اخطار", false, thisStage);
                comppany.requestFocus();
                return;
            }

            if (!card_issued_date.isFull()) {
                UtilsStage.showMsg("تاریخ صدور کارت را باید پر کنید", "اخطار", false, thisStage);
                issued_day.requestFocus();
                return;
            }

            if (!card_expiration_date.isFull()) {
                UtilsStage.showMsg("تاریخ انقضاء کارت را باید پر کنید", "اخطار", false, thisStage);
                expire_day.requestFocus();
                return;
            }

            carsHistory_iw.setCompanies(databaseHelper.companiesDao.getFirst("company_fa", comppany.getText()));

            if (card_void_date.isFull()) {
                carsHistory_iw.setCardVoidDateId(card_void_date.writeAndGet());
            } else {
                carsHistory_iw.setCardVoidDateId(null);
            }

            if (bimeh_date.isFull()) {
                carsHistory_iw.setBimehDateId(bimeh_date.writeAndGet());
            } else {
                carsHistory_iw.setBimehDateId(null);
            }

            carsHistory_iw.setCardIssuedDateId(card_issued_date.writeAndGet());
            carsHistory_iw.setCardExpirationDateId(card_expiration_date.writeAndGet());

            if (certificate_date.isFull()) {
                carsHistory_iw.setCertificateDateId(certificate_date.writeAndGet());
            } else {
                carsHistory_iw.setCertificateDateId(null);
            }

            carsHistory_iw.setPellak(pellak.getText());
            carsHistory_iw.setColor(color.getText());

            if (history_editMode.get()) {
                work_table.refresh();
            } else {
                work_table.getItems().add(carsHistory_iw);
            }
            work_back.getOnAction().handle(event);
        });

        work_back.setOnAction((ActionEvent event) -> {
            history_editMode.set(false);
            work_page.setVisible(false);
            if (work_view.getUserData() != null) {
                editable.setValue((boolean) work_view.getUserData());
            }
            clear_work();
        });

        add_to_print.setOnAction((ActionEvent event) -> {

        });
    }

    private void clear_work() {
        MyTime.set_empty_myTime(bimeh_date, certificate_date, card_void_date, card_expiration_date, card_issued_date);
        TextFiledLimited.set_empty_textField(pellak, color, driver_info, comppany);
    }

    private void load_work() {
        pellak.setText(carsHistory_iw.getPellak());
        color.setText(carsHistory_iw.getColor());
        if (carsHistory_iw.getWorkHistory_id() != null) {
            driver_info.setText(carsHistory_iw.getWorkHistory_id().getIndividual().getFirst_name() + " " + carsHistory_iw.getWorkHistory_id().getIndividual().getLast_name());
        }
        comppany.setText(carsHistory_iw.getCompanies().getCompany_fa());
        bimeh_date.setText(carsHistory_iw.getBimehDateId());
        certificate_date.setText(carsHistory_iw.getCertificateDateId());
        card_issued_date.setText(carsHistory_iw.getCardIssuedDateId());
        card_expiration_date.setText(carsHistory_iw.getCardExpirationDateId());
        card_void_date.setText(carsHistory_iw.getCardVoidDateId());
    }

    private void setUp_Replica_Page() {
        replica_insert.init("plus", 15);
        replica_edit.init("pencil", 15);

        replica_insert.disableProperty().bind(work_insert.disableProperty());
        if (replica_insert.isDisable()) {
            replica_edit.setDisable(true);
        } else {
            replica_edit.disableProperty().bind(replica_Table.getSelectionModel().selectedIndexProperty().isEqualTo(-1).and(replica_page.visibleProperty().not()));
        }

        replica_date = new MyTime(replica_year, replica_month, replica_day);
        TextFiledLimited.setEnterFocuse(
                replica_day, replica_month, replica_year, replica_mablagh,
                replica_sharh, replica_insert, replica_edit, replica_insert
        );
        replica_insert.setOnAction((ActionEvent event) -> {
            if (replica_page.isVisible()) {
                if (replica_date.isFull()) {
                    History history = replica_date.writeAndGet();
                    if (individualReplica_iw == null) {
                        individualReplica_iw = new IndividualReplica(history, replica_mablagh.getText(), replica_sharh.getText());
                        replica_Table.getItems().add(individualReplica_iw);
                    } else {
                        individualReplica_iw.setHistory_id(history);
                        individualReplica_iw.setMablagh(replica_mablagh.getText());
                        individualReplica_iw.setDescription(replica_sharh.getText());
                        replica_Table.refresh();
                    }
                    replica_page.setVisible(false);
                } else {
                    UtilsStage.showMsg("فیلد تاریخ به درستی پر نگردیده است.", "اخطار", false, thisStage);
                }
            } else {
                individualReplica_iw = null;
                replica_page.setVisible(true);
                replica_year.requestFocus();
            }
        });
        replica_edit.setOnAction((ActionEvent event) -> {
            if (replica_page.isVisible()) {
                replica_page.setVisible(false);
            } else {
                replica_page.setVisible(true);
                individualReplica_iw = replica_Table.getSelectionModel().getSelectedItem();
                replica_date.setText(individualReplica_iw.getHistory_id());
                replica_sharh.setText(individualReplica_iw.getDescription());
                replica_mablagh.setText(individualReplica_iw.getDescription());
            }
        });
        replica_page.visibleProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            replica_insert.changeText(newValue ? "edit" : "plus");
            replica_edit.changeText(newValue ? "reply" : "pencil");
            replica_date.set_empty_textField();
            replica_mablagh.setText("");
            replica_sharh.setText("");
            if (newValue) {
                replica_day.requestFocus();
            }
        });
    }

    private void setUp_Search_Page() {
        search.init("search", 16);
        search.visibleProperty().bind(search_page_controls.visibleProperty().not());
        search_page_buttons.visibleProperty().bind(searchPane.visibleProperty());

        search.setOnAction((ActionEvent event) -> {
            searchPane.setVisible(true);
        });
        search_close.setOnAction((ActionEvent event) -> {
            search_page_controls.setVisible(false);
        });
        search_close_stage.setOnAction((ActionEvent event) -> {
            searchPane.setVisible(false);
        });

        TextFiledLimited.setEnterFocuse(
                shasi_number_search, car_name_search, color_search, model_search, pellak_search,
                comments_search, search_submit, search_close_stage);

        TextFiledLimited.set_Number_Limit(shasi_number_search);
        TextFiledLimited.set_Number_Length_Limit(model_search, 4);
        simple_Search = new Simple_Search(thisStage, shasi_number_search, car_name_search, color_search, model_search, pellak_search, comments_search, search_Resault);
        simple_Search.setControls(search_Next, search_Back, search_first, search_end, search_submit, searchPane);

        simple_Search.setOnAction((Cars l) -> {
            search_page_controls.setVisible(true);
            if (l != null) {
                car = l;
                loadCars();
            } else {
                clear();
            }
        });

    }

    private void setUp_driver_Page() {
        dark_background.visibleProperty().bind(driver_page.visibleProperty());
        driver_submit.disableProperty().bind(driver_history_table.getSelectionModel().selectedIndexProperty().isEqualTo(-1));

        driver_back.setOnAction((ActionEvent event) -> {
            driver_page.setVisible(false);
        });

        driver_submit.setOnAction((ActionEvent event) -> {
            carsHistory_iw.setWorkHistory_id(driver_history_table.getSelectionModel().getSelectedItem());
            driver_page.setVisible(false);
            driver_info.setEditable(false);
            String str = carsHistory_iw.getWorkHistory_id().getIndividual().getFirst_name() + " " + carsHistory_iw.getWorkHistory_id().getIndividual().getLast_name();
            driver_info.setText(str);
            driver_info_button.changeText("cancel");
        });
    }
}
