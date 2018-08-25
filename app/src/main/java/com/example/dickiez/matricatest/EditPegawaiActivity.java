package com.example.dickiez.matricatest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditPegawaiActivity extends AppCompatActivity {

    EditText etNik, etNama, etLamaKerja, etPangkat, etAlamat, etJabatan, etGaji;
    Spinner spnLokasi;
    List<String> listLokasi;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pegawai);

        etNik = (EditText)findViewById(R.id.et_edit_nik);
        etNama = (EditText)findViewById(R.id.et_edit_nama);
        etLamaKerja = (EditText)findViewById(R.id.et_edit_lama_kerja);
        etPangkat = (EditText)findViewById(R.id.et_edit_pangkat);
        etAlamat = (EditText)findViewById(R.id.et_edit_alamat);
        etJabatan = (EditText)findViewById(R.id.et_edit_jabatan);
        etGaji = (EditText)findViewById(R.id.et_edit_gaji);
        spnLokasi = (Spinner) findViewById(R.id.spn_edit_lokasi);

        String data = getIntent().getStringExtra("nik");
        loadData(data);
    }

    public void loadData(String data) {
        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TablePegawaiContract.TB_NAME+" WHERE "+TablePegawaiContract.NIK+" = '"+data+"'", null);
        cursor.moveToFirst();

        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);
            etNama.setText(cursor.getString(1));
            etNik.setText(cursor.getString(0));
            etLamaKerja.setText(cursor.getString(2));
            etPangkat.setText(cursor.getString(3));
            etAlamat.setText(cursor.getString(4));
            etJabatan.setText(cursor.getString(5));
            etGaji.setText(cursor.getString(7));

            getLokasi();
            for (int i=0; i>listLokasi.size(); i++) {
                if (listLokasi.get(i).equals(cursor.getString(6))) {
                    spnLokasi.setSelection(adapter.getPosition(listLokasi.get(i)));
                }
            }

        }
    }

    public void getLokasi() {
        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        listLokasi = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TableLokasiContract.TB_NAME, null);
        cursor.moveToFirst();
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);
            listLokasi.add(cursor.getString(1));
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listLokasi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLokasi.setAdapter(adapter);
    }

    public void saveEdited(View v) {
        ContentValues contents = new ContentValues();
        contents.put(TablePegawaiContract.NAMA, etNama.getText().toString());
        contents.put(TablePegawaiContract.LAMA_KERJA, etLamaKerja.getText().toString());
        contents.put(TablePegawaiContract.PANGKAT, etPangkat.getText().toString());
        contents.put(TablePegawaiContract.ALAMAT, etAlamat.getText().toString());
        contents.put(TablePegawaiContract.JABATAN, etJabatan.getText().toString());
        contents.put(TablePegawaiContract.LOKASI, spnLokasi.getSelectedItem().toString());
        contents.put(TablePegawaiContract.GAJI, etGaji.getText().toString());

        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        db.update(TablePegawaiContract.TB_NAME, contents, TablePegawaiContract.NIK+" = '"+etNik.getText().toString()+"'", null);
        Toast.makeText(this, "UPDATE SUCCESS", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(EditPegawaiActivity.this, ReportActivity.class);
        startActivity(i);
        finish();
    }

    public void cancelEdit(View v) {
        finish();
    }
}
