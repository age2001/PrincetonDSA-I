/* *****************************************************************************
 *  Name:              Alan Enriquez
 *  Last modified:     October 24, 2023
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int count = 1;
        String word = "";
        String champion = "";
        while (!StdIn.isEmpty()) {
            word = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / count)) {
                champion = word;
            }
            count++;
        }
        System.out.println(champion);
    }
}
