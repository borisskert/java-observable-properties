package com.github.borisskert;

import java.util.Optional;

public interface OptionalProperty<T> extends Property<T> {
    Optional<T> asOptional();
}
