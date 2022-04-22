import net.skylix.elixor.apiSocket.APISocket;
import net.skylix.elixor.apiSocket.controller.Controller;
import net.skylix.elixor.apiSocket.controller.ControllerMessage;
import net.skylix.elixor.apiSocket.controller.request.ControllerRequest;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocketMessage;
import net.skylix.elixor.terminal.logger.Logger;

class HelloWorldController extends Controller {
    /**
     * Create a new controller instance.
     *
     * @param channel      The name of the controller channel path.
     * @param messageClass The response and request messages data class.
     */
    public HelloWorldController(String channel, ControllerMessage messageClass) {
        super(channel, messageClass);
    }

    @Override
    public void onActivate(ControllerSocket socket) {
        Logger.infoBase("New connection!");
    }

    @Override
    public void onDeactivate(ControllerSocket socket) {
        Logger.infoBase("Connection closed!");
    }

    @Override
    public void onRequest(ControllerSocket socket, ControllerRequest request) {
        Logger.infoBase("Request received! MESSAGE = " + request.get("message"));
        ControllerSocketMessage response = new ControllerSocketMessage();

        String message = request.get("message");

        if (message == null) {
            ControllerSocketMessage err = new ControllerSocketMessage();
            err.set("reason", "No message provided!");

            socket.send(err);
            return;
        }

        String[] args = message.split(" ");

        response.set("greeting", args[0]);
        response.set("user", args[1]);

        socket.send(response);
    }

    public static class ServerSideMessage {
        public String user;
        public String greeting;
    }

    public static class ClientSideMessage {
        public String message;
    }
}

class Main {
    public static void main(String[] args) {
        Logger.infoBase("Starting Elixor API Socket Server...");
        APISocket server = new APISocket();

        HelloWorldController helloWorldController = new HelloWorldController("helloWorld", new ControllerMessage(HelloWorldController.ServerSideMessage.class, HelloWorldController.ClientSideMessage.class));
        server.connectController(helloWorldController);

        try {
            server.run();
            Logger.successBase("Elixor API Socket Server started!");
        } catch (Exception error) {
            Logger.errorBase("Elixor API Socket Server failed to start!");

            for (String line : error.getMessage().split("\n")) {
                Logger.errorBase(line);
            }
        }
    }
}