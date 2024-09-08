//Michael Landesman Nir
package gameObjects;
import geometry.Point;
import Interfaces.Collidable;

/**
 * The CollisionInfo class represents the information about a collision between two objects in the game.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Creates a new Collision info object.
     */
    public CollisionInfo() { }

    /**
     * Creates a new CollisionInfo object with the given collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurred
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurred.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Sets the collision point of this CollisionInfo object to the given point.
     *
     * @param collisionPoint the new collision point
     */
    public void setPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * Sets the collidable object of this CollisionInfo object to the given collidable.
     *
     * @param collisionObject the new collidable object
     */
    public void setObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }
}
