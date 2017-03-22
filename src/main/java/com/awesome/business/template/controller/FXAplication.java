package com.awesome.business.template.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.concurrent.Semaphore;

/**
 * Created by pedro on 22/03/17.
 */
public class FXAplication extends Application {


    static Semaphore semaphore = new Semaphore(0);
    static Semaphore semaphoreShow = new Semaphore(0);
    static WebView view;
    static VBox box;

    @Override
    public void start(Stage primaryStage) throws Exception {

        view = loadScene(primaryStage);

        view.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue == Worker.State.SUCCEEDED) {
                System.out.println("Release");
                semaphore.release();
            }
        });

        primaryStage.show();
        System.out.println("show");
        semaphoreShow.release();
    }

    static void iniciar() {

        Thread thread = new Thread(() -> {
            try {
                Application.launch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        try {
            semaphoreShow.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebView getView() {
        return view;
    }

    public static void loadView(WebView view, String htmlLocation) {

        Platform.runLater(() -> {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL url = loader.getResource(htmlLocation);

            if (url == null) {
                throw new IllegalStateException("Missing MainView in location: " + htmlLocation);
            }
            view.getEngine().load(url.toExternalForm());

            System.out.println("Test");
        });

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebView loadScene(Stage stage) {

        view = new WebView();
        box = new VBox();
        box.getChildren().add(view);
        Scene scene = new Scene(box);

        stage.setScene(scene);
        return view;
    }

    public static void exit() {
        Platform.exit();
    }

}
