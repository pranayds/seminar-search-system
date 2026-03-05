/**
 * A linked list data structure for storing and managing Seminar
 * records in sorted order by ID.
 *
 * @author Pranay Dhalwani
 * @version 10.12.2023
 */
public class SortedLinkedList {
    /**
     * Node class used to instantiate nodes to be used in the 
     * SortedLinkedList. 
     * @author Pranay Dhalwani
     *
     */
    class Node {
        private Seminar seminar;
        private Node next;
        
        /**
         * Constructor method for Node class
         * 
         * @param sem
         *          Seminar object to initialize to
         */
        Node(Seminar sem) {
            this.seminar = sem;
        }
    }
    
    private Node head;
    private int size;
    
    /**
     * Inserts a Seminar into the sorted linked list, maintaining
     * order by Seminar ID.
     *
     * @param sem The Seminar to insert.
     */
    public void insert(Seminar sem) {
        Node newNode = new Node(sem);
        size++;
        if (head == null) {
            head = newNode;
        }
        else if (head.seminar.id() > sem.id()) {
            newNode.next = head;
            head = newNode;
        }
        else {
            Node current = head;
            while (current.next != null && 
                current.next.seminar.id() < sem.id()) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }
    
    /**
     * Deletes a Seminar with the specified ID from the linked list,
     * if it exists.
     *
     * @param id The ID of the Seminar to delete.
     */
    public void delete(int id) {
        if (head == null) {
            return;
        }
        
        size--;
        
        if (head.seminar.id() == id) {
            head = head.next;
            return;
        }
        
        Node current = head;
        Node prev = null;
        while (current != null && current.seminar.id() != id) {
            prev = current;
            current = current.next;
        }
        
        if (current != null) {
            prev.next = current.next;
        }
    }
    
    /**
     * Retrieves a Seminar with the specified ID from the linked list,
     * if it exists.
     *
     * @param id The ID of the Seminar to retrieve.
     * @return The Seminar with the given ID, or null if not found.
     */
    public Seminar get(int id) {
        Node current = head;
        while (current != null) {
            if (current.seminar.id() == id) {
                return current.seminar;
            }
            current = current.next;
        }
        return null;
    }
    
    /**
     * Retrieves the first Seminar in the linked list.
     *
     * @return The first Seminar in the list, or null if the list
     * is empty.
     */
    public Seminar getFirst() {
        if (head == null) {
            return null;
        }
        else {
            return head.seminar;
        }
    }
    
    /**
     * Retrieves all Seminars in the linked list as an array.
     *
     * @return An array containing all Seminars in the list.
     */
    public Seminar[] getAllSeminars() {
        Seminar[] result = new Seminar[size];
        Node current = head;
        int index = 0;
        
        while (current != null) {
            result[index++] = current.seminar;
            current = current.next;
        }
        return result;
    }
    
    /**
     * Gets the number of Seminars in the linked list.
     *
     * @return The size of the linked list.
     */
    public int getSize() {
        return size;
    }
}