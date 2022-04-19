package net.skylix.elixor.apiSocket;

import net.skylix.elixor.apiSocket.controller.Controller;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;
import net.skylix.elixor.apiSocket.errors.ServerAlreadyRunning;
import net.skylix.elixor.apiSocket.errors.ServerAlreadyStopped;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * The actual websocket server.
 */
class TrueServer extends WebSocketServer {
    private ArrayList<Controller> controllers = new ArrayList<>();

    public TrueServer(int port, ArrayList<Controller> controllers) {
        super(new InetSocketAddress(port));
        this.controllers = controllers;
    }

    private void dispatchOpen(WebSocket conn) {
        ControllerSocket socket = new ControllerSocket(conn);

        for (Controller controller : controllers) {
            controller.onActivate(socket);
        }
    }

    private void dispatchClose(WebSocket conn) {
        ControllerSocket socket = new ControllerSocket(conn);

        for (Controller controller : controllers) {
            controller.onDeactivate(socket);
        }
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        dispatchOpen(conn);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        dispatchClose(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {

    }

    @Override
    public void onError(WebSocket conn, Exception ex) {

    }

    @Override
    public void onStart() {

    }
}

/**
 * A class used for creating API services with real time interactions.
 */
public class APISocket {
    /**
     * The connected controllers.
     */
    private ArrayList<Controller> controllers = new ArrayList<>();

    /**
     * The actual socket host.
     */
    private final TrueServer wss;

    /**
     * If the server is fully ready.
     */
    private boolean isRunning = false;

    /**
     * If the server is currently booting or stopping.
     */
    private boolean isWorking = false;

    /**
     * Create a new realtime API service.
     */
    public APISocket() {
        wss = new TrueServer(8080, controllers);
    }

    /**
     * Adds a controller to the list of connected controllers.
     *
     * @param controller The controller to add.
     * @return The ID of the controller added.
     */
    public Integer connectController(Controller controller) {
        controllers.add(controller);

        return controllers.size() - 1;
    }

    /**
     * Get a specific controller by its ID.
     *
     * @param id The ID of the controller to get.
     * @return The controller with the specified ID.
     */
    public Controller getController(Integer id) {
        return controllers.get(id);
    }

    /**
     * Removes a controller from the list of connected controllers.
     *
     * @param controller The controller to remove.
     */
    public void disconnectController(Controller controller) {
        controllers.remove(controller);
    }

    /**
     * Run the API live server.
     */
    public void run() throws ServerAlreadyRunning {
        if (isRunning || (isRunning && isWorking)) {
            throw new ServerAlreadyRunning(isWorking ? "The server is already running." : "The server is currently loading.");
        }

        isWorking = true;
        wss.start();

        isWorking = false;
        isRunning = true;
    }

    public void stop() throws ServerAlreadyStopped, InterruptedException {
        if (!isRunning || (isRunning && isWorking)) {
            throw new ServerAlreadyStopped(isWorking ? "The server is already stopping." : "The server is not running.");
        }

        try {
            wss.stop();
        } catch (Exception error) {
            throw error;
        }
    }
}
