import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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


     public int[] countConflicts(ArrayList<ArrayList<LinkedList<CourseNode>>> state) {
        int hard = 0;
        int soft = 0;

        if (state == null) {
               
            return new int[] {0, 0};
        }

        int days = state.size();
        for (int day = 0; day < days; day++) {
            ArrayList<LinkedList<CourseNode>> slotsInDay = state.get(day);
            if (slotsInDay == null)
                continue;

            java.util.Map<String, Integer> courseCountInDay = new java.util.HashMap<>();

            int slots = slotsInDay.size();
            for (int slot = 0; slot < slots; slot++) {
                LinkedList<CourseNode> cell = slotsInDay.get(slot);
                if (cell == null || cell.isEmpty())
                    continue;

                for (CourseNode c : cell) {
                    if (c == null)
                        continue;
                    String cid = c.getCourseId();
                    courseCountInDay.put(cid, courseCountInDay.getOrDefault(cid, 0) + 1);
                }

                int n = cell.size();
                for (int i = 0; i < n; i++) {
                    CourseNode a = cell.get(i);
                    if (a == null)
                        continue;

                    for (int j = i + 1; j < n; j++) {
                        CourseNode b = cell.get(j);
                        if (b == null)
                            continue;

                        if (a.getInstructorId() == b.getInstructorId()) {
                            hard++;
                        }

                        int sa = a.getSemester();
                        int sb = b.getSemester();
                        int diff = Math.abs(sa - sb);

                        if (sa == sb) {
                            hard++;
                        } else {
                            if (diff == 1 || diff == 2 || diff == 3) {
                                soft++;
                            }
                        }
                    }
                }
            }

            for (java.util.Map.Entry<String, Integer> e : courseCountInDay.entrySet()) {
                int count = e.getValue();
                if (count > 1) {
                    soft += (count - 1);
                }
            }
        }

        return new int[] {hard, soft};
    }

     public int calculateHeuristic(ArrayList<ArrayList<LinkedList<CourseNode>>> state) {
           int penalty = 0;

        if (state == null)
            return 0;

        int days = state.size(); // 6
        for (int day = 0; day < days; day++) {
            ArrayList<LinkedList<CourseNode>> slotsInDay = state.get(day);
            if (slotsInDay == null)
                continue;

            int slots = slotsInDay.size(); //  4

            // "Two lectures of the same course on the same day"
            
           // java.util.Map<String, Integer> courseCountInDay = new java.util.HashMap<>();

            for (int slot = 0; slot < slots; slot++) {
                LinkedList<CourseNode> cell = slotsInDay.get(slot);
                if (cell == null || cell.isEmpty())
                    continue;

                
              

               
                int n = cell.size();
                for (int i = 0; i < n; i++) {
                    CourseNode a = cell.get(i);
                    if (a == null)
                        continue;

                    for (int j = i + 1; j < n; j++) {
                        CourseNode b = cell.get(j);
                        if (b == null)
                            continue;

                        //  Instructor double-booked
                        if (a.getInstructorId() == b.getInstructorId()) {
                            penalty += 1200;
                        }

                        int sa = a.getSemester();
                        int sb = b.getSemester();
                        int diff = Math.abs(sa - sb);

                        // 2) Same year course overlapping
                        if (sa == sb) {
                            penalty += 1000;
                        } else {
                            // 3) Conflicts between courses one / two / three years apart
                            if (diff == 1) {
                                penalty += 150; // one year apart
                            } else if (diff == 2) {
                                penalty += 50; // two years apart
                            } else if (diff == 3) {
                                penalty += 25; // three years apart
                            }
                        }
                    }
                }
            }

           
            // Two lectures of the same course on the same day: +75
            
           
        }

        return penalty;
    }

      public ArrayList<ArrayList<LinkedList<CourseNode>>> createEmptyTimetable() {

        ArrayList<ArrayList<LinkedList<CourseNode>>> timetable = new ArrayList<>();

        for (int dayy = 0; dayy < day; dayy++) {
            ArrayList<LinkedList<CourseNode>> slots = new ArrayList<>();

            for (int slott = 0; slott < slot; slott++) {
                slots.add(new LinkedList<CourseNode>()); // empty
            }

            timetable.add(slots);
        }

        return timetable;
    }

       public ArrayList<ArrayList<LinkedList<CourseNode>>> genrate_initial_state(CourseNode[] state, int weekly_lacture) {

        int best_h = 0;
        ArrayList<ArrayList<LinkedList<CourseNode>>> best_timetable = null;

        for (int k = 0; k < 100; k++) {
            ArrayList<ArrayList<LinkedList<CourseNode>>> timetable = createEmptyTimetable();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < weekly_lacture; j++) {
                    int col = random.nextInt(slot);
                    int row = random.nextInt(day);
                    if (state[i] == null) {
                        continue;
                    }
                    timetable.get(row).get(col).add(state[i]);
                }
            }
            if (k == 0) {
                best_timetable = timetable;
                best_h = calculateHeuristic(timetable);
            } else {
                if (best_h > calculateHeuristic(timetable)) {
                    best_timetable = timetable;
                    best_h = calculateHeuristic(timetable);
                }
            }
        }
         return best_timetable;
    }






}
