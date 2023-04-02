package FileHandler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
    @XmlElement(name="number")
    private short number;
    @XmlElement(name="number_of_seats")
    private short number_of_seats;
    @XmlElement(name="smoking")
    private boolean smoking;
    private boolean booked = false;

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public short getNumber() {
        return number;
    }

    public short getNumber_of_seats() {
        return number_of_seats;
    }

    public boolean isSmoking() {
        return smoking;
    }
}
