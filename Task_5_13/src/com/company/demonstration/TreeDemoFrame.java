package com.company.demonstration;

import com.company.BinaryTree;
import com.company.BinaryTreePainter;
import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class TreeDemoFrame extends JFrame {

    private JPanel panelMain;
    private JPanel panelPaintArea;
    private JPanel paintPanel = null;

    private JButton buttonMakeTree;
    private JButton buttonRandomGenerate;

    private JSplitPane splitPaneMain;
    private JTextField textFieldValues;
    private JSpinner spinnerRandomCount;

    private JFileChooser fileChooserSave;

    BinaryTree<Integer> tree = new SimpleBinaryTree<>();

    public TreeDemoFrame() {
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        splitPaneMain.setDividerLocation(0.5);
        splitPaneMain.setResizeWeight(1.0);
        splitPaneMain.setBorder(null);

        paintPanel = new JPanel() {
            private Dimension paintSize = new Dimension(0, 0);

            @Override
            public void paintComponent(Graphics gr) {
                super.paintComponent(gr);
                paintSize = BinaryTreePainter.paint(tree, gr);
                if (!paintSize.equals(this.getPreferredSize())) {
                    SwingUtils.setFixedSize(this, paintSize.width, paintSize.height);
                }
            }
        };
        JScrollPane paintJScrollPane = new JScrollPane(paintPanel);
        panelPaintArea.add(paintJScrollPane);

        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("./images"));
        FileFilter filter = new FileNameExtensionFilter("SVG images", "svg");
        fileChooserSave.addChoosableFileFilter(filter);
        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        spinnerRandomCount.setValue(30);

        buttonMakeTree.addActionListener(actionEvent -> {
            try {
                SimpleBinaryTree<Integer> tree = new SimpleBinaryTree<>(Integer::parseInt);
                this.tree = tree;
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });

        buttonRandomGenerate.addActionListener(actionEvent -> {
            int size = ((Integer) spinnerRandomCount.getValue()).intValue();
            int[] arr = ArrayUtils.createRandomIntArray(size, (size <= 50) ? 100 : 1000);
            textFieldValues.setText(ArrayUtils.toString(arr));
        });
            try {
                int[] arr = ArrayUtils.toIntArray(textFieldValues.getText());
                Arrays.sort(arr);
                textFieldValues.setText(ArrayUtils.toString(arr));
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
    }
}
