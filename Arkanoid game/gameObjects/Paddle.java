//Michael Landesman Nir
package gameObjects;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameSetup.Game;
import geometry.Point;
import geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.Sprite;

import java.awt.Color;


/**
 * The Paddle class representing a paddle in a game. The paddle is both a Sprite and a Collidable object,
 * and can move left or right in response to keyboard input.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */

public class Paddle implements Sprite, Collidable {
    //The speed at which the paddle moves left or right.
    public static final int PADDLE_SPEED = 5;
    //The number of regions the paddle is divided into for ball collision detection.
    public static final int PADDLE_REGIONS = 5;
    //The number of degrees that the ball's angle is changed when it hits a region of the paddle.
    public static final int DEGREES_CHANGE = 30;
    //The default width of the screen.
    public static final int DEFAULT_SCREEN_WIDTH = 750;
    private Point upperLeft;
    private int width;
    private int height;
    private KeyboardSensor keyboard;
    private int screenStart;
    private int screenWidth;

    /**
     * Creates a new paddle with the specified upper-left corner, width, height and keyboard.
     *
     * @param upperLeft the upper-left corner of the paddle.
     * @param width the width of the paddle.
     * @param height the height of the paddle
     * @param keyboard the keyboard to control the paddle movement
     */
    public Paddle(Point upperLeft, int width, int height, KeyboardSensor keyboard) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.keyboard = keyboard;
        //Set default screen attributes
        this.screenStart = 0;
        this.screenWidth = DEFAULT_SCREEN_WIDTH;
    }

    /**
     * Sets the upper-left corner of the paddle to the specified point.
     *
     * @param upperLeft the new upper-left corner of the paddle.
     */
    private void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Sets the screen attributes for the game that re valid for the paddle.
     *
     * @param screenStart the starting position of the game screen.
     * @param screenWidth the width of the game screen.
     */
    public void setScreenAttributes(int screenStart, int screenWidth) {
        this.screenStart = screenStart;
        this.screenWidth = screenWidth;
    }

    /**
     * Moves the paddle to the left by PADDLE_SPEED pixels in a circular fashion.
     */
    public void moveLeft() {
        double x = (this.upperLeft.getX() - PADDLE_SPEED + this.screenWidth) % (this.screenWidth);
        double y = this.upperLeft.getY();
        setUpperLeft(new Point(x, y));
    }

    /**
     * Moves the paddle to the right by PADDLE_SPEED pixels in a circular fashion.
     */
    public void moveRight() {
        double x = (this.upperLeft.getX() + PADDLE_SPEED + this.screenWidth) % this.screenWidth;
        double y = this.upperLeft.getY();
        setUpperLeft(new Point(x, y));
    }

    /**
     * Updates the position of the paddle according to the user's keyboard input.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface on which the paddle should be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), this.width, this.height);
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.upperLeft, this.width, this.height);
    }

    /**
     * This method calculates a new velocity based on which region of the paddle was hit.
     *
     @param collisionPoint the point where the ball collided with the paddle
     @param currentVelocity the current velocity of the ball
     @return a new velocity for the ball after the collision with the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionSize = (double) this.width / PADDLE_REGIONS;
        for (int i = -2, j = 1; i <= 2; i++, j++) {
            if (collisionPoint.getX() <= this.upperLeft.getX() + j * regionSize) {
                //For the middle part just change the dy attribute
                if (i == 0) {
                   return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                } else {
                    double speed = currentVelocity.calculateSpeed();
                    return Velocity.fromAngleAndSpeed(i * DEGREES_CHANGE, speed);
                }
            }
        }
        return currentVelocity;
    }

    /**
     * This method adds the paddle to a given game.
     *
     @param g the game to add the paddle to
     */
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}