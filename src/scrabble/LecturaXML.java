package scrabble;

import Estructura.listaDiccionario;
import Juego.Tablero;
import Lectura.LeerXML;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    private JFXButton btnLeer;

    @FXML
    private JFXButton btnPlay;
    LeerXML leer;

    @FXML
    void btnAccionPlay(ActionEvent event) throws IOException {
//        System.out.println(LeerXML.ListaDiccionario.buscar("primera"));
        try {
            //AnchorPane p = FXMLLoader.load(getClass().getResource("/venta_1/" + a));
            FXMLLoader loader = new FXMLLoader(Scrabble.class.getResource("/Scrabble/Juego/Tablero.fxml"));
           
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
            System.out.println(e);
        }

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
    }
}
