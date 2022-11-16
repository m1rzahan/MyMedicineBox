package com.mirzahansuslu.medicineapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper;
    ArrayList<String> medicineId,medicineName, medicineType, medicineCount;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    CustomAdapter customAdapter;
    ImageView emptyBox;
    TextView noData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.add_button);
        noData = findViewById(R.id.no_data);
        emptyBox = findViewById(R.id.empty_box);
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
           emptyBox.setVisibility(View.VISIBLE);
           noData.setVisibility(View.VISIBLE);
        }
        else {
            while (cursor.moveToNext()) {
                medicineId.add(cursor.getString(0));
                medicineName.add(cursor.getString(1));
                medicineType.add(cursor.getString(2));
                medicineCount.add(cursor.getString(3));
            }
            emptyBox.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.medicine_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all) {

            DbHelper db = new DbHelper(this);
            db.deleteAllMedicineData();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Are you sure to delete all  ? ");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    DbHelper db = new DbHelper(MainActivity.this);
                    db.deleteAllMedicineData();
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
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
        return super.onOptionsItemSelected(item);
    }
}