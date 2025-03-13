package org.view;

import org.content.FileDirectory;
import org.content.TextContent;
import org.reader.TextReader;
import org.writer.TextWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileManagerView extends JFrame {
    private FileDirectory leftDirectory;
    private FileDirectory rightDirectory;
    private JList<File> leftFileList;
    private JList<File> rightFileList;
    private File currentDirectory;

    public FileManagerView() {
        super("파일 탐색기");

        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        leftDirectory = new FileDirectory();
        rightDirectory = new FileDirectory();
        currentDirectory = FileDirectory.getRootDirectory();

        leftFileList = new JList<>(leftDirectory.getAllFiles().toArray(new File[0]));
        rightFileList = new JList<>(rightDirectory.getAllFiles().toArray(new File[0]));

        leftFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane leftScrollPane = new JScrollPane(leftFileList);
        JScrollPane rightScrollPane = new JScrollPane(rightFileList);

        // 버튼 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton moveLeftButton = new JButton("← 왼쪽 이동");
        JButton moveRightButton = new JButton("오른쪽 이동 →");
        buttonPanel.add(moveLeftButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // 간격 추가
        buttonPanel.add(moveRightButton);


        JSplitPane leftRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScrollPane, rightScrollPane);
        leftRightSplitPane.setDividerLocation(450); // 중간에 나누기 (창 크기 따라 자동 조정)

        // 1,2번 패널과 3번(버튼)을 조합
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftRightSplitPane, buttonPanel);
        mainSplitPane.setDividerLocation(850); // 오른쪽 버튼 패널을 작게 설정

        addEventToList();

        add(mainSplitPane);

        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }

    private void updateFileList(File file) {
        if(FileDirectory.isRootDirectory(file)) leftDirectory.setDirectory(file);
        else leftDirectory.setDirectory(file.getParentFile());
        rightDirectory.setDirectory(file);

        leftFileList.setListData(leftDirectory.getAllFiles().toArray(new File[0]));
        rightFileList.setListData(rightDirectory.getAllFiles().toArray(new File[0]));

        renew(leftFileList);
        renew(rightFileList);
    }

    private void addEventToList(){
        addDoubleClickEvent(leftFileList);
        addDoubleClickEvent(rightFileList);
    }

    private void addDoubleClickEvent(JList<File> fileList) {
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블 클릭 감지
                    File selectedFile = fileList.getSelectedValue();
                    if (selectedFile != null) {
                        updateFileList(selectedFile);
                    }
                }
            }
        });
    }

    private void renew(JList<File> list) {
        list.repaint();
        list.revalidate();
    }
}
