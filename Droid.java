public abstract class Droid {
    protected String name;
    protected int health;
    protected int initialHealth;
    protected int damage;
    protected int armor;
    protected int initialArmor;
    protected int x;
    protected int y;
    protected int initialX;
    protected int initialY;
    protected String weaponName;
    protected int weaponDamage;
    protected String team;

    public Droid(String name, int health, int damage, int armor, int x, int y, String weaponName, int weaponDamage, String team) {
        this.name = name;
        this.health = health;
        this.initialHealth = health;
        this.damage = damage;
        this.armor = armor;
        this.initialArmor = armor;
        this.x = x;
        this.y = y;
        this.initialX = x;
        this.initialY = y;
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.team = team;
    }

    public void reset() {
        this.health = initialHealth;
        this.x = initialX;
        this.y = initialY;
        this.armor = initialArmor;
    }
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getInitialHealth() {
        return initialHealth;
    }
    public int getDamage () {
        return damage ;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getArmor() {
        return armor;
    }
    public String getTeam() {
        return team;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setTeam(String team) {
        this.team = team;
    }

    public void takeDamage(int damage) {
        int reducedDamage = Math.max(damage - armor, 0);
        health -= reducedDamage;
        if (health <= 0) {
            health = 0;
        }
        System.out.println(name + " отримав " + reducedDamage + " урону. Здоров'я: " + health);
    }

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        System.out.println(name + " перемістився на нову позицію (" + this.x + ", " + this.y + ")");
    }

    public abstract void performAction(CombatManager combatManager);

    public boolean isAlive() {
        return health > 0;
    }
}
