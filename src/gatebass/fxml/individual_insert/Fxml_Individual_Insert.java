package gatebass.fxml.individual_insert;

import static gatebass.GateBass.databaseHelper;
import static gatebass.GateBass.server;
import gatebass.dataBase.tables.CarHistory;
import gatebass.dataBase.tables.Cars;
import gatebass.dataBase.tables.Companies;
import gatebass.dataBase.tables.History;
import gatebass.dataBase.tables.IndividualFile;
import gatebass.dataBase.tables.IndividualReplica;
import gatebass.dataBase.tables.IndividualWarning;
import gatebass.dataBase.tables.Individuals;
import gatebass.dataBase.tables.Manage;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.WorkHistory;
import static gatebass.fxml.main.Fxml_Main.show_print_preView;
import gatebass.fxml.individual_search.Fxml_Individual_Search;
import static gatebass.fxml.main.Fxml_Main.work_list;
import gatebass.myControl.MyButtonFont;
import gatebass.myControl.tableView.FileColumnTable;
import gatebass.utils.ErrorCheck;
import gatebass.utils.MenuTableInit;
import gatebass.utils.MyTime;
import gatebass.utils.ParentControl;
import gatebass.utils.PersianCalendar;
import gatebass.utils.TextFiledLimited;
import gatebass.utils.UtilsMsg;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_Individual_Insert extends ParentControl {

    @FXML
    private Label print_count;
    @FXML
    private MyButtonFont print_view;

    @FXML
    private TextField job_title;
    @FXML
    private RadioButton temporary_gate;
    @FXML
    private RadioButton contractor_gate;
    @FXML
    private RadioButton employer_gate;
    @FXML
    private TextField comppany;
    @FXML
    private TextField job_title_ENG;
    @FXML
    private TextField job_phone_number;
    @FXML
    private TextField employment_day;
    @FXML
    private TextField employment_month;
    @FXML
    private TextField employment_year;
    @FXML
    private TextField card_issued_day;
    @FXML
    private TextField card_issued_month;
    @FXML
    private TextField card_issued_year;
    @FXML
    private TextField card_expiration_day;
    @FXML
    private TextField card_expiration_month;
    @FXML
    private TextField card_expiration_year;
    @FXML
    private TextField card_delivery_day;
    @FXML
    private TextField card_delivery_month;
    @FXML
    private TextField card_delivery_year;
    @FXML
    private TextArea work_comments;
    @FXML
    private TextField car_info;
    @FXML
    private MyButtonFont car_info_text_clear;
    @FXML
    private MyButtonFont car_info_button;

    @FXML
    private TableView<Companies> companiesMenu;

    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField national_id;
    @FXML
    private TextField father_first_name;
    @FXML
    private TextField first_name_ENG;
    @FXML
    private TextField last_name_ENG;
    @FXML
    private TextField id_number;
    @FXML
    private TextField serial_number;
    @FXML
    private TextField birth_day;
    @FXML
    private TextField birth_month;
    @FXML
    private TextField birth_year;
    @FXML
    private TextField issued;
    @FXML
    private TextField birth_state;
    @FXML
    private TextField series_id_1;
    @FXML
    private TextField series_id_2;
    @FXML
    private TextField field_of_study;
    @FXML
    private TextField academic_degree;
    @FXML
    private TextField mobile;
    @FXML
    private TextArea criminal_records;
    @FXML
    private TextField nationality;
    @FXML
    private TextField dependants;
    @FXML
    private TextField din;
    @FXML
    private TextField religion;

    @FXML
    private RadioButton payan_khedmat;
    @FXML
    private RadioButton bedon_kart;
    @FXML
    private RadioButton moaf;

    @FXML
    private TextField soldiery_start_day;
    @FXML
    private TextField soldiery_start_month;
    @FXML
    private TextField soldiery_start_year;
    @FXML
    private TextField soldiery_end_day;
    @FXML
    private TextField soldiery_end_month;
    @FXML
    private TextField soldiery_end_year;
    @FXML
    private TextField soldiery_id;
    @FXML
    private TextField soldiery_unit;
    @FXML
    private TextField soldiery_location;
    @FXML
    private TextField soldiery_exempt;
    @FXML
    private TextField state_address;
    @FXML
    private TextField city_address;
    @FXML
    private TextField postal_code;
    @FXML
    private TextField phone_number;
    @FXML
    private TextArea street_address;
    @FXML
    private TableView<IndividualFile> fileSelected;
    @FXML
    private TableColumn<IndividualFile, IndividualFile> fileColumnButton;
    @FXML
    private TextArea individualComments;
    @FXML
    private MyButtonFont add_files;
    @FXML
    private VBox indivisual_pic;
    @FXML
    private MyButtonFont load_indivisual_pic;
    @FXML
    private MyButtonFont remove_indivisual_pic;
    @FXML
    private CheckBox is_soe_pishine;

    @FXML
    private HBox work_page;
    @FXML
    private Button work_back;
    @FXML
    private Button work_submit;
    @FXML
    private HBox work_button_page;
    @FXML
    private MyButtonFont work_remove;
    @FXML
    private MyButtonFont replica_insert;
    @FXML
    private MyButtonFont warning_insert;
    @FXML
    private MyButtonFont work_insert;
    @FXML
    private MyButtonFont work_view;
    @FXML
    private MyButtonFont work_edit;
    @FXML
    private MyButtonFont add_to_print;
    @FXML
    private TableView<WorkHistory> work_table;

    @FXML
    private VBox warn_rep_page;
    @FXML
    private Label warn_rep_title;
    @FXML
    private Label warn_rep_company;
    @FXML
    private TextField warn_rep_day;
    @FXML
    private TextField warn_rep_month;
    @FXML
    private TextField warn_rep_year;
    @FXML
    private TextField warn_rep_sharh;
    @FXML
    private TextField warn_rep_mablegh;
    @FXML
    private VBox mablegh_page;
    @FXML
    private Button warn_rep_submit;
    @FXML
    private Button warn_rep_new;
    @FXML
    private Button warn_rep_back;

    @FXML
    private MyButtonFont warning_remove;
    @FXML
    private MyButtonFont warning_edit;
    @FXML
    private TableView<IndividualWarning> warning_Table;

    @FXML
    private MyButtonFont replica_remove;
    @FXML
    private MyButtonFont replica_edit;
    @FXML
    private TableView<IndividualReplica> replica_Table;

    @FXML
    private TabPane tabPane;

    @FXML
    private VBox vBox_soldiery;
    @FXML
    private VBox vBox_soldiery_exempt;

    @FXML
    private HBox button_page;
    @FXML
    private Button insert_individual;
    @FXML
    private Button exit;
    @FXML
    private Button new_individual;
    @FXML
    private MyButtonFont search;

    @FXML
    private Label card_number;

    @FXML
    private VBox searchPane;
    @FXML
    private HBox search_page_buttons;
    @FXML
    private VBox search_page_controls;
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
    private VBox car_page;
    @FXML
    private TableView<CarHistory> car_history_table;
    @FXML
    private Button car_back;
    @FXML
    private Button car_submit;

    private MyTime warn_rep_date;
    private MyTime employment_date;
    private MyTime card_issued_date;
    private MyTime card_expiration_date;
    private MyTime card_delivery_date;
    private MyTime birth_date;
    private MyTime soldiery_start_date;
    private MyTime soldiery_end_date;

    private IndividualWarning individualWarning_iw;
    private IndividualReplica individualReplica_iw;
    private WorkHistory workHistory_iw;

    private List<IndividualFile> deleteFiles;

    private File imageFile;

    public Individuals individual;
    public boolean editMode = false;
    public boolean editMode_work = false;

    public BooleanProperty editable = new SimpleBooleanProperty(false);

    private UtilsStage<Fxml_Individual_Search> simpleSearchController;

    public static int PAYAN_KHEDMAT = 0;
    public static int BEDONE_KART = 1;
    public static int MOAF = 2;

    private void fileSelectedInit() {
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

    private void personPicInit() {
        load_indivisual_pic.init("picture", 15);
        remove_indivisual_pic.init("trash", 15);
        load_indivisual_pic.disableProperty().bind(editable.not());
        remove_indivisual_pic.disableProperty().bind(editable.not());
        remove_indivisual_pic.setOnAction((ActionEvent event) -> {
            imageFile = null;
            indivisual_pic.setStyle("-fx-border-color:  #2e7a8c;");
        });
        load_indivisual_pic.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "Files (*.jpg;*.jpeg;*.png)", "*.jpg;*.jpeg;*.png");
            fileChooser.getExtensionFilters().add(extFilter);

            File temp = fileChooser.showOpenDialog(thisStage);
            if (temp != null) {
                imageFile = temp;
                indivisual_pic.setStyle("-fx-background-image: url('" + imageFile.toURI().toString() + "'); -fx-background-repeat: stretch; -fx-background-size: stretch; -fx-background-position: center center; -fx-border-color:  #2e7a8c;");
            }
        });
        indivisual_pic.setOnDragOver((DragEvent event1) -> {
            Dragboard db = event1.getDragboard();
            final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                    || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                    || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");
            if (db.hasFiles() && isAccepted && editable.get()) {
                event1.acceptTransferModes(TransferMode.LINK);
            } else {
                event1.consume();
            }
        });
        indivisual_pic.setOnDragDropped((DragEvent event1) -> {
            Dragboard db = event1.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                imageFile = new File(db.getFiles().get(0).getAbsolutePath());
                indivisual_pic.setStyle("-fx-background-image: url('" + imageFile.toURI().toString() + "'); -fx-background-repeat: stretch; -fx-background-size: stretch; -fx-background-position: center center; -fx-border-color:  #2e7a8c;");
            }
            event1.setDropCompleted(success);
            event1.consume();
        });

    }

    @Override
    public void setStage(Stage s) {
        super.setStage(s);
        thisStage.getScene().addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            switch (event.getCode()) {
                case P: {
                    if (searchPane.isVisible() || work_page.isVisible()) {
                        event.consume();
                        break;
                    }
                    if (event.isShiftDown() && !add_to_print.isDisable()) {
                        add_to_print.getOnAction().handle(null);
                    } else if (event.isControlDown()) {
                        print_view.getOnAction().handle(null);
                    }
                    break;
                }
                case TAB:
                    if (searchPane.isVisible() || work_page.isVisible() || dark_background.isVisible()) {
                        event.consume();
                        break;
                    }
                    if (event.isControlDown()) {
                        event.consume();
                        if (!tabPane.getSelectionModel().getSelectedItem().getTabPane().isFocused()) {
                            if (tabPane.getTabs().get((tabPane.getSelectionModel().getSelectedIndex() + 1) % 6).isDisable()) {
                                tabPane.getSelectionModel().select((tabPane.getSelectionModel().getSelectedIndex() + 2) % 6);
                            } else {
                                tabPane.getSelectionModel().select((tabPane.getSelectionModel().getSelectedIndex() + 1) % 6);
                            }
                        }
                        switch (tabPane.getSelectionModel().getSelectedIndex()) {
                            case 0:
                                national_id.requestFocus();
                                break;
                            case 1:
                                if (vBox_soldiery.isDisable()) {
                                    soldiery_exempt.requestFocus();
                                } else {
                                    soldiery_start_day.requestFocus();
                                }
                                national_id.requestFocus();
                                break;
                            case 2:
                                state_address.requestFocus();
                                break;
                        }
                        break;
                    }
                case ESCAPE:
                    if (searchPane.isVisible()) {
                        searchPane.setVisible(false);
                    } else if (work_page.isVisible()) {
                        work_page.setVisible(false);
                    } else if (warn_rep_page.isVisible()) {
                        warn_rep_page.setVisible(false);
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
                        simpleSearchController.t.clear();
                    } else if (editable.get()) {
                        new_individual.getOnAction().handle(null);
                    }
                    break;
                case S:
                    if (!event.isControlDown()) {
                        break;
                    }
                case D:
                    if (!event.isControlDown()) {
                        break;
                    }
                case F9:
                case F8:
                    if (searchPane.isVisible()) {
                        search_submit.getOnAction().handle(null);
                    } else if (car_page.isVisible()) {
                        if (!event.getCode().equals(KeyCode.F9)) {
                            car_submit.getOnAction().handle(null);
                        }
                        break;
                    } else if (work_page.isVisible()) {
                        work_submit.getOnAction().handle(null);
                        if (event.getCode().equals(KeyCode.F9) || (event.getCode().equals(KeyCode.D) && !event.isShiftDown())) {
                            clear_work();
                        }
                    } else if (editable.get()) {
                        insert_individual.getOnAction().handle(null);
                        if (event.getCode().equals(KeyCode.F9) || (event.getCode().equals(KeyCode.D) && !event.isShiftDown())) {
                            clear(false);
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
        print_count.visibleProperty().bind(print_view.visibleProperty().and(work_list.sizeProperty().greaterThan(0)));
        print_count.textProperty().bind(work_list.sizeProperty().asString());
        insert_individual.visibleProperty().bind(editable);
        new_individual.visibleProperty().bind(editable);
        exit.visibleProperty().bind(editable.not());

        print_view.setOnAction((ActionEvent event) -> {
            if (work_list.isEmpty()) {
                UtilsMsg.show("موردی جهت چاپ وجود ندارد", "هشدار", false, thisStage);
            } else {
                show_print_preView(work_list);
            }
        });

        personPicInit();
        fileSelectedInit();

        individual = new Individuals();

        button_page.visibleProperty().bind(work_button_page.visibleProperty().not().and(search_page_buttons.visibleProperty().not()));

        birth_date = new MyTime(birth_year, birth_month, birth_day);
        soldiery_start_date = new MyTime(soldiery_start_year, soldiery_start_month, soldiery_start_day);
        soldiery_end_date = new MyTime(soldiery_end_year, soldiery_end_month, soldiery_end_day);

        setUp_Warn_Rep_Page();
        setUp_Work_Page();
        setUp_car_Page();
        setUp_Replica_Page();
        setUp_Warning_Page();
        setUp_Search_Page();

        tabPane.getTabs().get(1).disableProperty().bind(bedon_kart.selectedProperty());
        tabPane.visibleProperty().bind(searchPane.visibleProperty().not().and(work_page.visibleProperty().not()));

        vBox_soldiery.disableProperty().bind(payan_khedmat.selectedProperty().not());
        vBox_soldiery_exempt.disableProperty().bind(moaf.selectedProperty().not());

        moaf.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isShiftDown() && event.getCode() == TAB) {
                bedon_kart.requestFocus();
                event.consume();
                return;
            }
            if (event.isAltDown() && event.getCode() == TAB) {
                event.consume();
            } else if (event.getCode() == ENTER || event.getCode() == TAB) {
                tabPane.getSelectionModel().select(bedon_kart.isSelected() ? 2 : 1);
                (payan_khedmat.isSelected() ? soldiery_start_day : bedon_kart.isSelected() ? state_address : soldiery_exempt).requestFocus();
                event.consume();
            }
        });

        soldiery_start_day.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isShiftDown() && event.getCode() == TAB) {
                tabPane.getSelectionModel().select(0);
                moaf.requestFocus();
                event.consume();
                return;
            }
            if (event.isAltDown() && event.getCode() == TAB) {
                event.consume();
            } else if (event.getCode() == ENTER || event.getCode() == TAB) {
                soldiery_start_month.requestFocus();
                event.consume();
            }
        });

        soldiery_exempt.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isShiftDown() && event.getCode() == TAB) {
                tabPane.getSelectionModel().select(0);
                moaf.requestFocus();
                event.consume();
                return;
            }
            if (event.isAltDown() && event.getCode() == TAB) {
                event.consume();
            } else if (event.getCode() == ENTER || event.getCode() == TAB) {
                tabPane.getSelectionModel().select(2);
                state_address.requestFocus();
                event.consume();
            }
        });

        state_address.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isShiftDown() && event.getCode() == TAB) {
                tabPane.getSelectionModel().select(bedon_kart.isSelected() ? 0 : 1);
                (payan_khedmat.isSelected() ? soldiery_location : bedon_kart.isSelected() ? moaf : soldiery_exempt).requestFocus();
                event.consume();
                return;
            }
            if (event.isAltDown() && event.getCode() == TAB) {
                event.consume();
            } else if (event.getCode() == ENTER || event.getCode() == TAB) {
                city_address.requestFocus();
                event.consume();
            }
        });

        soldiery_location.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.isShiftDown() && event.getCode() == TAB) {
                soldiery_unit.requestFocus();
                event.consume();
                return;
            }
            if (event.isAltDown() && event.getCode() == TAB) {
                event.consume();
            } else if (event.getCode() == ENTER || event.getCode() == TAB) {
                tabPane.getSelectionModel().select(2);
                state_address.requestFocus();
                event.consume();
            }
        });

//        TextFiledLimited.autoCompleteText(first_name, databaseHelper.individualsDao, "SELECT first_name FROM individuals GROUP BY first_name ORDER BY first_name deSC");
        TextFiledLimited.setEnterFocuse(
                national_id, first_name, last_name, father_first_name, first_name_ENG,
                last_name_ENG, id_number, serial_number, birth_day, birth_month, birth_year,
                issued, birth_state, series_id_1, series_id_2, field_of_study,
                academic_degree, mobile, criminal_records, nationality, dependants,
                din, religion, payan_khedmat, bedon_kart, moaf
        );

        TextFiledLimited.setEnterFocuse(
                soldiery_start_day, soldiery_start_month, soldiery_start_year,
                soldiery_end_day, soldiery_end_month, soldiery_end_year,
                soldiery_id, soldiery_unit, soldiery_location, state_address,
                city_address, street_address, postal_code, phone_number,
                individualComments, work_insert, insert_individual
        );

        TextFiledLimited.set_Number_Length_Limit_Stop(national_id, 10);
        TextFiledLimited.set_Number_Length_Limit_Stop(series_id_1, 2);
        TextFiledLimited.set_Number_Limit(
                serial_number, mobile, dependants
        );

        set_editable_myTime(
                warn_rep_date, employment_date, card_issued_date, card_expiration_date,
                card_delivery_date, birth_date, soldiery_start_date, soldiery_end_date
        );

        TextFiledLimited.set_editable_textField(editable,
                first_name, last_name, national_id, father_first_name, first_name_ENG,
                last_name_ENG, id_number, serial_number, issued, birth_state, series_id_1,
                series_id_2, field_of_study, academic_degree, mobile, criminal_records,
                nationality, din, religion, dependants, soldiery_id, soldiery_unit,
                soldiery_location, individualComments, soldiery_exempt, state_address,
                city_address, street_address, postal_code, phone_number, comppany,
                job_phone_number, job_title, job_title_ENG, work_comments
        );

        payan_khedmat.disableProperty().bind(editable.not());
        bedon_kart.disableProperty().bind(editable.not());
        moaf.disableProperty().bind(editable.not());

        national_id.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (national_id.getText().isEmpty() || !thisStage.isFocused() || newValue) {
                return;
            }
            if (national_id.getText().length() != 10) {
                national_id.requestFocus();
                UtilsMsg.show("کد ملی باید 10 رقم باشد.", "هشدار", false, thisStage);
                tabPane.getSelectionModel().select(0);
                national_id.requestFocus();
                return;
            }
            if (!editMode) {
                Individuals temp = databaseHelper.individualsDao.getFirst("national_id", national_id.getText());
                if (temp != null) {
                    if (UtilsMsg.show("فردی با این شماره ملی قبلا ثبت شده. اکنون اطلاعات آن بارگذاری شود؟", "هشدار", true, thisStage)) {
                        clear(false);
                        individual = temp;
                        loadIndividual();
                    } else {
                        national_id.requestFocus();
                    }
                }
            }
        });

        insert_individual.setOnAction((ActionEvent event) -> {
            try {
                if (insert()) {
                    UtilsMsg.show(editMode ? "تغییرات با موفقیت ثبت گردید." : "اطلاعات با موفقیت در سیستم ثبت شد.", "هشدار", false, thisStage);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fxml_Individual_Insert.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        exit.setOnAction((ActionEvent event) -> {
            thisStage.close();
        });
        new_individual.setOnAction((ActionEvent event) -> {
            if (clear(true)) {
                tabPane.getSelectionModel().select(0);
                national_id.requestFocus();
            }
        });

        editable.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            work_insert.setDisable(!Permission.isAcces(Permission.INDIVIDUAL_WORK_INSERT) || !newValue);
        });

        Platform.runLater(national_id::requestFocus);
    }

    private void set_editable_myTime(MyTime... myTimes) {
        for (MyTime mt : myTimes) {
            mt.set_Editable(editable);
        }
    }

    private void set_editable_radio_button(RadioButton... controls) {
        for (RadioButton c : controls) {
            c.disableProperty().bind(editable.not());
            c.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue && c.isSelected()) {
                    c.setStyle("-fx-opacity : 1;");
                } else {
                    c.setStyle("");
                }
            });
        }
    }

    private void setUp_Work_Page() {

        car_info_text_clear.init("cancel", 15);
        car_info_button.init("search", 15);
        work_remove.init("minus", 15);
        work_insert.init("plus", 15);
        work_edit.init("pencil", 15);
        work_view.init("eye", 15);
        add_to_print.init("temporary_gate", "عبور موقت", 16);

        TextFiledLimited.set_Number_Limit(car_info);

        car_info.editableProperty().bind(editable.and(car_info_text_clear.visibleProperty().not()));

        car_info_button.visibleProperty().bind(editable);
        work_view.disableProperty().bind(work_table.getSelectionModel().selectedIndexProperty().isEqualTo(-1));
        work_remove.disableProperty().bind(work_view.disableProperty());
        work_insert.setDisable(!Permission.isAcces(Permission.INDIVIDUAL_WORK_INSERT) || !editable.get());
        work_edit.disableProperty().bind(work_view.disableProperty().or(editable.not()).or(work_insert.disableProperty()));
        work_submit.visibleProperty().bind(editable);

        add_to_print.disableProperty().bind(work_view.disableProperty());
        work_button_page.visibleProperty().bind(work_page.visibleProperty());

        card_issued_date = new MyTime(card_issued_year, card_issued_month, card_issued_day);
        employment_date = new MyTime(employment_year, employment_month, employment_day);
        card_delivery_date = new MyTime(card_delivery_year, card_delivery_month, card_delivery_day);
        card_expiration_date = new MyTime(card_expiration_year, card_expiration_month, card_expiration_day);

        set_editable_radio_button(temporary_gate, contractor_gate, employer_gate);

        TextFiledLimited.setEnterFocuse(
                comppany, job_phone_number, work_comments, job_title, job_title_ENG, employment_day,
                employment_month, employment_year, card_issued_day, card_issued_month,
                card_issued_year, card_expiration_day, card_expiration_month, card_expiration_year,
                card_delivery_day, card_delivery_month, card_delivery_year
        );

        work_page.visibleProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue && work_view.getUserData() != null) {
                editable.set((boolean) work_view.getUserData());
            }
        });

        work_table.setRowFactory((TableView<WorkHistory> tableView) -> {
            final TableRow<WorkHistory> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                try {
                    work_remove.setVisible(row.getItem().getId() == null && editable.get() && !work_remove.isDisable());
                } catch (Exception e) {
                    work_remove.setVisible(false);
                }
                if (event.getClickCount() >= 2) {
                    if (!work_edit.isDisabled()) {
                        work_edit.getOnAction().handle(null);
                    } else {
                        work_view.getOnAction().handle(null);
                    }
                }
            });
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem add_to_prnit_menu = new MenuItem("افزودن جهت چاپ");
            final MenuItem eidt_menu = new MenuItem("اصلاح");
            final MenuItem view_menu = new MenuItem("مشاهده");
            eidt_menu.visibleProperty().bind(work_edit.disableProperty());
            view_menu.visibleProperty().bind(work_view.disableProperty());

            add_to_prnit_menu.setOnAction((ActionEvent event) -> {
                add_to_print.getOnAction().handle(event);
            });
            eidt_menu.setOnAction((ActionEvent event) -> {
                work_edit.getOnAction().handle(event);
            });
            view_menu.setOnAction((ActionEvent event) -> {
                work_view.getOnAction().handle(event);
            });
            contextMenu.getItems().addAll(add_to_prnit_menu, eidt_menu, view_menu);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });

        String query = "SELECT * FROM companies WHERE active = 1 AND is_deleted = 0 ORDER BY company_fa ASC";
        MenuTableInit.companiesInit(query, comppany, companiesMenu);

        work_remove.setOnAction((ActionEvent event) -> {
            work_table.getItems().remove(work_table.getSelectionModel().getSelectedIndex());
        });

        work_insert.setOnAction((ActionEvent event) -> {
            clear_work();
            work_page.setVisible(true);
            workHistory_iw = new WorkHistory();
        });

        work_view.setOnAction((ActionEvent event) -> {
            work_view.setUserData(editable.get());
            editable.set(false);
            work_edit.getOnAction().handle(event);
        });

        work_edit.setOnAction((ActionEvent event) -> {
            clear_work();
            editMode_work = true;
            work_page.setVisible(true);
            workHistory_iw = work_table.getSelectionModel().getSelectedItem();
            comppany.setText(workHistory_iw.getCompanies().getCompany_fa());
            job_title.setText(workHistory_iw.getJobTitle());
            job_title_ENG.setText(workHistory_iw.getJobTitleENG());
            job_phone_number.setText(workHistory_iw.getJobPhoneNumber());
            work_comments.setText(workHistory_iw.getComments());
            employment_date.setText(workHistory_iw.getEmploymentDateId());
            card_delivery_date.setText(workHistory_iw.getCardDeliveryDate());
            card_expiration_date.setText(workHistory_iw.getCardExpirationDateId());
            card_issued_date.setText(workHistory_iw.getCardIssuedDateId());
            if (workHistory_iw.is_TEMPORARY_TYPE()) {
                temporary_gate.setSelected(true);
                temporary_gate.requestFocus();
            } else if (workHistory_iw.is_EMPLOYER_TYPE()) {
                employer_gate.setSelected(true);
                employer_gate.requestFocus();
            } else {
                contractor_gate.setSelected(true);
                contractor_gate.requestFocus();
            }
            if (workHistory_iw.getCar_history_id() != null) {
                prepare_car_submit();
            }
        });

        work_submit.setOnAction((ActionEvent event) -> {
            if (comppany.getText().trim().isEmpty()) {
                UtilsMsg.show("فیلد شرکت را باید پر کنید.", "اخطار", false, thisStage);
                comppany.requestFocus();
                return;
            }
            if (comppany.getStyle().contains("red")) {
                UtilsMsg.show("فیلد شرکت بدرستی پر نگردیده", "اخطار", false, thisStage);
                comppany.requestFocus();
                return;
            }
            if (job_title.getText().trim().isEmpty()) {
                UtilsMsg.show("فیلد عنوان شغلی را باید پر کنید.", "اخطار", false, thisStage);
                job_title.requestFocus();
                return;
            }
            if (!card_issued_date.isFull()) {
                UtilsMsg.show("تاریخ صدور کارت را باید پر کنید", "اخطار", false, thisStage);
                card_expiration_day.requestFocus();
                return;
            }
            if (!card_expiration_date.isFull()) {
                UtilsMsg.show("تاریخ انقضاء کارت را باید پر کنید", "اخطار", false, thisStage);
                card_expiration_day.requestFocus();
                return;
            }
            workHistory_iw.setGate_type(temporary_gate.isSelected() ? WorkHistory.TEMPORARY : contractor_gate.isSelected() ? WorkHistory.CONTRACTOR : WorkHistory.EMPLOYER);
            workHistory_iw.setCompanies(databaseHelper.companiesDao.getFirst("company_fa", comppany.getText()));
            workHistory_iw.setJobPhoneNumber(job_phone_number.getText());
            workHistory_iw.setJobTitle(job_title.getText());
            workHistory_iw.setJobTitleENG(job_title_ENG.getText());
            if (employment_date.isFull()) {
                workHistory_iw.setEmploymentDateId(employment_date.writeAndGet());
            } else {
                workHistory_iw.setEmploymentDateId(null);
            }
            workHistory_iw.setCardIssuedDateId(card_issued_date.writeAndGet());
            workHistory_iw.setCardExpirationDateId(card_expiration_date.writeAndGet());

            if (card_delivery_date.isFull()) {
                workHistory_iw.setCardDeliveryDate(card_delivery_date.writeAndGet());
            } else {
                workHistory_iw.setCardDeliveryDate(null);
            }
            workHistory_iw.setComments(work_comments.getText());

            if (editMode_work) {
                work_table.refresh();
            } else {
                work_table.getItems().add(workHistory_iw);
            }
            work_back.getOnAction().handle(event);
        });

        work_back.setOnAction((ActionEvent event) -> {
            work_page.setVisible(false);
            if (work_view.getUserData() != null) {
                editable.setValue((boolean) work_view.getUserData());
            }
            clear_work();
        });

        add_to_print.setOnAction((ActionEvent event) -> {
            WorkHistory wh = new WorkHistory(work_table.getSelectionModel().getSelectedItem());
            if (wh.getId() == null) {
                UtilsMsg.show("ابتدا باید تغییرات را ذخیره نمایید.", "اخطار", false, thisStage);
                return;
            }
            work_list.add(wh);
        });
    }

    private void setUp_car_Page() {
        dark_background.visibleProperty().bind(car_page.visibleProperty().or(warn_rep_page.visibleProperty()));
        car_submit.disableProperty().bind(car_history_table.getSelectionModel().selectedIndexProperty().isEqualTo(-1));

        car_info_text_clear.setOnAction((ActionEvent event) -> {
            if (editable.get()) {
                car_info.setText("");
                car_info_button.changeText("search");
                car_info_text_clear.setVisible(false);
                if (workHistory_iw.getCar_history_id() != null) {
                    workHistory_iw.car_is_edited = true;
                    workHistory_iw.setCar_history_id(null);
                } else {
                    workHistory_iw.car_is_edited = false;
                }
            }
        });

        car_info_button.setOnAction((ActionEvent event) -> {
            if (car_info.isEditable()) {
                if (car_info.getText().isEmpty()) {
                    return;
                }
                Cars cars_temp = databaseHelper.carDao.getFirst("shasi_number", car_info.getText());
                if (cars_temp == null) {
                    UtilsMsg.show("خودرویی با این شماره شاسی ثبت نگردیده است.", "هشدار", false, thisStage);
                    return;
                }
                car_history_table.getItems().setAll(databaseHelper.carsHistoryDao.getAll("car_id", cars_temp));
                car_page.setVisible(true);
            } else {
                my_action.get(workHistory_iw.getCar_history_id().getCar_id(), false);
            }
        });

        car_back.setOnAction((ActionEvent event) -> {
            car_page.setVisible(false);
        });

        car_submit.setOnAction((ActionEvent event) -> {
            if (workHistory_iw.getCar_history_id() == null) {
                workHistory_iw.car_is_edited = true;
            } else if (workHistory_iw.getCar_history_id().getId() != car_history_table.getSelectionModel().getSelectedItem().getId()) {
                workHistory_iw.car_is_edited = true;
            } else {
                workHistory_iw.car_is_edited = false;
            }
            workHistory_iw.setCar_history_id(car_history_table.getSelectionModel().getSelectedItem());
            prepare_car_submit();
        });
    }

    private void prepare_car_submit() {
        car_page.setVisible(false);
        String str = workHistory_iw.getCar_history_id().getCar_id().getCar_name();
        car_info.setText(str);
        car_info_text_clear.setVisible(true);
        car_info_button.changeText("truck");
    }

    private void setUp_Warning_Page() {

        warning_insert.init("attention", 15);
        warning_edit.init("pencil", 15);
        warning_remove.init("minus", 15);

        warning_insert.disableProperty().bind(editable.not().or(Permission.isAccesProperty(Permission.INDIVIDUAL_INSERT).not()).or(work_table.getSelectionModel().selectedIndexProperty().isEqualTo(-1)));
        warning_edit.disableProperty().bind(warning_insert.disableProperty().or(warning_Table.getSelectionModel().selectedIndexProperty().isEqualTo(-1)));

        warning_insert.setOnAction((ActionEvent event) -> {
            warn_rep_company.setText("شرکت : " + work_table.getSelectionModel().getSelectedItem().getSherkat());
            warn_rep_page.setVisible(true);
            warn_rep_title.setStyle(warn_rep_title.getStyle().replace("#004D40", "#D50000"));
            warn_rep_title.setText("اخطار");
            warn_rep_day.requestFocus();
        });

        warning_remove.setOnAction((ActionEvent event) -> {
            warning_Table.getItems().remove(warning_Table.getSelectionModel().getSelectedIndex());
        });

        warning_edit.setOnAction((ActionEvent event) -> {
            warning_insert.getOnAction().handle(null);
            individualWarning_iw = warning_Table.getSelectionModel().getSelectedItem();
            warn_rep_date.setText(individualWarning_iw.getHistory_id());
            warn_rep_sharh.setText(individualWarning_iw.getDescription());
        });

        warning_Table.setRowFactory((TableView<IndividualWarning> tableView) -> {
            final TableRow<IndividualWarning> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                try {
                    warning_remove.setVisible(row.getItem().getId() == null && editable.get() && !warning_remove.isDisable());
                } catch (Exception e) {
                    warning_remove.setVisible(false);
                }
                if (event.getClickCount() >= 2) {
                    if (!warning_edit.isDisabled()) {
                        warning_edit.getOnAction().handle(null);
                    }
                }
            });
            return row;
        });
    }

    private void setUp_Warn_Rep_Page() {

        mablegh_page.visibleProperty().bind(Bindings.equal(warn_rep_title.textProperty(), "المثنی"));

        warn_rep_date = new MyTime(warn_rep_year, warn_rep_month, warn_rep_day);

        TextFiledLimited.setEnterFocuse(
                warn_rep_day, warn_rep_month, warn_rep_year, warn_rep_sharh, warn_rep_mablegh
        );

        warn_rep_submit.setOnAction((ActionEvent event) -> {

            if (!warn_rep_date.isFull()) {
                UtilsMsg.show("فیلد تاریخ به درستی پر نگردیده است.", "اخطار", false, thisStage);
                warn_rep_day.requestFocus();
                return;
            }
            History history = warn_rep_date.writeAndGet();

            if (warn_rep_title.getText().equals("المثنی")) {
                if (individualReplica_iw == null) {
                    individualReplica_iw = new IndividualReplica(history, warn_rep_mablegh.getText(), warn_rep_sharh.getText());
                    individualReplica_iw.setWorkHistory_id(work_table.getSelectionModel().getSelectedItem());
                    replica_Table.getItems().add(individualReplica_iw);
                } else {
                    individualReplica_iw.setHistory_id(history);
                    individualReplica_iw.setMablagh(warn_rep_mablegh.getText());
                    individualReplica_iw.setDescription(warn_rep_sharh.getText());
                    individualReplica_iw.setWorkHistory_id(work_table.getSelectionModel().getSelectedItem());
                    replica_Table.refresh();
                }
            } else if (individualWarning_iw == null) {
                individualWarning_iw = new IndividualWarning(history, warn_rep_sharh.getText());
                individualWarning_iw.setWorkHistory_id(work_table.getSelectionModel().getSelectedItem());
                warning_Table.getItems().add(individualWarning_iw);
            } else {
                individualWarning_iw.setHistory_id(history);
                individualWarning_iw.setDescription(warn_rep_sharh.getText());
                individualWarning_iw.setWorkHistory_id(work_table.getSelectionModel().getSelectedItem());
                warning_Table.refresh();
            }
            warn_rep_back.getOnAction().handle(null);
        });

        warn_rep_new.setOnAction((ActionEvent event) -> {
            individualReplica_iw = null;
            individualWarning_iw = null;
            TextFiledLimited.set_empty_textField(
                    warn_rep_day, warn_rep_month, warn_rep_year, warn_rep_sharh, warn_rep_mablegh
            );
            warn_rep_day.requestFocus();
        });

        warn_rep_back.setOnAction((ActionEvent event) -> {
            warn_rep_new.getOnAction().handle(null);
            warn_rep_page.setVisible(false);
        });
    }

    private void setUp_Replica_Page() {
        replica_insert.init("paste", 15);
        replica_edit.init("pencil", 15);
        replica_remove.init("minus", 15);

        replica_insert.disableProperty().bind(editable.not().or(Permission.isAccesProperty(Permission.INDIVIDUAL_INSERT).not()).or(work_table.getSelectionModel().selectedIndexProperty().isEqualTo(-1)));
        replica_edit.disableProperty().bind(Permission.isAccesProperty(Permission.INDIVIDUAL_INSERT).not().or(replica_Table.getSelectionModel().selectedIndexProperty().isEqualTo(-1)));

        replica_insert.setOnAction((ActionEvent event) -> {
            warn_rep_company.setText("شرکت : " + work_table.getSelectionModel().getSelectedItem().getSherkat());
            warn_rep_page.setVisible(true);
            warn_rep_title.setStyle(warn_rep_title.getStyle().replace("#D50000", "#004D40"));
            warn_rep_title.setText("المثنی");
            warn_rep_day.requestFocus();
        });

        replica_remove.setOnAction((ActionEvent event) -> {
            replica_Table.getItems().remove(replica_Table.getSelectionModel().getSelectedIndex());
        });

        replica_edit.setOnAction((ActionEvent event) -> {
            replica_insert.getOnAction().handle(null);
            individualReplica_iw = replica_Table.getSelectionModel().getSelectedItem();
            warn_rep_date.setText(individualReplica_iw.getHistory_id());
            warn_rep_sharh.setText(individualReplica_iw.getDescription());
            warn_rep_mablegh.setText(individualReplica_iw.getMablagh());
        });

        replica_Table.setRowFactory((TableView<IndividualReplica> tableView) -> {
            final TableRow<IndividualReplica> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                try {
                    replica_remove.setVisible(row.getItem().getId() == null && editable.get() && !replica_remove.isDisable());
                } catch (Exception e) {
                    replica_remove.setVisible(false);
                }
                if (event.getClickCount() >= 2) {
                    if (!replica_edit.isDisabled()) {
                        replica_edit.getOnAction().handle(null);
                    }
                }
            });
            return row;
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

        simpleSearchController = new UtilsStage(Fxml_Individual_Search.class);
        simpleSearchController.t.setControls(search_Resault, search_Next, search_Back, search_first, search_end, search_submit, searchPane);
        searchPane.getChildren().add(simpleSearchController.t.root);
        simpleSearchController.t.setOnAction((Individuals l) -> {
            search_page_controls.setVisible(true);
            clear(false);
            if (l != null) {
                individual = l;
                loadIndividual();
            }
        });

    }

    public void loadIndividual() {

        editMode = true;
        work_page.setVisible(false);
        searchPane.setVisible(false);
        warn_rep_page.setVisible(false);
        first_name.setText(individual.getFirst_name());
        last_name.setText(individual.getLast_name());
        national_id.setText(individual.getNational_id());
        father_first_name.setText(individual.getFather_first_name());
        first_name_ENG.setText(individual.getFirst_name_ENG());
        last_name_ENG.setText(individual.getLast_name_ENG());
        id_number.setText(individual.getId_number());
        serial_number.setText(individual.getSerial_number());
        card_number.setText(individual.getCard_id());
        birth_date.setText(individual.getBirth_day());
        issued.setText(individual.getIssued());
        birth_state.setText(individual.getBirth_state());

        try {
            series_id_1.setText(individual.getSeries_id().substring(0, individual.getSeries_id().indexOf("/")));
            series_id_2.setText(individual.getSeries_id().substring(individual.getSeries_id().indexOf("/") + 1));
        } catch (Exception e) {
        }
        field_of_study.setText(individual.getField_of_study());
        academic_degree.setText(individual.getAcademic_degree());
        mobile.setText(individual.getMobile());
        criminal_records.setText(individual.getCriminal_records());
        nationality.setText(individual.getNationality());
        din.setText(individual.getDin());
        religion.setText(individual.getReligion());
        dependants.setText(individual.getDependants() != null ? individual.getDependants() + "" : "0");

        payan_khedmat.getToggleGroup().selectToggle(individual.getVeteran_status() == 0 ? payan_khedmat : individual.getVeteran_status() == 1 ? bedon_kart : moaf);

        soldiery_start_date.setText(individual.getSoldiery_start_date());
        soldiery_end_date.setText(individual.getSoldiery_end_date());
        soldiery_id.setText(individual.getSoldiery_id());
        soldiery_unit.setText(individual.getSoldiery_unit());
        soldiery_location.setText(individual.getSoldiery_location());
        soldiery_exempt.setText(individual.getSoldiery_exempt());
        state_address.setText(individual.getState_address());
        city_address.setText(individual.getCity_address());
        street_address.setText(individual.getStreet_address());
        postal_code.setText(individual.getPostal_code());
        phone_number.setText(individual.getPhone_number());
        individualComments.setText(individual.getComments());

        is_soe_pishine.setSelected(individual.isHave_soe_pishine());

        if (individual.getPicture_address() != null) {
            imageFile = new File(server + individual.getFilesPatch() + individual.getPicture_address());
            System.out.println("a = " + imageFile.getAbsolutePath());
            indivisual_pic.setStyle("-fx-background-image: url('" + imageFile.toURI().toString() + "'); -fx-background-repeat: stretch; -fx-background-size: stretch; -fx-background-position: center center; -fx-border-color:  #2e7a8c;");
        }

        fileSelected.getItems().clear();
        List<IndividualFile> fileList = databaseHelper.individualFileDao.getAll("individual_id", individual);
        if (fileList != null) {
            fileSelected.getItems().addAll(fileList);
        }

        work_table.getItems().clear();
        List<WorkHistory> worksList = databaseHelper.workHistoryDao.getAll("individuals_id", individual);
        if (worksList != null) {
            work_table.getItems().addAll(worksList);
        }

        warning_Table.getItems().setAll(databaseHelper.individualWarningDao.rawResults(
                "SELECT individualWarning.* from individualWarning\n"
                + "LEFT OUTER JOIN workhistory workhistory_j1 ON workhistory_j1.id = individualWarning.workHistory_id\n"
                + "where workhistory_j1.individuals_id = " + individual.getId()
        ));

        replica_Table.getItems().setAll(databaseHelper.individualReplicaDao.rawResults(
                "SELECT individualReplica.* from individualReplica\n"
                + "LEFT OUTER JOIN workhistory workhistory_j1 ON workhistory_j1.id = individualReplica.workHistory_id\n"
                + "where workhistory_j1.individuals_id = " + individual.getId()
        ));
    }

    public boolean clear(boolean show_msg) {

        boolean b = TextFiledLimited.clear_value(show_msg,
                national_id, first_name, last_name, father_first_name, first_name_ENG,
                last_name_ENG, id_number, serial_number, issued, birth_state, series_id_1,
                series_id_2, field_of_study, academic_degree, mobile, criminal_records,
                soldiery_id, soldiery_unit, soldiery_location, soldiery_exempt, individualComments,
                state_address, city_address, street_address, postal_code, phone_number
        );
        if (!b) {
            return false;
        }

        individual = new Individuals();
        editMode = false;

        MyTime.set_empty_myTime(
                birth_date, soldiery_start_date, soldiery_end_date
        );
        imageFile = null;
        card_number.setText("----");
        nationality.setText("ایران");
        dependants.setText("0");
        din.setText("اسلام");
        religion.setText("شیعه");
        payan_khedmat.setSelected(true);
        indivisual_pic.setStyle("-fx-border-color:  #2e7a8c;");
        is_soe_pishine.setSelected(false);
        fileSelected.getItems().clear();
        work_table.getItems().clear();
        warning_Table.getItems().clear();
        replica_Table.getItems().clear();
        clear_work();
        return true;
    }

    private void clear_work() {
        editMode_work = false;
        temporary_gate.setSelected(true);
        car_info_text_clear.setVisible(false);
        car_info_button.changeText("search");
        TextFiledLimited.set_empty_textField(comppany, job_phone_number, job_title, job_title_ENG, work_comments, car_info);
        MyTime.set_empty_myTime(employment_date, card_delivery_date, card_expiration_date, card_issued_date);
    }

    private boolean insert() throws SQLException {
        ErrorCheck errorCheck = new ErrorCheck("کد ملی");
        if (errorCheck.checked(false, "خطا", thisStage, national_id) != -1) {
            return false;
        }
        if (!national_id.getText().equals(individual.getNational_id())) {
            Individuals temp = databaseHelper.individualsDao.getFirst("national_id", national_id.getText());
            if (temp != null) {
                UtilsMsg.show("فردی با این شماره ملی قبلا ثبت شده.", "هشدار", true, thisStage);
                national_id.requestFocus();
            }
        }

        if (individual.getFilesPatch() == null) {
            PersianCalendar persianCalendar = new PersianCalendar();
            String split = FileSystems.getDefault().getSeparator();
            individual.setFilesPatch("data" + split + persianCalendar.year() + split + persianCalendar.get(Calendar.WEEK_OF_YEAR) + split + national_id.getText() + split);
        }

        individual.setFirst_name(first_name.getText());
        individual.setLast_name(last_name.getText());
        individual.setNational_id(national_id.getText());
        individual.setFather_first_name(father_first_name.getText());
        individual.setFirst_name_ENG(first_name_ENG.getText());
        individual.setLast_name_ENG(last_name_ENG.getText());
        individual.setId_number(id_number.getText());
        individual.setSerial_number(serial_number.getText());
        if (birth_date.isDamage("تاریخ تولد به درستی پر نشده.", thisStage)) {
            return false;
        } else if (birth_date.isFull()) {
            individual.setBirth_day(birth_date.writeAndGet());
        }

        individual.setIssued(issued.getText());
        individual.setBirth_state(birth_state.getText());
        individual.setSeries_id(series_id_1.getText() + "/" + series_id_2.getText());
        individual.setField_of_study(field_of_study.getText());
        individual.setAcademic_degree(academic_degree.getText());
        individual.setMobile(mobile.getText());
        individual.setCriminal_records(criminal_records.getText());
        individual.setNationality(nationality.getText());
        individual.setDin(din.getText());
        individual.setReligion(religion.getText());
        if (!dependants.getText().equals("")) {
            individual.setDependants(Integer.parseInt(dependants.getText()));
        }
        individual.setVeteran_status(payan_khedmat.isSelected() ? PAYAN_KHEDMAT : bedon_kart.isSelected() ? BEDONE_KART : MOAF);

        if (soldiery_start_date.isDamage("تاریخ اعزام به خدمت به درستی پر نشده.", thisStage)) {
            return false;
        } else if (soldiery_start_date.isFull()) {
            individual.setSoldiery_start_date(soldiery_start_date.writeAndGet());
        }

        if (soldiery_end_date.isDamage("تاریخ خاتمه خدمت به درستی پر نشده.", thisStage)) {
            return false;
        } else if (soldiery_end_date.isFull()) {
            individual.setSoldiery_end_date(soldiery_end_date.writeAndGet());
        }

        individual.setSoldiery_id(soldiery_id.getText());
        individual.setSoldiery_unit(soldiery_unit.getText());
        individual.setSoldiery_location(soldiery_location.getText());
        individual.setSoldiery_exempt(soldiery_exempt.getText());
        individual.setState_address(state_address.getText());
        individual.setCity_address(city_address.getText());
        individual.setStreet_address(street_address.getText());
        individual.setPostal_code(postal_code.getText());
        individual.setPhone_number(phone_number.getText());
        individual.setComments(individualComments.getText());

        if (!editMode) {
            Manage manage = databaseHelper.manageDao.getFirst("key", "card_id_count");
            individual.setCard_id(manage.getValue());
            manage.setValue((Integer.parseInt(manage.getValue()) + 1) + "");
            databaseHelper.manageDao.createOrUpdate(manage);
        }
        if (imageFile != null) {
            if (!imageFile.getAbsolutePath().endsWith(server + individual.getFilesPatch() + individual.getPicture_address())) {
                String s = "";
                try {
                    s = individual.getPicture_address().startsWith(individual.getNational_id() + "-pic") ? "-1" : "";
                } catch (Exception e) {
                }
                new File(server + individual.getFilesPatch() + individual.getPicture_address()).delete();
                individual.setPicture_address(individual.getNational_id() + s + "-pic");
                copyImageFile(imageFile.getAbsolutePath(), server + individual.getFilesPatch(), individual.getPicture_address());
                individual.setPicture_address(individual.getPicture_address() + getFileExtension(imageFile.getAbsolutePath()));
            }
        } else {
            new File(individual.getFilesPatch() + individual.getPicture_address()).delete();
            individual.setPicture_address(null);
        }
        individual.setHave_soe_pishine(is_soe_pishine.isSelected());
        databaseHelper.individualsDao.createOrUpdate(individual);

        for (IndividualFile f : deleteFiles) {
            new File(f.getAddress()).delete();
        }
        databaseHelper.individualFileDao.delete(deleteFiles);
        deleteFiles = new ArrayList<>();
        int j = 0;
        String pre_address = server + individual.getFilesPatch() + individual.getNational_id();
        for (IndividualFile f : fileSelected.getItems()) {
            String type = getFileExtension(f.getAddress());
            if (new File(individual.getFilesPatch()).listFiles() != null) {
                for (File fi : new File(individual.getFilesPatch()).listFiles()) {
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
            String fileName2 = individual.getNational_id() + "-(" + j + ")";
            if (f.getId() == null) {
                copyImageFile(f.getAddress(), server + individual.getFilesPatch(), fileName2);
                f.setAddress(individual.getFilesPatch() + fileName2 + getFileExtension(f.getAddress()));
            } else {
                String ttemp = individual.getFilesPatch() + individual.getNational_id() + "-(" + j + ")" + type;
                new File(f.getAddress()).renameTo(new File(server + ttemp));
                f.setAddress(ttemp);
            }
            f.setIndividual_id(individual);
        }
        fileSelected.refresh();
        databaseHelper.individualFileDao.insertList(fileSelected.getItems());

        for (WorkHistory wh : work_table.getItems()) {
            wh.setIndividualsId(individual);
            if (wh.car_is_edited) {
                CarHistory carHistory_temp = wh.getCar_history_id();
                carHistory_temp.setWorkHistory_id(wh.getCar_history_id() == null ? null : wh);
                databaseHelper.carsHistoryDao.createOrUpdate(carHistory_temp);
            }
        }
        databaseHelper.workHistoryDao.insertList(work_table.getItems());

        databaseHelper.individualReplicaDao.insertList(replica_Table.getItems());
        databaseHelper.individualWarningDao.insertList(warning_Table.getItems());

        editMode = true;
        card_number.setText(individual.getCard_id());
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

}
