//Michael Landesman Nir
package gameObjects;
import geometry.Point;

/**
 * The Velocity class represents a velocity of a ball in 2D space.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a new velocity with the given horizontal and vertical components.
     *
     * @param dx the horizontal component of the velocity
     * @param dy dy the vertical component of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Return the dx value of the velocity.
     *
     * @return the dx value of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Return the dy value of the velocity.
     *
     * @return the dy value of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Applies the velocity to a point and returns a new point with the resulting position.
     *
     * @param p the starting point to apply the velocity to
     * @return new point with the resulting position after applying the velocity
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;

        return new Point(newX, newY);
    }

    /**
     * This method constructs a new velocity with the given angle and speed.
     *
     * @param angle the angle (in degrees) of the velocity vector
     * @param speed the speed of the velocity
     * @return new velocity object based on speed and angle
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //Convert the angle to radians
        double radiansAngle = Math.toRadians(angle);
        //Calculate dx and dy as requested
        double dx = Math.sin(radiansAngle) * speed;
        double dy = -Math.cos(radiansAngle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Calculate the speed vector of the velocity.
     *
     * @return the speed of the velocity
     */
    public double calculateSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * Checks if two velocities are equal base on their dx and dy attributes.
     *
     * @param other the other velocity to compare
     * @return true if the velocities are equal, otherwise false
     */
    public boolean equals(Velocity other) {
        return Point.compDoubles(this.getDx(), other.getDx()) && Point.compDoubles(this.getDy(), other.getDy());
    }
}