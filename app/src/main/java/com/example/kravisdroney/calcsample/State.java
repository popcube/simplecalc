package com.example.kravisdroney.calcsample;
import android.view.View;

import android.widget.Button;

import java.math.BigDecimal;
import java.lang.Math;



public enum State implements ActionSet {

    Number{
        @Override public State inNumber(View view){
            Button button=(Button) view;
            if(digit>=15){
                return this;
            }
            if(decimal==-1) {
                number=number.multiply(bten);
                BigDecimal badd = new BigDecimal(button.getText().toString());
                number=number.add(badd);
            }else{
                decimal++;
                BigDecimal bsub = new BigDecimal(button.getText().toString());
                for(int i=0;i<decimal;i++) bsub=bsub.divide(bten);
                number=number.add(bsub);
            }
            digit++;
            return this;
        }
        @Override public State inOperator(View view){
            if(operator!=0) equal(view);
            operator = view.getId();
            result = number;
            return Operator;
        }

        @Override public State inEqual(View view){
            if(operator!=0) equal(view);
            return Equal;
        }
        @Override public State inDot(View view){
            if(decimal==-1) {
                decimal = 0;
                digit++;
                if(digit==1){digit=2;}
                return Dot;
            }else{
                return this;
            }
        }

    },

    Operator{
        @Override public State inNumber(View view){
            Button button=(Button) view;
            number=new BigDecimal(button.getText().toString());
            decimal = -1;
            digit=1;
            return Number;
        }
        @Override public State inOperator(View view){
            operator = view.getId(); return this;
        }
        @Override public State inEqual(View view){
            operator = 0; return Equal;
        }
        @Override public State inDot(View view){
            operator =0;
            if(decimal==-1) {
                decimal = 0;
                return Dot;
            }else {
                return Number;
            }
        }
    },

    Dot{
        @Override public State inNumber(View view){
            Button button=(Button) view;
            decimal++;
            BigDecimal bsub = new BigDecimal(button.getText().toString());
            for(int i=0;i<decimal;i++) bsub=bsub.divide(bten);
            number=number.add(bsub);
            digit++;
            return Number;
        }
        @Override public State inOperator(View view){
            if(decimal==0){
                decimal=-1;
            }
            if(operator!=0) equal(view);
            operator = view.getId();
            result = number;
            return Operator;

        }
        @Override public State inEqual(View view){
            if(decimal==0){
                decimal=-1;
            }
            if(operator!=0) equal(view);
            return Equal;
        }
        @Override public State inDot(View view){
            return this;
        }
    },

    Equal{
        @Override public State inNumber(View view){
            Button button=(Button) view;
            init();
            number=new BigDecimal(button.getText().toString());
            return Number;
        }
        @Override public State inOperator(View view){
            operator = view.getId();
            result = number;
            return Operator;
        }
        @Override public State inEqual(View view){
            return this;
        }
        @Override public State inDot(View view){
            init();
            decimal=0;
            digit=2;
            return Dot;
        }
    },

    ;

    private static BigDecimal number, result;
    private static int decimal, operator, digit,digitprev;
    private static final BigDecimal bten = new BigDecimal("10");
    private static final BigDecimal bzero = new BigDecimal("0");
    private static final BigDecimal bmax = new BigDecimal("1.e+15");
    private static final BigDecimal bmin = new BigDecimal("-1.e+15");


    public String getString(){
       return this.toString();
}

    private static void equal(View view){

        System.out.println("test A");

        if (number.compareTo(bzero)==0&&operator==R.id.buttonDivide){
            number=result=bmax;
            decimal=-1;
            digit=16;
            return;
        }

        System.out.println("test B");

        switch(operator){
            case R.id.buttonPlus: result=result.add(number); break;
            case R.id.buttonMinus: result=result.subtract(number); break;
            case R.id.buttonMultiply: result=result.multiply(number); break;
            case R.id.buttonDivide: result=result.divide(number,16,BigDecimal.ROUND_HALF_UP); break;

            default: assert false; break;
        }

        System.out.println("test C");

        if(result.compareTo(bmax)!=-1 || result.compareTo(bmin)!=1){
            number=result=bmax;
            decimal=-1;
            digit=16;
            return;
        }else {
            String tempres = result.toPlainString();
            System.out.println(tempres);
            tempres = tempres.substring(0, Math.min(15,tempres.length()));
            System.out.println(tempres);
            result = new BigDecimal(tempres);
        }

        System.out.println("test D");

        number=result;
        decimal=-1;
        digit=15;

    }

    public static void init(){
        number=bzero;
        result=bzero;
        decimal=-1;
        operator=0;
        digit=0;
        digitprev=0;
    }

    public static BigDecimal getNumber() {
        return number;
    }

    public static BigDecimal getResult() {
        return result;
    }

    public static int getDecimal() {
        return decimal;
    }

    public static int getOperator() {
        return operator;
    }

    public static int getDigit() {
        return digit;
    }
}
