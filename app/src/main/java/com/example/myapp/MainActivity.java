package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapp.adapter.ElementAdapter;
import com.example.myapp.model.Element;
import com.example.myapp.model.UserAcc;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    RecyclerView elementRecyclerPlus;
    ElementAdapter elementAdapter;
    static UserAcc userAcc;
    @SuppressLint("StaticFieldLeak")
    static TextView cashText;

    BottomNavigationView bottomNavigationView;
    PlusFragment plusFragment = new PlusFragment();
    MinusFragment minusFragment = new MinusFragment();

    static List<Element> elementList;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.plus);

        userAcc = new UserAcc(0, "KZT");
        cashText = findViewById(R.id.cashText);
        cashText.setText(userAcc.getCash()+" ₸");

        elementList = new ArrayList<>();
        elementList.add(new Element(1, "770 ₸", "Обед", "13.05.2022"));
    }

    private void setElementRecyclerPlus(List<Element> elementList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        elementRecyclerPlus = findViewById(R.id.elementRecyclerPlus);
        elementRecyclerPlus.setLayoutManager(layoutManager);

        elementAdapter = new ElementAdapter(plusFragment.getContext(), elementList);
        elementRecyclerPlus.setAdapter(elementAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.plus:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, plusFragment).commit();
                return true;

            case R.id.minus:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, minusFragment).commit();
                return true;
        }
        return false;
    }
}