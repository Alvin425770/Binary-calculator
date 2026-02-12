package com.alvin.binarycalc.model.operations;

import com.alvin.binarycalc.model.BinaryNumber;
import com.alvin.binarycalc.model.BinaryOperation;


public class BinaryDivision implements BinaryOperation {
    /* Take the Dividend (numerator) and Divisor (denominator).
    Look at the bits of the dividend from left to right.
    If the current chunk is >= the divisor, put a 1 in the quotient and subtract the divisor.
    If it's smaller, put a 0 in the quotient.
    Bring down the next bit and repeat.
     */
    @Override
    public BinaryNumber execute(BinaryNumber dividend, BinaryNumber divisor) {
        // 1. validation
        if (isZero(divisor.getValue())) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        // 2. Prepare strings
        String dividendStr = dividend.getValue();
        String divisorStr = divisor.getValue();

        //3. result builder
        StringBuilder quotient = new StringBuilder();
        String remainder = "0";

        BinaryOperation subtractor = new BinarySubtraction();

        //long division loop
        for (int i = 0; i < dividendStr.length(); i++) {
            // Bring down the next bit
            remainder = remainder + dividendStr.charAt(i);
            remainder = stripLeadingZeros(remainder);

            // Check if we can subtract (remainder >= divisor)
            if (isGreaterThanOrEqual(remainder, divisorStr)) {
                quotient.append("1");

                // We create temporary BinaryNumber objects to pass to the subtracto
                BinaryNumber remBN = new BinaryNumber(remainder);

                // execute returns a BinaryNumber, so we get the value back as a String
                BinaryNumber resultBN = subtractor.execute(remBN, divisor);
                remainder = resultBN.getValue();
            } else {
                quotient.append("0");
            }


        }

        return new BinaryNumber(stripLeadingZeros(quotient.toString()));
    }
    // Keep these helpers as they are specific to the Division algorithm flow
    private boolean isZero(String binary) {
        return binary.replace("0", "").isEmpty();
    }

    private String stripLeadingZeros(String binary) {
        String result = binary.replaceFirst("^0+(?!$)", "");
        return result.isEmpty() ? "0" : result;
    }

    private boolean isGreaterThanOrEqual(String num1, String num2) {
        num1 = stripLeadingZeros(num1);
        num2 = stripLeadingZeros(num2);
        if (num1.length() > num2.length()) return true;
        if (num1.length() < num2.length()) return false;
        return num1.compareTo(num2) >= 0;
    }
}


