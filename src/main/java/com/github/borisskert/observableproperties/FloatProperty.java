package com.github.borisskert.observableproperties;

/**
 * Implements a {@link Property} which holds a {@link Float} value.
 * The value cannot be null.
 */
public class FloatProperty extends SimpleObjectProperty<Float> {

    /**
     * Creates a {@link FloatProperty} containing an initial value.
     *
     * @param value the initial {@link Float} value.
     */
    public FloatProperty(float value) {
        super(value);
    }
}
