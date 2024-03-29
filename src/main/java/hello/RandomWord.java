package hello;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int index = 1;

        while (!StdIn.isEmpty()) {
            String sucessor = StdIn.readString();

            if (StdRandom.bernoulli(1.0 / index)) {
                champion = sucessor;
            }

            index++;
        }

        StdOut.print(champion);
    }
}
