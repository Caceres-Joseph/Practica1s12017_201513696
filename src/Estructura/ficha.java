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
public class ficha {

    public Label letra;
    public Integer valorCasilla;
    public int fila;
    public int columna;

    public ficha(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        crearLabel();
    }

    public void crearLabel() {
        letra = new Label();

        letra.setAlignment(Pos.CENTER);
        letra.setStyle("-fx-background-color:white;-fx-font: 25 arial; -fx-background-radius:0.2em;-fx-text-fill:#043761;-fx-font-weight: bold;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        letra.setEffect(dropShadow);
        letra.setPrefSize(50.0, 40.0);

        letra.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
            }
        });

        letra.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                String str = event.getDragboard().getString();
                letra.setText(str);
                System.out.println("LLego la letra");
            }
        });
    }

}
