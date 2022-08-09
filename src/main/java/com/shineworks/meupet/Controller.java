package com.shineworks.meupet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label infoLabel;

    @FXML
    private TextField tutorNome, tutorRg, tutorCpf, tutorTelefone, tutorEndereco, tutorNumero, tutorBairro,
            animalNome, animalMedicacao;

    @FXML
    private ComboBox animalEspecie, animalSexo, animalRaca, animalPelagem, animalIdade, animalPeso, responsavelNome;

    @FXML
    private CheckBox animalVacinacao, animalVermifugacao;

    @FXML
    private TableView<Animal> tableview;

    private Tutor tutor;
    private Animal animal;
    private ObservableList<Animal> animais;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tutor = new Tutor();
        animal = new Animal();
        animais = FXCollections.observableArrayList();
        tableview.setItems(animais);

        initComboBoxes();
        bindFields();

        //code

    }

    private void initComboBoxes() {
        animalEspecie.getItems().addAll("CÃO", "GATO");
        animalSexo.getItems().addAll("MACHO", "FÊMEA");
        animalPelagem.getItems().addAll("PRETO", "BRANCO", "CARAMELO", "MARROM", "MESCLADO");
        animalIdade.getItems().addAll("6 MESES", "1 A 2 ANOS", "3 A 4 ANOS", "5 A 6 ANOS", "7 A 8 ANOS");
        animalPeso.getItems().addAll("2 A 5 QUILOS", "5 A 10 QUILOS", "10 A 15 QUILOS", "15 A 20 QUILOS", "20 A 25 QUILOS", "25 A 30 QUILOS", "+30 QUILOS");
        animalRaca.getItems().addAll("SDR - SEM RAÇA DEFINIDA");
        responsavelNome.getItems().addAll("Emily");
        responsavelNome.getSelectionModel().selectFirst();
    }

    private void bindFields() {
        tutorNome.textProperty().bindBidirectional(tutor.nomeProperty());
        tutorRg.textProperty().bindBidirectional(tutor.rgProperty());
        tutorCpf.textProperty().bindBidirectional(tutor.cpfProperty());
        tutorTelefone.textProperty().bindBidirectional(tutor.telefoneProperty());
        tutorEndereco.textProperty().bindBidirectional(tutor.getEndereco().logradouroProperty());
        tutorNumero.textProperty().bindBidirectional(tutor.getEndereco().numeroProperty());
        tutorBairro.textProperty().bindBidirectional(tutor.getEndereco().bairroProperty());

        animalNome.textProperty().bindBidirectional(animal.nomeProperty());
        animalEspecie.valueProperty().bindBidirectional(animal.especieProperty());
        animalSexo.valueProperty().bindBidirectional(animal.sexoProperty());
        animalRaca.valueProperty().bindBidirectional(animal.racaProperty());
        animalPelagem.valueProperty().bindBidirectional(animal.pelagemProperty());
        animalIdade.valueProperty().bindBidirectional(animal.idadeProperty());
        animalPeso.valueProperty().bindBidirectional(animal.pesoProperty());
        animalMedicacao.textProperty().bindBidirectional(animal.medicacaoProperty());
        animalVacinacao.selectedProperty().bindBidirectional(animal.vacinacaoProperty());
        animalVermifugacao.selectedProperty().bindBidirectional(animal.vermifugacaoProperty());

        tableview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("especie"));
        tableview.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableview.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("idade"));
        tableview.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("hora"));
    }

    @FXML
    private void adicionarAnimal(ActionEvent e) {
        animais.add(animal);
    }

    @FXML
    private void gerarFormularios(ActionEvent e) {
        System.out.println(tutor.toString());
    }
}