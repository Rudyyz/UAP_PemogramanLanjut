package org.example.util;

import org.example.model.Barang;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class FileUtil digunakan untuk membaca dan menulis
 * data barang ke dalam file CSV.
 */
public class FileUtil {

    /** Lokasi file penyimpanan data barang */
    private static final String FILE = "src/main/resources/barang.csv";

    /**
     * Membaca data barang dari file CSV.
     *
     * @return daftar barang
     */
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
        } catch (Exception ignored) {
        }
        return list;
    }

    /**
     * Menyimpan data barang ke dalam file CSV.
     *
     * @param list daftar barang yang akan disimpan
     */
    public static void write(List<Barang> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Barang b : list) {
                pw.println(
                        b.getKode() + "," +
                                b.getNama() + "," +
                                b.getStok() + "," +
                                b.getHarga() + "," +
                                b.getTanggalMasuk()
                );
            }
        } catch (Exception ignored) {
        }
    }
}
