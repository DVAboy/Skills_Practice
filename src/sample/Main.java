package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListMenu lMenu = new ListMenu();

        primaryStage.setTitle("Weapon-pedia");
        primaryStage.setScene(lMenu.makeScene());
        primaryStage.show();

        lMenu.initActions();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
