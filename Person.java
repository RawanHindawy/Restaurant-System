package Persons;

import FileHandler.Restaurant;
import MainCode.LoginScreen;
import MainCode.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;

public abstract class Person {
    protected String name;
    protected String username;
    protected String password;
    protected String role;
    protected Restaurant restaurant;
    protected Stage stage;
    protected Label title;
    protected  HBox titleBox;

    //-------------------------------------------------------------------------------
    Font titleFont = new Font("verdana",38);
    //-------------------------------------------------------------------------------

    public Person(String name, String username, String password, String role, Restaurant restaurant) {
        setName(name);
        setPassword(password);
        setRole(role);
        setUsername(username);
        setRestaurant(restaurant);
    }

    private void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setUsername(String username) {
        this.username = username;
    }
    private void setPassword(String password) {
        this.password = password;
    }
    private void setRole(String role) {
        this.role = role;
    }

    protected void draw(){
        stage = new Stage();
      //Welcome Label-----------------------------------------------------------------
        title = new Label("Welcome "+name);
        title.setFont(titleFont);
        title.setTextFill(Color.BLACK);
        title.setAlignment(Pos.TOP_CENTER);
        Button logout = new Button("Log Out");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoginScreen login = new LoginScreen(stage, restaurant);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }

            }
        });
        HBox logOutBox = new HBox(20,logout);
        logOutBox.setAlignment(Pos.CENTER_RIGHT);
        titleBox = new HBox(40, title,logOutBox);
        titleBox.setAlignment(Pos.TOP_CENTER);
      //------------------------------------------------------------------------------

    }

}
