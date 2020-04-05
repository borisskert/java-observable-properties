package de.borisskert.observableproperties;

/**
 * Implements a {@link Property} which holds a {@link Boolean} value.
 * The value cannot be null.
 */
public class BooleanProperty extends SimpleObjectProperty<Boolean> {

    /**
     * Creates a {@link BooleanProperty} containing an initial value.
     *
     * @param value the initial {@link Boolean} value.
     */
    public BooleanProperty(boolean value) {
        super(value);
    }
}
