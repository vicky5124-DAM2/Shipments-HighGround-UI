package ins.marianao.shipments.fxml.services;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFormShipment implements Initializable {

    @FXML
    private ComboBox<?> category;
    @FXML
    private TextField addresseeAddress;

    @FXML
    private TextField addresseeName;

    @FXML
    private TextField dimensions;

    @FXML
    private TextField priority;

    @FXML
    private TextField senderAddress;

    @FXML
    private TextField senderName;

    @FXML
    private TextField shipmentDescription;

    @FXML
    private TextField weight;

    @FXML
    private Button bCreate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
