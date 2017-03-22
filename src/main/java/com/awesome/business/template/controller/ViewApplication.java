package com.awesome.business.template.controller;

import com.awesome.business.template.controller.util.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Carlos Couto Cerdeira on 3/15/17.
 */
public class ViewApplication extends Application {

    private static Data bridge;
    private static AbstractController controller;
    private static final Semaphore threadSync = new Semaphore(0);
    private static List<Thread> threadPool = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public static <T extends AbstractController> T startView(IControllerFactory<T> factory, ILocator viewLocator, String... args) {
        bridge = new Data(factory, viewLocator);
        Thread thread = new Thread(() -> {
            try {
                Application.launch(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadPool.add(thread);
        thread.start();
        syncThreads();
        return (T) controller;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Data data = bridge;
        bridge = null;

        WebView view = loadScene(primaryStage);

        loadView(view, data.viewLocator);
        loadController(view, data.factory);

        primaryStage.show();
    }

    private static void syncThreads() {
        try {
            threadSync.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadController(WebView view, IControllerFactory factory) {

        view.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue == Worker.State.SUCCEEDED) {

                JSObject window = (JSObject) view.getEngine().executeScript("window");
                AbstractController controller = factory.create(view.getEngine());
                window.setMember(factory.getJsName(), controller);
                window.setMember("console", new Logger());

                view.getEngine().executeScript("main()");
                ViewApplication.controller = controller;
                threadSync.release();
            }
        });
    }

    private void loadView(WebView view, ILocator locator) {
        String viewLocation = locator.getLocation();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(viewLocation);

        if (url == null) {
            throw new IllegalStateException("Missing MainView in location: " + viewLocation);
        }
        view.getEngine().load(url.toExternalForm());
    }

    private WebView loadScene(Stage stage) {
        WebView view = new WebView();
        VBox frame = new VBox();
        Scene scene = new Scene(frame);

        frame.getChildren().add(view);
        stage.setScene(scene);
        return view;
    }

    public static void exit() {
        Platform.exit();
        threadPool.forEach(Thread::interrupt);
    }

    private static class Data {

        IControllerFactory factory;
        ILocator viewLocator;

        public Data(IControllerFactory factory, ILocator viewLocator) {
            this.factory = factory;
            this.viewLocator = viewLocator;
        }
    }
}
