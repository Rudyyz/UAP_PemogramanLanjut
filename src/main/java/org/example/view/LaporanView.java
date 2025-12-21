package org.example.view;

import org.example.service.BarangService;

import javax.swing.*;
import java.awt.*;

public class LaporanView extends JFrame {

    public LaporanView() {
        setTitle("Laporan Inventaris");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BarangService s = new BarangService();

        JTextArea area = new JTextArea(
                "Total Barang : " + s.getAll().size()
        );
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton back = new JButton("⬅ Kembali");
        back.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        add(area, BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);
    }
}
