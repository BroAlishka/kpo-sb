package zoo.entities.animals;

public abstract class Predator extends Animal {
    public Predator(String name, int foodAmount) {
        super(name, foodAmount);
    }

    @Override
    public boolean canBeInContactZoo() {
        return false;
    }
}