package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Contato;
import view.EditarContato;

import java.net.URL;
import java.util.ResourceBundle;

public class EditarContatoController implements Initializable {

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
    private Button btn_editar;

    private int id;

    private void editarContato() {
        System.out.println(id);
    }

    private void sair() {
        EditarContato.getEditarContato().close();
    }

    public void preencherCampos(Contato contato) {
        id = contato.getId();
        campo_nome.setText(contato.getNome());
        campo_telefone.setText(contato.getTelefone());
        campo_email.setText(contato.getEmail());
        campo_endereco.setText(contato.getEndereco());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_cancelar.setOnMouseClicked(click -> {
            sair();
        });

        btn_editar.setOnMouseClicked(click -> {
            editarContato();
        });
    }

}
