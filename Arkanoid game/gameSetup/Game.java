//Michael Landesman Nir
package gameSetup;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import gameObjects.Ball;
import gameObjects.BallRemover;
import gameObjects.Block;
import gameObjects.BlockRemover;
import gameObjects.Counter;
import gameObjects.Paddle;
import gameObjects.ScoreIndicator;
import gameObjects.ScoreTrackingListener;
import geometry.Point;
import Interfaces.Collidable;
import Interfaces.Sprite;
import java.awt.Color;
import java.util.Random;

/**
 * The Game class represents the Arkanoid game.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class Game {
    //The size of the borders in the game
    public static final int BORDER_SIZE = 25;
    //The width of the blocks
    public static final int BLOCK_WIDTH = 50;
    //The height of the blocks
    public static final int BLOCK_HEIGHT = 20;
    public static final int SCORE_BORDER_HEIGHT = 20;
    //The width of the paddle
    public static final int PADDLE_WIDTH = 100;
    //The height of the paddle
    public static final int PADDLE_HEIGHT = 20;
    //Points to be added if all the blocks in the level are done
    public static final int LEVEL_POINTS = 100;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private int width;
    private int height;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;

    /**
     * Constructs a new Game with a given width and height.
     *
     * @param width  The width of the game screen.
     * @param height The height of the game screen.
     */
    public Game(int width, int height) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.width = width;
        this.height = height;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s The sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable object to the game.
     *
     * @param c The collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite object to the game.
     *
     * @param s The sprite object to remove.
     */
    public void  removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * This method initializes a new game. It creates the Blocks, Balls and Paddle and adds them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", this.width, this.height);
        initializeCounters();
        createBorders();
        createScoreIndicator();
        createBalls(3);
        createBlocks(6, 12);
        createPaddle();
    }

    /**
     * Initialize the game counters.
     */
    private void initializeCounters() {
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.score = new Counter();
    }

    /**
     * Creates the score indicator of the game and adds it to the game.
     */
    private void createScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, this.width, SCORE_BORDER_HEIGHT);
        scoreIndicator.addToGame(this);
    }

    /**
     * Creates the borders of the game screen and adds them to the game.
     */
    private void createBorders() {
        //Create the blocks
        Block topBorder = new Block(new Point(0, SCORE_BORDER_HEIGHT), this.width, BORDER_SIZE);
        Block leftBorder = new Block(new Point(0, BORDER_SIZE + SCORE_BORDER_HEIGHT), BORDER_SIZE,
                this.height - BORDER_SIZE - 66);
        Block rightBorder = new Block(new Point(this.width - BORDER_SIZE, BORDER_SIZE + SCORE_BORDER_HEIGHT),
                BORDER_SIZE, this.height - BORDER_SIZE - 66);
        Block bottomBorder = new Block(new Point(BORDER_SIZE, this.height), this.width - 2 * BORDER_SIZE, BORDER_SIZE);

        //Set the borders color
        topBorder.setColor(Color.darkGray);
        leftBorder.setColor(Color.darkGray);
        rightBorder.setColor(Color.darkGray);
        bottomBorder.setColor(Color.darkGray);

        //Add the blocks to the game
        topBorder.addToGame(this);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        bottomBorder.addToGame(this);

        //Bottom border is special as it the "death-area", create new BallRemover and add it to the bottom border
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        bottomBorder.addHitListener(ballRemover);
    }

    /**
     * Creates the paddle and adds it to the game.
     */
    private void createPaddle() {
        double upperLeftX = (double) (this.width - PADDLE_WIDTH) / 2;
        double upperLeftY = this.height - PADDLE_HEIGHT - BORDER_SIZE;
        Point upperLeft = new Point(upperLeftX, upperLeftY);
        Paddle paddle = new Paddle(upperLeft, PADDLE_WIDTH, PADDLE_HEIGHT, this.gui.getKeyboardSensor());
        paddle.setScreenAttributes(BORDER_SIZE, this.width - BORDER_SIZE);
        paddle.addToGame(this);
    }

    /**
     * Creates the blocks and adds them to the game.
     *
     @param numOfLines The number of lines of blocks to create.
     @param numOfBlocks The number of blocks in each line to create.
     */


    private void createBlocks(int numOfLines, int numOfBlocks) {
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);

        double x;
        double y = BLOCK_HEIGHT + 25; // Start from the top

        // Calculate the total width of all blocks in a line
        double totalLineWidth = numOfBlocks * BLOCK_WIDTH;

        // Calculate the initial x value to center the blocks horizontally
        double initialX = (this.width - totalLineWidth) / 2 + BLOCK_WIDTH / 2;

        for (int i = 0; i < numOfLines; i++) {
            // Generate random color for each line
            Color lineColor = generateRandomColor();
            x = initialX;
            for (int j = 0; j < i; j++) {
                x = x + BLOCK_WIDTH;
            }

            for (int j = 0; j < numOfBlocks - 2 * i; j++) {
                Block block = new Block(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT);
                block.setColor(lineColor);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                this.blocksCounter.increase(1);
                x = x + BLOCK_WIDTH;
            }
            y = y + BLOCK_HEIGHT;
        }
    }



    /**
     * Generates a random color.
     *
     * @return A random color
     */
    private Color generateRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    /**
     * Creates the balls and adds them to the game.
     *
     @param numberOfBalls The number of balls to create.
     */
    private void createBalls(int numberOfBalls) {
        //Increase the balls counter with the number of the balls
        this.ballsCounter.increase(numberOfBalls);

        //Create the balls
        for (int i = 1; i <= numberOfBalls; i++) {
            Ball ball = new Ball((double) this.width / (numberOfBalls + 1) * i, 300, 5, Color.BLACK);
            ball.setBounds(0, 0, this.width, this.height);
            ball.setVelocity(-3, -3);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
    }

    /**
     * Runs the game animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        boolean levelPointsAdded = false;
        while (true) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // Handle the frames timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            //End the game if the blocks or the balls are over
            if (this.blocksCounter.getValue() == 0 && !levelPointsAdded) {
                score.increase(LEVEL_POINTS);
                levelPointsAdded = true;
            } else if (this.blocksCounter.getValue() == 0 || this.ballsCounter.getValue() == 0) {
                this.gui.close();
                return;
            }
        }
    }
}
