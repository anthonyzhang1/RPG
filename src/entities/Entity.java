package entities;

public class Entity {
    private String name;
    int maxHP;
    int currentHP;
    int strength;

    public Entity(String name, int maxHP, int strength) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.strength = strength;
    }

    /** Ensures HP is between 0 and maxHP. */
    private void checkHP() {
        if (currentHP < 0) currentHP = 0;
        if (currentHP > maxHP) currentHP = maxHP;
    }

    /*
    /** Sets the HP to the given amount. *
    void setHP(int amount) {
        currentHP = amount;
        checkHP();
    }
    */

    /** Modifies the HP by the given amount. */
    public void modHP(int amount) {
        currentHP += amount;
        checkHP();
    }

    public int getCurrentHP() { return currentHP; }

    public String getName() { return name; }

    // An entity is alive if its HP is non-zero.
    public boolean isAlive() { return currentHP > 0; }

    /** Subtracts the HP of the defending entity based on the attacker's attack. */
    public void attack(Entity defender) {
        defender.modHP(-this.strength);
        System.out.printf("%s took %d damage and has %d HP remaining.\n",
                          defender.getName(), this.strength, defender.getCurrentHP());
    }
}