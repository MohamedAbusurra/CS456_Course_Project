import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class HelperMethods {
    private String fileName;
    private int n; // how many lactures
    private Random random;
    private int day;
    private int slot;

    public HelperMethods() {

    };

    public HelperMethods(String fileName, int n, int seed, int day, int slot) {
        this.fileName = fileName;
        this.n = n;
        this.random = new Random(seed);
        this.day = day;
        this.slot = slot;
    };

    public LinkedList<CourseNode[]> readTestsFromFile() {
        LinkedList<CourseNode[]> allTests = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue; 

                CourseNode[] table = new CourseNode[n];

                String[] tokens = line.split(",");

                int index = 0; 
                int i = 0;

                while (i < tokens.length) {
                    String token = tokens[i].trim();

                    
                    if (token.isEmpty() || token.equals("&")) {
                        i++;
                        continue;
                    }

                    
                    String courseId = token;

                    if (i + 2 >= tokens.length) {
                      
                        break;
                    }

                    String semStr = tokens[i + 1].trim();
                    String instStr = tokens[i + 2].trim();

                   
                    if (semStr.isEmpty() || semStr.equals("&") ||
                            instStr.isEmpty() || instStr.equals("&")) {
                        break;
                    }

                    int semester = Integer.parseInt(semStr);
                    int instructorId = Integer.parseInt(instStr);

                    CourseNode node = new CourseNode(courseId, semester, instructorId);

                    if (index >= table.length) {
                        
                        break;
                    }

                   
                    table[index] = node;

                    index++;
                    i += 3; 
                }

                allTests.add(table);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allTests;
    }

  
}
