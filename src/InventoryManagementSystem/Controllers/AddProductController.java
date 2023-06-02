package InventoryManagementSystem.Controllers;

import InventoryManagementSystem.Models.Part;
import InventoryManagementSystem.Models.Inventory;
import InventoryManagementSystem.Models.Product;
import InventoryManagementSystem.Properties;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProductController implements Initializable {
    // Rótulos FXML
    @FXML
    private Label partErrorLabel;
    // Campos de texto FXML
    @FXML
    private TextField partQueryTextField, idTextField, nameTextField, minTextField, maxTextField,
            invTextField, priceTextField;
    // Tabelas FXML
    @FXML
    private TableView<Part> partTable, selectedPartTable;
    // Colunas da tabela de peças FXML
    @FXML
    private TableColumn<Part, Integer> partTableIdColumn;
    @FXML
    private TableColumn<Part, String> partTableNameColumn;
    @FXML
    private TableColumn<Part, Integer> partTableInventoryColumn;
    @FXML
    private TableColumn<Part, Double> partTablePriceColumn;
    // Colunas da tabela de peças selecionadas FXML
    @FXML
    private TableColumn<Part, Integer> selectedPartTableIdColumn;
    @FXML
    private TableColumn<Part, String> selectedPartTableNameColumn;
    @FXML
    private TableColumn<Part, Integer> selectedPartTableInventoryColumn;
    @FXML
    private TableColumn<Part, Double> selectedPartTablePriceColumn;

    /**
     * @param location  Uso: localização usada para resolver caminhos relativos para
     *                  o objeto raiz ou objeto nulo se o local não for conhecido.
     * @param resources Uso: Chamado quando a cena é carregada.
     *                  Inicializa a cena.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partTableIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partId"));
        partTableNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inv"));
        partTablePriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        partTable.getItems().setAll(populatePartsTable());
        // A tabela de peças não precisa definir seu texto de espaço reservado porque o
        // usuário não será
        // capaz de acessar esta cena se eles não tiverem peças.
        selectedPartTable.setPlaceholder(new Label(Properties.getTABLE_PLACEHOLDER_MESSAGE()));
        idTextField.setText(String.valueOf(Inventory.getProducts().size() + 1));
        partErrorLabel.setVisible(false);
    }

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão cancelar.
     *              Se o usuário confirmar, as alterações do usuário serão
     *              perdidas.
     * @throws IOException se os recursos não estiverem disponíveis ao acessá-los.
     */
    @FXML
    public void onCancelButtonPress(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
        alert.setHeaderText(Properties.getDIALOG_HEADER());
        alert.setContentText(Properties.getCANCEL_CONFIRMATION_PROMPT());

        // Optional<ButtonType> result = alert.showAndWait();
        // if (result.get() == ButtonType.OK) {
        // goToMainScene(event);
        // }

        alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
            try {
                goToMainScene(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão Adicionar.
     *              Se o usuário tiver uma peça selecionada, ela será adicionada à
     *              tabela de peças selecionadas.
     */
    @FXML
    public void onAddButtonPress(ActionEvent event) {
        if (partTable.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = partTable.getSelectionModel().getSelectedItem();
            addToSelectedPartsTable(selectedPart);

            selectedPartTable.refresh();
        } else {
            partErrorLabel.setText(Properties.getTELL_USER_TO_SELECT_ELEMENT_MESSAGE());
            partErrorLabel.setVisible(true);
        }
    }

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão Excluir.
     *              Se o usuário selecionou uma peça, a peça é excluída da tabela
     *              de peças selecionadas.
     */
    @FXML
    void onDeleteButtonPress(ActionEvent event) {
        if (selectedPartTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
            alert.setHeaderText(Properties.getDIALOG_HEADER());
            alert.setContentText(Properties.getDELETE_CONFIRMATION_PROMPT());

            // Optional<ButtonType> result = alert.showAndWait();
            // if (result.get() == ButtonType.OK) {
            //     Part selectedPart = selectedPartTable.getSelectionModel().getSelectedItem();
            //     partTable.refresh();
            //     selectedPartTable.getItems().remove(selectedPart);
            //     selectedPartTable.refresh();
            // }

            alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
                Part selectedPart = selectedPartTable.getSelectionModel().getSelectedItem();
                partTable.refresh();
                selectedPartTable.getItems().remove(selectedPart);
                selectedPartTable.refresh();
            });
        } else {
            partErrorLabel.setText(Properties.getTELL_USER_TO_SELECT_ELEMENT_MESSAGE());
            partErrorLabel.setVisible(true);
        }
    }

    /**
     * @param event Uso: Chamado quando o usuário pressiona o botão salvar.
     *              Se os valores inseridos forem válidos, o produto é
     *              adicionado ao inventário.
     * @throws IOException se os recursos não estiverem disponíveis ao acessá-los.
     */
    @FXML
    public void onSaveButtonPress(ActionEvent event) throws IOException {

        try {
            if (nameTextField.getText().isEmpty()
                    || invTextField.getText().isEmpty()
                    || priceTextField.getText().isEmpty()
                    || minTextField.getText().isEmpty()
                    || maxTextField.getText().isEmpty()) {
                partErrorLabel.setText("ERROR: Please populate all fields.");
                partErrorLabel.setVisible(true);
            } else {
                int id = Integer.parseInt(idTextField.getText());
                String name = nameTextField.getText();
                int min = Integer.parseInt(minTextField.getText());
                int max = Integer.parseInt(maxTextField.getText());
                int inv = Integer.parseInt(invTextField.getText());
                double price = Double.parseDouble(priceTextField.getText());
                ObservableList<Part> parts = selectedPartTable.getItems();

                if (parts.isEmpty()) {
                    partErrorLabel.setText("ERROR: You must associate at least one Part with the Product.");
                    partErrorLabel.setVisible(true);
                } else {
                    double totalPartsPrice = 0.00;
                    for (int i = 0; i < parts.size(); i++) {
                        totalPartsPrice += parts.get(i).getPrice();
                    }
                    if (price < totalPartsPrice) {
                        partErrorLabel.setText("ERROR: The Product's price must be at least the sum of it's Parts ("
                                + totalPartsPrice + ").");
                        partErrorLabel.setVisible(true);
                    } else {
                        Product newProduct = new Product(id, name, min, max, inv, price, parts);
                        Inventory.addProduct(newProduct);
                        goToMainScene(event);
                    }
                }
            }
        } catch (Exception e) {
            partErrorLabel.setText(Properties.getINVALID_DATA_MESSAGE());
            partErrorLabel.setVisible(true);
        }
    }

    /**
     * @return um arraylist de peças.
     *         Uso: Preenche a tabela de peças com peças do inventário.
     */
    private List<Part> populatePartsTable() {
        ArrayList<Part> list = new ArrayList<Part>();
        for (int i = 0; i < Inventory.getParts().size(); i++) {
            list.add(Inventory.getParts().get(i));
        }
        return list;
    }

    /**
     * @param part Uso: Adiciona a peça à tabela de peças selecionadas.
     */
    private void addToSelectedPartsTable(Part part) {
        selectedPartTableIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partId"));
        selectedPartTableNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        selectedPartTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inv"));
        selectedPartTablePriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        selectedPartTable.getItems().add(part);
    }

    /**
     * Uso: Chamado quando o usuário pressiona o botão de pesquisa de peças.
     * Filtra a tabela de peças com base na consulta do usuário.
     */
    @FXML
    public void onPartSearchButtonPress() {
        String query = partQueryTextField.getText();
        if (query.isEmpty()) {
            partTable.getItems().setAll(populatePartsTable());
        } else {
            ObservableList<Part> queryMatches = FXCollections.observableArrayList();
            for (int i = 0; i < Inventory.getParts().size(); i++) {
                if (Inventory.getParts().get(i).getName().toLowerCase().contains(query.toLowerCase())) {
                    queryMatches.add(Inventory.getParts().get(i));
                }
            }
            partTable.getItems().setAll(queryMatches);
        }
    }

    /**
     * @param event Uso: Altera a cena para a cena principal.
     * @throws IOException se os recursos não estiverem disponíveis ao acessá-los.
     */
    private void goToMainScene(ActionEvent event) throws IOException {
        Parent MainParent = FXMLLoader.load(getClass().getResource("/InventoryManagementSystem/Views/Main.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MainScene);
        MainScene.getRoot().requestFocus();
        window.show();
    }
}
