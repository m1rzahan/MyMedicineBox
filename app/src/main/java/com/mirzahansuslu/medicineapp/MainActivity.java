package com.mirzahansuslu.medicineapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper;
    ArrayList<String> medicineId,medicineName, medicineType, medicineCount;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    CustomAdapter customAdapter;

/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }
        });
    dbHelper = new DbHelper(MainActivity.this);
        medicineId = new ArrayList<>();
        medicineName = new ArrayList<>();
        medicineType = new ArrayList<>();
        medicineCount = new ArrayList<>();
        storeMedicineDataInArray();
        customAdapter = new CustomAdapter(MainActivity.this,this, medicineId, medicineName, medicineType,
                medicineCount);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    void storeMedicineDataInArray() {
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                medicineId.add(cursor.getString(0));
                medicineName.add(cursor.getString(1));
                medicineType.add(cursor.getString(2));
                medicineCount.add(cursor.getString(3));
            }
        }
    }
}