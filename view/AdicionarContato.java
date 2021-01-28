package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AdicionarContato extends Application {

    private static Stage adicionarContato;

    public static void setAdicionarContato(Stage stage) {
        adicionarContato = stage;
    }

    public static Stage getAdicionarContato() {
        return adicionarContato;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(MenuPrincipal.getMenuPrincipal());
        setAdicionarContato(stage);
        Parent root = FXMLLoader.load(getClass().getResource("adicionar_contato.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("Adicionar Contato");
        stage.setResizable(false);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
