package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.AdicionarContato;

import java.net.URL;
import java.util.ResourceBundle;

public class AdicionarContatoController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField campo_nome;

    @FXML
    private TextField campo_telefone;

    @FXML
    private TextField campo_email;

    @FXML
    private TextField campo_endereco;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_adicionar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_cancelar.setOnMouseClicked(click -> {
            AdicionarContato.getAdicionarContato().close();
        });
    }
}
