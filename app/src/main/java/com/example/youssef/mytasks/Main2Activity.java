package com.example.youssef.mytasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView task1,task2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        task1=findViewById(R.id.taskk1);
        task2=findViewById(R.id.taskk2);

       task1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity( new Intent(Main2Activity.this,Main3Activity.class));

           }
       });

       task2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity( new Intent(Main2Activity.this,MainActivity.class));


           }
       });
}
}