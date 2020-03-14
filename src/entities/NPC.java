package entities;

public class NPC extends Entity {
    private int XP;

    public NPC(String name, int maxHP, int strength, int XP, int gold) {
        super(name, maxHP, strength);
        this.XP = XP;
        this.gold = gold;
    }

    public int getXP() { return XP; }
}
