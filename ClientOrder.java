package SavingPackage;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ClientOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientOrder {
    @XmlElement(name = "Client")
    private SavedClient client;

    @XmlElement(name = "Table")
    private ReservedTable reservedTable;

    @XmlElement(name = "Order")
    private SavedOrderedDishes savedOrderedDishes;

    @XmlElement(name ="Invoice")
    private float invoice;


    public SavedClient getClient() {
        return client;
    }

    public void setClient(SavedClient client) {
        this.client = client;
    }

    public ReservedTable getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(ReservedTable reservedTable) {
        this.reservedTable = reservedTable;
    }

    public SavedOrderedDishes getSavedOrderedDishes() {
        return savedOrderedDishes;
    }

    public void setSavedOrderedDishes(SavedOrderedDishes savedOrderedDishes) {
        this.savedOrderedDishes = savedOrderedDishes;
    }

    public float getInvoice() {
        return invoice;
    }

    public void setInvoice(float invoice) {
        this.invoice = invoice;
    }
}
