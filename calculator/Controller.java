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

    private double num = 0;
    private int numLeftPar = 0;
    private int numRightPar = 0;
    private Stack<Integer> left = new Stack<>();
    private Stack<Integer> right = new Stack<>();
    private char[] operators = {'+','-','*','/','^',')','('};

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

        int location = old.length();
        left.push(location);

        String add = "(";
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
    }
    public void minus_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = "-";
            text.setText(old + add);
        }

    }
    public void multi_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = "*";
            text.setText(old + add);
        }

    }
    public void div_click(){
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = "/";
            text.setText(old + add);
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
                ans = Double.toString(answer);
            }
            label.setText(ans);
        }
        else {
            label.setText("ERROR");
        }
    }

    public void clear_click(){
        label.setText("");
        String old = text.getText();
        text.setText("");
        numRightPar = 0;
        numLeftPar = 0;
        left.clear();
        right.clear();
    }
    public void point_click(){   //Create way to check for multiple inputs
        label.setText("");
        String old = text.getText();

        if(isLegal(old)){
            String add = ".";
            text.setText(old + add);
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
                old.substring(old.length()-1).equals("(")) {
            return false;
        }
        return true;
    }

    public double solve(String old){

        if(old.charAt(0) == '(' && old.charAt(old.length()-1) == ')') {
            old = old.substring(1, old.length() - 1);
        }

        boolean cont = true;
        double val1 = 0;
        String prev = "";
        String post = "";
        double val2 = 0;
        char op1 = ' ';
        char op2 = ' ';

        int i = 0;
        while(cont || i<old.length()){
            if(old.length() == 1){
                return Double.parseDouble(old);
            }
            else if(prev.equals("") || op1 == ' '){
                if(contains(old.charAt(i)) == false){
                    prev += old.charAt(i);
                }
                else{
                    op1 = old.charAt(i);
                }
            }

            else if(post.equals("")){
                if(contains(old.charAt(i)) == false && i < old.length() -1){
                    post += old.charAt(i);
                }
                else if(i < old.length()-1 && contains(old.charAt(i)) == true){
                    op2 = old.charAt(i);
                    val1 = Double.parseDouble(prev);
                    val2 = Double.parseDouble(post);
                    double ans = operation(val1, op1, val2);
                    double rest = solve(old.substring(i+1, old.length()));
                    return operation(ans, op2, rest);
                }
                else if(i == old.length() -1){
                    post += old.charAt(i);
                    val1 = Double.parseDouble(prev);
                    val2 = Double.parseDouble(post);
                    double ans = operation(val1, op1, val2);
                    return ans;
                }
            }

            else if(old.charAt(i) == '(' && i < old.length()-2){
                left.push(i);
                int countL = 1;
                int countR = 0;
                int j = i + 1;
                while(countL != countR){
                    if(old.charAt(j) == '(') countL++;
                    else if(old.charAt(j) == ')') countR++;
                    else j++;
                }
                String newStr = old.substring(left.pop()+1, j);
                double val = solve(newStr);

            }
            System.out.print(prev + "," + post);
            i++;
        }
        return 0;
    }

    public double operation(double val1, char op, double val2){
        switch(op){
            case('+'):
                return val1+val2;
            case('-'):
                return val1-val2;
            case('*'):
                return val1*val2;
            case('/'):
                return val1/val2;
            case('^'):
                return Math.pow(val1,val2);
            case('('):

                break;
            case(')'):

                break;
            default:
                return 0;
        }
        return 0;
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

