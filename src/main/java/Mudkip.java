import java.awt.Rectangle;

public class Mudkip extends WaterType {

    /**
     * Constructor
     * @param x The X position of Mudkip
     * @param y The Y position of Mudkip
     * @param bounds The bounding Rectangle
     */
    public Mudkip(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
        setImage("../resources/mudkip.png");
    }

    @Override
    public boolean canReproduceWithPokemon(Pokemon other) {
        int reproduceChance;

        // can only reproduce if of same species, and if there aren't 2 kids
        if (other instanceof Mudkip && (this.getChildren() < 2)
            && this.collidesWithPokemon(other)
            && this.getLevel() >= 50) {

            // determine probability, 1 in 3 (30%)
            reproduceChance = this.getRand().nextInt(3) + 1;

            if (reproduceChance == 2) {
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

            return new Mudkip(this.getXPos(), this.getYPos(),
            this.getBounds());

        }

        return null;


    }

    @Override
    public boolean isOld() {

        // Can have max level of 120
        if (this.getLevel() > 120) {
            return true;
        }

        return false;


    }


    @Override
    public boolean canHarmPokemon(Pokemon other) {

        int hitChance; // used to get a number to determine if we can attack

        if (other instanceof Mudkip && this.collidesWithPokemon(other)) {
            // if fighting another Mudkip, decrease attack prob. by 12%
            hitChance = this.getRand().nextInt(5) + 1; // effectively 20%

            if (hitChance == 3) {
                return true;
            }

        } else if (this.collidesWithPokemon(other)) {
            // if not, call our super
            super.canHarmPokemon(other);
        }

        return false;


    }

}
