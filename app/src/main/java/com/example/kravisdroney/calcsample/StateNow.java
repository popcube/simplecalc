package com.example.kravisdroney.calcsample;

        import android.view.View;

        import java.math.BigDecimal;


public class StateNow{
    private static State statenow=null;


    public StateNow(){
        statenow=State.Number; statenow.init();
    }



    public void setInNumber(View view){
        statenow=statenow.inNumber(view);
    }

    public void setInDot(View view){
        statenow=statenow.inDot(view);
    }

    public void setInOperator(View view){
        statenow=statenow.inOperator(view);
    }

    public void setInEqual(View view){
        statenow=statenow.inEqual(view);
    }

    public void setInClear(View view){
        statenow=State.Number; statenow.init();
    }

    public String getString(){
        return statenow.getString();
    }

    public BigDecimal number() {
        return statenow.getNumber();
    }

    public BigDecimal result() {
        return statenow.getResult();
    }

    public int decimal() {
        return statenow.getDecimal();
    }

    public int operator() {
        return statenow.getOperator();
    }

    public int digit() {
        return statenow.getDigit();
    }

}
