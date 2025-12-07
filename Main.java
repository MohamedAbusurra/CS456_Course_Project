
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){

        ArrayList<Integer> testDataSets = new ArrayList<>();
        File inputTests = new File("testRuns.txt");
        int randomSeed = 1;

        try {
            Scanner fileScanner = new Scanner(inputTests);
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        HillClimbingAlgorithm hillClimbingAlgorithm = new HillClimbingAlgorithm(randomSeed);
        SimulatedAnnealingAlgorithm simulatedAnnealingAlgorithm = new SimulatedAnnealingAlgorithm(randomSeed);

        for(int i=0; i< testDataSets.length; i++){
            

        }


        
        

        
        
    }
}