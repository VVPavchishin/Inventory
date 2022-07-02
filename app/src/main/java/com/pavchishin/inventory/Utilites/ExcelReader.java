package com.pavchishin.inventory.Utilites;

import static com.pavchishin.inventory.Utilites.DbEntry.FieldEntry.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ExcelReader {



    public static void readExcel( SQLiteDatabase database) throws FileNotFoundException {

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Inventory/inventory.xlsx");

        if (file.exists()) {
            InputStream stream = new FileInputStream(file);
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(stream);
                XSSFSheet sheet = workbook.getSheetAt(0);
                int rowsCount = sheet.getPhysicalNumberOfRows();
                Log.d("number rows", String.valueOf(rowsCount));

                ContentValues cv = new ContentValues();

                for (int r = 10; r < rowsCount; r++) {
                    Row row = sheet.getRow(r);

                    cv.put(DbEntry.FieldEntry.COLUMN_PART_LOCATION, String.valueOf(row.getCell(0)));
                    Log.d("cell location", String.valueOf(row.getCell(0)));
                    cv.put(DbEntry.FieldEntry.COLUMN_PART_NUMBER, String.valueOf(row.getCell(3)));
                    Log.d("cell number", String.valueOf(row.getCell(3)));
                    cv.put(DbEntry.FieldEntry.COLUMN_PART_NAME, String.valueOf(row.getCell(4)));
                    Log.d("cell name", String.valueOf(row.getCell(4)));
                    cv.put(DbEntry.FieldEntry.COLUMN_PART_EXIST, String.valueOf(row.getCell(8)));
                    Log.d("cell q", String.valueOf(row.getCell(8)));
                    cv.put(DbEntry.FieldEntry.COLUMN_PART_FACT, 0.0);

                    database.insert(TABLE_NAME, null, cv);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
