package sample.connection;

import sample.entities.AbsoluteLiquidityEntity;
import sample.entities.CurrentLiquidityEntity;
import sample.entities.ProfitabilityEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseHandler {
    private String object;
    private ArrayList<HashMap<String, String>> values;

    public ResponseHandler(ResponseParser parser) {
        this.object = parser.getObject();
        this.values = parser.parseValues();
    }

    public ArrayList<AbsoluteLiquidityEntity> handleAbsoluteLiquidity() {
        ArrayList<AbsoluteLiquidityEntity> list = new ArrayList<>();

        for (HashMap<String, String> hash : values) {
            AbsoluteLiquidityEntity liq = new AbsoluteLiquidityEntity(hash.get("shortFinancialInvestments"), hash.get("funds"), hash.get("shortLiabilities"));
            liq.setDate(hash.get("date"));
            liq.setResult(hash.get("result"));

            list.add(liq);
        }

        return list;
    }

    public ArrayList<CurrentLiquidityEntity> handleCurrentLiquidityEntity() {
        ArrayList<CurrentLiquidityEntity> list = new ArrayList<>();

        for (HashMap<String, String> hash : values) {
            CurrentLiquidityEntity liq = new CurrentLiquidityEntity(hash.get("revolvingAssets"), hash.get("shortLiabilities"));
            liq.setDate(hash.get("date"));
            liq.setResult(hash.get("result"));
            list.add(liq);
        }

        return list;
    }

    public ArrayList<ProfitabilityEntity> handleProfitabilityEntity() {
        ArrayList<ProfitabilityEntity> list = new ArrayList<>();

        for (HashMap<String, String> hash : values) {
            ProfitabilityEntity prof = new ProfitabilityEntity(hash.get("profitFromAllActivities"), hash.get("totalProductSalesCosts"));
            prof.setDate(hash.get("date"));
            prof.setResult(hash.get("result"));
            list.add(prof);
        }

        return list;
    }
}
