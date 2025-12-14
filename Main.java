import java.util.ArrayList;
import java.util.LinkedList;

public class Main{

    public static void main(String[] args){

    // maximum number of courses allowed in a semester
     int maxCoursesInSemester = 500;
     // fixed seed for random reproducibility
     int seed = 1;
     // number of days and timeslots in the timetable
     int days = 6;
     int timeSlots = 4;
     //how many lectures each course has
     int weeklyLecturesPerCourse = 2;
     // maximum number of iterations for simulated annealing
     int maxIterations = 4000;

     // Different starting temperatures that will be used by the simulated annealing searches
     ArrayList<Double> startingTemperature = new ArrayList<>();
     startingTemperature.add(100.0);
     startingTemperature.add(150.0);
     startingTemperature.add(200.0);
     // Different cooling rates that will be used by the simulated annealing searches
     ArrayList<Double> coolingRate = new ArrayList<>();
     coolingRate.add(0.90);
     coolingRate.add(0.95);
     coolingRate.add(0.99);


     // Builds helper method obejct used to generate random intitial timetable for each test and prints and read tests from txt file
     Helpermethods helperMethods = new Helpermethods("testRuns.txt", maxCoursesInSemester, seed, days, timeSlots);

     // calls method to read tests from file and store them in an arraylist of strings
     LinkedList<CourseNode[]> tests = helperMethods.readTestsFromFile();

    //Stores results in a string to write them into the results file after algorithm searches complete
     ArrayList<String> list = new ArrayList<>();

     //Used to store which test number is being performed for each one the input tests
     int testNumberCounter = 1;

     TextFileWriter TextFileWriter = new TextFileWriter();

     for(CourseNode[] table : tests){
        System.out.println("test" + testNumberCounter);
        ArrayList<ArrayList<LinkedList<CourseNode>>> result = helperMethods.generateInitialState();
        System.out.println("Simulated Annealing");
        for (double temperature : startingTemperature){
            for(double rate : coolingRate){
                    //implementat execution for simulated annealing
            }
        }
        System.out.println("Hill CLimbing");
        HillClimbingAlgorithm hillClimbingAlgorithm = new HillClimbingAlgorithm();
        list.add(hillClimbingAlgorithm.getResults());
        helperMethods.printTimetable(hillClimbingAlgorithm.getFinalTable());
        testNumberCounter++;
        System.out.println();
        

     }



}