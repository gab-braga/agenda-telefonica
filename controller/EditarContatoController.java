package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Agenda;
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

    public void preencherCampos(Contato contato) {
        this.id = contato.getId();
        campo_nome.setText(contato.getNome());
        campo_telefone.setText(contato.getTelefone());
        campo_email.setText(contato.getEmail());
        campo_endereco.setText(contato.getEndereco());
    }

    private boolean validarFormulario(String nome, String telefone, String email, String endereco) {
        return !(nome.trim().isEmpty() || telefone.trim().isEmpty() || email.trim().isEmpty() || endereco.trim().isEmpty());
    }

    private void editarContato() {
        String nome = campo_nome.getText();
        String telefone = campo_telefone.getText();
        String email = campo_email.getText();
        String endereco = campo_endereco.getText();

        if(validarFormulario(nome, telefone, email, endereco)) {
            Contato contato = new Contato(this.id, nome, telefone, email, endereco);
            if(Agenda.atualizarContato(contato)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("AVISO");
                alert.setHeaderText(null);
                alert.setContentText("Contato editado.");
                alert.showAndWait();
                sair();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Erro ao editar contato.");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ATENÇÃO");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
        }
    }

    private void sair() {
        EditarContato.getEditarContatoStage().close();
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
