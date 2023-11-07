/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size + 1 > queue.length) {
            Item[] newList = (Item[]) new Object[queue.length * 2];
            for (int i = 0; i < queue.length; i++) {
                newList[i] = queue[i];
            }
            queue = newList;
        }
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int randIndex = StdRandom.uniformInt(size);
        Item itemToReturn = queue[randIndex];
        if (!(randIndex + 1 == size)) {
            for (int i = randIndex; i < size - 1; i++) {
                queue[i] = queue[i + 1];
            }
        }
        size--;

        if (size == (queue.length / 4)) {
            Item[] newQueue;
            if (size == 0) {
                newQueue = (Item[]) new Object[1];
            }
            else {
                newQueue = (Item[]) new Object[queue.length / 2];
            }

            for (int i = 0; i < newQueue.length; i++) {
                newQueue[i] = queue[i];
                queue[i] = null;
            }
            queue = newQueue;
        }

        return itemToReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return queue[StdRandom.uniformInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return queue[i++];
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        // RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        // rq.enqueue(5);
        // rq.enqueue(20);
        // rq.enqueue(15);
        // rq.enqueue(30);
        //
        // printList(rq);
        //
        // // removeRandNum(rq);
        //
        // printList(rq);
        //
        // // System.out.println("Sampling a random number");
        // // System.out.println(rq.sample());
        //
        // // removeRandNum(rq);
        // printList(rq);
        // // removeRandNum(rq);
        // printList(rq);
        // // removeRandNum(rq);
        // // System.out.println("This should throw an error for trying to remove from empty list.");
        // // removeRandNum(rq);
    }

    // public static void removeRandNum(RandomizedQueue<Integer> rq) {
    //     System.out.println("Removing a random number: " + rq.dequeue());
    // }
    //
    // public static void printList(RandomizedQueue<Integer> rq) {
    //     for (Integer x : rq) {
    //         System.out.print(x + " ");
    //     }
    //     System.out.println();
    //     System.out.println("Printing size: " + rq.size());
    //     System.out.println();
    // }
}
