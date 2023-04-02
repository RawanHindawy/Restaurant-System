package Persons;

import FileHandler.Restaurant;
import FileHandler.Table;
import SavingPackage.ClientOrder;
import SavingPackage.ReservedTable;
import SavingPackage.ThankyouScreen;
import SavingPackage.WaiterThankYou;
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
import sun.text.resources.CollationData;

import java.util.ArrayList;
import java.util.List;

public class Waiter extends Person {

    private String name;
    private List<ClientOrder> oldOrders = new ArrayList<>();
    Font labelFont = new Font("verdana",36);
    Font buttonFont = new Font("verdana",30);

    public String getName() {
        return name;
    }

    public Waiter(String name, String username, String password, String role, Restaurant restaurant, List<ClientOrder> oldOrders) {
        super(name, username, password, role, restaurant);
        this.name = name;
        this.oldOrders=oldOrders;
        draw();
    }


    @Override
    protected void draw() {
        super.draw();
        GridPane tablesGrid = new GridPane();
        int columnNumber=0,rowNumber=0;

        //Table 1 Label----------------------------------------------------
        for (Table currentTable: restaurant.getTables().getTables())
        {
        Boolean flag = false;
        ClientOrder currentOrder = null;
        Label table1 = new Label();
        table1.setTextAlignment(TextAlignment.CENTER);
        table1.setFont(labelFont);
        table1.setAlignment(Pos.CENTER);
        table1.setPrefSize(400, 150);
        table1.setTextFill(Color.WHITE);
        for (ClientOrder tempOrder : oldOrders) {
            if (tempOrder.getReservedTable().getTableNumber() == currentTable.getNumber()) {
                flag = true;
                currentOrder = tempOrder;
            }
        }
        if (flag) {
            table1.setText("Table Number "+currentTable.getNumber()+"\n is booked\nby " + currentOrder.getClient().getName());
            table1.setStyle("-fx-background-color: CADETBLUE");
        } else {
            table1.setText("Table number "+currentTable.getNumber()+"\n is not booked");
            table1.setStyle("-fx-background-color: ROSYBROWN");
        }
            tablesGrid.add(table1,columnNumber,rowNumber);
            if (columnNumber==2){
                columnNumber=0; rowNumber++;
            }
            else columnNumber ++;}
            //----------------------------------------------------------------
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
                    ThankyouScreen thankyouScreen = new WaiterThankYou(restaurant,Waiter.this, null,null);
                }
            });
            //-----------------------------------------------------------------

            tablesGrid.setAlignment(Pos.CENTER);
            tablesGrid.setHgap(20);
            tablesGrid.setVgap(20);
            tablesGrid.setPrefWidth(1600);
        ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(1650);
        s.setValue(110);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(12);
        s.setBlockIncrement(10);
        s.setPrefSize(50,900);
        Group root = new Group();


            VBox titleVbox = new VBox(100,super.titleBox,tablesGrid,orderBox);
        root.getChildren().addAll(titleVbox,s);

        Scene ordersScene = new Scene(root,1650,900);
        s.setLayoutX(ordersScene.getWidth()-s.getWidth());
        s.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                titleVbox.setLayoutY(-new_val.doubleValue());
            }
        });
            stage.setScene(ordersScene);
            stage.show();
        //---------------------------------------------------------------------------


    }
}
