package net.skylix.elixor.apiSocket.controller.request;

import java.util.HashMap;

public class ControllerRequest {
    private final HashMap<String, ?> jsonData;

    public ControllerRequest(HashMap<String, ?> jsonData) {
        this.jsonData = jsonData;
    }

    public <DataType> DataType get(String key) {
        for (String jsonKey : jsonData.keySet()) {
            if (jsonKey.equals(key)) {
                return (DataType) jsonData.get(jsonKey);
            }
        }

        return null;
    }
}
