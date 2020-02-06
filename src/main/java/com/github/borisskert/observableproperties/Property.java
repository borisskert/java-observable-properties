package com.github.borisskert.observableproperties;

public interface Property<T> extends ReadonlyProperty<T> {

    void set(T value);
}
