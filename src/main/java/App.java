import common.AppStyles;
import common.FxmlPaneLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class App extends Application {

    private CalculatorRootPane calculatorRootPane;

    public void start(Stage stage) throws Exception {
        calculatorRootPane = FxmlPaneLoader.loadPane(CalculatorRootPane.class);
        Scene scene = new Scene(calculatorRootPane.getRootPane(), 450, 450);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        setStylesheets(stage.getScene());
        stage.setMinWidth(450.0);
        stage.setMinHeight(450.0);
        stage.show();
    }

    private void setStylesheets(Scene scene) {
        scene.getStylesheets().clear();
        scene.getStylesheets().addAll(AppStyles.getStylesheet("styles.css"));
    }
}
