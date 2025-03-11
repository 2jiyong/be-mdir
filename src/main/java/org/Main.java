package org;

import org.content.TextContent;
import org.reader.TextReader;
import org.writer.TextWriter;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "files";
        TextReader reader = new TextReader();
        TextWriter writer = new TextWriter(path);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit")) break;
            else if (input.startsWith("read")) {
                String[] inputs = input.split(" ");
                Optional<TextContent> textContent = reader.read(path + "/" + inputs[1]);
                if (textContent.isPresent()) System.out.println(textContent.get().getText());
                else System.out.println("해당 파일이 없습니다.");
            } else if (input.startsWith("write")) {
                String[] inputs = input.split(" ");
                String title = inputs[1];
                TextContent textContent = new TextContent(inputs[2]);
                writer.appendWrite(title, textContent);
            }
        }
    }
}
