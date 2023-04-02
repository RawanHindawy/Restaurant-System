package MainCode;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewMenu {
    public ViewMenu() {
        Image image = null;
        try {
            image = new Image(new FileInputStream("Menu.jpeg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setX(20);
        imageView.setY(20);
        imageView.setFitHeight(800);
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);
        HBox imageBox = new HBox(20,imageView);
        imageBox.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(imageBox, 700, 850);
        Stage imageStage = new Stage();
        imageStage.setTitle("Menu");
        imageStage.setScene(scene);
        imageStage.show();
    }
}
