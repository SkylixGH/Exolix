package net.skylix.elixor.apiSocket.controller.socket;

import java.util.HashMap;

import com.google.gson.Gson;

import org.java_websocket.WebSocket;

public class ControllerSocket {
    private WebSocket conn;

    private Gson gson = new Gson();

    private String channel;

    public ControllerSocket(WebSocket conn, String channel) {
        this.conn = conn;
        this.channel = channel;
    }

    public <ForcedDataType> void send(ControllerSocketMessage<ForcedDataType> message) {
        HashMap<String, ForcedDataType> data = message.getKeyPairs();
        Object jsonObj = gson.toJson(data);

        conn.send("channel:" + channel + ";" + jsonObj.toString());
    }
}
