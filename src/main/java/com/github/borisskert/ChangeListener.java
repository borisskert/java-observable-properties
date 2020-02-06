package com.github.borisskert;

public interface ChangeListener<T> {
    void onChange(ReadonlyProperty<T> property, T oldValue, T newValue);
}
