//Michael Landesman Nir
package Interfaces;
import gameObjects.Ball;
import gameObjects.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
