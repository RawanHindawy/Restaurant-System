package MainCode;

import FileHandler.Restaurant;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main extends Application {
    private static Restaurant restaurant;

    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
         restaurant = (Restaurant) unmarshaller.unmarshal(new File("Input.xml") );
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginScreen login = new LoginScreen(stage, restaurant);

    }
}
