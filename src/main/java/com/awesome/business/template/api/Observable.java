package com.awesome.business.template.api;

import com.awesome.business.template.controller.util.Utils;
import javafx.application.Platform;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public class Observable<T> {

    private List<JSObject> listeners = new ArrayList<>();
    private T value;

    public Observable() {
        this(null);
    }

    public Observable(T defaultValue) {
        value = defaultValue;
    }

    public void addListener(JSObject callback) {
        listeners.add(callback);
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        T oldValue = value;
        value = newValue;

        Platform.runLater(() -> {
            listeners.forEach(obj -> Utils.runCallback(obj, oldValue, newValue));
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observable<?> that = (Observable<?>) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
