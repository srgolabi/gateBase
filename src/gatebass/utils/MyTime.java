/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.utils;

import static gatebass.GateBass.databaseHelper;
import gatebass.dataBase.tables.History;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author reza
 */
public class MyTime {

    private TextField year;
    private TextField mount;
    private TextField day;

    public static void set_empty_myTime(MyTime... myTimes) {
        for (MyTime mt : myTimes) {
            mt.set_empty_textField();
        }
    }

    public MyTime() {
    }

    public void set_Editable(BooleanProperty bp) {
        year.editableProperty().bind(bp);
        mount.editableProperty().bind(bp);
        day.editableProperty().bind(bp);
    }

    public void set_empty_textField() {
        year.setText("");
        mount.setText("");
        day.setText("");
    }

    public MyTime(TextField year, TextField mount, TextField day) {
        this.year = year;
        this.mount = mount;
        this.day = day;
        TextFiledLimited.set_Number_Length_Date_Limit(this.year, 2);
        TextFiledLimited.set_Number_Length_Date_Limit(this.mount, 2);
        TextFiledLimited.set_Number_Length_Date_Limit(this.day, 2);
    }

    public void setText(History history) {
        if (history != null) {
            year.setText(addFirstZero(history.getYear()));
            mount.setText(addFirstZero(history.getMonth()));
            day.setText(addFirstZero(history.getDay()));
        }
    }

    private String addFirstZero(int field) {
        return field > 9 ? field + "" : "0" + field;
    }

    public boolean isFull() {
        if (getTxt(year) || getTxt(mount) || getTxt(day)) {
            return false;
        }
        return true;
    }

    public boolean isDamage(String st, Stage s) {
        if (getTxt(year) && getTxt(mount) && getTxt(day)) {
            return false;
        }
        if (!getTxt(year) && !getTxt(mount) && !getTxt(day)) {
            return false;
        }
        UtilsStage.showMsg(st, "خطا", false, s);
        return true;

//        if (!(getTxt(year) && getTxt(mount) && getTxt(day))) {
//            UtilsStage.showMsg(st, "خطا", false, s);
//            return true;
//        }
//        return false;
    }

    private boolean getTxt(TextField field) {
        return field.getText().trim().equals("");
    }

    public History writeAndGet() {
        String tempHistory = year.getText() + "/" + mount.getText() + "/" + day.getText();
        History letterHistory = databaseHelper.historyDao.getFirst("date", tempHistory);
        if (letterHistory == null) {
            letterHistory = new History(year.getText(), mount.getText(), day.getText());
            databaseHelper.historyDao.create(letterHistory);
        }
        return letterHistory;
    }

    public History writeAndGetNow() {
        PersianCalendar calendar = new PersianCalendar();
        String yaer = calendar.year2dig();
        String month = calendar.month().length() == 2 ? calendar.month() : "0" + calendar.month();
        String day = calendar.day().length() == 2 ? calendar.day() : "0" + calendar.day();

        String tempHistory = yaer + "/" + month + "/" + day;
        History letterHistory = databaseHelper.historyDao.getFirst("date", tempHistory);
        if (letterHistory == null) {
            letterHistory = new History(yaer, month, day);
            databaseHelper.historyDao.createOrUpdate(letterHistory);
        }
        return letterHistory;
    }

    public String getHourNow() {
        PersianCalendar calendar = new PersianCalendar();
        return calendar.hour() + ":" + calendar.minute() + ":" + calendar.second();
    }

    public String getDate() {
        return year.getText() + "/" + mount.getText() + "/" + day.getText();
    }
}
