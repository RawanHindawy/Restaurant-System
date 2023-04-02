package Persons;

import FileHandler.Restaurant;
import MainCode.Order_Quantity;
import SavingPackage.ClientOrder;
import SavingPackage.ManagerThankYou;
import SavingPackage.ThankyouScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;

public class Manager extends Person {
    private List<ClientOrder> oldOrders;
    private String name;

    public String getName() {
        return name;
    }

    Font buttonFont = new Font("verdana",36);
    Font labelFont = new Font("verdana",24);
    Font labelFont2 = new Font("verdana",30);

    public Manager(String name, String username, String password, String role, Restaurant restaurant, List<ClientOrder> oldOrders) {
        super(name,username,password,role,restaurant);
        this.name = name;
        this.oldOrders = oldOrders;
       draw();
    }

    @Override
    public void draw(){
        super.draw();
        stage.setTitle("Manager Dashboard");
        int i = 0,columnNumber=0,rowNumber=0;
        float Invoice = 0;
        GridPane gridPane = new GridPane();

        for (ClientOrder currentOrder: oldOrders)
        { Invoice += currentOrder.getInvoice();
            Button showDetails = new Button("Show details");
            Label currentLabel = new Label();
            currentLabel.setTextAlignment(TextAlignment.CENTER);
            currentLabel.setFont(labelFont);
            currentLabel.setAlignment(Pos.CENTER);
            currentLabel.setPrefWidth(250);
            currentLabel.setTextFill(Color.WHITE);
            currentLabel.setGraphic(showDetails);
            currentLabel.setText("Invoice of Table "+currentOrder.getReservedTable().getTableNumber()+"\n EGP "+currentOrder.getInvoice());
            currentLabel.setContentDisplay(ContentDisplay.BOTTOM);
            if (i++ % 2 == 0)
                currentLabel.setStyle("-fx-background-color: ROSYBROWN");
            else
                currentLabel.setStyle("-fx-background-color: CADETBLUE");
            showDetails.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // Details stage -----------------------------------------------------
                    Stage detailsStage = new Stage();
                    detailsStage.setTitle("Table number "+currentOrder.getReservedTable().getTableNumber()+" Details");
                    Label clientName = new Label ("Client "+currentOrder.getClient().getName());
                    clientName.setFont(labelFont2);
                    clientName.setAlignment(Pos.CENTER);
                    Label tableNumber = new Label("Table number "+currentOrder.getReservedTable().getTableNumber());
                    tableNumber.setFont(labelFont2);
                    tableNumber.setAlignment(Pos.CENTER);
                    Label orderLabel = new Label ("Orders :\n");
                    orderLabel.setFont(labelFont2);
                    orderLabel.setAlignment(Pos.CENTER);
                    for(Order_Quantity order : currentOrder.getSavedOrderedDishes().getOrderedDishes()){
                        orderLabel.setText(orderLabel.getText()+ order.getQuantity() + " X " + order.getDish().getName()+"\n" ); }
                    Label invoiceLabel = new Label ("Total Invoice: "+currentOrder.getInvoice());
                    invoiceLabel.setFont(labelFont2);
                    invoiceLabel.setAlignment(Pos.CENTER);

                    Button closeButton = new Button(" Close ");
                    closeButton.setFont(labelFont);
                    closeButton.setAlignment(Pos.BOTTOM_CENTER);
                    closeButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            detailsStage.close();
                        }
                    });

                    VBox showBox = new VBox(30,clientName,tableNumber,orderLabel,invoiceLabel,closeButton);
                    showBox.setAlignment(Pos.CENTER);
                    showBox.setPrefSize(500,600);
                    ScrollBar s = new ScrollBar();
                    s.setMin(0);
                    s.setMax(1650);
                    s.setValue(110);
                    s.setOrientation(Orientation.VERTICAL);
                    s.setUnitIncrement(12);
                    s.setBlockIncrement(10);
                    s.setPrefSize(50,500);
                    Group root = new Group();
                    root.getChildren().addAll(showBox,s);
                    Scene scene = new Scene(root,500,600);
                    s.setLayoutX(scene.getWidth()-s.getWidth());
                    s.valueProperty().addListener(new ChangeListener<Number>() {
                        public void changed(ObservableValue<? extends Number> ov,
                                            Number old_val, Number new_val) {
                            showBox.setLayoutY(-new_val.doubleValue());
                        }
                    });
                    detailsStage.setScene(scene);
                    detailsStage.show();

                    //--------------------------------------------------------------------
                }
            });
            gridPane.add(currentLabel,columnNumber,rowNumber);

            if (columnNumber==2){
                columnNumber=0; rowNumber++;
            }
            else columnNumber ++;


        }
        titleBox.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        Label invisibleLabel = new Label();
        invisibleLabel.setPrefSize(50,80);
        invisibleLabel.setStyle("-fx-background-color: TRANSPARENT");
        Label totalEarned = new Label ("Total earned amount : EGP "+ Invoice);
        totalEarned.setAlignment(Pos.CENTER);
        totalEarned.setFont(labelFont);
        Button proceedButton = new Button("Proceed");
        proceedButton.setFont(labelFont);
        proceedButton.setAlignment(Pos.CENTER);
        proceedButton.setPrefSize(600,100);
        proceedButton.setTextFill(Color.WHITE);
        proceedButton.setStyle("-fx-background-color: SLATEGREY");
        HBox proceedButtonBox = new HBox(20,proceedButton);
        proceedButtonBox.setAlignment(Pos.BOTTOM_CENTER);


        proceedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                ThankyouScreen thankyouScreen = new ManagerThankYou(restaurant, (Person) Manager.this , null,null);
            }
        });
        VBox managerBox = new VBox(20,titleBox,gridPane,invisibleLabel,totalEarned,proceedButtonBox);
        managerBox.setAlignment(Pos.TOP_CENTER);
        managerBox.setPrefSize(950,800);
        ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(1650);
        s.setValue(110);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(12);
        s.setBlockIncrement(10);
        s.setPrefSize(50,800);
        Group root = new Group();
        root.getChildren().addAll(managerBox,s);

        Scene scene = new Scene(root,1000,800);
        s.setLayoutX(scene.getWidth()-s.getWidth());
        s.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                managerBox.setLayoutY(-new_val.doubleValue());
            }
        });

        stage.setScene(scene);

        stage.show();

    }

}
