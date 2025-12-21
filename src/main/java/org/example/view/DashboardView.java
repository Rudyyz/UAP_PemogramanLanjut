package org.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("DASHBOARD INVENTARIS");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== ROOT =====
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(240, 244, 249));
        root.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        JLabel title = new JLabel("🛍️ Dashboard Inventaris");
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

        // ===== BOTTOM CARD (CENTERED) =====
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

    // ===== CARD COMPONENT =====
    private JPanel createCard(String icon, String text, java.awt.event.ActionListener action) {
        RoundedPanel card = new RoundedPanel(35);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(230, 140));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel lblIcon = new JLabel(icon, SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblText.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblIcon);
        card.add(Box.createVerticalStrut(12));
        card.add(lblText);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                action.actionPerformed(null);
            }

            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(225, 235, 255));
                card.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBackground(Color.WHITE);
                card.repaint();
            }
        });

        return card;
    }

    // ===== ROUNDED PANEL =====
    class RoundedPanel extends JPanel {
        private final int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2.setColor(getBackground());
            g2.fillRoundRect(
                    0, 0, getWidth(), getHeight(),
                    radius, radius
            );
            super.paintComponent(g);
        }
    }
}
