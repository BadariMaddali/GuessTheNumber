package com.example.guessthenumber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class StartUpActivity extends AppCompatActivity {
    int number;
    Random rand;
    ArrayList<Integer> num;
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        number = 0;
        str="";

    }

    public void start(View view) {
        number = generateRandomNumber();
        Intent intent = new Intent(StartUpActivity.this,MainActivity.class);
        intent.putExtra("number",number);
        startActivity(intent);

    }

    private int generateRandomNumber() {
        num = new ArrayList<Integer>();
        int n;
        rand = new Random();
        while (num.size()<4){
            n = rand.nextInt(10);
            if(!num.contains(n) && !num.contains(0)){
                num.add(n);
                str+=n;
            }
        }
        n = Integer.parseInt(str);

        return n;
    }
}
