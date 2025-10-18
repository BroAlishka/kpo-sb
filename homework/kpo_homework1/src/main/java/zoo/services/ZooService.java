package zoo.services;

import zoo.entities.animals.Animal;
import zoo.entities.animals.Herbo;
import zoo.entities.things.Thing;

import java.util.List;

public class ZooService {
    private final AnimalService animalService;
    private final ThingService thingService;

    public ZooService(AnimalService animalService, ThingService thingService) {
        this.animalService = animalService;
        this.thingService = thingService;
    }

    public boolean addAnimal(Animal animal) {
        return animalService.addAnimal(animal);
    }

    public void addThing(Thing thing) {
        thingService.addThing(thing);
    }

    public int getTotalFoodAmount() {
        return animalService.getTotalFoodAmount();
    }

    public List<Animal> getAnimalsForContactZoo() {
        return animalService.getAnimalsForContactZoo();
    }

    public void printFullInventoryReport() {
        animalService.printAnimalsReport();
        System.out.println();
        thingService.printThingsReport();
    }

    public void printContactZooReport() {
        var contactAnimals = animalService.getAnimalsForContactZoo();

        System.out.println("=== ЖИВОТНЫЕ ДЛЯ КОНТАКТНОГО ЗООПАРКА ===");
        if (contactAnimals.isEmpty()) {
            System.out.println("Нет животных, подходящих для контактного зоопарка.");
        } else {
            contactAnimals.forEach(animal -> {
                String kindnessInfo = "";
                if (animal instanceof Herbo) {
                    kindnessInfo = String.format(", Уровень доброты: %d",
                            ((Herbo) animal).getKindnessLevel());
                }
                System.out.printf("№%d: %s%s%n",
                        animal.getInventoryNumber(),
                        animal.getName(),
                        kindnessInfo);
            });
        }
    }
}