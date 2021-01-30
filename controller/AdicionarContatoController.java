package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Agenda;
import model.Contato;
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

    private boolean validarFormulario(String nome, String telefone, String email, String endereco) {
        return !(nome.trim().isEmpty() || telefone.trim().isEmpty() || email.trim().isEmpty() || endereco.trim().isEmpty());
    }

    public void adicionarContato() {
        String nome = campo_nome.getText();
        String telefone = campo_telefone.getText();
        String email = campo_email.getText();
        String endereco = campo_endereco.getText();

        if(validarFormulario(nome, telefone, email, endereco)) {
            Contato contato = new Contato(nome, telefone, email, endereco);
            if(Agenda.adicionarContato(contato)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("AVISO");
                alert.setHeaderText(null);
                alert.setContentText("Contato adicionado.");
                alert.showAndWait();
                sair();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText(null);
                alert.setContentText("Erro ao adicionar contato.");
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
        AdicionarContato.getAdicionarContato().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_cancelar.setOnMouseClicked(click -> {
            sair();
        });

        btn_adicionar.setOnMouseClicked(click -> {
            adicionarContato();
        });
    }
}
