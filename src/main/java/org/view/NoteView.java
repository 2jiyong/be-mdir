package org.view;

import org.content.TextContent;
import org.reader.TextReader;
import org.writer.TextWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class NoteView {
    private final JFrame frame;
    private final JTextArea textArea;
    private final JButton loadButton;
    private final JButton saveButton;
    private final TextReader textReader;
    private final TextWriter textWriter;

    public NoteView(TextReader textReader, TextWriter textWriter) {
        this.textReader = textReader;
        this.textWriter = textWriter;

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

        loadButton.addActionListener(e -> setLoadButton());


        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    private void setLoadButton(){
        String stringFilePath = JOptionPane.showInputDialog(
                null,
                "불러올 파일 경로를 입력하세요:",
                "파일 불러오기",
                JOptionPane.PLAIN_MESSAGE
        );
        if (stringFilePath != null && !stringFilePath.trim().isEmpty()) {
            Path filePath = Paths.get(stringFilePath);
            Optional<TextContent> text = textReader.read(filePath);
            text.ifPresent(this::setText);
        }
    }


    public void addText(TextContent textContent) {
        SwingUtilities.invokeLater(() -> textArea.append(textContent.getText()));
    }

    public void setText(TextContent textContent) {
        SwingUtilities.invokeLater(() -> textArea.setText(textContent.getText()));
    }
}
