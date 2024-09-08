//Michael Landesman Nir
package Interfaces;
import biuoop.DrawSurface;
import gameSetup.Game;

/**
 * The Sprite interface represents an object that can be drawn on a DrawSurface and can change its state over time.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface object.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add the Sprite object to the game.
     *
     * @param g the game to add the sprite to
     */
    void addToGame(Game g);
}