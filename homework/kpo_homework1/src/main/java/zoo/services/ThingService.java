package zoo.services;

import org.springframework.stereotype.Component;
import zoo.entities.things.Thing;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThingService {
    private final List<Thing> things;
    private int nextInventoryNumber = 1001;

    public ThingService() {
        this.things = new ArrayList<>();
    }

    public void addThing(Thing thing) {
        thing.setInventoryNumber(nextInventoryNumber++);
        things.add(thing);
    }

    public List<Thing> getThings() {
        return new ArrayList<>(things);
    }

    public void printThingsReport() {
        System.out.println("===Вещи===");
        if (things.isEmpty()) {
            System.out.println("Вещей нет.");
            return;
        }

        for (Thing thing : things) {
            System.out.printf("№%d: %s%n", thing.getInventoryNumber(), thing.getName());
        }
    }
}