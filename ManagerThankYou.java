package SavingPackage;

import FileHandler.Restaurant;
import FileHandler.Table;
import MainCode.Order_Quantity;
import Persons.Manager;
import Persons.Person;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ManagerThankYou extends ThankyouScreen {
    private Manager user;

    public ManagerThankYou(Restaurant restaurant, Person user, Table reservedTable, List<Order_Quantity> orderedDishes) {
        super(restaurant, user, reservedTable, orderedDishes);
        this.user = (Manager) user;
        Stage stage = new Stage();
        VBox vBox = showFinalScreen(stage);
        thankYouLabel.setText("Thank you Mr "+ this.user.getName());
        specialMessage.setText("Thank You, Boss!");
        Scene scene = new Scene(vBox,500,500);
        stage.setScene(scene);
        stage.show();
    }

}
