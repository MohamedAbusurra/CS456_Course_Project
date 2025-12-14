
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
     int weeklyLecturesPerCourse = 2
     // maximum number of iterations for simulated annealing
     int maxIterations = 4000;


     // Builds helper method obejct used to generate random intitial timetable for each test and prints and read tests from txt file
     Helpermethods helperMethods = new Helpermethods("testRuns.txt", maxCoursesInSemester, seed, days, timeSlots);

     // calls method to read tests from file and store them in an arraylist of strings
     ArrayList<String> myTests = helperMethods.readTestsFromFile();

     int testCounter = 1;

     TextFileWriter TextFileWriter = new TextFileWriter();




}