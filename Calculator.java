import List.LinkedList;
import List.Node;

public class Calculator extends cs201_project1{

    //I will implement all the properties of the class "calculator".
    //Here will be the variables I will use to implement the calculator

    //Some things in this class can change. These are just plans right now.
    int number;
    int length;
    char[] number_array;
    LinkedList list ;

    public Calculator(){
        //This is going to be my basic constructor. I may not use it because I will probably use the constructor
        //with the parameters but it won't hurt to have it.
        list = new LinkedList();
    }
    public Calculator(LinkedList list){
        this.list = list;
    }

    public Calculator(String x){
        //This is the constructor with the parameter. I will use this constructor to create the object at first.
        length = x.length();
        number_array = new char[length];
        list = new LinkedList();
        for(int i = 0; i < length ; i++){
            number_array[i] = x.charAt(i);
            number = Integer.parseInt(String.valueOf((number_array[i])));
            Node node = new Node(number);
            list.insertFirst(node);
        }
    }

    public void getNumber(){
        System.out.println(list.toString());
    }
    public void printNumber(){
        int a = this.getNumberElements();
        LinkedList l = new LinkedList();
        for(int i = 0; i < a ; i++){
            Node node = list.getNodeI(i);
            Node node1 = new Node(node.getData());
            l.insertFirst(node1);
        }
        System.out.println(l.toString());
    }
    public LinkedList printNumberLinkedList(){
        int a = this.getNumberElements();
        LinkedList l = new LinkedList();
        for(int i = 0; i < a ; i++){
            Node node = list.getNodeI(i);
            Node node1 = new Node(node.getData());
            l.insertFirst(node1);
        }
        return l;
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public int getNumberElements() {
        return list.numberOfElements();
    }
    public int getData(int i){

        return (this.list.getNodeI(i)).getData();
    }
    public void insertLast(int i){
        Node node = new Node(i);
        list.insertLast(node);
    }
    public void deleteLast(){
        list.deleteLast();
    }
    public void printList(LinkedList[ ] listA){
        for(int i = 0; i < listA.length ; i++){
            System.out.println(listA[i]);
        }
    }
    public boolean allZero(){
        int a = 0;
        while(this.getData(a) == 0){
            a++;
            if(a == this.getNumberElements()){
                return true;
            }
        }
        return false;
    }

    public void add(Calculator x){  //In this method I completed the add function.
        Calculator z = x;
        Calculator k = this;
        Calculator m = new Calculator();
        int carry = 0;
        int max_length = 0;
        int copy = 0;
        while (z.getNumberElements() < k.getNumberElements()) {  //In these while loops both of the numbers are transformed into the same length.
            z.insertLast(0);
        }
        while (k.getNumberElements() < z.getNumberElements()) {
            k.insertLast(0);
        }
        max_length = z.getNumberElements();
        //z.getNumber();
        //k.getNumber();
        for(int j = 0; j < max_length ; j++){   //In this for loop we do the main adding. It is straightforward.
            copy = z.getData(j) + k.getData(j) + carry;
            if(copy>=10){
                carry = 1;
                copy = copy % 10;
            } else{
                carry = 0;
            }
            m.insertLast(copy);
            copy=0;
        }
        if(carry != 0){   //With this if statement, we insert our remaining carry into the number in order for it to be complete.
            m.insertLast(carry);
        }
        System.out.print("Addition    = ");
        m.printNumber();
    }
    public void subtraction(Calculator x){
        Calculator z = this;
        Calculator k = x;
        Calculator m = new Calculator();
        int borrow = 0;
        int max_length = 0;
        while (z.getNumberElements() < k.getNumberElements()) {  //In these while loops both of the numbers are transformed into the same length.
            z.insertLast(0);
        }
        while (k.getNumberElements() < z.getNumberElements()) {
            k.insertLast(0);
        }
        max_length = z.getNumberElements();
        //z.getNumber();
        //k.getNumber();
        for(int j = 0; j < max_length ; j++){   //In this for loop we do the main subtracting. It is straightforward.
            int diff = z.getData(j) - k.getData(j) - borrow;
            if(diff < 0){
                diff += 10;
                borrow = 1;
            }else{
                borrow = 0;
            }
            m.insertLast(diff);
        }
        if(m.allZero()) { //This if/else statement is to get rid of the unnecessary zeros.
            Calculator calculatorC = new Calculator("0"); //Because we need to check if the result is zero or not.
            m = calculatorC;
        }else {
            int c = 0;   //In this last part, we remove the unnecessary zeros from the number.
            c = m.getNumberElements() - 1;
            while (m.getData(c) == 0) {
                m.deleteLast();
                c--;
            }
        }
        System.out.print("Subtraction = ");
        m.printNumber();
    }
    public LinkedList multiplication1Digit(Calculator x,int a) { //In this method we multiply a 1-digit number with a bigger number(no digit limit).
        Calculator z = x;
        Calculator k = this;
        Calculator m = new Calculator();
        int carry = 0;
        int product = 0;
        int i = a;
        for (int j = 0; j < k.getNumberElements(); j++) {
            product = z.getData(i) * k.getData(j) + carry;
            carry = product / 10;
            product = product % 10;
            m.insertLast(product);
        }
        if(carry != 0){     //With this if statement, we insert our remaining carry into the number in order for it to be complete.
            m.insertLast(carry);
        }
        //m.getNumber();
        return m.list;
    }

    public void multiplication(Calculator x){ //This is the main multiplication function.
        Calculator z = x;
        Calculator k = this;
        int max_length;
        while (z.getNumberElements() < k.getNumberElements()) {    //In these while loops both of the numbers are transformed into the same length.
            z.insertLast(0);
        }
        while (k.getNumberElements() < z.getNumberElements()) {
            k.insertLast(0);
        }
        if(z.getNumberElements() < k.getNumberElements()) {  //With this if/else statement we get our max_length so we can create our LinkedList of LinkedLists.
            max_length = k.getNumberElements();
        } else {
            max_length = z.getNumberElements();
        }
        LinkedList[ ]  listoflist = new LinkedList[max_length];
        for(int i = 0; i < k.getNumberElements(); i++) { //We call the 1 digit multiplication here. We iterate this and write the results into the LinkedList.
            listoflist[i] = k.multiplication1Digit(z,i);
        }
        for(int i = 0; i < listoflist.length; i++){ //In this for loop, we shift the numbers to the left by the required amount.
            for(int j = 0; j < i; j++) {
                Node node = new Node(0);
                listoflist[i].insertFirst(node);
            }
        }
        //printList(listoflist);
        int o = 0;
        Calculator calculatorA = new Calculator(listoflist[o]);
        Calculator calculatorB = new Calculator();
        while(o < listoflist.length - 1){
            Calculator calculatorTemp = new Calculator(listoflist[o+1]); //We add all the numbers together.
            calculatorB = calculatorA.addMultiply(calculatorTemp);
            calculatorA = calculatorB;
            o++;
        }
        if(calculatorB.allZero()) { //This if/else statement is to get rid of the unnecessary zeros..
            Calculator calculatorC = new Calculator("0"); //Because we need to check if the result is zero or not.
            calculatorB = calculatorC;
        }else {
            int c = 0; //In this last part, we remove the unnecessary zeros from the number.
            c = calculatorB.getNumberElements() - 1;
            while (calculatorB.getData(c) == 0) {
                calculatorB.deleteLast();
                c--;
            }
        }
        System.out.print("Multiplication = ");
        calculatorB.printNumber();

    }
    public Calculator addMultiply(Calculator x){ //This is a modified version of the add method.
        Calculator z = x;                        //The only difference is that this one returns a Calculator object.
        Calculator k = this;
        Calculator m = new Calculator();
        int carry = 0;
        int max_length = 0;
        int copy = 0;
        while (z.getNumberElements() < k.getNumberElements()) {
            z.insertLast(0);
        }
        while (k.getNumberElements() < z.getNumberElements()) {
            k.insertLast(0);
        }
        max_length = z.getNumberElements();
        //z.getNumber();
        //k.getNumber();
        for(int j = 0; j < max_length ; j++){
            copy = z.getData(j) + k.getData(j) + carry;
            if(copy>=10){
                carry = 1;
                copy = copy % 10;
            } else{
                carry = 0;
            }
            m.insertLast(copy);
            copy=0;
        }
        if(carry != 0){
            m.insertLast(carry);
        }
        return m;
    }

    public void division(Calculator x){//This is my division method.
        Calculator z = new Calculator(this.printNumberLinkedList()); //This part is different from the other methods because for the other methods
        Calculator p = x;                                            //it was easier to represent the numbers in reverse ex: 512 was represented as 215
        Calculator k = new Calculator(p.printNumberLinkedList());    //but this time the normal version was better so i represented them like it is 512 => 512
        Calculator quotient = new Calculator();

        if (k.getNumberElements() == 1 && k.getData(0) == 0) {      //This part gives an error whenever it detects that the number is getting divided by 0
            System.out.println("Zero Division Error!");
            return;
        }
        int dividend = 0;
        int divisor = 0;

        for (int i = 0; i < k.getNumberElements(); i++) {               //I assigned the short number to an integer because it is easier to calculate like this
            divisor = divisor * 10 + k.getData(i);
        }

        for (int i = 0; i < z.getNumberElements(); i++) {               //I also assigned the number to an integer but part by part so this was I didn't exceed the integer limit.
            dividend = dividend * 10 + z.getData(i);

            int result = dividend / divisor;
            if (!(result == 0 && quotient.isEmpty())) {                 //This helps to get rid of the unnecessary 0's in the answer
                quotient.insertLast(result);
            }

            dividend = dividend % divisor;                              //With this part, I ensure that the method is repeatable. So with this part we get the remainder and write it
        }                                                               //on top of the dividend.
        System.out.print("Division = ");
        quotient.getNumber();

    }

}