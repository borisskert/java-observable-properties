package de.borisskert.observableproperties;

/**
 * Implements a {@link Property} which holds a {@link Long} value.
 * The value cannot be null.
 */
public class LongProperty extends SimpleObjectProperty<Long> {

    /**
     * Creates a {@link LongProperty} containing an initial value.
     *
     * @param value the initial {@link Long} value.
     */
    public LongProperty(long value) {
        super(value);
    }
}
