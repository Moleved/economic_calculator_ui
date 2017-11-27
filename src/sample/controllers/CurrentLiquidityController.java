package sample.controllers;

import sample.entities.CurrentLiquidityEntity;

public class CurrentLiquidityController extends ActionController {
    private CurrentLiquidityEntity entity;

    public CurrentLiquidityController(CurrentLiquidityEntity entity) {
        this.entity = entity;
    }

    @Override
    public void get() {
        String message = "GET;CurrentLiquidity;";
        exchange(message);
    }

    @Override
    public void post() {
        String message = "POST;CurrentLiquidity;" + entity;
        exchange(message);
    }
}
