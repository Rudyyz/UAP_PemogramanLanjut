package org.example.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("Dashboard - Inventaris Barang");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel("APLIKASI INVENTARIS BARANG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));

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

        JPanel panelButton = new JPanel(new GridLayout(3, 1, 10, 10));
        panelButton.add(btnList);
        panelButton.add(btnTambah);
        panelButton.add(btnLaporan);

        add(lblTitle, BorderLayout.NORTH);
        add(panelButton, BorderLayout.CENTER);
    }
}
