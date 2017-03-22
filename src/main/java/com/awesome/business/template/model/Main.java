package com.awesome.business.template.model;

import com.awesome.business.template.api.View;
import com.awesome.business.template.api.ViewHandler;
import com.awesome.business.template.controller.AbstractController;
import com.awesome.business.template.controller.ViewApplication;
import com.awesome.business.template.controller.ViewHandlerImpl;
import javafx.scene.web.WebEngine;


/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public class Main {

    public static void main(String... args) {

        ViewHandler viewHandler = new ViewHandlerImpl();
        View vista = viewHandler.loadView("view/MainView.html");
        viewHandler.show(vista);

    }

    public static class TestController extends AbstractController {

        public TestController(WebEngine engine) {
            super(engine);
        }
    }


}
