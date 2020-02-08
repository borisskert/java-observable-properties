package com.github.borisskert.observableproperties;

/**
 * Implements a {@link Property} which holds a {@link String} value.
 * The value cannot be null.
 */
public class StringProperty extends SimpleObjectProperty<String> {

    /**
     * Creates a {@link StringProperty} containing an initial value.
     *
     * @param value the initial {@link String} value.
     */
    public StringProperty(String value) {
        super(value);
    }
}
