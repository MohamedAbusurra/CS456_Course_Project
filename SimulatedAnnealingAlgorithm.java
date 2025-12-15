import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class SimulatedAnnealingAlgorithm {

    private ArrayList<ArrayList<LinkedList<CourseNode>>> state;
    private Random random;
    private int max_iteritor = 10000;
    HelperMethods ob = new HelperMethods();
    String result ;
    ArrayList<ArrayList<LinkedList<CourseNode>>> final_taple;

    private double starting_temperature;
    private double cooling_rate;
    private int test_it;

    private int[] initialConflicts = new int[2];
    private int[] finalConflicts = new int[2];

   
    public SimulatedAnnealingAlgorithm(ArrayList<ArrayList<LinkedList<CourseNode>>> state, int seed,double starting_temperature,double cooling_rate,int test_it) {
        this.state = state;
        this.random = new Random(seed);
        this.starting_temperature = starting_temperature;
        this.cooling_rate = cooling_rate;
        this.test_it = test_it;
    }

    public SimulatedAnnealingAlgorithm(ArrayList<ArrayList<LinkedList<CourseNode>>> state, int seed,  int max_iteritor,double starting_temperature,double cooling_rate,int test_it) {
        this.state = state;
        this.random = new Random(seed);
        this.max_iteritor = max_iteritor;
        this.starting_temperature = starting_temperature;
        this.cooling_rate = cooling_rate;
        this.test_it = test_it;
    }

private ArrayList<ArrayList<LinkedList<CourseNode>>> copyState(
            ArrayList<ArrayList<LinkedList<CourseNode>>> state) {

        ArrayList<ArrayList<LinkedList<CourseNode>>> copy = new ArrayList<>();

        for (int day = 0; day < state.size(); day++) {
            ArrayList<LinkedList<CourseNode>> row = state.get(day);
            ArrayList<LinkedList<CourseNode>> rowCopy = new ArrayList<>();

            for (int slot = 0; slot < row.size(); slot++) {
                LinkedList<CourseNode> cell = row.get(slot);
               
                LinkedList<CourseNode> cellCopy = new LinkedList<>(cell);
                rowCopy.add(cellCopy);
            }

            copy.add(rowCopy);
        }

        return copy;
    }

    
private ArrayList<ArrayList<LinkedList<CourseNode>>> getNeighbor(
        ArrayList<ArrayList<LinkedList<CourseNode>>> state,
        int heuristic) {

    ArrayList<ArrayList<ArrayList<LinkedList<CourseNode>>>> possibleStates = new ArrayList<>();

    int days = state.size();              
    int slotsPerDay = state.get(0).size(); 

    
    for (int day = 0; day < days; day++) {
        for (int slot = 0; slot < slotsPerDay; slot++) {

            LinkedList<CourseNode> cell = state.get(day).get(slot);
            if (cell.isEmpty()) continue;

            
            for (int idx = 0; idx < cell.size(); idx++) {
                CourseNode course = cell.get(idx);

                
                for (int newSlot = 0; newSlot < slotsPerDay; newSlot++) {
                    if (newSlot == slot) continue; 

                    ArrayList<ArrayList<LinkedList<CourseNode>>> neighbor = copyState(state);

                    
                    neighbor.get(day).get(slot).remove(idx);
                    
                    neighbor.get(day).get(newSlot).add(course);

                    possibleStates.add(neighbor);
                }

                
                for (int newDay = 0; newDay < days; newDay++) {
                    if (newDay == day) continue;

                    ArrayList<ArrayList<LinkedList<CourseNode>>> neighbor = copyState(state);

                    neighbor.get(day).get(slot).remove(idx);
                    neighbor.get(newDay).get(slot).add(course);

                    possibleStates.add(neighbor);
                }
            }
        }
    }

    
    if (possibleStates.size() == 0)
        return null;


    int randomIndex = random.nextInt(possibleStates.size());
    return possibleStates.get(randomIndex);
}



    
    public void performSimulatedAnnealingSearch(){
        
    }
}
