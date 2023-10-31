package Entidades;

import java.util.Objects;

public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private Integer fkempresa;


    public void autenticarUsuario(String emailUsuario, String senhaUsuario){
        Usuario usuario = new Usuario();

    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFkempresa() {
        return fkempresa;
    }

    public void setFkempresa(Integer fkempresa) {
        this.fkempresa = fkempresa;
    }
}
