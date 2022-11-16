package com.mirzahansuslu.medicineapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText nameInput, typeInput, countInput;
    Button updateButton;
    Button deleteButton;
     String id;
    String name;
    String type;
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
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(name);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

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
        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();
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
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete  " +name + " ?");
        builder.setTitle("Are you sure to delete ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DbHelper db = new DbHelper(UpdateActivity.this);
                db.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

        });
        builder.create().show();
    }

}