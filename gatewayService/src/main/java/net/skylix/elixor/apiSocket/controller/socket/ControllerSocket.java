package net.skylix.elixor.apiSocket.controller.socket;

import org.java_websocket.WebSocket;

public class ControllerSocket {
    private WebSocket conn;

    public ControllerSocket(WebSocket conn) {
        this.conn = conn;
    }
}
