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
    @DisplayName("FileDirectory 객체를 생성하고 초기값으로 초기화 할 수 있다.")
    void 객체_초기화_생성(){
        fileDirectory = new FileDirectory();
        fileDirectory.printFiles();
    }

    @Test
    @DisplayName("FileDirectory 객체의 경로를 설정하고, 객체의 부모, 디렉토리와 파일을 출력할 수 있다.")
    void 객체_생성_및_출력() {
        fileDirectory.setDirectory(new File("C://Users/hulli/Desktop/be-mdir/files"));
        assertThat(fileDirectory.getParentString()).isEqualTo("/..\n" );
        assertThat(fileDirectory.getDirectoriesString()).isEqualTo("/direc\n" );
        assertThat(fileDirectory.getFilesString()).isEqualTo("hello.txt\n");
    }
}