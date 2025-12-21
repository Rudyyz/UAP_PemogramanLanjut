package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;
import org.example.util.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Class LaporanView digunakan untuk menampilkan
 * ringkasan laporan inventaris barang.
 *
 * Laporan yang ditampilkan meliputi total jenis barang
 * dan total stok barang.
 */
public class LaporanView extends JFrame {

    private BarangService service = new BarangService();

    /**
     * Konstruktor LaporanView untuk
     * menginisialisasi tampilan laporan inventaris.
     */
    public LaporanView() {
        setTitle("LAPORAN INVENTARIS");
        setSize(750, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(240, 244, 249));

        /* ================= HEADER ================= */
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIStyle.PRIMARY);
        header.setBorder(new EmptyBorder(18, 30, 18, 30));

        JLabel lblTitle = new JLabel("Laporan Inventaris");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));

        header.add(lblTitle, BorderLayout.WEST);

        /* ================= CARD ================= */
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(30, 30, 20, 30));

        /* ================= TABLE ================= */
        String[] kolom = {"Keterangan", "Jumlah"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));

        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table.getTableHeader().setBorder(
                BorderFactory.createLineBorder(Color.BLACK)
        );

        table.setSelectionBackground(new Color(220, 235, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setForeground(Color.BLACK);
        table.setFillsViewportHeight(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        /* ================= DATA ================= */
        List<Barang> list = service.getAll();
        int totalBarang = list.size();
        int totalStok = list.stream().mapToInt(Barang::getStok).sum();

        model.addRow(new Object[]{"Total Jenis Barang", totalBarang});
        model.addRow(new Object[]{"Total Stok Barang", totalStok});

        card.add(scrollPane, BorderLayout.CENTER);

        /* ================= BUTTON ================= */
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 15));
        bottom.setOpaque(false);

        JButton btnBack = new JButton("Kembali");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setPreferredSize(new Dimension(170, 48));
        btnBack.setBackground(new Color(108, 117, 125));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnBack.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        bottom.add(btnBack);
        card.add(bottom, BorderLayout.SOUTH);

        /* ================= WRAPPER ================= */
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(new EmptyBorder(25, 35, 25, 35));
        centerWrapper.add(card, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(centerWrapper, BorderLayout.CENTER);

        setContentPane(root);
    }
}
