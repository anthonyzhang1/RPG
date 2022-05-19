import entities.Player;
import java.util.Scanner;

public class Shop {
    private Scanner reader;
    private Player player;

    private static ShopItem maxHPIncrease = new ShopItem("Max HP Up", 5);
    private static ShopItem strengthIncrease = new ShopItem("Strength Up", 10);

    public Shop(Scanner reader, Player player) {
        this.reader = reader;
        this.player = player;
    }

    public void openShop() {
        displayShop();

        while (true) {
            System.out.println("What would you like to buy?");
            System.out.println("Enter the number in the bracket to purchase it, ");
            System.out.print("or enter a non-number to exit the shop: ");

            int answer;
            try {
                answer = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nExiting the shop.");
                break;
            }

            ShopItem selectedItem = ShopItem.items.get(answer);
            if (selectedItem == null) {
                System.out.println("That entry does not exist.");
                continue;
            }

            System.out.print("How many would you like to buy? ");
            int quantity;
            try {
                quantity = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid response.");
                continue;
            }

            if (quantity == 0) {
                System.out.println("The quantity must be non-zero!");
                continue;
            }

            int cost = quantity * selectedItem.getCost();

            if (cost > player.getGold()) {
                System.out.println("You do not have enough money to purchase that!");
                continue;
            } else if (selectedItem.equals(maxHPIncrease)) {
                player.modMaxHP(quantity);
            } else if (selectedItem.equals(strengthIncrease)) {
                player.modStrength(quantity);
            }

            player.modGold(-cost);
            break;
        }
    }

    /** Displays items being sold in the shop. */
    public void displayShop() {
        System.out.println("Items for Sale:");
        ShopItem.displayItems();
        System.out.println();
    }
}
