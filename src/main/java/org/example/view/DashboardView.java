package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Class DashboardView merupakan tampilan utama
 * (dashboard) dari aplikasi inventaris.
 *
 * Tampilan ini menyediakan menu navigasi
 * ke fitur Data Barang, Tambah Barang, dan Laporan.
 */
public class DashboardView extends JFrame {

    /**
     * Konstruktor DashboardView untuk
     * menginisialisasi dan menampilkan komponen UI dashboard.
     */
    public DashboardView() {
        setTitle("DASHBOARD INVENTARIS");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== ROOT PANEL =====
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(240, 244, 249));
        root.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel title = new JLabel("Dashboard Inventaris");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Kelola Data Inventaris Barang");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(Color.DARK_GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        // ===== CARD CONTAINER =====
        JPanel cards = new JPanel(new BorderLayout(20, 20));
        cards.setOpaque(false);
        cards.setBorder(new EmptyBorder(30, 40, 20, 40));

        // ===== TOP CARDS =====
        JPanel topCards = new JPanel(new GridLayout(1, 2, 20, 20));
        topCards.setOpaque(false);

        topCards.add(createCard(
                "📋",
                "Data Barang",
                e -> {
                    new ListBarangView().setVisible(true);
                    dispose();
                }
        ));

        topCards.add(createCard(
                "➕",
                "Tambah Barang",
                e -> {
                    new FormBarangView().setVisible(true);
                    dispose();
                }
        ));

        // ===== BOTTOM CARD =====
        JPanel bottomCards = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomCards.setOpaque(false);

        bottomCards.add(createCard(
                "📊",
                "Laporan",
                e -> {
                    new LaporanView().setVisible(true);
                    dispose();
                }
        ));

        cards.add(topCards, BorderLayout.NORTH);
        cards.add(bottomCards, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(cards, BorderLayout.CENTER);

        setContentPane(root);
    }

    /**
     * Membuat komponen kartu menu yang dapat diklik.
     *
     * @param icon ikon kartu
     * @param text teks menu
     * @param action aksi yang dijalankan saat kartu diklik
     * @return panel kartu
     */
    private JPanel createCard(String icon, String text, java.awt.event.ActionListener action) {
        RoundedPanel card = new RoundedPanel(35);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(230, 140));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblText.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblIcon);
        card.add(Box.createVerticalStrut(12));
        card.add(lblText);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                action.actionPerformed(null);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setHover(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setHover(false);
            }
        });

        return card;
    }

    /**
     * Panel khusus dengan sudut membulat
     * yang digunakan sebagai kartu menu.
     */
    class RoundedPanel extends JPanel {

        private final int radius;
        private boolean hover = false;

        /**
         * Konstruktor RoundedPanel.
         *
         * @param radius tingkat kelengkungan sudut panel
         */
        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        /**
         * Mengatur efek hover pada kartu.
         *
         * @param hover status hover
         */
        public void setHover(boolean hover) {
            this.hover = hover;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // Background
            g2.setColor(hover ? new Color(225, 235, 255) : Color.WHITE);
            g2.fillRoundRect(
                    0, 0,
                    getWidth(), getHeight(),
                    radius, radius
            );

            // Border
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(
                    1, 1,
                    getWidth() - 3,
                    getHeight() - 3,
                    radius, radius
            );

            super.paintComponent(g);
        }
    }
}
