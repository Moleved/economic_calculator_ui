package sample.controllers;

import sample.entities.AbsoluteLiquidityEntity;

public class AbsoluteLiquidityController extends ActionController {
    private AbsoluteLiquidityEntity entity;

    public AbsoluteLiquidityController(AbsoluteLiquidityEntity entity) {
        this.entity = entity;
    }

    @Override
    public String get() {
        String message = "GET;AbsoluteLiquidity;";
        return exchange(message);
    }

    @Override
    public void post() {
        String message = "POST;AbsoluteLiquidity;" + entity;
        exchange(message);
    }
}
