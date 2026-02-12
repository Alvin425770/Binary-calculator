package com.alvin.binarycalc.view;

import com.alvin.binarycalc.model.BinaryNumber;
import com.alvin.binarycalc.model.BinaryOperation;
import com.alvin.binarycalc.model.operations.BinaryAddition;
import com.alvin.binarycalc.model.operations.BinaryDivision;
import com.alvin.binarycalc.model.operations.BinarySubtraction;
import com.alvin.binarycalc.model.operations.BinaryMultiplication;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI extends JFrame {
    // This class handles the graphical user interface
// It displays buttons, text fields, and results

    private JTextField firstField;
    private JTextField secondField;

    private JLabel resultLabel;

    public CalculatorUI(){
        setTitle("Calculator");//title
        setSize(400, 300);//width x height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close app
        setLayout(new FlowLayout());



        JLabel firstLabel = new JLabel("First Binary:");
        firstField = new JTextField(10);

        JLabel secondLabel = new JLabel("Second Binary");
        secondField = new JTextField(10);

        add(firstLabel);
        add(firstField);
        add(secondLabel);
        add(secondField);

        JButton subtractButton = new JButton(" - ");
        add(subtractButton);

        JButton addButton = new JButton(" + ");
        add(addButton);

        JButton multiplyButton = new JButton(" x ");
        add(multiplyButton);

        JButton divideButton = new JButton(" / ");
        add(divideButton);

        resultLabel = new JLabel("Result:");
        add(resultLabel);


        subtractButton.addActionListener(new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent e) {
                                                 performOperation(new BinarySubtraction());
                                             }
                                         });

        addButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            performOperation(new BinaryAddition());
                                        }
                                    });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(new BinaryMultiplication());

            }
        });

        divideButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                performOperation(new BinaryDivision());
            }
        });


        setVisible(true);//window appear

    }
    private void performOperation(BinaryOperation operation){
        String firstInput = firstField.getText();
        String secondInput = secondField.getText();

        try {
            BinaryNumber a = new BinaryNumber(firstInput);
            BinaryNumber b = new BinaryNumber(secondInput);

            BinaryNumber result = operation.execute(a, b);

            resultLabel.setText("Result: " + result.getValue());
        }
        catch (IllegalArgumentException ex){
            //handles invalid binary input
            resultLabel.setText("Result: ");

            JOptionPane.showMessageDialog(
                    CalculatorUI.this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

        catch (ArithmeticException ex){
            //handles divide by zero
            resultLabel.setText("Result: Error");
            JOptionPane.showMessageDialog(
                    this,
                    "Cannot divide by zero!",
                    "Math Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    public static void main(String[] args){
        new CalculatorUI();


    }

}
