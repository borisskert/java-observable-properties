package com.github.borisskert.observableproperties;

public interface ChangeListener<T> {
    void onChange(ReadonlyProperty<T> property, T oldValue, T newValue);
}
