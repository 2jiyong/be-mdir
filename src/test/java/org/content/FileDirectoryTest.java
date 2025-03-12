package org.content;

import org.junit.jupiter.api.*;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class FileDirectoryTest {
    public FileDirectory fileDirectory;

    @BeforeEach
    void setUp() {
        fileDirectory = new FileDirectory();
    }

    @Test
    @DisplayName("FileDirectory 객체의 경로를 설정하고, 객체의 디렉토리와 파일을 출력할 수 있다.")
    void 객체_생성_및_출력() {
        fileDirectory.setDirectory(new File("C://Users/hulli/Desktop/be-mdir/files"));
        fileDirectory.printFiles();
    }
}