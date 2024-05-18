package ru.wolfnord.task7;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView dateTextView, timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTextView = findViewById(R.id.date_textview);
        timeTextView = findViewById(R.id.time_textview);

        Button startServiceButton = findViewById(R.id.start_service_button);
        Button stopServiceButton = findViewById(R.id.stop_service_button);
        Button customDialogButton = findViewById(R.id.custom_dialog_button);

        startServiceButton.setOnClickListener(v -> startService(new Intent(MainActivity.this, MyService.class)));

        stopServiceButton.setOnClickListener(v -> stopService(new Intent(MainActivity.this, MyService.class)));

        customDialogButton.setOnClickListener(v -> openCustomDialog());
    }

    public void openAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("AlertDialog")
                .setMessage("Вы уверены, что хотите выполнить это действие?")
                .setPositiveButton("Да", (dialog, id) -> {
                    // Обработка нажатия кнопки "Да"
                })
                .setNegativeButton("Отмена", (dialog, id) -> {
                    dialog.dismiss();
                    // Обработка нажатия кнопки "Отмена"
                });
        builder.create().show();
    }

    public void openDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                (view1, year, month, dayOfMonth) -> dateTextView.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void openTimePickerDialog(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MainActivity.this, (view1, hourOfDay, minute) -> timeTextView.setText(hourOfDay + ":" + minute), 12, 0, true);
        timePickerDialog.show();
    }

    public void openCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        Button dialogButton = dialog.findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
