package sample.controllers;

import sample.entities.ProfitabilityEntity;

public class ProfitabilityController extends ActionController {
    private ProfitabilityEntity profit;

    public ProfitabilityController(ProfitabilityEntity entity) {
        this.profit = entity;
    }

    @Override
    public void get() {
        String message = "GET;Profitability;";
        exchange(message);
    }

    @Override
    public void post() {
        String message = "POST;Profitability;" + profit.toString();
        exchange(message);
    }
}
