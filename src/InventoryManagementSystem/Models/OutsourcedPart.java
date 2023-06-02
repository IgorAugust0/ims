package InventoryManagementSystem.Models;

public class OutsourcedPart extends Part {
    // a empresa que criou a peça.
    private String companyName;

    /**
     * Uso: Cria uma peça terceirizada
     * 
     * @param partId      - id da peça (gerado automaticamente)
     * @param name        - nome da peça
     * @param price       - preço da peça
     * @param inv         - quantidade no inventário (estoque)
     * @param min         - quantidade mínima
     * @param max         - quantidade máxima
     * @param companyName - nome da empresa que criou a peça
     */
    public OutsourcedPart(int partId, String name, double price, int inv, int min, int max, String companyName) {
        this.setPartId(partId);
        this.setName(name);
        this.setPrice(price);
        this.setInv(inv);
        this.setMin(min);
        this.setMax(max);
        this.setCompanyName(companyName);
    }

    /**
     * @return companyName
     *         Uso: Retorna o nome da empresa.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName Uso: Define o nome da empresa.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
