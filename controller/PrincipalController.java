package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Agenda;
import model.Contato;
import view.AdicionarContato;
import view.EditarContato;
import view.Principal;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btn_pesquisar;

    @FXML
    private Button btn_ad_usuario;

    @FXML
    private Button btn_sair;

    @FXML
    private AnchorPane conteiner;

    @FXML
    private TableView<Contato> tabela_contato;

    @FXML
    private TableColumn<Contato, String> coluna_nome;

    @FXML
    private TableColumn<Contato, String> coluna_telefone;

    @FXML
    private TableColumn<Contato, String> coluna_email;

    @FXML
    private TableColumn<Contato, String> coluna_endereco;

    @FXML
    private MenuItem item_atualizar;

    @FXML
    private MenuItem item_editar;

    @FXML
    private MenuItem item_deletar;

    private boolean isFiltro = false;
    private String filtro = null;

    private void pesquisar(String valor) {
        if(valor.trim().isEmpty()) {
            isFiltro = false;
            filtro = null;
            preencherTabela();
        }
        else {
            isFiltro = true;
            filtro = valor;
            preencherTabela();
        }
    }

    private void preencherTabela() {
        List<Contato> contatos = Agenda.consutarTodosContatos();
        if(isFiltro) {
            contatos = Agenda.consutarContatosFiltrados(this.filtro);
        }
        if(contatos.size() > 0) {
            coluna_nome.setCellValueFactory(new PropertyValueFactory<Contato, String>("nome"));
            coluna_telefone.setCellValueFactory(new PropertyValueFactory<Contato, String>("telefone"));
            coluna_email.setCellValueFactory(new PropertyValueFactory<Contato, String>("email"));
            coluna_endereco.setCellValueFactory(new PropertyValueFactory<Contato, String>("endereco"));
            ObservableList<Contato> itens = FXCollections.observableArrayList(contatos);
            tabela_contato.setItems(itens);
            tabela_contato.refresh();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            if(isFiltro) {
                alert.setContentText("Este contato ainda não está na agenda.");
            }
            else {
                alert.setContentText("Ainda não existem contatos na agenda.");
            }
            alert.setHeaderText(null);
            alert.setTitle("ATENÇÃO");
            alert.showAndWait();
        }
    }

    private void editar() throws IOException {
        Contato contato = tabela_contato.getSelectionModel().getSelectedItem();
        if(contato == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("ATENÇÃO");
            alert.setContentText("Selecione um registro!");
            alert.showAndWait();
        }
        else {
            EditarContato editarContato = new EditarContato();
            editarContato.setContatoEdit(contato);
            editarContato.start(new Stage());
        }
    }

    private void deletar() {
        Contato contato = tabela_contato.getSelectionModel().getSelectedItem();
        if(contato == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("ATENÇÃO");
            alert.setContentText("Selecione um registro!");
            alert.showAndWait();
        }
        else {
            Alert cofirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não");

            cofirmacao.setTitle("DELEÇÃO");
            cofirmacao.setHeaderText(null);
            cofirmacao.setContentText("Tem certeza que deseja deletar esse contato?");
            cofirmacao.getButtonTypes().setAll(btnSim, btnNao);
            cofirmacao.showAndWait().ifPresent(botao -> {
                if (botao == btnSim) {
                   Agenda.deletarContato(contato.getId());
                   preencherTabela();
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preencherTabela();

        btn_pesquisar.setOnMouseClicked((action) -> {
            TextInputDialog pesquisa = new TextInputDialog();
            pesquisa.setTitle("Pesquisar");
            pesquisa.setHeaderText(null);
            pesquisa.setContentText("Digite o nome do contato:");
            pesquisa.showAndWait().ifPresent(this::pesquisar);
        });

        btn_ad_usuario.setOnMouseClicked(action -> {
            try {
                AdicionarContato adicionarContato = new AdicionarContato();
                adicionarContato.start(new Stage());
            }
            catch (Exception e) {
                System.err.println("ERRO (Tela Adicionar Contato): " + e.getMessage());
            }
        });

        btn_sair.setOnMouseClicked((action) -> {
            Principal.getPrincipal().close();
        });

        item_atualizar.setOnAction(action -> {
            isFiltro = false;
            filtro = null;
            preencherTabela();
        });

        item_editar.setOnAction(action -> {
            try {
                editar();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        item_deletar.setOnAction(action -> {
            deletar();
        });
    }
}