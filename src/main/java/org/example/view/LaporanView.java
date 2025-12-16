package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LaporanView extends JFrame {

    public LaporanView() {
        BarangService s = new BarangService();
        List<Barang> l = s.getAll();

        int total = l.size();
        int stok = l.stream().mapToInt(Barang::getStok).sum();

        setTitle("Laporan");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea area = new JTextArea(
                "Total Jenis Barang : "+total+"\n"+
                        "Total Stok Barang  : "+stok
        );
        area.setEditable(false);

        JButton back = new JButton("Kembali");
        back.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        add(area, BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);
    }
}
