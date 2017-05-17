/**
 * The abstract Pokemon for the PokeBattle Simulation
 *
 * @author Heather, Aniruddha
 */
import java.util.Random;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public abstract class Pokemon {

    private Rectangle worldRectangle;
    private int xPos;
    private int yPos;
    private ImageIcon image;
    private int health;
    private int level;
    private int children;
    private int speed;
    private static Random rand = new Random();

    /**
     * Constructor
     *
     * Represents a Pokemon in the PokeWorld. Each Pokemon
     * has a location in the world and attributes which help
     * it reproduce and thrive.
     * @param xPos The X position of this Pokemon
     * @param yPos The Y position of this Pokemon
     * @param worldRectangle The boundaries of the PokeWorld where
     *               the Pokemon can exist
     */
    public Pokemon(int xPos, int yPos, Rectangle worldRectangle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.worldRectangle = worldRectangle;
        this.level = 0;
        this.health = 150;
        this.children = 0;
        this.speed = 20;
    }

    /**
     * @return the bounding rectangle of the PokeWorld
     *             that this Pokemon exists in
     */
    public Rectangle getBounds() {
        return worldRectangle;
    }

    /**
     * @return the X position of this Pokemon
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * @return the Y position of this Pokemon
     */
    public int getYPos() {
        return yPos;
    }


    /**
     * @return the current health value of this Pokemon
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return the current level of this Pokemon
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the number of children this Pokemon has
     */
    public int getChildren() {
        return children;
    }

    /**
     * @return the current speed of this pokemon
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return the Random object used by Pokemon for random events
     */
    public static Random getRand() {
        return rand;
    }

    /**
    * Sets the image attribute for this pokemon
    * @param image the ImageIcon to use to represent this Pokemon
    */
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    /**
     * Sets the image attribute for this pokemon
     * @param image the path to the image to use for this Pokemon
     */
    public void setImage(String path) {
        this.image = new ImageIcon(path);
    }

    /**
     * Sets the Pokemon's health value
     * @param health The new health of the Pokemon
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the Pokemon's level
     * @param level The new level of the Pokemon
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the number of children of this Pokemon
     * @param children The number of children this Pokemon now has
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Sets the speed of this Pokemon
     * @param speed the updated speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Should draw the Pokemon at its location.
     * @param Graphics object for drawing use
     */
    public void draw(Graphics g) {
        image.paintIcon(null, g, xPos, yPos);
    }

    // Implement the following methods:

    /**
     * Will move the Pokemon in a random yet effective manner (it doesn't move
     * in circles). Each time a Pokemon moves, its health should decrease and
     * its level should increase.
     */
    public void move() {
        // Update postions in coordinate axis to simulate movement

        // First, use speed to determine magnitude of movement
        int mvmtMag = (int) (this.speed * (2)); // change this scale if needed

        // Then get a random int between 1 & 4
        int mvmtDir = rand.nextInt(4) + 1;

        // Use that int to determine movement direction
        if (mvmtDir == 1) {
            // move up
            this.yPos = this.yPos + mvmtMag;

        } else if (mvmtDir == 2) {
            // move down
            this.yPos = this.yPos - mvmtMag;

        } else if (mvmtDir == 3) {
            // move right
            this.xPos = this.xPos + mvmtMag;

        } else if (mvmtDir == 4) {
            // move left
            this.xPos = this.xPos - mvmtMag;
        }

        // Check if we're still in bounds
        if (this.xPos < 0) {
            this.xPos = 0;

        } else if (this.xPos > (int) this.worldRectangle.getWidth()) {
            this.xPos = (int) this.worldRectangle.getWidth();

        } else if (this.yPos < 0) {
            this.yPos = 0;

        } else if (this.xPos + this.image.getIconWidth()
            > (int) this.worldRectangle.getWidth()) {
            this.xPos = (int) this.worldRectangle.getWidth()
                    - this.image.getIconWidth();

        } else if (this.yPos > (int) this.worldRectangle.getHeight()) {
            this.yPos = (int) this.worldRectangle.getHeight();

        } else if (this.yPos + this.image.getIconHeight()
            > (int) this.worldRectangle.getHeight()) {
            this.yPos = (int) this.worldRectangle.getHeight()
                    - this.image.getIconHeight();
        }

        // Increase level by 1
        this.level = this.level + 1;

        // Decrease health by 3, but don't make it negative
        if (this.health >= 1) {
            this.health = this.health - 1;
        }

    }

    /**
     * Returns whether or not this Pokemon is colliding with a given Pokemon.
     * This should be determined by the Pokemon's location and the dimensions of
     * its image.
     * @param other the Pokemon to check for collision with
     * @return true if the two Pokemon have collided, false otherwise
     */
    public boolean collidesWithPokemon(Pokemon other) {

        // if the xPos and yPos match up, they are definitely colliding
        if (this.xPos == other.xPos & this.yPos == other.yPos) {
            return true;

        } else {
            // if not, we shoud check the image dimensions
            int thisWidth = this.image.getIconWidth();
            int thisHeight = this.image.getIconHeight();

            int otherWidth = other.image.getIconWidth();
            int otherHeight = other.image.getIconHeight();

            // Then we can compute our sides to see if it's within bounds
            int thisLeftSide = this.xPos;
            int thisRightSide = this.xPos + thisWidth;
            int thisTopSide = this.yPos;
            int thisBottomSide = this.yPos + thisHeight;

            int otherLeftSide = other.xPos;
            int otherRightSide = other.xPos + otherWidth;
            int otherTopSide = other.yPos;
            int otherBottomSide = other.yPos + otherHeight;


            // Now check to see if they're crossing
            if (otherRightSide > thisLeftSide
                && otherRightSide < thisRightSide
                && otherTopSide > thisTopSide
                && otherTopSide < thisBottomSide) {
                return true;

            } else if (otherRightSide > thisLeftSide
                && otherRightSide < thisRightSide
                && otherBottomSide > thisTopSide
                && otherBottomSide < thisBottomSide) {
                return true;

            } else if (otherLeftSide > thisLeftSide
                && otherLeftSide < thisRightSide
                && otherBottomSide > thisTopSide
                && otherBottomSide < thisBottomSide) {
                return true;

            } else if (otherLeftSide > thisLeftSide
                && otherLeftSide < thisRightSide
                && otherTopSide > thisTopSide
                && otherTopSide < thisBottomSide) {
                return true;

            }

            return false;

        }
    }

    /**
     * Returns whether or not the two Pokemon can reproduce
     * @param other the Pokemon to check if this can reproduce with
     * @return true if the two Pokemon can reproduce, false otherwise
     */
    public abstract boolean canReproduceWithPokemon(Pokemon other);

    /**
     * If the two Pokemon are able to reproduce (as determined by
     * canReproduceWithPokemon(Pokemon)), this method returns a new Pokemon of
     * the same type and in the same location. If for some reason, reproduction
     * does not happen, null should be returned.
     * Reproduction rates should be controlled in some manner so that infinite
     * population growth does not occur. This can be acheived in a variety of
     * ways: reproduction probability, limiting total offspring per
     * Pokemon, etc.
     * @param other the Pokemon to reproduce with
     * @return the offspring Pokemon
     */
    public abstract Pokemon reproduceWithPokemon(Pokemon other);

    /**
     * Returns whether or not a Pokemon has surpassed some determined max level.
     * @return true if the Pokemon has passed the specified level,
     *              false otherwise
     */
    public abstract boolean isOld();

    /**
    * Returns whether or not a Pokemon can damage another Pokemon
    * @param other the Pokemon for which to check if harming is possible
    * @return true if this can harm Pokemon a, false otherwise
    */
    public abstract boolean canHarmPokemon(Pokemon other);

    /**
    * Harms another instance of a Pokemon by decreasing its health by a fixed
    * amount.
    * @param other the Pokemon to harm
    */
    public void harmPokemon(Pokemon other) {
        // Check if we can harm other pokemon

        if (this.canHarmPokemon(other)) {
            // Now actually harm the pokemon
            other.setHealth((other.getHealth()) - 20);


        }
    }

    /**
     * Called when a Pokemon faints.
     */
    public void faint() {
        this.health = 0;
    }

    /**
     * Returns whether or not the Pokemon has fainted (ie health 0).
     * @return true if this Pokemon has fainted, false otherwise
     */
    public boolean hasFainted() {
        if (this.health <= 0) {
            return true;
        }

        return false;
    }

}
