import java.util.List;

public class Game {

    // Кольори для дроїдів і рамки
    public static final String RESET = "\u001B[0m";  // Скидання кольору
    public static final String RED = "\u001B[31m";   // Колір для команди 1
    public static final String GREEN = "\u001B[32m"; // Колір для команди 2
    public static final String BLUE = "\u001B[34m";  // Колір для команди 3
    public static final String YELLOW = "\u001B[33m"; // Жовтий для рамки

    // Функція для відображення арени з рамкою
    public static void displayArenaWithBorder(Arena arena) {
        int width = arena.getWidth();
        int height = arena.getHeight();

        // Верхня межа
        System.out.print(YELLOW + "╔");
        for (int i = 0; i < width; i++) {
            System.out.print("═");
        }
        System.out.println("╗" + RESET);

        // Виведення поля з вертикальними межами
        for (int y = 0; y < height; y++) {
            System.out.print(YELLOW + "║" + RESET);  // Ліва межа
            for (int x = 0; x < width; x++) {
                boolean isDroid = false;
                for (Droid droid : arena.getDroids()) {
                    if (droid.getX() == x && droid.getY() == y) {
                        // Вибір кольору для кожної команди
                        if (droid.getTeam().equals("Red")) {
                            System.out.print(RED + droid.getName().charAt(0) + RESET); // Червоний для команди RedTeam
                        } else if (droid.getTeam().equals("Green")) {
                            System.out.print(GREEN + droid.getName().charAt(0) + RESET); // Зелений для команди GreenTeam
                        } else if (droid.getTeam().equals("Blue")) {
                            System.out.print(BLUE + droid.getName().charAt(0) + RESET); // Синій для команди BlueTeam
                        }
                        isDroid = true;
                        break;
                    }
                }
                if (!isDroid) {
                    System.out.print(" "); // Порожнє місце
                }
            }
            System.out.println(YELLOW + "║" + RESET);  // Права межа
        }

        // Нижня межа
        System.out.print(YELLOW + "╚");
        for (int i = 0; i < width; i++) {
            System.out.print("═");
        }
        System.out.println("╝" + RESET);
    }
    public static void main(String[] args) {
        Arena arena = new Arena(15, 10);

        // Створюємо команду червоних
        Warrior redWarrior = new Warrior("Warrior Red ", 5, 15, 10, 2, 2, "Sword", 10, "Red");
        Archer redArcher = new Archer("Archer Red", 4, 10, 5, 3, 3, "Bow", 7, 5, "Red");

        // Створюємо команду синіх
        Mage blueMage = new Mage("Mage Blue", 3, 0, 5, 7, 7, "Magic Staff", 0, 50, 10, "Blue");
        Archer blueArcher = new Archer("Archer Blue", 4, 10, 5, 8, 8, "Bow", 7, 5, "Blue");

        // Додаємо дроїдів на арену
        arena.addDroid(redWarrior);
        arena.addDroid(redArcher);
        arena.addDroid(blueMage);
        arena.addDroid(blueArcher);

        while (!arena.isBattleOver()) {
            displayArenaWithBorder(arena);
            arena.updateArena();
        }
        displayArenaWithBorder(arena);
    }


}
