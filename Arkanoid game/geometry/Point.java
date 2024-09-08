//Michael Landesman Nir
package geometry;
import java.util.Random;

/**
 * The Point class representing a point with x and y coordinates in a 2D space.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class Point {
    //A constant value used for comparing doubles
    public static final double THRESHOLD = 0.00000001;
    private double x;
    private double y;

    /**
     * Constructs a point object with given x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance of this point to the other point.
     *
     * @param other the other point to calculate the distance to
     * @return the distance between this point and other point
     */
    public double distance(Point other) {
        return (Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Checks if this point is equal to other point.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return compDoubles(this.x, other.getX()) && compDoubles(this.y, other.getY());
    }

    /**
     * Returns the x coordinate of this point.
     *
     * @return the x coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of this point.
     *
     * @return the y coordinate of this point.
     */
    public double getY() {
        return this.y;
    }


    /**
     * Check if two doubles are equal.
     *
     * @param d1 the first double to compare
     * @param d2 the second double to compare
     * @return true if the doubles are equal within the threshold value, false otherwise
     */
    public static boolean compDoubles(double d1, double d2) {
        double diff = d1 - d2;
        return Math.abs(diff) <= THRESHOLD;
    }


    /**
     * Generates a random double value within a given range.
     *
     * @param min the min value of the double
     * @param max the max value of the double
     * @return random double value within the range
     */
    public static double generateRandomDouble(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    /**
     * Generates a random point within a given rectangle.
     *
     * @param i1 the starting x coordinate of the area
     * @param i2 the starting y coordinate of the area
     * @param width  the width of the area
     * @param height the height of the area
     * @return point within the rectangle area
     */
    public static Point generateRandomPoint(int i1, int i2, int width, int height) {
        double x = Point.generateRandomDouble(i1, i1 + width);
        double y = Point.generateRandomDouble(i2, i2 + height);

        return new Point(x, y);
    }

}