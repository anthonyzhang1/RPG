import entities.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final Scanner reader = new Scanner(System.in);
    private static boolean isNewGame = true; // true when first starting game

    private static Player player;
    private static final ArrayList<NPC> enemies = new ArrayList<>();

    private static final String CHECK_CHARACTER = "C";
    private static final String FIGHT = "F";
    private static final String HELP = "H";
    private static final String QUIT = "Q";
    private static final String RESET_ENEMIES = "R";
    private static final String SHOP = "S";
    private static final String WAIT = "W";

    public static void main(String[] args) {
        if (isNewGame) {
            System.out.print("What shall be your name? ");
            String name = reader.nextLine();
            player = new Player(name, 5, 1);

            initializeEnemies();
            isNewGame = false;
        }

        printActions();
        while (true) {
            System.out.print("Enter field action: ");
            String action = reader.nextLine().toUpperCase();

            switch (action) {
                case QUIT -> {
                    System.out.println("Exiting.");
                    System.exit(0);
                }
                case HELP -> printActions();
                case FIGHT -> {
                    if (!player.isAlive()) System.out.println("You are dead.");
                    else if (getEnemy() == null) System.out.println("All enemies slain.");
                    else {
                        new Battle(reader, player, getEnemy()).beginBattle();
                        printActions();
                    }
                }
                case WAIT -> {
                    if (!player.isAlive()) {
                        System.out.println("You are revived.");
                        player.modHP(1);
                    } else player.rest();
                }
                case CHECK_CHARACTER -> player.displayCharacterStats();
                case SHOP -> new Shop(reader, player).openShop();
                case RESET_ENEMIES -> {
                    enemies.clear();
                    initializeEnemies();
                    System.out.println("Area reset.");
                }
                default -> System.out.println("Unrecognized command.");
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
        System.out.println("'R' = Reset Enemies");
        System.out.println("'Q' = Quit Game\n");
    }

    /** Adds enemies to the 'enemies' array. */
    private static void initializeEnemies() {
        enemies.add(new NPC("Mouse", 2, 1, 5, 5));
        enemies.add(new NPC("Rat", 4, 2, 15, 10));
        enemies.add(new NPC("Imp", 7, 3, 25, 15));
        enemies.add(new NPC("Goblin", 10, 5, 40, 25));
        enemies.add(new NPC("Human", 20, 7, 60, 40));
        enemies.add(new NPC("Thief", 30, 15, 80, 50));
        enemies.add(new NPC("Highwayman", 40, 20, 110, 75));
        enemies.add(new NPC("Thug", 75, 30, 140, 100));
        enemies.add(new NPC("Bandit", 80, 40, 190, 125));
        enemies.add(new NPC("Militia", 150, 50, 250, 150));
        enemies.add(new NPC("Guard", 300, 75, 325, 175));
        enemies.add(new NPC("Mercenary", 400, 100, 400, 200));
        enemies.add(new NPC("Warrior", 750, 150, 500, 250));
        enemies.add(new NPC("Berserker", 1250, 200, 600, 300));
        enemies.add(new NPC("Praetorian", 3000, 300, 700, 400));
        enemies.add(new NPC("Hero", 6000, 600, 800, 500));
        enemies.add(new NPC("Dragon", 10000, 1000, 900, 750));
        enemies.add(new NPC("Colossus", 30000, 3000, 1000, 1000));
        enemies.add(new NPC("Titan", 50000, 5000, 1250, 1500));
        enemies.add(new NPC("God", 100000, 10000, 1500, 2000));
        enemies.add(new NPC("Saitama", 300000, 30000, 2000, 3000));
    }
}