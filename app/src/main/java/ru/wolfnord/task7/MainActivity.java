package ru.wolfnord.task7;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnDateSelectedListener {

    private TextView dateTextView, timeTextView;
    private Button startServiceButton, stopServiceButton, customDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTextView = findViewById(R.id.date_textview);
        timeTextView = findViewById(R.id.time_textview);

        startServiceButton = findViewById(R.id.start_service_button);
        stopServiceButton = findViewById(R.id.stop_service_button);
        customDialogButton = findViewById(R.id.custom_dialog_button);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
            }
        });

        customDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog();
            }
        });
    }

    public void openAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("AlertDialog")
                .setMessage("Вы уверены, что хотите выполнить это действие?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Обработка нажатия кнопки "Да"
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // Обработка нажатия кнопки "Отмена"
                    }
                });
        builder.create().show();
    }

    public void openDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateTextView.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void openTimePickerDialog(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeTextView.setText(hourOfDay + ":" + minute);
            }
        }, 12, 0, true);
        timePickerDialog.show();
    }

    public void openCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        Button dialogButton = dialog.findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onDateSelected(int year, int month, int day) {
        dateTextView.setText(String.format("%d/%d/%d/", day, month, year));
    }
}
