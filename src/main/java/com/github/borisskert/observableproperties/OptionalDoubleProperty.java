package com.github.borisskert.observableproperties;

/**
 * Implements a {@link OptionalProperty} which holds a {@link Double} value.
 * The value is mandatory and may be null.
 */
public class OptionalDoubleProperty extends SimpleOptionalProperty<Double> {

    /**
     * Creates a {@link OptionalDoubleProperty} without an initial value.
     */
    public OptionalDoubleProperty() {
        super();
    }

    /**
     * Creates a {@link OptionalDoubleProperty} containing an initial value.
     *
     * @param value the initial {@link Double} value which may be null.
     */
    public OptionalDoubleProperty(Double value) {
        super(value);
    }
}
