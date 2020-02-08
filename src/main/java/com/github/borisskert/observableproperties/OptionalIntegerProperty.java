package com.github.borisskert.observableproperties;

/**
 * Implements a {@link OptionalProperty} which holds a {@link Integer} value.
 * The value is mandatory and may be null.
 */
public class OptionalIntegerProperty extends SimpleOptionalProperty<Integer> {

    /**
     * Creates a {@link OptionalIntegerProperty} without an initial value.
     */
    public OptionalIntegerProperty() {
        super();
    }

    /**
     * Creates a {@link OptionalIntegerProperty} containing an initial value.
     *
     * @param value the initial {@link Integer} value which may be null.
     */
    public OptionalIntegerProperty(Integer value) {
        super(value);
    }
}
