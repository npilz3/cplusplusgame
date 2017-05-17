/**
 * A grass type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;

public abstract class GrassType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of the Grass type Pokemon
     * @param y The Y position of the Grass type Pokemon
     * @param bounds The bounding Rectangle
     */
    public GrassType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        int hitChance; // used to store a random number to determine attack


        // Check if opposing pokemon is of water type
        // if it is, increase attack probability (50%)
        if (other instanceof WaterType) {
            // Grab the pokemon's Random object
            // to increase attack chance, generate b/t 1 & 2 (50%)
            hitChance = this.getRand().nextInt(2) + 1;

            if (hitChance == 2) {
                return true;
            }

            // Check if opposing type is fire type
            // if it is, decrease attack probability (making it 20%)
        } else if (other instanceof FireType) {

            hitChance = this.getRand().nextInt(5) + 1;

            if (hitChance == 3) {
                return true;
            }

            // If it's neither a water nor grass type, let's make it 1 in 3
        } else {
            hitChance = this.getRand().nextInt(3) + 1;

            if (hitChance == 2) {
                return true;
            }


        }

        return false;


    }


    // Checks to see if we're in the grass square, if so, regain health
    @Override
    public void move() {
        // Check bounds of world
        int worldWidth = (int) this.getBounds().getWidth();
        int worldHeight = (int) this.getBounds().getHeight();

        // Grass square is top left square, so:
        if (this.getXPos() <= (worldWidth / 2)
            && this.getYPos() <= (worldHeight / 2)) {

            // If in here, we can regain health, and move like normal
            this.setHealth(this.getHealth() + 20);
            super.move();

        } else {
            // if we're not in the square, move like normal
            super.move();
        }

    }

}
