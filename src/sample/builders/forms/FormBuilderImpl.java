package sample.builders.forms;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public abstract class FormBuilderImpl implements FormBuilder{
    private VBox box = new VBox();
    protected ArrayList<TextField> inputs;

    @Override
    public VBox createForm() {
        GridPane grid = createGrid();

        ArrayList<Label> labels = (ArrayList<Label>) makeLabels();
        inputs = (ArrayList<TextField>) makeInputs();

        for (int i = 0; i < labels.size(); i++) {
            Label label = labels.get(i);
            TextField input = inputs.get(i);

            label.setLabelFor(input);

            grid.add(label, 0, i, 4, 1);
            grid.add(input, 4, i, 2, 1);
        }

        Button button = addSubmitButton();
        grid.add(button, 5, labels.size(), 1, 1);

        box.getChildren().add(grid);

        return box;
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();

        ColumnConstraints fivePercentConstr = new ColumnConstraints();
        fivePercentConstr.setPercentWidth(5);

        ColumnConstraints fifteenPercentConstr = new ColumnConstraints();
        fifteenPercentConstr.setPercentWidth(15);

        grid.getColumnConstraints().add(fivePercentConstr);
        for (int i = 0; i <= 6; i++) grid.getColumnConstraints().add(fifteenPercentConstr);
        grid.getColumnConstraints().add(fivePercentConstr);

        grid.setVgap(5);

        return grid;
    }

    protected void validate() {
        for (TextField input : inputs) {
            String value = input.getText();
            if (!matcher(value)) {
                input.getStyleClass().add("invalid");
            }
        }
    }

    protected boolean matcher(String value) {
        return Pattern.matches("[0-9]+(\\.)?([0-9]+)?", value);
    }

    protected void addWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Введены неверные данные");
        alert.setHeaderText("Ошибка чтения данные");
        alert.setContentText("Данные должные содержать только цифры");

        alert.showAndWait();
    }

    protected abstract Collection<Label> makeLabels();
    protected abstract Collection<TextField> makeInputs();
    protected abstract Button addSubmitButton();
}
