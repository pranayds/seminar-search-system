/**
 * SeminarDB class is responsible for managing and organizing
 * seminar data through four separate binary trees, allowing 
 * insertion, deletion, and retrieval operations and a bin tree.
 * 
 * @author Pranay Dhalwani
 * @version 09.02.2023
 */
public class SeminarDB {
    private BST<Integer, Seminar> idTree;
    private BST<Integer, Seminar> costTree;
    private BST<String, Seminar> keywordTree;
    private BST<String, Seminar> dateTree;
    private BinTree binTree;
    private int size = 0;

    /**
     * Constructor for creating a Seminar Database.
     * Initializes four binary trees for organizing seminars.
     * 
     * @param worldSize
     *            The size of the world.
     */
    public SeminarDB(int worldSize) {
        this.idTree = new BST<Integer, Seminar>(false);
        this.costTree = new BST<Integer, Seminar>(true);
        this.keywordTree = new BST<String, Seminar>(true);
        this.dateTree = new BST<String, Seminar>(true);
        this.size = worldSize;
        this.binTree = new BinTree(size);
    }

    /**
     * Inserts a seminar into the database and relevant binary trees.
     * 
     * @param sem
     *            The seminar to be inserted.
     */
    public void insert(Seminar sem) {
        
        if (sem.x() < 0 || sem.y() < 0 || 
            sem.x() > size - 1 || sem.y() > size - 1) {
            System.out.println("Insert FAILED - Bad x, y coordinates: " + sem
                .x() + ", " + sem.y());
            return;
        }
        // First insert into ID tree and check if duplicate or not
        boolean inCheck = idTree.insert(sem.id(), sem);

        // If false print error
        if (inCheck == false) {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + sem
                    .id());
            return;
        }
        // Else continue
        // Insert into cost tree
        costTree.insert(sem.cost(), sem);

        // Insert into date tree
        dateTree.insert(sem.date(), sem);

        // Insert into keyword tree
        for (String str : sem.keywords()) {
            keywordTree.insert(str, sem);
        }

        System.out.println("Successfully inserted record with ID " + sem.id());
        System.out.println(sem);
        binTree.insert(sem);
    }

    /**
     * Deletes a seminar with the given ID from the database and associated
     * binary trees.
     * 
     * @param id
     *            The ID of the seminar to be deleted.
     */
    public void delete(int id) {
        Seminar sem = idTree.searchExact(id);

        if (sem == null) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
            return;
        }

        idTree.delete(sem.id(), sem);

        costTree.delete(sem.cost(), sem);

        dateTree.delete(sem.date(), sem);

        for (String str : sem.keywords()) {
            keywordTree.delete(str, sem);
        }

        binTree.delete(sem);

        System.out.println("Record with ID " + id
            + " successfully deleted from the database");
    }

    /**
     * Searches for seminars based on category and a search string (exact
     * match).
     * 
     * @param cat
     *            The category (e.g., "ID" or "keyword").
     * @param str
     *            The search string.
     */
    public void searchExact(String cat, String str) {
        if (cat.equals("ID")) {
            int id = Integer.parseInt(str);
            Seminar sem = idTree.searchExact(id);
            if (sem == null) {
                System.out.println(
                    "Search FAILED -- There is no record with ID " + id);
                return;
            }
            System.out.println("Found record with ID " + str + ":");
            System.out.println(sem);
        }
        else if (cat.equals("keyword")) {
            System.out.println("Seminars matching keyword " + str + ":");
            keywordTree.searchExactKeyword(str);
        }
    }

    /**
     * Searches for seminars within a specified range for a given category
     * (e.g., "cost" or "date").
     * 
     * @param cat
     *            The category (e.g., "cost" or "date").
     * @param low
     *            The lower bound of the range.
     * @param high
     *            The upper bound of the range.
     */
    public void searchRange(String cat, String low, String high) {
        if (cat.equals("cost")) {
            System.out.println("Seminars with costs in range " + low + " to "
                + high + ":");
            int lowCost = Integer.parseInt(low);
            int highCost = Integer.parseInt(high);
            costTree.searchRange(lowCost, highCost);
        }
        else if (cat.equals("date")) {
            System.out.println("Seminars with dates in range " + low + " to "
                + high + ":");
            dateTree.searchRange(low, high);
        }
    }

    /**
     * Searches for Seminars within a specified radius from the given location.
     *
     * @param x The x-coordinate of the search location.
     * @param y The y-coordinate of the search location.
     * @param r The search radius.
     */
    public void searchLocation(int x, int y, int r) {
        binTree.search(x, y, r);
    }

    /**
     * Prints the binary tree for a given category (e.g., "date" or "keyword").
     * 
     * @param cat
     *            The category for which to print the binary tree.
     */
    public void print(String cat) {
        if (cat.equals("date")) {
            System.out.println("Date Tree:");
            dateTree.print();
        }
        else if (cat.equals("keyword")) {
            System.out.println("Keyword Tree:");
            keywordTree.print();
        }
        else if (cat.equals("cost")) {
            System.out.println("Cost Tree:");
            costTree.print();
        }
        else if (cat.equals("ID")) {
            System.out.println("ID Tree:");
            idTree.print();
        }
        else if (cat.equals("location")) {
            System.out.println("Location Tree:");
            binTree.print();
        }
    }
}
