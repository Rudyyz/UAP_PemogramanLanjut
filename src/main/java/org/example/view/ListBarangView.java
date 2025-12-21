package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;
import org.example.util.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListBarangView extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private BarangService service = new BarangService();

    public ListBarangView() {
        setTitle("DATA BARANG");
        setSize(950, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(240, 244, 249));

        // ================= HEADER BIRU =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIStyle.PRIMARY);
        header.setBorder(new EmptyBorder(12, 16, 12, 16));

        JButton btnBackTop = new JButton("←");
        btnBackTop.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnBackTop.setForeground(Color.WHITE);
        btnBackTop.setBackground(UIStyle.PRIMARY);
        btnBackTop.setBorderPainted(false);
        btnBackTop.setFocusPainted(false);
        btnBackTop.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        JLabel lblTitle = new JLabel("Data Barang");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftHeader.setOpaque(false);
        leftHeader.add(btnBackTop);
        leftHeader.add(lblTitle);

        header.add(leftHeader, BorderLayout.WEST);

        // ================= SEARCH BAR =================
        JPanel searchPanel = new JPanel(new BorderLayout(8, 0));
        searchPanel.setOpaque(false);

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(220, 32));

        JButton btnSearch = new JButton("🔍 Cari");
        btnSearch.setBackground(Color.WHITE);
        btnSearch.addActionListener(e -> searchData());

        searchPanel.add(txtSearch, BorderLayout.CENTER);
        searchPanel.add(btnSearch, BorderLayout.EAST);

        header.add(searchPanel, BorderLayout.EAST);

        // ================= CARD PUTIH =================
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ================= TABEL =================
        model = new DefaultTableModel(
                new String[]{"Kode", "Nama", "Stok", "Harga", "Tanggal"}, 0
        );
        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setSelectionBackground(new Color(220, 235, 255));
        table.setShowGrid(false);

        loadData(service.getAll());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        card.add(scrollPane, BorderLayout.CENTER);

        // ================= TOMBOL AKSI =================
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        bottom.setOpaque(false);

        JButton btnEdit = createActionButton("✏ Edit", UIStyle.PRIMARY);
        JButton btnDelete = createActionButton("🗑 Hapus", new Color(220, 53, 69));
        JButton btnBack = createActionButton("← Kembali", new Color(108, 117, 125));

        btnEdit.addActionListener(e -> editData());
        btnDelete.addActionListener(e -> deleteData());
        btnBack.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        bottom.add(btnEdit);
        bottom.add(btnDelete);
        bottom.add(btnBack);

        card.add(bottom, BorderLayout.SOUTH);

        // ================= SUSUN LAYOUT =================
        root.add(header, BorderLayout.NORTH);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBorder(new EmptyBorder(20, 30, 20, 30));
        centerWrapper.setOpaque(false);
        centerWrapper.add(card, BorderLayout.CENTER);

        root.add(centerWrapper, BorderLayout.CENTER);

        setContentPane(root);
    }

    // ================= HELPER BUTTON =================
    private JButton createActionButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return btn;
    }

    // ================= LOAD DATA =================
    private void loadData(List<Barang> list) {
        model.setRowCount(0);
        for (Barang b : list) {
            model.addRow(new Object[]{
                    b.getKode(),
                    b.getNama(),
                    b.getStok(),
                    b.getHarga(),
                    b.getTanggalMasuk()
            });
        }
    }

    // ================= SEARCH =================
    private void searchData() {
        String keyword = txtSearch.getText().toLowerCase();
        loadData(
                service.getAll().stream()
                        .filter(b -> b.getNama().toLowerCase().contains(keyword))
                        .toList()
        );
    }

    // ================= DELETE =================
    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            service.deleteBarang(row);
            loadData(service.getAll());
        }
    }

    // ================= EDIT =================
    private void editData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
            return;
        }

        Barang barang = service.getAll().get(row);
        new FormBarangView(barang, row).setVisible(true);
        dispose();
    }
}