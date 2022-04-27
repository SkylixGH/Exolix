package net.skylix.elixor.apiSocket.controller.socket;

import java.util.HashMap;

public class ControllerSocketMessage {
    private final HashMap<String, Object> keyPairs = new HashMap<>();

    public final <DataType> void set(String key, DataType value) {
        keyPairs.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public final <HashItemType> HashMap<String, HashItemType> getKeyPairs() {
        HashMap<String, HashItemType> mapClone = new HashMap<>();

        for (String key : keyPairs.keySet()) {
            mapClone.put(key, (HashItemType) keyPairs.get(key));
        }

        return mapClone;
    }
}
