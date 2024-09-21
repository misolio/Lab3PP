public class Archer extends Droid {

    private int range;

    public Archer(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage,
                  int range,String team) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage,team);
        this.range = range;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public void attack(Droid enemy) {
        // Обчислюємо відстань до ворога
        double distance = Math.sqrt(Math.pow(this.x - enemy.x, 2) + Math.pow(this.y - enemy.y, 2));

        // Якщо ворог в зоні досяжності, атака можлива
        if (distance <= this.range) {
            // Залежно від відстані сила атаки зменшується (чим далі, тим слабший удар)
            double attackStrengthFactor = 1.0 - (distance / range);  // Наприклад, сила зменшується з відстанню
            int effectiveDamage = (int) (this.damage * attackStrengthFactor);

            System.out.println(name + " стріляє з лука на відстані " + (int)distance + " метрів з силою удару "
                    + effectiveDamage);
            enemy.takeDamage(effectiveDamage); // Використовуємо зменшений дамаг залежно від дистанції
        } else {
            System.out.println(name + " не може атакувати ворога, оскільки ворог занадто далеко.");
        }
    }
    @Override
    public void move(Droid enemy, Arena arena) {
        // Обчислюємо відстань між лучником і ворогом
        int distanceX = Math.abs(this.x - enemy.getX());
        int distanceY = Math.abs(this.y - enemy.getY());

        int newX = x;
        int newY = y;

        // Якщо ворог є воїном, лучник відступає
        if (enemy instanceof Warrior ) {
            calculateMovement(enemy, false, arena);
        }
        // Якщо ворог є магом, лучник переслідує його
        else {
            // Наближаємося до мага
            calculateMovement(enemy, true, arena);
        }
    }

}
