package FileHandler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dish")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dish {
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="price")
    private float price;
    @XmlElement(name="type")
    private String type;
    @XmlElement(name = "quantity")
    private short quantity;

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}
