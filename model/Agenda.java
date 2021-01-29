package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Agenda {

    public Agenda() {
        criarBancoDeDados(bancoDeDados);
    }

    String url = "jdbc:mysql://127.0.0.1:3306?verifyServerCertificate=false&useSSl=true";
    final private String usuario = "root";
    final private String senha = "";

    final private String bancoDeDados = "agenda";
    final private String tabela = "contato";

    Connection conexao = null;

    private void abrirConexao() {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        catch(SQLException e) {
            System.err.println("ERRO (Abrir Conexao): " + e.getMessage());
        }
    }

    private void fecharConexao() {
        try {
            conexao.close();
        }
        catch(SQLException e) {
            System.err.println("ERRO (Fechar Conexao): " + e.getMessage());
        }
    }

    private void criarBancoDeDados(String bancoDeDados) {
        abrirConexao();
        try {
            Statement declaracao = conexao.createStatement();
            String sql = String.format("CREATE DATABASE IF NOT EXISTS %s;", bancoDeDados);
            declaracao.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("ERRO (Criar Banco De Dados): " + e.getCause());
        }
        fecharConexao();
    }

    public void adicionarContato(Contato contato) {
        abrirConexao();
        try {
            Statement declaracao = conexao.createStatement();
            String sql = String.format("INSERT INTO contato (nome, numeroTelefone, endereco, email) VALUES ('%s', '%s', '%s', '%s');", contato.getNome(), contato.getNumeroTelefone(), contato.getEndereco(), contato.getEmail());
            declaracao.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("ERRO (Adicionar Contato): " + e.getCause());
        }
        fecharConexao();
    }
}
