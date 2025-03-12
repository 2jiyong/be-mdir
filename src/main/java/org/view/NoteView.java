package org.view;

import org.content.TextContent;
import org.reader.TextReader;
import org.writer.TextWriter;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class NoteView {
    private final JFrame frame;
    private final JTextArea textArea;
    private final JButton loadButton;
    private final JButton saveButton;
    private final TextReader textReader;
    private final TextWriter textWriter;
    private Optional<Path> currentPath;

    public NoteView(TextReader textReader, TextWriter textWriter) {
        this.textReader = textReader;
        this.textWriter = textWriter;
        currentPath = Optional.empty();

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
        saveButton.addActionListener(e -> addSaveButtons());

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
            currentPath = Optional.of(filePath);
            text.ifPresent(this::setText);
        }
    }

    private void addSaveButtons() {
        String[] options = {"저장하기", "다른 이름으로 저장하기"}; // 버튼 이름 지정
        int choice = JOptionPane.showOptionDialog(
                null,
                "어떤 작업을 할까요?", // 메시지 내용
                "선택하세요", // 다이얼로그 제목
                JOptionPane.DEFAULT_OPTION, // 옵션 타입
                JOptionPane.INFORMATION_MESSAGE, // 아이콘 타입
                null, // 아이콘 (null이면 기본)
                options, // 버튼 리스트
                options[0] // 기본 선택 버튼
        );

        if (choice == 0) {
            setSaveButton();
        } else if (choice == 1) {
            setSaveOtherNameButton();
        }
    }

    private void setSaveButton(){
        if (currentPath.isPresent()) {
            textWriter.write(currentPath.get(), getTextByTextContent());
        } else{
            JOptionPane.showMessageDialog(
                    null,
                    "잘못됨"
            );
        }
    }

    private void setSaveOtherNameButton(){
        String title = JOptionPane.showInputDialog(
                null,
                "저장할 제목을 입력하세요:",
                "파일 저장하기",
                JOptionPane.PLAIN_MESSAGE
        );
        if (title != null && !title.trim().isEmpty()) {
            textWriter.write(Paths.get(title), getTextByTextContent());
        }
    }

    private void setText(TextContent textContent) {
        SwingUtilities.invokeLater(() -> textArea.setText(textContent.getText()));
    }

    private TextContent getTextByTextContent() {
        return new TextContent(textArea.getText());
    }
}
