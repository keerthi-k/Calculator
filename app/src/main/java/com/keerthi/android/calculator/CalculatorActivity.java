/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/***
 * Author: Keerthi Kaliraman
 */


package com.keerthi.android.calculator;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends ActionBarActivity {
    private static final int ADD = 1;
    private static final int SUB = 2;
    private static final int MUL = 3;
    private static final int DIV = 4;
    private static final int MAXDIGITS = 9;

    private ButtonClickListener mButtonClickListener;
    private EditText mOperandA;
    private EditText mOperandB;
    private double mNumberA;
    private double mNumberB;
    private double mResult;
    private double mNumber;
    private int mOperator;
    private boolean mReadyForOperand2;
    private boolean mDecimal;
    private int mDecimalDigitCount;
    private int mDigitCount;
    private Button mClearButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        mButtonClickListener = new ButtonClickListener((TextView)findViewById(R.id.calc_display));

        findViewById(R.id.zero_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.one_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.two_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.three_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.four_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.five_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.six_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.seven_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.eight_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.nine_button).setOnClickListener(mButtonClickListener);

        mClearButton = (Button)findViewById(R.id.allclear_button);
        mClearButton.setOnClickListener(mButtonClickListener);

        findViewById(R.id.plus_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.equalto_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.minus_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.multiplication_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.division_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.sign_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.dot_button).setOnClickListener(mButtonClickListener);
        findViewById(R.id.percentage_button).setOnClickListener(mButtonClickListener);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/




    class ButtonClickListener implements View.OnClickListener{
        private TextView mResultView;

        ButtonClickListener(@NonNull TextView resultView) {
            mResultView = resultView;
        }

        private void resetOperand() {
            mNumber = 0;
            mDecimal = false;
            mDecimalDigitCount = 0;
            mDigitCount = 0;
        }



        private void updateResultView(int num){
            if (mDigitCount >= MAXDIGITS){
                return;
            }
            if (mNumber== 0 &&  num == 0){
                mResultView.setText(Long.toString((long)mNumber));
                return;
            }
            mDigitCount += 1;

            mClearButton.setText(R.string.clear_button);

            if (mDecimal){
                mDecimalDigitCount += 1;
                mNumber = mNumber  + num / Math.pow(10, mDecimalDigitCount);
                mResultView.setText(Double.toString(mNumber));
            } else {
                mNumber = (mNumber * 10) + num;
                mResultView.setText(Long.toString((long)mNumber));
            }

        }
        private void updateNumberA(){
            mNumberA = mNumber;
            resetOperand();

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.zero_button:
                    updateResultView(0);
                    break;
                case R.id.one_button:
                    updateResultView(1);
                    break;
                case R.id.two_button:
                    updateResultView(2);
                    break;
                case R.id.three_button:
                    updateResultView(3);
                    break;
                case R.id.four_button:
                    updateResultView(4);
                    break;
                case R.id.five_button:
                    updateResultView(5);
                    break;
                case R.id.six_button:
                    updateResultView(6);
                    break;
                case R.id.seven_button:
                    updateResultView(7);
                    break;
                case R.id.eight_button:
                    updateResultView(8);
                    break;
                case R.id.nine_button:
                    updateResultView(9);
                    break;
                case R.id.allclear_button:
                    String clearButtonText = mClearButton.getText().toString();
                    if ("AC".equals(clearButtonText)){
                        mOperator = 0;
                        mNumberA = 0;
                        mNumberB = 0;
                    } else {
                        mClearButton.setText(R.string.allclear_button);
                    }
                    resetOperand();
                    updateResultView(0);

                    break;
                case R.id.sign_button:
                    mNumber = mNumber * -1;
                    mResultView.setText(Double.toString(mNumber));
                    break;
                case R.id.percentage_button:
                    mNumber = mNumber / 100;
                    mResultView.setText(Double.toString(mNumber));
                    break;

                case R.id.dot_button:
                    if (!mDecimal){
                        mDecimal = true;
                        mResultView.append(getString(R.string.dot_button));
                    }
                    break;
                case R.id.plus_button:
                    updateNumberA();
                    mOperator = ADD;
                    break;
                case R.id.minus_button:
                    updateNumberA();
                    mOperator = SUB;
                    break;
                case R.id.multiplication_button:
                    updateNumberA();
                    mOperator = MUL;
                    break;
                case R.id.division_button:
                    updateNumberA();
                    mOperator = DIV;
                    break;
                case R.id.equalto_button:
                    mNumberB = mNumber;
                    resetOperand();
                    switch (mOperator){
                        case ADD:
                            mResult = mNumberA + mNumberB;
                            break;
                        case SUB:
                            mResult = mNumberA - mNumberB;
                            break;
                        case MUL:
                            mResult = mNumberA * mNumberB;
                            break;
                        case DIV:
                            mResult = mNumberA / mNumberB;
                            break;
                        default:
                            mResult = mNumberB;
                    }

                    mNumberA = 0;
                    mNumberB = 0;
                    if(mResult % 1 != 0){
                        mResultView.setText(Double.toString(mResult));
                    } else {
                        mResultView.setText(Long.toString((long)mResult));
                    }

                    break;

            }
        }


    }


}
