import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class JFXDemo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Label label = new Label("Hello World");
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        
        // Stage properties
        stage.setTitle("First JavaFX GUI");
        stage.show();
        
    }
  
    public static void main(String[] args) {
        launch(); // optional
    }
    
}
