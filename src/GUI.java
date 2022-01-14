import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI represents the GUI interface of a calculator.
 *
 * @author Nikolay Rozanov
 * @version 2022
 */
public class GUI implements ActionListener {
    private final JButton[] arrayOfButtons;
    private final JTextField resultField;
    private static JTextArea stepsField;
    private final int FONT_SIZE = 15;

    /**
     * Constructs a GUI interface.
     *
     */
    public GUI() {
        // Font properties.
        final Font textFont = new Font("Dialog", Font.BOLD, FONT_SIZE);

        // Result text field properties.
        JTextField resultField = new JTextField();
        resultField.setBounds(50, 25, 440, 50);
        resultField.setBackground(Color.white);
        resultField.setFont(textFont);
        resultField.setEditable(false);
        this.resultField = resultField;

        // Result text field properties.
        JTextArea stepsField = new JTextArea();
        stepsField.setBounds(530, 25, 150, 380);
        stepsField.setBackground(Color.white);
        stepsField.setFont(textFont);
        stepsField.setEditable(false);
        GUI.stepsField = stepsField;

        // Buttons properties.
        JButton buttonForOne = new JButton("1");
        JButton buttonForTwo = new JButton("2");
        JButton buttonForThree = new JButton("3");
        JButton buttonForFour = new JButton("4");
        JButton buttonForFive = new JButton("5");
        JButton buttonForSix = new JButton("6");
        JButton buttonForSeven = new JButton("7");
        JButton buttonForEight = new JButton("8");
        JButton buttonForNine = new JButton("9");
        JButton buttonForZero = new JButton("0");
        JButton buttonForPlus = new JButton(" + ");
        JButton buttonForMinus = new JButton(" - ");
        JButton buttonForDivide = new JButton(" / ");
        JButton buttonForMultiply = new JButton(" * ");
        JButton buttonForEquals = new JButton("=");
        JButton buttonForDelete = new JButton("DEL");
        JButton buttonForClear = new JButton("CLR");
        JButton buttonForDot = new JButton(".");
        JButton buttonForPower = new JButton(" ^ ");
        JButton buttonForLog = new JButton(" ln ");
        JButton buttonForLn = new JButton(" log10 ");
        JButton buttonForRoot = new JButton(" √ ");
        JButton buttonForPi = new JButton("π");
        JButton buttonForLeftParenthesis = new JButton("( ");
        JButton buttonForRightParenthesis = new JButton(" )");
        JButton[] arrayOfButtons = {
                buttonForPower, buttonForLeftParenthesis, buttonForRightParenthesis, buttonForDelete, buttonForClear,
                buttonForLog, buttonForOne, buttonForTwo, buttonForThree, buttonForPlus,
                buttonForLn, buttonForFour, buttonForFive, buttonForSix, buttonForMinus,
                buttonForRoot, buttonForSeven, buttonForEight, buttonForNine, buttonForDivide,
                buttonForPi, buttonForZero, buttonForDot, buttonForEquals, buttonForMultiply
        };
        this.arrayOfButtons = arrayOfButtons;
        applyActionListener(arrayOfButtons);
        applyFont(arrayOfButtons, textFont);

        // Panel properties.
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 440, 300);
        panel.setLayout(new GridLayout(5, 5, 10, 10));

        // Frame properties.
        JFrame frame = new JFrame("Basic Calculator");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(resultField);
        frame.add(stepsField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(740, 470);
        frame.setTitle("Calculator");
        frame.setLayout(null);
        frame.setVisible(true);
        addButtonsToPanel(arrayOfButtons, panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
         for (JButton button : arrayOfButtons) {
             // Calculate the expression.
             if (button.getText().equals("=") && event.getSource() == button) {
                 String result = BasicMath.calculateExpression(resultField.getText());
                 resultField.setText(result);
             }
             // Display the pi.
             else if (button.getText().equals("π") && event.getSource() == button) {
                 resultField.setText(resultField.getText().concat(String.valueOf(Math.PI)));
             }
             // Delete the last character.
             else if (button.getText().equals("DEL") && event.getSource() == button) {
                 resultField.setText(resultField.getText().substring(0, resultField.getText().length() - 1));
             }
             // Clear the result and steps text fields.
             else if (button.getText().equals("CLR") && event.getSource() == button) {
                 resultField.setText("");
                 stepsField.setText("");
             }
             else if (event.getSource() == button) {
                 // Add the value of the pressed button to the result text field.
                 resultField.setText(resultField.getText().concat(String.valueOf(button.getText())));
             }
         }
    }

    public static void addSteps(String firstOperand, String operator, String secondOperand, String result) {
        // Display the calculation step.
        if (operator.equals("log10") || operator.equals("ln")) {
            stepsField.setText(
                    stepsField.getText() + operator + "(" + secondOperand +") = " + result + "\n"
            );
        }
        else {
            stepsField.setText(
                    stepsField.getText() + firstOperand + " " + operator + " " + secondOperand + " = " + result + "\n"
            );
        }
    }

    private void applyFont(JButton[] arrayOfButtons, Font font) {
        // Apply font to every button in the array.
        for (JButton button : arrayOfButtons){
            button.setFont(font);
            button.setFocusable(false);
        }
    }

    private void applyActionListener(JButton[] arrayOfButtons) {
        // Add action listener to every button in the array.
        for (JButton button : arrayOfButtons){
            button.addActionListener(this);
        }
    }

    private void addButtonsToPanel(JButton[] arrayOfButtons, JPanel panel) {
        // Add every button in the array to the given panel.
        for (JButton button : arrayOfButtons){
            panel.add(button);
        }
    }
}
