package SavingPackage;

import FileHandler.Restaurant;
import FileHandler.Table;
import MainCode.Order_Quantity;
import Persons.Cooker;
import Persons.Person;
import Persons.Waiter;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CookerThankYou extends ThankyouScreen {
    private Cooker cooker;



    public CookerThankYou(Restaurant restaurant, Person user, Table reservedTable, List<Order_Quantity> orderedDishes) {
        super(restaurant, user, reservedTable, orderedDishes);
        this.cooker = (Cooker) user;
        Stage stage = new Stage();
        stage.setTitle("Thank you");
        VBox vbox = super.showFinalScreen(stage);
        thankYouLabel.setText("Thank you "+ this.cooker.getName());
        specialMessage.setText("Thank you for your\ndelicious meals!");
        Scene scene = new Scene(vbox,500,300);
        stage.setScene(scene);
        stage.show();
    }
}
