/**
 * A water type pokemon
 *
 * @author Farhan Tejani
 */

import java.awt.Rectangle;

public abstract class WaterType extends Pokemon {

    /**
     * Constructor
     * @param x The X position of this Water Type Pokemon
     * @param y The Y position of this Water Type Pokemon
     * @param bounds The bounding Rectangle
     */
    public WaterType(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {
        int hitChance;

        // Water types have a greater chance of harming fire types
        if (other instanceof FireType) {
            // Generate a hit chance of 1 in 2 (50%)
            hitChance = this.getRand().nextInt(2) + 1;

            if (hitChance == 2) {
                return true;

            }

        } else if (other instanceof GrassType) {
            // Generate a hit chance of 1 in 5 (20%)
            hitChance = this.getRand().nextInt(5) + 1;

            if (hitChance == 3) {
                return true;

            }


        } else {
            // If opposing is of neither type, then give it a 1 in 3 chance
            hitChance = this.getRand().nextInt(3) + 1;

            if (hitChance == 2) {
                return true;
            }

        }

        return false;


    }

    @Override
    public void move() {
        // Check bounds of world
        int worldWidth = (int) this.getBounds().getWidth();
        int worldHeight = (int) this.getBounds().getHeight();

        // Water square is bottom left square, so:
        if (this.getXPos() <= (worldWidth / 2)
            && this.getYPos() >= (worldHeight / 2)
            && this.getYPos() <= worldHeight) {

            // increase level by 4 in addition to 1, after super call
            super.move();
            this.setLevel(this.getLevel() + 4);

        } else {
            // if not, just call the normal move
            super.move();
        }


    }


}
