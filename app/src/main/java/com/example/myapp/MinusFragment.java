package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapp.adapter.ElementAdapter;
import com.example.myapp.adapter.ElementAdapterMin;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class MinusFragment extends Fragment {
    RecyclerView recyclerView;
    Button plusBtn;

    public MinusFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_minus, container, false);
        recyclerView = view.findViewById(R.id.elementRecyclerMinus);
        ElementAdapterMin elementAdapter = new ElementAdapterMin(getContext(), MainActivity.elementListMin);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(elementAdapter);

        // обработка кнопки +
        plusBtn = view.findViewById(R.id.plus_btn_min);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MinusPage.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }
}