package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;
import org.example.util.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class ListBarangView extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch;
    private BarangService service = new BarangService();

    public ListBarangView() {
        setTitle("DATA BARANG");
        setSize(1050, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(240, 244, 249));

        /* ================= HEADER ================= */
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIStyle.PRIMARY);
        header.setBorder(new EmptyBorder(14, 20, 14, 20));

        JButton btnBackTop = new JButton("←");
        btnBackTop.setFont(new Font("Segoe UI", Font.BOLD, 26));
        btnBackTop.setForeground(Color.WHITE);
        btnBackTop.setBackground(UIStyle.PRIMARY);
        btnBackTop.setBorderPainted(false);
        btnBackTop.setFocusPainted(false);
        btnBackTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBackTop.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        JLabel lblTitle = new JLabel("Data Barang");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        leftHeader.setOpaque(false);
        leftHeader.add(btnBackTop);
        leftHeader.add(lblTitle);

        header.add(leftHeader, BorderLayout.WEST);

        /* ================= SEARCH ================= */
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        searchPanel.setOpaque(false);

        txtSearch = new JTextField("nama barang");
        txtSearch.setPreferredSize(new Dimension(260, 36));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSearch.setForeground(Color.GRAY);

        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("nama barang")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText("nama barang");
                    txtSearch.setForeground(Color.GRAY);
                }
            }
        });

        JButton btnSearch = new JButton("🔍 Cari");
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(e -> searchData());

        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        header.add(searchPanel, BorderLayout.EAST);

        /* ================= CARD ================= */
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        /* ================= TABLE ================= */
        model = new DefaultTableModel(
                new String[]{"Kode", "Nama", "Stok", "Harga", "Tanggal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // NON EDITABLE
            }
        };

        table = new JTable(model);
        table.setRowHeight(36);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setSelectionBackground(new Color(220, 235, 255));
        table.setSelectionForeground(Color.BLACK); // FIX BURAM
        table.setForeground(Color.BLACK);
        table.setShowGrid(false);
        table.setFillsViewportHeight(true);

        loadData(service.getAll());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        card.add(scrollPane, BorderLayout.CENTER);

        /* ================= BUTTON BAWAH ================= */
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        bottom.setOpaque(false);

        JButton btnEdit = createButton("✏ Edit", UIStyle.PRIMARY);
        JButton btnDelete = createButton("🗑 Hapus", new Color(220, 53, 69));
        JButton btnBack = createButton("← Kembali", new Color(108, 117, 125));

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

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.setBorder(new EmptyBorder(20, 25, 20, 25));
        centerWrapper.add(card, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(centerWrapper, BorderLayout.CENTER);

        setContentPane(root);
    }

    /* ================= HELPER ================= */
    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        return btn;
    }

    /* ================= LOAD DATA ================= */
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

    /* ================= SEARCH ================= */
    private void searchData() {
        String keyword = txtSearch.getText().toLowerCase();
        if (keyword.equals("nama barang")) keyword = "";

        String finalKeyword = keyword;
        loadData(
                service.getAll().stream()
                        .filter(b -> b.getNama().toLowerCase().contains(finalKeyword))
                        .toList()
        );
    }

    /* ================= DELETE ================= */
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

    /* ================= EDIT ================= */
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
