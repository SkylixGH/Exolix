package net.skylix.elixor.apiSocket.controller.socket;

import java.util.HashMap;

public class ControllerSocketMessage<ForcedDataType> {
    private HashMap<String, ForcedDataType> keyPairs = new HashMap<>();

    public <DataType extends ForcedDataType> void set(String key, DataType value) {
        keyPairs.put(key, value);
    }

    public HashMap<String, ForcedDataType> getKeyPairs() {
        return new HashMap<>(keyPairs);
    }
}
