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
    }

  
}
