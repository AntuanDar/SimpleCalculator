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
    @FXML
    private Button btnDot;
    @FXML
    private Button btnC;
    @FXML
    private Button btnRemove;

    //Flags
    private boolean textFieldIsEmpty = true;
    private boolean isResult = false;
    private boolean firstZero = false;
    private boolean dotIntroduced = false;
    //Operands
    double firstOperand;
    double secondOperand;

    @FXML
    public void initialize(){
        textField.setEditable(false);           //Запрещаем ввод текста в textField

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

        initButton(btnC, event -> getClear());

        initButton(btnRemove, event -> textField.deleteText(textField.getText().length() -1, textField.getText().length()));

        initButton(btnDot, event -> appendDot());

        initButton(btnAdd, event -> {
            if (!textFieldIsEmpty) {
                appendOperator("+");
            }
        });

        initButton(btnSub, event -> {
            if (!textFieldIsEmpty) {
                appendOperator("-");
            }
        });

        initButton(btnDiv, event -> {
            if (!textFieldIsEmpty) {
                appendOperator("/");
            }
        });

        initButton(btnMult, event -> {
            if (!textFieldIsEmpty) {
                appendOperator("*");
            }
        });

        initButton(btnPercent, event -> {
            if (!textFieldIsEmpty) {
                appendOperator("%");
            }
        });

        initButton(btnCalc, event -> {
            if (!textFieldIsEmpty) {
                calculate();
            }
        });

        initButton(btnZero, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("0");
        });
        initButton(btnOne, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("1");
        });
        initButton(btnTwo, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("2");
        });
        initButton(btnThree, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("3");
        });
        initButton(btnFour, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("4");
        });
        initButton(btnFive, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("5");
        });
        initButton(btnSix, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("6");
        });
        initButton(btnSeven, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("7");
        });
        initButton(btnEight, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("8");
        });
        initButton(btnNine, event -> {
            if (event.isPrimaryButtonDown())
                appendNumber("9");
        });

    }

    private void getClear() {
        textField.clear();
        isResult = false;
        textFieldIsEmpty = true;
        dotIntroduced = false;
        firstZero = false;
    }

    private void appendOperator(String s) {
        //Заготовка под парсинг целой и дробной части по отдельности
        String[] firstOperands;
        if (textField.getText().contains(".")) {
            firstOperands = textField.getText().split("\\.");
        }

        firstOperand = Double.parseDouble(textField.getText());
        textField.appendText(" " + s + " ");
        textFieldIsEmpty = true;
        firstZero = false;
        dotIntroduced = false;
    }

    private void calculate() {
        String[] parsedArray = textField.getText().split(" ");
        secondOperand = Double.parseDouble(parsedArray[2]);
        double result = 0;
        switch (parsedArray[1]) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "/":
                result = firstOperand / secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "%":
                result = (firstOperand * secondOperand) / 100;
                break;
        }

        textField.setText(String.valueOf(result));
        isResult = true;
    }

    private void initButton(Button button, EventHandler<MouseEvent> handler) {
        button.addEventFilter(MouseEvent.MOUSE_PRESSED, handler);
    }

    private boolean isNumber(String text) {
        return text.matches("-?[0-9]+");
    }

    private void appendNumber(String number) {
        if (isResult) {
            getClear();
        }
        if(number.equals("0")) {
            appendZero();
        } else {
            if (firstZero) {
                textField.deleteText(textField.getText().length() - 1, textField.getText().length());
                firstZero = false;
                textFieldIsEmpty = true;
            }
            textField.appendText(number);
            textFieldIsEmpty = false;
            firstZero = false;
        }
    }
    private void appendZero() {
        if (textFieldIsEmpty) {
            textField.appendText("0");
            textFieldIsEmpty = false;
            firstZero = true;
        } else if (dotIntroduced) {
            textField.appendText("0");
            firstZero = false;
        } else if (!firstZero) {
            textField.appendText("0");
        }
    }

    private void appendDot() {
        if (textFieldIsEmpty) {
            textField.appendText("0.");
            textFieldIsEmpty = false;
            dotIntroduced = true;
        } else if (!dotIntroduced){
            textField.appendText(".");
            dotIntroduced = true;
            firstZero = false;
        }
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }
}
