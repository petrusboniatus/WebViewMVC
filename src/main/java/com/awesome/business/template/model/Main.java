package com.awesome.business.template.model;

import com.awesome.business.template.api.View;
import com.awesome.business.template.api.ViewHandler;
import com.awesome.business.template.controller.ViewHandlerImpl;


/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public class Main {

    public static void main(String... args) {
        ViewHandler viewHandler = new ViewHandlerImpl();
        View vista = viewHandler.loadView("view/MainView.html");

        vista.addObjectOnJS("enviado desde java", "ejemplo");

        viewHandler.show(vista);
    }
}
