package sample.entities;

public class CurrentLiquidityEntity {
    private String revolvingAssets;
    private String shortLiabilities;

    public CurrentLiquidityEntity(String revolvingAssets, String shortLiabilities) {
        this.revolvingAssets = revolvingAssets;
        this.shortLiabilities = shortLiabilities;
    }

    @Override
    public String toString() {
        return "revolvingAssets:" + revolvingAssets + ",shortLiabilities:" + shortLiabilities;
    }
}
