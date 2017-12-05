package sample.connection;

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
        }
    }

    public String getObject() {
        return object;
    }

    public ArrayList<HashMap<String, String>> parseValues() {
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
