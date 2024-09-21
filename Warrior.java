public class Warrior extends Droid {


    public Warrior(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage, String team) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage, team);
    }

    @Override
    public void move(Droid enemy, Arena arena) {
        calculateMovement(enemy, true, arena);
    }
}
