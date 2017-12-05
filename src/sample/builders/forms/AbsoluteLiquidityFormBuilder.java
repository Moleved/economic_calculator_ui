package sample.builders.forms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.controllers.AbsoluteLiquidityController;
import sample.entities.AbsoluteLiquidityEntity;

import java.util.ArrayList;
import java.util.Collection;

public class AbsoluteLiquidityFormBuilder extends FormBuilderImpl {
    private String[] labelsContent = { "Short financial investments: ", "Funds: ", "Short liabilities: " };
    private Collection<TextField> inputs;

    @Override
    protected Collection<Label> makeLabels() {
        ArrayList<Label> labels = new ArrayList<>();

        for (String label : labelsContent) {
            labels.add(new Label(label));
        }

        return labels;
    }

    @Override
    protected Collection<TextField> makeInputs() {
        inputs = new ArrayList<>();
        
        for (int i = 0; i < labelsContent.length; i++) {
            inputs.add(new TextField());
        }

        return inputs;
    }

    @Override
    protected Button addSubmitButton() {
        Button button = new Button("Submit");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                ArrayList<TextField> al = (ArrayList<TextField>) inputs;
                String shortFinancialInvestments = al.get(0).getText();
                String funds = al.get(1).getText();
                String shortLiabilities = al.get(2).getText();

                AbsoluteLiquidityEntity profit = new AbsoluteLiquidityEntity(shortFinancialInvestments, funds, shortLiabilities);
                new AbsoluteLiquidityController(profit).post();
            }
        });

        return button;
    }
}
