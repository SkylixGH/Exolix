package net.skylix.elixor.apiSocket.controller.socket;

import java.util.HashMap;

public class ControllerSocketMessage {
    private HashMap<String, Object> keyPairs = new HashMap<>();

    public <DataType> void set(String key, DataType value) {
        keyPairs.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <HashItemType> HashMap<String, HashItemType> getKeyPairs() {
        HashMap<String, HashItemType> mapClone = new HashMap<>();

        for (String key : keyPairs.keySet()) {
            mapClone.put(key, (HashItemType) keyPairs.get(key));
        }

        return mapClone;
    }
}
