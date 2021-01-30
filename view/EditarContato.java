package view;

import controller.EditarContatoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import model.Contato;

import java.io.IOException;

public class EditarContato extends Application {

    private Contato contatoEditar;
    private static Stage editarContato;

    public static Stage getEditarContato() {
        return editarContato;
    }

    public static void setEditarContato(Stage stage) {
        editarContato = stage;
    }

    public Contato getContatoEditar() {
        return contatoEditar;
    }

    public void setContatoEditar(Contato contatoEditar) {
        this.contatoEditar = contatoEditar;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(MenuPrincipal.getMenuPrincipal());
        setEditarContato(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editar_contato.fxml"));
        Parent root = fxmlLoader.load();
        EditarContatoController controller = (EditarContatoController) fxmlLoader.getController();
        controller.preencherCampos(contatoEditar);

        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("Editar Contato");
        stage.setResizable(false);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
