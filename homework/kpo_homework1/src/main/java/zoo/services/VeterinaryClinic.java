package zoo.services;

import zoo.entities.animals.Animal;

public class VeterinaryClinic {
    public boolean checkHealth(Animal animal) {
        boolean isHealthy = Math.random() > 0.3;
        animal.setHealthy(isHealthy);
        return isHealthy;
    }
}