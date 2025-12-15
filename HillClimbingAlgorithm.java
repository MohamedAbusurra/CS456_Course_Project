import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class HillClimbingAlgorithm {

    private int seed;
    Private ArrayList<ArrayList<LinkedList<CourseNode>>> state;
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

    // used to create a copy of the solution state so modifications do not affect the original when searching all current solution neighbors 
    private ArrayList<ArrayList<LinkedList<CourseNode>>> copyState(ArrayList<ArrayList<LinkedList<CourseNode>>> state) {
        //creates a new arraylist to store the copied state
        ArrayList<ArrayList<LinkedList<CourseNode>>> copy = new ArrayList<>();
        // iterate through each day in the schedule
        for (int day = 0; day < state.size(); day++) {
            // store the list of slots for that day
            ArrayList<LinkedList<CourseNode>> row = state.get(day);
            // initialize a arraylist to store a copy of that row (all slots for each day)
            ArrayList<LinkedList<CourseNode>> rowCopy = new ArrayList<>();
            // iterate though all the slots in the row (all slots for day)
            for (int slot = 0; slot < row.size(); slot++) {
                // get the linked list of all courses (the course node with relevant course information for that day) 
                LinkedList<CourseNode> cell = row.get(slot);
                //create a copy of the linked lisst of coursenodes
                LinkedList<CourseNode> cellCopy = new LinkedList<>(cell);
                rowCopy.add(cellCopy);
            }
            //add the copied row to the copied state
            copy.add(rowCopy);
        }
        //return the copy of the sent state given
        return copy;
    }


    private void generateNeighbor(int){

    }


    public void PerformHillClimbingSearch(){
        
    }
    
}
