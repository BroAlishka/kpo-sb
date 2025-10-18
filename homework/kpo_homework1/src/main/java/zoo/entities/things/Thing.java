package zoo.entities.things;
import zoo.interfaces.IInventory;

public abstract class Thing implements IInventory {
    protected int inventoryNumber;
    protected String name;

    public Thing(String name) {
        this.name = name;
    }

    @Override
    public int getInventoryNumber() {
        return inventoryNumber;
    }

    @Override
    public void setInventoryNumber(int number) {
        this.inventoryNumber = number;
    }

    @Override
    public String getName() {
        return name;
    }
}