package org.example.view;

import org.example.model.Barang;
import org.example.service.BarangService;
import org.example.util.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class FormBarangView extends JFrame {

    private JTextField kode, nama, stok, harga;
    private BarangService service = new BarangService();
    private int index = -1;

    public FormBarangView() {
        setTitle("Form Barang");
        setSize(520, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== ROOT =====
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(220, 225, 235));
        root.setBorder(new EmptyBorder(30, 30, 30, 30));

        // ===== CARD =====
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 25, 20, 25));

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("📦  Form Barang");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton btnClose = new JButton("✕");
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        header.add(title, BorderLayout.WEST);
        header.add(btnClose, BorderLayout.EAST);

        // ===== FORM FIELD =====
        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new GridLayout(4, 2, 15, 15));
        form.setBorder(new EmptyBorder(20, 10, 20, 10));

        kode = createField();
        nama = createField();
        stok = createField();
        harga = createField();
        harga.setText("Rp. ");

        form.add(new JLabel("Kode"));
        form.add(kode);
        form.add(new JLabel("Nama"));
        form.add(nama);
        form.add(new JLabel("Stok"));
        form.add(stok);
        form.add(new JLabel("Harga"));
        form.add(harga);

        // ===== BUTTON SIMPAN =====
        JButton btnSimpan = new JButton("💾  Simpan");
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSimpan.setBackground(UIStyle.PRIMARY);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setPreferredSize(new Dimension(160, 40));

        btnSimpan.addActionListener(e -> simpan());

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(btnSimpan);

        // ===== SUSUN =====
        card.add(header, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        root.add(card, BorderLayout.CENTER);
        setContentPane(root);
    }

    // ===== CONSTRUCTOR EDIT =====
    public FormBarangView(Barang b, int index) {
        this();
        this.index = index;

        kode.setText(b.getKode());
        nama.setText(b.getNama());
        stok.setText(String.valueOf(b.getStok()));
        harga.setText(String.valueOf(b.getHarga()));
    }

    // ===== SIMPAN =====
    private void simpan() {
        try {
            Barang b = new Barang(
                    kode.getText(),
                    nama.getText(),
                    Integer.parseInt(stok.getText()),
                    Double.parseDouble(harga.getText().replace("Rp.", "").trim()),
                    LocalDate.now()
            );

            if (index == -1) service.addBarang(b);
            else service.updateBarang(index, b);

            new ListBarangView().setVisible(true);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Pastikan semua field diisi dengan benar!");
        }
    }

    // ===== FIELD STYLE =====
    private JTextField createField() {
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(250, 36));
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return tf;
    }
}
