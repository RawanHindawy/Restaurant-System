package MainCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Dish")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedDish {
    @XmlElement (name = "name")
    private String name;
    @XmlElement(name = "price")
    private float price;
    @XmlElement(name = "type")
    private String type;

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
