package org.example.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("Dashboard Inventaris");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("DASHBOARD INVENTARIS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnList = new JButton("Data Barang");
        JButton btnTambah = new JButton("Tambah Barang");
        JButton btnLaporan = new JButton("Laporan");

        btnList.addActionListener(e -> {
            new ListBarangView().setVisible(true);
            dispose();
        });

        btnTambah.addActionListener(e -> {
            new FormBarangView().setVisible(true);
            dispose();
        });

        btnLaporan.addActionListener(e -> {
            new LaporanView().setVisible(true);
            dispose();
        });

        JPanel panel = new JPanel(new GridLayout(3,1,10,10));
        panel.add(btnList);
        panel.add(btnTambah);
        panel.add(btnLaporan);

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }
}
