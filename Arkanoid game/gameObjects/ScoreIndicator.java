//Michael Landesman Nir
package gameObjects;
import biuoop.DrawSurface;
import gameSetup.Game;
import Interfaces.Sprite;
import java.awt.Color;

/**
 * A ScoreIndicator class that implements the Sprite interface.
 * It represents a graphical score indicator in a game.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class ScoreIndicator implements Sprite {
    //The font size of the score text
    public static final int FONT_SIZE = 14;
    private Counter score;
    private int width;
    private int height;

    /**
     * Constructs a ScoreIndicator with the given score, width, and height.
     *
     * @param score the Counter object representing the score
     * @param width the width of the score indicator
     * @param height the height of the score indicator
     */
    public ScoreIndicator(Counter score, int width, int height) {
        this.score = score;
        this.width = width;
        this.height = height;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, this.width, this.height);
        String text = "Score: " + score.getValue();
        d.setColor(Color.BLACK);
        d.drawText((this.width / 2) - 10, this.height - 4, text, FONT_SIZE);
    }

    @Override
    public void timePassed() {
        // No updates needed for the score indicator
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
