package sample.builders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controllers.CurrentLiquidityController;
import sample.entities.CurrentLiquidityEntity;

import java.util.ArrayList;
import java.util.Collection;

public class CurrentLiquidityFormBuilder extends FormBuilderImpl {
    private Collection<TextField> inputs;

    @Override
    protected Collection<Label> makeLabels() {
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label("Revolving assets: "));
        return labels;
    }

    @Override
    protected Collection<TextField> makeInputs() {
        inputs = new ArrayList<>();
        inputs.add(new TextField());
        return inputs;
    }

    @Override
    protected Button addSubmitButton() {
        Button button = new Button("Submit");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                ArrayList<TextField> al = (ArrayList<TextField>) inputs;
                String revolvingAssets = al.get(0).getText();

                CurrentLiquidityEntity current = new CurrentLiquidityEntity(revolvingAssets);
                new CurrentLiquidityController(current).post();
            }
        });

        return button;
    }
}
