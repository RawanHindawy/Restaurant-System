package SavingPackage;

import MainCode.Order_Quantity;
import MainCode.OrderedDish;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class SavedOrderedDishes {
    @XmlElement(name = "Dish")
    private List<Order_Quantity> orderedDishes;

    public List<Order_Quantity> getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(List<Order_Quantity> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }
}
