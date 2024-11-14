
public class cs201_project1 {
    public static void main(String[] args){

        String number1 = "100000000000000000000";
        String number2 = "20000000";
        Calculator num1 = new Calculator(number1);
        Calculator num2 = new Calculator(number2);
        num1.add(num2);
        num1.subtraction(num2); //For the subtraction method, num1 needs to be bigger than num2.
        num1.multiplication(num2);
        num1.division(num2); //For the division method, num1 is the dividend, num2 is the divisor meaning the result gives num1/num2.

    }
}


