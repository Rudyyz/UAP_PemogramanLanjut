package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FormBarangView extends JFrame {

    private JTextField kode, nama, stok, harga;
    private BarangService service = new BarangService();
    private int index = -1;

    public FormBarangView() {
        setTitle("Form Barang");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        kode = new JTextField();
        nama = new JTextField();
        stok = new JTextField();
        harga = new JTextField();

        JPanel form = new JPanel(new GridLayout(4,2,10,10));
        form.add(new JLabel("Kode")); form.add(kode);
        form.add(new JLabel("Nama")); form.add(nama);
        form.add(new JLabel("Stok")); form.add(stok);
        form.add(new JLabel("Harga")); form.add(harga);

        JButton simpan = new JButton("Simpan");
        simpan.addActionListener(e -> simpan());

        add(form, BorderLayout.CENTER);
        add(simpan, BorderLayout.SOUTH);
    }

    public FormBarangView(Barang b, int index) {
        this();
        this.index = index;
        kode.setText(b.getKode());
        nama.setText(b.getNama());
        stok.setText(String.valueOf(b.getStok()));
        harga.setText(String.valueOf(b.getHarga()));
    }

    private void simpan() {
        Barang b = new Barang(
                kode.getText(),
                nama.getText(),
                Integer.parseInt(stok.getText()),
                Double.parseDouble(harga.getText()),
                LocalDate.now()
        );

        if (index == -1) service.addBarang(b);
        else service.updateBarang(index, b);

        new ListBarangView().setVisible(true);
        dispose();
    }
}
