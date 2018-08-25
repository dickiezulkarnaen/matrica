package com.example.dickiez.matricatest;

public class TableLokasiContract {
    public static final String TB_NAME = "TB_LOKASI";

    public static final String KD_LOKASI = "kd_lokasi";
    public static final String NM_LOKASI = "nm_lokasi";

    public static final String CREATE_TB_LOKASI = "CREATE TABLE "+TB_NAME+"(" +
            KD_LOKASI+" TEXT PRIMARY KEY , " +
            NM_LOKASI+" TEXT )";
}
