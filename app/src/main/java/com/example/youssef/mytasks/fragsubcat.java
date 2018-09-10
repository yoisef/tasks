package com.example.youssef.mytasks;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youssef.mytasks.category.categoryadaoter;
import com.example.youssef.mytasks.category.catgsubadapter;

public class fragsubcat extends android.app.Fragment {

    RecyclerView categoryrecycle;
    Context context;
    TextView titlecarr;
    ImageView backimg;
    FragmentManager manager;
    FragmentTransaction transaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragsubcat,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context=view.getContext();
        titlecarr=view.findViewById(R.id.titlecar);
        backimg=view.findViewById(R.id.backimg);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/GE Dinar One Medium.ttf");
        titlecarr.setTypeface(typeface);
        manager=getFragmentManager();
        transaction=manager.beginTransaction();



        categoryrecycle=view.findViewById(R.id.recyclesub);
        categoryrecycle.setLayoutManager(new GridLayoutManager(context, 2));
        categoryrecycle.setAdapter(new catgsubadapter(context));

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragcategory mycat=new fragcategory();
                transaction.replace(R.id.main4cat,mycat);
                transaction.commit();


            }
        });

    }
}