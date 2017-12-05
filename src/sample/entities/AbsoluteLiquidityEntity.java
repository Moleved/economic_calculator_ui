package sample.entities;

public class AbsoluteLiquidityEntity extends EntityImpl implements Entity {
    private String shortFinancialInvestments;
    private String funds;
    private String shortLiabilities;

    public AbsoluteLiquidityEntity(String shortFinancialInvestments, String funds, String shortLiabilities) {
        this.shortFinancialInvestments = shortFinancialInvestments;
        this.funds = funds;
        this.shortLiabilities = shortLiabilities;
    }

    public String getShortFinancialInvestments() {
        return shortFinancialInvestments;
    }

    public String getFunds() {
        return funds;
    }

    public String getShortLiabilities() {
        return shortLiabilities;
    }

    @Override
    public String toString() {
        return "shortFinancialInvestments:" + shortFinancialInvestments + ",funds:" + funds + ",shortLiabilities:" + shortLiabilities;
    }
}
