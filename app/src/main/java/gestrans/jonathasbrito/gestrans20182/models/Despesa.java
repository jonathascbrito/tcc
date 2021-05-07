package gestrans.jonathasbrito.gestrans20182.models;

import java.io.Serializable;

/**
 * Created by Brito on 20/11/2018.
 */

public class Despesa implements Serializable {

    public long id;
    public String valor;
    public String hora;
    public String tipo;

    public Despesa() {

    }


    public Despesa(long id, String valor, String hora, String tipo){

        super();
        this.id = id;
        this.valor = valor;
        this.hora = hora;
        this.tipo = tipo;

    }

    public Despesa(String valor, String hora, String tipo) {
        super();
        this.valor = valor;
        this.tipo = tipo;
        this.hora = hora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){

        return "R$ "+ valor + "  Tipo: "+ tipo;

    }

}

