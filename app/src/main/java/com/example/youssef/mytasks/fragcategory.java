package com.example.youssef.mytasks;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youssef.mytasks.category.categoryadaoter;

public class fragcategory extends android.app.Fragment {

    RecyclerView categoryrecycle;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragcategory,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context=view.getContext();

        categoryrecycle=view.findViewById(R.id.recyclecate);
        categoryrecycle.setLayoutManager(new GridLayoutManager(context, 2));
        categoryrecycle.setAdapter(new categoryadaoter(context));

    }
}
