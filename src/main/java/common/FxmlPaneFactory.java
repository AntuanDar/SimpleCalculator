package common;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class FxmlPaneFactory<T extends IFxmlPane> extends ObjectFactory<T> {

    public FxmlPaneFactory(Class<T> paneType) {
        super(() -> FxmlPaneLoader.loadPane(paneType));
    }

    public static <T extends IFxmlPane> T loadPane(Class<T> controllerType) {
        return FxmlPaneLoader.loadPane(controllerType, null);
    }

    public static <T extends IFxmlPane> T loadPane(Class<T> controllerType, T controller) {
        return FxmlPaneLoader.loadPane(controllerType, controller);
    }

}
