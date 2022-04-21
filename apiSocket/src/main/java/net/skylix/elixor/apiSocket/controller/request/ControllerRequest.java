package net.skylix.elixor.apiSocket.controller.request;

import java.util.HashMap;

public class ControllerRequest {
    private HashMap jsonData;

    public ControllerRequest(HashMap jsonData) {
        this.jsonData = jsonData;
    }

    public <DataType> DataType get(String key) {
        return (DataType) jsonData.get(key);
    }
}
