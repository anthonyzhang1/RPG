import entities.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner reader = new Scanner(System.in);
    private static boolean isNewGame = true; // True when first starting game

    private static Player player;
    private static ArrayList<NPC> enemies = new ArrayList<>();

    private static final String CHECK_CHARACTER = "C";
    private static final String FIGHT = "F";
    private static final String HELP = "H";
    private static final String QUIT = "Q";
    private static final String RESET_AREA = "R";
    private static final String SHOP = "S";
    private static final String WAIT = "W";

    public static void main(String[] args) {
        if (isNewGame) {
            initializeEnemies();
            // TODO: temporary hard-coded name
            // System.out.print("What shall be your name? ");
            // String name = reader.nextLine();
            // TODO: end temporary hard-coded name
            player = new Player("Kipper", 5, 1);
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
                case CHECK_CHARACTER:
                    player.displayCharacterStats();
                    break;
                case SHOP:
                    new Shop(reader, player).openShop();
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
    }

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
        System.out.println("'C' = Character Stats");
        System.out.println("'S' = Shop");
        System.out.println("'R' = Reset Area");
        System.out.println("'Q' = Quit Game\n");
    }

    /** Adds enemies to the 'enemies' array. */
    private static void initializeEnemies() {
        enemies.add(new NPC("Imp", 3, 1, 5, 5));
        enemies.add(new NPC("Goblin", 5, 3, 10, 15));
        enemies.add(new NPC("Man", 15, 5, 20, 30));
        enemies.add(new NPC("Highwayman", 20, 10, 35, 60));
        enemies.add(new NPC("Scout", 50, 25, 50, 100));
        enemies.add(new NPC("Militia", 100, 40, 75, 150));
        enemies.add(new NPC("Bandit", 200, 80, 100, 250));
        enemies.add(new NPC("Guard", 400, 125, 125, 500));
        enemies.add(new NPC("Mercenary", 700, 250, 150, 1000));
        enemies.add(new NPC("Warrior", 1250, 400, 200, 1500));
        enemies.add(new NPC("Berserker", 2500, 1000, 250, 3000));
        enemies.add(new NPC("Praetorian", 5000, 3000, 300, 5000));
        enemies.add(new NPC("Dragon", 15000, 10000, 400, 8000));
        enemies.add(new NPC("Hero", 30000, 25000, 500, 12500));
        enemies.add(new NPC("Colossus", 75000, 50000, 750, 25000));
        enemies.add(new NPC("Saitama", 250000, 150000, 1000, 100000));
        enemies.add(new NPC("God", 1000000, 500000, 2000, 500000));
    }
}
