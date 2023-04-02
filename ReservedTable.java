package SavingPackage;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Table")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservedTable {
    @XmlElement(name = "TableNumber")
    private short tableNumber;
    @XmlElement(name = "Numberofseats")
    private short seatNumber;
    @XmlElement(name = "IsSmoking")
    private boolean smoking;

    public short getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(short tableNumber) {
        this.tableNumber = tableNumber;
    }

    public short getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(short seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isSmoking(boolean smoking) {
        return this.smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }
}
