package MainCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order_Quantity {
    @XmlElement(name = "dish")
    private OrderedDish dish;
    @XmlElement(name = "quantity")
    private short quantity=0;

    public Order_Quantity() {

    }

    public OrderedDish getDish() {
        return dish;
    }

    public void setDish(OrderedDish dish) {
        this.dish = dish;
    }

    public short getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity ++;
    }
}
