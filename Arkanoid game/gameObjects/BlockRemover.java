//Michael Landesman Nir
package gameObjects;

import gameSetup.Game;
import Interfaces.HitListener;

/**
 * A BlockRemover class that implements the HitListener interface.
 * It is responsible for removing blocks from the game and keeping track of the
 * remaining blocks count.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with the given game and remaining blocks counter.
     *
     * @param game            the game instance
     * @param remainingBlocks the counter representing the removed blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        hitter.setColor(beingHit.getColor());
        beingHit.removeFromGame(this.game);
        // Update the counter
        this.remainingBlocks.decrease(1);
    }
}