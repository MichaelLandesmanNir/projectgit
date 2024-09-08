//Michael Landesman Nir
package gameObjects;
/**
 * The Counter class represents a simple counter that can be incremented and decremented.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public class Counter {
    private int count;

    /**
     * Constructs a counter with an initial count of 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return this.count;
    }
}