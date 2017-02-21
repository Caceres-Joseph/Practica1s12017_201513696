package Juego;

import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joseph
 */
public class Tablero implements Initializable {

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
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    @FXML
    private void clickTab(MouseEvent event) {
    }

    @FXML
    private void clickTabPane(MouseEvent event) {
    }
}
