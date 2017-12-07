package sample.controllers;

import sample.UniqHash;
import sample.connection.TCPClient;

public abstract class ActionController {
    protected String exchange(String fullMessage) {
        String message = getApplicationHash() + ";" + fullMessage;
        TCPClient.sendMessage(message + "\n");
        return TCPClient.getServerResponse();
    }

    private String getApplicationHash() {
        return UniqHash.getUniqHash();
    }

    public abstract String get();
    public abstract void post();
}
