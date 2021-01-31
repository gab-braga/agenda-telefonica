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
        stage.initOwner(Principal.getPrincipal());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Adicionar Contato");
        stage.setResizable(false);
        setAdicionarContato(stage);

        Parent root = FXMLLoader.load(getClass().getResource("adicionar_contato.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
