package de.borisskert.observableproperties;

/**
 * Implements a {@link Property} which holds a {@link Short} value.
 * The value cannot be null.
 */
public class ShortProperty extends SimpleObjectProperty<Short> {

    /**
     * Creates a {@link ShortProperty} containing an initial value.
     *
     * @param value the initial {@link Short} value.
     */
    public ShortProperty(short value) {
        super(value);
    }
}
