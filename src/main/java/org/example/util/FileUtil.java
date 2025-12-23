package org.example.util;

import org.example.model.Barang;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class FileUtil {
    private static final String FILE = "src/main/resources/barang.csv";
    public static List<Barang> read() {
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
        } catch (FileNotFoundException e) {
            System.err.println("File barang.csv tidak ditemukan, data kosong.");
        } catch (Exception e) {
            System.err.println("Format data barang tidak valid: " + e.getMessage());
        }
        return list;
    }

    public static void write(List<Barang> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Barang b : list) {
                pw.println(
                        b.getKode() + "," + b.getNama() + "," + b.getStok() + "," + b.getHarga() + "," + b.getTanggalMasuk()
                );
            }
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data barang: " + e.getMessage());
        }
    }
}
