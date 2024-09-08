//Michael Landesman Nir
package Interfaces;

import gameObjects.Ball;
import gameObjects.Velocity;
import gameSetup.Game;
import geometry.Point;
import geometry.Rectangle;

/**
 * The Collidable interface represents an object in a game that can be collided with.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public interface Collidable {

    /**
     * Returns the collision shape of the object.
     *
     @return the collision shape of the object as a geometry.Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that we collided with it at the given collision point with a given velocity.
     * Calculates the new velocity expected after the hit based on the force the object inflicted on us.
     *
     * @param collisionPoint the point where the collision occurred
     * @param currentVelocity the velocity of the object before the collision
     * @param hitter the ball which hit the collidable
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Add the Collidable object to the game environment.
     *
     * @param g the game to add the collidable to
     */
    void addToGame(Game g);
}