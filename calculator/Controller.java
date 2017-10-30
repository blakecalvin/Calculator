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
    private Label label;

    private long num;
    private int numLeftPar = 0;
    private int numRightPar = 0;
    private Stack<Integer> left;
    private Stack<Integer> right;

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
            String answer = solve(old);
            label.setText(answer);
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

    public boolean isLegal(String old){
        if(old.substring(old.length()-1).equals("+") ||
                old.substring(old.length()-1).equals("-") ||
                old.substring(old.length()-1).equals("*") ||
                old.substring(old.length()-1).equals("/") ||
                old.substring(old.length()-1).equals(".") ||
                old.substring(old.length()-1).equals("(")) {
            return false;
        }
        return true;
    }

    public String solve(String old){
        if(old.charAt(0) == '(' && old.charAt(old.length()-1) == ')') {
            old = old.substring(1, old.length() - 1);
        }

        String prev;
        String post;
        char op;

        for(int i = 0; i<old.length(); i++){
            if(old.charAt(i) == '('){
                left.push(i);
            }
            else if(old.charAt(i) == ')'){
                String newStr = old.substring(left.pop()+1, i);
                String val = solve(newStr);
            }
        }

        return old;
    }

}

