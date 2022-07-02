package com.pavchishin.inventory;

import static com.pavchishin.inventory.Utilites.DbHelper.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pavchishin.inventory.Utilites.DbHelper;
import com.pavchishin.inventory.Utilites.ExcelReader;

import java.io.FileNotFoundException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonLoad;
    Context context = this;
    public static DbHelper helper;
    private TextView textTitle;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private Button buttonStart;

    private RadioButton radioFull;
    private RadioButton radioCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoad = findViewById(R.id.load);
        buttonStart = findViewById(R.id.button_start);
        textTitle = findViewById(R.id.title_text);
        radioGroup = findViewById(R.id.radio);
        spinner = findViewById(R.id.spinner);

        radioFull = findViewById(R.id.full_warehouse);
        radioCustom = findViewById(R.id.custom_warehouse);

        context.deleteDatabase(DATABASE_NAME);
        helper = new DbHelper(getApplicationContext());

        buttonLoad.setOnClickListener(view -> {
            buttonLoad.setEnabled(false);
            new Thread(() -> {
                SQLiteDatabase database = helper.getWritableDatabase();
                try {
                    ExcelReader.readExcel(database);
                    buttonLoad.setText(R.string.complete);
                    helper.close();
                    Thread.sleep(1000);
                    buttonLoad.setVisibility(View.INVISIBLE);
                    runOnUiThread(this::calculationPage);
                } catch (FileNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Handler(Looper.getMainLooper()).post(() -> {
                buttonLoad.setText(R.string.Loading_button);
                //Toast.makeText(getApplicationContext(), "Intent.ACTION_BATTERY_CHANGED", Toast.LENGTH_LONG).show();
            });
        });
    }

    private void calculationPage() {

        buttonStart.setVisibility(View.VISIBLE);
        radioGroup.setVisibility(View.VISIBLE);
        textTitle.setVisibility(View.VISIBLE);

        SQLiteDatabase database = helper.getReadableDatabase();
        List<String> location = new DbHelper(context).onSpinner(database);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, location);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.custom_warehouse) {
                    spinner.setVisibility(View.VISIBLE);
                } else {
                    spinner.setVisibility(View.INVISIBLE);
                }
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId == radioFull.getId()) {
                    fullCalculation();
                } else {
                    customCalculation();
                }

            }
        });

    }

    private void customCalculation() {
    }

    private void fullCalculation() {
    }

}