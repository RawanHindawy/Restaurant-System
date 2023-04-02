package Persons;

import FileHandler.Restaurant;
import MainCode.Order_Quantity;
import MainCode.OrderedDish;
import SavingPackage.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.List;

public class Cooker extends Person {
    private List<ClientOrder> oldOrders = new ArrayList<>();
    private String name;


    Font labelFont = new Font("verdana", 30);
    Font buttonFont = new Font("verdana", 30);

    public String getName() {
        return name;
    }

    public Cooker(String name, String username, String password, String role, Restaurant restaurant, List<ClientOrder> oldOrders) {
        super(name, username, password, role, restaurant);
        this.name = name;

        this.oldOrders = oldOrders;
        draw();
    }

    @Override
    protected void draw() {
        super.draw();
        int i = 0;
        VBox cookerBox = new VBox(20);
        cookerBox.getChildren().add(super.titleBox);
        GridPane ordersGrid = new GridPane();
        int columnNumber = 0, rowNumber = 0;

        for (ClientOrder currentOrder : oldOrders) {
            //Dish --------------------------------------------------------------------
            Label table1 = new Label();
            table1.setTextAlignment(TextAlignment.CENTER);
            table1.setFont(labelFont);
            table1.setAlignment(Pos.CENTER);
            table1.setPrefWidth(400);
            table1.setTextFill(Color.WHITE);
            if (i++ % 2 == 0)
                table1.setStyle("-fx-background-color: ROSYBROWN");
            else
                table1.setStyle("-fx-background-color: CADETBLUE");

            table1.setText("Table " + currentOrder.getReservedTable().getTableNumber() + " \n");
            ordersGrid.add(table1, columnNumber, rowNumber);
            if (columnNumber == 2) {
                columnNumber = 0;
                rowNumber++;
            } else columnNumber++;
            for (Order_Quantity currentDish : currentOrder.getSavedOrderedDishes().getOrderedDishes()) {
                table1.setText(table1.getText() + currentDish.getQuantity() + " X " + currentDish.getDish().getName() + "\n");
            }
        }
        // Continue Button----------------------------------------------
        Button continueButton = new Button ("Proceed");
        continueButton.setFont(buttonFont);
        continueButton.setTextFill(Color.WHITE);
        continueButton.setAlignment(Pos.CENTER);
        continueButton.setPrefSize(620,150);
        continueButton.setStyle("-fx-background-color: SLATEGRAY");
        HBox orderBox = new HBox(20,continueButton);
        orderBox.setAlignment(Pos.CENTER);
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                ThankyouScreen thankyouScreen = new CookerThankYou(restaurant,Cooker.this, null,null);
            }
        });
        //-----------------------------------------------------------------
        ordersGrid.setHgap(20);
        ordersGrid.setVgap(20);
        ordersGrid.setAlignment(Pos.CENTER);
        cookerBox.getChildren().add(ordersGrid);
        cookerBox.getChildren().add(orderBox);
        cookerBox.setAlignment(Pos.TOP_CENTER);
        cookerBox.setPrefSize(1000, 750);

        ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(1650);
        s.setValue(110);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(12);
        s.setBlockIncrement(10);
        s.setPrefSize(50, 800);
        Group root = new Group();
        root.getChildren().addAll(cookerBox, s);


        Scene scene = new Scene(root, 1050, 800);
        s.setLayoutX(scene.getWidth() - s.getWidth());
        s.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                cookerBox.setLayoutY(-new_val.doubleValue());
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}

