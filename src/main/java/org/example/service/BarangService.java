package org.example.service;

import org.example.model.Barang;
import org.example.util.FileUtil;

import java.util.List;

/**
 * Class BarangService digunakan untuk mengelola
 * data barang dalam aplikasi inventaris.
 */
public class BarangService {

    /** Menyimpan daftar data barang */
    private List<Barang> data = FileUtil.read();

    /**
     * Mengambil seluruh data barang.
     *
     * @return daftar barang
     */
    public List<Barang> getAll() {
        return data;
    }

    /**
     * Menambahkan data barang baru.
     *
     * @param b objek barang yang akan ditambahkan
     */
    public void addBarang(Barang b) {
        data.add(b);
        FileUtil.write(data);
    }

    /**
     * Mengubah data barang berdasarkan indeks.
     *
     * @param index posisi data yang akan diubah
     * @param b data barang baru
     */
    public void updateBarang(int index, Barang b) {
        data.set(index, b);
        FileUtil.write(data);
    }

    /**
     * Menghapus data barang berdasarkan indeks.
     *
     * @param index posisi data yang akan dihapus
     */
    public void deleteBarang(int index) {
        data.remove(index);
        FileUtil.write(data);
    }
}
