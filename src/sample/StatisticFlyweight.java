package sample;

import sample.entities.Entity;

import java.util.HashMap;

public class StatisticFlyweight {
    private HashMap<String, Entity> views = new HashMap<>();

    public StatisticFlyweight(String indexName) {
        defineViews();
    }

    private void defineViews() {}


}
