package ins.marianao.shipments.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import cat.institutmarianao.shipmentsws.model.Shipment;

public class ControllerShipmentsDirectory {
    @FXML
    private TableView<Shipment> shipmentsTable;
    @FXML
    private TableColumn<Shipment, String> colAddresseeName;

    @FXML
    private TableColumn<Shipment, String> colAssignedCourier;

    @FXML
    private TableColumn<Shipment, Integer> colCategory;

    @FXML
    private TableColumn<Shipment, Boolean> colDelivered;

    @FXML
    private TableColumn<Shipment, Integer> colPriority;

    @FXML
    private TableColumn<Shipment, String> colRecipientAddress;

    @FXML
    private TableColumn<Shipment, Integer> colTrackingId;

}
