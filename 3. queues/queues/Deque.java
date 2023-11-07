/* *****************************************************************************
 *  Name:           Alan Enriquez
 *  Date:           10/23/2023
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Item val;
        private Node next = null;
        private Node previous = null;

        public Node(Item val) {
            this.val = val;
        }

        public Item val() {
            return val;
        }
    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        Node newNode = new Node(item);

        // Increment size by 1
        ++size;

        // Set new node next to the current head
        newNode.next = this.head;

        // Check for first object added to linked list
        if (size == 1) {
            tail = newNode;
        }
        else {
            // Set current head previous to the new node
            this.head.previous = newNode;
        }

        // Set current head as the new node
        this.head = newNode;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        Node newNode = new Node(item);

        // Increment size by 1
        ++size;

        // Set previous new node to current tail
        newNode.previous = this.tail;

        // Check for only remaining object in list
        if (size == 1) {
            head = newNode;
        }
        else {
            // Set current tail next node to new node
            this.tail.next = newNode;
        }

        // Set current tail to new node
        this.tail = newNode;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove since size is 0");
        }

        Item val = head.val;

        if (size - 1 == 0) {
            head = null;
            tail = null;
            --size;
            return val;
        }

        Node oldhead = head;
        if (size > 1) {
            oldhead.next.previous = null;
        }
        head = oldhead.next;

        // Returning memory from old head
        oldhead = null;

        --size;
        return val;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove since size is 0");
        }

        Item val = tail.val;

        if (size - 1 == 0) {
            head = null;
            tail = null;
            --size;
            return val;
        }

        Node oldtail = tail;

        if (size > 1) {
            oldtail.previous.next = null;
        }

        tail = oldtail.previous;

        // Returning memory from old tail
        oldtail = null;

        --size;
        return val;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current != null) {
                Item item = current.val;
                current = current.next;
                return item;
            }
            else {
                throw new NoSuchElementException("Next value is null");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("TESTING DOUBLY LINKED LIST QUEUE");
        Deque<Integer> queue = new Deque<>();

        queue.addFirst(10);
        queue.addFirst(5);
        queue.addFirst(7);
        queue.addLast(20);

        System.out.println("Removed first: " + queue.removeFirst());
        System.out.println("Removed last: " + queue.removeLast());

        for (int x : queue) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println("Size: " + queue.size());
    }
}
