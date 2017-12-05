package sample.controllers;

import sample.entities.ProfitabilityEntity;

public class ProfitabilityController extends ActionController {
    private ProfitabilityEntity profit;

    public ProfitabilityController(ProfitabilityEntity entity) {
        this.profit = entity;
    }

    @Override
    public String get() {
        String message = "GET;Profitability;";
        return exchange(message);
    }

    @Override
    public void post() {
        String message = "POST;Profitability;" + profit.toString();
        exchange(message);
    }
}
