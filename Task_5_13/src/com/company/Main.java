package com.company;

import com.company.demonstration.TreeDemoFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        ru.vsu.cs.util.SwingUtils.setDefaultFont("Arial", 20);

        EventQueue.invokeLater(() -> {
            try {
                JFrame frameMain = new TreeDemoFrame();
                frameMain.setVisible(true);
                frameMain.setExtendedState(MAXIMIZED_BOTH);
            } catch (Exception ex) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(ex);
            }
        });
    }
}
