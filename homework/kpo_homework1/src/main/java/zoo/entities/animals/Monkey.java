package zoo.entities.animals;

public class Monkey extends Animal {
    private int kindnessLevel;

    public Monkey(String name, int foodAmount, int kindnessLevel) {
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