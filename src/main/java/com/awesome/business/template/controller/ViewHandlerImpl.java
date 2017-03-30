package com.awesome.business.template.controller;

import com.awesome.business.template.api.View;
import com.awesome.business.template.api.ViewHandler;
import javafx.application.Platform;
import javafx.scene.web.WebView;

/**
 * Created by pedro on 22/03/17.
 */
public class ViewHandlerImpl implements ViewHandler {


    public ViewHandlerImpl() {
        FXApplication.start();
    }

    @Override
    public View loadView(String htmlUrl){

        WebView web = FXApplication.getView();
        return new ViewImpl(htmlUrl, web, FXApplication.box);
    }

    @Override
    public void show(View v) {

        FXApplication.loadView(FXApplication.getView(), v.getHtmlUrl());
        Platform.runLater(() -> {
            try {
                v.runOnJS("main()");
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("The javascript code need a main() on this framework");
            }
        });
    }

    @Override
    public void close() {
        Platform.exit();
    }


}
