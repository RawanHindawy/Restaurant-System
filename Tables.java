package FileHandler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.List;

@XmlRootElement(name = "tables")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tables {
    @XmlElement (name = "table")
    private List<Table> tables;

    public List<Table> getTables() {
        return tables;
    }
}
