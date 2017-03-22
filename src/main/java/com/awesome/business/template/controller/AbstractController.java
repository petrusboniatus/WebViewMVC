package com.awesome.business.template.controller;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

/**
 * Created by Carlos Couto Cerdeira on 3/15/17.
 */
public abstract class AbstractController {

    protected final WebEngine engine;

    public AbstractController(WebEngine engine) {
        this.engine = engine;
    }

    public Object call(String function, Object... args) {
        JSObject obj = (JSObject) engine.executeScript("window");
        return obj.call(function, args);
    }
}
