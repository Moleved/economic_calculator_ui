package sample;

import javafx.scene.layout.VBox;
import sample.builders.AbsoluteLiquidityFormBuilder;
import sample.builders.CurrentLiquidityFormBuilder;
import sample.builders.ProfitabilityFormBuilder;

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
