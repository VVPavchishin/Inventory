package com.pavchishin.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pavchishin.inventory.Utilites.DbHelper;
import com.pavchishin.inventory.Utilites.ExcelReader;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private Button buttonLoad;
    Context context = this;
    public static DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoad = findViewById(R.id.load);
        helper = new DbHelper(getApplicationContext());

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLoad.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SQLiteDatabase database = helper.getWritableDatabase();
                        try {
                            ExcelReader.readExcel(database);
                            buttonLoad.setText("Complete");
                            helper.close();
                            startActivity(new Intent(context, SecondActivity.class));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        buttonLoad.setText("Loading...");
                        //Toast.makeText(getApplicationContext(), "Intent.ACTION_BATTERY_CHANGED", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}