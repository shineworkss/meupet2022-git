package com.shineworks.meupet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Endereco {
    private StringProperty logradouro, numero, bairro;

    public Endereco() {
        logradouro = new SimpleStringProperty();
        numero = new SimpleStringProperty();
        bairro = new SimpleStringProperty();
    }

    public Endereco(String logradouro, String numero, String bairro){
        this();

        setLogradouro(logradouro);
        setNumero(numero);
        setBairro(bairro);
    }

    public String asString(){
        return getLogradouro() + ", " + getNumero() + " - " + getBairro();
    }

    public String getLogradouro() {
        return logradouro.get();
    }

    public StringProperty logradouroProperty() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro.set(logradouro);
    }

    public String getNumero() {
        return numero.get();
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public String getBairro() {
        return bairro.get();
    }

    public StringProperty bairroProperty() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro.set(bairro);
    }
}
