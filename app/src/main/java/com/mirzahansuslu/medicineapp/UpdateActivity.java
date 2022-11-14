package com.mirzahansuslu.medicineapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText nameInput, typeInput, countInput;
    Button updateButton;
    String id,name,type;
    String count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        nameInput = findViewById(R.id.medicineName2);
        typeInput = findViewById(R.id.medicineType2);
        countInput = findViewById(R.id.medicineCount2);
        updateButton = findViewById(R.id.updateMedicineButton);


        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelper db = new DbHelper(UpdateActivity.this);
                name=nameInput.getText().toString().trim();
                type=typeInput.getText().toString().trim();
                count= countInput.getText().toString().trim();
                db.updateMedicineData(id,name,type,count);
            }
        });


    }
    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("type") && getIntent().hasExtra("count")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
            count = getIntent().getStringExtra("count");

            nameInput.setText(name);
            typeInput.setText(type);
            countInput.setText(count);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}