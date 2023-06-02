package InventoryManagementSystem.Models;

public class InhousePart extends Part {

    // O id da máquina que criou a peça
    private static int machineId;

    /**
     * Uso: Cria uma peça que é feita na própria empresa.
     * 
     * @param partId    - id da peça (gerado automaticamente)
     * @param name      - nome da peça
     * @param price     - preço da peça
     * @param inv       - quantidade no inventário (estoque)
     * @param min       - quantidade mínima
     * @param max       - quantidade máxima
     * @param machineId - id da máquina que criou a peça
     */
    public InhousePart(int partId, String name, double price, int inv, int min, int max, int machineId) {
        this.setPartId(partId);
        this.setName(name);
        this.setPrice(price);
        this.setInv(inv);
        this.setMin(min);
        this.setMax(max);
        this.setMachineId(machineId);
    }

    /**
     * Uso: Obtém o id da máquina.
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param machineId Uso: Define o id da máquina.
     */
    public void setMachineId(int machineId) {
        InhousePart.machineId = machineId;
    }
}
