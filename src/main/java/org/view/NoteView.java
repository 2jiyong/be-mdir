package org.view;

import javax.swing.*;

public class NoteView {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("메모장");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 400);

            JTextArea textArea = new JTextArea(5, 20);

            frame.add(new JScrollPane(textArea));

            frame.setVisible(true);
        });
    }
}
