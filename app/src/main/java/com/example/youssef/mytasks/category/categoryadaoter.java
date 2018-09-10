package com.example.youssef.mytasks.category;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.youssef.mytasks.R;
import com.example.youssef.mytasks.fragcategory;
import com.example.youssef.mytasks.fragsubcat;
import com.example.youssef.mytasks.myinterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class categoryadaoter extends RecyclerView.Adapter<categoryadaoter.viewholder> {

    Context context;

    Call<List<category>> call;
    List<category> mylist=new ArrayList<>();


    public categoryadaoter(Context con)
    {
        this.context=con;


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
        call=service.getcategories();
        call.enqueue(new Callback<List<category>>() {
            @Override
            public void onResponse(Call<List<category>> call, Response<List<category>> response) {

              mylist =response.body();
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<category>> call, Throwable t) {

            }
        });

    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.singlecategory,parent,false);
        viewholder holder=new viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        holder.catname.setText(mylist.get(position).getTitleEN());
        Glide.with(context)
                .load(mylist.get(position).getPhoto())
                .into(holder.catimge);

        if (position == 0) {
            holder.singlerow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragcategory mycate = new fragcategory();
                    fragsubcat mysubcat = new fragsubcat();
                    android.app.FragmentManager manager = ((Activity) context).getFragmentManager();
                    android.app.FragmentTransaction transaction = manager.beginTransaction();

                    transaction.replace(R.id.main4cat, mysubcat);

                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

        }
    }
    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView catname;
        ImageView catimge;
        RelativeLayout singlerow;
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular.ttf");

        public viewholder(View itemView) {
            super(itemView);

            catname=itemView.findViewById(R.id.catname);
            catimge=itemView.findViewById(R.id.catimg);
            singlerow=itemView.findViewById(R.id.mysinglerow);
            catname.setTypeface(typeface);
        }
    }
}
