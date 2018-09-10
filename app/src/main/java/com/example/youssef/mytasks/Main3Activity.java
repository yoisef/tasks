package com.example.youssef.mytasks;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {

    FragmentManager manager;
    FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        fragcategory fragcategory=new fragcategory();
        fragsubcat fragsubcat=new fragsubcat();

        manager=getFragmentManager();
        transaction=manager.beginTransaction();
       transaction.replace(R.id.main4cat,fragcategory);
        transaction.commit();


    }
}
