package Inheritance;

import edu.princeton.cs.algs4.StdIn;

public class Enemy extends Character {

    int[] attacks = { 10, 20, 30 };

    public Enemy() {
        super();
    }

    public void chooseAttack() {
        int attack = (int) (Math.random() * 3);
        attackDamage = attacks[attack];
    }

    public static void main(String[] args) {
        Character character = new Character();
        Enemy enemy = new Enemy();
        System.out.println();

        while (!enemy.dead && !character.dead) {
            System.out.println("You: " + character.curHealth + "/" + character.maxHealth);
            System.out.println("The enemy: " + enemy.curHealth + "/" + enemy.maxHealth);
            System.out.println("Attack? (y/n)");
            String input = StdIn.readLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            if (input.equals("y")) {
                System.out.print("You");
                character.attack(enemy);
            } else {
                System.out.println("What why????");
            }

            enemy.chooseAttack();
            System.out.print("The enemy");
            enemy.attack(character);
            System.out.println();

        }

        System.out.println("You: " + character.curHealth + "/" + character.maxHealth);
        System.out.println("The enemy: " + enemy.curHealth + "/" + enemy.maxHealth);

        if (character.dead && enemy.dead) {
            System.out.println("You both died 💀");
        } else if (enemy.dead) {
            System.out.println("You Won!");
        } else {
            System.out.println("You lost RIP");
        }
    }

}
