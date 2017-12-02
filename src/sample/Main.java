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
import sample.connection.TCPClient;

import java.net.SocketException;
import java.util.ArrayList;

public class Main extends Application {
    private ChoiceBox choiceBox;
    private GridPane grid = new GridPane();
    private boolean firstLaunch = true;
    VBox box = new VBox();

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
        addForm("");


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

                addChart(stage);
//                addTable(stage);

                stage.setTitle("Statistics");
                stage.initOwner(parentStage);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
        });
    }

    private void addChart(Stage stage) {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    private void addTable(Stage stage) {
        TableView table = new TableView();

        Scene scene = new Scene(new Group());

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    private void addForm(String formName) {
        FormFactory factory = new FormFactory();
        if (!firstLaunch) grid.getChildren().remove(this.box);

        switch (formName) {
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
