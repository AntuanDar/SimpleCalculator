import common.IFxmlPane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class CalculatorRootPane implements IFxmlPane {
    //Element of JavaFX
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField textField;
    @FXML
    private Button btnZero;
    @FXML
    private Button btnOne;
    @FXML
    private Button btnTwo;
    @FXML
    private Button btnThree;
    @FXML
    private Button btnFour;
    @FXML
    private Button btnFive;
    @FXML
    private Button btnSix;
    @FXML
    private Button btnSeven;
    @FXML
    private Button btnEight;
    @FXML
    private Button btnNine;
    @FXML
    private Button btnPercent;
    @FXML
    private Button btnSub;
    @FXML
    private Button btnMult;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCalc;

    //Flags
    private boolean textFieldIsEmpty = true;
    private boolean isResult = false;
    //Operands
    double firstOperand;
    double secondOperand;

    @FXML
    public void initialize(){
        rootPane.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });

        rootPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (isNumber(event.getText())) {
                    textField.appendText(event.getText());
                }
            }
        });

        textField.setEditable(false);           //Запрещаем ввод текста в textField

        btnAdd.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!textFieldIsEmpty) {
                    firstOperand = Double.parseDouble(textField.getText());
                    textField.appendText(" + ");
                }
            }
        });

        btnSub.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!textFieldIsEmpty) {
                    firstOperand = Double.parseDouble(textField.getText());
                    textField.appendText(" - ");
                }
            }
        });

        btnDiv.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!textFieldIsEmpty) {
                    firstOperand = Double.parseDouble(textField.getText());
                    textField.appendText(" / ");
                }
            }
        });

        btnMult.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!textFieldIsEmpty) {
                    firstOperand = Double.parseDouble(textField.getText());
                    textField.appendText(" * ");
                }
            }
        });

        btnPercent.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!textFieldIsEmpty) {
                    firstOperand = Double.parseDouble(textField.getText());
                    textField.appendText(" % ");
                }
            }
        });

        btnCalc.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!textFieldIsEmpty) {
                    String parsed = textField.getText().split(" ")[2];
                    secondOperand = Double.parseDouble(parsed);
                    double result = firstOperand + secondOperand;
                    textField.setText(String.valueOf(result));
                }
            }
        });

        btnZero.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("0");
            }
        });
        btnOne.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("1");
            }
        });
        btnTwo.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("2");
            }
        });
        btnThree.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("3");
            }
        });
        btnFour.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("4");
            }
        });
        btnFive.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("5");
            }
        });
        btnSix.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("6");
            }
        });
        btnSeven.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("7");
            }
        });
        btnEight.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("8");
            }
        });
        btnNine.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appendNumber("9");
            }
        });
    }

    private boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void appendNumber(String number) {
        if(number.equals("0") && textFieldIsEmpty) {
            //NOP
        } else {
            textField.appendText(number);
            textFieldIsEmpty = false;
        }
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }
}
