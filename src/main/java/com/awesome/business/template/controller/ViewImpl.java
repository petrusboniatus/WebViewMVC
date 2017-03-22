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
    private VBox caja;

    public ViewImpl(String url, WebView webView, VBox caja) {
        this.url = url;
        this.webView = webView;
        this.caja = caja;
    }

    @Override
    public String getHtmlUrl() {
        return url;
    }

    @Override
    public Object runOnJS(String script) {
        return webView.getEngine().executeScript(script);
    }

    @Override
    public void addObjectOnJS(Object obj, String name) {

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
        return caja;
    }

}
