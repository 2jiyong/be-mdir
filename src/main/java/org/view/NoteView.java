package org.view;

import org.content.TextContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NoteView {
    private JFrame frame;
    private JTextArea textArea;
    private JButton loadButton;
    private JButton saveButton;

    public NoteView() {
        frame = new JFrame("메모장");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);

        textArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // 버튼 생성
        loadButton = new JButton("불러오기");
        saveButton = new JButton("저장하기");

        // 버튼 패널에 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // 세로 정렬
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.EAST);

        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }


    public void addText(TextContent textContent) {
        SwingUtilities.invokeLater(() -> textArea.append(textContent.getText()));
    }

    public void setText(TextContent textContent) {
        SwingUtilities.invokeLater(() -> textArea.setText(textContent.getText()));
    }

    // 불러오기 버튼에 액션 리스너를 등록하는 메서드
    public void addLoadButtonActionListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }

    // 저장하기 버튼에 액션 리스너를 등록하는 메서드
    public void addSaveButtonActionListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
}
