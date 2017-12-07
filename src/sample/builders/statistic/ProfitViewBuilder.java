package sample.builders.statistic;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import sample.entities.ProfitabilityEntity;

public class ProfitViewBuilder extends StatisticViewBuilder {
    public ProfitViewBuilder(String indexName, ProfitabilityEntity[] entities) {
        super(indexName);
        this.entities = entities;
    }

    @Override
    protected void addTable() {
        TableView<ProfitabilityEntity> table = new TableView();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxWidth(600);

        final Label label = new Label(economicParameters.get(indexName));

        ProfitabilityEntity[] ents = (ProfitabilityEntity[]) entities;
        table.getItems().addAll(ents);
        for (String name : tableColumns.get(indexName)) {
            TableColumn<ProfitabilityEntity, String> col = new TableColumn<>(name);
            col.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDate()));

            if (name == "profitFromAllActivities") col.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProfitFromAllActivities()));
            if (name == "totalProductSalesCosts") col.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTotalProductSalesCosts()));
            if (name == "date") col.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDate()));
            if (name == "result") col.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getResult()));

            table.getColumns().add(col);
        }

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        box.getChildren().add(vbox);
    }

    private void addToPdfButton(VBox box) {
        Button button = new Button("To PDF");
        box.getChildren().add(button);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new PDFBuilder(indexName, entities).perform();
            }
        });
    }
}
