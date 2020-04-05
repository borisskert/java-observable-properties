package de.borisskert.observableproperties;

/**
 * Implements a {@link OptionalProperty} which holds a {@link String} value.
 * The value is mandatory and may be null.
 */
public class OptionalStringProperty extends SimpleOptionalProperty<String> {

    /**
     * Creates a {@link OptionalStringProperty} without an initial value.
     */
    public OptionalStringProperty() {
        super();
    }

    /**
     * Creates a {@link OptionalStringProperty} containing an initial value.
     *
     * @param value the initial {@link String} value which may be null.
     */
    public OptionalStringProperty(String value) {
        super(value);
    }
}
