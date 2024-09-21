public class Archer extends Droid {

    private int range;

    public Archer(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage, int range) {
        super(name, health, damage, armor, x, y, weaponName, weaponDamage);
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

            System.out.println(name + " стріляє з лука на відстані " + (int)distance + " метрів з силою удару " + effectiveDamage);
            enemy.takeDamage(effectiveDamage); // Використовуємо зменшений дамаг залежно від дистанції
        } else {
            System.out.println(name + " не може атакувати ворога, оскільки ворог занадто далеко.");
        }
    }
    @Override
    public void move(Droid enemy) {
        // Обчислюємо відстань між арчерем і ворогом
        int distanceX = Math.abs(this.x - enemy.getX());
        int distanceY = Math.abs(this.y - enemy.getY());

        // Якщо ворог знаходиться на відстані 2 або менше кроків, відступаємо
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
