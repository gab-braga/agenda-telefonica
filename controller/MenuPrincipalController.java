package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.AdicionarContato;
import view.MenuPrincipal;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {

    @FXML
    private Button btn_pesquisar;

    @FXML
    private Button btn_ad_usuario;

    @FXML
    private Button btn_sair;

    @FXML
    private AnchorPane conteiner;

    private void preencherTabela(){

    }

    private void pesquisar(String valor) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NADA");
        alert.setHeaderText(null);
        alert.setContentText(valor);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_pesquisar.setOnMouseClicked((action) -> {
            TextInputDialog dialogoNome = new TextInputDialog();
            dialogoNome.setTitle("Pesquisar");
            dialogoNome.setHeaderText("Entre com o nome do contato");
            dialogoNome.setContentText(null);
            dialogoNome.showAndWait().ifPresent(this::pesquisar);
        });

        btn_ad_usuario.setOnMouseClicked(action -> {
            try {
                AdicionarContato adicionarContato = new AdicionarContato();
                adicionarContato.start(new Stage());
            }
            catch (Exception ex) {
                System.err.println("ERRO: " + ex.getCause());
            }
        });

        btn_sair.setOnMouseClicked((action) -> {
            MenuPrincipal.getMenuPrincipal().close();
        });
    }
}