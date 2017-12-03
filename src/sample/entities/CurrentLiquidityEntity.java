package sample.entities;

public class CurrentLiquidityEntity extends Entity {
    private String revolvingAssets;
    private String shortLiabilities;

    public CurrentLiquidityEntity(String revolvingAssets, String shortLiabilities) {
        this.revolvingAssets = revolvingAssets;
        this.shortLiabilities = shortLiabilities;
    }

    public String getRevolvingAssets() {
        return revolvingAssets;
    }

    public String getShortLiabilities() {
        return shortLiabilities;
    }

    @Override
    public String toString() {
        return "revolvingAssets:" + revolvingAssets + ",shortLiabilities:" + shortLiabilities;
    }
}
