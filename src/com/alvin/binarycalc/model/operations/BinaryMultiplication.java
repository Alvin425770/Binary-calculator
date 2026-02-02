package com.alvin.binarycalc.model.operations;

import com.alvin.binarycalc.model.BinaryNumber;
import com.alvin.binarycalc.model.BinaryOperation;

public class BinaryMultiplication implements BinaryOperation {
    // 1. Get binary string values
// 2. Start from the rightmost bit of the multiplier
// 3. For each '1', shift multiplicand left
// 4. Add shifted values using BinaryAddition
// 5. Return result as BinaryNumber
    @Override
    public BinaryNumber execute(BinaryNumber first, BinaryNumber second){
        String firstValue = first.getValue();
        String secondValue = second.getValue();

        BinaryNumber result = new BinaryNumber("0");
        for(int i = secondValue.length() - 1; i >= 0; i--){
            if (secondValue.charAt(i) == '1'){

                StringBuilder shifted = new StringBuilder(firstValue);
                int shiftAmount = secondValue.length() - 1 - i;

                for(int j = 0; j < shiftAmount; j++ ){
                    shifted.append("0");
                }

                BinaryAddition addition = new BinaryAddition();
                result = addition.execute(result, new BinaryNumber(shifted.toString()));
            }
        }

        return result;
    }

}
