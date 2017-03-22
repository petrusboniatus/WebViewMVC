package com.awesome.business.template.model;

import com.awesome.business.template.controller.AbstractController;
import com.awesome.business.template.controller.ViewApplication;
import javafx.scene.web.WebEngine;


/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public class Main {

    public static void main(String... args) {
        TestController controller = ViewApplication.startView(
                TestController::new,
                () -> "view/MainView.html"
        );
    }

    public static class TestController extends AbstractController {

        public TestController(WebEngine engine) {
            super(engine);
        }
    }
}
