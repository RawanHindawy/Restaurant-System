package MainCode;

import FileHandler.Restaurant;
import FileHandler.Table;
import Persons.Client;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class ViewTables {
    private Restaurant restaurant;
    private Table reservedTable=null;
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    private List<Table> allTables = new ArrayList<>();
    private List<Table> smokingTables= new ArrayList<>();
    private List<Table> noSmokingTables= new ArrayList<>();
    private List<Button> smokingbuttons = new ArrayList<>();
    private List<Button> noSmokingbuttons = new ArrayList<>();
    Stage stage = new Stage();
    Font buttonFont = new Font("verdana", 36);
    Font buttonFont2 = new Font("verdana", 26);
    Font labelFont = new Font("verdana",28);
    Font labelFont1 = new Font("verdana",20);
    public ViewTables(Restaurant restaurant) {
        this.restaurant = restaurant;
       allTables=restaurant.getTables().getTables();
        for (Table S:allTables
        ) { if (S.isSmoking()){
            smokingTables.add(S);        }
        }
        for (Table noS:allTables
        ) { if (!noS.isSmoking()){
            noSmokingTables.add(noS);
        }
        }
    }

    public void draw() {
        //Smoking Button -----------------------------------------------------
        Button smokingButton = new Button("Smoking");
        smokingButton.setFont(buttonFont);
        smokingButton.setTextFill(Color.WHITE);
        smokingButton.setAlignment(Pos.CENTER);
        smokingButton.setPrefSize(300, 200);
        smokingButton.setStyle("-fx-background-color: ROSYBROWN");
        //--------------------------------------------------------------------
        Button noSmokingButton = new Button("No Smoking");
        noSmokingButton.setFont(buttonFont);
        noSmokingButton.setTextFill(Color.WHITE);
        noSmokingButton.setAlignment(Pos.CENTER);
        noSmokingButton.setPrefSize(300, 200);
        noSmokingButton.setStyle("-fx-background-color: CADETBLUE");
        //---------------------------------------------------------------------
        HBox buttonBox = new HBox(50, smokingButton, noSmokingButton);
        buttonBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(buttonBox, 800, 250);
        stage.setScene(scene);
        stage.show();
        //---------------------------------------------------------------------
        //Smoking Button Action------------------------------------------------
        smokingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawSmoking();
            }
        });
        //---------------------------------------------------------------------
        //No Smoking Button Action----------------------------------------------
        noSmokingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drawNoSmoking();
            }
        });
        //----------------------------------------------------------------------
    }
    private void drawSmoking(){
        //---------------------------------------------
        Label choiceLabel= new Label("Choose your preferred table");
        choiceLabel.setFont(labelFont);
        choiceLabel.setAlignment(Pos.CENTER);
        HBox labelbox =new HBox(20,choiceLabel);
        labelbox.setAlignment(Pos.TOP_CENTER);
        GridPane smokingGrid = new GridPane();
        smokingGrid.setAlignment(Pos.CENTER);
        smokingGrid.setVgap(20);
        smokingGrid.setHgap(20);
        smokingGrid.setPrefWidth(750);
        int columnNumber=0,rowNumber=0;

        for (Table S: smokingTables){
            Button currentButton = new Button("Table Number "+S.getNumber()+"\nAvailable Seats: " +S.getNumber_of_seats()+"\nBooked: "+S.isBooked());
            currentButton.setFont(buttonFont2);
            currentButton.setTextFill(Color.WHITE);
            currentButton.setAlignment(Pos.CENTER);
            currentButton.setTextAlignment(TextAlignment.CENTER);
            currentButton.setPrefSize(300, 200);
            if (!S.isBooked())
                currentButton.setStyle("-fx-background-color: CADETBLUE");
            else
                currentButton.setStyle("-fx-background-color: ROSYBROWN");
            currentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(S.isBooked())
                    { bookedError();}
                    else{
                        S.setBooked(true);
                        reservedTable=S;
                        client.setReservedTable(reservedTable);
                        stage.close();
                        confirmBooking();
                    }
                }
            });
            smokingbuttons.add(currentButton);
        }
        for (Button sB: smokingbuttons) {
            smokingGrid.add(sB,columnNumber,rowNumber);
            if (columnNumber==1){
                columnNumber=0; rowNumber++;
            }
            else columnNumber ++;
        }
        ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(600);
        s.setValue(110);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(12);
        s.setBlockIncrement(10);
        s.setPrefSize(50,600);

        VBox viewBox = new VBox(20,labelbox,smokingGrid);
        viewBox.setAlignment(Pos.CENTER);
        Group root = new Group();
        root.getChildren().addAll(viewBox,s);
        Scene scene = new Scene(root,800,600);
        s.setLayoutX(scene.getWidth()-s.getWidth());
        s.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                viewBox.setLayoutY(-new_val.doubleValue());
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    //-------------------------------------------------------------------------------------------------------
    private void drawNoSmoking(){
        //---------------------------------------------
        Label choiceLabel= new Label("Choose your preferred table");
        choiceLabel.setFont(labelFont);
        choiceLabel.setAlignment(Pos.CENTER);
        HBox labelbox =new HBox(20,choiceLabel);
        labelbox.setAlignment(Pos.TOP_CENTER);
        GridPane noSmokingGrid = new GridPane();
        noSmokingGrid.setAlignment(Pos.CENTER);
        noSmokingGrid.setVgap(20);
        noSmokingGrid.setHgap(20);
        noSmokingGrid.setPrefWidth(750);
        int columnNumber=0,rowNumber=0;

        for (Table S: noSmokingTables){
            Button currentButton = new Button("Table Number "+S.getNumber()+"\nAvailable Seats: " +S.getNumber_of_seats()+"\nBooked: "+S.isBooked());
            currentButton.setFont(buttonFont2);
            currentButton.setTextFill(Color.WHITE);
            currentButton.setAlignment(Pos.CENTER);
            currentButton.setTextAlignment(TextAlignment.CENTER);
            currentButton.setPrefSize(300, 200);
            if (!S.isBooked())
                currentButton.setStyle("-fx-background-color: CADETBLUE");
            else
                currentButton.setStyle("-fx-background-color: ROSYBROWN");
            currentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(S.isBooked())
                    { bookedError();}
                    else{
                        S.setBooked(true);
                        reservedTable=S;
                        client.setReservedTable(reservedTable);
                        stage.close();
                        confirmBooking();
                    }
                }
            });
            noSmokingbuttons.add(currentButton);
        }
        for (Button sB: noSmokingbuttons) {
            noSmokingGrid.add(sB,columnNumber,rowNumber);
            if (columnNumber==1){
                columnNumber=0; rowNumber++;
            }
            else columnNumber ++;
        }
        ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(600);
        s.setValue(110);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(12);
        s.setBlockIncrement(10);
        s.setPrefSize(50,600);

        VBox viewBox = new VBox(20,labelbox,noSmokingGrid);
        viewBox.setAlignment(Pos.CENTER);
        Group root = new Group();
        root.getChildren().addAll(viewBox,s);
        Scene scene = new Scene(root,800,600);
        s.setLayoutX(scene.getWidth()-s.getWidth());
        s.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                viewBox.setLayoutY(-new_val.doubleValue());
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    private void confirmBooking(){
        String s;
        Stage confirmStage = new Stage();
        if (reservedTable.isSmoking()) s = "In the smoking area.";
        else s = "In the non smoking area.";
        Label success = new Label("You have successfully reserved table number "+reservedTable.getNumber()+"\nfor "+reservedTable.getNumber_of_seats()+" persons\n"+s);
        success.setFont(labelFont);
        success.setAlignment(Pos.CENTER);
        success.setTextAlignment(TextAlignment.CENTER);
        HBox confirmationBox = new HBox(20,success);
        confirmationBox.setAlignment(Pos.CENTER);
        Button confirmButton = new Button (" confirm");
        confirmButton.setFont(labelFont1);
        confirmButton.setAlignment(Pos.CENTER);
        confirmButton.setTextAlignment(TextAlignment.CENTER);
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                confirmStage.close();
            }
        });
        HBox confirmationBox1= new HBox(20,confirmButton);
        confirmationBox1.setAlignment(Pos.CENTER);
        VBox confirmationBox2 = new VBox(20,confirmationBox,confirmationBox1);
        Scene confirmScene= new Scene(confirmationBox2,700,200);
        confirmStage.setScene(confirmScene);
        confirmStage.show();
    }

    private void bookedError() {
        Stage errorStage = new Stage();
        Label error = new Label("Table is booked!");
        error.setFont(labelFont);
        error.setTextFill(Color.RED);
        HBox labelBox = new HBox(20, error);
        labelBox.setAlignment(Pos.CENTER);
        Button errorButton = new Button("Go Back!");
        errorButton.setFont(buttonFont2);
        HBox buttonBox = new HBox(20, errorButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox errorvBox = new VBox(20, labelBox, buttonBox);
        Scene scene = new Scene(errorvBox, 300, 200);
        errorStage.setScene(scene);
        errorStage.show();
        errorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorStage.close();
            }
        });
    }
}




