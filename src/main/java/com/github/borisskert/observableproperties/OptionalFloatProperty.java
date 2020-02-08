package com.github.borisskert.observableproperties;

/**
 * Implements a {@link OptionalProperty} which holds a {@link Float} value.
 * The value is mandatory and may be null.
 */
public class OptionalFloatProperty extends SimpleOptionalProperty<Float> {

    /**
     * Creates a {@link OptionalFloatProperty} without an initial value.
     */
    public OptionalFloatProperty() {
        super();
    }

    /**
     * Creates a {@link OptionalFloatProperty} containing an initial value.
     *
     * @param value the initial {@link Float} value which may be null.
     */
    public OptionalFloatProperty(Float value) {
        super(value);
    }
}
