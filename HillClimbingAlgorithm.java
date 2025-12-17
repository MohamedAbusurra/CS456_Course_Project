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


    private ArrayList<ArrayList<LinkedList<CourseNode>>> getNeighbor(
        ArrayList<ArrayList<LinkedList<CourseNode>>> state,
        int fitness) {

    // Initialize the variable to track the lowest fitness found starting with the current fitness value.
    int lowestFitnessFound = fitness;

    // stores all possible neighbor states.
    ArrayList<ArrayList<ArrayList<LinkedList<CourseNode>>>> possibleStates = new ArrayList<>();

    // Stores the best possible states found (with the lowest fitness).
    ArrayList<ArrayList<ArrayList<LinkedList<CourseNode>>>> bestPossibleStates = new ArrayList<>();

    // Get the number of days in the schedule.
    int days = state.size(); 
    
    // Get the number of slots per day.
    int slotsPerDay = state.get(0).size(); 

    // Loop through every cell in the schedule (each day and slot)
    for (int day = 0; day < days; day++) {
        for (int slot = 0; slot < slotsPerDay; slot++) {

            // Get the list of courses(course nodes) for the current day and slot.
            LinkedList<CourseNode> cell = state.get(day).get(slot);
            
            // If the cell is empty ( meaning there are no courses in this slot), skip iteration.
            if (cell.isEmpty())
                continue;

            // Loop through all the courses in the current cell
            for (int i = 0; i < cell.size(); i++) {
                CourseNode course = cell.get(i);

                // ===== 1) Move the course to a different slot on the same day =====
                // Loop through all the available slots for the current day (excluding the current slot)
                for (int newSlot = 0; newSlot < slotsPerDay; newSlot++) {
                    // Skip if the new slot is the same as the current slot
                    if (newSlot == slot)
                        continue;

                    // Create a copy of the current state to modify it without changing the original
                    ArrayList<ArrayList<LinkedList<CourseNode>>> neighbor = copyState(state);

                    // Remove the course from the current slot
                    neighbor.get(day).get(slot).remove(i);

                    // Add the course to the new slot on the same day
                    neighbor.get(day).get(newSlot).add(course);

                    // Calculate the fitness of the new state (after moving the course)
                    int fitnessOfNeighbor = helperMethods.calculateHeuristic(neighbor);

                    // If the fitness of this new state is lower than the previous best, store it.
                    if (fitnessOfNeighbor < lowestFitnessFound) {
                        lowestFitnessFound = fitnessOfNeighbor;
                        // Add the neighbor state to possible states
                        possibleStates.add(neighbor);  
                    }
                }

                // ===== 2) Move the course to a different day with the same slot =====
                // Loop through all the available days (excluding the current day)
                for (int newDay = 0; newDay < days; newDay++) {
                    // Skip if the new day is the same as the current day
                    if (newDay == day)
                        continue;

                    // Create a copy of the current state to modify it
                    ArrayList<ArrayList<LinkedList<CourseNode>>> neighbor = copyState(state);

                    // Remove the course from the current day and slot
                    neighbor.get(day).get(slot).remove(i);

                    // Add the course to the same slot but on the new day
                    neighbor.get(newDay).get(slot).add(course);

                    // Calculate the fitness of the new state (after moving the course)
                    int fitnessOfNeighbor = helperMethods.calculateHeuristic(neighbor);

                    // If the fitness of this new state is better (lower) than the previous best, store it.
                    if (fitnessOfNeighbor < lowestFitnessFound) {
                        lowestFitnessFound = fitnessOfNeighbor;
                        // Add the neighbor state to possible states.
                        possibleStates.add(neighbor);  
                    }
                }
            }
        }
    }
    // Iterare through all the possible neighbo states storing ones with same fitness function as best possible ones found.
    for (ArrayList<ArrayList<LinkedList<CourseNode>>> neighbor : possibleStates) {
        // Calculate the fitness of each possible neighbor
        int fitnessOfNeighbor = helperMethods.calculateHeuristic(neighbor);

        // If this state's fitness matches the lowest fitness found, it's one of the best possible states
        if (fitnessOfNeighbor == lowestFitnessFound) {
            bestPossibleStates.add(neighbor);
        }
    }

    // If no equal or better states were found, return null 
    if (bestPossibleStates.size() == 0)
        return null;

    // Randomly select one of the best possible states and return it.
    int randomIndex = randomlySelectNeighbor.nextInt(bestPossibleStates.size());
    return bestPossibleStates.get(randomIndex);
}

    private void addresult(int conflict, int finalFitness, long time, int iteration) {
           result = "test : " + testNumber + "\n" +
                "algorthim name: " + "HillClimbingAlgorithm" + "\n" +
                  "Initial Conflicts (hard/soft): " + initialConflicts[0] + "/" + initialConflicts[1] + "\n" +
                 "Final Conflicts (hard/soft): " + finalConflicts[0] + "/" + finalConflicts[1] + "\n" +
                "Final Heuristic: " + finalFitness + "\n" +
                "Execution Time (ms): " + time + "\n" +
                "Iterations: " + iteration + "\n";
    }

    public String getResults() {
        return result;
    }

    public ArrayList<ArrayList<LinkedList<CourseNode>>> getFinalTable() {
        return finalTable;
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
                addresult(0, currentStateHeuristic, duration, iteration);
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