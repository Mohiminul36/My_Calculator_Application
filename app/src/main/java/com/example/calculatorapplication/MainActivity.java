package com.example.calculatorapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultView, solutionView;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEqual;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultView = findViewById(R.id.result_view);
        solutionView = findViewById(R.id.solution_view);
        assignId(buttonC, R.id.button_C);
        assignId(buttonBrackOpen, R.id.button_open_bracket);
        assignId(buttonBrackClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonEqual, R.id.button_equal);
        assignId(button0, R.id.button_zero);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonDot, R.id.button_AC);
        assignId(buttonAC, R.id.button_dot);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

    MaterialButton button = (MaterialButton) view;
    String buttonText = button.getText().toString();
    String dataToCalculate = solutionView.getText().toString();

    if(buttonText.equals("AC"))

    {

        solutionView.setText("");
        resultView.setText("0");
        return;
    }

    if(buttonText.equals("="))

    {

        solutionView.setText(resultView.getText());
        return;
    }
    if(buttonText.equals("C"))

    {
        dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
    }
else

    {
        dataToCalculate = dataToCalculate + buttonText;
    }


    solutionView.setText(dataToCalculate);
    String getfinalResult = getfinalResult(dataToCalculate);

if(!getfinalResult.equals("Error"))

    {
        resultView.setText(getfinalResult);
    }
}
        String getfinalResult(String data) {
            try  {
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scope = context.initStandardObjects();

                String finalResult = context.evaluateString(scope, data, "JavaScript", 1, null).toString();
                if(finalResult.endsWith(".0")){
                    finalResult = finalResult.replace(".0","");
                }
                return finalResult;
            } catch (Exception e) {
                return "Invalid expression"; // More specific error message
            }
        }

    }
