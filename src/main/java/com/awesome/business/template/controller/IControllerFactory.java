package com.awesome.business.template.controller;

import javafx.scene.web.WebEngine;

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public interface IControllerFactory<T extends AbstractController> {

    T create(WebEngine engine);

    default String getJsName() {
        return "controller";
    }
}
