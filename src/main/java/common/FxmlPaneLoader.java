package common;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Objects;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class FxmlPaneLoader {

    public static final PrintStream DUMMY_STREAM = new PrintStream(new ByteArrayOutputStream());
    private static FxmlPaneLoader instance = new FxmlPaneLoader();

    public static <T extends IFxmlPane> T loadPane(Class<T> controllerType) {
        return instance.load(controllerType, null);
    }

    public static <T extends IFxmlPane> T loadPane(Class<T> controllerType, T controller) {
        return instance.load(controllerType, controller);
    }

    private <T extends IFxmlPane> T load(Class<T> controllerType, T controller) {
        FXMLLoader loader = new FXMLLoader();
        if (controller != null)
            loader.setControllerFactory(param -> controller);
        String resourcePath = '/' + controllerType.getName().replace('.', '/') + ".fxml";
        URL url = Objects.requireNonNull(controllerType.getResource(resourcePath), "Resource not found: " + resourcePath);
        loader.setLocation(url);

        PrintStream err = System.err;
        System.setErr(DUMMY_STREAM);
        try {
            Pane rootPane = loader.load();
            IFxmlPane.setController(rootPane, loader.getController());
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.setErr(err);
        }
    }

}
