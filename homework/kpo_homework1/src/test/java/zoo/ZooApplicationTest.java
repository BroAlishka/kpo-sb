package zoo;

import org.junit.jupiter.api.Test;
import zoo.entities.animals.*;
import zoo.entities.things.Table;
import zoo.entities.things.Computer;
import zoo.interfaces.IAlive;
import zoo.interfaces.IInventory;

import static org.junit.jupiter.api.Assertions.*;

class AnimalLogicTest {

    @Test
    void testRabbitContactZoo() {
        Rabbit rabbit = new Rabbit("БаксБаннни", 2, 7);
        rabbit.setHealthy(true);
        assertTrue(rabbit.canBeInContactZoo());
    }

    @Test
    void testMonkeyContactZoo() {
        Monkey monkey = new Monkey("Николай", 3, 8);
        monkey.setHealthy(true);
        assertTrue(monkey.canBeInContactZoo());
    }

    @Test
    void testTigerNeverInContactZoo() {
        Tiger tiger = new Tiger("Али", 10);
        tiger.setHealthy(true);
        assertFalse(tiger.canBeInContactZoo());
    }

    @Test
    void testWolfNeverInContactZoo() {
        Wolf wolf = new Wolf("Егор", 8);
        wolf.setHealthy(true);
        assertFalse(wolf.canBeInContactZoo());
    }

    @Test
    void testAnimalFoodAmount() {
        Rabbit rabbit = new Rabbit("Тест", 5, 6);
        assertEquals(5, rabbit.getFoodAmount());

        rabbit.setFoodAmount(10);
        assertEquals(10, rabbit.getFoodAmount());
    }

    @Test
    void testThingInventory() {
        Table table = new Table("Стол");
        table.setInventoryNumber(1001);
        assertEquals(1001, table.getInventoryNumber());
        assertEquals("Стол", table.getName());

        Computer computer = new Computer("Компьютер");
        computer.setInventoryNumber(1002);
        assertEquals(1002, computer.getInventoryNumber());
    }

    @Test
    void testInterfacesImplementation() {
        Rabbit rabbit = new Rabbit("Тест", 1, 5);
        assertTrue(rabbit instanceof IAlive);
        assertTrue(rabbit instanceof IInventory);

        Table table = new Table("Тест");
        assertTrue(table instanceof IInventory);
    }
}