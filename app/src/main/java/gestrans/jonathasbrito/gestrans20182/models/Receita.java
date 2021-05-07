package gestrans.jonathasbrito.gestrans20182.models;

import java.io.Serializable;

/**
 * Created by Brito on 18/11/2018.
 */

public class Receita implements Serializable {

    public Long id;
    public String valor;
    public String tipo;
    public String hora;

    public Receita() {

    }

    public Receita(long id, String valor, String hora, String tipo){
        super();
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.hora = hora;

    }

    public Receita(String valor, String tipo, String hora) {
        super();
        this.valor = valor;
        this.tipo = tipo;
        this.hora = hora;
    }


    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){

        return "R$"+ valor + "  Tipo: "+ tipo;

    }
}
