package SavingPackage;

import FileHandler.Restaurant;
import FileHandler.Table;
import MainCode.Order_Quantity;
import MainCode.OrderedDish;
import Persons.Person;
import Persons.Waiter;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class WaiterThankYou extends ThankyouScreen{
    private Waiter waiter;

    public WaiterThankYou(Restaurant restaurant, Person user, Table reservedTable, List<Order_Quantity> orderedDishes) {
        super(restaurant, user, reservedTable, orderedDishes);

        this.waiter = (Waiter) user;
        Stage stage = new Stage();
        stage.setTitle("Thank you");
        VBox vbox = super.showFinalScreen(stage);
        thankYouLabel.setText("Thank you "+ this.waiter.getName());
        specialMessage.setText("Thank you for your service");
        Scene scene = new Scene(vbox,500,300);
        stage.setScene(scene);
        stage.show();


    }
}
