package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    public Agenda() {
//        criarBancoDeDados();
        criarTabela();
    }

    final static private String url = "jdbc:mysql://127.0.0.1:3306/agenda?verifyServerCertificate=false&useSSl=true";
    final static private String usuario = "root";
    final static private String senha = "";

    static Connection conexao = null;

    private static void abrirConexao() {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        catch(SQLException e) {
            System.err.println("ERRO (Abrir Conexao): " + e.getMessage());
        }
    }

    private static void fecharConexao() {
        try {
            conexao.close();
        }
        catch(SQLException e) {
            System.err.println("ERRO (Fechar Conexao): " + e.getMessage());
        }
    }

    private void criarBancoDeDados() {
        abrirConexao();
        try {
            Statement declaracao = conexao.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS agenda;";
            declaracao.execute(sql);
        } catch (SQLException e) {
            System.err.println("ERRO (Criar Banco De Dados): " + e.getMessage());
        }
        fecharConexao();
    }

    private void criarTabela() {
        abrirConexao();
        try {
            String sql = "CREATE TABLE IF NOT EXISTS contato(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(80) NOT NULL, " +
                    "telefone VARCHAR(20) NOT NULL, " +
                    "email VARCHAR(80) NOT NULL, " +
                    "endereco VARCHAR(80) NOT NULL" +
                    ")" +
                    "ENGINE=InnoDB;";
            Statement declaracao = conexao.createStatement();
            declaracao.execute(sql);
        } catch (SQLException e) {
            System.err.println("ERRO (Criar Tabela): " + e.getMessage());
        }
        fecharConexao();
    }

    public static boolean adicionarContato(Contato contato) {
        boolean flag = false;
        abrirConexao();
        try {
            String sql = "INSERT INTO contato (nome, telefone, endereco, email) VALUES (?, ?, ?, ?);";
            PreparedStatement declaracao = conexao.prepareStatement(sql);
            declaracao.setString(1, contato.getNome());
            declaracao.setString(2, contato.getTelefone());
            declaracao.setString(3, contato.getEndereco());
            declaracao.setString(4, contato.getEmail());
            declaracao.execute();
            flag = true;
        } catch (SQLException e) {
            System.err.println("ERRO (Adicionar Contato): " + e.getMessage());
        }
        fecharConexao();
        return flag;
    }

    private static List<Contato> obterListaContatos(ResultSet pesquisa) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        while(pesquisa.next()) {
            int id = pesquisa.getInt("id");
            String nome = pesquisa.getString("nome");
            String telefone = pesquisa.getString("telefone");
            String email = pesquisa.getString("email");
            String endereco = pesquisa.getString("endereco");
            contatos.add(new Contato(id, nome, telefone, email, endereco));
        }
        return contatos;
    }

    public static List<Contato> consutarTodosContato() {
        List<Contato> contatos = null;
        abrirConexao();
        try {
            String sql = "SELECT * FROM contato;";
            PreparedStatement declaracao = conexao.prepareStatement(sql);
            contatos = obterListaContatos(declaracao.executeQuery());
        } catch (SQLException e) {
            System.err.println("ERRO (Consutar todos os Contato): " + e.getMessage());
        }
        fecharConexao();
        return contatos;
    }
}