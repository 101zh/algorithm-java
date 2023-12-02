package Inheritance;

public class Character {
    public int maxHealth;
    public int curHealth;
    public int attackDamage;
    public int attackRNG;
    public boolean dead;

    public Character() {
        maxHealth = 100;
        curHealth = 100;
        attackDamage = 20;
        attackRNG = 2;
        dead = false;
    }

    public Character(int maxHealth, int attackDamage, int attackRNG) {
        this.maxHealth = maxHealth;
        setHealth(maxHealth);
        this.attackDamage = attackDamage;
        this.attackRNG = attackRNG;
        dead = false;

        setAliveOrDead();
    }

    public void attack(Character character) {
        int rng = (int) (Math.random() * 5) - 2;

        int finalDamage = attackDamage + rng;
        character.setHealth(character.curHealth - finalDamage);

        System.out.println(" just dealt " + finalDamage + " damage");
    }

    public void setHealth(int health) {
        if (health < 0) {
            curHealth = 0;
        } else if (health > maxHealth) {
            curHealth = maxHealth;
        } else {
            curHealth = health;
        }
        setAliveOrDead();

    }

    public void setAliveOrDead() {
        if (curHealth <= 0) {
            dead = true;
        } else {
            dead = false;
        }
    }

}
