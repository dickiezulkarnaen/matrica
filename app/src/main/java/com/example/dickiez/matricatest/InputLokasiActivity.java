package com.example.dickiez.matricatest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputLokasiActivity extends AppCompatActivity {
    EditText etKdLokasi, etNmLokasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_lokasi);

        etKdLokasi = (EditText)findViewById(R.id.et_kd_lokasi);
        etNmLokasi = (EditText)findViewById(R.id.et_nm_lokasi);

    }

    public void simpanLokasi(View v) {
        String kode = etKdLokasi.getText().toString();
        String nama = etNmLokasi.getText().toString();

        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableLokasiContract.KD_LOKASI, kode);
        contentValues.put(TableLokasiContract.NM_LOKASI, nama);
        db.insert(TableLokasiContract.TB_NAME, null, contentValues);
        Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
        etKdLokasi.setText("");
        etNmLokasi.setText("");
    }

    public void batalLokasi(View v) {
        finish();
    }
}
