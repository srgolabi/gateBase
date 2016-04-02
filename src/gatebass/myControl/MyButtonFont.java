/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatebass.myControl;

import gatebass.GateBass;
import gatebass.utils.AGTPFont;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

/**
 *
 * @author reza
 */
public class MyButtonFont extends Button {

    public MyButtonFont() {
    }

    public MyButtonFont(String text) {
        super(text);
    }

    public MyButtonFont(String text, Node graphic) {
        super(text, graphic);
    }

    public MyButtonFont(String text, int fontSize, String style) {
        Font font = Font.loadFont(GateBass.class.getResource("resourse/agtp_font.ttf").toExternalForm(), fontSize);
        this.setFont(font);
        this.setText(AGTPFont.Icons.valueOf(text).getIcon());
        getStyleClass().add(style);
    }

//    public MyButtonFont(String text, int fontSize) {
//        Font font = Font.loadFont(GateBass.class.getResource("resourse/agtp_font.ttf").toExternalForm(), fontSize);
//        this.setFont(font);
//        this.setText(AGTPFont.Icons.valueOf(text).getIcon());
//        setStyle(getStyle() + "-fx-label-padding: -2;");
//    }
    public void init(String text, int fontSize, String style) {
        Font font = Font.loadFont(GateBass.class.getResource("resourse/agtp_font.ttf").toExternalForm(), fontSize);
        this.setFont(font);
        this.setText(AGTPFont.Icons.valueOf(text).getIcon());
        getStyleClass().add(style);
    }

    public void init(String text, int fontSize, String style, String tooTipText) {
        this.init(text, fontSize, style);
        this.set_toolTip(tooTipText);
    }

    public void init(String text, int fontSize) {
        Font font = Font.loadFont(GateBass.class.getResource("resourse/agtp_font.ttf").toExternalForm(), fontSize);
        this.setFont(font);
        this.setText(AGTPFont.Icons.valueOf(text).getIcon());
        setStyle(getStyle() + "-fx-label-padding: -2;");
    }

    public void init(String text, String tooTipText, int fontSize) {
        this.init(text, fontSize);
        set_toolTip(tooTipText);
    }

    private void set_toolTip(String tooTipText) {
        Tooltip tooltip = new Tooltip(tooTipText);
        this.setTooltip(tooltip);
    }

    public void changeText(String text) {
        this.setText(AGTPFont.Icons.valueOf(text).getIcon());
    }
}
