import java.util.List;

public class ArenaDrawer {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";

    public static void displayArenaWithBorder(Arena arena, List<String> battleLog) {
        StringBuilder arenaState = new StringBuilder();

        String topBorder = buildHorizontalBorder(arena, true);
        arenaState.append(topBorder);
        System.out.print(topBorder);

        for (int y = 0; y < arena.getHeight(); y++) {
            displayArenaRow(arena, y, arenaState);
        }

        String bottomBorder = buildHorizontalBorder(arena, false);
        arenaState.append(bottomBorder);
        System.out.print(bottomBorder);

        battleLog.add(arenaState.toString());
    }

    private static void displayArenaRow(Arena arena, int y, StringBuilder arenaState) {
        arenaState.append("║");
        System.out.print(YELLOW + "║" + RESET);
        for (int x = 0; x < arena.getWidth(); x++) {
            if (!displayDroidIfPresent(arena, x, y, arenaState)) {
                arenaState.append(" ");
                System.out.print(" ");
            }
        }
        arenaState.append("║\n");
        System.out.println(YELLOW + "║" + RESET);
    }

    public static boolean displayDroidIfPresent(Arena arena, int x, int y, StringBuilder arenaState) {
        for (Droid droid : arena.getDroids()) {
            if (droid.getX() == x && droid.getY() == y) {
                arenaState.append(droid.getName().charAt(0));
                displayDroidOnArena(droid);
                return true;
            }
        }
        return false;
    }

    public static String buildHorizontalBorder(Arena arena, boolean isTop) {
        StringBuilder border = new StringBuilder();
        border.append(isTop ? "╔" : "╚");
        for (int i = 0; i < arena.getWidth(); i++) {
            border.append("═");
        }
        border.append(isTop ? "╗\n" : "╝\n");
        return YELLOW + border + RESET;
    }

    public static void displayDroidOnArena(Droid droid) {
        if (droid.getTeam().equals("Red")) {
            System.out.print(RED + droid.getName().charAt(0) + RESET);
        } else if (droid.getTeam().equals("Green")) {
            System.out.print(GREEN + droid.getName().charAt(0) + RESET);
        } else if (droid.getTeam().equals("Blue")) {
            System.out.print(BLUE + droid.getName().charAt(0) + RESET);
        } else if (droid.getTeam().equals("Purple")) {
            System.out.print(PURPLE + droid.getName().charAt(0) + RESET);
        } else {
            System.out.print(droid.getName().charAt(0));
        }
    }
}
