package SavingPackage;

import FileHandler.Restaurant;
import FileHandler.Table;
import MainCode.Order_Quantity;
import MainCode.OrderedDish;
import Persons.Client;
import Persons.Person;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrepareSavingClient extends ThankyouScreen {
  private Client client;
  private float invoice;
  List<ClientOrder> orders = new ArrayList<>();

    public PrepareSavingClient(Restaurant restaurant, Person user, Table reservedTable, List<Order_Quantity> orderedDishes, float invoice) throws JAXBException {
        super(restaurant, user, reservedTable, orderedDishes);
        this.client = (Client) user;
        Stage finalStage = new Stage();
        VBox finalBox = super.showFinalScreen(finalStage);
        super.thankYouLabel.setText("Thank you, "+client.getName());
        super.specialMessage.setText("Have a nice Stay !");
        Scene finalScene = new Scene(finalBox,400,200);
        finalStage.setScene(finalScene);
        finalStage.show();
        this.invoice = invoice ;
        saveClient();
    }
    private void saveClient() throws JAXBException {


        JAXBContext jaxbContext = JAXBContext.newInstance(MainOrders.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        MainOrders rootFile = new MainOrders();

        ClientOrder saveOrder = new ClientOrder();
        SavedClient savedClient = new SavedClient();
        savedClient.setName(client.getName());
        savedClient.setRole("client");
        saveOrder.setClient(savedClient);

        ReservedTable savedTable = new ReservedTable();
        savedTable.setTableNumber(reservedTable.getNumber());
        savedTable.setSeatNumber(reservedTable.getNumber_of_seats());
        savedTable.isSmoking(reservedTable.isSmoking());
        saveOrder.setReservedTable(savedTable);
        saveOrder.setInvoice(invoice);

        SavedOrderedDishes savedOrderedDishes = new SavedOrderedDishes();
        savedOrderedDishes.setOrderedDishes(orderedDishes);
        saveOrder.setSavedOrderedDishes(savedOrderedDishes);

        File file = new File("SaveClient.xml");
        Boolean exists = file.exists();
        if (exists) orders = getPreviousOrder();

        orders.add(saveOrder);
        rootFile.setOrders(orders);



           marshaller.marshal(rootFile, new File("SaveClient.xml"));

    }
    private List<ClientOrder> getPreviousOrder() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(MainOrders.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        MainOrders rootInput = new MainOrders();
        rootInput = (MainOrders) unmarshaller.unmarshal(new File("SaveClient.xml") );
        return rootInput.getOrders();



    }

}
