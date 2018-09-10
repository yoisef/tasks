package com.example.youssef.mytasks;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView changelayout;
    FragmentTransaction transaction;
    FragmentManager manager;
    arabicfrag myarabicfrag;
    englishfrag myenglish;
    LinearLayout fragenglish;
    retrofit2.Call<Countries> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //changelayout=findViewById(R.id.changebutton);
        fragenglish=findViewById(R.id.englishlayout);

        myarabicfrag=new arabicfrag();
        myenglish=new englishfrag();
         manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.mainlayout,myenglish);

       // transaction.add(R.id.mainlayout,myarabicfrag,"myarabicfrag");
       // transaction.hide(myarabicfrag);
        transaction.commit();


/*
        changelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (changelayout.getText().toString())
                {
                    case "تغيير اللغة":{
                        changelayout.setText("Change Language");
                        FragmentTransaction transaction2=manager.beginTransaction();
                       // transaction2.hide(myarabicfrag);
                       // transaction2.show(myenglish);
                        transaction2.replace(R.id.mainlayout,myenglish);

                        transaction2.commit();
                        break;

                    }
                    case "Change Language":{

                        changelayout.setText("تغيير اللغة");

                        FragmentTransaction transaction3=manager.beginTransaction();
                       // transaction3.hide(myenglish);
                      //  transaction3.show(myarabicfrag);
                        transaction3.replace(R.id.mainlayout,myarabicfrag);

                        transaction3.commit();
                        break;

                    }
                }




            }
        });
        */
    }


}
