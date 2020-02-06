package com.github.borisskert;

public class TestChangeListener<T> implements ChangeListener<T> {

    public ReadonlyProperty<T> property;
    public T oldValue;
    public T newValue;

    public int calls = 0;

    @Override
    public void onChange(ReadonlyProperty<T> property, T oldValue, T newValue) {
        calls++;
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
