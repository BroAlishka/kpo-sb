package zoo.services;

import org.springframework.stereotype.Component;
import zoo.entities.animals.Animal;

@Component
public class VeterinaryClinic {
    public boolean checkHealth(Animal animal) {
        boolean isHealthy = Math.random() > 0.3;
        animal.setHealthy(isHealthy);
        return isHealthy;
    }
}