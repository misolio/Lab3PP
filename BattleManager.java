import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BattleManager {
    private List<Droid> droids;
    private List<String> battleLog;
    private Arena arena;

    public BattleManager(List<Droid> droids, List<String> battleLog) {
        this.droids = droids;
        this.battleLog = battleLog;
        this.arena = new Arena(15, 15, droids);  // Арена приймає список дроїдів
    }

    public void startOneOnOneBattle() {
        Droid droid1 = selectDroid("першого");
        Droid droid2 = selectDroid("другого");

        if (droid1 == null || droid2 == null) {
            System.out.println("Бій не може бути запущений. Дроїди не обрані.");
            return;
        }

        List<Droid> team1 = new ArrayList<>();
        team1.add(droid1);
        List<Droid> team2 = new ArrayList<>();
        team2.add(droid2);

        prepareTeamsForBattle(team1, team2);
        battleLog.add("Бій між " + droid1.getName() + " та " + droid2.getName() + " розпочався!");

        // Виконуємо бойові дії через CombatManager, передаючи вибрані команди
        CombatManager combatManager = new CombatManager(arena, team1, team2);
        executeTeamBattle(team1, team2, combatManager);

        System.out.println("Переможець: " + (team1.isEmpty() ? droid2.getName() : droid1.getName()));

        arena.clearDroids();
    }

    public void startTeamBattle() {
        List<Droid> team1 = selectTeam("Команди Red ");
        List<Droid> team2 = selectTeam("Команди Blue ");

        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Командний бій не може бути запущений. Одна з команд порожня.");
            return;
        }

        prepareTeamsForBattle(team1, team2);
        battleLog.add("Командний бій розпочався!");

        CombatManager combatManager = new CombatManager(arena, team1, team2);
        executeTeamBattle(team1, team2, combatManager);

        System.out.println("Перемогла команда: " + (team1.isEmpty() ? "Blue" : "Red"));
        arena.clearDroids();
    }

    private Droid selectDroid(String droidPosition) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть " + droidPosition + " дроїда:");
        displayDroids();

        int choice = scanner.nextInt();
        if (choice < 0 || choice >= droids.size()) {
            System.out.println("Невірний вибір.");
            return null;
        }
        return droids.get(choice);
    }

    private List<Droid> selectTeam(String teamName) {
        Scanner scanner = new Scanner(System.in);
        List<Droid> team = new ArrayList<>();

        System.out.print("Введіть кількість дроїдів для " + teamName + ": ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            Droid droid = selectDroid("дроїда для " + teamName);
            if (droid != null && !team.contains(droid) && droid.isAlive()) {
                team.add(droid);
            } else {
                System.out.println("Невірний вибір або дроїд вже обраний.");
            }
        }
        return team;
    }

    private void displayDroids() {
        for (int i = 0; i < droids.size(); i++) {
            Droid droid = droids.get(i);
            System.out.println(i + ". " + droid.getName() + " (HP: " + droid.getHealth() + ")");
        }
    }

    private void prepareTeamsForBattle(List<Droid> team1, List<Droid> team2) {
        for (Droid droid : team1) {
            droid.setTeam("Red");
            arena.addDroid(droid);
        }
        for (Droid droid : team2) {
            droid.setTeam("Blue");
            arena.addDroid(droid);
        }
    }

    private void executeTeamBattle(List<Droid> team1, List<Droid> team2, CombatManager combatManager) {
        // CombatManager обробляє бойові дії для двох команд
        while (!team1.isEmpty() && !team2.isEmpty()) {
            combatManager.executeCombat();

            // Після кожного раунду перевіряємо стан команд
            team1.removeIf(droid -> !droid.isAlive());
            team2.removeIf(droid -> !droid.isAlive());

            arena.updateArena();
            ArenaDrawer.displayArenaWithBorder(arena, battleLog);  // Оновлення арени та виведення стану бою
        }

        battleLog.add("Командний бій завершився.");
    }

    public void savePreviousBattle(String fileName) {
        if (battleLog.isEmpty()) {
            System.out.println("Немає бою для запису.");
            return;
        }
        BattleFile.saveBattleLog(fileName, battleLog);
    }
}
