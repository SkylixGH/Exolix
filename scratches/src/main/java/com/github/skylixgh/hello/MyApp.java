package com.github.skylixgh.hello;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import net.skylix.elixor.desktop.Desktop;
import net.skylix.elixor.desktop.errors.WindowAlreadyRunning;
import net.skylix.elixor.desktop.ux.uxButton.UXButton;
import net.skylix.elixor.desktop.ux.uxComponent.UXComponent;
import net.skylix.elixor.terminal.color.errors.InvalidHexCode;

import javax.swing.*;
import java.net.URISyntaxException;

public class MyApp {
    public static void main(String[] args) throws URISyntaxException, WindowAlreadyRunning, InvalidHexCode {
        Desktop window = new Desktop();
        window.setRootElement(new UXButton("Hey"));
        window.run();
    }
}
