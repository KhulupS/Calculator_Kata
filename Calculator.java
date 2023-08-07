import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение (арабскими или римскими числами): ");
        String expression = scanner.nextLine();
        System.out.println("Результат: " + calc(expression));
    }

    public static String calc(String input) throws Exception {
        Converter converter = new Converter();
        int num1;
        int num2;
        String operation;
        String result;
        boolean isRoman;
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2)
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        operation = operatorSearch(input);
        if (operation == null) throw new Exception("т.к. строка не является математической операцией");
        if (converter.isRoman(operands[0]) && converter.isRoman(operands[1])) {
            num1 = converter.romanToInt(operands[0]);
            num2 = converter.romanToInt(operands[1]);
            isRoman = true;
        } else if (!converter.isRoman(operands[0]) && !converter.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        } else {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = expressionExecution(num1, num2, operation);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
            result = converter.intToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static int expressionExecution(int a, int b, String operation) {

        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                return a / b;
        }
    }

    static String operatorSearch(String expression) {
        if (expression.contains("+")) {
            return "+";
        } else if (expression.contains("-")) {
            return "-";
        } else if (expression.contains("*")) {
            return "*";
        } else if (expression.contains("/")) {
            return "/";
        } else {
            return null;
        }
    }
}


