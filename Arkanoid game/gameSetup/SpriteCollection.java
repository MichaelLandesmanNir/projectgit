//Michael Landesman Nir
package gameSetup;
import biuoop.DrawSurface;
import Interfaces.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection interface represents a collection of sprites, with methods for adding sprites,
 * updating their time passed, and drawing them onto a DrawSurface.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Creates a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Updates the time passed for all sprites in the collection by calling their timePassed() method.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection onto a given DrawSurface by calling their drawOn() method.
     *
     * @param d the DrawSurface onto which the sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }
}
