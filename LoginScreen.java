package MainCode;

import FileHandler.Restaurant;
import FileHandler.Table;
import FileHandler.User;
import Persons.*;
import SavingPackage.ClientOrder;
import SavingPackage.MainOrders;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class LoginScreen {
    private Stage stage;
    private Restaurant restaurant;
    private User userx;
    private String role;
    private int flag = 0;
    private HBox titleBox;
    private  GridPane grid;
    private  Label warning3;
    private  Label warning1;
    private  Label warning2;
    private List<ClientOrder> oldOrders= new ArrayList<>();
    //Fonts-------------------------------------------------------------
    Font titleFont = new Font("verdana",38);
    Font labelFont = new Font("verdana",28);
    Font buttonFont = new Font("verdana",24);
    Font warningFont = new Font("verdana",16);
    //------------------------------------------------------------------


    public List<ClientOrder> getOldOrders() {
        return oldOrders;
    }

    public void setOldOrders(List<ClientOrder> oldOrders) {
        this.oldOrders = oldOrders;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LoginScreen(Stage stage, Restaurant restaurant) throws JAXBException {
        this.stage = stage;
        this.restaurant = restaurant;
        //----------------------------------------------------------------
        File file = new File("SaveClient.xml");
        Boolean exists = file.exists();
        if (exists) {
            MainOrders rootInput = new MainOrders();
            JAXBContext jaxbContext2 = JAXBContext.newInstance(MainOrders.class);
            Unmarshaller unmarshaller2 = jaxbContext2.createUnmarshaller();
            rootInput = (MainOrders) unmarshaller2.unmarshal(new File("SaveClient.xml") );
            oldOrders = rootInput.getOrders();
            for (ClientOrder tempOrder: oldOrders)
            {
                int tableNumber = tempOrder.getReservedTable().getTableNumber();
                List<Table> tables = this.restaurant.getTables().getTables();
                for (Table tempTable: tables) {
                    if (tempTable.getNumber() == tableNumber) tempTable.setBooked(true);
                }
            }
        }
        setOldOrders(oldOrders);





        // title --------------------------------------------------------
        Label title = new Label("Welcome to Friends Restaurant");
        title.setFont(titleFont);
        title.setTextFill(Color.BLACK);
        title.setAlignment(Pos.CENTER);
        titleBox = new HBox(20, title);
        titleBox.setAlignment(Pos.TOP_CENTER);
        //---------------------------------------------------------------
        //Warning 1------------------------------------------------------
        warning1 = new Label("Please enter username\n\t and password !");
        warning1.setPrefSize(200,80);
        warning1.setFont(warningFont);
        warning1.setAlignment(Pos.CENTER);
        warning1.setTextFill(Color.RED);
        //---------------------------------------------------------------
        //Warning 2------------------------------------------------------
        warning2 = new Label("Username is not found");
        warning2.setPrefSize(200,80);
        warning2.setFont(warningFont);
        warning2.setAlignment(Pos.CENTER);
        warning2.setTextFill(Color.RED);
        //---------------------------------------------------------------
        //Warning 3------------------------------------------------------
        warning3 = new Label("Incorrect Password");
        warning3.setPrefSize(200,80);
        warning3.setFont(warningFont);
        warning3.setAlignment(Pos.CENTER);
        warning3.setTextFill(Color.RED);
        //----------------------------------------------------------------
        //username -------------------------------------------------------
        Label username = new Label("\tUsername");
        username.setFont(labelFont);
        username.setAlignment(Pos.CENTER);
        TextField usernameField = new TextField();
        usernameField.setAlignment(Pos.CENTER);
        //-----------------------------------------------------------------
        //password---------------------------------------------------------
        Label password = new Label("\tPassword");
        password.setFont(labelFont);
        password.setAlignment(Pos.CENTER);
        PasswordField passwordField = new PasswordField();
        passwordField.setAlignment(Pos.CENTER);
        //Confirm Button---------------------------------------------------
        Button confirm = new Button("Confirm");
        confirm.setFont(buttonFont);
        confirm.setAlignment(Pos.CENTER);
        confirm.setPrefSize(200,50);
        //------------------------------------------------------------------
        //Grid--------------------------------------------------------------
        grid = new GridPane();
        grid.add(username, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(password, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(confirm, 1, 2);
        grid.setHgap(50);
        //Final View--------------------------------------------------------
        VBox logView = new VBox(20, titleBox, grid);
        HBox logView2 = new HBox(20,logView);
        logView2.setAlignment(Pos.CENTER);
        //------------------------------------------------------------------
        Scene login = new Scene(logView2, 800, 200);
        stage.setScene(login);
        stage.show();
        //------------------------------------------------------------------
        // Button action----------------------------------------------------
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if (usernameField.getText().isEmpty()||passwordField.getText().isEmpty())
                   error1();
               else { for(User user: LoginScreen.this.restaurant.getUsers().getUsers()){ flag = 0;
                   if (usernameField.getText().equals(user.getUsername())){
                       userx = user;
                        flag = 1;
                        break;
                   }
               }
               if (flag!=1) error2();
              else if (userx.getPassword().equals(passwordField.getText())){
                   role=userx.getRole();
                   switch (role) {
                       case "Manager":
                           stage.close();
                           Person manager = new Manager(userx.getName(),userx.getUsername(),userx.getPassword(),userx.getRole(),getRestaurant(),oldOrders);
                           break;
                       case "Client":
                           stage.close();
                           Person client = new Client(userx.getName(),userx.getUsername(),userx.getPassword(),userx.getRole(),getRestaurant());
                           break;
                       case "Cooker":
                           stage.close();
                           Person cooker = new Cooker(userx.getName(),userx.getUsername(),userx.getPassword(),userx.getRole(),getRestaurant(),oldOrders);
                           break;
                       case "Waiter":
                           stage.close();
                           Person waiter = new Waiter(userx.getName(),userx.getUsername(),userx.getPassword(),userx.getRole(),getRestaurant(),oldOrders);
                           break;
                       default:
                           throw new IllegalStateException("Unexpected value: " + role);
                   }
               }
               else error3();
               }
            }
        });
        //------------------------------------------------------------------
    }
    //Missing Field error --------------------------------------------------
    private void error1()
    {
        HBox warning1HBox = new HBox(20, grid, warning1);
        VBox warning1VBox = new VBox(20, titleBox, warning1HBox);
        Scene login2 = new Scene(warning1VBox, 800, 200);
        stage.setScene(login2);
    }
    //----------------------------------------------------------------------
    //Username not found error----------------------------------------------
    private void error2()
    {
        HBox warning2HBox = new HBox(20, grid, warning2);
        VBox warning2VBox = new VBox(20, titleBox, warning2HBox);
        Scene login3 = new Scene(warning2VBox, 800, 200);
        stage.setScene(login3);
    }
    //----------------------------------------------------------------------
    //Password is invalid error---------------------------------------------
    private void error3()
    {
        HBox warning3HBox = new HBox(20, grid, warning3);
        VBox warning3VBox = new VBox(20, titleBox, warning3HBox);
        Scene login4 = new Scene(warning3VBox, 800, 200);
        stage.setScene(login4);
    }
    //-----------------------------------------------------------------------
}