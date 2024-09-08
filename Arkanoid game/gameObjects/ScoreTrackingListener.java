//Michael Landesman Nir
package gameObjects;
import Interfaces.HitListener;

/**
 * A ScoreTrackingListener class that implements the HitListener interface.
 * It is responsible for tracking the score in the game when blocks are hit.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class ScoreTrackingListener implements HitListener {
    public static final int POINTS_PER_BLOCK = 5;

    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with the given score counter.
     *
     * @param scoreCounter the counter representing the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       beingHit.removeHitListener(this);
       this.currentScore.increase(POINTS_PER_BLOCK);
    }
}
