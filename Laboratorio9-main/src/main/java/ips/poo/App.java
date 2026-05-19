package ips.poo;
import ips.poo.view.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

               
                MainWindow mainWindow=  new MainWindow();

                // Scene
                Scene scene = new Scene(mainWindow, 400, 300);

                // Stage
                primaryStage.setTitle("JavaFX Example");
                primaryStage.setScene(scene);
                primaryStage.show();
            }




    public static void main(String[] args) {

        launch();
    }

}
