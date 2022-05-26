package com.github.skylixgh.hello;

import java.awt.AWTException;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Float;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;

import net.skylix.elixor.desktop.generic.div.Div;
import net.skylix.elixor.desktop.presets.layout.BorderLayout;
import net.skylix.elixor.desktop.renderer.canvas.Canvas;
import net.skylix.elixor.desktop.renderer.color.Color;
import net.skylix.elixor.desktop.renderer.element.Element;
import net.skylix.elixor.desktop.renderer.gpu.GPU;
import net.skylix.elixor.desktop.renderer.gpu.Graphics;
import net.skylix.elixor.desktop.renderer.gpu.Renderer;
import net.skylix.elixor.desktop.unit.CornerRadius;
import net.skylix.elixor.desktop.unit.Location;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;

public class MyApp {
    private final Window window;

    public MyApp() {
        GPU.setHardwareAcceleration(true);
        window = new Window("Hello World", new Size(1000, 600));

        // Log debug information
        System.out.println("Refresh Rate: " + GPU.getFrameRate());

        final Div el = new Div();

        el.setSize(new Size(100, 200));
        el.setBackgroundColor(new Color(0, 255, 84, 255));

        for (int i = 0; i < 10; i++) {
            final Div e2 = new Div();

            e2.setSize(new Size(200, 20));
            e2.setBackgroundColor(new Color(255, 0, 0, 255));

            el.add(e2);
        }

        el.setLayoutEngine(new BorderLayout());

        window.setRootElement(el);
        window.run();
    }

    public static void main(String[] args) throws AWTException {
        new MyApp();
    }

    public Window getWindow() {
        return window;
    }
}
//import org.lwjgl.Sys;
//import org.lwjgl.glfw.*;
//import org.lwjgl.opengl.*;
//
//import java.nio.ByteBuffer;
//
//import static org.lwjgl.glfw.Callbacks.*;
//import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL11.*;
//import static org.lwjgl.system.MemoryUtil.*;
//
//public class MyApp {
//
//    // We need to strongly reference callback instances.
//    private GLFWErrorCallback errorCallback;
//    private GLFWKeyCallback   keyCallback;
//
//    // The window handle
//    private long window;
//
//    public void run() {
//        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
//
//        try {
//            init();
//            loop();
//
//            // Release window and window callbacks
//            glfwDestroyWindow(window);
//            keyCallback.release();
//        } finally {
//            // Terminate GLFW and release the GLFWerrorfun
//            glfwTerminate();
//            errorCallback.release();
//        }
//    }
//
//    private void init() {
//        // Setup an error callback. The default implementation
//        // will print the error message in System.err.
//        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
//
//        // Initialize GLFW. Most GLFW functions will not work before doing this.
//        if ( glfwInit() != GL11.GL_TRUE )
//            throw new IllegalStateException("Unable to initialize GLFW");
//
//        // Configure our window
//        glfwDefaultWindowHints(); // optional, the current window hints are already the default
//        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
//        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
//
//        int WIDTH = 300;
//        int HEIGHT = 300;
//
//        // Create the window
//        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
//        if ( window == NULL )
//            throw new RuntimeException("Failed to create the GLFW window");
//
//        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
//        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
//            @Override
//            public void invoke(long window, int key, int scancode, int action, int mods) {
//                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
//                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
//            }
//        });
//
//        // Get the resolution of the primary monitor
//        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//        // Center our window
//        glfwSetWindowPos(
//                window,
//                (GLFWvidmode.width(vidmode) - WIDTH) / 2,
//                (GLFWvidmode.height(vidmode) - HEIGHT) / 2
//        );
//
//        // Make the OpenGL context current
//        glfwMakeContextCurrent(window);
//        // Enable v-sync
//        glfwSwapInterval(1);
//
//        // Make the window visible
//        glfwShowWindow(window);
//    }
//
//    private void loop() {
//        // This line is critical for LWJGL's interoperation with GLFW's
//        // OpenGL context, or any context that is managed externally.
//        // LWJGL detects the context that is current in the current thread,
//        // creates the ContextCapabilities instance and makes the OpenGL
//        // bindings available for use.
//        GLContext.createFromCurrent();
//
//        // Set the clear color
//        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
//
//        // Run the rendering loop until the user has attempted to close
//        // the window or has pressed the ESCAPE key.
//        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
//            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
//
//            // Draw cube, left and right must be green, top and bottom must be red, and front and back must be blue, rotate 45 degrees
//            GL11.glColor3f(0, 255, 0);
//            GL11.glBegin(GL11.GL_QUADS);
//            GL11.glVertex3f(-1.0f, 1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, 1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, -1.0f, 0.0f);
//            GL11.glVertex3f(-1.0f, -1.0f, 0.0f);
//            GL11.glEnd();
//
//            GL11.glColor3f(255, 0, 0);
//            GL11.glBegin(GL11.GL_QUADS);
//            GL11.glVertex3f(-1.0f, 1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, 1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, 1.0f, -2.0f);
//            GL11.glVertex3f(-1.0f, 1.0f, -2.0f);
//            GL11.glEnd();
//
//            GL11.glColor3f(0, 0, 255);
//            GL11.glBegin(GL11.GL_QUADS);
//            GL11.glVertex3f(-1.0f, -1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, -1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, -1.0f, -2.0f);
//            GL11.glVertex3f(-1.0f, -1.0f, -2.0f);
//            GL11.glEnd();
//
//            GL11.glColor3f(255, 255, 0);
//            GL11.glBegin(GL11.GL_QUADS);
//            GL11.glVertex3f(-1.0f, 1.0f, 0.0f);
//            GL11.glVertex3f(-1.0f, -1.0f, 0.0f);
//            GL11.glVertex3f(-1.0f, -1.0f, -2.0f);
//            GL11.glVertex3f(-1.0f, 1.0f, -2.0f);
//            GL11.glEnd();
//
//            GL11.glColor3f(255, 0, 255);
//            GL11.glBegin(GL11.GL_QUADS);
//            GL11.glVertex3f(1.0f, 1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, -1.0f, 0.0f);
//            GL11.glVertex3f(1.0f, -1.0f, -2.0f);
//            GL11.glVertex3f(1.0f, 1.0f, -2.0f);
//            GL11.glEnd();
//
//            glfwSwapBuffers(window);
//            glfwPollEvents();
//        }
//    }
//
//    public static void main(String[] args) {
//        SharedLibraryLoader.load();
//        new MyApp().run();
//    }
//}