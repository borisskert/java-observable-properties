package com.github.borisskert;

public interface Property<T> extends ReadonlyProperty<T> {

    void set(T value);
}
