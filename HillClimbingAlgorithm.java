import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class HillClimbingAlgorithm {

    private int seed;
    private ArrayList<ArrayList<LinkedList<CourseNode>>> state;
    Random randomlySelectNeighbor;
    private int testNumber;
    HelperMethods helperMethods = new HelperMethods();
    String result;
    ArrayList<ArrayList<LinkedList<CourseNode>>> finalTable;
    int[] initialConflicts = new int[2];
    int[] finalConflicts = new int[2];



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

    private void addresult(int conflict, int finalFitness, long time, int iteration) {

    }


    public void performHillClimbingSearch() {
        // start timer for execution time
        long start = System.currentTimeMillis();

        // initilize the inirialize state as the current state
        ArrayList<ArrayList<LinkedList<CourseNode>>> currentState = state;
        // will store state before current state to track progress
        ArrayList<ArrayList<LinkedList<CourseNode>>> previousState = null;
        // count the initial number of conflicts to print later
        initialConflicts = helperMethods.countConflicts(state);
        int iteration = 0;
        int currentStateHeuristic;

        System.out.println();
        System.out.println("Hill Climbing Search with " + seed + " as random generator seed");
        // Main loop will continue executing until global minimum found or stuck
        while (true) {
            // calculate current state heuristic
            currentStateHeuristic = helperMethods.calculateHeuristic(currentState);

            System.out.print(iteration + ": ");
            System.out.println(" " + currentStateHeuristic);
            // if current state is not null save it as previous state for comparison later
            if (currentState != null) {
                previousState = currentState;
            }

            // Terminates search if optimal solution found (solved).
            // get final conflict and final timetable and end search time and add result to results for printing 
            if (currentStateHeuristic == 0) {
                System.out.println("Solved");
                long end = System.currentTimeMillis();
                long duration = end - start;
                finalConflicts = helperMethods.countConflicts(finalTable);
                addResult(0, currentStateHeuristic, duration, iteration);
                finalTable = previousState;
                break;
            }
            // get the neighbor of state
            currentState = getNeighbor(currentState, currentStateHeuristic);

            // terminates search if no equal or worse neighbors exist (stuck).
            if (currentState == null) {
                long end = System.currentTimeMillis();
                long duration = end - start;
                finalConflicts = helperMethods.countConflicts(previousState);
                addresult(0, currentStateHeuristic, duration, iteration);
                System.out.println("Stuck");
                finalTable = previousState;
                break;
            }

            iteration++;

        // upon finishing completion, print the final conflicts into the solution state found
         finalConflicts = helperMethods.countConflicts(finalTable);

    }
    
}
}