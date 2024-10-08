import java.io.*;
import java.util.List;

public class BattleFile {

    public static void saveBattleLog(String fileName, List<String> battleLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String log : battleLog) {
                writer.write(log);
                writer.newLine();
            }
            System.out.println("Бій успішно записано у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());
        }
    }

    public static void loadBattleLog(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Відтворення бою з файлу: " + fileName);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}
