package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonDot, buttonAC, buttonEqual;
    ImageButton btnImgAdd, btnImgDivision, btnImgMultiple, btnImgMinus, btnImgPlusMinus, btnImgPercentage, btnImgBack;
    TextView  tvRecent;
    EditText edtDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.btn0);
        button2 = findViewById(R.id.btn1);
        button1 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonDot = findViewById(R.id.btnDot);
        buttonEqual = findViewById(R.id.btnEqual);
        buttonAC = findViewById(R.id.btnAC);

        btnImgAdd = findViewById(R.id.btn_img_add);
        btnImgPlusMinus = findViewById(R.id.btn_img_plusminus);
        btnImgMinus = findViewById(R.id.btn_img_minus);
        btnImgDivision = findViewById(R.id.btn_img_division);
        btnImgMultiple = findViewById(R.id.btn_img_multiple);
        btnImgPercentage = findViewById(R.id.btn_img_percentage);
        btnImgBack = findViewById(R.id.btn_img_back);

        edtDisplay = findViewById(R.id.edt_display);
        tvRecent = findViewById(R.id.tv_recent);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        btnImgBack.setOnClickListener(this);
        btnImgAdd.setOnClickListener(this);
        btnImgMultiple.setOnClickListener(this);
        btnImgDivision.setOnClickListener(this);
        btnImgMinus.setOnClickListener(this);
        btnImgPercentage.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonAC.setOnClickListener(this);
        buttonDot.setOnClickListener(this);

        edtDisplay.setShowSoftInputOnFocus(false);
    }

    @Override
    public void onClick(View view) {
        String result = edtDisplay.getText().toString().trim();
        switch (view.getId()){
            case R.id.btn0:
                updateText("0");
                break;
            case R.id.btn1:
                updateText("1");
                break;
            case R.id.btn2:
                updateText("2");
                break;
            case R.id.btn3:
                updateText("3");
                break;
            case R.id.btn4:
                updateText("4");
                break;
            case R.id.btn5:
                updateText("5");
                break;
            case R.id.btn6:
                updateText("6");
                break;
            case R.id.btn7:
                updateText("7");
                break;
            case R.id.btn8:
                updateText("8");
                break;
            case R.id.btn9:
                updateText("9");
                break;
            case R.id.btn_img_back:
                if(result.equals("")){
                    edtDisplay.setText("");
                }
                else{
                    int cursorPos = edtDisplay.getSelectionStart();
                    int textLen = edtDisplay.getText().length();

                    if(cursorPos != 0 && textLen !=0){
                        SpannableStringBuilder selection = (SpannableStringBuilder) edtDisplay.getText();
                        selection.replace(cursorPos-1, cursorPos, "");
                        edtDisplay.setText(selection);
                        edtDisplay.setSelection(cursorPos-1);
                    }
                }

                break;
            case R.id.btn_img_add:
                if(result.equals("")){
                    edtDisplay.setText("");
                }else {
                    checkOperator('+');
                }

                break;
            case R.id.btn_img_division:
                if(result.equals("")){
                    edtDisplay.setText("");
                }else {
                    checkOperator(':');
                }
                break;
            case R.id.btn_img_minus:
                if(result.equals("")){
                    edtDisplay.setText("");
                }else {
                    checkOperator('-');
                }
                break;
            case R.id.btn_img_multiple:
                if(result.equals("")){
                    edtDisplay.setText("");
                }else {
                    checkOperator('x');
                }
                break;
            case R.id.btnEqual:
                String userExp = edtDisplay.getText().toString();

                userExp = userExp.replaceAll(":", "/");
                userExp = userExp.replaceAll("x", "*");

                Expression exp = new Expression(userExp);
                String results = String.valueOf(exp.calculate());

                tvRecent.setText(result);
                edtDisplay.setText(results);
                edtDisplay.setSelection(results.length());

                break;
            case R.id.btnAC:
                tvRecent.setText("");
                edtDisplay.setText("");
                break;
            case R.id.btnDot:
                updateText(".");
                break;
            case R.id.btn_img_percentage:
                if(result.equals("")){
                    edtDisplay.setText("");
                }else {
                    checkOperator('%');
                }
                break;
            default:

        }
    }

    void checkOperator(char tail){
        String result = edtDisplay.getText().toString().trim();
        char operator = result.charAt(result.length()-1);
        if (tail != operator){
            updateText(" "+String.valueOf(tail)+" ");
        }
    }

    void updateText(String strToAdd){
        String oldStr = edtDisplay.getText().toString();
        int cursorPos = edtDisplay.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        edtDisplay.setText(String.format("%s%s%s",leftStr, strToAdd, rightStr));
        edtDisplay.setSelection(cursorPos+strToAdd.length());
    }

}