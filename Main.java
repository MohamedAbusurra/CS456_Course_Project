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


     // Builds helper method obejct used to generate random intitial timetable for each test and prints and read tests from txt file
     Helpermethods helperMethods = new Helpermethods("testRuns.txt", maxCoursesInSemester, seed, days, timeSlots);

     LinkedList<CourseNode[]> tests = helperMethods.readTestsFromFile();

     // calls method to read tests from file and store them in an arraylist of strings
     ArrayList<String> list = helperMethods.readTestsFromFile();

     int testNumberCounter = 1;

     TextFileWriter TextFileWriter = new TextFileWriter();

     for(CourseNode[] table : tests){
        System.out.println("test" + testNumberCounter);
        ArrayList<ArrayList<LinkedList<CourseNode>>> result = helperMethods.generateInitialState();
        System.out.println("Simulated Annealing");
        //Imeplement simulated annealing
        System.out.println("Hill CLimbing");
        HillClimbingAlgorithm hillClimbingAlgorithm = new HillClimbingAlgorithm();
        list.add(hillClimbingAlgorithm.getResults());
        helperMethods.printTimetable(hillClimbingAlgorithm.getFinalTable());
        testNumberCounter++;
        System.out.println();
        

     }



}