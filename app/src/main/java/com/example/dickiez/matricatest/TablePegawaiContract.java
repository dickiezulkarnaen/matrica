package com.example.dickiez.matricatest;

public class TablePegawaiContract {
    public static final String TB_NAME = "TB_PEGAWAI";

    public static final String NIK = "nik";
    public static final String NAMA = "nama";
    public static final String LAMA_KERJA = "lama_kerja";
    public static final String PANGKAT = "nm_pangkat";
    public static final String ALAMAT = "alamat";
    public static final String JABATAN = "nm_jabatan";
    public static final String LOKASI = "nm_lokasi";
    public static final String GAJI = "gaji";

    public static final String CREATE_TB_PEGAWAI = "CREATE TABLE "+TB_NAME+"(" +
            NIK+" TEXT PRIMARY KEY , " +
            NAMA+" TEXT, "+
            LAMA_KERJA+" INTEGER, "+
            PANGKAT+" TEXT, "+
            ALAMAT+" TEXT, "+
            JABATAN+" TEXT, "+
            LOKASI+" TEXT, "+
            GAJI+" INTEGER)";

}
