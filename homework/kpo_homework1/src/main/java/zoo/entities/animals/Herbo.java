package zoo.entities.animals;

public abstract class Herbo extends Animal {
    protected int kindnessLevel;

    public Herbo(String name, int foodAmount, int kindnessLevel) {
        super(name, foodAmount);
        this.kindnessLevel = kindnessLevel;
    }

    public int getKindnessLevel() {
        return kindnessLevel;
    }

    @Override
    public boolean canBeInContactZoo() {
        return isHealthy && kindnessLevel > 5;
    }
}
