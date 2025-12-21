package org.example.service;

import org.example.model.Barang;
import org.example.util.FileUtil;

import java.util.List;

public class BarangService {
    private List<Barang> data = FileUtil.read();

    public List<Barang> getAll() {
        return data;
    }

    public void addBarang(Barang b) {
        data.add(b);
        FileUtil.write(data);
    }

    public void updateBarang(int index, Barang b) {
        data.set(index, b);
        FileUtil.write(data);
    }

    public void deleteBarang(int index) {
        data.remove(index);
        FileUtil.write(data);
    }
}
