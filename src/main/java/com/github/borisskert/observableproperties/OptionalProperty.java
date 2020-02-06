package com.github.borisskert.observableproperties;

import java.util.Optional;

/**
 * Represents an optional {@link Property}
 *
 * @param <T> the value type
 */
public interface OptionalProperty<T> extends Property<T> {

    /**
     * Provides an {@link Optional} which contains the current value.
     *
     * @return the current value as {@link Optional}
     */
    Optional<T> asOptional();
}
