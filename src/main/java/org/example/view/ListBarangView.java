package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListBarangView extends JFrame {

    private BarangService service = new BarangService();
    private DefaultTableModel model;
    private JTable table;
    private JTextField txtSearch;

    public ListBarangView() {
        setTitle("Data Barang");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"Kode","Nama","Stok","Harga","Tanggal"},0);
        table = new JTable(model);
        loadData(service.getAll());

        txtSearch = new JTextField(15);
        JButton btnCari = new JButton("Cari");
        JButton btnSortNama = new JButton("Sort Nama");
        JButton btnSortStok = new JButton("Sort Stok");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnBack = new JButton("Kembali");

        btnCari.addActionListener(e -> loadData(service.searchByNama(txtSearch.getText())));
        btnSortNama.addActionListener(e -> { service.sortByNama(); loadData(service.getAll()); });
        btnSortStok.addActionListener(e -> { service.sortByStok(); loadData(service.getAll()); });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row==-1) return;
            new FormBarangView(service.getAll().get(row), row).setVisible(true);
            dispose();
        });

        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row==-1) return;
            service.deleteBarang(row);
            loadData(service.getAll());
        });

        btnBack.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Cari:"));
        top.add(txtSearch);
        top.add(btnCari);

        JPanel bottom = new JPanel();
        bottom.add(btnSortNama);
        bottom.add(btnSortStok);
        bottom.add(btnEdit);
        bottom.add(btnHapus);
        bottom.add(btnBack);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void loadData(List<Barang> list) {
        model.setRowCount(0);
        for (Barang b : list) {
            model.addRow(new Object[]{
                    b.getKode(), b.getNama(), b.getStok(), b.getHarga(), b.getTanggalMasuk()
            });
        }
    }
}
