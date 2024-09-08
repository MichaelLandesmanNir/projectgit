//Michael Landesman Nir
package geometry;
import java.util.List;

/**
 * The Line class represents a line in a two-dimensional coordinate system.
 * It has start and end points, slope, and b values for the line equation.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class Line {
    private Point start;
    private Point end;
    private double slope;
    private double b;

    /**
     * Constructs a new Line with two given points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        //Calculate the slope and the b value of the line based on the points
        this.slope = calculateSlope();
        this.b = findB();
    }

    /**
     * Constructs a new Line with four given coordinates.
     *
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        //Calculate the slope and the b value of the line based on the points
        this.slope = calculateSlope();
        this.b = findB();
    }

    /**
     * Calculate the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculate the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculate slope of the line based on start and end points.
     *
     * @return the slope of the line
     */
    public double calculateSlope() {
        if (Point.compDoubles(this.end.getX(), this.start.getX())) {
           return Double.MAX_VALUE;
        }

        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Find the b value for the line equation.
     *
     * @return the b value for the line equation
     */
    public double findB() {
        return this.end.getY() - (this.slope * this.end.getX());
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //First handle the case that one or both lines are parallel to the Y axis
        if (this.lineParallelToYAxis() || other.lineParallelToYAxis()) {
            return handleYParallelCase(other);
        } else if (!Point.compDoubles(this.slope, other.slope)) {
            //If slopes are not the same then check if intersection point in both lines range
            double interX = (other.b - this.b) / (this.slope - other.slope);
            return this.xOnLine(interX) && other.xOnLine(interX);
        } else {
            //Otherwise check lines Inclusion
            if (Point.compDoubles(this.b, other.b)) {
                return linesHasSameEdge(other) || linesInclusion(other);
            } else {
                return false;
            }
        }
    }

    /**
     * Check if x value is on the line.
     *
     * @param x the x value to be checked
     * @return true if x is on the line, otherwise false
     */
    public boolean xOnLine(double x) {
        return xInLineRange(x) || xIsLineEdge(x);
    }

    /**
     * Check if x value is in line range.
     *
     * @param x the x value to be checked
     * @return true if x is in the line range, otherwise false
     */
    public boolean xInLineRange(double x) {
        return (x > this.start.getX() && x < this.end.getX()) || (x > this.end.getX() && x < this.start.getX());
    }

    /**
     * Check if x value is the x coordinate of one of the line's edges.
     *
     * @param x the x value to be checked
     * @return true if x is in the x coordinate of one of the line's edges, otherwise false
     */
    public boolean xIsLineEdge(double x) {
        return Point.compDoubles(this.start.getX(), x) || Point.compDoubles(this.end.getX(), x);
    }

    /**
     * Check if y value is on the line.
     *
     * @param y the y value to be checked
     * @return true if y is in the line range or edges, otherwise false
     */
    public boolean yOnLine(double y) {
        return (y > this.start.getY() && y < this.end.getY()) || (y > this.end.getY() && y < this.start.getY())
                || Point.compDoubles(y, this.start.getY())  || Point.compDoubles(y, this.end.getY());
    }

    /**
     * Check if two lines are included in each other.
     *
     * @param other the other line to check inclusion with.
     * @return true if both lines are included in each other, otherwise false
     */
    public boolean linesInclusion(Line other) {
        return xInLineRange(other.start.getX()) || xInLineRange(other.end.getX())
                || other.xInLineRange(this.start.getX()) || other.xInLineRange(this.end.getX());
    }

    /**
     * Check if two lines has same edge point.
     *
     * @param other the other line to check its edges.
     * @return true if the lines have same edge, false otherwise
     */
    public boolean linesHasSameEdge(Line other) {
        return this.start.equals(other.start) || this.start.equals(other.end)
            || this.end.equals(other.start) || this.end.equals(other.end);
    }

    /**
     * Check if line is parallel to y-axis.
     *
     * @return true if line is parallel to y-axis, false otherwise
     */
    public boolean lineParallelToYAxis() {
        return Double.isInfinite(this.b) && Point.compDoubles(this.slope, Double.MAX_VALUE);
    }

    /**
     * Handle the case in which one or both lines are parallel to y-axis.
     *
     * @param other the other line which might be parallel to y-axis.
     * @return true if lines are included in each other or intersecting one another, false otherwise
     */
    public boolean handleYParallelCase(Line other) {
        if (this.lineParallelToYAxis() && other.lineParallelToYAxis()) {
            return yParallelComp(other);
        }
        return this.lineParallelToYAxis() ? yParallelIntersection(other) : other.yParallelIntersection(this);
    }

    /**
     * Check if two lines that parallel to y-axis are included in each other.
     *
     * @param other the other line to check inclusion with
     * @return true if lines are included or have same edge, false otherwise
     */
    public boolean yParallelComp(Line other) {
        if (Point.compDoubles(this.start.getX(), this.end.getX())
            && Point.compDoubles(other.start.getX(), other.end.getX())) {
            if (Point.compDoubles(this.start.getX(), other.start.getX())) {
                return linesHasSameEdge(other) || linesInclusion(other);
            }
        }

        return false;
    }

    /**
     * Check if lines are intersecting, in of one of the line is parallel to the y-axis.
     *
     * @param other the other line to check intersection with
     * @return true if the lines are intersecting, false otherwise
     */
    public boolean yParallelIntersection(Line other) {
        if (other.xOnLine(this.start.getX())) {
            double interY = other.findYbyX(this.start.getX());
            return yOnLine(interY);
        }

        return false;
    }

    /**
     * Find the y value of a point by given x using the line equation.
     *
     * @param x the x value of a point
     * @return the y value based on the line equation
     */
    public double findYbyX(double x) {
        return (this.slope * x) + this.b;
    }

    /**
     * Find the x value of a point by given y using the line equation.
     *
     * @param y the y value of a point
     * @return the x value based on the line equation
     */
    public double findXbyY(double y) {
        return (y - this.b) / this.slope;
    }

    /**
     * Find the intersection point of two lines if the lines intersect.
     *
     * @param other the other line to check intersection with
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersectionPoint = null;
        if (this.isIntersecting(other)) {
            //If slopes are not the same find the intersection point
            if (!Point.compDoubles(this.slope, other.slope)) {
                //Check if one of the lines is parallel to the Y axis
                if (this.lineParallelToYAxis()) {
                    intersectionPoint = new Point(this.start.getX(), other.findYbyX(this.start.getX()));
                } else if (other.lineParallelToYAxis()) {
                    intersectionPoint = new Point(other.start.getX(), this.findYbyX(other.start.getX()));
                } else {
                    //Otherwise find the intersection point and return it
                    double interX = (other.b - this.b) / (this.slope - other.slope);
                    intersectionPoint = new Point(interX, findYbyX(interX));
                }
            } else if (!this.equals(other) && !linesInclusion(other) && linesHasSameEdge(other)) {
                //If lines has same edge point than return it
                if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    intersectionPoint = this.start;
                } else {
                    intersectionPoint = this.end;
                }
            }
        }

        return intersectionPoint;
    }

    /**
     * Check if two lines are equal based in their start and end points.
     *
     * @param other the other line to compare
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        } else {
            return this.start.equals(other.end) && this.end.equals(other.start);
        }
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect to check intersection with
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //First get all the intersection points
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closestPoint = null;
        //If there are any intersection points, find the closest one and return it
        for (Point point : intersectionPoints) {
            if (closestPoint == null || point.distance(this.start) < closestPoint.distance(this.start)) {
                closestPoint = point;
            }
        }

        return closestPoint;
    }

}
