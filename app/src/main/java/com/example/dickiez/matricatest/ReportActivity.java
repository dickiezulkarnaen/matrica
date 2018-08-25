package com.example.dickiez.matricatest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        List<String> nama = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TablePegawaiContract.TB_NAME, null);
        cursor.moveToFirst();
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);
            nama.add(cursor.getString(1));
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_report);
        ReportAdapter adapter = new ReportAdapter(nama, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
