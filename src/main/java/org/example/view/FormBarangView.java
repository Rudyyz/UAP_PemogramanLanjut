package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FormBarangView extends JFrame {

    private JTextField kode,nama,stok,harga;
    private BarangService service = new BarangService();
    private int index = -1;

    public FormBarangView() {
        this(null,-1);
    }

    public FormBarangView(Barang b, int idx) {
        index = idx;

        setTitle("Form Barang");
        setSize(350,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        kode = new JTextField();
        nama = new JTextField();
        stok = new JTextField();
        harga = new JTextField();

        if(b!=null){
            kode.setText(b.getKode());
            nama.setText(b.getNama());
            stok.setText(String.valueOf(b.getStok()));
            harga.setText(String.valueOf(b.getHarga()));
        }

        JButton simpan = new JButton("Simpan");
        JButton back = new JButton("Kembali");

        simpan.addActionListener(e -> {
            Barang barang = new Barang(
                    kode.getText(),
                    nama.getText(),
                    Integer.parseInt(stok.getText()),
                    Double.parseDouble(harga.getText()),
                    LocalDate.now()
            );
            if(index==-1) service.addBarang(barang);
            else service.updateBarang(index, barang);

            new ListBarangView().setVisible(true);
            dispose();
        });

        back.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        JPanel p = new JPanel(new GridLayout(5,2,10,10));
        p.add(new JLabel("Kode")); p.add(kode);
        p.add(new JLabel("Nama")); p.add(nama);
        p.add(new JLabel("Stok")); p.add(stok);
        p.add(new JLabel("Harga")); p.add(harga);
        p.add(simpan); p.add(back);

        add(p);
    }
}
