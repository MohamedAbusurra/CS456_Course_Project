import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class HillClimbingAlgorithm {

    private int seed;
    Private ArrayList<ArrayList<LinkedList<CourseNode>>> state,
    Random randomlySelectNeighbor;
    Private int testNumber;
    HelperMethods helperMethods = new HelperMethods();
    String result;
    ArrayList<ArrayList<LinkedList<CourseNode>>> finalTable;



    public HillClimbingAlgorithm(ArrayList<ArrayList<LinkedList<CourseNode>>> state, int seed, int testNumber){
        this.seed = seed;
        this.state = state;
        this.randomlySelectNeighbor = new Random(seed);
        this.testNumber = testNumber;

    }


    private void generateNeighbor(int){

    }


    public void PerformHillClimbingSearch(){
        
    }
    
}
