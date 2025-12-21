package org.example;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.view.DashboardView;

import javax.swing.*;

/**
 * Class Main merupakan titik awal (entry point)
 * dari aplikasi inventaris berbasis Java Swing.
 */
public class Main {

    /**
     * Method utama yang dijalankan saat aplikasi dimulai.
     *
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {

        // Mengatur tema tampilan aplikasi
        FlatLightLaf.setup();

        // Menjalankan tampilan utama aplikasi
        SwingUtilities.invokeLater(() ->
                new DashboardView().setVisible(true)
        );
    }
}
