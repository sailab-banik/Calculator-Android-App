package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.input);
        et.setShowSoftInputOnFocus(false);

        tv = findViewById(R.id.output);
    }

    private void updateText(String strToAdd) {
        String oldStr = et.getText().toString();
        int cursorPos = et.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        et.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
        et.setSelection(cursorPos + 1);
    }

    private String calculation() {
        String userExp = et.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        result = result.replace(".0", "");
        result = result.replace("NaN", "");
        return result;
    }

    public void backspaceBTN(View view) {
        int cursorPos = et.getSelectionStart();
        int textLen = et.getText().length();
        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder lastText = (SpannableStringBuilder) et.getText();
            lastText.replace(cursorPos - 1, cursorPos, "");
        }
        tv.setText(calculation());
    }

    public void clearBTN(View view) {
        et.setText("");
        tv.setText("");
    }

    public void bracketBTN(View view) {
        int cursorPos = et.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = et.getText().length();

        for(int i = 0; i < cursorPos; i++) {
            if(et.getText().toString().charAt(i) == '('){
                openPar += 1;
            }
            if(et.getText().toString().charAt(i) == ')'){
                closedPar += 1;
            }
        }

        if(openPar == closedPar || et.getText().toString().charAt(textLen - 1) == '(') {
            updateText("(");
        }
        else if(closedPar < openPar && !(et.getText().toString().charAt(textLen - 1) == '(')) {
            updateText(")");
        }
        et.setSelection(cursorPos + 1);
        tv.setText(calculation());
    }

    public void percentageBTN(View view) {
        updateText("%");
    }

    public void divideBTN(View view) {
        if(et.getText().toString().contains("("))
            updateText("÷");
        else {
            et.setText(calculation());
            et.setSelection(et.getText().toString().length());
            updateText("÷");
        }
    }

    public void sevenBTN(View view) {
        updateText("7");
        tv.setText(calculation());
    }

    public void eightBTN(View view) {
        updateText("8");
        tv.setText(calculation());
    }

    public void nineBTN(View view) {
        updateText("9");
        tv.setText(calculation());
    }

    public void multiBTN(View view) {
        if(et.getText().toString().contains("("))
            updateText("×");
        else {
            et.setText(calculation());
            et.setSelection(et.getText().toString().length());
            updateText("×");
        }
    }

    public void fourBTN(View view) {
        updateText("4");
        tv.setText(calculation());
    }

    public void fiveBTN(View view) {
        updateText("5");
        tv.setText(calculation());
    }

    public void sixBTN(View view) {
        updateText("6");
        tv.setText(calculation());
    }

    public void minusBTN(View view) {
        if(et.getText().toString().contains("("))
            updateText("-");
        else {
            et.setText(calculation());
            et.setSelection(et.getText().toString().length());
            updateText("-");
        }
    }

    public void oneBTN(View view) {
        updateText("1");
        tv.setText(calculation());
    }

    public void twoBTN(View view) {
        updateText("2");
        tv.setText(calculation());
    }

    public void threeBTN(View view) {
        updateText("3");
        tv.setText(calculation());
    }

    public void plusBTN(View view) {
        if(et.getText().toString().contains("("))
            updateText("+");
        else {
            et.setText(calculation());
            et.setSelection(et.getText().toString().length());
            updateText("+");
        }
    }

    public void plusMinusBTN(View view) {
        et.setSelection(0);
        if(et.getText().toString().charAt(0) == '-') {
            SpannableStringBuilder lastText = (SpannableStringBuilder) et.getText();
            lastText.replace(0, 1, "");
        }
        else
            updateText("-");
        et.setSelection(et.getText().toString().length());
        tv.setText(calculation());
    }

    public void zeroBTN(View view) {
        updateText("0");
        tv.setText(calculation());
    }

    public void dotBTN(View view) {
        updateText(".");
        tv.setText(calculation());
    }

    public void equalBTN(View view) {
        tv.setText(calculation());
        et.setText(calculation());
        et.setSelection(et.getText().toString().length());
    }
}