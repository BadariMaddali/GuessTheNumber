package com.example.guessthenumber;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    int number=0;
    boolean proceed;
    String presentText="";
    int usernum=0;
    String mat,matched="";
    int count=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit1);
        textView = (TextView) findViewById(R.id.text1);

        number = getIntent().getIntExtra("number",0000);


    }

    public void subOk(View view) {
        String usernumString = editText.getText().toString();
        String proceed= inputValidations(usernumString);
        if(proceed==""){
            usernum = Integer.parseInt(usernumString);
            editText.setText("");
            matched = checkMatchings(usernum);
            presentText=textView.getText().toString();
            textView.setText(presentText+count+"             "+usernum+"             "+matched+"\n");
            count++;
        }
        else{
            Toast toast = Toast.makeText(this,proceed,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            editText.setText("");
        }

    }

    private String inputValidations(String numstring) {
        if(numstring == ""){
            return "Enter Number";
        }
        else if((numstring.length()<4) || (numstring.length()>4)){
            return "Length invalid";
        }
        else if(!digitsAreRepeating(numstring)){
            return "digits are repeating";
        }
        return "";
    }

    private boolean digitsAreRepeating(String numstring) {
        for(int i =0; i<4;i++){
            if(contains(numstring.toCharArray()[i],numstring.substring(i+1).toCharArray())){
                return false;
            }
        }
        return  true;
    }

    private String checkMatchings(int ab) {
        int bcount=0,ccount=0;
        char[] a,b;

        //c = Integer.toString(number); //c - original num string
        a = Integer.toString(number).toCharArray();   // a - original num char array
        b = Integer.toString(ab).toCharArray(); // b - entered num char array
        for(int i=0;i<4;i++){
            if (a[i] == b[i]){
                bcount ++;
            }
            else if(contains(b[i],a)){
                ccount++;
            }
        }
        if(bcount!=4){
            mat = String.valueOf(bcount) + "B "+String.valueOf(ccount) + "C";

        }
        else{
            Toast toast = Toast.makeText(this,"You Won!!\nThe Number is "+number,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,StartUpActivity.class);
                    startActivity(intent);
                }
            },5000);
            return "4B 0C";
        }
        return mat;
    }
    private boolean contains(char c,char[] array){
        for (char x : array) {
            if (x == c) {
                return true;
            }
        }
        return false;
    }
}
