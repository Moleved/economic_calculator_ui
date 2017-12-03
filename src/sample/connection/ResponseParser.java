package sample.connection;

import sample.entities.AbsoluteLiquidityEntity;
import sample.entities.CurrentLiquidityEntity;
import sample.entities.ProfitabilityEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseParser {
    private String message;

    private String object;
    private String values;

    private HashMap<String, String> valuesHash = new HashMap<>();

    public ResponseParser(String message) {
        this.message = message;
    }

    public void parse() {
        String[] array;

        if (message == "404") System.out.println("Not Found!");
        else {
            array = message.split(">");

            this.object = array[0];
            this.values = array[1];

            handleValues();
        }
    }

    private void handleValues() {
        ArrayList<?> handledValues;

        if (object == "AbsoluteLiquidityEntity") handledValues = handleAbsoluteLiquidity();
        else if (object == "CurrentLiquidityEntity") handledValues = handleCurrentLiquidityEntity();
        else if (object == "ProfitabilityEntity") handledValues = handleProfitabilityEntity();
    }

    private ArrayList<AbsoluteLiquidityEntity> handleAbsoluteLiquidity() {
        ArrayList<AbsoluteLiquidityEntity> list = new ArrayList<>();

        for (HashMap<String, String> hash : parseValues()) {
            list.add(new AbsoluteLiquidityEntity(hash.get("shortFinancialInvestments"), hash.get("funds"), hash.get("shortLiabilities")));
        }

        return list;
    }

    private ArrayList<CurrentLiquidityEntity> handleCurrentLiquidityEntity() {
        ArrayList<CurrentLiquidityEntity> list = new ArrayList<>();

        for (HashMap<String, String> hash : parseValues()) {
            list.add(new CurrentLiquidityEntity(hash.get("revolvingAssets"), hash.get("shortLiabilities")));
        }

        return list;
    }

    private ArrayList<ProfitabilityEntity> handleProfitabilityEntity() {
        ArrayList<ProfitabilityEntity> list = new ArrayList<>();

        for (HashMap<String, String> hash : parseValues()) {
            list.add(new ProfitabilityEntity(hash.get("profitFromAllActivities"), hash.get("totalProductSalesCosts")));
        }

        return list;
    }

    private ArrayList<HashMap<String, String>> parseValues() {
        String[] valuesForEachClass = values.split(";");
        ArrayList<HashMap<String, String>> hashesList = new ArrayList<>();

        for (String elem : valuesForEachClass) {

            HashMap<String, String> hash = new HashMap<>();
            String[] array = elem.split(",");

            for (String el : array) {
                if (el == "break") break;
                String[] pair =  el.split(":");
                hash.put(pair[0], pair[1]);
            }

            hashesList.add(hash);
        }

        return hashesList;
    }
}
