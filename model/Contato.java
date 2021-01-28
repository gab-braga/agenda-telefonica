package model;

public class Contato {
    private String nome;
    private String numeroTelefone;
    private String email;
    private String endereco;

    public Contato() {
    }

    public Contato(String nome, String numeroTelefone, String email, String endereco) {
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
