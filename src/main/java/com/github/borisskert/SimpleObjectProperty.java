package com.github.borisskert;

public class SimpleObjectProperty<T> implements Property<T> {

    private final ChangeListeners<T> listeners = new ChangeListeners<>();

    private T value;


    public SimpleObjectProperty(T value) {
        this.value = value;
    }

    @Override
    public void set(T value) {
        T oldValue = this.value;
        this.value = value;

        this.listeners.onChange(this, oldValue, value);
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void addListener(ChangeListener<T> listener) {
        listeners.addListener(listener);
    }

    @Override
    public void removeListener(ChangeListener<T> listener) {
        listeners.removeListener(listener);
    }
}
