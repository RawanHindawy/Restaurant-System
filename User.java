package FileHandler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "username")
    private String username;
    @XmlElement(name = "password")
    private String password;
    @XmlElement(name = "role")
    private String role;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
