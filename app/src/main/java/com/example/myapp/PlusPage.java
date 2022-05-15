package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.model.Element;
import com.example.myapp.model.UserAcc;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlusPage extends AppCompatActivity {
    Button addElemBtn;
    Button convert_btn_2;
    ImageButton date_btn;

    EditText plus_amount_field;
    EditText comment_box;
    TextView dateBox;
    TextView currency;
    @SuppressLint("StaticFieldLeak")
    static TextView cashText;

    Calendar dateAndTime= Calendar.getInstance();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_page);

        addElemBtn = findViewById(R.id.addElementBtn);
        convert_btn_2 = findViewById(R.id.convert_btn_2);
        date_btn = findViewById(R.id.date_btn);
        cashText = findViewById(R.id.cashText);

        plus_amount_field = findViewById(R.id.plus_amount_field);
        comment_box = findViewById(R.id.comment_box);
        dateBox = findViewById(R.id.dateBox);
        currency = findViewById(R.id.currency);
        if(MainActivity.userAcc.getCurrency().equals("KZT")) {
            @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
            cashText.setText(formattedDouble + " ₸");
            currency.setText("₸");
        }
        else {
            @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
            cashText.setText(formattedDouble + " $");
            currency.setText("$");
        }

        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });

        setInitialDateTime();

        convert_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.userAcc.getCurrency().equals("KZT")) {
                    if(MainActivity.userAcc.getCash() < 22) {
                        Toast.makeText(PlusPage.this, R.string.top_up_balance, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    MainActivity.userAcc.setCurrency("USD");
                    MainActivity.userAcc.setCash(MainActivity.userAcc.getCash()/430);
                    @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
                    MainActivity.cashText.setText(formattedDouble + " $");
                    cashText.setText(formattedDouble + " $");
                }
                else {
                    if(MainActivity.userAcc.getCash() < 0.05) {
                        Toast.makeText(PlusPage.this, R.string.top_up_balance, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    MainActivity.userAcc.setCurrency("KZT");
                    MainActivity.userAcc.setCash(MainActivity.userAcc.getCash()*430);
                    @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
                    MainActivity.cashText.setText(formattedDouble + " ₸");
                    cashText.setText(formattedDouble + " ₸");
                }
            }
        });

        addElemBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String amount = plus_amount_field.getText().toString().trim();
                String commentBox = comment_box.getText().toString().trim();
                String dateString = dateBox.getText().toString().trim();

                if(amount.equals("")) {
                    Toast.makeText(PlusPage.this, R.string.empty_plus_amount_field, Toast.LENGTH_SHORT).show();
                }
                else if(commentBox.equals("")) {
                    Toast.makeText(PlusPage.this, R.string.empty_comment_box, Toast.LENGTH_SHORT).show();
                }
                else if(dateString.equals("")) {
                    Toast.makeText(PlusPage.this, R.string.empty_dateBox, Toast.LENGTH_SHORT).show();
                }
                else {
                    //Collections.reverse(MainActivity.elementList);
                    if(MainActivity.userAcc.getCurrency().equals("KZT")) {
                        if(MainActivity.elementList.size() == 0)
                            MainActivity.elementList.add(new Element(1, amount + " ₸", commentBox, dateString));
                        else
                            MainActivity.elementList.add(new Element(MainActivity.elementList.get(MainActivity.elementList.size()-1).getId() + 1, amount + " ₸", commentBox, dateString));
                        MainActivity.userAcc.addCash(Double.parseDouble(amount));

                        @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
                        MainActivity.cashText.setText(formattedDouble + " ₸");
                        cashText.setText(formattedDouble + " ₸");
                    }
                    else {
                        if(MainActivity.elementList.size() == 0)
                            MainActivity.elementList.add(new Element(1, amount + " $", commentBox, dateString));
                        else
                            MainActivity.elementList.add(new Element(MainActivity.elementList.get(MainActivity.elementList.size()-1).getId() + 1, amount + " $", commentBox, dateString));
                        MainActivity.userAcc.addCash(Double.parseDouble(amount));

                        @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
                        MainActivity.cashText.setText(formattedDouble + " $");
                        cashText.setText(formattedDouble + " $");
                    }
                    //Collections.reverse(MainActivity.elementList);
                    finish();
                }
            }
        });
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(PlusPage.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {
        dateBox.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}