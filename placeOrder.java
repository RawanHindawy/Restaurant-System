package MainCode;
import FileHandler.Dish;
import FileHandler.Restaurant;
import FileHandler.Table;
import Persons.Client;
import SavingPackage.PrepareSavingClient;
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
import javafx.scene.control.Label;

import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class placeOrder {
 private Restaurant restaurant;
    private Client client;
    private List<OrderedDish> appetizersList = new ArrayList<>();
    private List<OrderedDish> mainCourseList = new ArrayList<>();
    private List<OrderedDish> desertsList = new ArrayList<>();
    private List<OrderedDish> allDishes = new ArrayList<>();
    private List<OrderedDish> orderedDishes = new ArrayList<>();
    private List<Order_Quantity> ordersQuantity = new ArrayList<>();
    private Table reservedTable;
 private int i=0;
 private float totalInvoice;
 Font titleFont = new Font("verdana",40);
 Font headingFont = new Font("verdana",36);
 Font buttonFont = new Font("verdana",30);
 Font labelFont = new Font("verdana",20);
 Font labelFont2 = new Font("verdana",18);

    public float getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(float totalInvoice) {
        this.totalInvoice = totalInvoice;
    }

    public placeOrder(Restaurant restaurant, Client client, Table reservedTable) {
        this.restaurant = restaurant;
        this.client = client;
        this.reservedTable=reservedTable;

        int i=0;
        for (Dish currentDish: this.restaurant.getDishes().getDishes()) {
            switch (currentDish.getType()) {
                case "main_course":
                    OrderedDish dish = new OrderedDish();
                    dish.setName(currentDish.getName());
                    dish.setPrice(currentDish.getPrice());
                    dish.setType(currentDish.getType());
                    mainCourseList.add(dish);
                    break;
                case "appetizer":
                    OrderedDish dish2 = new OrderedDish();
                    dish2.setName(currentDish.getName());
                    dish2.setPrice(currentDish.getPrice());
                    dish2.setType(currentDish.getType());
                    appetizersList.add(dish2);
                    break;
                case "desert":
                    OrderedDish dish3 = new OrderedDish();
                    dish3.setName(currentDish.getName());
                    dish3.setPrice(currentDish.getPrice());
                    dish3.setType(currentDish.getType());
                    desertsList.add(dish3);
                    break;
            }

        }

        showOrderMenu();
    }
    private void showOrderMenu()
    {
        Stage stage = new Stage();
        //Title ---------------------------------------------------------
        Label title = new Label("Select your preferred Dishes");
        title.setFont(titleFont);
        title.setTextAlignment(TextAlignment.CENTER);
        HBox titleBox = new HBox(20,title);
        titleBox.setAlignment(Pos.TOP_CENTER);
        //------------------------------------------------------------------
        //Headings----------------------------------------------------------
        Label appetizers = new Label("Appetizers");
        appetizers.setFont(headingFont);
        appetizers.setTextAlignment(TextAlignment.CENTER);
        appetizers.setTextFill(Color.RED);
        appetizers.setPrefSize(300,70);
        appetizers.setAlignment(Pos.CENTER);
        Label appetizersTaxes = new Label("(10% Taxes added)");
        appetizersTaxes.setPrefSize(300,50);
        appetizersTaxes.setFont(labelFont2);
        appetizersTaxes.setTextAlignment(TextAlignment.CENTER);
        appetizersTaxes.setAlignment(Pos.CENTER);
        VBox Label1Box = new VBox(10,appetizers,appetizersTaxes);

        Label mainCourse = new Label("Main Course");
        mainCourse.setFont(headingFont);
        mainCourse.setTextAlignment(TextAlignment.CENTER);
        mainCourse.setTextFill(Color.GREEN);
        mainCourse.setPrefSize(300,70);
        mainCourse.setAlignment(Pos.CENTER);
        Label mainCourseTaxes = new Label("(15% Taxes added)");
        mainCourseTaxes.setPrefSize(300,50);
        mainCourseTaxes.setFont(labelFont2);
        mainCourseTaxes.setTextAlignment(TextAlignment.CENTER);
        mainCourseTaxes.setAlignment(Pos.CENTER);
        VBox Label2Box = new VBox(10,mainCourse,mainCourseTaxes);


        Label desserts = new Label("Desserts");
        desserts.setFont(headingFont);
        desserts.setTextAlignment(TextAlignment.CENTER);
        desserts.setTextFill(Color.VIOLET);
        desserts.setPrefSize(300,70);
        desserts.setAlignment(Pos.CENTER);
        Label dessertsTaxes = new Label("(20% Taxes added)");
        dessertsTaxes.setPrefSize(300,50);
        dessertsTaxes.setFont(labelFont2);
        dessertsTaxes.setTextAlignment(TextAlignment.CENTER);
        dessertsTaxes.setAlignment(Pos.CENTER);
        VBox Label3Box = new VBox(10,desserts,dessertsTaxes);

        HBox labelsBox = new HBox(20,Label1Box,Label2Box,Label3Box);
        labelsBox.setAlignment(Pos.TOP_CENTER);

        //-------------------------------------------------------------------------
        //Buttons ------------------------------------------------------------------
        VBox appetizersVbox = new VBox(20);
        for (OrderedDish currentDish: appetizersList) {
            final Boolean[] flag = {false};
            final int[] index = new int[1];
            Button currentButton = new Button();
            currentButton.setTextAlignment(TextAlignment.CENTER);
            currentButton.setFont(buttonFont);
            currentButton.setAlignment(Pos.CENTER);
            currentButton.setPrefSize(300,150);
            currentButton.setText(currentDish.getName()+"\nEGP "+currentDish.getPrice());
            appetizersVbox.getChildren().add(currentButton);
            currentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (!flag[0]) {ordersQuantity.add(new Order_Quantity());
                    index[0] =ordersQuantity.size();
                    ordersQuantity.get(index[0]-1).setDish(currentDish);

                        ordersQuantity.get(index[0]-1).increaseQuantity();
                    }
                    else
                        ordersQuantity.get(index[0]-1).increaseQuantity();
                        flag[0] =true;
                        confirmAddition(currentDish.getName());
                }
            });
        }
        VBox mainCourseVbox = new VBox(20);
        for (OrderedDish currentDish: mainCourseList) {
            final Boolean[] flag = {false};
            final int[] index = new int[1];
            Button currentButton = new Button();
            currentButton.setTextAlignment(TextAlignment.CENTER);
            currentButton.setFont(buttonFont);
            currentButton.setAlignment(Pos.CENTER);
            currentButton.setPrefSize(300,150);
            currentButton.setText(currentDish.getName()+"\nEGP "+currentDish.getPrice());
            mainCourseVbox.getChildren().add(currentButton);
            currentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!flag[0]) {ordersQuantity.add(new Order_Quantity());
                        index[0] =ordersQuantity.size();
                        ordersQuantity.get(index[0]-1).setDish(currentDish);

                        ordersQuantity.get(index[0]-1).increaseQuantity();
                    }
                    else
                        ordersQuantity.get(index[0]-1).increaseQuantity();
                    flag[0] =true;
                    confirmAddition(currentDish.getName());
                }
            });
        }
        VBox desertsVbox = new VBox(20);

        for (OrderedDish currentDish: desertsList) {
            final Boolean[] flag = {false};
            final int[] index = new int[1];
            Button currentButton = new Button();
            currentButton.setTextAlignment(TextAlignment.CENTER);
            currentButton.setFont(buttonFont);
            currentButton.setAlignment(Pos.CENTER);
            currentButton.setPrefSize(300,150);
            currentButton.setText(currentDish.getName()+"\nEGP "+currentDish.getPrice());
            desertsVbox.getChildren().add(currentButton);
            currentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!flag[0]) {ordersQuantity.add(new Order_Quantity());
                        index[0] =ordersQuantity.size();
                        ordersQuantity.get(index[0]-1).setDish(currentDish);

                        ordersQuantity.get(index[0]-1).increaseQuantity();
                    }
                    else
                        ordersQuantity.get(index[0]-1).increaseQuantity();
                    flag[0] =true;
                    confirmAddition(currentDish.getName());

                }
            });
        }
        HBox hbox1 = new HBox(40,appetizersVbox,mainCourseVbox,desertsVbox);

        //------------------------------------------------------------------------------
        Button showOrder = new Button("view order");
        showOrder.setFont(buttonFont);
        showOrder.setTextFill(Color.WHITE);
        showOrder.setAlignment(Pos.CENTER);
        showOrder.setPrefSize(300,150);
        showOrder.setStyle("-fx-background-color: CADETBLUE");
        HBox showOrderBox = new HBox(20,showOrder);
        showOrderBox.setAlignment(Pos.CENTER_RIGHT);
        showOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    Stage invoiceStage = new Stage();
                    Label invoiceTitle = new Label("Invoice for table number "+reservedTable.getNumber());
                    invoiceTitle.setFont(titleFont);
                    invoiceTitle.setAlignment(Pos.TOP_CENTER);
                    HBox titleBox = new HBox(20,invoiceTitle);
                    Label heading1 = new Label("Dish");
                    Label heading2 = new Label("Price before Taxes");
                    Label heading3 = new Label("Price after Taxes");
                    Label heading4 = new Label("Quantity");
                    Label heading5 = new Label("Total Price");
                    heading1.setFont(labelFont); heading2.setFont(labelFont); heading3.setFont(labelFont);heading4.setFont(labelFont);heading5.setFont(labelFont);
                    heading1.setAlignment(Pos.CENTER); heading2.setAlignment(Pos.CENTER); heading3.setAlignment(Pos.CENTER);heading4.setAlignment(Pos.CENTER);heading5.setAlignment(Pos.CENTER);
                    heading1.setTextAlignment(TextAlignment.CENTER); heading2.setTextAlignment(TextAlignment.CENTER); heading3.setTextAlignment(TextAlignment.CENTER);heading4.setTextAlignment(TextAlignment.CENTER);heading5.setTextAlignment(TextAlignment.CENTER);
                    heading1.setPrefSize(200,50);
                    heading2.setPrefSize(300,50);
                    heading3.setPrefSize(300,50);
                    heading4.setPrefSize(200,50);
                    heading5.setPrefSize(250,50);
                    HBox headingsBox = new HBox(20,heading1,heading2,heading3,heading4,heading5);
                    titleBox.setAlignment(Pos.TOP_CENTER);
                    VBox invoiceVBox = new VBox(20);
                    invoiceVBox.getChildren().add(titleBox);
                    invoiceVBox.getChildren().add(headingsBox);
                    for (Order_Quantity currentdish : ordersQuantity)
                    {
                        Label dish1 = new Label(currentdish.getDish().getName() );
                        dish1.setTextAlignment(TextAlignment.CENTER);
                        dish1.setFont(labelFont2);
                        dish1.setAlignment(Pos.CENTER_LEFT);
                        dish1.setPrefSize(200,50);
                        Label dish1_1 = new Label(""+currentdish.getDish().getPrice());
                        dish1_1.setFont(labelFont2);
                        dish1_1.setAlignment(Pos.CENTER);
                        dish1_1.setPrefSize(200,50);
                        Label dish1_2 = new Label();
                        dish1_2.setFont(labelFont2);
                        dish1_2.setAlignment(Pos.CENTER);
                        dish1_2.setPrefSize(200,50);
                        Label dish1_3 = new Label(""+currentdish.getQuantity());
                        dish1_3.setFont(labelFont2);
                        dish1_3.setAlignment(Pos.CENTER);
                        dish1_3.setPrefSize(200,50);
                        Label dish1_4 = new Label();
                        switch (currentdish.getDish().getType()) {
                            case "appetizer":
                                dish1_2.setText("" + currentdish.getDish().getPrice() * 1.1);
                                dish1_4.setText("" + currentdish.getQuantity() * currentdish.getDish().getPrice() * 1.1);
                                totalInvoice += currentdish.getQuantity() * currentdish.getDish().getPrice() * 1.1;
                                break;
                            case "main_course":
                                dish1_2.setText("" + currentdish.getDish().getPrice() * 1.15);
                                dish1_4.setText("" + currentdish.getQuantity() * currentdish.getDish().getPrice() * 1.15);
                                totalInvoice += currentdish.getQuantity() * currentdish.getDish().getPrice() * 1.15;
                                break;
                            case "desert":
                                dish1_2.setText("" + currentdish.getDish().getPrice() * 1.2);
                                dish1_4.setText("" + currentdish.getQuantity() * currentdish.getDish().getPrice() * 1.2);
                                totalInvoice += currentdish.getQuantity() * currentdish.getDish().getPrice() * 1.2;
                                break;
                        }
                        dish1_4.setFont(labelFont2);
                        dish1_4.setAlignment(Pos.CENTER);
                        dish1_4.setPrefSize(200,50);
                        HBox dish1Box = new HBox(0,dish1,dish1_1,dish1_2,dish1_3,dish1_4);
                        dish1Box.setAlignment(Pos.CENTER_LEFT);
                        invoiceVBox.getChildren().add(dish1Box);
                    }
                    Label totalLabel = new Label("Total Price to be paid");
                    totalLabel.setAlignment(Pos.CENTER_LEFT);
                    totalLabel.setFont(labelFont2);
                    totalLabel.setPrefSize(300,50);

                    Label totalPriceAfter = new Label(String.valueOf(getTotalInvoice()));
                    totalPriceAfter.setFont(labelFont2);
                    totalPriceAfter.setPrefSize(600,50);
                    totalPriceAfter.setAlignment(Pos.CENTER_RIGHT);

                    Button accept = new Button("Accept and Proceed");
                    accept.setFont(labelFont2);
                    accept.setTextFill(Color.WHITE);
                    accept.setAlignment(Pos.CENTER);
                    accept.setPrefSize(300,80);
                    accept.setStyle("-fx-background-color: CADETBLUE");
                    accept.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stage.close();
                            invoiceStage.close();
                            int i;
                            try {
                                for (Order_Quantity currentOrder:ordersQuantity)
                                {for (i=0;i<currentOrder.getQuantity();i++)
                                    orderedDishes.add(currentOrder.getDish());
                                }
                                ThankyouScreen save = new PrepareSavingClient(restaurant,client,reservedTable,ordersQuantity,totalInvoice);
                            } catch (JAXBException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Button add = new Button("Add more dishes");
                    add.setFont(labelFont2);
                    add.setTextFill(Color.WHITE);
                    add.setAlignment(Pos.CENTER);
                    add.setPrefSize(300,80);
                    add.setStyle("-fx-background-color: ROSYBROWN");
                    add.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            invoiceStage.close();
                        }
                    });
                    HBox buttonsBox = new HBox(20,accept,add);
                    buttonsBox.setAlignment(Pos.BOTTOM_CENTER);

                    HBox tempBox = new HBox(20,totalLabel,totalPriceAfter);
                    tempBox.setAlignment(Pos.CENTER_LEFT);
                    invoiceVBox.getChildren().add(tempBox);
                    invoiceVBox.setSpacing(20);
                    invoiceVBox.getChildren().add(buttonsBox);
                    Scene invoiceScene = new Scene(invoiceVBox,1000,1000);
                    invoiceStage.setScene(invoiceScene);
                    invoiceStage.show();


            }
        });
        //--------------------------------------------------------------------------

        ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(500);
        s.setValue(0);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(12);
        s.setBlockIncrement(10);
        s.setPrefSize(50,1000);




        VBox wholeView = new VBox(20,labelsBox,hbox1,showOrderBox);
        wholeView.setAlignment(Pos.CENTER);
        Group root = new Group();
        root.getChildren().addAll(wholeView,s);
        Scene menuScene = new Scene(root,1000,1000);
        s.setLayoutX(menuScene.getWidth()-s.getWidth());
        s.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                wholeView.setLayoutY(-new_val.doubleValue());
            }
        });
        stage.setScene(menuScene);
        stage.show();

    }
    public void confirmAddition(String dish){
        Stage addStage = new Stage();
        Label addition = new Label("1 X "+dish+" was successfully added");
        addition.setFont(labelFont);
        HBox labelBox = new HBox(20,addition);
        labelBox.setAlignment(Pos.CENTER);
        Button addButton = new Button("Continue!");
        addButton.setFont(buttonFont);
        HBox buttonBox = new HBox(20,addButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox addvBox = new VBox(20,labelBox,buttonBox);
        Scene scene = new Scene(addvBox,1000,120);
        addStage.setScene(scene);
        addStage.show();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addStage.close();
            }
        });
    }
}