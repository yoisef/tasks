package com.example.youssef.mytasks;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class englishfrag extends Fragment {

    Call<List<Countries>> call;
    Call<List<City>> call1;
    TextView mytext,change;
    Context context;
    Spinner spinnercountry,spinnercode,spincity;
    TextView changelan;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.englishlayout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mytext=view.findViewById(R.id.texttgrba);
        spinnercountry=view.findViewById(R.id.countspineren);
        changelan=view.findViewById(R.id.changeeng);
        spinnercode=view.findViewById(R.id.codee);
        spincity=view.findViewById(R.id.cityid);
        context=view.getContext();
        manager=getFragmentManager();
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular.ttf");
        changelan.setTypeface(typeface);


        changelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 transaction=manager.beginTransaction();
                // transaction3.hide(myenglish);
                //  transaction3.show(myarabicfrag);
                arabicfrag myarabic=new arabicfrag();
                transaction.replace(R.id.mainlayout,myarabic);

                transaction.commit();
            }
        });



        OkHttpClient.Builder builderr = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptorr = new HttpLoggingInterceptor();
        loggingInterceptorr.setLevel(HttpLoggingInterceptor.Level.BODY);

        builderr.addInterceptor(loggingInterceptorr);


        Retrofit retrofitt = new Retrofit.Builder()
                .baseUrl("http://souq.hardtask.co/app/app.asmx/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builderr.build())
                .build();

        final myinterface servicee = retrofitt.create(myinterface.class);



        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(loggingInterceptor);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://souq.hardtask.co/app/app.asmx/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        myinterface service = retrofit.create(myinterface.class);
       call=service.getcountries();
       call.enqueue(new Callback<List<Countries>>() {
           @Override
           public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {

               List<Countries> mylist=response.body();
               ArrayList<String> countrynames=new ArrayList<>();
               ArrayList<String> codnum=new ArrayList<>();
               final ArrayList<Integer> couid=new ArrayList<>();

               for (int i=0;i<mylist.size();i++)
               {
                   countrynames.add(mylist.get(i).getTitleEN());
                   codnum.add(mylist.get(i).getCode());
                   couid.add(mylist.get(i).getId());
               }
               ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, countrynames);
               ArrayAdapter<String> dataAdapterr = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, codnum);

               dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               dataAdapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

               spinnercountry.setAdapter(dataAdapter);
               spinnercode.setAdapter(dataAdapterr);

               spinnercountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       call1=servicee.getcities(couid.get(position));
                       call1.enqueue(new Callback<List<City>>() {
                           @Override
                           public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                               List<City> mycities=response.body();
                               ArrayList<String> citiesn=new ArrayList<>();
                               for (int c=0;c<mycities.size();c++)
                               {
                                   citiesn.add(mycities.get(c).getTitleEN());
                               }
                               ArrayAdapter<String> dataAdapterrr = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, citiesn);

                               dataAdapterrr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                               spincity.setAdapter(dataAdapterrr);
                           }

                           @Override
                           public void onFailure(Call<List<City>> call, Throwable t) {

                           }
                       });
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });


            //   mytext.setText(mylist.get(0).getTitleAR());


           }

           @Override
           public void onFailure(Call<List<Countries>> call, Throwable t) {



           }
       });
    }
}
