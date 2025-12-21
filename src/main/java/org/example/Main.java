package org.example;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.view.DashboardView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() ->
                new DashboardView().setVisible(true)
        );
    }
}
