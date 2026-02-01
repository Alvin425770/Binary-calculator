package com.alvin.binarycalc.model.operations;

import com.alvin.binarycalc.model.BinaryNumber;
import com.alvin.binarycalc.model.BinaryOperation;

public class BinaryAddition implements BinaryOperation {
    public BinaryNumber add(BinaryNumber first, BinaryNumber second){
        // 1. Get the binary string values from both BinaryNumber objects
        // 2. Start addition from the rightmost bit of each string
        // 3. Use a carry to handle sums greater than 1
        // 4. Add corresponding bits one by one
        // 5. Build the result binary string
        // 6. Return the result as a new BinaryNumber object

        String firstValue = first.getValue();
        String secondValue = second.getValue();

        int i = firstValue.length() - 1;
        int j = secondValue.length() - 1;
        int carry = 0;
// -1 POINTS TO THE MOST RIGHTMOST BIT
        StringBuilder result = new StringBuilder();
        // STRING Builder allows efficient modification of strings inside loops

        while (i >= 0 || j >= 0 || carry == 1) {
           int bit1 = (i >= 0) ? firstValue.charAt(i) - '0' : 0;
           int bit2 = (j >= 0) ? secondValue.charAt(j) - '0' : 0;
           //missing bits considered as 0 to accomodate numbers of different lenghts

            int sum = bit1 + bit2 + carry;
            int resultBit = sum % 2;
            carry = sum / 2;

            result.append(resultBit);

            i--;
            j--;

        }
        result.reverse();
        return new BinaryNumber(result.toString());



    }

    @Override
    public BinaryNumber execute(BinaryNumber first, BinaryNumber second) {
        return add(first, second);
    }
}
