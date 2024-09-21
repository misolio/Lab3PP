public abstract class Droid {
    protected String name;
    protected int health;
    protected int damage;
    protected int armor;
    protected int x;
    protected int y;
    protected String weaponName;
    protected int weaponDamage;
    protected int initialHealth;
    protected String team;

    // Конструктор
    public Droid(String name, int health, int damage, int armor, int x, int y,
                 String weaponName, int weaponDamage, String team) {
        this.name = name;
        this.health = health;
        this.initialHealth = health;  // Зберігаємо початкове здоров'я під час створення
        this.damage = damage;
        this.armor = armor;
        this.x = x;
        this.y = y;
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.team = team;
    }

    // Геттер для початкового здоров'я
    public int getInitialHealth() {
        return initialHealth;
    }

    // Інші геттери та сеттери
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }
    public String getTeam() {
        return team;
    }
    // Метод атаки
    public void attack(Droid enemy) {
        int totalDamage = damage + weaponDamage;
        System.out.println(name + " атакує " + enemy.getName() + " і наносить " + totalDamage + " одиниць урону.");
        enemy.takeDamage(totalDamage);
    }

    // Метод для отримання урону
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(damage - armor, 0);
        health -= reducedDamage;
        System.out.println(name + " отримав " + reducedDamage + " урону. Здоров'я: " + health);
    }
    public abstract void move(Droid enemy, Arena arena);
    // Метод перевірки чи дроїд живий
    public boolean isAlive() {
        return health > 0;
    }
    public void calculateMovement(Droid enemy, boolean towardsEnemy, Arena arena) {
        int newX = this.x;
        int newY = this.y;

        // Якщо рухаємося до ворога
        int directionX = towardsEnemy ? 1 : -1;
        int directionY = towardsEnemy ? 1 : -1;

        // Логіка руху по осі X
        if (this.x < enemy.getX()) {
            newX += directionX; // Рух вправо до ворога
        } else if (this.x > enemy.getX()) {
            newX -= directionX; // Рух вліво до ворога
        }

        // Логіка руху по осі Y
        if (this.y < enemy.getY()) {
            newY += directionY; // Рух вниз до ворога
        } else if (this.y > enemy.getY()) {
            newY -= directionY; // Рух вгору до ворога
        }

        // Перевіряємо можливість переміщення
        if (canMoveTo(newX, newY, arena)) {
            this.x = newX;
            this.y = newY;
            System.out.println(name + " перемістився на нову позицію (" + this.x + ", " + this.y + ")");
        } else {
            System.out.println(name + " не може переміститися на позицію (" + newX + ", " + newY + "), вона зайнята або за межами арени.");
        }
    }
    public boolean canMoveTo(int newX, int newY, Arena arena) {
        // Перевіряємо, чи нова позиція в межах арени
        if (!arena.isWithinBounds(newX, newY)) {
            return false;
        }

        // Перевіряємо, чи позиція не зайнята іншим дроїдом
        for (Droid droid : arena.getDroids()) {
            if (droid.getX() == newX && droid.getY() == newY) {
                return false; // Позиція зайнята іншим дроїдом
            }
        }
        return true; // Позиція доступна для переміщення
    }
}
