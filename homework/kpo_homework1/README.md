Принципы SOLID соблюдены:
1)Single Responsibility Principle
AnimalService - управление животными
ThingService - управление вещами
VeterinaryClinic - проверка здоровья
ZooService - координация работы сервисов
2)Open/Closed Principle
Система расширяема через наследование
3)Liskov Substitution Principle
Все подклассы Animal могут использоваться вместо родительского класса
4)Interface Segregation Principle
IAlive - для живых существ (еда)
IInventory - для инвентаря (номер, название)
5)Dependency Inversion Principle
Зависимости внедряются через конструкторы

Команды
bash
# Сборка и запуск
./gradlew bootRun
# Сборка JAR
./gradlew build
# Запуск тестов
./gradlew test
# Запуск в IDE
MainApplication.java → Run


Тестирование
Покрытие тестами
Логика определения животных для контактного зоопарка
Работа с инвентарными номерами
Расчет потребления пищи
Проверка здоровья животных
Запуск тестов
bash
./gradlew test
