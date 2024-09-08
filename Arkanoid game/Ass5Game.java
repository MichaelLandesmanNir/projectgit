//Michael Landesman Nir

import gameSetup.Game;

/**
 * The Ass5Game class contains the main method that runs the game.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */

public class Ass5Game {
    /**
     * The main method of the game, creates a new game.Game object with a specified
     * width and height,
     * initializes the game and runs it.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game(800, 600);
        game.initialize();
        game.run();
    }
}
