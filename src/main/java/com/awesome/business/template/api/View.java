package com.awesome.business.template.api;

import javafx.scene.layout.VBox;

/**
 * Created by Carlos Couto Cerdeira and Pedro Bueno on 3/22/17.
 */
public interface View {

    String getHtmlUrl();

    void runOnJS(String script);

    void addObjectOnJS(String variable, Object value);

    VBox getFrameBox();
}
