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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    Button convert_btn_1;

    BottomNavigationView bottomNavigationView;
    PlusFragment plusFragment = new PlusFragment();
    MinusFragment minusFragment = new MinusFragment();

    static List<Element> elementList;
    static List<Element> elementListMin;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        convert_btn_1 = findViewById(R.id.convert_btn_1);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.plus);

        userAcc = new UserAcc(0, "KZT");
        cashText = findViewById(R.id.cashText);
        cashText.setText(userAcc.getCash()+" ₸");

        elementList = new ArrayList<>();
        elementList.add(new Element(1, "770 ₸", "Обед", "13.05.2022"));

        elementListMin = new ArrayList<>();
        elementListMin.add(new Element(1, "780 ₸", "Обед minus", "13.05.2022"));

        convert_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userAcc.getCurrency().equals("KZT")) {
                    if(userAcc.getCash() < 22) {
                        Toast.makeText(MainActivity.this, R.string.top_up_balance, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    userAcc.setCurrency("USD");
                    userAcc.setCash(userAcc.getCash()/430);
                    @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", userAcc.getCash());
                    cashText.setText(formattedDouble + " $");
                }
                else {
                    if(userAcc.getCash() < 0.05) {
                        Toast.makeText(MainActivity.this, R.string.top_up_balance, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    userAcc.setCurrency("KZT");
                    userAcc.setCash(userAcc.getCash()*430);
                    @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", userAcc.getCash());
                    cashText.setText(formattedDouble + " ₸");
                }
            }
        });
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