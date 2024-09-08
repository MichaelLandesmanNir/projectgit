//Michael Landesman Nir
package Interfaces;
/**
 * The HitNotifier interface represents an object that can notify hit events to registered listeners.
 *
 * @author Michael Landesman Nir
 * @version 1.0
 * @since 2024 -04-03
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}