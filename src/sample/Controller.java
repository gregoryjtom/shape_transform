package sample;

import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private Rectangle rect;

    @FXML
    private TextField textField;

    @FXML
    private Slider slider;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;

    @FXML
    private RadioButton rb5;

    @FXML
    private void setRectColor(ActionEvent event) {
        Color newColor = (Color) toggleGroup.getSelectedToggle().getUserData();
        rect.setFill(newColor);
    }

    @FXML
    private void scaleRect(ActionEvent event) {
        scale(Double.valueOf(textField.getText()) / 100, 0.1);
    }

    @FXML
    private void translateUp(ActionEvent event){
        translate("up");
    }

    @FXML
    private void translateDown(ActionEvent event){
        translate("down");
    }

    @FXML
    private void translateLeft(ActionEvent event){
        translate("left");
    }

    @FXML
    private void translateRight(ActionEvent event){
        translate("right");
    }

    @FXML
    private void rotateRectLeft(ActionEvent event) {
        rotate(false);
    }

    @FXML
    private void rotateRectRight(ActionEvent event) {
        rotate(true);
    }

    @FXML
    private void setDefault() {
        translateDefault();
        scale(1, 2);
        orientationDefault();
        colorDefault();
        slider.setValue(100);
        rb1.setSelected(true);
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.L)) {
            rotate(false);
        } else if (event.getCode().equals(KeyCode.R)) {
            rotate(true);
        } else if (event.getCode().equals(KeyCode.D)) {
            setDefault();
        }
        else if (event.isAltDown() && event.getCode().equals(KeyCode.UP)) {
            translate("up");
        }
        else if (event.isAltDown() && event.getCode().equals(KeyCode.DOWN)) {
            translate("down");
        }
        else if (event.isAltDown() && event.getCode().equals(KeyCode.LEFT)) {
            translate("left");
        }
        else if (event.isAltDown() && event.getCode().equals(KeyCode.RIGHT)) {
            translate("right");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                scale((newValue.doubleValue() / 100), 0.1);

            }
        });
        textField.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());

        rb1.setUserData(Color.DODGERBLUE);
        rb2.setUserData(Color.MAGENTA);
        rb3.setUserData(Color.CYAN);
        rb4.setUserData(Color.GOLD);
        rb5.setUserData(Color.LIME);
    }



    private void scale(double size, double time) {
        ScaleTransition st = new ScaleTransition(Duration.seconds(time), rect);
        st.setToX(size);
        st.setToY(size);
        st.play();
    }

    private void translateDefault() {
        TranslateTransition tr = new TranslateTransition(Duration.seconds(2), rect);
        tr.setToX(0);
        tr.setToY(0);
        tr.play();
    }

    private void translate(String dir) {
        TranslateTransition tr = new TranslateTransition(Duration.seconds(0.1), rect);
        if (dir == "up") {
            tr.setByY(-10);
        } else if (dir == "down") {
            tr.setByY(10);
        } else if (dir == "right") {
            tr.setByX(10);
        } else {
            tr.setByX(-10);
        }
        tr.play();
    }

    private void orientationDefault() {
        RotateTransition rt = new RotateTransition(Duration.seconds(2), rect);
        rt.setToAngle(0);
        rt.play();
    }

    private void rotate(boolean right) {
        RotateTransition rt = new RotateTransition(Duration.seconds(0.1), rect);
        if (right) {
            rt.setByAngle(2);
        } else {
            rt.setByAngle(-2);
        }
        rt.play();
    }

    private void colorDefault() {
        FillTransition ft = new FillTransition(Duration.seconds(2), rect);
        ft.setToValue(Color.DODGERBLUE);
        ft.play();
    }
}
