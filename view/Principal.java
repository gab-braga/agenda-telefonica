package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Principal extends Application {

    private static Stage menuPrincipal;

    public static void setMenuPrincipal(Stage stage) {
        menuPrincipal = stage;
    }

    public static Stage getMenuPrincipal() {
        return menuPrincipal;
    }

    @Override
    public void start(Stage stage) throws IOException {
        setMenuPrincipal(stage);
        Parent root = FXMLLoader.load(getClass().getResource("principal.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("Agenda Telefônica");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
