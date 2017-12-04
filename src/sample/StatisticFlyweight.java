package sample;

import sample.entities.EntityImpl;

import java.util.HashMap;

public class StatisticFlyweight {
    private HashMap<String, EntityImpl> views = new HashMap<>();

    public StatisticFlyweight(String indexName) {
        defineViews();
    }

    private void defineViews() {}


}
