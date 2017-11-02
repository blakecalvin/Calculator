package calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Stack;

public class Controller {

    @FXML
    private TextField text;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button eight;

    @FXML
    private Button seven;

    @FXML
    private Button nine;

    @FXML
    private Button zero;

    @FXML
    private Button point;

    @FXML
    private Button leftPar;

    @FXML
    private Button rightPar;

    @FXML
    private Button plus;

    @FXML
    private Button minus;

    @FXML
    private Button multi;

    @FXML
    private Button div;

    @FXML
    private Button equal;

    @FXML
    private Button one;

    @FXML
    private Button clear;

    @FXML
    private Button delete;

    @FXML
    private Button pow;

    @FXML
    private Label label;

    private int numLeftPar = 0;
    private int numRightPar = 0;

    private char[] operators = {'+','-','*','/','^','(',')'};

    protected int roundOff = 3;

    @FXML
    public void initialize() {
        text.setPromptText("0");
        text.setEditable(false);
    }

    public void one_click(){
        label.setText("");
        String old = text.getText();
        String add = "1";
        text.setText(old + add);
    }
    public void two_click(){
        label.setText("");
        String old = text.getText();
        String add = "2";
        text.setText(old + add);
    }
    public void three_click(){
        label.setText("");
        String old = text.getText();
        String add = "3";
        text.setText(old + add);
    }
    public void four_click(){
        label.setText("");
        String old = text.getText();
        String add = "4";
        text.setText(old + add);
    }
    public void five_click(){
        label.setText("");
        String old = text.getText();
        String add = "5";
        text.setText(old + add);
    }
    public void six_click(){
        label.setText("");
        String old = text.getText();
        String add = "6";
        text.setText(old + add);
    }
    public void seven_click(){
        label.setText("");
        String old = text.getText();
        String add = "7";
        text.setText(old + add);
    }
    public void eight_click(){
        label.setText("");
        String old = text.getText();
        String add = "8";
        text.setText(old + add);
    }
    public void nine_click(){
        label.setText("");
        String old = text.getText();
        String add = "9";
        text.setText(old + add);
    }
    public void zero_click(){
        label.setText("");
        String old = text.getText();
        String add = "0";
        text.setText(old + add);
    }
    public void leftPar_click(){
        label.setText("");
        String old = text.getText();
        String add;
        if(old.length() > 0 && old.charAt(old.length()-1) == ')'){
            add = "*(";
        }
        else{
            add = "(";
        }
        text.setText(old + add);
        numLeftPar++;
    }
    public void rightPar_click() {
        label.setText("");
        String old = text.getText();

        if(old.length() > 0 && old.contains("(") && isLegal(old) && numRightPar < numLeftPar){
            String add = ")";
            text.setText(old + add);
            numRightPar++;
        }
        else {
            label.setText("ERROR");
        }
    }
    public void plus_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = "+";
            text.setText(old + add);
        }
        else {
            label.setText("ERROR");
        }
    }
    public void minus_click(){
        label.setText("");
        String old = text.getText();

        if(isMinusLegal(old)){
            String add = "-";
            text.setText(old + add);
        }
        else {
            label.setText("ERROR");
        }

    }
    public void multi_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = "*";
            text.setText(old + add);
        }
        else {
            label.setText("ERROR");
        }

    }
    public void div_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = "/";
            text.setText(old + add);
        }
        else {
            label.setText("ERROR");
        }

    }
    public void equal_click(){
        if(numRightPar == numLeftPar){
            String old = text.getText();
            double answer = solve(old);
            String ans = "";
            if(answer % 1 == 0){
                int a = (int)answer;
                ans = Integer.toString(a);
            }
            else{
                ans = String.format("%."+roundOff+"f", answer);
            }
            label.setText(ans);
        }
        else {
            label.setText("ERROR");
        }
    }

    public void clear_click(){
        label.setText("");
        text.setText("");
        numRightPar = 0;
        numLeftPar = 0;
    }
    public void point_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old) && checkPoint(old) == false){
            String add = ".";
            text.setText(old + add);
        }
        else {
            label.setText("ERROR");
        }
    }
    public void delete_click(){
        label.setText("");
        String old = text.getText();

        if(old.length() > 0){
            if(old.charAt(old.length()-1)=='('){
                numLeftPar--;
            }
            else if(old.charAt(old.length()-1)==')'){
                numRightPar--;
            }
            text.setText(old.substring(0,old.length()-1));
        }
    }
    public void pow_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = "^";
            text.setText(old + add);
        }
        else {
            label.setText("ERROR");
        }
    }

    public boolean isLegal(String old){
        if(old.length() == 0){
            return false;
        }
        else if(old.substring(old.length()-1).equals("+") ||
                old.substring(old.length()-1).equals("-") ||
                old.substring(old.length()-1).equals("*") ||
                old.substring(old.length()-1).equals("/") ||
                old.substring(old.length()-1).equals(".") ||
                old.substring(old.length()-1).equals("(") ||
                old.substring(old.length()-1).equals("^")) {
            return false;
        }
        return true;
    }

    public boolean isMinusLegal(String old){
        if(old.length() == 0){
            return true;
        }
        else if(old.substring(old.length()-1).equals("+") ||
                old.substring(old.length()-1).equals("-") ||
                old.substring(old.length()-1).equals("*") ||
                old.substring(old.length()-1).equals(".")) {
            return false;
        }
        return true;
    }

    public boolean checkPoint(String old){
        int i = old.length()-1;
        boolean point = false;
        boolean op = false;
        while(i>0 && point == false && op == false){
            if(old.charAt(i) == '.'){
                return true;
            }
            else if(contains(old.charAt(i))){
                return false;
            }
            i--;
        }
        return false;
    }

    public double solve(String old){

        old = implicitMulti(old);
        System.out.println(old);

        char[] tokens = old.toCharArray();
        Stack<Double> vals = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++){

            System.out.println("i: " + i);

            if ((tokens[i] >= '0' && tokens[i] <= '9')|| tokens[i] == '.'
                    || (tokens[i] == '-' && i == 0)
                    || (tokens[i] == '-' && i>0
                    && (tokens[i-1] == '(' || tokens[i-1] == '/' || tokens[i-1] == '^'))){

                StringBuffer s = new StringBuffer();

                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9')||tokens[i] == '.'
                        || (tokens[i] == '-' && i == 0)
                        || (tokens[i] == '-' && i>0
                        && (tokens[i-1] == '(' || tokens[i-1] == '/' || tokens[i-1] == '^')))){

                    s.append(tokens[i++]);
                }

                vals.push(Double.parseDouble(s.toString()));
                i--;
            }

            else if (tokens[i] == '('){
                ops.push(tokens[i]);
            }

            else if (tokens[i] == ')'){
                while (ops.peek() != '(')
                    vals.push(operation(ops.pop(), vals.pop(), vals.pop()));
                ops.pop();
            }

            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '^'){

                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    vals.push(operation(ops.pop(), vals.pop(), vals.pop()));

                ops.push(tokens[i]);
            }
            System.out.println("vals: " + vals.toString());
            System.out.println("ops: " + ops.toString());
        }

        while (!ops.empty())
            vals.push(operation(ops.pop(), vals.pop(), vals.pop()));

        return vals.pop();
    }

    public String implicitMulti(String old){
        for(int i = 1; i < old.length()-1; i++){
            System.out.println("i:"+i+" s:" + old);
            String one;
            String two;
            String three;

            if(old.charAt(i) == '(' && contains(old.charAt(i-1)) == false){
                System.out.println(2);
                one = old.substring(0,i);
                two = "*(";
                three = old.substring(i+1,old.length());
                old = one+two+three;
            }
            else if(old.charAt(i) == ')' && contains(old.charAt(i+1)) == false){
                one = old.substring(0,i);
                two = ")*";
                three = old.substring(i+1,old.length());
                old = one+two+three;
            }
        }
        return old;
    }

    public boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/' || op1 == '^') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public double operation( char op, double val2, double val1){
        switch(op){
            case('+'):
                return val1+val2;
            case('-'):
                return val1-val2;
            case('*'):
                return val1*val2;
            case('/'):
                if(val2 == 0){
                    label.setText("ERROR");
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return val1/val2;
            case('^'):
                return Math.pow(val1,val2);
            default:
                return 0;
        }
    }

    public boolean contains(char one){
        for(char x: operators){
            if(one == x){
                return true;
            }
        }
        return false;
    }

}
