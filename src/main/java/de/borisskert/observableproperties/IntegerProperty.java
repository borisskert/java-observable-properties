package de.borisskert.observableproperties;

/**
 * Implements a {@link Property} which holds a {@link Integer} value.
 * The value cannot be null.
 */
public class IntegerProperty extends SimpleObjectProperty<Integer> {

    /**
     * Creates a {@link IntegerProperty} containing an initial value.
     *
     * @param value the initial {@link Integer} value.
     */
    public IntegerProperty(int value) {
        super(value);
    }
}
