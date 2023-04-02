package FileHandler;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement (name ="dishes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dishes {
    @XmlElement(name = "dish")
    private List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }
}
