package Persons;

import FileHandler.Restaurant;
import FileHandler.Table;
import MainCode.ViewMenu;
import MainCode.ViewTables;
import MainCode.placeOrder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Client extends Person {
    private Table reservedTable = null;
    private String name= super.name;

    public void setReservedTable(Table reservedTable) {
        this.reservedTable = reservedTable;
    }

    Font buttonFont = new Font("verdana",36);
    Font labelFont = new Font("verdana",24);
    Font labelFont2 = new Font("verdana",30);
    
    public Client(String name, String username, String password, String role, Restaurant restaurant) {
        super(name, username, password, role, restaurant);
        draw();
    }

    public String getName() {
        return name;
    }

    @Override
    protected void draw() {
        super.draw();
        //View Menu Button-----------------------------------------------------
        Button viewMenuButton = new Button ("View Menu");
        viewMenuButton.setFont(buttonFont);
        viewMenuButton.setTextFill(Color.WHITE);
        viewMenuButton.setAlignment(Pos.CENTER);
        viewMenuButton.setPrefSize(300,150);
        viewMenuButton.setStyle("-fx-background-color: ROSYBROWN");
        //---------------------------------------------------------------------
        // Book a Table Button-------------------------------------------------
        Button bookATableButton = new Button ("Book a Table");
        bookATableButton.setFont(buttonFont);
        bookATableButton.setTextFill(Color.WHITE);
        bookATableButton.setAlignment(Pos.CENTER);
        bookATableButton.setPrefSize(300,150);
        bookATableButton.setStyle("-fx-background-color: CADETBLUE");
        // Make an order Button -----------------------------------------------
        Button makeOrderButton = new Button ("Place Order");
        makeOrderButton.setFont(buttonFont);
        makeOrderButton.setTextFill(Color.WHITE);
        makeOrderButton.setAlignment(Pos.CENTER);
        makeOrderButton.setPrefSize(620,150);
        makeOrderButton.setStyle("-fx-background-color: SLATEGRAY");
        HBox orderBox = new HBox(20,makeOrderButton);
        orderBox.setAlignment(Pos.CENTER);
        //---------------------------------------------------------------------
        //----------------------------------------------------------------------
        HBox buttonsBox = new HBox(20,viewMenuButton,bookATableButton);
        buttonsBox.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox(30,buttonsBox,orderBox);
        VBox clientBox = new VBox(70,super.titleBox,vbox1);
        clientBox.setAlignment(Pos.TOP_CENTER);
        Scene clientView = new Scene(clientBox,800,600);
        stage.setScene(clientView);
        stage.show();
        // View Menu Button Action -----------------------------------------------
        viewMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewMenu viewMenu = new ViewMenu();
            }
        });
        //------------------------------------------------------------------------
        //Book a table button action---------------------------------------------
        bookATableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

               ViewTables tables = new ViewTables(restaurant);
               tables.draw();
               tables.setClient(Client.this);
            }
        });
        //----------------------------------------------------------------------
        // Place Order button Action ---------------------------------------------
        makeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (reservedTable == null) {
                    Stage errorStage = new Stage();
                    Label error = new Label("Please Book a table First");
                    error.setFont(labelFont2);
                    HBox labelBox = new HBox(20,error);
                    labelBox.setAlignment(Pos.CENTER);
                    Button errorButton = new Button("Go Back!");
                    errorButton.setFont(buttonFont);
                    HBox buttonBox = new HBox(20,errorButton);
                    buttonBox.setAlignment(Pos.CENTER);
                    VBox errorvBox = new VBox(20,labelBox,buttonBox);
                    Scene scene = new Scene(errorvBox,600,200);
                    errorStage.setScene(scene);
                    errorStage.show();
                    errorButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            errorStage.close();
                        }
                    });
                }else {
                    stage.close();
                    placeOrder order = new placeOrder(restaurant,Client.this,reservedTable);
                }
                }
        });
        //-------------------------------------------------------------------------
    }

}
