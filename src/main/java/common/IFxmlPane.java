package common;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public interface IFxmlPane {

    Object CONTROLLER_KEY = new Object();

    Pane getRootPane();

    static IFxmlPane getController(Node rootPane) {
        return (IFxmlPane) rootPane.getProperties().get(CONTROLLER_KEY);
    }

    static void setController(Node rootPane, IFxmlPane controller) {
        rootPane.getProperties().put(CONTROLLER_KEY, controller);
    }

    default void requestFocus() {

    }

    default void setVisible(boolean visible) {
        getRootPane().setVisible(visible);
    }

}
