package zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zoo.entities.animals.*;
import zoo.entities.things.*;
import zoo.services.ZooService;

import java.util.Scanner;

@Component
public class ZooApplication implements CommandLineRunner {

    private final ZooService zooService;
    private Scanner scanner;

    @Autowired
    public ZooApplication(ZooService zooService) {
        this.zooService = zooService;
    }

    @Override
    public void run(String... args) {
        scanner = new Scanner(System.in);
        initializeSampleData();

        System.out.println("=== СИСТЕМА УЧЕТА МОСКОВСКОГО ЗООПАРКА (Spring Boot + Gradle) ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> addThing();
                case 3 -> showTotalFood();
                case 4 -> showContactZooAnimals();
                case 5 -> showFullInventoryReport();
                case 0 -> running = false;
                default -> System.out.println("Неверный выбор!");
            }
        }

        System.out.println("Работа системы завершена.");
        scanner.close();
    }

    private void initializeSampleData() {
        zooService.addThing(new Table("Стол ветеринара"));
        zooService.addThing(new Computer("Компьютер администратора"));
    }

    private void printMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Добавить животное");
        System.out.println("2. Добавить вещь");
        System.out.println("3. Показать общее количество еды");
        System.out.println("4. Показать животных для контактного зоопарка");
        System.out.println("5. Показать полный инвентарный отчет");
        System.out.println("0. Выход");
    }

    private void addAnimal() {
        System.out.println("\n=== ДОБАВЛЕНИЕ ЖИВОТНОГО ===");
        System.out.println("1. Кролик");
        System.out.println("2. Обезьяна");
        System.out.println("3. Тигр");
        System.out.println("4. Волк");

        int type = getIntInput("Выберите тип животного: ");
        String name = getStringInput("Введите имя: ");
        int food = getIntInput("Введите количество еды (кг/день): ");

        Animal animal = null;

        switch (type) {
            case 1 -> {
                int kindness = getIntInput("Введите уровень доброты (1-10): ");
                animal = new Rabbit(name, food, kindness);
            }
            case 2 -> {
                int kindness = getIntInput("Введите уровень доброты (1-10): ");
                animal = new Monkey(name, food, kindness);
            }
            case 3 -> animal = new Tiger(name, food);
            case 4 -> animal = new Wolf(name, food);
            default -> {
                System.out.println("Неверный тип животного!");
                return;
            }
        }

        if (zooService.addAnimal(animal)) {
            System.out.println("Животное успешно добавлено в зоопарк!");
        } else {
            System.out.println("Животное не прошло проверку здоровья и не может быть добавлено.");
        }
    }

    private void addThing() {
        System.out.println("\n=== ДОБАВЛЕНИЕ ВЕЩИ ===");
        System.out.println("1. Стол");
        System.out.println("2. Компьютер");

        int type = getIntInput("Выберите тип вещи: ");
        String name = getStringInput("Введите название: ");

        Thing thing = null;

        switch (type) {
            case 1 -> thing = new Table(name);
            case 2 -> thing = new Computer(name);
            default -> {
                System.out.println("Неверный тип вещи!");
                return;
            }
        }

        zooService.addThing(thing);
        System.out.println("Вещь успешно добавлена!");
    }

    private void showTotalFood() {
        int totalFood = zooService.getTotalFoodAmount();
        System.out.printf("\nОбщее количество еды для всех животных: %d кг/день%n", totalFood);
    }

    private void showContactZooAnimals() {
        zooService.printContactZooReport();
    }

    private void showFullInventoryReport() {
        zooService.printFullInventoryReport();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите число!");
            scanner.next();
            System.out.print(prompt);
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}