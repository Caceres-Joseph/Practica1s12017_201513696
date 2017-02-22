package Juego;

import Estructura.ficha;
import Estructura.listaUsuario;
import Estructura.matrizOrtogonal;
import Estructura.usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joseph
 */
public class Tablero implements Initializable {

    listaUsuario listaDeUsuario = new listaUsuario();
    matrizOrtogonal matriz =new matrizOrtogonal(Lectura.LeerXML.dimensionMatriz);
    

    @FXML
    private JFXButton PrimerFicha;
    @FXML
    private JFXButton PrimerFicha1;

    @FXML
    private JFXButton PrimerFicha2;

    @FXML
    private JFXButton PrimerFicha3;

    @FXML
    private JFXButton PrimerFicha31;

    @FXML
    private JFXButton PrimerFicha32;

    @FXML
    private JFXButton PrimerFicha33;

    @FXML
    private ScrollPane panelFihcasTablero; //tabla en donde se encuentran las fichas

    private Stage stagePrincipal;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab tabProductos;
    @FXML
    private AnchorPane apProductos;
    @FXML
    private Tab tabNuevoProducto;
    @FXML
    private AnchorPane apNuevoProducto;
    @FXML
    private AnchorPane apCaja;
    @FXML
    private Tab tabProductos1;
    @FXML
    private AnchorPane apProductos1;
    @FXML
    private Tab tabProductos2;
    @FXML
    private AnchorPane apProductos2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        pintarCuadritos();
    }

    @FXML
    void agregarUsuario(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingrese el nombre del usuario");
        dialog.setHeaderText("Ingresar");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            usuario user = new usuario();
            user.nombre = result.get();
            user.puntuacion = 0;
            listaDeUsuario.insertarUsuario(user);
        }

        //pintarLabels();
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
        stagePrincipal.setMaximized(true);
    }

    @FXML
    void btnPrueba1(ActionEvent event) {//iniciar juego
        String fil=JOptionPane.showInputDialog("fila");
        Integer fila=Integer.valueOf(fil);
        String col=JOptionPane.showInputDialog("columna");
        Integer columna=Integer.valueOf(col);
        System.out.println(matriz.devolverFichaMatriz(fila,columna).letra.getText());
        
    }

    @FXML
    private void clickTab(MouseEvent event) {
    }

    @FXML
    private void clickTabPane(MouseEvent event) {
    }

    public void pintarCuadritos() {
        HBox horizontal = new HBox();
        BorderPane panelBorde = new BorderPane();
        horizontal.setSpacing(7.0);
        
        for (int i = 0; i < Lectura.LeerXML.dimensionMatriz; i++) {
            for (int j = 0; j < Lectura.LeerXML.dimensionMatriz; j++) {
                ficha fichaTablero=new ficha(j, i);
                matriz.insertarElementoEnPosicion(j, i, fichaTablero);
            }
        }
        
        for (int j = 0; j < Lectura.LeerXML.dimensionMatriz; j++) {
            VBox verticalSupremo = new VBox();
            verticalSupremo.setSpacing(7.0);
            for (int k = 0; k < Lectura.LeerXML.dimensionMatriz; k++) {
                
                verticalSupremo.getChildren().add(matriz.devolverFichaMatriz(k, j).letra);
            }
            horizontal.setAlignment(Pos.CENTER);
            horizontal.getChildren().add(verticalSupremo);
        }
        
        panelBorde.setCenter(horizontal);
        panelFihcasTablero.setContent(panelBorde);

    }

    public void pintarLabels() {
        HBox horizontal = new HBox();
        BorderPane panelBorde = new BorderPane();
//        panelBorde.prefHeightProperty().bind(panelFihcasTablero.heightProperty());
        horizontal.setSpacing(7.0);
        for (int j = 0; j < 13; j++) {
            VBox verticalSupremo = new VBox();
            verticalSupremo.setSpacing(7.0);
            for (int k = 0; k < 13; k++) {
                Label boton = new Label("");

                boton.setAlignment(Pos.CENTER);
                boton.setStyle("-fx-background-color:white;-fx-font: 25 arial; -fx-background-radius:0.2em;-fx-text-fill:#043761;-fx-font-weight: bold;");
                DropShadow dropShadow = new DropShadow();
                dropShadow.setRadius(5.0);
                dropShadow.setOffsetX(3.0);
                dropShadow.setOffsetY(3.0);
                dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
                boton.setEffect(dropShadow);
                boton.setPrefSize(50.0, 40.0);

                boton.setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        if (event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.ANY);
                        }
                    }
                });

                boton.setOnDragDropped(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        String str = event.getDragboard().getString();
                        boton.setText(str);
                        System.out.println("LLego la letra");
                    }
                });

                verticalSupremo.getChildren().add(boton);

            }
            horizontal.setAlignment(Pos.CENTER);
            horizontal.getChildren().add(verticalSupremo);
        }
        Label etri = new Label("hola");
        //panelBorde.setRight(etri);
        panelBorde.setLeft(etri);

        panelBorde.setCenter(horizontal);
        panelFihcasTablero.setContent(panelBorde);

    }

    /**
     * ******************
     *                  *
     * Fichas de la mano*
     */
    @FXML
    void PrimerDrag(MouseEvent event) {
        Dragboard db = PrimerFicha.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(PrimerFicha.getText());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void PrimerDone(DragEvent event) {
        System.out.println("se completo");
    }

    @FXML
    void PrimerDrag2(MouseEvent event) {
        Dragboard db = PrimerFicha2.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(PrimerFicha2.getText());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void PrimerDone2(DragEvent event) {
        System.out.println("se completo");
    }

}
