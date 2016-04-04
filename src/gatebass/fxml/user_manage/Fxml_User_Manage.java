/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.fxml.user_manage;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.Permission;
import gatebass.dataBase.tables.UserGroup;
import gatebass.dataBase.tables.UserPermission;
import gatebass.dataBase.tables.Users;
import gatebass.myControl.CheckBoxTreeCellMy;
import gatebass.myControl.MyButtonFont;
import gatebass.utils.ErrorCheck;
import gatebass.utils.ParentControl;
import gatebass.utils.UtilsStage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author reza
 */
public class Fxml_User_Manage extends ParentControl {

    @FXML
    private VBox step_1;
    @FXML
    private VBox step_2;
    @FXML
    private VBox step_4;
    @FXML
    private VBox step_5;

    @FXML
    private MyButtonFont add;
    @FXML
    private MyButtonFont change_pass;
    @FXML
    private MyButtonFont edit;
    @FXML
    private MyButtonFont review;
    @FXML
    private TableView<Users> user_table;
    @FXML
    private TextField filter_user_table;

    @FXML
    private TextField user_name_fa;
    @FXML
    private TextField user_name_en;
    @FXML
    private TextField user_name;
    @FXML
    private TextField position;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField again_pass;
    @FXML
    private CheckBox is_deactive;
    @FXML
    private TreeView<Permission> permission_table;
    @FXML
    private Button back_2;
    @FXML
    private Button submit_2;

    @FXML
    private PasswordField pass_4;
    @FXML
    private PasswordField again_pass_4;
    @FXML
    private Button back_4;
    @FXML
    private Button submit_4;

    @FXML
    private Label user_name_fa_Label;
    @FXML
    private Label user_name_en_Label;
    @FXML
    private Label user_name_Label;
    @FXML
    private Label position_Label;
    @FXML
    private Label email_Label;
    @FXML
    private Label is_deactive_Label;
    @FXML
    private TreeView<Permission> permission_table_review;
    @FXML
    private Button back_5;

    private Users new_user;
    private List<UserGroup> userGroup;
    private List<UserGroup> deleteGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        step_1_init();
        step_2_init();
        step_4_init();
        step_5_init();
    }

    private void step_1_init() {
        change_pass.disableProperty().bind(user_table.getSelectionModel().selectedItemProperty().isNull());
        edit.disableProperty().bind(change_pass.disableProperty());
        review.disableProperty().bind(change_pass.disableProperty());

        user_table.setRowFactory((TableView<Users> param) -> {
            TableRow<Users> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() != 2) {
                    return;
                }
                review.getOnAction().handle(null);
            });
            return row;
        });

        add.init("plus", 15);
        change_pass.init("lock_open_alt", 15);
        edit.init("pencil", 15);
        review.init("eye", 15);

        add.setOnAction((ActionEvent event) -> {
            step_1.setVisible(false);
            step_2.setVisible(true);
        });

        change_pass.setOnAction((ActionEvent event) -> {
            step_1.setVisible(false);
            step_4.setVisible(true);
        });

        edit.setOnAction((ActionEvent event) -> {
            step_1.setVisible(false);
            step_2.setVisible(true);
            new_user = user_table.getSelectionModel().getSelectedItem();
            user_name_fa.setText(new_user.getName_fa());
            user_name_en.setText(new_user.getName_en());
            user_name.setText(new_user.getUsername());
            position.setText(new_user.getSemat());
            email.setText(new_user.getEmail());
            pass.setText(new_user.getPassword());
            again_pass.setText(new_user.getPassword());
            is_deactive.setSelected(!new_user.getActive());
            String query = "";

            query = "SELECT permission.id , permission.name , permission.title , permission.icon , permission.iconColor , userPermission.state defaultValue , permission.parent , permission.sort_index\n"
                    + "FROM  permission\n"
                    + "LEFT JOIN userPermission\n"
                    + "ON userPermission.user_id = '" + new_user.getId() + "' AND permission.id = userPermission.permission_id";
            permission_table_init(permission_table, query);

        });

        review.setOnAction((ActionEvent event) -> {
            Users u2 = user_table.getSelectionModel().getSelectedItem();
            if (u2 != null) {
                step_5.setVisible(true);
                step_1.setVisible(false);
                user_name_fa_Label.setText(u2.getName_fa());
                user_name_en_Label.setText(u2.getName_en());
                user_name_Label.setText(u2.getUsername());
                position_Label.setText(u2.getSemat());
                email_Label.setText(u2.getEmail());
                is_deactive_Label.setText(u2.getActive() ? "فعال" : "غیر فعال");

                String query = "SELECT permission.id , permission.name , permission.title , permission.icon , permission.iconColor , userPermission.state defaultValue , permission.parent , permission.sort_index\n"
                        + "FROM  permission\n"
                        + "LEFT JOIN userPermission\n"
                        + "ON userPermission.user_id = '" + u2.getId() + "' AND permission.id = userPermission.permission_id";
                permission_table_init(permission_table_review, query);
            }
        });
        user_table_init();
    }

    private void step_2_init() {
        new_user = new Users();
        userGroup = new ArrayList<>();
        deleteGroup = new ArrayList<>();

        permission_table.setCellFactory((TreeView<Permission> param) -> {
            CheckBoxTreeCellMy cc = new CheckBoxTreeCellMy();
            return cc;
        });

        permission_table_init(permission_table, "SELECT * FROM permission ");

        back_2.setOnAction((ActionEvent event) -> {
            step_1.setVisible(true);
            step_2.setVisible(false);
            new_user = new Users();
            userGroup = new ArrayList<>();
            user_name_fa.setText("");
            user_name_en.setText("");
            user_name.setText("");
            position.setText("");
            email.setText("");
            pass.setText("");
            again_pass.setText("");
            is_deactive.setSelected(false);
            permission_table_init(permission_table, "SELECT * FROM permission ");
        });

        submit_2.setOnAction((ActionEvent event) -> {

            ErrorCheck errorCheck = new ErrorCheck("نام", "نام کاربری", "رمز عبور", "تکرار رمز عبور");
            if (!pass.getText().equals(again_pass.getText())) {
                UtilsStage.showMsg("رمزعبور با تکرار آن مطابق نیست.", "خطا", false, thisStage);
                return;
            }
            if (errorCheck.checked(false, "خطا", thisStage, user_name_fa, user_name, pass, again_pass) != -1) {
                return;
            }
            if (databaseHelper.usersDao.getFirst("username", user_name.getText()) != null && new_user.getId() == null) {
                UtilsStage.showMsg("این نام کاربری قبلا ثبت شده است.", "خطا", false, thisStage);
                return;
            }

            new_user.setName_fa(user_name_fa.getText());
            new_user.setName_en(user_name_en.getText());
            new_user.setUsername(user_name.getText());
            new_user.setSemat(position.getText());
            new_user.setEmail(email.getText());
            new_user.setPassword(pass.getText());
            new_user.setActive(!is_deactive.isSelected());
            new_user.setType(Users.USERACTION);
            boolean b = new_user.getId() == null;
            databaseHelper.usersDao.createOrUpdate(new_user);
            List<UserPermission> permissions = new ArrayList<>();
            for (UserGroup ug : userGroup) {
                ug.setUser_id(new_user);
            }
            if (b) {
                for (TreeItem<Permission> item : permission_table.getRoot().getChildren()) {
                    permissions.add(new UserPermission(new_user, item.getValue(), ((CheckBoxTreeItem) item).isSelected()));
                    if (!item.getChildren().isEmpty()) {
                        for (TreeItem<Permission> child : item.getChildren()) {
                            permissions.add(new UserPermission(new_user, child.getValue(), ((CheckBoxTreeItem) child).isSelected()));
                        }
                    }
                }
            } else {
                for (TreeItem<Permission> item : permission_table.getRoot().getChildren()) {
                    List<UserPermission> search = databaseHelper.userPermissionDao.rawResults(
                            "SELECT * FROM userPermission WHERE user_id = " + new_user.getId() + " AND permission_id = " + item.getValue().getId()
                    );
                    UserPermission up2 = search.isEmpty()
                            ? new UserPermission(new_user, item.getValue(), ((CheckBoxTreeItem) item).isSelected())
                            : search.get(0);
                    up2.setState(((CheckBoxTreeItem) item).isSelected());
                    permissions.add(up2);
                    if (!item.getChildren().isEmpty()) {
                        for (TreeItem<Permission> child : item.getChildren()) {
                            search = databaseHelper.userPermissionDao.rawResults(
                                    "SELECT * FROM userPermission WHERE user_id = " + new_user.getId() + " AND permission_id = " + child.getValue().getId()
                            );
                            up2 = search.isEmpty()
                                    ? new UserPermission(new_user, child.getValue(), ((CheckBoxTreeItem) child).isSelected())
                                    : search.get(0);
                            up2.setState(((CheckBoxTreeItem) child).isSelected());
                            permissions.add(up2);
                        }
                    }
                }
            }

            try {
                databaseHelper.userPermissionDao.insertList(permissions);
                databaseHelper.userGroupDao.delete(deleteGroup);
                if (b) {
                    databaseHelper.userGroupDao.createOrUpdate(new UserGroup(new_user, new_user));
                }
                databaseHelper.userGroupDao.insertList(userGroup);
            } catch (SQLException ex) {
                Logger.getLogger(Fxml_User_Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
            back_2.getOnAction().handle(null);
            user_table_init();
        });
    }

    private void step_4_init() {
        submit_4.setOnAction((ActionEvent event) -> {
            ErrorCheck errorCheck = new ErrorCheck("رمز عبور", "تکرار رمز عبور");
            if (!pass_4.getText().equals(again_pass_4.getText())) {
                UtilsStage.showMsg("رمزعبور با تکرار آن مطابق نیست.", "خطا", false, thisStage);
                return;
            }
            if (errorCheck.checked(false, "خطا", thisStage, pass_4, again_pass_4) != -1) {
                return;
            }
            Users u2 = user_table.getSelectionModel().getSelectedItem();
            u2.setPassword(pass_4.getText());
            databaseHelper.usersDao.createOrUpdate(u2);
            user_table_init();
            back_4.getOnAction().handle(event);
        });

        back_4.setOnAction((ActionEvent event) -> {
            step_1.setVisible(true);
            step_4.setVisible(false);
            pass_4.setText("");
            again_pass_4.setText("");
        });
    }

    private void step_5_init() {
        permission_table_review.setCellFactory((TreeView<Permission> param) -> {
            CheckBoxTreeCellMy cc = new CheckBoxTreeCellMy(true);
            return cc;
        });
        permission_table_review.setEditable(false);
        back_5.setOnAction((ActionEvent event) -> {
            step_1.setVisible(true);
            step_5.setVisible(false);
        });
    }

    private void permission_table_init(TreeView tv, String query) {
        CheckBoxTreeItem<Permission> root = new CheckBoxTreeItem<>(new Permission());
        List<Permission> permissionParent = databaseHelper.permissionDao.rawResults(
                query + " WHERE parent IS NULL ORDER BY sort_index ASC"
        );
        for (Permission cM : permissionParent) {
            CheckBoxTreeItem<Permission> parent = new CheckBoxTreeItem<>(cM);
            List<Permission> permissionChild = databaseHelper.permissionDao.rawResults(query + " WHERE parent = " + cM.getId() + " ORDER BY sort_index ASC");
            int i = 0;
            parent.setIndependent(true);
            for (Permission cMChild : permissionChild) {
                i = i + (cMChild.getDefaultValue() ? 1 : 0);
                CheckBoxTreeItem<Permission> child = new CheckBoxTreeItem<>(cMChild);
                child.setSelected(cMChild.getDefaultValue());
                parent.getChildren().add(child);
            }
            if (i == permissionChild.size()) {
                parent.setSelected(true);
            } else if (i > 0) {
                parent.setIndeterminate(true);
            }
            parent.setIndependent(false);
            root.getChildren().add(parent);
        }
        tv.setRoot(root);
        tv.refresh();
    }

    private void user_table_init() {
        filter_user_table.setText("");
        FilteredList<Users> filteredListt = new PrepareTable<Users>().init(user_table, databaseHelper.usersDao.rawResults(
                "SELECT * FROM users WHERE is_deleted = 0"
        ));
        filter_user_table.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredListt.setPredicate(s -> (newValue == null || newValue.length() == 0) ? true : (s.getName_fa() + s.getName_en() + s.getUsername()).toLowerCase().contains(newValue.toLowerCase()));
        });
    }

    public class PrepareTable<S> {

        public FilteredList<S> init(TableView<S> tb, List<S> list) {
            ObservableList<S> observableList = FXCollections.observableArrayList(list);
            FilteredList<S> filteredlist = new FilteredList<>(observableList, p -> true);
            SortedList<S> sortedData = new SortedList<>(filteredlist);
            sortedData.comparatorProperty().bind(tb.comparatorProperty());
            tb.setItems(sortedData);

            ArrayList<TableColumn<S, ?>> sortOrder = new ArrayList<>(tb.getColumns());
            tb.getSortOrder().clear();
            tb.getSortOrder().addAll(sortOrder);

            return filteredlist;
        }
    }
}
