/**
 * A fire type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;

public abstract class FireType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Fire type
     * @param y The Y position of this Fire type
     * @param bounds The bounding Rectangle
     */
    public FireType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {

        int hitChance; // used to get a number to determine if we can attack

        // Check if the opposing pokemon is of grass type
        // if it is, increase probability of attack, making it 50%
        if (other instanceof GrassType) {
            // Grab the pokemon's Random object
            // to increase attack chance, generate b/t 1 & 2 (50%)
            hitChance = this.getRand().nextInt(2) + 1;

            if (hitChance == 2) {
                return true;
            }

            // Check if opposing pokemon is water type
            // if it is, decrease probability of attack (making it 20%)
        } else if (other instanceof WaterType) {
            hitChance = this.getRand().nextInt(5) + 1;

            if (hitChance == 3) {
                return true;
            }

            // If it's neither a grass nor water type, give it a 1 in 3 chance
        } else {
            hitChance = this.getRand().nextInt(3) + 1;

            if (hitChance == 2) {
                return true;
            }

        }

        return false;



    }

    // Make move method that checks if in fire sqaure
    // if (in fire square), setSpeed, else setSpeed 20, super.move()
    @Override
    public void move() {
        // Check bounds of world
        int worldWidth = (int) this.getBounds().getWidth();
        int worldHeight = (int) this.getBounds().getHeight();

        // Fire square is top right square, so:
        if (this.getXPos() >= (worldWidth / 2) && this.getXPos() <= worldWidth
            && this.getYPos() <= (worldHeight / 2)) {

            // Make our speed faster
            this.setSpeed(60);
            super.move();

        } else {
            // if not, set our speed back to normal
            this.setSpeed(20);
            super.move();
        }

    }

}
