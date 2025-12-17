import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextFileWriter {

    
    public String readTextFromConsole() {
        Scanner scanner = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("END")) {
                break;
            }
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();
    }

    
    public void writeTextToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}