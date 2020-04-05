package de.borisskert.observableproperties;

/**
 * Represents a {@link Property} which value can be changed by its {@link Property#set(Object)} method.
 *
 * @param <T> the value type
 */
public interface Property<T> extends ReadonlyProperty<T> {

    /**
     * Changes the value of this {@link Property}
     *
     * @param value the new value
     */
    void set(T value);
}
