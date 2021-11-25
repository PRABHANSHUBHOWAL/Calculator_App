package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.math.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    CardView zero,one,two,three,four,five,six,seven,eight,nine,add,div,sub,mul,back,point,equal,clear;
    TextView operation,result;
    String input="";
    ArrayList<String>operands=new ArrayList<String>();
    double finalresult=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Defining the hooks
        defineHooks();
        //setListener
        setListener();

        result.setVisibility(View.GONE);
        operation.setText("0");
    }

    private void setListener() {
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        clear.setOnClickListener(this);
        back.setOnClickListener(this);
        mul.setOnClickListener(this);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
        div.setOnClickListener(this);
        equal.setOnClickListener(this);
        point.setOnClickListener(this);

    }

    private void defineHooks() {
        zero=findViewById(R.id.zero);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        clear=findViewById(R.id.AC);
        back=findViewById(R.id.back);
        mul=findViewById(R.id.multiply);
        sub=findViewById(R.id.minus);
        add=findViewById(R.id.add);
        div=findViewById(R.id.divide);
        equal=findViewById(R.id.equals);
        point=findViewById(R.id.point);
        operation=findViewById(R.id.operation);
        result=findViewById(R.id.result);
    }

    @Override
    public void onClick(View view) {
        if(view==zero){
            operation("0");
        }
        else if(view==one){
            operation("1");
        }
        else if(view==two){
            operation("2");
        }
        else if(view==three){
            operation("3");
        }
        else if(view==four){
            operation("4");
        }
        else if(view==five){
            operation("5");
        }
        else if(view==six){
            operation("6");
        }
        else if(view==seven){
            operation("7");
        }
        else if(view==eight){
            operation("8");
        }
        else if(view==nine){
            operation("9");
        }
        else if(view==point){
            operation(".");
        }
        else if(view==add){
            operation(" + ");
        }
        else if(view==sub){
            operation(" - ");
        }
        else if(view==mul){
            operation(" x ");
        }
        else if(view==div){
            operation(" % ");
        }
        else if(view==clear){
            operation("AC");
        }
        else if(view==back){
            operation("<-");
        }
        else if(view==equal){
            operation("result");
        }

    }

    private void operation(String no)
    {
        Vibrator vibe=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        if(no.equals("AC"))
        {
            input="";
            finalresult=0;
            result.setText(String.valueOf(finalresult));
        }
        else if(no.equals("<-"))
        {
            if(!input.equals(""))
            {
                Character last=input.charAt(input.length()-1);
                if (last.equals(' '))
                {
                    input = input.substring(0, input.length()-2);
                }
                input = input.substring(0, input.length() - 1);
            }
        }
        else if(no.equals("result"))
        {
            getResult();
        }
        else
        {
            boolean isTwiceOperator=false;
            String prev=""; Character prev_pt=' ';
            if (input.length() > 1)
            {
              prev=input.substring(input.length()-2,input.length()-1);
              prev_pt=input.charAt(input.length()-1);
            }
            if ((prev.equals("+")||prev.equals("-")||prev.equals("%")||prev.equals("x")||prev_pt.equals('.'))&&(no.equals(" + ")||no.equals(" - ")||no.equals(" % ")||no.equals(" x ")||no.equals(".")))
            {
                isTwiceOperator=true;
            }
            if (input.equals("")&&(no.equals(" + ")||no.equals(" - ")||no.equals(" x ")||no.equals(" % ")))
            {
                isTwiceOperator=true;
            }
            if (!isTwiceOperator)
            {
                input=input+no;
            }
        }

        operation.setText(input);
    }

    private void getResult()
    {
        result.setVisibility(View.VISIBLE);
        operands.clear();
        String dup=input;
        dup+=" ";
        Character ch,ch1;
        String value="";
        int len=dup.length();
        for (int i=0;i<len;i++)
        {
            ch=dup.charAt(i);

            if (ch.equals(' '))
            {
                value=value.trim();
                operands.add(value);
                value="";
            }
            value=value+ch;
        }
        getAns("%");
        getAns("x");
        getAns("+");
        getAns("-");

        finalresult=Double.parseDouble(operands.get(0));

        result.setText(String.valueOf(finalresult));
        input="";
    }

    private void getAns(String operator)
    {
        int len=operands.size();
        for(int j=0;j<len*len;j++)
        {

            for (int i = 0; i < len; i++)
            {
                if (operands.get(i).equals(operator))
                {
                    if (operands.get(i).equals("%")) {
                        finalresult = Double.parseDouble(operands.get(i - 1)) / Double.parseDouble(operands.get(i + 1));
                    } else if (operands.get(i).equals("x")) {
                        finalresult = Double.parseDouble(operands.get(i - 1)) * Double.parseDouble(operands.get(i + 1));
                    } else if (operands.get(i).equals("+")) {
                        finalresult = Double.parseDouble(operands.get(i - 1)) + Double.parseDouble(operands.get(i + 1));
                    } else if (operands.get(i).equals("-")) {
                        finalresult = Double.parseDouble(operands.get(i - 1)) - Double.parseDouble(operands.get(i + 1));
                    }
                    operands.remove(i-1);
                    operands.add(i-1,String.valueOf(finalresult));
                    operands.remove(i+1);
                    operands.remove(i );
                    len = len - 2;
                }
            }
        }
    }
}