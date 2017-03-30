package com.awesome.business.template.controller;

import com.awesome.business.template.api.View;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * Created by pedro on 22/03/17.
 */
public class ViewImpl implements View {

    private String url;
    private WebView webView;
    private VBox box;

    public ViewImpl(String url, WebView webView, VBox box) {
        this.url = url;
        this.webView = webView;
        this.box = box;
    }

    @Override
    public String getHtmlUrl() {
        return url;
    }

    @Override
    public void runOnJS(String script) {
        Platform.runLater(() -> {
            try {
                webView.getEngine().executeScript(script);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void addObjectOnJS(String name, Object obj) {

        Platform.runLater(() -> {
            webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {

                if (newValue == Worker.State.SUCCEEDED) {

                    JSObject window = (JSObject) webView.getEngine().executeScript("window");
                    window.setMember(name, obj);

                }
            });
        });
    }

    @Override
    public VBox getFrameBox() {
        return box;
    }

}
