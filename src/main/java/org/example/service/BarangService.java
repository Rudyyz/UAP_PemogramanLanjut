package org.example.service;

import org.example.model.Barang;
import org.example.util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class BarangService {

    private List<Barang> list;

    public BarangService() {
        list = new ArrayList<>(FileUtil.readBarang());
    }

    public List<Barang> getAll() {
        return list;
    }

    public void addBarang(Barang b) {
        list.add(b);
        FileUtil.writeBarang(list);
    }

    public void updateBarang(int index, Barang b) {
        list.set(index, b);
        FileUtil.writeBarang(list);
    }

    public void deleteBarang(int index) {
        list.remove(index);
        FileUtil.writeBarang(list);
    }

    public List<Barang> searchByNama(String key) {
        return list.stream()
                .filter(b -> b.getNama().toLowerCase().contains(key.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void sortByNama() {
        list.sort(Comparator.comparing(Barang::getNama));
    }

    public void sortByStok() {
        list.sort(Comparator.comparingInt(Barang::getStok));
    }
}
