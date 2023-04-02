package FileHandler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "restaurant")
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {
@XmlElement(name = "users")
    private Users users = null;

@XmlElement(name = "tables")
    private Tables tables = null;

@XmlElement(name = "dishes")
private Dishes dishes = null;

    public Dishes getDishes() {
        return dishes;
    }

    public Users getUsers() {
        return users;
    }

    public Tables getTables() {
        return tables;
    }
}
