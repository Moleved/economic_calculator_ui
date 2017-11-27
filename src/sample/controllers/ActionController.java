package sample.controllers;

import sample.UniqHash;

public abstract class ActionController {
    protected void send(String fullMessage) {
        String message = getApplicationHash() + "|||" + fullMessage;
        System.out.println(message);
        // TODO: Sending logic
    }

    private String getApplicationHash() {
        return UniqHash.getUniqHash();
    }

    public abstract void get();
    public abstract void post();
}
