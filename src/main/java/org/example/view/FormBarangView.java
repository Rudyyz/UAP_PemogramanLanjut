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
        setTitle("FORM BARANG");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== ROOT =====
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(235, 240, 248));

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIStyle.PRIMARY);
        header.setPreferredSize(new Dimension(0, 60));

        JLabel title = new JLabel("Form Barang");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(0, 20, 0, 0));

        header.add(title, BorderLayout.WEST);

        // ===== FORM =====
        JPanel form = new JPanel(new GridLayout(4, 2, 20, 20));
        form.setOpaque(false);
        form.setBorder(new EmptyBorder(30, 60, 30, 60));

        kode = createField();
        nama = createField();
        stok = createField();
        harga = createField();
        harga.setText("Rp. ");

        form.add(createLabel("Kode"));
        form.add(kode);
        form.add(createLabel("Nama"));
        form.add(nama);
        form.add(createLabel("Stok"));
        form.add(stok);
        form.add(createLabel("Harga"));
        form.add(harga);

        // ===== BUTTON =====
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSimpan.setBackground(UIStyle.PRIMARY);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        btnSimpan.setPreferredSize(new Dimension(180, 44));
        btnSimpan.addActionListener(e -> simpan());

        JButton btnKembali = new JButton("Kembali");
        btnKembali.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKembali.setBackground(new Color(120, 120, 120));
        btnKembali.setForeground(Color.WHITE);
        btnKembali.setFocusPainted(false);
        btnKembali.setPreferredSize(new Dimension(180, 44));
        btnKembali.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottom.setOpaque(false);
        bottom.add(btnSimpan);
        bottom.add(btnKembali);

        root.add(header, BorderLayout.NORTH);
        root.add(form, BorderLayout.CENTER);
        root.add(bottom, BorderLayout.SOUTH);

        setContentPane(root);
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

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        return lbl;
    }
    private JTextField createField() {
        RoundedTextField tf = new RoundedTextField(22);
        tf.setPreferredSize(new Dimension(280, 40));
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        return tf;
    }

    class RoundedTextField extends JTextField {

        private final int radius;
        public RoundedTextField(int radius) {
            this.radius = radius;
            setOpaque(false);
            setBorder(new EmptyBorder(8, 14, 8, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(Color.BLACK);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        }
    }
}
