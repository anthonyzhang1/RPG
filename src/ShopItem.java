import java.util.HashMap;

public class ShopItem {
    private static int id = 1;
    private String name;
    private int cost;

    public static HashMap<Integer, ShopItem> items = new HashMap<>();

    public ShopItem(String name, int cost) {
        this.name = name;
        this.cost = cost;

        items.put(id, this);
        id++;
    }

    /** Displays the items stored in the 'items' HashMap. */
    public static void displayItems() {
        for (HashMap.Entry<Integer, ShopItem> item : items.entrySet()) {
            Integer id = item.getKey();
            String name = item.getValue().getName();
            Integer cost = item.getValue().getCost();

            System.out.printf("[%d] %s: %d gp\n", id, name, cost);
        }
    }

    public String getName() { return name; }

    public int getCost() { return cost; }
}
