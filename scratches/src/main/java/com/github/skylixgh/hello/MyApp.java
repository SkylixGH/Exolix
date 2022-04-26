package com.github.skylixgh.hello;

import com.sun.javafx.tk.TKStage;
import javafx.application.Application;
import javafx.stage.Stage;
import net.skylix.elixor.apiSocket.controller.Controller;
import net.skylix.elixor.apiSocket.controller.ControllerMessage;
import net.skylix.elixor.apiSocket.controller.request.ControllerRequest;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocket;
import net.skylix.elixor.apiSocket.controller.socket.ControllerSocketMessage;
import net.skylix.elixor.elixorFX.ElixorFX;
import net.skylix.elixor.terminal.logger.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class HelloWorldController extends Controller {
    private final ArrayList<ControllerSocket> sockets = new ArrayList<>();

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
//         ApiSocket server = new ApiSocket();

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

public class MyApp  {
    public static class Engine extends JPanel {
        private static class PlayerState {
            int x;
            int y;
            Integer[] rgb;
        }

        private ArrayList<PlayerState> players = new ArrayList<>();

        private boolean upPressed = false;
        private boolean downPressed = false;
        private boolean leftPressed = false;
        private boolean rightPressed = false;

        private Double speedOffset = 0.0;

        public Engine() {
            super();

            setFocusable(true);
            requestFocus();

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        upPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        downPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightPressed = true;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        upPressed = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        downPressed = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftPressed = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightPressed = false;
                    }

                    if (KeyEvent.VK_R == e.getKeyCode()) {
                        setPlayerPos(0, 0, 0);
                    }
                }
            });

            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException error) {
                        Logger.errorBase("Thread sleep failed!");
                    }

                    if (getGraphics() != null) {
                        int currentX = players.get(0).x;
                        int currentY = players.get(0).y;

                        if (upPressed && !downPressed) {
                            speedOffset = speedOffset + 0.1;

                            if (speedOffset > 20) {
                                speedOffset = 20.0;
                            }

                            currentY = (int) (currentY - speedOffset - 1);
                        } else if (downPressed && !upPressed) {
                            speedOffset = speedOffset + 0.1;

                            if (speedOffset > 20) {
                                speedOffset = 20.0;
                            }

                            currentY = (int) (currentY + speedOffset + 0.1);
                        }

                        System.out.println("Speed: " + speedOffset);
                        
                        if (leftPressed && !rightPressed) {
                            speedOffset = speedOffset + 0.05;

                            if (speedOffset > 20) {
                                speedOffset = 20.0;
                            }

                            currentX = (int) (currentX - speedOffset - 1);
                        } else if (rightPressed && !leftPressed) {
                            speedOffset = speedOffset + 0.1;

                            if (speedOffset > 20) {
                                speedOffset = 20.0;
                            }

                            currentX = (int) (currentX + speedOffset + 1);
                        }

                        if (!upPressed && !downPressed && !leftPressed && !rightPressed) {
                            speedOffset = 0.0;
                        }

                        setPlayerPos(currentX, currentY, 0);

                        repaint();
                    }
                }
            }).start();

            addPlayer(new PlayerState() {{
                x = 0;
                y = 0;
                rgb = randomRgb();
            }});

            addPlayer(new PlayerState() {{
                x = 30;
                y = 30;
                rgb = randomRgb();
            }});
        }

        public void setPlayerPos(int x, int y, int index) {
            players.get(index).x = x;
            players.get(index).y = y;

            repaint();
        }

        public void renderPlayer(int x, int y, Integer[] rgb, Graphics2D g) {
            g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
            g.fillRect(x, y, 20, 20);        
        }

        public void addPlayer(PlayerState player) {
            players.add(player);
            repaint();
        }

        public int randomInt(int min, int max) {
            return (int) (Math.random() * (max - min + 1)) + min;
        }

        public Integer[] randomRgb() {
            return new Integer[] {randomInt(0, 255), randomInt(0, 255), randomInt(0, 255)};
        }

        @Override
        public void paintComponent(Graphics g3d) {
            super.paintComponent(g3d);

            Graphics2D g = (Graphics2D) g3d;

            for (PlayerState player : players) {
                renderPlayer(player.x, player.y, player.rgb, g);
            }

            g.dispose();
        }
    }

    public static void main(String[] args) {
        JFrame gameRT = new JFrame("Game [Multiplayer 1.0.0]");
        Engine engine = new Engine();

        engine.setSize(1200, 600);
        engine.setBackground(new Color(20, 20, 20));
        gameRT.add(engine);
        gameRT.setSize(1200, 600);

        gameRT.setVisible(true);
        engine.requestFocusInWindow();
    }
}
