package InventoryManagementSystem.Models;

public abstract class Part {
    // O nome da peça
    private String name;
    // O id da peça, nível de inv, valores mínimos e máximos.
    private int partId, inv, min, max;
    // O preço da peça.
    private double price;

    /**
     * @return name
     *         Uso: Retorna o nome da peça
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Uso: Define o nome da peça
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return price
     *         Uso: Retorna o preço
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Uso: Define o preço
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return inv
     *         Uso: Retorna o inv
     */
    public int getInv() {
        return inv;
    }

    /**
     * @param inv Uso: Define o inv
     */
    public void setInv(int inv) {
        this.inv = inv;
    }

    /**
     * @return min
     *         Uso: Retorna o min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min Uso: Define o min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return max
     *         Uso: Retorna o max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max Uso: Define o max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return partId
     *         Uso: Retorna o partId
     */
    public int getPartId() {
        return partId;
    }

    /**
     * @param partId Uso: Define o partId
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }
}
