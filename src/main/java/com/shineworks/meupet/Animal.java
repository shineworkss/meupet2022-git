package com.shineworks.meupet;

import javafx.beans.property.*;

public class Animal {
    private IntegerProperty fichaNumero;
    private StringProperty nome, especie, sexo, raca, pelagem, idade, peso, medicacao, hora;
    private BooleanProperty vacinacao, vermifugacao, cadastroDeReserva;

    public Animal() {
        fichaNumero = new SimpleIntegerProperty();
        nome = new SimpleStringProperty();
        especie = new SimpleStringProperty();
        sexo = new SimpleStringProperty();
        raca = new SimpleStringProperty();
        pelagem = new SimpleStringProperty();
        idade = new SimpleStringProperty();
        peso = new SimpleStringProperty();
        medicacao = new SimpleStringProperty();
        hora = new SimpleStringProperty();

        vacinacao = new SimpleBooleanProperty();
        vermifugacao = new SimpleBooleanProperty();
        cadastroDeReserva = new SimpleBooleanProperty();
    }

    public Animal(Animal animal){
        this();

        fichaNumero.set(animal.getFichaNumero());
        nome.set(animal.getNome());
        especie.set(animal.getEspecie());
        sexo.set(animal.getSexo());
        raca.set(animal.getRaca());
        pelagem.set(animal.getPelagem());
        idade.set(animal.getIdade());
        peso.set(animal.getPeso());
        medicacao.set(animal.getMedicacao());
        hora.set(animal.getHora());

        vacinacao.set(animal.isVacinacao());
        vermifugacao.set(animal.isVermifugacao());
        cadastroDeReserva.set(animal.isCadastroDeReserva());
    }

    public void clear() {
        nome.set("");
        especie.set("");
        sexo.set("");
        raca.set("");
        pelagem.set("");
        idade.set("");
        peso.set("");
        medicacao.set("");
        hora.set("");

        vacinacao.set(false);
        vermifugacao.set(false);
        cadastroDeReserva.set(false);
    }
    public int getFichaNumero() {
        return fichaNumero.get();
    }

    public IntegerProperty fichaNumeroProperty() {
        return fichaNumero;
    }

    public void setFichaNumero(int fichaNumero) {
        this.fichaNumero.set(fichaNumero);
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

    public String getEspecie() {
        return especie.get();
    }

    public StringProperty especieProperty() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie.set(especie);
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public String getRaca() {
        return raca.get();
    }

    public StringProperty racaProperty() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca.set(raca);
    }

    public String getPelagem() {
        return pelagem.get();
    }

    public StringProperty pelagemProperty() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem.set(pelagem);
    }

    public String getIdade() {
        return idade.get();
    }

    public StringProperty idadeProperty() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade.set(idade);
    }

    public String getPeso() {
        return peso.get();
    }

    public StringProperty pesoProperty() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso.set(peso);
    }

    public String getMedicacao() {
        return medicacao.get();
    }

    public StringProperty medicacaoProperty() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao.set(medicacao);
    }

    public String getHora() {
        return hora.get();
    }

    public StringProperty horaProperty() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora.set(hora);
    }

    public boolean isVacinacao() {
        return vacinacao.get();
    }

    public BooleanProperty vacinacaoProperty() {
        return vacinacao;
    }

    public void setVacinacao(boolean vacinacao) {
        this.vacinacao.set(vacinacao);
    }

    public boolean isVermifugacao() {
        return vermifugacao.get();
    }

    public BooleanProperty vermifugacaoProperty() {
        return vermifugacao;
    }

    public void setVermifugacao(boolean vermifugacao) {
        this.vermifugacao.set(vermifugacao);
    }

    public boolean isCadastroDeReserva() {
        return cadastroDeReserva.get();
    }

    public BooleanProperty cadastroDeReservaProperty() {
        return cadastroDeReserva;
    }

    public void setCadastroDeReserva(boolean cadastroDeReserva) {
        this.cadastroDeReserva.set(cadastroDeReserva);
    }
}
