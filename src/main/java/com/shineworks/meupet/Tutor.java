package com.shineworks.meupet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tutor {
    private StringProperty nome, rg, cpf, telefone;
    private Endereco endereco;

    public Tutor() {
        nome = new SimpleStringProperty();
        rg = new SimpleStringProperty();
        cpf = new SimpleStringProperty();
        telefone = new SimpleStringProperty();
        endereco = new Endereco();
    }

    public Tutor(String nome, String rg, String cpf, String telefone, Endereco endereco) {
        this();

        setNome(nome);
        setRg(rg);
        setCpf(cpf);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    public String toString() {
        return nome.get() + " | " +
                rg.get() + " | " +
                cpf.get() + " | " +
                telefone.get() + " | " +
                endereco.getLogradouro() + ", " +
                endereco.getNumero() + " - " +
                endereco.getBairro();
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getRg() {
        return rg.get();
    }

    public StringProperty rgProperty() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg.set(rg);
    }

    public String getCpf() {
        return cpf.get();
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
