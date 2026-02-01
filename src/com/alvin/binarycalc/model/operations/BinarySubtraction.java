package com.alvin.binarycalc.model.operations;

import com.alvin.binarycalc.model.BinaryNumber;
import com.alvin.binarycalc.model.BinaryOperation;

public class BinarySubtraction implements BinaryOperation {
    public BinaryNumber subtract(BinaryNumber first, BinaryNumber second){
        // 1. Get the binary string values from both BinaryNumber objects
        // 2. Start subtraction from the rightmost bit of each string
        // 3. Use a borrow to handle 0-1
        // 4. Subtract corresponding bits one by one
        // 5. Build the result binary string
        // 6. Return the result as a new BinaryNumber object

        String firstValue = first.getValue();
        String secondValue = second.getValue();

        int i = firstValue.length() - 1;
        int j = secondValue.length() - 1;
        int borrow = 0;

        StringBuilder result = new StringBuilder();

        while(i >= 0 || j >= 0) {
            int bit1 = (i >= 0) ? firstValue.charAt(i) - '0' : 0;
            int bit2 = (j >= 0) ? secondValue.charAt(j) - '0' : 0;

            int diff = bit1 - bit2 - borrow;

            if (diff < 0) {
                diff += 2;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.append(diff);
            i--;
            j--;
        }

        if(borrow == 1) {
            throw new IllegalArgumentException("Subtraction will result in a negative value");
        }

        result.reverse();
        return new BinaryNumber(result.toString());
        }

    @Override
    public BinaryNumber execute(BinaryNumber first, BinaryNumber second) {
        return subtract(first, second);
    }
}


