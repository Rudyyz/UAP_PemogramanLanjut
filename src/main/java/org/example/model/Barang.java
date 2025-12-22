package org.example.model;

import java.time.LocalDate;

public class Barang {
    private String kode;
    private String nama;
    private int stok;
    private double harga;
    private LocalDate tanggalMasuk;


    public Barang(String kode, String nama, int stok, double harga, LocalDate tanggalMasuk) {
        this.kode = kode;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.tanggalMasuk = tanggalMasuk;
    }
    public String getKode() {
        return kode;
    }
    public String getNama() {
        return nama;
    }
    public int getStok() {
        return stok;
    }
    public double getHarga() {
        return harga;
    }
    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }
}
