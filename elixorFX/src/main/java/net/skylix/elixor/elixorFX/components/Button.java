package net.skylix.elixor.elixorFX.components;

import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Button extends VBox {
    public Button() {
        super();
        
        Text text = new Text("Button");
        text.setFill(new Color(1, 1, 1, 1));
        getChildren().add(text);
        
        setStyle("-fx-background-color: #000; -fx-border-radius: 4px 4px 4px 4px;");
        setPrefSize(100, 30);
        setMaxHeight(30);
        setAlignment(Pos.CENTER);
    }
}
