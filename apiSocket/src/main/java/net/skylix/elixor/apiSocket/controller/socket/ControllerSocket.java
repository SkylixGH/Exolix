package net.skylix.elixor.apiSocket.controller.socket;

import java.util.HashMap;

import com.google.gson.Gson;

import org.java_websocket.WebSocket;

public class ControllerSocket {
    private final WebSocket conn;

    private final Gson gson = new Gson();

    private final String channel;

    public ControllerSocket(WebSocket conn, String channel) {
        this.conn = conn;
        this.channel = channel;
    }

    public final <ForcedDataType> void send(ControllerSocketMessage message) {
        HashMap<String, ForcedDataType> data = message.getKeyPairs();
        Object jsonObj = gson.toJson(data);

        new Thread(() -> {
            conn.send("channel:" + channel + ";" + jsonObj.toString());
        }).start();
    }
}
