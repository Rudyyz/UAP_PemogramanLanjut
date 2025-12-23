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

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIStyle.PRIMARY);
        header.setBorder(new EmptyBorder(16, 24, 16, 24));

        JLabel lblTitle = new JLabel("Data Barang");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(lblTitle, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        searchPanel.setOpaque(false);

        txtSearch = new JTextField("Nama Barang");
        txtSearch.setPreferredSize(new Dimension(260, 38));
        txtSearch.setForeground(Color.GRAY);

        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Nama Barang")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setText("Nama Barang");
                    txtSearch.setForeground(Color.GRAY);
                }
            }
        });

        JButton btnSearch = new JButton("Cari");
        btnSearch.setPreferredSize(new Dimension(90, 38));
        btnSearch.addActionListener(e -> searchData());

        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        header.add(searchPanel, BorderLayout.EAST);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        model = new DefaultTableModel(
                new String[]{"Kode", "Nama", "Stok", "Harga", "Tanggal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(36);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setSelectionBackground(new Color(220, 235, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);

        loadData(service.getAll());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        card.add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 12));
        bottom.setOpaque(false);

        JButton btnEdit = createButton("Edit", UIStyle.PRIMARY);
        JButton btnDelete = createButton("Hapus", new Color(220, 53, 69));
        JButton btnBack = createButton("Kembali", new Color(108, 117, 125));

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
        centerWrapper.setBorder(new EmptyBorder(20, 25, 20, 25));
        centerWrapper.setOpaque(false);
        centerWrapper.add(card, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(centerWrapper, BorderLayout.CENTER);
        setContentPane(root);
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setPreferredSize(new Dimension(160, 45));
        btn.setFocusPainted(false);
        return btn;
    }

    private void loadData(List<Barang> list) {
        model.setRowCount(0);

        list.stream()
                .sorted((a, b) -> a.getKode().compareToIgnoreCase(b.getKode()))
                .forEach(b -> model.addRow(new Object[]{
                        b.getKode(),
                        b.getNama(),
                        b.getStok(),
                        b.getHarga(),
                        b.getTanggalMasuk()
                }));
    }

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
