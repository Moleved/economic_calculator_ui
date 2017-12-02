package sample;

import javafx.scene.layout.VBox;
import sample.builders.forms.AbsoluteLiquidityFormBuilder;
import sample.builders.forms.CurrentLiquidityFormBuilder;
import sample.builders.forms.ProfitabilityFormBuilder;

public class FormFactory {
    public VBox createAbsoluteLiquidityForm() {
        return new AbsoluteLiquidityFormBuilder().createForm();
    }

    public VBox createCurrentLiquidityForm() {
        return new CurrentLiquidityFormBuilder().createForm();
    }

    public VBox createProfitabilityForm() {
        return new ProfitabilityFormBuilder().createForm();
    }
}
