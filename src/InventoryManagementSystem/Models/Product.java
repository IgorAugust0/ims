package InventoryManagementSystem.Models;

import javafx.collections.ObservableList;

public class Product {
    // As peças associadas a este produto
    // ObservableList é uma interface e uma implementação de uma lista dinâmica
    // que permite que os elementos sejam observados. Padrão de projeto Observer.
    private final ObservableList<Part> associatedParts;
    private final int productId;
    private final int inv;
    private final int min;
    private final int max;
    // O nome do produto
    private final String name;
    // O preço do produto
    private final double price;

    /**
     * @param id    - id do produto (gerado automaticamente)
     * @param name  - nome do produto
     * @param min   - quantidade mínima
     * @param max   - quantidade máxima
     * @param inv   - quantidade no inventário (estoque)
     * @param price - preço do produto
     * @param parts - peças associadas ao produto (lista dinâmica)
     */
    public Product(int id, String name, int min, int max, int inv, double price, ObservableList<Part> parts) {
        this.productId = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.inv = inv;
        this.price = price;
        this.associatedParts = parts;
    }

    /**
     * @return name
     *         Uso: Retorna o nome
     */
    public String getName() {
        return name;
    }

    /**
     * @return price
     *         Uso: Retorna o preço
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return inv
     *         Uso: Retorna o inv
     */
    public int getInv() {
        return inv;
    }

    /**
     * @return min
     *         Uso: Retorna o min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return max
     *         Uso: Retorna o max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param associatedPart Uso: Adiciona a peça fornecida às peças do produto.
     */
    public void addAssociatedPart(Part associatedPart) {
        associatedParts.add(associatedPart);
    }

    /**
     * @param associatedPart Uso: Remove a peça fornecida das peças do produto.
     */
    public void removeAssociatedPart(Part associatedPart) {
        associatedParts.removeIf(part -> part == associatedPart);
    }

    /**
     * @return product id
     *         Uso: Retorna o id do produto.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @return the product's parts
     *         Uso: Retorna as peças do produto
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
}
