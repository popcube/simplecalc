package com.example.kravisdroney.calcsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    StateNow current= new StateNow();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show();
    }


    public void show(){
        System.out.println(current.number().toPlainString()+':'+Integer.toString(current.decimal())+':'+Integer.toString(current.digit()));
        TextView resultView = (TextView) findViewById(R.id.result_view);
        if(current.digit()>15){
            resultView.setText("Error");
            return;
        }
//        if(Math.abs(current.number())>=1.e+15){
//            resultView.setText("Error");
//            return;
//        }
        if(current.decimal()==0) {
            resultView.setText(current.number().setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString().replaceAll(",","")+'.');
        }else if(current.number().compareTo(current.number().setScale(0, BigDecimal.ROUND_HALF_UP))==0&&current.decimal()==-1) {
            resultView.setText(current.number().toPlainString().replaceAll(",",""));
        }else{
            DecimalFormat df= new DecimalFormat();
            if(current.decimal()!=-1) {
                df.setMaximumIntegerDigits(current.digit()-current.decimal());
                df.setMaximumFractionDigits(current.decimal());
                resultView.setText(df.format(current.number()).replaceAll(",",""));

         //       String format = "%" + Integer.toString(current.digit()) + "."+Integer.toString(current.decimal())+"lf";
         //       resultView.setText(String.format(format, current.number()));
         //       resultView.setText(String.format("%d%d", current.digit(), current.decimal()));
            }else{
         //       if(Double.toString(current.number()).length()<15){
         //           resultView.setText(String.format("%.15s", String.format("%15f",current.number())));
          //      }else {

                df.setMaximumIntegerDigits(current.digit());
                resultView.setText(current.number().toPlainString().replaceAll(",",""));
         //           String format = "%" + Integer.toString(current.digit()) + "lf";
         //           resultView.setText(String.format("%.15s",String.format(format, current.number())));
          //      }
           //     resultView.setText(String.format("%s%d", BigDecimal.valueOf(current.number()).toPlainString(), current.decimal()));
           //     resultView.setText(String.format("%s%d", Double.toString((current.number())), current.decimal()));
            }
        }
    }

    public void onClickNumber(View view) {
        current.setInNumber(view);
        show();
    }

    public void onClickDot(View view) {
        current.setInDot(view);
        show();
    }

    public void onClickOperator(View view) {
        current.setInOperator(view);
        show();
    }

    public void onClickEqual(View view) {
        current.setInEqual(view);
        show();
    }

    public void onClickClear(View view) {
        current.setInClear(view);
        show();
    }







}
