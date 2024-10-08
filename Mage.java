public class Mage extends Droid {
    private int mana;
    private int magicPower;
    private int initialMana;
    private int initialMagicPower;

    public Mage(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage, int mana, int magicPower, String team) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage, team);
        this.mana = mana;
        this.magicPower = magicPower;
        this.initialMana = mana;
        this.initialMagicPower = magicPower;
    }

    @Override
    public void performAction(CombatManager combatManager) {
        // Перевірка, чи маг бере участь у бою (чи є вороги поруч)
        Droid closestEnemy = combatManager.findClosestEnemy(this);

        if (mana >= 20 && closestEnemy != null) {
            // Якщо маг може виконувати дії і є вороги поруч, він бере участь в бою
            combatManager.performMageAction(this);
        } else if (mana < 20) {
            recoverMana();  // Відновлює ману, якщо недостатньо для дії
        } else {
            System.out.println(getName() + " не бере участь у бою та не лікує тіммейтів.");
        }
    }

    public void heal(Droid ally) {
        int healAmount = 30 + magicPower;
        ally.setHealth(ally.getHealth() + healAmount);
        mana -= 20;
        System.out.println(getName() + " зцілює " + ally.getName() + " на " + healAmount + " одиниць здоров'я.");
    }

    public void empower(Droid ally) {
        ally.setDamage(ally.getDamage() + magicPower);
        mana -= 20;
        System.out.println(getName() + " підсилює " + ally.getName() + " збільшуючи урон на " + magicPower + " одиниць.");
    }

    public void recoverMana() {
        mana += 15;
        System.out.println(getName() + " відновлює ману. Поточна мана: " + mana);
    }

    @Override
    public void reset() {
        super.reset();
        this.mana = initialMana;
        this.magicPower = initialMagicPower;
    }
}
