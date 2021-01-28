import common.IFxmlPane;
import javafx.fxml.FXML;
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

    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }
}
