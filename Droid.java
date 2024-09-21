public abstract class Droid {

    protected String name;
    protected int health;
    protected int damage;
    protected int armor;
    protected int x;
    protected int y;
    protected String weaponName;
    protected int weaponDamage;

    public Droid(String name, int health, int damage, int armor, int x, int y, String weaponName,int weaponDamage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.armor = armor;
        this.x = 0;
        this.y = 0;
        this.weaponName = weaponName;
        this.weaponDamage = 0;
    }

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public int getArmor() {
        return armor;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getWeaponName(){
        return weaponName;
    }
    public int getWeaponDamage(){
        return weaponDamage;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeaponName(String name) {
        this.weaponName = weaponName;
    }
    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        System.out.println(name + " перемістився на нову позицію (" + this.x + "," + this.y + ")");

    }
    public void attack(Droid enemy) {
        int totalDamage = damage + weaponDamage;
        System.out.println(name + " атакує "+enemy.getName()+" і наносить"+damage+" одиниць урону");
        enemy.takeDamage(totalDamage);
    }
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(damage-armor, 0);
        health -= reducedDamage;
    }
    public boolean isAlive() {
        return health > 0;
    }

    public abstract void move(Droid enemy);
}
