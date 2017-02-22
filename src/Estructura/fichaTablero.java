package Estructura;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

/**
 *
 * @author joseph
 */
public class fichaTablero {

    public Label label;
    

    public void metodo() {
        Label label = new Label("");

        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color:white;-fx-font: 25 arial; -fx-background-radius:0.2em;-fx-text-fill:#043761;-fx-font-weight: bold;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        label.setEffect(dropShadow);
        label.setPrefSize(50.0, 40.0);

        label.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
            }
        });

        label.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                String str = event.getDragboard().getString();
                label.setText(str);
                System.out.println("LLego la letra");
            }
        });
    }
}
