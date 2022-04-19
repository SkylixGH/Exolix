package net.skylix.elixor.apiSocket.controller;

import net.skylix.elixor.apiSocket.controller.request.ControllerRequest;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;

/**
 * A controller used to handle live requests.
 */
public abstract class Controller {
    /**
     * Create a new controller instance.
     */
    public Controller() {

    }

    /**
     * Listen for when the controller is activated via a socket.
     * @param socket The socket that activated the controller.
     */
    public abstract void onActivate(ControllerSocket socket);

    /**
     * Listen for when the controller is deactivated via a socket ( usually when the socket is closed ).
     * @param socket The socket that deactivated the controller.
     */
    public abstract void onDeactivate(ControllerSocket socket);

    /**
     * Listen for when the active controller receives a request from the socket.
     * @param socket The socket that sent the request.
     * @param request The request that was received.
     */
    public abstract void onRequest(ControllerSocket socket, ControllerRequest request);
}
