package net.skylix.elixor.apiSocket.errors;

public class ServerAlreadyRunning extends Exception {
    public ServerAlreadyRunning(String message) {
        super(message);
    }
}
