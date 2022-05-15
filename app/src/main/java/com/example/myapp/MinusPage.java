package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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

import java.util.Calendar;

public class MinusPage extends AppCompatActivity {
    Button addElemBtn_min;
    Button convert_btn_2_min;
    ImageButton date_btn_min;

    EditText plus_amount_field_min;
    EditText comment_box_min;
    TextView dateBox_min;
    TextView currency_min;
    @SuppressLint("StaticFieldLeak")
    static TextView cashText_min;

    Calendar dateAndTime= Calendar.getInstance();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minus_page);

        addElemBtn_min = findViewById(R.id.addElementBtn_min);
        convert_btn_2_min = findViewById(R.id.convert_btn_2_min);
        date_btn_min = findViewById(R.id.date_btn_min);
        cashText_min = findViewById(R.id.cashText_min);

        plus_amount_field_min = findViewById(R.id.plus_amount_field_min);
        comment_box_min = findViewById(R.id.comment_box_min);
        dateBox_min = findViewById(R.id.dateBox_min);
        currency_min = findViewById(R.id.currency_min);
        if(MainActivity.userAcc.getCurrency().equals("KZT")) {
            cashText_min.setText(MainActivity.userAcc.getCash() + " ₸");
            currency_min.setText("₸");
        }
        else {
            cashText_min.setText(MainActivity.userAcc.getCash() + " $");
            currency_min.setText("$");
        }

        date_btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });

        setInitialDateTime();

        convert_btn_2_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.userAcc.getCurrency().equals("KZT")) {
                    MainActivity.userAcc.setCurrency("USD");
                    MainActivity.userAcc.setCash(MainActivity.userAcc.getCash()/430);
                    @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
                    MainActivity.cashText.setText(formattedDouble + " $");
                    cashText_min.setText(formattedDouble + " $");
                }
                else {
                    MainActivity.userAcc.setCurrency("KZT");
                    MainActivity.userAcc.setCash(MainActivity.userAcc.getCash()*430);
                    @SuppressLint("DefaultLocale") String formattedDouble = String.format("%.2f", MainActivity.userAcc.getCash());
                    MainActivity.cashText.setText(formattedDouble + " ₸");
                    cashText_min.setText(formattedDouble + " ₸");
                }
            }
        });

        addElemBtn_min.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String amount = plus_amount_field_min.getText().toString().trim();
                String commentBox = comment_box_min.getText().toString().trim();
                String dateString = dateBox_min.getText().toString().trim();

                if(amount.equals("")) {
                    Toast.makeText(MinusPage.this, R.string.empty_plus_amount_field, Toast.LENGTH_SHORT).show();
                }
                else if(commentBox.equals("")) {
                    Toast.makeText(MinusPage.this, R.string.empty_comment_box, Toast.LENGTH_SHORT).show();
                }
                else if(dateString.equals("")) {
                    Toast.makeText(MinusPage.this, R.string.empty_dateBox, Toast.LENGTH_SHORT).show();
                }
                else {
                    //Collections.reverse(MainActivity.elementListMin);
                    if(MainActivity.userAcc.getCurrency().equals("KZT")) {
                        MainActivity.elementListMin.add(new Element(MainActivity.elementListMin.get(MainActivity.elementListMin.size()-1).getId() + 1, amount + " ₸", commentBox, dateString));
                        MainActivity.userAcc.minusCash(Double.parseDouble(amount));

                        MainActivity.cashText.setText(MainActivity.userAcc.getCash() + " ₸");
                        cashText_min.setText(MainActivity.userAcc.getCash() + " ₸");
                    }
                    else {
                        MainActivity.elementListMin.add(new Element(MainActivity.elementListMin.get(MainActivity.elementListMin.size()-1).getId() + 1, amount + " $", commentBox, dateString));
                        MainActivity.userAcc.minusCash(Double.parseDouble(amount));

                        MainActivity.cashText.setText(MainActivity.userAcc.getCash() + " $");
                        cashText_min.setText(MainActivity.userAcc.getCash() + " $");
                    }
                    //Collections.reverse(MainActivity.elementListMin);
                    finish();
                }
            }
        });
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(MinusPage.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {
        dateBox_min.setText(DateUtils.formatDateTime(this,
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