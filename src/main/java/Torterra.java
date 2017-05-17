import java.awt.Rectangle;

public class Torterra extends GrassType {

    /**
     * Constructor
     * @param x The X position of Torterra
     * @param y The Y position of Torterra
     * @param bounds The bounding Rectangle
     */
    public Torterra(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/torterra.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        int reproduceChance;

        // can only reproduce if of same type, and if there aren't 2 kids
        if (other instanceof GrassType && (this.getChildren() < 2)
            && this.collidesWithPokemon(other)
            && this.getLevel() >= 50) {

            // determine probability, 1 in 5 (20%)
            reproduceChance = this.getRand().nextInt(5) + 1;

            if (reproduceChance == 4) {
                return true;
            }

        }

        return false;

    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {
        // first, see if they can reproduce in the first place

        if (this.canReproduceWithPokemon(other)) {

            // increment children count
            this.setChildren(this.getChildren() + 1);


            return new Torterra(this.getXPos(), this.getYPos(),
            this.getBounds());

        }

        return null;

    }

    @Override
    public boolean isOld() {
        // Max level of 100
        if (this.getLevel() > 100) {
            return true;
        }

        return false;

    }

    @Override
    public boolean canHarmPokemon(Pokemon other) {

        int hitChance; // used to get a number to determine if we can attack

        if (other instanceof FireType && this.collidesWithPokemon(other)) {

            // Give probability of 30%
            hitChance = this.getRand().nextInt(3) + 1;

            if (hitChance == 2) {
                return true;
            }


            // if it's not a fire type, check if it's a poliwhirl
        } else if (other instanceof Poliwhirl
            && this.collidesWithPokemon(other)) {
            // if it is, it gets a 70% chance to attack
            hitChance = this.getRand().nextInt(10) + 1;

            if (hitChance > 3) {
                return true;
            }


        } else if (this.collidesWithPokemon(other)) {
            // if it's not anything else, call the type's harm
            super.canHarmPokemon(other);

        }

        return false;



    }





}
