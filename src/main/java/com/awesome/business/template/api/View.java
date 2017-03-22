package com.awesome.business.template.api;

import javafx.scene.layout.VBox;

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public interface View {

    String getHtmlUrl();

    Object runOnJS(String script);

    void addObjectOnJS(Object obj, String name);

    VBox getFrameBox();
}
