package sample;

import javafx.scene.layout.VBox;
import sample.builders.statistic.AbsoluteViewBuilder;
import sample.builders.statistic.CurrentViewBuilder;
import sample.builders.statistic.ProfitViewBuilder;
import sample.connection.ResponseHandler;
import sample.connection.ResponseParser;
import sample.controllers.AbsoluteLiquidityController;
import sample.controllers.ActionController;
import sample.controllers.CurrentLiquidityController;
import sample.controllers.ProfitabilityController;
import sample.entities.AbsoluteLiquidityEntity;
import sample.entities.CurrentLiquidityEntity;
import sample.entities.Entity;
import sample.entities.ProfitabilityEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticFacade {
    private String indexName;

    private static HashMap<String, ActionController> controllers = new HashMap<>();

    static {
        controllers.put("absolute", new AbsoluteLiquidityController(new AbsoluteLiquidityEntity("", "", "")));
        controllers.put("current", new CurrentLiquidityController(new CurrentLiquidityEntity("","")));
        controllers.put("profit", new ProfitabilityController(new ProfitabilityEntity("", "")));
    }

    public StatisticFacade(String indexName) {
        this.indexName = indexName;
    }

    public VBox perform() {
        if (indexName == "absolute") {
            ArrayList<AbsoluteLiquidityEntity> list = handleResponse(parseResponse(getResponse())).handleAbsoluteLiquidity();
            AbsoluteLiquidityEntity[] entities = new AbsoluteLiquidityEntity[list.size()];
            entities = list.toArray(entities);
            AbsoluteViewBuilder view = new AbsoluteViewBuilder(indexName, entities);
            return view.build();
        } else if (indexName == "current") {
            ArrayList<CurrentLiquidityEntity> list = handleResponse(parseResponse(getResponse())).handleCurrentLiquidityEntity();
            CurrentViewBuilder view = new CurrentViewBuilder(indexName, list.toArray(new CurrentLiquidityEntity[list.size()]));
            return view.build();
        } else if (indexName == "profit") {
            ArrayList<ProfitabilityEntity> list = handleResponse(parseResponse(getResponse())).handleProfitabilityEntity();
            ProfitViewBuilder view = new ProfitViewBuilder(indexName, list.toArray(new ProfitabilityEntity[list.size()]));
            return view.build();
        }
        return new VBox();
    }

    private String getResponse() {
        return controllers.get(indexName).get();
    }

    private ResponseParser parseResponse(String message) {
        ResponseParser parser = new ResponseParser(message);
        parser.parse();

        return parser;
    }

    private ResponseHandler handleResponse(ResponseParser parser) {
        ResponseHandler handler = new ResponseHandler(parser);
        return handler;
    }

    private void defineViews() {}
}
