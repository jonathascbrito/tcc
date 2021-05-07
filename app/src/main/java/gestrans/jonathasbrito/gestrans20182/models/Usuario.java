package gestrans.jonathasbrito.gestrans20182.models;

import java.io.Serializable;

/**
 * Created by Brito on 19/11/2018.
 */

public class Usuario implements Serializable {

    public long id;
    public String nome;
    public String senha;
    public String email;
    public String login;


    public Usuario(long id, String nome, String senha, String email, String login) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.login = login;
    }

    public Usuario(String nome, String senha, String email, String login) {

        this(0, nome, senha, email, login);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
