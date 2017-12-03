package sample.entities;

public class ProfitabilityEntity extends Entity {
    private String profitFromAllActivities;
    private String totalProductSalesCosts;

    public ProfitabilityEntity(String profitFromAllActivities, String totalProductSalesCosts) {
        this.profitFromAllActivities = profitFromAllActivities;
        this.totalProductSalesCosts = totalProductSalesCosts;
    }

    public String getProfitFromAllActivities() {
        return profitFromAllActivities;
    }

    public String getTotalProductSalesCosts() {
        return totalProductSalesCosts;
    }

    @Override
    public String toString() {
        return "profitFromAllActivities:" + profitFromAllActivities + "," + "totalProductSalesCosts:" + totalProductSalesCosts;
    }
}
