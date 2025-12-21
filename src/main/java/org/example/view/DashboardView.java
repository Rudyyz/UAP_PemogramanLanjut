package org.example.view;

import org.example.util.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("Dashboard Inventaris");
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

        JLabel title = new JLabel("🛍️ Dashboard Inventaris", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Kelola data inventaris barang", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(Color.DARK_GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);

        // ===== CARD CONTAINER =====
        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setOpaque(false);
        cards.setBorder(new EmptyBorder(30, 40, 20, 40));

        // ===== CARDS =====
        cards.add(createCard(
                "📋",
                "Data Barang",
                e -> {
                    new ListBarangView().setVisible(true);
                    dispose();
                }
        ));

        cards.add(createCard(
                "➕",
                "Tambah Barang",
                e -> {
                    new FormBarangView().setVisible(true);
                    dispose();
                }
        ));

        cards.add(createCard(
                "📊",
                "Laporan",
                e -> {
                    new LaporanView().setVisible(true);
                    dispose();
                }
        ));

        // Placeholder biar grid rapi (2x2)
        cards.add(new JLabel());

        root.add(header, BorderLayout.NORTH);
        root.add(cards, BorderLayout.CENTER);

        setContentPane(root);
    }

    // ===== CARD BUTTON =====
    private JPanel createCard(String icon, String text, java.awt.event.ActionListener action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(25, 25, 25, 25));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Shadow effect
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel lblIcon = new JLabel(icon, SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblText.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblIcon);
        card.add(Box.createVerticalStrut(15));
        card.add(lblText);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                action.actionPerformed(null);
            }

            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(230, 240, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }
}
