package com.github.borisskert;

public interface ReadonlyProperty<T> {

    T get();

    void addListener(ChangeListener<T> listener);

    void removeListener(ChangeListener<T> listener);
}
