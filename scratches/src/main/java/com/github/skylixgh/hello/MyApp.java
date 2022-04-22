package com.github.skylixgh.hello;

import net.skylix.elixor.apiSocket.APISocket;
import net.skylix.elixor.apiSocket.controller.Controller;
import net.skylix.elixor.apiSocket.controller.ControllerMessage;
import net.skylix.elixor.apiSocket.controller.request.ControllerRequest;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocketMessage;
import net.skylix.elixor.terminal.logger.Logger;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import net.skylix.elixor.elixorFX.components.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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

    private ArrayList<ControllerSocket> sockets = new ArrayList<>();

    @Override
    public void onActivate(ControllerSocket socket) {
        sockets.add(socket);
        Logger.infoBase("New connection! TOTAL = " + sockets.size());
    }

    @Override
    public void onDeactivate(ControllerSocket socket) {
        sockets.remove(socket);
        Logger.infoBase("Connection closed! TOTAL = " + sockets.size());
    }

    @Override
    public void onRequest(ControllerSocket socket, ControllerRequest request) {
        Logger.infoBase("Request received! MESSAGE = " + request.get("message"));
        ControllerSocketMessage response = new ControllerSocketMessage();

        String message = request.get("message");

        if (message == null) {
            return;
        }

        response.set("message", message);
        response.set("user", "Unknown");

        for (ControllerSocket socketInstance : sockets) {
            socketInstance.send(response);
        }
    }

    public static class ServerSideMessage {
        public String user;
        public String greeting;
    }

    public static class ClientSideMessage {
        public String message;
    }
}

// class Main {
//     public void main() throws IOException {
//         Logger.infoBase("Starting Elixor API Socket Server...");
//         APISocket server = new APISocket();

//         HelloWorldController helloWorldController = new HelloWorldController("message", new ControllerMessage(HelloWorldController.ServerSideMessage.class, HelloWorldController.ClientSideMessage.class));
//         server.connectController(helloWorldController);

//         try {
//             server.run();
//             Logger.successBase("Elixor API Socket Server started!");
//         } catch (Exception error) {
//             Logger.errorBase("Elixor API Socket Server failed to start!");

//             for (String line : error.getMessage().split("\n")) {
//                 Logger.errorBase(line);
//             }
//         }
//     }

//     private static void handleHTTPConn(Socket conn) throws IOException {
//         BufferedOutputStream dataOut = new BufferedOutputStream(conn.getOutputStream());
//         dataOut.write("HTTP/1.1 200 OK\r\n".getBytes());
//         dataOut.write("Content-Type: text/html\r\n".getBytes());
//         dataOut.write("\r\n".getBytes());
//         dataOut.write("<html><body>Hello World!</body></html>".getBytes());
//         dataOut.flush();
//         conn.close();
//     }
// }

public class MyApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        
        HBox root = new HBox();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
