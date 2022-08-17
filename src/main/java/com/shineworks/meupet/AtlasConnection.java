package com.shineworks.meupet;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.bson.Document;
import org.bson.types.ObjectId;

public class AtlasConnection {

    private MongoCollection<Document> tutores, animais, config;
    private Tutor tutor;
    private Animal animal;
    private IntegerProperty caoCounter, gatoCounter, fichaNumero;
    private boolean ativo;

    public AtlasConnection(Tutor tutor, Animal animal) {
        this.tutor = tutor;
        this.animal = animal;

        ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:admin@cluster0.flgy1h4.mongodb.net/?retryWrites=true&w=majority");
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("banco");

        tutores = database.getCollection("tutores");
        animais = database.getCollection("animais");
        config = database.getCollection("config");

        FindIterable<Document> documents = config.find(new BasicDBObject().append("_id", new ObjectId("62f3ebbec9d3a3797d7c72c5")));
        Document first = documents.first();
        caoCounter = new SimpleIntegerProperty(first.getInteger("cao_counter"));
        gatoCounter = new SimpleIntegerProperty(first.getInteger("gato_counter"));
        fichaNumero = new SimpleIntegerProperty(first.getInteger("ficha_numero"));
        ativo = first.getBoolean("ativo");
    }

    public boolean isAtivo() {
        return ativo;
    }

    public IntegerProperty caoCounterProperty() {
        return caoCounter;
    }

    public void incrementCao() {
        caoCounter.set(caoCounter.get() + 1);
        //https://www.mongodb.com/community/forums/t/get-object-id-as-primary-key-to-a-return-method-in-java/10233/2
        config.updateOne(Filters.eq("_id", new ObjectId("62f3ebbec9d3a3797d7c72c5")), Updates.set("cao_counter", caoCounter.get()));
    }

    public IntegerProperty gatoCounterProperty() {
        return gatoCounter;
    }

    public void incrementGato() {
        gatoCounter.set(gatoCounter.get() + 1);
        //https://www.mongodb.com/community/forums/t/get-object-id-as-primary-key-to-a-return-method-in-java/10233/2
        config.updateOne(Filters.eq("_id", new ObjectId("62f3ebbec9d3a3797d7c72c5")), Updates.set("gato_counter", gatoCounter.get()));
    }

    public IntegerProperty fichaNumeroProperty() {
        return fichaNumero;
    }

    public void incrementFichaNumero() {
        fichaNumero.set(fichaNumero.get() + 1);
        //https://www.mongodb.com/community/forums/t/get-object-id-as-primary-key-to-a-return-method-in-java/10233/2
        config.updateOne(Filters.eq("_id", new ObjectId("62f3ebbec9d3a3797d7c72c5")), Updates.set("ficha_numero", fichaNumero.get()));
    }

    public void insertTutor() {
        tutores.insertOne(new Document()
                .append("nome", tutor.getNome())
                .append("rg", tutor.getRg())
                .append("cpf", tutor.getCpf())
                .append("telefone", tutor.getTelefone())
                .append("endereco", tutor.getEndereco().asString())
        );
    }

    public void insertAnimal() {
        animais.insertOne(new Document()
                .append("tutorCpf", tutor.getCpf())
                .append("fichaNumero", animal.getFichaNumero())
                .append("nome", animal.getNome())
                .append("especie", animal.getEspecie())
                .append("sexo", animal.getSexo())
                .append("raca", animal.getRaca())
                .append("pelagem", animal.getPelagem())
                .append("idade", animal.getIdade())
                .append("peso", animal.getPeso())
                .append("medicacao", animal.getMedicacao())
                .append("hora", animal.getHora())
                .append("vacinacao", animal.isVacinacao())
                .append("vermifugacao", animal.isVermifugacao())
                .append("cadastroDeReserva", animal.isCadastroDeReserva())
        );
    }
}
