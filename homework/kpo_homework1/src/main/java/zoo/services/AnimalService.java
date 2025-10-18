package zoo.services;

import org.springframework.stereotype.Component;
import zoo.entities.animals.Animal;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalService {
    private final List<Animal> animals;
    private final VeterinaryClinic veterinaryClinic;
    private int nextInventoryNumber = 1;

    public AnimalService(VeterinaryClinic veterinaryClinic) {
        this.veterinaryClinic = veterinaryClinic;
        this.animals = new ArrayList<>();
    }

    public boolean addAnimal(Animal animal) {
        if (veterinaryClinic.checkHealth(animal)) {
            animal.setInventoryNumber(nextInventoryNumber++);
            animals.add(animal);
            return true;
        }
        return false;
    }

    public int getTotalFoodAmount() {
        return animals.stream().mapToInt(Animal::getFoodAmount).sum();
    }

    public List<Animal> getAnimalsForContactZoo() {
        return animals.stream()
                .filter(Animal::canBeInContactZoo)
                .toList();
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void printAnimalsReport() {
        System.out.println("===Животные===");
        if (animals.isEmpty()) {
            System.out.println("В зоопарке нет животных.");
            return;
        }

        for (Animal animal : animals) {
            System.out.printf("№%d: %s (Еда: %d кг/день, Болен: %s)%n",
                    animal.getInventoryNumber(),
                    animal.getName(),
                    animal.getFoodAmount(),
                    animal.isHealthy() ? "Нет" : "Да");
        }

        System.out.printf("Общее количество еды: %d кг/день%n", getTotalFoodAmount());
    }
}