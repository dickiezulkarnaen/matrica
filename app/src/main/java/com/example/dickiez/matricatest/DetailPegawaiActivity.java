package com.example.dickiez.matricatest;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class DetailPegawaiActivity extends AppCompatActivity {
    TextView tvNama, tvNik, tvLamaKerja, tvPangkat, tvAlamat, tvJabatan, tvGaji, tvLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pegawai);

        tvNama = (TextView) findViewById(R.id.tv_detail_nama);
        tvNik = (TextView)findViewById(R.id.tv_detail_nik);
        tvLamaKerja = (TextView)findViewById(R.id.tv_detail_lama);
        tvPangkat = (TextView)findViewById(R.id.tv_detail_pangkat);
        tvAlamat = (TextView)findViewById(R.id.tv_detail_alamat);
        tvJabatan = (TextView)findViewById(R.id.tv_detail_jabatan);
        tvGaji = (TextView)findViewById(R.id.tv_detail_gaji);
        tvLokasi = (TextView) findViewById(R.id.tv_detail_lokasi);

        String data = getIntent().getStringExtra("nama");

        DataHelper helper = new DataHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TablePegawaiContract.TB_NAME+" WHERE "+TablePegawaiContract.NAMA+" = '"+data+"'", null);
        cursor.moveToFirst();

        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);
            tvNama.setText(cursor.getString(1));
            tvNik.setText(cursor.getString(0));
            tvLamaKerja.setText(cursor.getString(2));
            tvPangkat.setText(cursor.getString(3));
            tvAlamat.setText(cursor.getString(4));
            tvJabatan.setText(cursor.getString(5));
            tvLokasi.setText(cursor.getString(6));
            tvGaji.setText(cursor.getString(7));

        }
    }

    public void gotoEdit(View v) {
        Intent i = new Intent(DetailPegawaiActivity.this, EditPegawaiActivity.class);
        i.putExtra("nik", tvNik.getText().toString());
        startActivity(i);
    }

    public void gotoDelete(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Delete");

        alertDialogBuilder
                .setMessage("Are You Sure TO Delete "+tvNik.getText())
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        DataHelper helper = new DataHelper(DetailPegawaiActivity.this);
                        SQLiteDatabase db = helper.getReadableDatabase();
                        db.delete(TablePegawaiContract.TB_NAME, TablePegawaiContract.NIK+" = '"+tvNik.getText().toString()+"'", null);
                        Intent i = new Intent(DetailPegawaiActivity.this, ReportActivity.class);
                        i.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
