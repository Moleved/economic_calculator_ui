package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.connection.TCPClient;

import java.net.SocketException;
import java.util.ArrayList;

public class Main extends Application {
    private ChoiceBox choiceBox;
    private GridPane grid = new GridPane();
    private boolean firstLaunch = true;

    private final String[] TYPES = {"Абсолютная ликвидность", "Текущая ликвидность", "Прибыльность"};
    private final String[] ANCHORS = {"absolute", "current", "profit"};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        createChoiceBox();

        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(choiceBox, 1,1 , 2, 1);

        addForm("");

        Scene scene = new Scene(grid, 800, 360);
        primaryStage.setScene(scene);

        firstLaunch = false;
        primaryStage.show();
    }

    private void addForm(String formName) {
        VBox vBox;
        FormFactory factory = new FormFactory();

        switch (formName) {
            case "absolute": vBox = factory.createAbsoluteLiquidityForm();
                             break;
            case "current":  vBox = factory.createCurrentLiquidityForm();
                             break;
            case "profit":   vBox = factory.createProfitabilityForm();
                             break;
            default:         vBox = factory.createAbsoluteLiquidityForm();
        }

        if (!firstLaunch) grid.getChildren().remove(1);
        grid.add(vBox, 3, 3, 5, 5);
    }

    private void createChoiceBox() {
        choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(TYPES));
        choiceBox.getSelectionModel().selectFirst();

        ArrayList<String> ents = fillList(ANCHORS);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        addForm(ents.get(newValue.intValue()));
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
