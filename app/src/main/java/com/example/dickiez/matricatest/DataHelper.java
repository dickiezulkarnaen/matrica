package com.example.dickiez.matricatest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "db_pegawai.db";
    public static final int DB_VER = 1;

    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTbLokasi = TableLokasiContract.CREATE_TB_LOKASI;
        db.execSQL(createTbLokasi);

        String createTbPegawai = TablePegawaiContract.CREATE_TB_PEGAWAI;
        db.execSQL(createTbPegawai);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
