public class Warrior extends Droid {

    public Warrior(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage, String team) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage, team);
    }

    @Override
    public void performAction(CombatManager combatManager) {
        Droid closestEnemy = combatManager.findClosestEnemy(this);
        if (closestEnemy != null) {
            double distanceToEnemy = combatManager.calculateDistance(this, closestEnemy);
            if (distanceToEnemy <= 1.0) {
                combatManager.attack(this, closestEnemy);  // Атакує ворога
            } else {
                combatManager.moveTowards(this, closestEnemy);  // Рухається до ворога
            }
        }
    }
}
