/* *****************************************************************************
 *  Name:       Alan Enriquez
 *  Date:       10/27/2023
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String c = "";
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            c = StdIn.readString();
            rq.enqueue(c);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }

    }
}
