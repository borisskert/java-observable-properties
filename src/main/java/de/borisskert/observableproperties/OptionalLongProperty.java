package de.borisskert.observableproperties;

/**
 * Implements a {@link OptionalProperty} which holds a {@link Long} value.
 * The value is mandatory and may be null.
 */
public class OptionalLongProperty extends SimpleOptionalProperty<Long> {

    /**
     * Creates a {@link OptionalLongProperty} without an initial value.
     */
    public OptionalLongProperty() {
        super();
    }

    /**
     * Creates a {@link OptionalLongProperty} containing an initial value.
     *
     * @param value the initial {@link Long} value which may be null.
     */
    public OptionalLongProperty(Long value) {
        super(value);
    }
}
