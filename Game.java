import java.util.Scanner;
import java.util.Random;

public class Game {
    // Розміри ігрового поля
    static final int WIDTH = 20;
    static final int HEIGHT = 10;

    // Дроїди
    static Droid droid1;
    static Droid droid2;

    // Символи для меж поля
    static final char WALL_HORIZONTAL = '═';
    static final char WALL_VERTICAL = '║';
    static final char CORNER_TOP_LEFT = '╔';
    static final char CORNER_TOP_RIGHT = '╗';
    static final char CORNER_BOTTOM_LEFT = '╚';
    static final char CORNER_BOTTOM_RIGHT = '╝';
    static final char EMPTY_SPACE = ' ';

    // Опис класу дроїда
    static class Droid {
        int x, y;
        int health;
        int attackPower;
        String symbol; // Символ для позначення дроїда

        public Droid(int x, int y, int health, int attackPower, String symbol) {
            this.x = x;
            this.y = y;
            this.health = health;
            this.attackPower = attackPower;
            this.symbol = symbol;
        }

        public void move(int dx, int dy) {
            this.x += dx;
            this.y += dy;
            // Залишаємо дроїда в межах поля
            if (x < 0) x = 0;
            if (x >= WIDTH) x = WIDTH - 1;
            if (y < 0) y = 0;
            if (y >= HEIGHT) y = HEIGHT - 1;
        }

        public boolean isAlive() {
            return this.health > 0;
        }

        public void attack(Droid other) {
            System.out.println(symbol + " атакує " + other.symbol + " на " + attackPower + " одиниць!");
            other.health -= this.attackPower;
        }
    }

    // Метод для відображення ігрового поля
    public static void drawField() {
        System.out.print(CORNER_TOP_LEFT);
        for (int i = 0; i < WIDTH; i++) {
            System.out.print(WALL_HORIZONTAL);
        }
        System.out.println(CORNER_TOP_RIGHT);

        for (int y = 0; y < HEIGHT; y++) {
            System.out.print(WALL_VERTICAL);
            for (int x = 0; x < WIDTH; x++) {
                if (x == droid1.x && y == droid1.y) {
                    System.out.print(droid1.symbol); // Дроїд 1
                } else if (x == droid2.x && y == droid2.y) {
                    System.out.print(droid2.symbol); // Дроїд 2
                } else {
                    System.out.print(EMPTY_SPACE); // Порожнє місце
                }
            }
            System.out.println(WALL_VERTICAL);
        }

        System.out.print(CORNER_BOTTOM_LEFT);
        for (int i = 0; i < WIDTH; i++) {
            System.out.print(WALL_HORIZONTAL);
        }
        System.out.println(CORNER_BOTTOM_RIGHT);
    }

    // Очищення консолі
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            // Якщо очищення не вдалося, продовжуємо
        }
    }

    // Метод для перевірки, чи дроїди можуть атакувати один одного
    public static boolean areDroidsInRange() {
        return Math.abs(droid1.x - droid2.x) <= 1 && Math.abs(droid1.y - droid2.y) <= 1;
    }

    // Основний метод гри
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Ініціалізуємо дроїдів
        droid1 = new Droid(random.nextInt(WIDTH), random.nextInt(HEIGHT), 20, 5, "D1");
        droid2 = new Droid(random.nextInt(WIDTH), random.nextInt(HEIGHT), 20, 5, "D2");

        String input;
        while (droid1.isAlive() && droid2.isAlive()) {
            clearConsole();
            drawField();
            System.out.println("Здоров'я D1: " + droid1.health);
            System.out.println("Здоров'я D2: " + droid2.health);
            System.out.println("Введіть WASD для руху D1 або Q для виходу: ");
            input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                System.out.println("Гра завершена.");
                break;
            }

            // Обробка введення для переміщення дроїда 1
            switch (input) {
                case "W":
                    droid1.move(0, -1); // Вгору
                    break;
                case "S":
                    droid1.move(0, 1); // Вниз
                    break;
                case "A":
                    droid1.move(-1, 0); // Вліво
                    break;
                case "D":
                    droid1.move(1, 0); // Вправо
                    break;
                default:
                    System.out.println("Невірна команда!");
            }

            // Переміщення дроїда 2 (рандомне)
            droid2.move(random.nextInt(3) - 1, random.nextInt(3) - 1); // Рух випадковим чином

            // Перевірка, чи дроїди можуть атакувати один одного
            if (areDroidsInRange()) {
                droid1.attack(droid2);
                droid2.attack(droid1);
            }
        }

        // Визначення переможця
        if (droid1.isAlive()) {
            System.out.println("Droid 1 переміг!");
        } else if (droid2.isAlive()) {
            System.out.println("Droid 2 переміг!");
        } else {
            System.out.println("Обидва дроїди знищені!");
        }

        scanner.close();
    }
}
