import java.util.Scanner;
import entities.*;

public class Battle {
    private Scanner reader;
    private Player player;
    private NPC enemy;

    public Battle(Scanner reader, Player player, NPC enemy) {
        this.reader = reader;
        this.player = player;
        this.enemy = enemy;
    }

    private static final String ATTACK = "F";
    private static final String HELP = "H";
    private static final String RUN = "R";
    private static final String QUIT = "Q";

    public void beginBattle() {
        System.out.printf("You have engaged in combat with %s!\n\n", enemy.getName());
        printActions();

        // Combat occurs as long as both parties are alive, or the player flees.
        while (bothAreAlive()) {
            if (battle().equals(RUN)) break; // If player chooses flee
        } // end while

        finishBattle();
    }

    /** Returns true when both parties are alive. */
    private boolean bothAreAlive() { return player.isAlive() && enemy.isAlive(); }

    private String battle() {
        System.out.print("Enter battle action: ");
        String action = reader.nextLine().toUpperCase();

        switch (action) {
            case QUIT:
                System.out.println("Exiting.");
                System.exit(0);
                break;
            case HELP:
                printActions();
                break;
            case ATTACK:
                player.attack(enemy);
                enemy.attack(player);
                break;
            case RUN:
                enemy.setHP(enemy.getMaxHP());
                System.out.println("You flee from the battle.");
                return RUN; // End combat
            default:
                System.out.println("Unrecognized command!");
        }

        return "";
    }

    /** Reports if the player or the enemy is dead, and calculates XP gain. */
    private void finishBattle() {
        if (!player.isAlive()) {
            enemy.setHP(enemy.getMaxHP());
            System.out.println("\nYou have been defeated.\n");
            return;
        }

        if (!enemy.isAlive()) {
            System.out.printf("\n%s has been slain.\n", enemy.getName());
            player.gainXP(enemy);
            player.gainGold(enemy);
            System.out.printf("You gained %d XP and %d gold.\n", enemy.getXP(), enemy.getGold());
            player.calcLevelUp();
        }

        System.out.println("\nYou return to the field.");
    }

    private static void printActions() {
        System.out.println("== Battle Actions ==");
        System.out.println("'H' = List Actions");
        System.out.println("'F' = Attack");
        System.out.println("'R' = Run");
        System.out.println("'Q' = Quit Game\n");
    }
}
