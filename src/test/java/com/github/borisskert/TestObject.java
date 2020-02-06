package com.github.borisskert;

import java.util.Objects;

public class TestObject {
    private final String text;

    public TestObject(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObject that = (TestObject) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "text='" + text + '\'' +
                '}';
    }
}
