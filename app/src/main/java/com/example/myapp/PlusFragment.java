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
import com.example.myapp.model.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class PlusFragment extends Fragment {
    RecyclerView recyclerView;
    Button plusBtn;

    public PlusFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plus, container, false);
        recyclerView = view.findViewById(R.id.elementRecyclerPlus);
        ElementAdapter elementAdapter = new ElementAdapter(getContext(), MainActivity.elementList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(elementAdapter);

        // обработка кнопки +
        plusBtn = view.findViewById(R.id.plus_btn);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlusPage.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }
}