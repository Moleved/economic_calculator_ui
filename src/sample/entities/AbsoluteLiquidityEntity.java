package sample.entities;

public class AbsoluteLiquidityEntity {
    private String shortFinancialInvestments;
    private String funds;
    private String shortLiabilities;

    public AbsoluteLiquidityEntity(String shortFinancialInvestments, String funds, String shortLiabilities) {
        this.shortFinancialInvestments = shortFinancialInvestments;
        this.funds = funds;
        this.shortLiabilities = shortLiabilities;
    }

    @Override
    public String toString() {
        return "shortFinancialInvestments:" + shortFinancialInvestments + ",funds:" + funds + ",shortLiabilities:" + shortLiabilities;
    }
}
