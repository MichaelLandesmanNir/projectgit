//Michael Landesman Nir
package gameSetup;
import Interfaces.Collidable;
import gameObjects.CollisionInfo;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the game environment, which contains all the collidable objects in the game.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class GameEnvironment {
    private List<Collidable> gameObjects;

    /**
     * Creates a new game environment with an empty list of game objects.
     */
    public GameEnvironment() {
        this.gameObjects = new ArrayList<Collidable>();
    }

    /**
     * Adds the given collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.gameObjects.add(c);
    }

    /**
     * Remove the given collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.gameObjects.remove(c);
    }

    /**
     * Returns a list of all the collidable objects in the game environment.
     *
     * @return a list of collidable objects
     */
    public List<Collidable> getCollidableObjects() {
        return this.gameObjects;
    }

    /**
     * Checks if a given trajectory line collides with any of the collidable objects in the game environment.
     * If a collision is detected, returns information about the closest collision point.
     *
     * @param trajectory the line representing the ball's trajectory
     * @return a CollisionInfo object containing information about the closest collision, null if no collision occurred
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;
        List<Collidable> collidables = new ArrayList<Collidable>(this.gameObjects);
        //foreach collidable object check if the is collision
        for (Collidable c : collidables) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (collisionPoint != null) {
                //If collision occurred then check if it's the closest one yet
                double currentPointDistance = collisionPoint.distance(trajectory.start());
                if (closestCollision == null) {
                    closestCollision = new CollisionInfo(collisionPoint, c);
                } else {
                    double closestPointDistance = closestCollision.collisionPoint().distance(trajectory.start());
                    if (currentPointDistance < closestPointDistance) {
                        closestCollision.setObject(c);
                        closestCollision.setPoint(collisionPoint);
                    }
                }
            }
        }

        return closestCollision;
    }
}
