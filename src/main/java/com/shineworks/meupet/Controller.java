package com.shineworks.meupet;

import javafx.beans.binding.Bindings;
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
    private Label infoCaes, infoGatos, infoFichas;

    @FXML
    private TextField tutorNome,  tutorEndereco, tutorBairro, animalNome, animalMedicacao;

    @FXML
    private MNumberField tutorRg, tutorCpf, tutorTelefone, tutorNumero;

    @FXML
    private ComboBox animalEspecie, animalSexo, animalRaca, animalPelagem, animalIdade, animalPeso, responsavelNome;

    @FXML
    private CheckBox animalVacinacao, animalVermifugacao;

    @FXML
    private TableView<Animal> tableview;

    private Tutor tutor;
    private Animal animal;
    private ObservableList<Animal> animais;
    private AtlasConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tutor = new Tutor();
        animal = new Animal();
        animais = FXCollections.observableArrayList();
        tableview.setItems(animais);

        connection = new AtlasConnection(tutor, animal);

        if (!connection.isAtivo()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Sua chave do MeuPet 2022 expirou!");
            alert.setContentText("Maiores informações: Marcelo Perez Maciel - (44) 99949-3354");
            alert.showAndWait();

            System.exit(1);
        }

        maskNumberFields();
        initComboBoxes();
        bindFields();

        //code
    }

    private void maskNumberFields(){
        tutorRg.installMask("##.###.###-#");
        tutorCpf.installMask(MNumberField.CPF);
        tutorTelefone.installMask(MNumberField.TELEFONE);
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
        /**
         * Tutor Bindings
         */
        tutorNome.textProperty().bindBidirectional(tutor.nomeProperty());
        tutorRg.textProperty().bindBidirectional(tutor.rgProperty());
        tutorCpf.textProperty().bindBidirectional(tutor.cpfProperty());
        tutorTelefone.textProperty().bindBidirectional(tutor.telefoneProperty());
        tutorEndereco.textProperty().bindBidirectional(tutor.getEndereco().logradouroProperty());
        tutorNumero.textProperty().bindBidirectional(tutor.getEndereco().numeroProperty());
        tutorBairro.textProperty().bindBidirectional(tutor.getEndereco().bairroProperty());

        /**
         * Animal Bindings
         */
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

        /**
         * Tableview Bindings
         */
        tableview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("especie"));
        tableview.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tableview.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("hora"));
        //tableview.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cadastroDeReserva"));
        //https://stackoverflow.com/questions/36436169/boolean-to-string-in-tableview-javafx
        TableColumn<Animal, Boolean> crColumn = (TableColumn<Animal, Boolean>) tableview.getColumns().get(4);
        crColumn.setCellValueFactory(cr -> cr.getValue().cadastroDeReservaProperty());
        crColumn.setCellFactory(col -> new TableCell<Animal, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item ? "CADASTRO DE RESERVA" : "");
            }
        });

        /**
         * Info Labels bindings
         */
        //https://stackoverflow.com/questions/25325209/javafx-concatenation-of-multiple-stringproperty
        //http://www.java2s.com/Code/Java/JavaFX/ChangeListenerforIntegerProperty.htm
        infoCaes.textProperty().bind(Bindings.concat("Cães: ", connection.caoCounterProperty(), "/80"));
        infoGatos.textProperty().bind(Bindings.concat("Gatos: ", connection.gatoCounterProperty(), "/70"));
        infoFichas.textProperty().bind(Bindings.concat("Ficha Numero: ", connection.fichaNumeroProperty()));
    }

    @FXML
    private void adicionarAnimal(ActionEvent e) {
        if (animal.getEspecie().equals("CÃO")) { //animal é um cao
            int cao = connection.caoCounterProperty().get();
            if (1 <= cao && cao <= 15) { // caes 10:00h 15 vagas
                animal.setHora("10:00h");
            } else if (cao <= 30) { // caes 11:00h 15 vagas
                animal.setHora("11:00h");
            } else if (cao <= 45) { // caes 12:00h 15 vagas
                animal.setHora("12:00h");
            } else if (cao <= 60) { // caes 13:00h 15 vagas
                animal.setHora("13:00h");
            } else if (cao <= 65) { // caes 10:00h 5 vagas RESERVA
                animal.setHora("10:00h");
                animal.setCadastroDeReserva(true);
            } else if (cao <= 70) { // caes 11:00h 5 vagas RESERVA
                animal.setHora("11:00h");
                animal.setCadastroDeReserva(true);
            } else if (cao <= 75) { // caes 12:00h 5 vagas RESERVA
                animal.setHora("12:00h");
                animal.setCadastroDeReserva(true);
            } else if (cao <= 80) { // caes 13:00h 5 vagas RESERVA
                animal.setHora("13:00h");
                animal.setCadastroDeReserva(true);
            } else { // sem vagas
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Não existem mais vagas para Cães");
                alert.setContentText("Orientar o tutor a aguardar por alguma possível desistência.");
                alert.showAndWait();
                return;
            }

        } else if (animal.getEspecie().equals("GATO")) { //animal é um gato
            int gato = connection.gatoCounterProperty().get();
            if (1 <= gato && gato <= 25) { // gatos 08:00h 25 vagas
                animal.setHora("08:00h");
            } else if (gato <= 50) { // gatos 09:00h 25 vagas
                animal.setHora("09:00h");
            } else if (gato <= 60) { // gatos 08:00h 10 vagas RESERVA
                animal.setHora("08:00h");
                animal.setCadastroDeReserva(true);
            } else if (gato <= 70) { // gatos 09:00h 10 vagas RESERVA
                animal.setHora("09:00h");
                animal.setCadastroDeReserva(true);
            } else { // sem vagas
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Não existem mais vagas para GATOS");
                alert.setContentText("Orientar o tutor a aguardar por alguma possível desistência.");
                alert.showAndWait();
                return;
            }
        }
        //insert
        animal.setFichaNumero(connection.fichaNumeroProperty().get()); //adicionar numero da ficha ao animal
        connection.insertAnimal(); //banco
        connection.incrementFichaNumero(); //increment ficha
        if (animal.getEspecie().equals("CÃO")) {
            connection.incrementCao(); //increment cao
        } else {
            connection.incrementGato();//increment cao if not cao
        }
        animais.add(new Animal(animal)); //tableview
    }

    @FXML
    private void gerarFormularios(ActionEvent e) {
        connection.insertTutor(); //inserir tutor

        //gerar pdfs



        tutor.clear(); //clear fields
        animal.clear();
        animais.clear();
    }
}