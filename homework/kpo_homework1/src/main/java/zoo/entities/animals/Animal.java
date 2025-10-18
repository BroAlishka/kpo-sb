package zoo.entities.animals;
import zoo.interfaces.IAlive;
import zoo.interfaces.IInventory;

public abstract class Animal implements IAlive, IInventory {
    protected int foodAmount;
    protected int inventoryNumber;
    protected String name;
    protected boolean isHealthy;

    public Animal(String name, int foodAmount) {
        this.name = name;
        this.foodAmount = foodAmount;
        this.isHealthy = false;
    }

    @Override
    public int getFoodAmount() {
        return foodAmount;
    }

    @Override
    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
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

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }
    public abstract boolean canBeInContactZoo();
}