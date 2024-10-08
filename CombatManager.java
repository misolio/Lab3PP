import java.util.List;

public class CombatManager {
    private Arena arena;
    private List<Droid> team1;  // Команда 1
    private List<Droid> team2;  // Команда 2

    public CombatManager(Arena arena, List<Droid> team1, List<Droid> team2) {
        this.arena = arena;
        this.team1 = team1;
        this.team2 = team2;
    }

    // Основний метод для виконання бойових дій
    public void executeCombat() {
        for (Droid droid : team1) {
            if (droid.isAlive()) {
                droid.performAction(this);
            }
        }
        for (Droid droid : team2) {
            if (droid.isAlive()) {
                droid.performAction(this);
            }
        }
    }

    // Знайти найближчого ворога для дроїда
    public Droid findClosestEnemy(Droid currentDroid) {
        List<Droid> enemies = currentDroid.getTeam().equals("Red") ? team2 : team1;

        Droid closestEnemy = null;
        double minDistance = Double.MAX_VALUE;

        for (Droid enemy : enemies) {
            if (enemy.isAlive()) {
                double distance = calculateDistance(currentDroid, enemy);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestEnemy = enemy;
                }
            }
        }

        return closestEnemy;
    }

    public void attack(Droid attacker, Droid enemy) {
        int totalDamage = attacker.getDamage() + attacker.getWeaponDamage();
        enemy.takeDamage(totalDamage);
        System.out.println(attacker.getName() + " атакує " + enemy.getName() + " і наносить " + totalDamage + " урону.");
    }

    public void moveTowards(Droid droid, Droid target) {
        int newX = droid.getX();
        int newY = droid.getY();

        if (newX < target.getX()) {
            newX++;
        } else if (newX > target.getX()) {
            newX--;
        }

        if (newY < target.getY()) {
            newY++;
        } else if (newY > target.getY()) {
            newY--;
        }

        droid.move(newX, newY);
    }

    // Розрахунок відстані між двома дроїдами
    public double calculateDistance(Droid droid1, Droid droid2) {
        int deltaX = droid1.getX() - droid2.getX();
        int deltaY = droid1.getY() - droid2.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    // Метод для виконання дій мага
    public void performMageAction(Mage mage) {
        List<Droid> allies = findAllies(mage);
        List<Droid> enemies = findEnemies(mage);

        // Якщо команда має низький рівень здоров'я, маг лікує союзника
        if (isTeamHealthBelow60Percent(allies)) {
            Droid weakestAlly = findWeakestAlly(allies);
            if (weakestAlly != null) {
                mage.heal(weakestAlly);
            }
        } else {
            // Інакше маг підсилює найбільш сильного союзника
            Droid strongestAlly = findStrongestAlly(allies);
            if (strongestAlly != null) {
                mage.empower(strongestAlly);
            }
        }
    }

    private List<Droid> findAllies(Droid currentDroid) {
        return currentDroid.getTeam().equals("Red") ? team1 : team2;
    }

    private List<Droid> findEnemies(Droid currentDroid) {
        return currentDroid.getTeam().equals("Red") ? team2 : team1;
    }

    private boolean isTeamHealthBelow60Percent(List<Droid> team) {
        int totalHealth = 0;
        int currentHealth = 0;

        for (Droid droid : team) {
            totalHealth += droid.getInitialHealth();
            currentHealth += droid.getHealth();
        }

        return currentHealth < 0.6 * totalHealth;
    }

    private Droid findWeakestAlly(List<Droid> allies) {
        Droid weakest = null;
        int minHealth = Integer.MAX_VALUE;

        for (Droid ally : allies) {
            if (ally.isAlive() && ally.getHealth() < minHealth) {
                minHealth = ally.getHealth();
                weakest = ally;
            }
        }

        return weakest;
    }

    // Знайти найбільш сильного союзника
    private Droid findStrongestAlly(List<Droid> allies) {
        Droid strongest = null;
        int maxHealth = 0;

        for (Droid ally : allies) {
            if (ally.isAlive() && ally.getHealth() > maxHealth) {
                maxHealth = ally.getHealth();
                strongest = ally;
            }
        }

        return strongest;
    }
}
