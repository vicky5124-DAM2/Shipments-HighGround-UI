package ins.marianao.shipments.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerShipmentsDirectory {
    @FXML
    private TableView<Shipments> shipmentsTable;
    @FXML
    private TableColumn<Shipments, String> colAddresseeName;

    @FXML
    private TableColumn<Shipments, String> colAssignedCourier;

    @FXML
    private TableColumn<Shipments, Integer> colCategory;

    @FXML
    private TableColumn<Shipments, Boolean> colDelivered;

    @FXML
    private TableColumn<Shipments, Integer> colPriority;

    @FXML
    private TableColumn<Shipments, String> colRecipientAddress;

    @FXML
    private TableColumn<Shipments, Integer> colTrackingId;

}
