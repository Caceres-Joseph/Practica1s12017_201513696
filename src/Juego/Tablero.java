package Juego;

import Estructura.ficha;
import Estructura.letra;
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
import javafx.application.Platform;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
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

    public static WebView webDiccionarioS;
    public static WebView webFichaS;
    public static WebView webTableroS;
    public static WebView webColaS;
    public static WebView webJugadoresS;

    public static JFXButton PrimerFichaS;
    public static JFXButton PrimerFicha1S;
    public static JFXButton PrimerFicha2S;
    public static JFXButton PrimerFicha3s;
    public static JFXButton PrimerFicha4s;
    public static JFXButton PrimerFicha5s;
    public static JFXButton PrimerFicha6s;

    @FXML
    private TextArea textUsuarios;

    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblScore;

    @FXML
    private WebView webDiccionario;

    @FXML
    private WebView webFicha;

    @FXML
    private WebView webTablero;

    @FXML
    private WebView webCola;

    @FXML
    private WebView webJugadores;

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
        webDiccionarioS = webDiccionario;
        webFichaS = webFicha;
        webTableroS = webTablero;
        webColaS = webCola;
        webJugadoresS = webJugadores;

        //botones estaticos
        PrimerFichaS = PrimerFicha;
        PrimerFicha1S = PrimerFicha1;
        PrimerFicha2S = PrimerFicha2;
        PrimerFicha3s = PrimerFicha3;
        PrimerFicha4s = PrimerFicha4;
        PrimerFicha5s = PrimerFicha5;
        PrimerFicha6s = PrimerFicha6;
        //scrabble.LecturaXML.colaDeLetras.actualizarWeb();
        // TODO
        pintarCuadritos();
        inicializarWebs();

        //inicializar los reportes
//        Lectura.LeerXML.ListaDiccionario.actualizarWeb();
//        Lectura.LeerXML.colaLetra1.actualizarWeb();
//        listaDeUsuario.actualizarWeb();
//        matriz.actualizarWeb();
    }

    @FXML
    void agregarUsuario(ActionEvent event) {
        if (contadorUsuario == 0) {

        } else if (contadorUsuario == 1) {
            listaDeUsuario.inicicializarAux();
        }
        contadorUsuario++;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingrese el nombre del usuario");
        dialog.setHeaderText("Ingresar");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            usuario user = new usuario();
            user.nombre = result.get();
            user.puntuacion = 0;
            lblUsuario.setText(user.nombre);
            lblScore.setText(String.valueOf(user.puntuacion));
            listaDeUsuario.insertarUsuario(user);

        }
        listaDeUsuario.actualizarWeb();

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
        Lectura.LeerXML.ListaDiccionario.actualizarWeb();
    }

    @FXML
    void btnPrueba2(ActionEvent event) {
//        listaDeUsuario.inicicializarAux();

       // textUsuarios.setText("Esta cadena es demasiado larga y no deberia de caber\n");
        textUsuarios.setText(listaDeUsuario.cadenaUsuario());
    }

    @FXML
    void btnPrueba3(ActionEvent event) {
        //matriz.imprimir();
        //matriz.actualizarWeb();
//        
//     System.out.println(Lectura.LeerXML.ListaDiccionario.buscar(matriz.buscarPalabraHorizontal()));
        //  System.out.println("Los puntos de la palabra son: " + String.valueOf(matriz.buscarPalabraHorizontal()));
        matriz.actualizarWeb();
//     System.out.println(Lectura.LeerXML.ListaDiccionario.buscar("JHOSEF"));

    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
        stagePrincipal.setMaximized(true);
    }

    public void agregarLetrasABotones() {
        String palabra;
        try {
            palabra = scrabble.LecturaXML.colaDeLetras.pop().letra;
            PrimerFicha.setText(palabra);
            listaDeUsuario.puntero.usuario1.listaDeLetras.insertarDeUltimo(palabra);
            palabra = scrabble.LecturaXML.colaDeLetras.pop().letra;
            PrimerFicha1.setText(palabra);
            listaDeUsuario.puntero.usuario1.listaDeLetras.insertarDeUltimo(palabra);
            palabra = scrabble.LecturaXML.colaDeLetras.pop().letra;
            PrimerFicha2.setText(palabra);
            listaDeUsuario.puntero.usuario1.listaDeLetras.insertarDeUltimo(palabra);
            palabra = scrabble.LecturaXML.colaDeLetras.pop().letra;
            PrimerFicha3.setText(palabra);
            listaDeUsuario.puntero.usuario1.listaDeLetras.insertarDeUltimo(palabra);
            palabra = scrabble.LecturaXML.colaDeLetras.pop().letra;
            PrimerFicha4.setText(palabra);
            listaDeUsuario.puntero.usuario1.listaDeLetras.insertarDeUltimo(palabra);
            palabra = scrabble.LecturaXML.colaDeLetras.pop().letra;
            PrimerFicha5.setText(palabra);
            listaDeUsuario.puntero.usuario1.listaDeLetras.insertarDeUltimo(palabra);
            palabra = scrabble.LecturaXML.colaDeLetras.pop().letra;
            PrimerFicha6.setText(palabra);
            listaDeUsuario.puntero.usuario1.listaDeLetras.insertarDeUltimo(palabra);

            listaDeUsuario.puntero.usuario1.listaDeLetras.actualizarWeb1();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("...");
            alert.setHeaderText("Fin del Juego");
            String s = "Ya no hay más letras en la cola";
            alert.setContentText(s);
            alert.show();
        }
        scrabble.LecturaXML.colaDeLetras.actualizarWeb();
    }

    @FXML
    void btnPrueba1(ActionEvent event) {//iniciar juego
        agregarLetrasABotones();
         textUsuarios.setText(listaDeUsuario.cadenaUsuario());
         matriz.actualizarWeb();
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
    WebEngine weDiccionario;
    WebEngine weFicha;
    WebEngine weTablero;
    WebEngine weCola;
    WebEngine weJugadores;

    //WebEngine we
    //src/Images/Grafo/
    public void inicializarWebs() {

        String ruta = new File("").getAbsolutePath();

        weDiccionario = webDiccionario.getEngine();
        weDiccionario.load("file://" + ruta + "/src/Images/Grafo/diccionario.html");

        weFicha = webFicha.getEngine();
        weFicha.load("file://" + ruta + "/src/Images/Grafo/ficha.html");

        weTablero = webTablero.getEngine();
        weTablero.load("file://" + ruta + "/src/Images/Grafo/tablero.html");

        weCola = webCola.getEngine();
        weCola.load("file://" + ruta + "/src/Images/Grafo/cola.html");

        weJugadores = webJugadores.getEngine();
        weJugadores.load("file://" + ruta + "/src/Images/Grafo/jugadores.html");

    }

    Integer contadorUsuario = 0;

    @FXML
    void validarTiro(ActionEvent event) {
//        lblScore.setText(String.valueOf(listaDeUsuario.avanzarEspacio(matriz.buscarPalabraHorizontal())));
//        
//        System.out.println("Los puntos de la palabra son: " + String.valueOf(matriz.buscarPalabraHorizontal()));

//    listaDeUsuario.avanzarUno();
//    lblUsuario.setText(listaDeUsuario.inicio.usuario1.nombre);
        lblUsuario.setText(listaDeUsuario.puntero.usuario1.nombre);
        lblScore.setText(String.valueOf(listaDeUsuario.avanzarEspacio(matriz.buscarPalabraHorizontal())));
        agregarLetrasABotones();
//            System.out.println(listaDeUsuario.avanzarEspacio(10));
 textUsuarios.setText(listaDeUsuario.cadenaUsuario());
matriz.actualizarWeb();
    }

    @FXML
    void cancelarTiro(ActionEvent event) {
        matriz.cancelarTiro();
         textUsuarios.setText(listaDeUsuario.cadenaUsuario());
         matriz.actualizarWeb();
    }

    @FXML
    void clickPrimerFicha(ActionEvent event) {
    }

    @FXML
    void MousePrimerFicha(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cambio");
            alert.setHeaderText("Seguro que desea cambiar la ficha actual?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cambio(PrimerFicha);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    void MousePrimerFicha1(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cambio");
            alert.setHeaderText("Seguro que desea cambiar la ficha actual?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cambio(PrimerFicha1);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    void MousePrimerFicha2(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cambio");
            alert.setHeaderText("Seguro que desea cambiar la ficha actual?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cambio(PrimerFicha2);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    void MousePrimerFicha3(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cambio");
            alert.setHeaderText("Seguro que desea cambiar la ficha actual?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cambio(PrimerFicha3);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    void MousePrimerFicha4(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cambio");
            alert.setHeaderText("Seguro que desea cambiar la ficha actual?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cambio(PrimerFicha4);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    void MousePrimerFicha5(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cambio");
            alert.setHeaderText("Seguro que desea cambiar la ficha actual?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cambio(PrimerFicha5);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    @FXML
    void MousePrimerFicha6(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cambio");
            alert.setHeaderText("Seguro que desea cambiar la ficha actual?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cambio(PrimerFicha6);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    public void cambio(JFXButton boton) {
//        scrabble.LecturaXML.colaDeLetras.pop().letra
        letra letr = new letra();

        String letra = boton.getText();
//                scrabble.LecturaXML.colaDeLetras.pop().letra;
        letr.letra = letra;

        scrabble.LecturaXML.colaDeLetras.push(letr);

        boton.setText(scrabble.LecturaXML.colaDeLetras.pop().letra);

        scrabble.LecturaXML.colaDeLetras.actualizarWeb();
    }

    @FXML
    void fin(ActionEvent event) {
        System.out.println("---------" + listaDeUsuario.puntero.usuario1.nombre + "---------");
        listaDeUsuario.puntero.usuario1.listaDeLetras.imprimir();
    }

}
