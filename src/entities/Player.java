package entities;

public class Player extends Entity {
    private int level = 0;
    private int XP = 0;

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

    /** Gain gold based on the amount dropped by an enemy. */
    public void gainGold(NPC enemy) {
        this.modGold(enemy.getGold());
    }

    /** Modify player's gold by given amount. Result must be non-negative. */
    public void modGold(int amount) {
        this.gold += amount;
        if (this.gold < 0) this.gold = 0;
    }

    /** Gain XP based on the XP value of the enemy. */
    public void gainXP(NPC enemy) {
        this.modXP(enemy.getXP());
    }

    /** Adds the specified amount of XP. */
    private void modXP(int amount) { this.XP += amount; }

    private int getXP() { return XP; }

    private int getXPToLevelUp() { return 5 * (level + 1); }

    /** As long as the XP requirement for level up is met, level up. */
    public void calcLevelUp() {
        boolean leveledUp = false;

        while (XP >= getXPToLevelUp()) {
            XP -= getXPToLevelUp();
            levelUp();
            leveledUp = true;
        }

        if (leveledUp) {
            System.out.printf("You leveled up! You are now level %d.\n", level);
        } else {
            System.out.printf("%d XP required for next level.\n", getXPToLevelUp() - XP);
        }
    }

    /** Increase stats and level. */
    private void levelUp() {
        double MaxHPIncreaseFromLevelUp = (maxHP * 0.05) + 1;
        double StrengthIncreaseFromLevelUp = (strength * 0.05) + 1;

        level++;
        maxHP += MaxHPIncreaseFromLevelUp;
        strength += StrengthIncreaseFromLevelUp;
    }

    public void displayCharacterStats() {
        System.out.printf("Name: %s\n", name);
        System.out.printf("Level: %d | XP: %d / %d\n", level, XP, getXPToLevelUp());
        System.out.printf("Gold: %d\n", gold);
        System.out.printf("HP: %d / %d\n", currentHP, maxHP);
        System.out.printf("Strength: %d\n\n", strength);
    }
}