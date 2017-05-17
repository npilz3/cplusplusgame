import java.awt.Rectangle;

public class Blaziken extends FireType {

    /**
     * Constructor
     * @param x The X position of Blaziken
     * @param y The Y position of Blaziken
     * @param bounds The bounding Rectangle
     */
    public Blaziken(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/blaziken.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {

        int reproduceChance;

        // can only reproduce if of same species, and if there aren't 2 kids
        if (other instanceof Blaziken && (this.getChildren() < 2)
                && this.collidesWithPokemon(other)
                && this.getLevel() >= 50) {

            // determine probability, 1 in 2 (50%)
            reproduceChance = this.getRand().nextInt(2) + 1;

            if (reproduceChance == 2) {
                return true;
            }

        }

        return false;

    }

    @Override
    public Pokemon reproduceWithPokemon(Pokemon other) {

        // Now, see if they can reproduce
        if (this.canReproduceWithPokemon(other)) {

            // increment children count
            int curChildren = this.getChildren();
            this.setChildren(curChildren + 1);

            return new Blaziken(this.getXPos(), this.getYPos(),
            this.getBounds());

        }

        return null;


    }

    @Override
    public boolean isOld() {
        // if Blaziken is over level 150, it's old
        if (this.getLevel() > 150) {
            return true;

        }

        return false;

    }


    @Override
    public boolean canHarmPokemon(Pokemon other) {

        int hitChance; // used to get a number to determine if we can attack

        // If we're fighting another fire type of a higher level,
        // probability of hitting to 90%
        if (other instanceof FireType && this.collidesWithPokemon(other)) {

            // if it is, grab it's level & compare
            if (other.getLevel() > this.getLevel()) {
                // Now increase attack probability to 90%
                hitChance = this.getRand().nextInt(10) + 1;

                if (hitChance <= 9) {
                    return true;

                }

            } else {
                // if the level isn't greater, it only has a 12% chance
                hitChance = this.getRand().nextInt(25) + 1;

                if (hitChance <= 3) {
                    return true;
                }

            }

        } else if (this.collidesWithPokemon(other)) {
            // if it's not another fire type, call our normal harm method
            super.canHarmPokemon(other);
        }

        return false;


    }

}
