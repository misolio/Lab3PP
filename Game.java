import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private static List<Droid> droids = new ArrayList<>();
    private static List<String> battleLog = new ArrayList<>();
    private static String fileName = "C:\\Users\\solij\\OneDrive\\Рабочий стол\\ППлаб3.txt";
    private static BattleManager battleManager = new BattleManager(droids, battleLog);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeDroids();

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            handleMenuChoice(choice);
        } while (choice != 7);
    }

    private static void displayMenu() {
        System.out.println("\n===== Меню =====");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Вивести список дроїдів");
        System.out.println("3. Запустити бій 1 на 1");
        System.out.println("4. Запустити командний бій");
        System.out.println("5. Записати попередній бій у файл");
        System.out.println("6. Відтворити попередній бій");
        System.out.println("7. Вийти");
        System.out.println("================\n");
        System.out.print("Виберіть пункт меню: ");
    }

    private static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                droids.add(DroidFactory.createDroid());
                System.out.println("Дроїд доданий у список дроїдів.");
                break;
            case 2:
                DroidFactory.displayDroids(droids);
                break;
            case 3:
                battleManager.startOneOnOneBattle();
                DroidFactory.resetAllDroids(droids);
                break;
            case 4:
                battleManager.startTeamBattle();
                DroidFactory.resetAllDroids(droids);
                break;
            case 5:
                battleManager.savePreviousBattle(fileName);
                break;
            case 6:
                BattleFile.loadBattleLog(fileName);
                break;
            case 7:
                System.out.println("Вихід");
                break;
            default:
                System.out.println("Невірний вибір.");
                break;
        }
    }

    private static void initializeDroids() {
        droids.add(new Warrior("Wert", 129, 50, 10, 7, 6, "Молот", 40, "Red"));
        droids.add(new Archer("Atyr", 80, 30, 5, 1, 1, "Лук", 25, 5, "Red"));
        droids.add(new Mage("Met", 70, 0, 5, 2, 2, "Посох", 0, 50, 10, "Purple"));
        droids.add(new Warrior("Wit", 100, 20, 10, 8, 7, "Молот", 30, "Blue"));
        droids.add(new Archer("Aro", 90, 15, 15, 3, 4, "Лук", 40, 5, "Blue"));
        droids.add(new Mage("Max", 88, 0, 20, 8, 1, "Посох", 0, 50, 10, "Green"));
    }
}
