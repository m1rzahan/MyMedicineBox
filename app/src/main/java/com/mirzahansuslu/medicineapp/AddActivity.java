package com.mirzahansuslu.medicineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText medicineName, medicineType, medicineCount;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        medicineName = findViewById(R.id.medicineName);
        medicineType = findViewById(R.id.medicineType);
        medicineCount = findViewById(R.id.medicineCount);
        addButton = findViewById(R.id.addMedicineButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(AddActivity.this);
                dbHelper.addMedicine(medicineName.getText().toString().trim(),medicineType.getText().toString().trim(),
                        Integer.valueOf(medicineCount.getText().toString().trim()));
            }
        });

    }
}