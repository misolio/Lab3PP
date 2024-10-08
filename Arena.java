import java.util.List;
import java.util.ArrayList;

public class Arena {
    private final int width; // Ширина арени
    private final int height; // Висота арени
    private List<Droid> droids; // Список дроїдів на арені

    // Конструктор
    public Arena(int width, int height, List<Droid> droids) {
        this.width = width;
        this.height = height;
        this.droids = new ArrayList<>(droids);
    }

    public int getWidth() {
        return width; // Повертає ширину арени
    }

    public int getHeight() {
        return height; // Повертає висоту арени
    }

    public List<Droid> getDroids() {
        return droids; // Повертає список дроїдів
    }

    // Додає дроїда на арену
    public void addDroid(Droid droid) {
        if (!isWithinBounds(droid.getX(), droid.getY())) {
            System.out.println("Дроїд виходить за межі арени!");
            return; // Вихід, якщо координати дроїда за межами арени
        }

        if (isPositionOccupied(droid.getX(), droid.getY())) {
            System.out.println("Позиція зайнята іншим дроїдом! Не можна додати " + droid.getName());
            return;
        }

        droids.add(droid); // Додаємо дроїда
        System.out.println(droid.getName() + " додано на арену!");
    }

    // Оновлює арені, видаляючи мертвих дроїдів
    public void updateArena() {
        droids.removeIf(droid -> !droid.isAlive());
        System.out.println("Арена оновлена. Кількість дроїдів: " + droids.size());
    }

    // Перевіряє, чи зайнята позиція
    public boolean isPositionOccupied(int x, int y) {
        for (Droid droid : droids) {
            if (droid.getX() == x && droid.getY() == y) {
                return true; // Позиція зайнята
            }
        }
        return false; // Позиція вільна
    }

    // Перевіряє, чи координати в межах арени
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height; // Повертає true, якщо координати в межах
    }

    // Очищає арену від дроїдів
    public void clearDroids() {
        droids.clear(); // Видаляє всіх дроїдів
    }

    // Відображає арену та дроїдів
    public void displayArena() {
        System.out.println("Арена:");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Droid droidAtPosition = getDroidAt(x, y);
                if (droidAtPosition != null) {
                    System.out.print(droidAtPosition.getName().charAt(0) + " "); // Відображення першої літери імені дроїда
                } else {
                    System.out.print(". "); // Виведення пустого місця
                }
            }
            System.out.println(); // Перехід на новий рядок
        }
    }

    // Метод для отримання дроїда за координатами
    public Droid getDroidAt(int x, int y) {
        for (Droid droid : droids) {
            if (droid.getX() == x && droid.getY() == y) {
                return droid; // Повертає дроїда на вказаних координатах
            }
        }
        return null; // Якщо дроїда немає на цій позиції
    }

    // Знайти найближчого ворога
    public Droid findClosestEnemy(Droid currentDroid) {
        Droid closestEnemy = null;
        double minDistance = Double.MAX_VALUE;

        for (Droid enemy : droids) {
            if (!enemy.getTeam().equals(currentDroid.getTeam()) && enemy.isAlive()) {
                double distance = calculateDistance(currentDroid, enemy);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestEnemy = enemy; // Зберігаємо найближчого ворога
                }
            }
        }

        return closestEnemy; // Повертає найближчого ворога
    }

    // Розрахунок відстані між двома дроїдами
    public double calculateDistance(Droid droid1, Droid droid2) {
        int deltaX = droid1.getX() - droid2.getX();
        int deltaY = droid1.getY() - droid2.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Повертає відстань
    }
}
