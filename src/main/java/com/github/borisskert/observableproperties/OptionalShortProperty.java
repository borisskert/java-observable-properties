package com.github.borisskert.observableproperties;

/**
 * Implements a {@link OptionalProperty} which holds a {@link Short} value.
 * The value is mandatory and may be null.
 */
public class OptionalShortProperty extends SimpleOptionalProperty<Short> {

    /**
     * Creates a {@link OptionalShortProperty} without an initial value.
     */
    public OptionalShortProperty() {
        super();
    }

    /**
     * Creates a {@link OptionalShortProperty} containing an initial value.
     *
     * @param value the initial {@link Short} value which may be null.
     */
    public OptionalShortProperty(Short value) {
        super(value);
    }
}
