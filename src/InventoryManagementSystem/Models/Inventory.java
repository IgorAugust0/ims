package InventoryManagementSystem.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    // A lista de todas as peças.
    private static final ObservableList<Part> parts = FXCollections.observableArrayList();
    // A lista de todos os produtos.
    private static final ObservableList<Product> products = FXCollections.observableArrayList();

    /**
     * @param partToBeAdded Uso: Adiciona a peça dada à lista de peças.
     */
    public static void addPart(Part partToBeAdded) {
        parts.add(partToBeAdded);
    }

    /**
     * @param partToBeRemoved Uso: Exclui a peça dada da lista de peças.
     */
    public static void deletePart(Part partToBeRemoved) {
        parts.removeIf(part -> part.equals(partToBeRemoved));
    }

    /**
     * @param productToBeAdded Uso: Adiciona o produto fornecido à lista de
     *                         produtos.
     */
    public static void addProduct(Product productToBeAdded) {
        products.add(productToBeAdded);
    }

    /**
     * @param productToBeDeleted Uso: Remove o produto fornecido da lista de
     *                           produtos.
     */
    public static void removeProduct(Product productToBeDeleted) {
        products.removeIf(product -> product == productToBeDeleted);
    }

    /**
     * @return the products.
     *         Uso: Retorna todos os produtos.
     */
    public static ObservableList<Product> getProducts() {
        return products;
    }

    /**
     * @return the parts
     *         Uso: Retorna todas as peças.
     */
    public static ObservableList<Part> getParts() {
        return parts;
    }
}
