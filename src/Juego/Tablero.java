package Juego;

import Estructura.ficha;
import Estructura.listaUsuario;
import Estructura.matrizOrtogonal;
import Estructura.usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.io.File;
import java.lang.Thread.State;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joseph
 */
public class Tablero implements Initializable {

    listaUsuario listaDeUsuario = new listaUsuario();
    matrizOrtogonal matriz = new matrizOrtogonal(Lectura.LeerXML.dimensionMatriz);

    @FXML
    private WebView webDiccionario;

    @FXML
    private JFXButton PrimerFicha;
    @FXML
    private JFXButton PrimerFicha1;

    @FXML
    private JFXButton PrimerFicha2;

    @FXML
    private JFXButton PrimerFicha3;

    @FXML
    private JFXButton PrimerFicha4;

    @FXML
    private JFXButton PrimerFicha5;

    @FXML
    private JFXButton PrimerFicha6;

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
      inicializarWeb();
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

    @FXML
    void agregarPalabra(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingrese la nueva palabra");
        dialog.setHeaderText("Ingresar palabra al diccionario:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            Lectura.LeerXML.ListaDiccionario.insertarAlPrincipio(result.get().toUpperCase());
        }

    }

    @FXML
    void btnPrueba2(ActionEvent event) {
        /*
        //Lectura.LeerXML.ListaDiccionario.actuaalizarDot();
//         WebEngine webEngine = webDiccionario.getEngine();
        String ruta = new File("").getAbsolutePath();
        ruta = "file://" + ruta + "/src/Images/Grafo/diccionario.html";
//        webEngine.load(ruta);
        System.out.println("boton");
        try {
            apProductos.getChildren().clear();
            //apCaja.getChildren().clear();
            WebView wu = new WebView();
            WebEngine web = wu.getEngine();
            ruta = "file://" + ruta + "/src/Images/Grafo/diccionario.html";
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(wu);

            web.load(ruta);

            apProductos.getChildren().add(scrollPane);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        */
        Lectura.LeerXML.ListaDiccionario.actuaalizarDot();
            webEngine.reload();
        

    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
        stagePrincipal.setMaximized(true);
    }

    @FXML
    void btnPrueba1(ActionEvent event) {//iniciar juego
        try {
            PrimerFicha.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);
            PrimerFicha1.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);
            PrimerFicha2.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);
            PrimerFicha3.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);
            PrimerFicha4.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);
            PrimerFicha5.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);
            PrimerFicha6.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("...");
            alert.setHeaderText("Fin del Juego");
            String s = "Ya no hay más letras en la cola";
            alert.setContentText(s);
            alert.show();
        }

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
                ficha fichaTablero = new ficha(j, i);
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
        PrimerFicha.setText("");
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
        PrimerFicha2.setText("");
    }

    @FXML
    void PrimerDrag1(MouseEvent event) {
        Dragboard db = PrimerFicha1.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(PrimerFicha1.getText());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void PrimerDone1(DragEvent event) {
        PrimerFicha1.setText("");
    }

    @FXML
    void PrimerDrag3(MouseEvent event) {
        Dragboard db = PrimerFicha3.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(PrimerFicha3.getText());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void PrimerDone3(DragEvent event) {
        PrimerFicha3.setText("");
    }

    @FXML
    void PrimerDrag4(MouseEvent event) {
        Dragboard db = PrimerFicha4.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(PrimerFicha4.getText());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void PrimerDone4(DragEvent event) {
        PrimerFicha4.setText("");
    }

    @FXML
    void PrimerDrag5(MouseEvent event) {
        Dragboard db = PrimerFicha5.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(PrimerFicha5.getText());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void PrimerDone5(DragEvent event) {
        PrimerFicha5.setText("");
    }

    @FXML
    void PrimerDrag6(MouseEvent event) {
        Dragboard db = PrimerFicha6.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(PrimerFicha6.getText());
        db.setContent(cb);
        event.consume();
    }

    @FXML
    void PrimerDone6(DragEvent event) {
        PrimerFicha6.setText("");
    }
WebEngine webEngine;
    //src/Images/Grafo/
    public void inicializarWeb() {
        webEngine=webDiccionario.getEngine();
        String ruta = new File("").getAbsolutePath();
        ruta = "file://" + ruta + "/src/Images/Grafo/diccionario.html";
        webEngine.load(ruta);
    }

}
