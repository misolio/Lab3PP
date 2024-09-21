public class Mage extends Droid {
    private int mana;
    private int magicPower;

    public Mage(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage,
                int mana, int magicPower) {
        super(name, health,0, armor, x, y, weaponName, 0);
        this.mana = mana;
        this.magicPower = magicPower;
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

    public void heal(Droid ally) {
        if (mana >= 20) {
            int healAmount = 30 + magicPower;  // Базове зцілення + бонус від зброї
            System.out.println(name + " зцілює " + ally.getName() + " на " + healAmount
                    + " одиниць здоров'я (підсилено " + weaponName + ").");
            ally.setHealth(ally.getHealth() + healAmount);
            mana -= 20;  // Витрата мани
            System.out.println(name + " залишок мани: " + mana);
        } else {
            System.out.println(name + " не вистачає мани для зцілення.");
        }
    }

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

    @Override
    public void move(Droid enemy) {
        int distanceX = Math.abs(this.x - enemy.getX());
        int distanceY = Math.abs(this.y - enemy.getY());

        if (distanceX <= 2 && distanceY <= 2) {
            if (this.x < enemy.getX()) {
                this.x--; // Відступаємо вліво
            } else if (this.x > enemy.getX()) {
                this.x++; // Відступаємо вправо
            }

            if (this.y < enemy.getY()) {
                this.y--; // Відступаємо вгору
            } else if (this.y > enemy.getY()) {
                this.y++; // Відступаємо вниз
            }

            System.out.println(name + " відступає від ворога на позицію (" + this.x + "," + this.y + ")");
        } else {
            System.out.println(name + " залишається на місці.");
        }
    }

}
