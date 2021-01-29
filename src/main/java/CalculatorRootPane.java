import common.IFxmlPane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class CalculatorRootPane implements IFxmlPane {

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize(){
        rootPane.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }
}
