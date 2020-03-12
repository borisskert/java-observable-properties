package com.github.borisskert.observableproperties;

import java.util.LinkedList;
import java.util.List;

/**
 * Encapsulates holding, adding and removing and changing values to bound {@link Property} instances.
 * This class is package-private and should only used within this package.
 *
 * @param <T> the value type
 */
class BoundProperties<T> {

    private final List<Property<T>> boundProperties = new LinkedList<>();

    public void bind(Property<T> property) {
        this.boundProperties.add(property);
    }

    public void unbind(Property<T> boundProperty) {
        this.boundProperties.remove(boundProperty);
    }

    public void setValue(T newValue) {
        boundProperties.forEach(boundProperty -> boundProperty.set(newValue));
    }
}
