package sample.builders.statistic;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public abstract class StatisticViewBuilder {
    protected VBox box = new VBox();
    protected String indexName;

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

        return box;
    }

    private void addChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle(economicParameters.get(indexName) + " Statistic");

        XYChart.Series series = new XYChart.Series();
        series.setName(economicParameters.get(indexName));

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

        lineChart.getData().add(series);

        box.getChildren().add(lineChart);
    }

    protected abstract void addTable();
}
