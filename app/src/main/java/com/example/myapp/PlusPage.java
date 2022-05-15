package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Calendar;
import java.util.List;

public class PlusPage extends AppCompatActivity {
    Button addElemBtn;
    ImageButton date_btn;

    EditText plus_amount_field;
    EditText comment_box;
    TextView dateBox;

    Calendar dateAndTime= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_page);

        addElemBtn = findViewById(R.id.addElementBtn);
        date_btn = findViewById(R.id.date_btn);

        plus_amount_field = findViewById(R.id.plus_amount_field);
        comment_box = findViewById(R.id.comment_box);
        dateBox = findViewById(R.id.dateBox);

        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(view);
            }
        });

        addElemBtn.setOnClickListener(new View.OnClickListener() {
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
                    PlusFragment.elementList.add(new Element(4, 777, "saken test", "11.55.2222"));
                    //PlusFragment.elementList.add(new Element(PlusFragment.elementList.get(PlusFragment.elementList.size()-1).getId() + 1, Double.parseDouble(amount), commentBox, dateString));
                    /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);*/
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
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}