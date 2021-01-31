/**
 * @author Francisco Gabriel Braga
 */
/*
* Muito prazer, meu nome é Francisco Gabriel Braga do Nascimento,
* sou estudante de Ciência da Computação pelo Instituto Federal de Educação,
* Ciência e Tecnologia do Ceará Campus Tinaguá. Estudo programação dês de 2017,
* a partir dai me apaixonei por essa vasta área. Ainda tenho pouca
* experiência em programação, mas como alguém que eu não lembro disse
* em algum lugar que eu também não lembro: "O sucesso é a soma de pequenos
* esforços repetidos dia após dai". 😁
* */

package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Principal extends Application {

    private static Stage principal;

    public static void setPrincipal(Stage stage) {
        principal = stage;
    }

    public static Stage getPrincipal() {
        return principal;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Agenda Telefônica");
        stage.setResizable(false);
        setPrincipal(stage);

        Parent root = FXMLLoader.load(getClass().getResource("principal.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
