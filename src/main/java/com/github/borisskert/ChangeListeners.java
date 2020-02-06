package com.github.borisskert;

import java.util.LinkedList;
import java.util.List;

class ChangeListeners<T> implements ChangeListener<T> {

    private final List<ChangeListener<T>> listeners = new LinkedList<>();

    @Override
    public void onChange(ReadonlyProperty<T> property, T oldValue, T newValue) {
        this.listeners.forEach(listener -> listener.onChange(property, oldValue, newValue));
    }

    void addListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    void removeListener(ChangeListener<T> listener) {
        listeners.remove(listener);
    }
}
