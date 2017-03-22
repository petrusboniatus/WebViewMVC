package com.awesome.business.template.model;

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

    public void addListener(JSObject callback){
        listeners.add(callback);
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        T oldValue = value;
        value = newValue;

        Platform.runLater(() -> {
            listeners.forEach(obj -> {
                JSObject window = (JSObject) obj.eval("window");
                window.setMember("private_arg1", oldValue);
                window.setMember("private_arg2", newValue);
                obj.eval("this(private_arg1, private_arg2);");
            });
        });

    }
}
