package org;

import org.content.TextContent;
import org.reader.TextReader;
import org.view.NoteView;
import org.writer.TextWriter;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class GUIMain {
    public static void main(String[] args) {
        Path basePath = Paths.get("files");
        TextReader reader = new TextReader();
        TextWriter writer = new TextWriter(basePath);

        NoteView noteView = new NoteView();
        noteView.addLoadButtonActionListener(e -> {
            // 파일 경로를 입력받는 다이얼로그 표시
            String filePath = JOptionPane.showInputDialog(
                    null,
                    "불러올 파일 경로를 입력하세요:",
                    "파일 불러오기",
                    JOptionPane.PLAIN_MESSAGE
            );
            if (filePath != null && !filePath.trim().isEmpty()) {
                Path filePath1 = Paths.get(filePath);
                Optional<TextContent> text = reader.read(filePath1);
                text.ifPresent(noteView::setText);
            }
        });
    }
}
