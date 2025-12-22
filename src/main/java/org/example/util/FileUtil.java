package org.example.util;

import org.example.model.Barang;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String FILE = "src/main/resources/barang.csv";

    public static List<Barang> readBarang() {
        List<Barang> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                list.add(new Barang(
                        d[0], d[1],
                        Integer.parseInt(d[2]),
                        Double.parseDouble(d[3]),
                        LocalDate.parse(d[4])
                ));
            }
        } catch (Exception e) {
            System.out.println("File belum ada / kosong");
        }
        return list;
    }

    public static void writeBarang(List<Barang> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Barang b : list) {
                bw.write(b.getKode()+","+b.getNama()+","+b.getStok()+","+b.getHarga()+","+b.getTanggalMasuk());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
