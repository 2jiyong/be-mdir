package org.view;

import org.content.FileDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class FileManagerView extends JFrame {
    private FileDirectory leftDirectory;
    private FileDirectory rightDirectory;
    private JList<String> leftFileList;
    private JList<String> rightFileList;
    private JLabel leftPathLabel;
    private JLabel rightPathLabel;
    private JList<String> activeList; // 현재 선택된 리스트


    public FileManagerView() {
        super("파일 탐색기");

        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        leftDirectory = new FileDirectory();
        rightDirectory = new FileDirectory();

        List<String> leftStringList = leftDirectory.getAllString();
        List<String> rightStringList = rightDirectory.getAllString();
        leftStringList.add(0,FileDirectory.ROOT_DIRECTORY_PATH);

        leftFileList = new JList<>(leftStringList.toArray(new String[0]));
        rightFileList = new JList<>(rightStringList.toArray(new String[0]));

        leftFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane leftScrollPane = new JScrollPane(leftFileList);
        JScrollPane rightScrollPane = new JScrollPane(rightFileList);

        leftPathLabel = new JLabel(leftDirectory.getAbsolutePath());
        rightPathLabel = new JLabel(leftDirectory.getAbsolutePath());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(leftPathLabel, BorderLayout.NORTH);
        leftPanel.add(leftScrollPane, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(rightPathLabel, BorderLayout.NORTH);
        rightPanel.add(rightScrollPane, BorderLayout.CENTER);

        // 버튼 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JButton moveLeftButton = new JButton("← 왼쪽 이동");
        JButton moveRightButton = new JButton("오른쪽 이동 →");
        buttonPanel.add(moveLeftButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // 간격 추가
        buttonPanel.add(moveRightButton);
        moveLeftButton.addActionListener(e -> switchActiveList(leftFileList));
        moveRightButton.addActionListener(e -> switchActiveList(rightFileList));


        JSplitPane leftRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        leftRightSplitPane.setDividerLocation(450); // 중간에 나누기 (창 크기 따라 자동 조정)

        // 1,2번 패널과 3번(버튼)을 조합
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftRightSplitPane, buttonPanel);
        mainSplitPane.setDividerLocation(850); // 오른쪽 버튼 패널을 작게 설정

        addEventToList();
        addKeyEventToList();

        add(mainSplitPane);
        activeList = leftFileList; // 기본적으로 왼쪽 리스트를 활성화

        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }

    private void updateFileList(File file) {
        if(FileDirectory.isRootDirectory(file)) leftDirectory.setDirectory(file);
        else leftDirectory.setDirectory(file.getParentFile());
        rightDirectory.setDirectory(file);

        List<String> leftStringList = leftDirectory.getAllString();
        List<String> rightStringList = rightDirectory.getAllString();
        leftStringList.add(0,FileDirectory.ROOT_DIRECTORY_PATH);
        if (!FileDirectory.isRootDirectory(rightDirectory.getDirectory())) rightStringList.add(0,FileDirectory.PARENT_PATH);

        leftFileList.setListData(leftStringList.toArray(new String[0]));
        rightFileList.setListData(rightStringList.toArray(new String[0]));

        leftPathLabel.setText(leftDirectory.getAbsolutePath());
        rightPathLabel.setText(rightDirectory.getAbsolutePath());

        renew(leftFileList);
        renew(rightFileList);
        renew(leftPathLabel);
        renew(rightPathLabel);
    }

    private void addEventToList(){
        addDoubleClickEventToLeft(leftFileList);
        addDoubleClickEventToRight(rightFileList);
    }

    private void addDoubleClickEventToLeft(JList<String> fileList) {
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블 클릭 감지
                    String fileName = fileList.getSelectedValue();
                    if (fileName != null) {
                        updateFileList(selectLeftFile(leftDirectory, fileName));
                    }
                }
            }
        });
    }

    private void addDoubleClickEventToRight(JList<String> fileList) {
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블 클릭 감지
                    String fileName = fileList.getSelectedValue();
                    if (fileName != null) {
                        updateFileList(selectRightFile(rightDirectory, fileName));
                    }
                }
            }
        });
    }

    private File selectRightFile(FileDirectory directory, String fileName){
        if(fileName.equals(FileDirectory.PARENT_PATH)) return directory.getDirectory().getParentFile();
        return new File(directory.getDirectory(), fileName);
    }

    private File selectLeftFile(FileDirectory directory, String fileName){
        if(fileName.equals(FileDirectory.ROOT_DIRECTORY_PATH)) return new File(FileDirectory.ROOT_DIRECTORY_PATH);
        return new File(directory.getDirectory(), fileName);
    }

    private void addKeyEventToList() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (activeList == null) return;
                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_LEFT -> {
                        switchActiveList(leftFileList);
                        e.consume(); // 기본 동작 방지
                    }
                    case KeyEvent.VK_RIGHT -> {
                        switchActiveList(rightFileList);
                        e.consume();
                    }
                    case KeyEvent.VK_UP -> {
                        moveSelection(-1);
                        e.consume();
                    }
                    case KeyEvent.VK_DOWN -> {
                        moveSelection(1);
                        e.consume();
                    }
                    case KeyEvent.VK_ENTER -> {
                        openSelectedDirectory();
                        e.consume();
                    }
                }
            }
        };

        leftFileList.addKeyListener(keyAdapter);
        rightFileList.addKeyListener(keyAdapter);
    }

    private void switchActiveList(JList<String> newActiveList) {
        activeList = newActiveList;
        activeList.requestFocus();
    }

    private void moveSelection(int direction) {
        int currentIndex = activeList.getSelectedIndex();
        if (currentIndex == -1) currentIndex = 0;
        int newIndex = Math.max(0, Math.min(activeList.getModel().getSize() - 1, currentIndex + direction));
        activeList.setSelectedIndex(newIndex);
    }

    private void openSelectedDirectory() {
        String selectedValue = activeList.getSelectedValue();
        if (selectedValue == null) return;

        File selectedFile = activeList == leftFileList
                ? selectFile(leftDirectory, selectedValue)
                : selectFile(rightDirectory, selectedValue);

        if (selectedFile.isDirectory()) {
            updateFileList(selectedFile);
        }
    }

    private File selectFile(FileDirectory directory, String fileName) {
        if (fileName.equals(FileDirectory.PARENT_PATH)) return directory.getDirectory().getParentFile();
        if (fileName.equals(FileDirectory.ROOT_DIRECTORY_PATH)) return new File(FileDirectory.ROOT_DIRECTORY_PATH);
        return new File(directory.getDirectory(), fileName);
    }

    private void renew(JList<String> list) {
        list.repaint();
        list.revalidate();
    }

    private void renew(JLabel list) {
        list.repaint();
        list.revalidate();
    }
}
