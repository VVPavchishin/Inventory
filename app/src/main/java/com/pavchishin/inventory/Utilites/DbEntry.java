package com.pavchishin.inventory.Utilites;

import android.provider.BaseColumns;

public final class DbEntry {

    private DbEntry() { }

    public static class FieldEntry implements BaseColumns {
        public static final String TABLE_NAME = "warehouse";
        public static final String COLUMN_PART_NUMBER = "part_number";
        public static final String COLUMN_PART_NAME = "part_name";
        public static final String COLUMN_PART_LOCATION = "part_location";
        public static final String COLUMN_PART_EXIST = "part_exist";
        public static final String COLUMN_PART_FACT = "part_fact";
    }
}
