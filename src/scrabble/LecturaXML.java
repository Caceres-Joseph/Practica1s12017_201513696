package scrabble;

import Estructura.colaLetra;
import Estructura.letra;
import Estructura.listaDiccionario;
import Estructura.listaUsuario;
import Estructura.usuario;
import Juego.Tablero;
import Lectura.LeerXML;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joseph
 */
public class LecturaXML implements Initializable {

    // colaLetra colaLetra1 = new colaLetra();
    listaDiccionario listaTemporalDeLetras = new listaDiccionario();
    public static colaLetra colaDeLetras = new colaLetra();

    @FXML
    private JFXButton btnLeer;

    @FXML
    private JFXButton btnPlay;
    LeerXML leer;
    listaUsuario temp = new listaUsuario();

    @FXML
    void btnAccionPlay(ActionEvent event) throws IOException {
//        System.out.println(listaDeUsuario.avanzarUnEspacio().nombre);
        abrirTablero();

    }

    @FXML
    void accionLeer(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(Scrabble.sta);
        leer = new LeerXML(String.valueOf(file));
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image filtro = new Image(getClass().getResourceAsStream("/Images/play.png"), 48, 48, false, false);
        btnPlay.setGraphic(new ImageView(filtro));

        Image clip = new Image(getClass().getResourceAsStream("/Images/clip24.png"), 24, 24, false, false);
        btnLeer.setGraphic(new ImageView(clip));
        llenarLaColaDeLetras();
        

    }

    public void abrirTablero() {
        try {
            //AnchorPane p = FXMLLoader.load(getClass().getResource("/venta_1/" + a));
            FXMLLoader loader = new FXMLLoader(Scrabble.class.getResource("/Juego/Tablero.fxml"));

            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Venta Dos");
            ventana.initOwner(Scrabble.sta);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
           
            Tablero controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("...");
            alert.setHeaderText("Falta la entrada de texto");
            String s = "No se ha leído el archivo, por favor introduzacalo para poder jugar";
            alert.setContentText(s);
            alert.show();
        }
    }

    public void llenarLaColaDeLetras() {
        meterLetras();

        Integer longitudDeListaTemporal = listaTemporalDeLetras.longitud;
        for (int i = 0; i < longitudDeListaTemporal; i++) {
            int numero = ThreadLocalRandom.current().nextInt(0, listaTemporalDeLetras.longitud);
            letra nuevaLetra = new letra(listaTemporalDeLetras.pop(numero));
            colaDeLetras.push(nuevaLetra);
        }
    }

    public void meterLetras() {
        for (int i = 0; i < 12; i++) {
            listaTemporalDeLetras.insertarAlPrincipio("A");
            listaTemporalDeLetras.insertarAlPrincipio("E");
        }
        for (int i = 0; i < 9; i++) {
            listaTemporalDeLetras.insertarAlPrincipio("O");
        }
        for (int i = 0; i < 6; i++) {
            listaTemporalDeLetras.insertarAlPrincipio("I");
            listaTemporalDeLetras.insertarAlPrincipio("S");
        }
        for (int i = 0; i < 5; i++) {
            listaTemporalDeLetras.insertarAlPrincipio("N");
            listaTemporalDeLetras.insertarAlPrincipio("R");
            listaTemporalDeLetras.insertarAlPrincipio("U");
            listaTemporalDeLetras.insertarAlPrincipio("D");

        }
        for (int i = 0; i < 4; i++) {
            listaTemporalDeLetras.insertarAlPrincipio("L");
            listaTemporalDeLetras.insertarAlPrincipio("T");
            listaTemporalDeLetras.insertarAlPrincipio("C");

        }
        for (int i = 0; i < 2; i++) {
            listaTemporalDeLetras.insertarAlPrincipio("G");
            listaTemporalDeLetras.insertarAlPrincipio("B");
            listaTemporalDeLetras.insertarAlPrincipio("M");
            listaTemporalDeLetras.insertarAlPrincipio("P");
            listaTemporalDeLetras.insertarAlPrincipio("H");

        }
        listaTemporalDeLetras.insertarAlPrincipio("F");
        listaTemporalDeLetras.insertarAlPrincipio("V");
        listaTemporalDeLetras.insertarAlPrincipio("Y");
        listaTemporalDeLetras.insertarAlPrincipio("Q");
        listaTemporalDeLetras.insertarAlPrincipio("J");
        listaTemporalDeLetras.insertarAlPrincipio("Ñ");
        listaTemporalDeLetras.insertarAlPrincipio("X");
        listaTemporalDeLetras.insertarAlPrincipio("Z");
    }

}
