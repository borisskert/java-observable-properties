package de.borisskert.observableproperties;

/**
 * Implements a {@link Property} which holds a {@link Double} value.
 * The value cannot be null.
 */
public class DoubleProperty extends SimpleObjectProperty<Double> {

    /**
     * Creates a {@link DoubleProperty} containing an initial value.
     *
     * @param value the initial {@link Double} value.
     */
    public DoubleProperty(double value) {
        super(value);
    }
}
