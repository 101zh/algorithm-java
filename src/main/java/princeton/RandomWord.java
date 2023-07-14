package princeton;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args){
        String champion="";
        double count=1;

        while (!StdIn.isEmpty()){ // in intellij, use ctrl+D to send end of input signal
            String word = StdIn.readString();
            double probability= 1/count;
            if (StdRandom.bernoulli(probability)){
                champion=word;
            }
            count++;
        }
        System.out.println(champion);
    }
}
