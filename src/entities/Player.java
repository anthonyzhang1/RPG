package entities;

public class Player extends Entity {
    private int XP = 0;
    private int level = 0;

    public Player(String name, int maxHP, int strength) {
        super(name, maxHP, strength);
    }

    /** Used when waiting. Restores HP. */
    public void rest() {
        int HPRestoredFromRest;

        if (maxHP < 10) HPRestoredFromRest = 1;
        else HPRestoredFromRest = (int) (maxHP * 0.1); // 10% of max HP

        this.modHP(HPRestoredFromRest);
        System.out.printf("You restore %d HP while waiting, and now have %d HP.\n",
                          HPRestoredFromRest, getCurrentHP());
    }

    /** Gain XP based on the XP value of the enemy. */
    public void gainXP(NPC enemy) {
        this.modXP(enemy.getXP());
        System.out.printf("You gained %d XP and now have %d XP.\n", enemy.getXP(), this.getXP());
    }

    /** Adds the specified amount of XP. */
    public void modXP(int amount) { this.XP += amount; }
    public int getXP() { return XP; }

    private static final int XP_FOR_LEVEL_UP = 10;

    /** As long as the XP requirement for level up is met, level up. */
    public void calcLevelUp() {
        boolean leveledUp = false;

        while (XP >= XP_FOR_LEVEL_UP) {
            XP -= XP_FOR_LEVEL_UP;
            levelUp();
            leveledUp = true;
        }
        if (leveledUp) {
            System.out.printf("You leveled up! You are now level %d.\n", level);
            System.out.printf("You now have %d XP.\n", XP);
        }
    }

    /** Increase stats and level, and replenishes HP. */
    private void levelUp() {
        level++;
        maxHP += (maxHP * 0.03) + 1; // >3% growth rate
        strength += (strength * 0.02) + 1; // >2% growth rate
    }
}
