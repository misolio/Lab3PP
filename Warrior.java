
public class Warrior extends Droid{

    public Warrior(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage);
    }
    @Override
    public void move(Droid enemy) {
        // Якщо ворог знаходиться далі, воїн буде рухатись в напрямку ворога
        if (this.x < enemy.getX()) {
            this.x++;
        } else if (this.x > enemy.getX()) {
            this.x--;
        }

        if (this.y < enemy.getY()) {
            this.y++;
        } else if (this.y > enemy.getY()) {
            this.y--;
        }

        System.out.println(name + " переслідує ворога і переміщується на (" + this.x + "," + this.y + ")");
    }
}
