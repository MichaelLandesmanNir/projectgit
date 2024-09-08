// Michael Landesman Nir
package gameObjects;

import biuoop.DrawSurface;
import gameSetup.Game;
import gameSetup.GameEnvironment;
import geometry.Line;
import geometry.Point;
import Interfaces.Sprite;

import java.awt.Color;

/**
 * The Ball class represents a circular object with size, color, location, and
 * velocity properties.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class Ball implements Sprite {
    // The default borders size of the ball
    public static final int DEFAULT_BORDER = 200;
    // The minimum radius size of the ball
    public static final int MIN_SIZE = 1;
    // The MAX_SIZE_SPEED variable represents the radius size which above it all
    // balls has same speed
    public static final int MAX_SIZE_SPEED = 50;
    // The maximum speed of the ball
    public static final double MAX_SPEED = 30;
    // The minimum speed of the ball
    public static final double MIN_SPEED = 0.5;
    // The screen divider is using to calculate the max radius size possible
    public static final int SCREEN_DIVIDER = 2;
    private int size;
    private Color color;
    private Point location;
    private Velocity velocity;
    private Point startBound;
    private int width;
    private int height;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a Ball object with the given center point, size, and color.
     *
     * @param center the location of the center of the ball
     * @param r      the size of the radius of the ball
     * @param color  the ball color
     */
    // constructor
    public Ball(Point center, int r, Color color) {
        this.size = r;
        this.color = color;
        this.location = center;
        // Set default values to velocity and bounds
        this.velocity = new Velocity(0, 0);
        this.startBound = new Point(0, 0);
        this.width = DEFAULT_BORDER;
        this.height = DEFAULT_BORDER;
        // Set the game environment to null
        this.gameEnvironment = null;
    }

    /**
     * Constructs a Ball object with the given x and y coordinates, size, and color.
     *
     * @param x     the x coordinate of the center point of the ball
     * @param y     the y coordinate of the center point of the ball
     * @param r     the size of the radius of the ball
     * @param color the ball color
     */
    public Ball(double x, double y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Returns the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.location.getX();
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.location.getY();
    }

    /**
     * Returns the size of the ball's radius.
     *
     * @return the size of the ball's radius
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Return the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the location of the ball manually in case needed.
     *
     * @param newLocation the new point to set the ball location to
     */
    private void setLocation(Point newLocation) {
        this.location = newLocation;
    }

    /**
     * Sets velocity of the ball using velocity object.
     *
     * @param v the velocity to set the ball velocity to
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity of the ball using dx and dy.
     *
     * @param dx the change in x direction
     * @param dy the change in y direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the ball's bounds.
     *
     * @param i1     the x coordinate of the starting point
     * @param i2     the y coordinate of the starting point
     * @param width  the width of the bound
     * @param height the height of the bound
     */
    public void setBounds(int i1, int i2, int width, int height) {
        this.startBound = new Point(i1, i2);
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the ball's game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets game environment to the ball.
     *
     * @param gameEnvironment the game environment to be set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the surface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // First validate the ball size
        validateSize();
        // Then draw the ball itself on the screen
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * This method calls the moveOneStep method to move the ball.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * This method adds the ball to a given game.
     *
     * @param g the game to add the ball to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * This method removes the ball to a given game.
     *
     * @param g the game to remove the ball from
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * This method validates that the ball size is fits into the bounds and resizes
     * the ball if needed.
     */
    public void validateSize() {
        if (this.size >= (this.height) / SCREEN_DIVIDER || this.size >= (this.width) / SCREEN_DIVIDER) {
            this.size = Math.min(this.height / SCREEN_DIVIDER, this.width / SCREEN_DIVIDER) - 1;
        } else if (this.size < MIN_SIZE) {
            this.size = MIN_SIZE;
        }
    }

    /**
     * This method checks what should be the ball next according to its obstacles
     * and move the ball one step
     * in its velocity direction and speed.
     */
    public void moveOneStep() {
        // First check if there is collision ahead
        CollisionInfo collisionInfo = this.getGameEnvironment().getClosestCollision(this.computeTrajectory());
        Velocity newVelocity = checkCollision(collisionInfo);
        // If no then continue in the trajectory
        if (newVelocity.equals(this.getVelocity())) {
            setLocation(this.getVelocity().applyToPoint(this.location));
        } else {
            // If there is an obstacle then move the ball to the collision point and set the
            // new velocity
            setLocation(calculateNewLocation(collisionInfo));
            setVelocity(newVelocity);
        }
    }

    /**
     * Compute trajectory line of the ball without obstacles.
     *
     * @return the trajectory line
     */
    private Line computeTrajectory() {
        Point startP = this.location;
        // Calculate the end point based on velocity and size, use the ratio to make
        // sure the ball won't overlap
        double sizeSpeedRatio = this.size / Math.min(Math.abs(this.velocity.getDx()), Math.abs(this.velocity.getDy()));
        double endPointX = this.getX() + this.velocity.getDx() + this.velocity.getDx() * sizeSpeedRatio;
        double endPointY = this.getY() + this.velocity.getDy() + this.velocity.getDy() * sizeSpeedRatio;
        Point endP = new Point(endPointX, endPointY);

        // Create new line and return it
        return new Line(startP, endP);
    }

    /**
     * This method checks if there is any collision with collidable object.
     *
     * @param collisionInfo the collision to be occurred.
     * @return the new velocity of the ball if there is a collision, otherwise the
     *         current ball's velocity
     */
    public Velocity checkCollision(CollisionInfo collisionInfo) {
        Velocity newVelocity = getVelocity();
        if (collisionInfo != null) {
            if (adjustLocationToPoint(collisionInfo)) {
                newVelocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
            }
        }
        return newVelocity;
    }

    /**
     * Checks if the collision is on the next frame.
     *
     * @param collisionInfo the collision to be occurred.
     * @return true if the collision will occur in the next frame, false otherwise.
     */
    private boolean adjustLocationToPoint(CollisionInfo collisionInfo) {
        Point collisionP = collisionInfo.collisionPoint();

        // Calculate the distance until the ball overlap
        double distanceX = Math.abs(this.velocity.getDx()) + this.size;
        double distanceY = Math.abs(this.velocity.getDy()) + this.size;

        // Return true if the distance of the ball from the collision is valid
        if (collisionInfo.collisionObject().getCollisionRectangle().hitOnSides(collisionP)) {
            return Math.abs(collisionP.getX() - this.location.getX()) < distanceX;
        }
        return Math.abs(collisionP.getY() - this.location.getY()) < distanceY;
    }

    /**
     * This method calculate the new ball location base on the collision point.
     *
     * @param collisionInfo the collision to be occurred.
     * @return the new location to be set.
     */
    private Point calculateNewLocation(CollisionInfo collisionInfo) {
        Point collisionPoint = collisionInfo.collisionPoint();
        double x;
        double y;

        // Calculate the new point base on the hit place
        if (collisionInfo.collisionObject().getCollisionRectangle().hitOnSides(collisionPoint)) {
            x = this.velocity.getDx() > 0 ? collisionPoint.getX() - this.size : collisionPoint.getX() + this.size;
            y = computeTrajectory().findYbyX(x);
        } else {
            y = this.velocity.getDy() > 0 ? collisionPoint.getY() - this.size : collisionPoint.getY() + this.size;
            x = computeTrajectory().findXbyY(y);
        }

        return new Point(x, y);
    }

    /**
     * Sets the color of the object.
     *
     * @param color The new color to be set.
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
