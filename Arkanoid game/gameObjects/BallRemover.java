//Michael Landesman Nir
package gameObjects;

import gameSetup.Game;
import Interfaces.HitListener;

/**
 * A BallRemover class that implements the HitListener interface.
 * It is responsible for removing balls from the game when they hit a block.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover with the given game and remaining balls counter.
     *
     * @param game           the game instance
     * @param remainingBalls the counter representing the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Handles the hit event when a ball hits a block.
     * Removes the ball from the game and decreases the remaining balls count.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}
