package com.masonc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class App extends Application {

    private Button convert = new Button("Convert!");
    private RadioButton leftKelvin = new RadioButton("Kelvin");
    private RadioButton rightKelvin = new RadioButton("Kelvin");
    private RadioButton fLeft = new RadioButton("Fahrenheit");
    private RadioButton fRight = new RadioButton("Fahrenheit");
    private RadioButton leftCelsius = new RadioButton("Celsius");
    private RadioButton rightCelsius = new RadioButton("Celsius");
    private TextField input = new TextField("Input here!");
    private TextField output = new TextField("Output here!");
    private ToggleGroup leftSide = new ToggleGroup();
    private ToggleGroup rightSide = new ToggleGroup();
    private Label instructions = new Label("Use the input box to enter the measurement you want to convert, " + "\n select what you are converting *from* with the left side toggles, " + " \n and what you are converting *to* with the right side toggles.")
;


    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        input.isEditable();
        output.setEditable(false);
        leftCelsius.setToggleGroup(leftSide);
        leftKelvin.setToggleGroup(leftSide);
        fLeft.setToggleGroup(leftSide);
        rightCelsius.setToggleGroup(rightSide);
        rightKelvin.setToggleGroup(rightSide);
        fRight.setToggleGroup(rightSide);

        HBox conversionMenu = new HBox();
        conversionMenu.setAlignment(Pos.TOP_CENTER);
        conversionMenu.getChildren().addAll(input,convert,output);

        HBox bottomText = new HBox();
        bottomText.setAlignment(Pos.CENTER);
        bottomText.getChildren().add(instructions);

        GridPane optionsMenu = new GridPane();
        optionsMenu.setAlignment(Pos.CENTER);
        GridPane.setConstraints(leftKelvin, 0,0);
        GridPane.setConstraints(rightKelvin,3,0);
        GridPane.setConstraints(leftCelsius, 0,1);
        GridPane.setConstraints(rightCelsius,3,1);
        GridPane.setConstraints(fLeft,0,2);
        GridPane.setConstraints(fRight,3,2);
        GridPane.setConstraints(instructions,7,7);

        convert.setOnAction(actionEvent -> assignFormula(actionEvent));

        optionsMenu.getChildren().addAll(leftKelvin,rightKelvin,leftCelsius,rightCelsius,fLeft,fRight);
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(conversionMenu);
        mainPane.setCenter(optionsMenu);
        mainPane.setBottom(bottomText);

        var scene = new Scene(mainPane, 640, 480);
        stage.setTitle("Temperature Converter v1.0");





        stage.setScene(scene);
        stage.show();
    }

    public void assignFormula(ActionEvent event) {


        try {
            if ((this.leftSide.getSelectedToggle().equals(leftKelvin) && this.rightSide.getSelectedToggle().equals(rightKelvin)))
                sameCalc();
            else if ((this.leftSide.getSelectedToggle().equals(leftCelsius) && this.rightSide.getSelectedToggle().equals(rightCelsius)))
                sameCalc();
            else if ((this.leftSide.getSelectedToggle().equals(fLeft) && this.rightSide.getSelectedToggle().equals(fRight)))
                sameCalc();
            else if (this.leftSide.getSelectedToggle().equals(leftKelvin) && this.rightSide.getSelectedToggle().equals(fRight))
                kelvinToFahrenheit();
            else if (this.leftSide.getSelectedToggle().equals(leftKelvin) && this.rightSide.getSelectedToggle().equals(rightCelsius))
                kelvinToCelsius();
            else if (this.leftSide.getSelectedToggle().equals(fLeft) && this.rightSide.getSelectedToggle().equals(rightKelvin))
                fahrenheitToKelvin();
            else if (this.leftSide.getSelectedToggle().equals(fLeft) && this.rightSide.getSelectedToggle().equals(rightCelsius))
                fahrenheitToCelsius();
            else if (this.leftSide.getSelectedToggle().equals(leftCelsius) && this.rightSide.getSelectedToggle().equals(rightKelvin))
                celsiusToKelvin();
            else if (this.leftSide.getSelectedToggle().equals(leftCelsius) && this.rightSide.getSelectedToggle().equals(fRight))
                celsiusToFahrenheit();
        }

        catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Please select two options to convert and input numbers only!");
            errorAlert.showAndWait();

        }


    }

    public void sameCalc() {
        output.setText(input.getText());
    }



    public void kelvinToFahrenheit() {
        double kelvinIn;
        double beforeRound;
        double exitValue = 0.0;
        kelvinIn = Double.parseDouble(input.getText());
        beforeRound = (kelvinIn * 9.0/5.0) - 459.67;
        exitValue = Math.round(beforeRound * 100.0) / 100.0;
        output.setText(Double.toString(exitValue));
    }

    public void kelvinToCelsius() {
        double kelvinIn;
        double beforeRound;
        double exitValue = 0.0;
        kelvinIn = Double.parseDouble(input.getText());
        beforeRound = kelvinIn - 273.15;
        exitValue = Math.round(beforeRound * 100.0) / 100.0;
        output.setText(Double.toString(exitValue));
    }

    public void fahrenheitToKelvin() {
        double fahrenheitIn;
        double beforeRound;
        double exitValue = 0.0;
        fahrenheitIn = Double.parseDouble(input.getText());
        beforeRound = (fahrenheitIn + 459.67) * 5.0/9.0;
        exitValue = Math.round(beforeRound * 100.0) / 100.0;
        output.setText(Double.toString(exitValue));
    }

    public void fahrenheitToCelsius() {
        double fahrenheitIn;
        double beforeRound;
        double exitValue = 0.0;
        fahrenheitIn = Double.parseDouble(input.getText());
        beforeRound = (fahrenheitIn - 32) * (5.0 / 9.0);
        exitValue = Math.round(beforeRound * 100.0) / 100.0;
        output.setText(Double.toString(exitValue));
    }

    public void celsiusToKelvin() {
        double celsiusIn;
        double beforeRound;
        double exitValue = 0.0;
        celsiusIn = Double.parseDouble(input.getText());
        beforeRound = (celsiusIn + 273.15);
        exitValue = Math.round(beforeRound * 100.0) / 100.0;
        output.setText(Double.toString(exitValue));
    }

    public void celsiusToFahrenheit() {
        double celsiusIn;
        double beforeRound;
        double exitValue = 0.0;
        celsiusIn = Double.parseDouble(input.getText());
        beforeRound = (celsiusIn * 1.8) + 32.0;
        exitValue = Math.round(beforeRound * 100.0) / 100.0;
        output.setText(Double.toString(exitValue));
    }





    public static void main(String[] args) {
        launch();
    }



}