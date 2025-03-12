package org;

import org.content.TextContent;
import org.reader.TextReader;
import org.writer.TextWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path basePath = Paths.get("files");
        TextReader reader = new TextReader(basePath);
        TextWriter writer = new TextWriter(basePath);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit")) break;
            else if (input.startsWith("read")) {
                String[] inputs = input.split(" ");
                Optional<TextContent> textContent = reader.read(basePath.resolve(inputs[1]));
                if (textContent.isPresent()) System.out.println(textContent.get().getText());
                else System.out.println("해당 파일이 없습니다.");
            } else if (input.startsWith("write")) {
                String[] inputs = input.split(" ");
                String title = inputs[1];
                TextContent textContent = new TextContent(inputs[2]);
                writer.write(Paths.get(title), textContent);
            }
        }
    }
}
