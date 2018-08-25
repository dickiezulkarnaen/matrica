package com.example.dickiez.matricatest;

import android.content.ContentValues;
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

public class InputPegawaiActivity extends AppCompatActivity {

    EditText etNik, etNama, etLamaKerja, etPangkat, etAlamat, etJabatan, etGaji;
    Spinner spnLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pegawai);

        etNik = (EditText)findViewById(R.id.et_nik_pegawai);
        etNama = (EditText)findViewById(R.id.et_nama_pegawai);
        etLamaKerja = (EditText)findViewById(R.id.et_lm_kerja_pegawai);
        etPangkat = (EditText)findViewById(R.id.et_pangkat_pegawai);
        etAlamat = (EditText)findViewById(R.id.et_alamat_pegawai);
        etJabatan = (EditText)findViewById(R.id.et_jabatan_pegawai);
        etGaji = (EditText)findViewById(R.id.et_gaji_pegawai);
        spnLokasi = (Spinner) findViewById(R.id.spn_lokasi_pegawai);

        getLokasiData();

    }

    private void getLokasiData() {
        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        List<String> listLokasi = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TableLokasiContract.TB_NAME, null);
        cursor.moveToFirst();
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);
            listLokasi.add(cursor.getString(1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listLokasi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLokasi.setAdapter(adapter);
    }

    public void simpanPegawai(View v) {
        String nik = etNik.getText().toString();
        String nama = etNama.getText().toString();
        int lama = Integer.parseInt(etLamaKerja.getText().toString());
        String pangkat = etPangkat.getText().toString();
        String alamat = etAlamat.getText().toString();
        String jabatan = etJabatan.getText().toString();
        int gaji = Integer.parseInt(etGaji.getText().toString());
        String lokasi = spnLokasi.getSelectedItem().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TablePegawaiContract.NIK, nik);
        contentValues.put(TablePegawaiContract.NAMA, nama);
        contentValues.put(TablePegawaiContract.LAMA_KERJA, lama);
        contentValues.put(TablePegawaiContract.PANGKAT, pangkat);
        contentValues.put(TablePegawaiContract.ALAMAT, alamat);
        contentValues.put(TablePegawaiContract.JABATAN, jabatan);
        contentValues.put(TablePegawaiContract.LOKASI, lokasi);
        contentValues.put(TablePegawaiContract.GAJI, gaji);

        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert(TablePegawaiContract.TB_NAME, null, contentValues);
        Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
        clearForm();
    }

    private void clearForm() {
        etNik.setText("");
        etNama.setText("");
        etPangkat.setText("");
        etLamaKerja.setText("");
        etAlamat.setText("");
        etJabatan.setText("");
        etGaji.setText("");
    }

    public void batalPegawai(View v) {
        finish();
    }
}
