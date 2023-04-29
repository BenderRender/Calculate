package Calculator;

import java.util.Scanner;


public class Main {

    public static void main(String[] args)throws ScannerException{

        Scanner console = new Scanner(System.in);
        CalculatorEngine solution = new CalculatorEngine();

        System.out.println("Input");
        String input = console.nextLine();

        System.out.printf("Output:\n" + solution.calc(input));


    }


}

class CalculatorEngine {

    public static String calc(String input) throws ScannerException {

        String[] stringArray = input.split(" ");

        if(stringArray.length >= 4){
            throw new ScannerException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }else if(stringArray.length <= 2){
            throw new ScannerException("throws Exception //т.к. строка не является математической операцией");
        }

        String sign = stringArray[1];
        String n1 = stringArray[0];
        String n2 = stringArray[2];

        int num1 = 0;
        int num2 = 0;

        String[] dataNum = {"0","1","2","3","4","5","6","7","8","9","10"};

        String[] data = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X",
                "XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX",
                "XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX",
                "XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL",
                "XLI","","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L",
                "LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX",
                "LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX",
                "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX",
                "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
                "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"};


        int arabic = 0;
        int roman = 0;


        for(String element1: dataNum){
            if(n1.equals(element1)){
                num1 = Integer.parseInt(n1);
                arabic++;
                break;
            }else {
                for (String element2 : data){
                    if (n1.equals(element2)) {
                        RomanNumerals romanNum1 = RomanNumerals.valueOf(stringArray[0]);
                        num1 = romanNum1.getNum();
                        roman++;
                        break;
                    }
                }
            }
        }

        for(String element1: dataNum){
            if(n2.equals(element1)){
                num2 = Integer.parseInt(n2);
                arabic++;
                break;
            }else {
                for (String element2 : data) {
                    if (n2.equals(element2)) {
                        RomanNumerals romanNum2 = RomanNumerals.valueOf(stringArray[2]);
                        num2 = romanNum2.getNum();
                        roman++;
                        break;
                    }
                }
            }
        }


        if(arabic == 1 && roman != 0){
            throw new ScannerException("throws Exception //т.к. используются одновременно разные системы счисления");
        }

        if(num1 > 10 || num2 > 10 || num1 == 0 || num2 == 0){
            throw new ScannerException("throws Exception //т.к. калькулятор должен принимать на вход числа от 1 до 10 включительно");
        }


        int result = 0;

        switch(sign){
            case "/":
                result = num1 / num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
        }

        if(arabic == 0 && result < 0){
            throw new ScannerException("throws Exception //т.к. в римской системе нет отрицательных чисел");
        }

        String finishResult;

        if(arabic == 2){
            finishResult = Integer.toString(result);
        }else{
            finishResult = data[result];
        }

        return finishResult;
    }
}

class ScannerException extends Exception{
    public ScannerException(String description){
        super(description);
    }
}