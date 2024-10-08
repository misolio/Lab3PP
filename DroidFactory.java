import java.util.List;
import java.util.Scanner;

public class DroidFactory {

    public static Droid createDroid() {
        Scanner scanner = new Scanner(System.in);
        String type = getDroidType(scanner);
        String name = getDroidName(scanner);
        int health = getValidIntegerInput(scanner, "Здоров'я", 1, 1000);
        int damage = getValidIntegerInput(scanner, "Урон", 0, 500);
        int armor = getValidIntegerInput(scanner, "Броня", 0, 200);
        int x = getValidIntegerInput(scanner, "Координата X", 1, 15);
        int y = getValidIntegerInput(scanner, "Координата Y", 1, 15);
        String weaponName = getWeaponName(scanner);
        int weaponDamage = getValidIntegerInput(scanner, "Урон зброї", 0, 500);
        String team = getDroidTeam(scanner);
        return buildDroid(type, name, health, damage, armor, x, y, weaponName, weaponDamage, team);
    }

    public static void displayDroids(List<Droid> droids) {
        System.out.println("Список дроїдів:");
        for (int i = 0; i < droids.size(); i++) {
            Droid d = droids.get(i);
            System.out.println("Позиція " + i + ": " + d.getName() + " з команди " + d.getTeam() +
                    ", Здоров'я: " + d.getHealth() + ", Урон: " + d.getDamage() + ", Броня: " + d.getArmor());
        }
    }

    public static void resetAllDroids(List<Droid> droids) {
        for (Droid droid : droids) {
            droid.reset();
        }
        System.out.println("Всі дроїди скинуті до початкового стану.");
    }

    private static String getDroidType(Scanner scanner) {
        String type;
        do {
            System.out.print("Тип дроїда (Warrior, Archer, Mage): ");
            type = scanner.nextLine();
        } while (!type.equals("Warrior") && !type.equals("Archer") && !type.equals("Mage"));
        return type;
    }

    private static String getDroidName(Scanner scanner) {
        System.out.print("Ім'я дроїда: ");
        return scanner.nextLine();
    }

    private static int getValidIntegerInput(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt + " (" + min + " - " + max + "): ");
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Введіть значення у межах від " + min + " до " + max + ".");
                }
            } else {
                System.out.println("Некоректний ввід. Будь ласка, введіть число.");
                scanner.next();
            }
        }
        return value;
    }

    private static String getWeaponName(Scanner scanner) {
        System.out.print("Назва зброї: ");
        scanner.nextLine();
        return scanner.nextLine();
    }

    private static String getDroidTeam(Scanner scanner) {
        String team;
        do {
            System.out.print("Команда дроїда (Red, Blue, Green, Purple): ");
            team = scanner.nextLine();
        } while (!team.equals("Red") && !team.equals("Blue") && !team.equals("Green") && !team.equals("Purple"));
        return team;
    }

    private static Droid buildDroid(String type, String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage, String team) {
        Droid droid = null;
        switch (type) {
            case "Warrior":
                droid = new Warrior(name, health, damage, armor, x, y, weaponName, weaponDamage, team);
                break;
            case "Archer":
                droid = new Archer(name, health, damage, armor, x, y, weaponName, weaponDamage, 8, team);
                break;
            case "Mage":
                droid = new Mage(name, health, 0, armor, x, y, weaponName, 0, 50, 10, team);
                break;
            default:
                System.out.println("Невідомий тип дроїда.");
                break;
        }
        return droid;
    }

}
