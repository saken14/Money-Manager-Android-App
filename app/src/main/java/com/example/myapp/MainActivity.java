package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapp.adapter.ElementAdapter;
import com.example.myapp.model.Element;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    RecyclerView elementRecyclerPlus;
    ElementAdapter elementAdapter;

    BottomNavigationView bottomNavigationView;
    PlusFragment plusFragment = new PlusFragment();
    MinusFragment minusFragment = new MinusFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.plus);

        /*List<Element> elementList = new ArrayList<>();
        elementList.add(new Element(1, 770, "Обед", "13.05.2022"));
        elementList.add(new Element(2, 1000, "Транспорт", "14.05.2022"));
        elementList.add(new Element(3, 2800, "Тариф Актив", "15.05.2022"));

        setElementRecyclerPlus(elementList);*/

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity_layout, plusFragment);
        fragmentTransaction.commit();*/
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