public class Archer extends Droid {
    private int range;
    private int initialRange;

    public Archer(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage, int range, String team) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage, team);
        this.range = range;
        this.initialRange = range;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public void performAction(CombatManager combatManager) {
        Droid closestEnemy = combatManager.findClosestEnemy(this);
        if (closestEnemy != null) {
            double distanceToEnemy = combatManager.calculateDistance(this, closestEnemy);
            if (distanceToEnemy <= range) {
                combatManager.attack(this, closestEnemy);  // Атакує ворога на відстані
            } else {
                combatManager.moveTowards(this, closestEnemy);  // Рухається ближче до ворога
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.range = initialRange;
    }
}
