import java.util.List;

public class Mage extends Droid {
    private int mana;
    private int magicPower;
    private int initialHealth;  // Початкове здоров'я

    public Mage(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage,
                int mana, int magicPower, String team) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage, team);
        this.mana = mana;
        this.magicPower = magicPower;
        this.initialHealth = health;
    }

    public int getMana() {
        return mana;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    // Метод для перевірки рівня здоров'я команди
    private boolean isTeamHealthBelow60Percent(List<Droid> team) {
        int totalHealth = 0;
        int totalInitialHealth = 0;

        for (Droid ally : team) {
            // Враховуємо лише союзників з тієї ж команди
            if (ally.getTeam().equals(this.getTeam())) {
                totalHealth += ally.getHealth(); // Поточне здоров'я союзника
                totalInitialHealth += ally.getInitialHealth(); // Початкове здоров'я союзника
            }
        }

        // Повертаємо true, якщо здоров'я всієї команди менше 60% від початкового
        return totalHealth < (0.6 * totalInitialHealth);
    }

    // Метод для лікування союзника
    public void heal(Droid ally) {
        if (mana >= 20 && ally.getHealth() != 0) {
            int healAmount = 30 + magicPower;  // Базове зцілення + бонус від магічної сили
            System.out.println(name + " зцілює " + ally.getName() + " на " + healAmount
                    + " одиниць здоров'я (підсилено " + weaponName + ").");
            ally.setHealth(ally.getHealth() + healAmount);
            mana -= 20;  // Витрата мани
            System.out.println(name + " залишок мани: " + mana);
        } else {
            System.out.println(name + " не вистачає мани для зцілення.");
        }
    }

    // Метод для підсилення союзника
    public void empower(Droid ally) {
        if (mana >= 10) {
            System.out.println(name + " підсилює " + ally.getName() + " з "
                    + weaponName + ", збільшуючи урон на " + magicPower + " одиниць.");
            ally.setDamage(ally.getDamage() + magicPower);
            mana -= 10;
            System.out.println(name + " залишок мани: " + mana);
        } else {
            System.out.println(name + " не вистачає мани для підсилення.");
        }
    }

    // Магічне зачарування: зцілення або підсилення союзників
    public void enchantWithMagic(List<Droid> team) {
        if (isTeamHealthBelow60Percent(team)) {
            // Лікуємо найслабшого союзника з тієї ж команди
            Droid weakestAlly = findWeakestAlly(team);
            if (weakestAlly != null) {
                heal(weakestAlly);
            }
        } else {
            // Підсилюємо найсильнішого союзника з тієї ж команди
            Droid strongestAlly = findStrongestAlly(team);
            if (strongestAlly != null) {
                empower(strongestAlly);
            }
        }
    }

    // Знаходимо найслабшого союзника з тієї ж команди (з найменшим здоров'ям)
    private Droid findWeakestAlly(List<Droid> team) {
        Droid weakest = null;
        int minHealth = Integer.MAX_VALUE;

        for (Droid ally : team) {
            if (ally.getTeam().equals(this.getTeam()) && ally.getHealth() < minHealth) {
                minHealth = ally.getHealth();
                weakest = ally;
            }
        }

        return weakest;
    }

    // Знаходимо найсильнішого союзника з тієї ж команди (з найбільшим здоров'ям)
    private Droid findStrongestAlly(List<Droid> team) {
        Droid strongest = null;
        int maxHealth = Integer.MIN_VALUE;

        for (Droid ally : team) {
            if (ally.getTeam().equals(this.getTeam()) && ally.getHealth() > maxHealth) {
                maxHealth = ally.getHealth();
                strongest = ally;
            }
        }

        return strongest;
    }

    @Override
    public void move(Droid enemy, Arena arena) {
        calculateMovement(enemy, false, arena);
    }
}
