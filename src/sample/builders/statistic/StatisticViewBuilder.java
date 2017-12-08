package sample.builders.statistic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import sample.entities.EntityImpl;

import java.util.HashMap;

public abstract class StatisticViewBuilder {
    protected VBox box = new VBox();
    protected String indexName;
    protected EntityImpl[] entities;

    protected static final HashMap<String, String> economicParameters = new HashMap<>();
    protected static final HashMap<String, String[]> tableColumns = new HashMap<>();

    static {
        economicParameters.put("absolute", "Absolute Liquidity");
        economicParameters.put("current", "Current Liquidity");
        economicParameters.put("profit", "Profitability");

        tableColumns.put("absolute", new String[] {"shortFinancialInvestments", "funds", "shortLiabilities", "result", "date"});
        tableColumns.put("current", new String[] {"revolvingAssets", "shortLiabilities", "result", "date"});
        tableColumns.put("profit", new String[] {"profitFromAllActivities", "totalProductSalesCosts", "result", "date"});
    }

    public StatisticViewBuilder(String indexName) {
        this.indexName = indexName;
    }

    public VBox build() {
        addChart();
        addTable();
        addFileButton();

        return box;
    }

    private void addFileButton() {
        Button button = new Button("To File");

        box.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                new PDFBuilder(indexName, entities).perform();
            }
        });
    }

    private void addChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle(economicParameters.get(indexName) + " Statistic");

        XYChart.Series series = new XYChart.Series();
        series.setName(economicParameters.get(indexName));

        for (EntityImpl entity : entities) {
            series.getData().add(new XYChart.Data(entity.getDate(), entity.getResultDoub()));
        }

        lineChart.getData().add(series);

        box.getChildren().add(lineChart);
    }

    protected abstract void addTable();
}
