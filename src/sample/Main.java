package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.builders.statistic.StatisticViewBuilder;
import sample.connection.TCPClient;

import java.net.SocketException;
import java.util.ArrayList;

public class Main extends Application {
    private ChoiceBox choiceBox;
    private GridPane grid = new GridPane();
    private boolean firstLaunch = true;
    private String dropdownChoice = "absolute";
    private VBox box = new VBox();

    private final String[] TYPES = {"Абсолютная ликвидность", "Текущая ликвидность", "Прибыльность"};
    private final String[] ANCHORS = {"absolute", "current", "profit"};

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Economic calculator");

        createChoiceBox();

        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(choiceBox, 1,1 , 2, 1);

        addStatisticButton(primaryStage);
        addForm();



        Scene scene = new Scene(grid, 800, 360);
        primaryStage.setScene(scene);

        firstLaunch = false;
        primaryStage.show();
    }

    private void addStatisticButton(Stage parentStage) {
        Button button = new Button("Statistics");
        grid.add(button, 1, 2, 1, 1);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

                Stage stage = new Stage();

                stage.setTitle("Statistics");
                stage.initOwner(parentStage);
                stage.initModality(Modality.APPLICATION_MODAL);

                VBox vbox = new StatisticFacade(dropdownChoice).perform();

                Scene scene = new Scene(vbox, 800, 600);

                stage.setScene(scene);
                stage.showAndWait();
            }
        });
    }

    private void addForm() {
        FormFactory factory = new FormFactory();
        if (!firstLaunch) grid.getChildren().remove(this.box);

        switch (dropdownChoice) {
            case "absolute": box = factory.createAbsoluteLiquidityForm();
                break;
            case "current":  box = factory.createCurrentLiquidityForm();
                break;
            case "profit":   box = factory.createProfitabilityForm();
                break;
            default:         box = factory.createAbsoluteLiquidityForm();
        }

        grid.add(box, 3, 3, 5, 5);
    }

    private void createChoiceBox() {
        choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(TYPES));
        choiceBox.getSelectionModel().selectFirst();

        ArrayList<String> ents = fillList(ANCHORS);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        dropdownChoice = ents.get(newValue.intValue());
                    }
                }
        );
    }

    private ArrayList<String> fillList(String... args) {
        ArrayList<String> list = new ArrayList<>();
        for (String arg : args) list.add(arg);
        return list;
    }

    public static void main(String[] args) {
        try {
            TCPClient.establishConnection();
            launch(args);
        } catch (SocketException ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            TCPClient.closeSession();
        }
    }
}
