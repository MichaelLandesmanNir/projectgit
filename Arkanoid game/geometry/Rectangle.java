//Michael Landesman Nir
package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a rectangle with a left upper point, width, and height.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class Rectangle {
    //The left upper point of the rectangle
    private Point upperLeft;
    //The width of the rectangle
    private double width;
    //The height of the rectangle
    private double height;

    /**
     * Constructs a new Rectangle object with the given location and dimensions.
     *
     * @param upperLeft the Point object representing the left upper point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points between the rectangle and the specified line.
     *
     * @param line the Line object to check intersection with.
     * @return a List of Point objects representing the intersection points, May be empty.
     */
    public List<Point> intersectionPoints(Line line) {
        //First create lines out of the rectangle borders
        Line[] rectangleBorders = getRectangleBorderLines();
        List<Point> intersectionPoints = new ArrayList<>();

        //Foreach line add its intersection point to the list
        for (Line border : rectangleBorders) {
            if (border.isIntersecting(line)) {
                Point intersectionPoint = border.intersectionWith(line);
                if (intersectionPoint != null) {
                    intersectionPoints.add(intersectionPoint);
                }
            }
        }

        return intersectionPoints;
    }

    /**
     * Returns an array of Line objects representing the borders of this rectangle.
     *
     * @return an array of Line objects representing the borders of this rectangle.
     */
    private Line[] getRectangleBorderLines() {
        //First create all the vertexes
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        Point bottomRight = new Point(upperRight.getX(), bottomLeft.getY());

        //Now create the lines using the points
        Line upperBorder = new Line(this.upperLeft, upperRight);
        Line leftBorder = new Line(this.upperLeft, bottomLeft);
        Line rightBorder = new Line(upperRight, bottomRight);
        Line bottomBorder = new Line(bottomLeft, bottomRight);

        //Add the lines to array and return it
        Line[] rectangleLines = new Line[4];
        rectangleLines[0] = upperBorder;
        rectangleLines[1] = leftBorder;
        rectangleLines[2] = rightBorder;
        rectangleLines[3] = bottomBorder;

        return rectangleLines;
    }

    /**
     * This method check whether the collision occurred on the sides of the rectangle.
     *
     * @param collisionPoint the collision point
     * @return true if the collision occurred on the sides, otherwise false.
     */
    public boolean hitOnSides(Point collisionPoint) {
        return Point.compDoubles(collisionPoint.getX(), this.upperLeft.getX())
                || Point.compDoubles(collisionPoint.getX(), this.upperLeft.getX() + this.width);
    }


    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
