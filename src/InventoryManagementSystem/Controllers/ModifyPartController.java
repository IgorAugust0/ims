package InventoryManagementSystem.Controllers;

import InventoryManagementSystem.Models.OutsourcedPart;
import InventoryManagementSystem.Models.Part;
import InventoryManagementSystem.Models.InhousePart;
import InventoryManagementSystem.Models.Inventory;
import InventoryManagementSystem.Properties;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ModifyPartController implements Initializable {
    // A peça que está sendo modificada no momento.
    private static Part partToBeModified = null;

    // Rótulos FXML
    @FXML
    private Label errorLabel;
    // Campos de texto FXML
    @FXML
    private TextField idTextField, nameTextField, invTextField, priceCostTextField, maxTextField,
            minTextField, companyNameTextField, machineIdTextField;
    // HBoxes FXML
    @FXML
    private HBox companyNameHBox, machineIDHBox;
    // Botões radio FXML
    @FXML
    private RadioButton inHouseRadioButton, outsourcedRadioButton;

    /**
     * @param location  Uso: localização usada para resolver caminhos relativos para
     *                  o objeto raiz ou objeto nulo se o local não for conhecido.
     * @param resources Uso: Chamado quando a cena é carregada.
     *                  Inicializa a cena.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateFields();
        errorLabel.setVisible(false);
    }

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão cancelar.
     *              Se o usuário confirmar, as alterações do usuário serão
     *              perdidas e ele será levado à cena principal.
     * @throws IOException se os recursos não estiverem disponíveis ao acessá-los.
     */
    @FXML
    public void onCancelButtonPress(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
        alert.setHeaderText(Properties.getDIALOG_HEADER());
        alert.setContentText(Properties.getCANCEL_CONFIRMATION_PROMPT());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            goToMainView(event);
        }
    }

    /**
     * @param partToBeModified Uso: Define a peça a ser modificada.
     */
    public static void setPartToBeModified(Part partToBeModified) {
        ModifyPartController.partToBeModified = partToBeModified;
    }

    /**
     * Uso: Preenche os campos com base nos valores atuais da peça.
     */
    private void populateFields() {

        if (partToBeModified instanceof InhousePart) {
            InhousePart part = (InhousePart) partToBeModified;
            idTextField.setText(String.valueOf(part.getPartId()));
            nameTextField.setText(part.getName());
            invTextField.setText(String.valueOf(part.getInv()));
            priceCostTextField.setText(String.valueOf(part.getPrice()));
            maxTextField.setText(String.valueOf(part.getMax()));
            minTextField.setText(String.valueOf(part.getMin()));
            machineIdTextField.setText(String.valueOf(part.getMachineId()));

            inHouseRadioButton.setSelected(true);
            companyNameHBox.setVisible(false);
            machineIDHBox.setVisible(true);
        } else {
            OutsourcedPart part = (OutsourcedPart) partToBeModified;
            idTextField.setText(String.valueOf(part.getPartId()));
            nameTextField.setText(part.getName());
            invTextField.setText(String.valueOf(part.getInv()));
            priceCostTextField.setText(String.valueOf(part.getPrice()));
            maxTextField.setText(String.valueOf(part.getMax()));
            minTextField.setText(String.valueOf(part.getMin()));
            companyNameTextField.setText(String.valueOf(part.getCompanyName()));

            outsourcedRadioButton.setSelected(true);
            companyNameHBox.setVisible(true);
            machineIDHBox.setVisible(false);
        }
    }

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão salvar.
     *              Se os valores inseridos forem válidos, a peça antiga é removida
     *              e a nova peça é salva.
     */
    @FXML
    public void onSaveButtonPress(ActionEvent event) {

        try {
            int id = partToBeModified.getPartId();
            String name = nameTextField.getText();
            int inv = Integer.parseInt(invTextField.getText());
            double price = Double.parseDouble(priceCostTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());

            if (min > max) {
                errorLabel.setText("ERROR: Please correct your Max/Min values (min must be less than max).");
                errorLabel.setVisible(true);
            } else if (inv > max || inv < min) {
                errorLabel.setText("ERROR: Please correct your inv value (inv must be between min and max).");
                errorLabel.setVisible(true);
            } else {
                if (inHouseRadioButton.isSelected()) {
                    int machineId = Integer.parseInt(machineIdTextField.getText());
                    InhousePart replacementPart = new InhousePart(id, name, price, inv, min, max, machineId);

                    Inventory.deletePart(partToBeModified);
                    Inventory.addPart(replacementPart);
                } else {
                    String companyName = companyNameTextField.getText();
                    OutsourcedPart replacementPart = new OutsourcedPart(id, name, price, inv, min, max, companyName);

                    Inventory.deletePart(partToBeModified);
                    Inventory.addPart(replacementPart);
                }
                goToMainView(event);
            }
        } catch (Exception e) {
            errorLabel.setText(Properties.getINVALID_DATA_MESSAGE());
            errorLabel.setVisible(true);
        }
    }

    /**
     * @param event Uso: Altera a cena para a cena principal.
     * @throws IOException se os recursos não estiverem disponíveis ao acessá-los.
     */
    private void goToMainView(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/InventoryManagementSystem/Views/Main.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MainScene);
        MainScene.getRoot().requestFocus();
        window.show();
    }

    /**
     * @param event Uso: Chamado quando o usuário seleciona um botão de opção no
     *              grupo de alternância do tipo de peça.
     *              Altera os campos e rótulos que são exibidos.
     */
    @FXML
    public void onPartTypeToggleChange(ActionEvent event) {
        if (inHouseRadioButton.isSelected()) {
            companyNameHBox.setVisible(false);
            machineIDHBox.setVisible(true);
        } else {
            companyNameHBox.setVisible(true);
            machineIDHBox.setVisible(false);
        }
    }
}
