package sample.entities;

public class AbsoluteLiquidityEntity {
    private String shortFinancialInvestments;
    private String funds;

    public AbsoluteLiquidityEntity(String shortFinancialInvestments, String funds) {
        this.shortFinancialInvestments = shortFinancialInvestments;
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "shortFinancialInvestments:" + shortFinancialInvestments + ";" + "funds:" + funds;
    }
}
