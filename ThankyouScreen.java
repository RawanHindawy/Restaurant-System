package SavingPackage;

import FileHandler.Restaurant;
import FileHandler.Table;
import MainCode.LoginScreen;
import MainCode.Order_Quantity;
import Persons.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.util.List;

public abstract class ThankyouScreen {
    protected Restaurant restaurant;
    protected Person user;
    protected Table reservedTable;
    protected List<Order_Quantity> orderedDishes;
    protected Label specialMessage;
    protected Label thankYouLabel;



    public ThankyouScreen(Restaurant restaurant, Person user, Table reservedTable, List<Order_Quantity> orderedDishes) {
        this.restaurant = restaurant;
        this.user = user;
        this.reservedTable = reservedTable;
        this.orderedDishes = orderedDishes;

    }

    protected VBox showFinalScreen(Stage stage){

        Font labelFont = new Font ("verdana",28);
        thankYouLabel = new Label();
        thankYouLabel.setFont(labelFont);
        thankYouLabel.setAlignment(Pos.CENTER);

        specialMessage = new Label();
        specialMessage.setFont(labelFont);
        specialMessage.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setFont(labelFont);
        closeButton.setAlignment(Pos.BOTTOM_CENTER);

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                try {
                    LoginScreen x = new LoginScreen(new Stage(),restaurant);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }

            }
        });

        VBox closeView = new VBox(30,thankYouLabel,specialMessage,closeButton);
        closeView.setAlignment(Pos.CENTER);

        return closeView;

   }

}
