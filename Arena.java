import java.util.List;
import java.util.ArrayList;

public class Arena {
    private final int width;
    private final int height;
    private List<Droid> droids;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.droids = new ArrayList<>();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public List<Droid> getDroids() {
        return droids;
    }

    public void setDroids(List<Droid> droids) {
        this.droids = droids;
    }

    public void addDroid(Droid droid) {
        // Перевіряємо, чи не зайнята позиція іншим дроїдом
        for (Droid d : droids) {
            if (d.getX() == droid.getX() && d.getY() == droid.getY()) {
                System.out.println("Позиція зайнята іншим дроїдом! Не можна додати " + droid.getName());
                return;
            }
        }
        droids.add(droid);
    }
    public void updateArena() {
        System.out.println("\n--- Оновлення арени ---");

        // Дроїди роблять свій хід
        for (Droid droid : droids) {
            Droid closestEnemy = findClosestEnemy(droid);
            if (closestEnemy != null) {
                System.out.println(droid.getName() + " (" + droid.getTeam() + ") намагається переміститись до " + closestEnemy.getName() + " (" + closestEnemy.getTeam() + ")");
                droid.move(closestEnemy, this); // Дроїд рухається відповідно до своєї логіки
            }
        }

        // Дроїди атакують або використовують спеціальні дії (маги чарують, інші атакують)
        for (Droid droid : droids) {
            if (droid instanceof Mage) {
                Mage mage = (Mage) droid;
                mage.enchantWithMagic(droids); // Маг лікує або підсилює команду
                System.out.println(mage.getName() + " використовує магію на своїх союзників.");
            } else {
                Droid closestEnemy = findClosestEnemy(droid);
                if (closestEnemy != null && isInAttackRange(droid, closestEnemy) && !droid.getTeam().equals(closestEnemy.getTeam())) {
                    System.out.println(droid.getName() + " атакує " + closestEnemy.getName() + "!");
                    droid.attack(closestEnemy); // Дроїд атакує
                    if (!closestEnemy.isAlive()) {
                        System.out.println(closestEnemy.getName() + " загинув!");
                    }
                }
            }
        }

        // Видаляємо мертвих дроїдів
        droids.removeIf(droid -> !droid.isAlive());

        System.out.println("--- Оновлення завершено ---\n");
    }

    // Знаходимо найближчого ворога з іншої команди
    private Droid findClosestEnemy(Droid currentDroid) {
        Droid closestEnemy = null;
        double minDistance = Double.MAX_VALUE;
        for (Droid enemy : droids) {
            if (!enemy.getTeam().equals(currentDroid.getTeam()) && enemy.isAlive()) {
                double distance = Math.sqrt(Math.pow(currentDroid.getX() - enemy.getX(), 2) +
                        Math.pow(currentDroid.getY() - enemy.getY(), 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestEnemy = enemy;
                }
            }
        }
        return closestEnemy;
    }
    // Перевіряємо, чи ворог в зоні досяжності для атаки
    private boolean isInAttackRange(Droid attacker, Droid enemy) {
        double distance = Math.sqrt(Math.pow(attacker.getX() - enemy.getX(), 2) +
                Math.pow(attacker.getY() - enemy.getY(), 2));
        if (attacker instanceof Archer) {
            return distance <= ((Archer) attacker).getRange(); // Для лучника враховуємо дальність атаки
        }
        return distance <= 1.0; // Для воїна і мага атака на відстані 1
    }


    public boolean isBattleOver() {
        boolean redTeamAlive = false;
        boolean blueTeamAlive = false;

        for (Droid droid : droids) {
            if (droid.isAlive()) {
                if (droid.getTeam().equals("Red")) {
                    redTeamAlive = true;
                } else if (droid.getTeam().equals("Blue")) {
                    blueTeamAlive = true;
                }

                // Якщо живі дроїди є в обох командах, бій не завершено
                if (redTeamAlive && blueTeamAlive) {
                    return false;
                }
            }
        }

        return true;
    }

    // Оголошуємо переможця
    public void declareWinner() {
        boolean redTeamAlive = false;
        boolean blueTeamAlive = false;

        for (Droid droid : droids) {
            if (droid.isAlive()) {
                if (droid.getTeam().equals("Red")) {
                    redTeamAlive = true;
                } else if (droid.getTeam().equals("Blue")) {
                    blueTeamAlive = true;
                }
            }
        }

        if (redTeamAlive && !blueTeamAlive) {
            System.out.println("Переможцем стає команда Red!");
        } else if (blueTeamAlive && !redTeamAlive) {
            System.out.println("Переможцем стає команда Blue!");
        } else {
            System.out.println("Усі дроїди загинули, переможця немає.");
        }
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

}
