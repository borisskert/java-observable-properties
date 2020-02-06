package com.github.borisskert.observableproperties;

import java.util.Optional;

public interface OptionalProperty<T> extends Property<T> {
    Optional<T> asOptional();
}
