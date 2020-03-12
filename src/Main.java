import java.util.Scanner;
import java.util.ArrayList;
import entities.*;

public class Main {
    private static Scanner reader = new Scanner(System.in);
    private static boolean isNewGame = true; // True when first starting game

    private static Player player = new Player("Kip", 10, 1);
    private static ArrayList<NPC> enemies = new ArrayList<>();

    private static final String FIGHT = "F";
    private static final String HELP = "H";
    private static final String QUIT = "Q";
    private static final String RESET_AREA = "R";
    private static final String WAIT = "W";

    public static void main(String[] args) {
        if (isNewGame) {
            initializeEnemies();
            isNewGame = false;
        }

        printActions();
        while (true) {
            System.out.print("Enter field action: ");
            String action = reader.nextLine().toUpperCase();

            switch (action) {
                case QUIT:
                    System.out.println("Exiting.");
                    System.exit(0);
                    break;
                case HELP:
                    printActions();
                    break;
                case FIGHT:
                    if (!player.isAlive()) System.out.println("You are dead.");
                    else if (getEnemy() == null) System.out.println("All enemies slain.");
                    else {
                        new Battle(reader, player, getEnemy()).beginBattle();
                        printActions();
                    }

                    break;
                case WAIT:
                    if (!player.isAlive()) {
                        System.out.println("You are revived.");
                        player.modHP(1);
                    } else player.rest();

                    break;
                case RESET_AREA:
                    enemies.clear();
                    initializeEnemies();
                    System.out.println("Area reset.");
                    break;
                default:
                    System.out.println("Unrecognized command!");
            }
        }
    } // end main()

    /** Returns an enemy entity from the 'enemies' array.
     *  Returns null if there are no enemies left alive. */
    private static NPC getEnemy() {
        for (NPC enemy : enemies) {
            if (enemy.isAlive()) return enemy;
        }
        return null; // If all enemies are dead
    }

    private static void printActions() {
        System.out.println("== Field Actions ==");
        System.out.println("'H' = List Actions");
        System.out.println("'F' = Fight");
        System.out.println("'W' = Wait");
        System.out.println("'R' = Reset Area");
        System.out.println("'Q' = Quit");
        System.out.println();
    }

    /** Adds enemies to the 'enemies' array. */
    private static void initializeEnemies() {
        enemies.add(new NPC("Imp", 3, 1, 5));
        enemies.add(new NPC("Goblin", 5, 3, 10));
        enemies.add(new NPC("Man", 15, 5, 15));
        enemies.add(new NPC("Highwayman", 20, 10, 20));
        enemies.add(new NPC("Scout", 50, 25, 25));
        enemies.add(new NPC("Militia", 100, 40, 30));
        enemies.add(new NPC("Bandit", 200, 80, 35));
        enemies.add(new NPC("Guard", 400, 125, 40));
        enemies.add(new NPC("Mercenary", 700, 250, 50));
        enemies.add(new NPC("Warrior", 1250, 400, 60));
        enemies.add(new NPC("Berserker", 2500, 1000, 70));
        enemies.add(new NPC("Praetorian", 5000, 3000, 80));
        enemies.add(new NPC("Dragon", 15000, 10000, 90));
        enemies.add(new NPC("Hero", 30000, 25000, 100));
        enemies.add(new NPC("Colossus", 75000, 50000, 125));
        enemies.add(new NPC("Saitama", 250000, 150000, 150));
    }
}
