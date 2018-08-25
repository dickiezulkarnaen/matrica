package com.example.dickiez.matricatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataHelper helper = new DataHelper(this);
        helper.getWritableDatabase();
    }

    public void gotoInputPegawai(View v) {
        Intent i = new Intent(MainActivity.this, InputPegawaiActivity.class);
        startActivity(i);
    }

    public void gotoInputLokasi(View v) {
        Intent i = new Intent(MainActivity.this, InputLokasiActivity.class);
        startActivity(i);
    }

    public void gotoRiwayatJabatan(View v) {
        Intent i = new Intent(MainActivity.this, RiwayatJabatanActivity.class);
        startActivity(i);
    }

    public void gotoReport(View v) {
        Intent i = new Intent(MainActivity.this, ReportActivity.class);
        startActivity(i);
    }
}
