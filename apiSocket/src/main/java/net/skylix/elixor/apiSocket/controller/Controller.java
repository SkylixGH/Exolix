package net.skylix.elixor.apiSocket.controller;

import net.skylix.elixor.apiSocket.controller.request.ControllerRequest;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocketMessage;

import java.util.HashMap;

/**
 * A controller used to handle live requests.
 */
public abstract class Controller {
    /**
     * The name of the controller channel path.
     */
    private String channel;

    /**
     * Class info for message communication.
     */
    private ControllerMessage messageInfo;

    /**
     * Create a new controller instance.
     * @param channel The name of the controller channel path.
     * @param messageClass The response and request messages data class.
     */
    public Controller(String channel, ControllerMessage messageClass) {
        this.channel = channel;
        this.messageInfo = messageClass;
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

    /**
     * Get the routing channel name for this controller.
     * @return The routing channel name for this controller.
     */
    public String getChannelName() {
        return channel;
    }

    /**
     * Get message meta info.
     * @return The message meta info.
     */
    public ControllerMessage getMessageClass() {
        return messageInfo;
    }
}
