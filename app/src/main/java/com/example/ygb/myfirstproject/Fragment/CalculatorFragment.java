package com.example.ygb.myfirstproject.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygb.myfirstproject.Activity.MainActivity;
import com.example.ygb.myfirstproject.R;

/**
 * Created by YuanBuyuan on 2017/10/26 0026.
 */

public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnCu,btnChen,btnAnd,btnJ,btnD,btnC,btnDel,btnPoint;
    private TextView tv_showInfo,tv_result;
    private String showIn="",showIn2="",str_p="1234567890.";
    private String num_Str;
    private boolean isClear=true,canPoint=true,hPoint=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_calculator,null);

        btn1=view.findViewById(R.id.btn_1);
        btn2=view.findViewById(R.id.btn_2);
        btn3=view.findViewById(R.id.btn_3);
        btn4=view.findViewById(R.id.btn_4);
        btn5=view.findViewById(R.id.btn_5);
        btn6=view.findViewById(R.id.btn_6);
        btn7=view.findViewById(R.id.btn_7);
        btn8=view.findViewById(R.id.btn_8);
        btn9=view.findViewById(R.id.btn_9);
        btn0=view.findViewById(R.id.btn_0);
        btnCu=view.findViewById(R.id.btn_chuFa);
        btnChen=view.findViewById(R.id.btn_chenFa);
        btnAnd=view.findViewById(R.id.btn_jiaF);
        btnJ=view.findViewById(R.id.btn_jianFa);
        btnD=view.findViewById(R.id.btn_dengYu);
        btnC=view.findViewById(R.id.btn_c);
        btnDel=view.findViewById(R.id.btn_huiShan);
        btnPoint=view.findViewById(R.id.btn_point);
        tv_showInfo=view.findViewById(R.id.tx_showInfo);
        tv_result= view.findViewById(R.id.tx_result);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnCu.setOnClickListener(this);
        btnChen.setOnClickListener(this);
        btnAnd.setOnClickListener(this);
        btnJ.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
    }

    public double calculate(String str){
        if(str.lastIndexOf("+")!=-1||str.lastIndexOf("-")!=-1)
        {
            if(str.lastIndexOf("+")-str.lastIndexOf("-")>0){
                int i=str.lastIndexOf("+");
                return  (str.substring(0,i).equals("")? 0:calculate(str.substring(0, i)))
                        +calculate(str.substring(i+1, str.length()));
            }
            else
            {
                int i=str.lastIndexOf("-");
                return  (str.substring(0,i).equals("")? 0:calculate(str.substring(0, i)))
                        -calculate(str.substring(i+1, str.length()));
            }
        }

        if(str.lastIndexOf("*")!=-1||str.lastIndexOf("/")!=-1)
        {
            int i1=str.lastIndexOf("*");
            int i2=str.lastIndexOf("/");
            if( i1>i2)
            {

                return  calculate(str.substring(0, str.lastIndexOf("*")))
                        *calculate(str.substring(str.lastIndexOf("*")+1, str.length()));
            }
            else if( i2>i1)
            {
                return calculate(str.substring(0, str.lastIndexOf("/")))
                        / calculate(str.substring(str.lastIndexOf("/") + 1, str.length()));
            }
        }
        return Double.parseDouble(str);
    }

    public void res(){
        try {
            int isInt=(int)calculate(showIn2);
            String isStr=calculate(showIn2)+"";
            if (isInt==calculate(showIn2)&&!hPoint){
                showIn=isInt+"";
                showIn2=isInt+"";
                tv_showInfo.setText(showIn);
            }
            else if (isStr.equals("Infinity")){
                showIn="";
                showIn2="";
                tv_showInfo.setText(showIn);
                tv_result.setText("除数不能为0");
                isClear=true;
            }
            else{
                showIn=calculate(showIn2)+"";
                showIn2=calculate(showIn2)+"";
                tv_showInfo.setText(showIn);

            }
        }catch (Exception e){
            tv_result.setText("错误");
        }

    }

    public void lastChar(){
        int i=str_p.lastIndexOf(showIn.charAt(showIn.length()-1));
        if (i==-1){
            showIn=showIn.substring(0,showIn.length()-1);
            showIn2=showIn2.substring(0,showIn2.length()-1);
        }
    }
    @Override
    public void onClick(View view) {
       /* Toast.makeText(getActivity(),"test",Toast.LENGTH_LONG).show();*/
        Button btn= (Button) view;
        String info=btn.getText()+"";
        switch (info)
        {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                isClear=false;
                num_Str=btn.getText().toString();
                showIn=showIn+num_Str;
                showIn2=showIn2+num_Str;
                tv_showInfo.setText(showIn);
                tv_result.setText("");
                break;
            case ".":
                if (!isClear&&canPoint){
                    num_Str=btn.getText().toString();
                    showIn=showIn+num_Str;
                    showIn2=showIn2+num_Str;
                    tv_showInfo.setText(showIn);
                    isClear=false;
                    canPoint=false;
                }else {
                    tv_result.setText("错误");
                }
                break;
            case "+":
                if (!isClear)
                {
                    lastChar();
                    num_Str=btn.getText().toString();
                    showIn=showIn+num_Str;
                    showIn2=showIn2+"+";
                    tv_showInfo.setText(showIn);
                    isClear=false;
                    canPoint=true;
                }else {
                    tv_result.setText("第一位不能为符号");
                }
                break;
            case "-":
                if (!isClear)
                {
                    lastChar();
                    num_Str=btn.getText().toString();
                    showIn=showIn+num_Str;
                    showIn2=showIn2+"-";
                    tv_showInfo.setText(showIn);
                    isClear=false;
                    canPoint=true;
                }else {
                    tv_result.setText("第一位不能为符号");
                }
                break;
            case "×":
                if (!isClear)
                {
                    lastChar();
                    num_Str=btn.getText().toString();
                    showIn=showIn+num_Str;
                    showIn2=showIn2+"*";
                    tv_showInfo.setText(showIn);
                    isClear=false;
                    canPoint=true;
                }else {
                    tv_result.setText("第一位不能为符号");
                }
                break;
            case "÷":
                if (!isClear)
                {
                    lastChar();
                    num_Str=btn.getText().toString();
                    showIn=showIn+num_Str;
                    showIn2=showIn2+"/";
                    tv_showInfo.setText(showIn);
                    isClear=false;
                    canPoint=true;
                }else {
                    tv_result.setText("第一位不能为符号");
                }
                break;
            case "C":
                isClear=true;
                canPoint=true;
                showIn="";
                showIn2="";
                tv_showInfo.setText(showIn);
                tv_result.setText("");
                break;
            case "◀":
                if(!isClear){
                    char p='.';
                    showIn=showIn.substring(0,showIn.length()-1);
                    showIn2=showIn2.substring(0,showIn2.length()-1);
                    if (showIn.length()==0){
                        isClear=true;
                        canPoint=true;
                    }
                    else if (showIn.charAt(showIn.length()-1)==p){
                        canPoint=true;
                    }

                    tv_showInfo.setText(showIn);
                }
                break;
            case "=":
                if(!isClear){
                    res();
                }else {
                    tv_result.setText("请先输入算式再输入等号！");
                }
                break;
            default:
                break;
        }
    }
}
