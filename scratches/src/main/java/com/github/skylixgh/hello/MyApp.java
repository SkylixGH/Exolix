package com.github.skylixgh.hello;

import net.skylix.elixor.desktop.component.WindowDragState;
import net.skylix.elixor.desktop.component.div.Div;
import net.skylix.elixor.desktop.engines.layout.BorderLayout;
import net.skylix.elixor.desktop.events.MouseEvent;
import net.skylix.elixor.desktop.system.microsoft.windowsUtil.MicrosoftWindowsController;
import net.skylix.elixor.desktop.unit.BorderRadius;
import net.skylix.elixor.desktop.unit.Position;
import net.skylix.elixor.desktop.unit.Size;
import net.skylix.elixor.desktop.window.Window;
import net.skylix.elixor.desktop.window.WindowAdapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;

public class MyApp {
    private static MicrosoftWindowsController wc = null;
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        final Window window = new Window("Elixor");
 
        configureWindow(window);

        final Div div = new Div();

        div.disableEdgeClipping();

        div.setSize(new Size(window.getWidth() - 16, window.getHeight() - 39));

        final Div titleBar = new Div();

        titleBar.setSize(new Size(window.getWidth(), 32));
        titleBar.setPosition(new Position(0, 0));
        titleBar.setBackgroundColor(new Color(32, 32, 32));

        final Div closeButton = new Div();
 
        titleBar.add(closeButton);
        titleBar.disableEdgeClipping();

        closeButton.setPosition(new Position(
            window.getWidth() - 45, 
            0
        ));

        final ArrayList<Div> bl = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            final Div div2 = new Div();

            div2.setSize(new Size(i / 4, 10));
            div2.setBackgroundColor(new Color(83, 150, 255));

            div.add(div2);
            bl.add(div2);
        }

        final Window ctx = new Window("");

        titleBar.setWindowDragState(WindowDragState.ALLOW);
        closeButton.setWindowDragState(WindowDragState.DENY);

        div.setPosition(new Position(20, 20));

        closeButton.setSize(new Size(45, 32));

        ctx.setSize(new Size(350, 400));

        Div ctxb = new Div();
        ctx.add(ctxb);
        ctx.disableResizeSupport();
        ctx.enableAlwaysOnTop();
        ctx.addListener(new WindowAdapter() {
            @Override
            public void onResize() {
                ctxb.setSize(new Size(ctx.getWidth(), ctx.getHeight()));
            }
        });

        AtomicInteger frame = new AtomicInteger(2);
        int maxFrameI = 3;

        final ArrayList<Div> leds = new ArrayList<>() {{
            add(createLED());
            add(createLED());
            add(createLED());
        }};

        ctxb.setLayoutEngine(new BorderLayout());

        ctxb.add(leds.get(0));
        ctxb.add(leds.get(1));
        ctxb.add(leds.get(2));

        ctx.add(ctxb);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (frame.get() > maxFrameI) {
                    frame.set(0);
                }

                for (Div l : leds) {
                    l.setBackgroundColor(new Color(0, 0, 0));
                }

                if (frame.get() == 0) {
                    leds.get(0).setBackgroundColor(new Color(24, 150, 255));
                } else if (frame.get() == 1) {
                    leds.get(1).setBackgroundColor(new Color(24, 150, 255));
                } else if (frame.get() == 2) {
                    leds.get(2).setBackgroundColor(new Color(24, 150, 255));
                }

                frame.getAndIncrement();
            }
        }).start();

//        ctx.setAlwaysOnTop(true);

        window.addListener(new WindowAdapter() {
            @Override
            public void onMouseInteract(MouseEvent event) {
                if (closeButton.isMouseOver()) {
                    closeButton.setBackgroundColor(new Color(255, 0, 0));

                    if (event.pressed()) {
                        window.stop();
                    }
                } else {
                    closeButton.setBackgroundColor(new Color(32, 32, 32));
                }

                if (!titleBar.isMouseOver() && !closeButton.isMouseOver() && event.pressed()) {
                    ctx.moveToCenter();
                    ctx.run();
                } else {
//                    ctx.setVisible(false);
                }
            }

            @Override
            public void onResize() {
                titleBar.setSize(new Size(window.getWidth(), 32));
                closeButton.setPosition(new Position(
                    window.getWidth() - 45,
                    0
                ));
            }
        });

        closeButton.setBackgroundColor(new Color(255, 83, 20));

        window.add(titleBar); 

        window.add(div);
        window.run();

        wc = window.getOSWindowsController();
    }

    private static void configureWindow(Window win) {
        win.setSize(new Size(800, 500));
    }

    private static Div createLED() {
        final Div led = new Div();

        led.setSize(new Size(20, 20));
        led.setBackgroundColor(new Color(0, 0, 0));
        led.setBorderRadius(new BorderRadius(50));

        return led;
    }
}
 