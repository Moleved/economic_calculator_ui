package sample.entities;

public class CurrentLiquidityEntity {
    private String revolvingAssets;

    public CurrentLiquidityEntity(String revolvingAssets) {
        this.revolvingAssets = revolvingAssets;
    }

    @Override
    public String toString() {
        return "revolvingAssets:" + revolvingAssets;
    }
}
