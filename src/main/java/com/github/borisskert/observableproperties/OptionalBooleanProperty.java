package com.github.borisskert.observableproperties;

/**
 * Implements a {@link OptionalProperty} which holds a {@link Boolean} value.
 * The value is mandatory and may be null.
 */
public class OptionalBooleanProperty extends SimpleOptionalProperty<Boolean> {

    /**
     * Creates a {@link OptionalBooleanProperty} without an initial value.
     */
    public OptionalBooleanProperty() {
        super();
    }

    /**
     * Creates a {@link OptionalBooleanProperty} containing an initial value.
     *
     * @param value the initial {@link Boolean} value which may be null.
     */
    public OptionalBooleanProperty(Boolean value) {
        super(value);
    }
}
