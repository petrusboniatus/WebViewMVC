package com.awesome.business.template.api;

import com.awesome.business.template.controller.util.Utils;
import javafx.application.Platform;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public class ObservableProperty<T> {

    private T value;
    private List<JSObject> listeners = new ArrayList<>();

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
}
