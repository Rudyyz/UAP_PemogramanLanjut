package org.example.model;

import java.time.LocalDate;

/**
 * Class Barang digunakan untuk menyimpan data barang
 * dalam aplikasi inventaris.
 */
public class Barang {

    /** Kode unik barang */
    private String kode;

    /** Nama barang */
    private String nama;

    /** Jumlah stok barang */
    private int stok;

    /** Harga barang */
    private double harga;

    /** Tanggal barang masuk */
    private LocalDate tanggalMasuk;

    /**
     * Membuat objek Barang baru.
     *
     * @param kode kode barang
     * @param nama nama barang
     * @param stok jumlah stok
     * @param harga harga barang
     * @param tanggalMasuk tanggal masuk barang
     */
    public Barang(String kode, String nama, int stok, double harga, LocalDate tanggalMasuk) {
        this.kode = kode;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.tanggalMasuk = tanggalMasuk;
    }

    /** @return kode barang */
    public String getKode() {
        return kode;
    }

    /** @return nama barang */
    public String getNama() {
        return nama;
    }

    /** @return jumlah stok barang */
    public int getStok() {
        return stok;
    }

    /** @return harga barang */
    public double getHarga() {
        return harga;
    }

    /** @return tanggal masuk barang */
    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }
}
