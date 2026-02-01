package com.alvin.binarycalc.model;

public class BinaryNumber {
    // This class represents a binary number
// It will store and validate binary values
    //private is to prevent modification of method by other classes
// validation is here and private

    private String value;

    public BinaryNumber(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

    private boolean isValidBinary(String value){
        //validation
     if(value.length() == 0) {
         return false;
     }
         for (int i = 0; i < value.length(); i++){
             char currentChar = value.charAt(i);
             if(currentChar != '0' && currentChar != '1'){
                 return false;
             }
     }
         return true;
    }

}