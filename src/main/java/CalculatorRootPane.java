import common.IFxmlPane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.math.BigDecimal;


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


    @FXML
    public void initialize(){
        textField.setEditable(false);           //Запрещаем ввод текста в textField
        textField.setContextMenu(new ContextMenu());

        rootPane.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });

        rootPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().isDigitKey()) {
                    appendNumber(event.getText());
                } else if (event.getCode() == KeyCode.SUBTRACT || event.getCode() == KeyCode.MULTIPLY
                        || event.getCode() == KeyCode.DIVIDE || event.getCode() == KeyCode.ADD) {
                    appendOperator(event.getText());
                } else if (event.getCode() == KeyCode.DECIMAL){
                    appendDot();
                } else if (event.getCode() == KeyCode.ENTER) {
                    calculate();
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    deleteText();
                }
            }
        });

        initButton(btnC, event -> getClear());

        initButton(btnRemove, event -> deleteText());

        initButton(btnDot, event -> appendDot());

        initButton(btnAdd, event -> appendOperator("+"));

        initButton(btnSub, event -> appendOperator("-"));

        initButton(btnDiv, event -> appendOperator("/"));

        initButton(btnMult, event -> appendOperator("*"));

        initButton(btnPercent, event -> appendOperator("%"));

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

    private boolean firstZero() {
        if (textField.getText().length() > 0) {
            return textField.getText().charAt(0) == '0';
        }
        return false;
    }

    private void deleteText() {
        String operand = getLastString();
        if ((operand != null && !operand.isEmpty()) && !operand.matches("[ +\\-*\\/% ]{3}")) {
            textField.deleteText(textField.getText().length() - 1, textField.getText().length());
        }
    }
    // Метод возвращает строку из последних трёх символов, если длина строки больше 3
    private String getLastString() {
        String operand = "";
        if (textField.getText().length() >= 3) {
            operand = textField.getText().substring(textField.getText().length() - 3);
        }
        return operand;
    }
    // Метод очищает текстфилд
    private void getClear() {
        textField.clear();
        isResult = false;
        textFieldIsEmpty = true;
        firstZero = false;
    }
    // Метод проверяет условия и добавляет оператор к текстфилду
    private void appendOperator(String s) {
        String operand = getLastString();
        if (operand.matches("[ +\\-*\\/% ]{3}")) {
            textField.deleteText(textField.getText().length() - 3, textField.getText().length());
        }
        if (textField.getText().split(" ").length > 2) {
            String[] arrayOfOperands = textField.getText().split(" ");
            calculateFromString(arrayOfOperands);
        }
        if (!textFieldIsEmpty()) {
            textField.appendText(" " + s + " ");
            textFieldIsEmpty = true;
            firstZero = false;
        }
    }

    // Метод парсит строку, вычисляет результат и выводит в текстфилд
    private void calculateFromString(String[] arrayOfOperands) {

        BigDecimal first = new BigDecimal(arrayOfOperands[0]);
        BigDecimal second = new BigDecimal(arrayOfOperands[2]);
        BigDecimal result = null;

        if (arrayOfOperands[1].equals("+")) {
            result = first.add(second);
        } else if (arrayOfOperands[1].equals("-")) {
            result = first.subtract(second);
        } else if (arrayOfOperands[1].equals("*")) {
            result = first.multiply(second);
        } else if (arrayOfOperands[1].equals("/")) {
            if (second.doubleValue() == 0) {
                textField.setText("На ноль делить нельзя, мудило");
                return;
            }
            result = first.divide(second);
        } else if (arrayOfOperands[1].equals("%")) {

            result = first.multiply(second).divide(new BigDecimal(100));

        }
        textField.setText(String.valueOf(result));
        isResult = true;
    }

    // Проверяет пустой ли текстфилд
    private boolean textFieldIsEmpty() {
        return textField.getText().length() < 1;
    }

    private void calculate() {
        if (textField.getText().split(" ").length > 2) {
            String[] arrayOfOperands = textField.getText().split(" ");
            calculateFromString(arrayOfOperands);
        }
    }

    private void initButton(Button button, EventHandler<MouseEvent> handler) {
        button.addEventFilter(MouseEvent.MOUSE_PRESSED, handler);
    }

    // Метод проверяет условия и добавляет цифру к текстфилду
    private void appendNumber(String number) {
        if (operandLength() < 15) {
            if (isResult && !lastStringOperator()) {
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
                isResult = false;
            }
        }
    }

    private int operandLength() {
        String[] array = textField.getText().split(" ");
        if (array.length > 2) {
            return array[2].length();
        } else if (array.length == 1){
            return array[0].length();
        } else {
            return -1;
        }
    }

    private boolean lastStringOperator() {
        String str = getLastString().trim();
        return str.matches("[*\\/\\-+%]");
    }

    // Метод проверяет условия и добавляет ноль к текстфилду
    private void appendZero() {
        if (textFieldIsEmpty) {
            textField.appendText("0");
            textFieldIsEmpty = false;
            firstZero = true;
        } else if (isDotIntroduced()) {
            textField.appendText("0");
            firstZero = false;
        } else if (!firstZero()) {
            textField.appendText("0");
        }
    }

    private boolean isDotIntroduced() {
        String[] array = textField.getText().split(" ");
        if (array.length > 2) {
            return array[2].contains(".");
        } else {
            return textField.getText().contains(".");
        }
    }
    // Метод проверяет условия и добавляет десятичную точку к текстфилду
    private void appendDot() {
        if (textFieldIsEmpty) {
            textField.appendText("0.");
            textFieldIsEmpty = false;
        } else if (!isDotIntroduced()){
            textField.appendText(".");
            firstZero = false;
        }
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }
}
