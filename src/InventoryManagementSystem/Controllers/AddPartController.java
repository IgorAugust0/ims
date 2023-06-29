package InventoryManagementSystem.Controllers;

import InventoryManagementSystem.Models.InhousePart;
import InventoryManagementSystem.Models.Inventory;
import InventoryManagementSystem.Models.OutsourcedPart;
import InventoryManagementSystem.Models.Part;
import InventoryManagementSystem.Properties;

import java.io.IOException;
import java.net.URL;
import java.util.List;
// import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddPartController implements Initializable {
    // A label FXML que exibe mensagens de erro.
    @FXML
    private Label errorLabel;
    // O campo de texto FXML que exibe o ID da peça.
    @FXML
    private TextField idTextField, nameTextField, priceCostTextField, maxTextField, minTextField,
            invTextField, companyNameTextField, machineIdTextField;
    // A HBox FXML que contém o campo de texto da empresa.
    @FXML
    private HBox companyNameHBox, machineIDHBox;
    // O botão de radio FXML que seleciona a peça como sendo feita na própria
    // empresa.
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     * @param location  Uso: localização usada para resolver caminhos relativos para
     *                  o objeto raiz ou objeto nulo se o local não for conhecido.
     * @param resources Uso: Chamado quando a cena é carregada.
     *                  Inicializa a cena.
     */
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        List<Part> parts = Inventory.getParts();
        int id = 1;
        for (Part part : parts) {
            if (part.getPartId() >= id) {
                id = part.getPartId() + 1;
            }
        }
        inHouseRadioButton.setSelected(true);
        companyNameHBox.setVisible(false);
        machineIDHBox.setVisible(true);
        idTextField.setText(String.valueOf(id));
        errorLabel.setVisible(false);
    }

    // public void initialize(URL location, ResourceBundle resources) {
    // int index = Inventory.getParts().size();
    // int id = Inventory.getParts().get(index).getId() + 1;
    // inHouseRadioButton.setSelected(true);
    // companyNameHBox.setVisible(false);
    // machineIDHBox.setVisible(true);
    // idTextField.setText(String.valueOf(id));
    // errorLabel.setVisible(false);
    // }

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão cancelar.
     *              Se o usuário confirmar, as alterações do usuário serão
     *              perdidas.
     * @throws IOException se os recursos não estiverem disponíveis ao acessá-los.
     */
    @FXML
    public void onCancelButtonPress(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
        alert.setHeaderText(Properties.getDIALOG_HEADER());
        alert.setContentText(Properties.getCANCEL_CONFIRMATION_PROMPT());

        alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
            try {
                goToMainView(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @param event Uso: Chamado quando o usuário seleciona um botão de radio no
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

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão salvar.
     *              Se os valores inseridos forem válidos, a peça é salva.
     */
    @FXML
    public void onSaveButtonPress(ActionEvent event) {

        try {
            int id = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            int inv = Integer.parseInt(invTextField.getText());
            double priceCost = Double.parseDouble(priceCostTextField.getText());
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
                    InhousePart newPart = new InhousePart(id, name, priceCost, inv, min, max, machineId);
                    Inventory.addPart(newPart);
                    goToMainView(event);
                } else {
                    String companyName = companyNameTextField.getText();
                    OutsourcedPart newPart = new OutsourcedPart(id, name, priceCost, inv, min, max, companyName);
                    Inventory.addPart(newPart);
                    goToMainView(event);
                }
            }
        } catch (Exception e) {
            errorLabel.setText("ERROR: Please correct your values, they contain invalid characters.");
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
}
