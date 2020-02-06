package com.github.borisskert;

import java.util.ArrayList;
import java.util.List;

public class SimpleObjectProperty<T> implements Property<T> {

    private final List<ChangeListener<T>> listeners = new ArrayList<>();

    private T value;


    public SimpleObjectProperty(T value) {
        this.value = value;
    }

    @Override
    public void set(T value) {
        T oldValue = this.value;
        this.value = value;

        this.listeners.forEach(listener -> listener.onChange(this, oldValue, value));
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void addListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<T> listener) {
        listeners.remove(listener);
    }
}
