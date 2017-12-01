package sample.entities;

public class ProfitabilityEntity {
    private String profitFromAllActivities;
    private String totalProductSalesCosts;

    public ProfitabilityEntity(String profitFromAllActivities, String totalProductSalesCosts) {
        this.profitFromAllActivities = profitFromAllActivities;
        this.totalProductSalesCosts = totalProductSalesCosts;
    }

    @Override
    public String toString() {
        return "profitFromAllActivities:" + profitFromAllActivities + "," + "totalProductSalesCosts:" + totalProductSalesCosts;
    }
}
