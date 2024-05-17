package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerEtudiant {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private ChoiceBox<String> filiere;

    @FXML
    private TableColumn<Etudiant, String> filiereTAB;

    @FXML
    private TableColumn<Etudiant, Integer> id;

    @FXML
    private TableView<Etudiant> listEtudiants;

    @FXML
    private TextField nom;

    @FXML
    private TableColumn<Etudiant, String> nomTAB;

    @FXML
    private TextField prenom;

    @FXML
    private TableColumn<Etudiant, String> prenomTAB;

    @FXML
    private RadioButton radioBtn1;

    @FXML
    private RadioButton radioBtn2;

    @FXML
    private ToggleGroup se;

    @FXML
    private TableColumn<Etudiant, String> sexeTAB;

    private EtudiantM etudiantM;
    private ObservableList<Etudiant> etudiantList;

    @FXML
    public void initialize() {
        etudiantM = new EtudiantM();
        
        filiere.setItems(FXCollections.observableArrayList("DSI", "MDW", "RSI", "SEM"));
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomTAB.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomTAB.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeTAB.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        filiereTAB.setCellValueFactory(new PropertyValueFactory<>("filiere"));

        etudiantList = FXCollections.observableArrayList(etudiantM.findAll());
        listEtudiants.setItems(etudiantList);
    }

    @FXML
    void ajouter(ActionEvent event) {
        String nomValue = nom.getText();
        String prenomValue = prenom.getText();
        String filiereValue = filiere.getValue();
        String sexeValue = ((RadioButton) se.getSelectedToggle()).getText();

        if (nomValue.isEmpty() || prenomValue.isEmpty() || filiereValue == null || sexeValue.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all fields");
            return;
        }

        Etudiant etudiant = new Etudiant(0, nomValue, prenomValue, filiereValue, sexeValue);
        boolean isCreated = etudiantM.create(etudiant);

        if (isCreated) {
            etudiantList.add(etudiantM.findById(etudiant.getId())); // Reload the added student from DB to get the actual ID
            listEtudiants.refresh(); // Refresh the TableView to show the new student
            showAlert(Alert.AlertType.INFORMATION, "Success", "Student added successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed", "Could not add student!");
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Etudiant selectedEtudiant = listEtudiants.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            boolean isDeleted = etudiantM.delete(selectedEtudiant);
            if (isDeleted) {
                etudiantList.remove(selectedEtudiant);
                listEtudiants.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed", "Could not delete student!");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Selection Error", "Please select a student to delete");
        }
    }

    @FXML
    void edit(ActionEvent event) {
        Etudiant selectedEtudiant = listEtudiants.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            String nomValue = nom.getText();
            String prenomValue = prenom.getText();
            String filiereValue = filiere.getValue();
            String sexeValue = ((RadioButton) se.getSelectedToggle()).getText();

            if (nomValue.isEmpty() || prenomValue.isEmpty() || filiereValue == null || sexeValue.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all fields");
                return;
            }

            selectedEtudiant.setNom(nomValue);
            selectedEtudiant.setPrenom(prenomValue);
            selectedEtudiant.setFilere(filiereValue);
            selectedEtudiant.setSexe(sexeValue);

            boolean isUpdated = etudiantM.update(selectedEtudiant);
            if (isUpdated) {
                listEtudiants.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Student updated successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Failed", "Could not update student!");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Selection Error", "Please select a student to edit");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
