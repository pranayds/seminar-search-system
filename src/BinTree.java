/**
 * BinTree represents a binary tree data structure designed to
 * efficiently store and manage Seminars within a specified
 * world size. It provides operations for inserting, searching,
 * deleting, and printing Seminar records, as well as tracking
 * the number of nodes visited during searches. The tree's
 * structure optimizes the search for Seminars based on their
 * coordinates and allows for spatial queries within a given
 * world size.
 *
 * @author Pranay Dhalwani
 * @version 09.02.2023
 */

interface BinTreeNode {
    /**
     * Inserts a Seminar into the binary tree.
     *
     * @param sem
     *            The Seminar to insert.
     * @param depth
     *            The current depth in the tree.
     * @param xMin
     *            Min of x.
     * 
     * @param xMax
     *            Max of x.
     * 
     * @param yMin
     *            Min of y.
     * 
     * @param yMax
     *            Max of y.
     * 
     * @return The updated binary tree node after insertion.
     */
    BinTreeNode insert(
        Seminar sem,
        int depth,
        int xMin,
        int xMax,
        int yMin,
        int yMax);


    /**
     * Deletes a Seminar from the binary tree.
     *
     * @param sem
     *            The Seminar to delete.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     * @return The updated binary tree node after deletion.
     */
    BinTreeNode delete(Seminar sem, int depth, int xRange, int yRange);


    /**
     * Searches for Seminars within a specified radius of a given point.
     *
     * @param x
     *            The x-coordinate of the center of the search area.
     * @param y
     *            The y-coordinate of the center of the search area.
     * @param radius
     *            The radius within which to search for Seminars.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     */
    void search(int x, int y, int radius, int depth, int xRange, int yRange);


    /**
     * Prints the structure of the binary tree with proper indentation.
     *
     * @param depth
     *            The current depth in the tree (used for indentation).
     */
    void print(int depth);
}




/**
 *
 * Represents an internal node in a binary tree, implementing the
 * BinTreeNode interface. It serves as a node that branches further
 * into the tree and contains references to its children.
 *
 * @author Pranay Dhalwani
 * @version 09.02.2023
 *
 */
class InternalNode implements BinTreeNode {
    private BinTreeNode firstChild;
    private BinTreeNode secondChild;

    /**
     * Constructor for InternalNode class. Initializes the firstChild and
     * secondChild as EmptyNode instances.
     */
    public InternalNode() {
        firstChild = EmptyNode.getInstance();
        secondChild = EmptyNode.getInstance();
    }


    /**
     * Inserts a Seminar into the binary tree.
     *
     * @param sem
     *            The Seminar to insert.
     * @param depth
     *            The current depth in the tree.
     * @param xMin
     *            Min of x.
     * 
     * @param xMax
     *            Max of x.
     * 
     * @param yMin
     *            Min of y.
     * 
     * @param yMax
     *            Max of y.
     * 
     * @return The updated binary tree node after insertion.
     */
    @Override
    public BinTreeNode insert(
        Seminar sem,
        int depth,
        int xMin,
        int xMax,
        int yMin,
        int yMax) {
        if (depth % 2 == 0) {
            if (sem.x() < (xMin + xMax) / 2) {
                firstChild = firstChild.insert(sem, depth + 1, xMin, (xMin
                    + xMax) / 2, yMin, yMax);
            }
            else {
                secondChild = secondChild.insert(sem, depth + 1, (xMin + xMax)
                    / 2, xMax, yMin, yMax);
            }
        }
        else {
            if (sem.y() < (yMin + yMax) / 2) {
                firstChild = firstChild.insert(sem, depth + 1, xMin, xMax, yMin,
                    (yMin + yMax) / 2);
            }
            else {
                secondChild = secondChild.insert(sem, depth + 1, xMin, xMax,
                    (yMin + yMax) / 2, yMax);
            }
        }
        return this;
    }


    /**
     * Deletes a Seminar from the binary tree.
     *
     * @param sem
     *            The Seminar to delete.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     * @return The updated binary tree node after deletion.
     */
    @Override
    public BinTreeNode delete(Seminar sem, int depth, int xRange, int yRange) {
        if (depth % 2 == 0) {
            if (sem.x() < xRange / 2) {
                firstChild = firstChild.delete(sem, depth + 1, xRange / 2,
                    yRange);
            }
            else {
                secondChild = secondChild.delete(sem, depth + 1, xRange / 2,
                    yRange);
            }
        }
        else {
            if (sem.y() < yRange / 2) {
                firstChild = firstChild.delete(sem, depth + 1, xRange, yRange
                    / 2);
            }
            else {
                secondChild = secondChild.delete(sem, depth + 1, xRange, yRange
                    / 2);
            }
        }

        if (firstChild instanceof EmptyNode
            && secondChild instanceof LeafNode) {
            return secondChild;
        }
        else if (firstChild instanceof LeafNode
            && secondChild instanceof EmptyNode) {
            return firstChild;
        }
        else {
            return this;
        }
    }


    /**
     * Searches for Seminars within a specified radius of a given point.
     *
     * @param x
     *            The x-coordinate of the center of the search area.
     * @param y
     *            The y-coordinate of the center of the search area.
     * @param radius
     *            The radius within which to search for Seminars.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     */
    @Override
    public void search(
        int x,
        int y,
        int radius,
        int depth,
        int xRange,
        int yRange) {
        BinTree.incrementNodesVisited();
        // Calculate bounding box
        int leftBound = x - radius;
        int rightBound = x + radius;
        int upperBound = y - radius;
        int lowerBound = y + radius;

        if (depth % 2 == 0) {
            int splitX = xRange / 2;

            if (leftBound <= splitX) {
                firstChild.search(x, y, radius, depth + 1, splitX, yRange);
            }
            if (rightBound > splitX) {
                secondChild.search(x, y, radius, depth + 1, splitX, yRange);
            }
        }
        else {
            int splitY = yRange / 2;

            if (upperBound <= splitY) {
                firstChild.search(x, y, radius, depth + 1, xRange, splitY);
            }
            if (lowerBound > splitY) {
                secondChild.search(x, y, radius, depth + 1, xRange, splitY);
            }
        }

    }


    /**
     * Prints the structure of the binary tree with proper indentation.
     *
     * @param depth
     *            The current depth in the tree (used for indentation).
     */
    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("I");
        // Preorder traversal
        firstChild.print(depth + 1);
        secondChild.print(depth + 1);
    }
}




/**
 * Represents a leaf node in a binary tree, implementing the
 * BinTreeNode interface. It holds a collection of Seminar
 * records and represents the end points of the tree.
 * 
 * @author Pranay Dhalwani
 * @version 09.02.2023
 */
class LeafNode implements BinTreeNode {
    private SortedLinkedList seminars;

    /**
     * Constructor for LeafNode class. Initializes the node with a single
     * Seminar.
     *
     * @param seminar
     *            The Seminar to insert into the leaf node.
     */
    public LeafNode(Seminar seminar) {
        this.seminars = new SortedLinkedList();
        this.seminars.insert(seminar);
    }


    /**
     * Inserts a Seminar into the leaf node or converts it into an InternalNode
     * if needed.
     *
     * @param sem
     *            The Seminar to insert.
     * @param depth
     *            The current depth in the tree.
     * @param xMin
     *            Min of x.
     *            
     * @param xMax
     *            Max of x.
     *            
     * @param yMin
     *            Min of y.
     *            
     * @param yMax
     *            Max of y.
     *            
     * @return The updated binary tree node after insertion.
     */
    @Override
    public BinTreeNode insert(
        Seminar sem,
        int depth,
        int xMin,
        int xMax,
        int yMin,
        int yMax) {
        if (seminars.getSize() == 0) {
            seminars.insert(sem);
            return this;
        }
        else if (seminars.getFirst().x() == sem.x() && seminars.getFirst()
            .y() == sem.y()) {
            seminars.insert(sem);
            return this;
        }
        else {
            InternalNode newNode = new InternalNode();

            for (Seminar currSem : seminars.getAllSeminars()) {
                newNode.insert(currSem, depth, xMin, xMax, yMin, yMax);
            }

            return newNode.insert(sem, depth, xMin, xMax, yMin, yMax);
        }
    }


    /**
     * Deletes a Seminar from the leaf node.
     *
     * @param sem
     *            The Seminar to delete.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     * @return The updated binary tree node after deletion.
     */
    @Override
    public BinTreeNode delete(Seminar sem, int depth, int xRange, int yRange) {
        seminars.delete(sem.id());

        if (seminars.getSize() == 0) {
            return EmptyNode.getInstance();
        }

        return this;
    }


    /**
     * Searches for Seminars within a specified radius of a given point.
     *
     * @param x
     *            The x-coordinate of the center of the search area.
     * @param y
     *            The y-coordinate of the center of the search area.
     * @param radius
     *            The radius within which to search for Seminars.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     */
    @Override
    public void search(
        int x,
        int y,
        int radius,
        int depth,
        int xRange,
        int yRange) {
        BinTree.incrementNodesVisited();
        int seminarX = seminars.getFirst().x();
        int seminarY = seminars.getFirst().y();

        int deltaX = x - seminarX;
        int deltaY = y - seminarY;
        int distanceSquared = deltaX * deltaX + deltaY * deltaY;

        if (distanceSquared <= radius * radius) {
            for (Seminar sem : seminars.getAllSeminars()) {
                System.out.println("Found a record with key value " + sem.id()
                    + " at " + sem.x() + ", " + sem.y());
            }
        }
    }


    /**
     * Prints the structure of the leaf node.
     *
     * @param depth
     *            The current depth in the tree (used for indentation).
     */
    @Override
    public void print(int depth) {
        String indentation = repeatString("  ", depth);
        System.out.print(indentation + "Leaf with " + seminars.getSize()
            + " objects: ");

        Seminar[] allSeminars = seminars.getAllSeminars();
        for (int i = 0; i < seminars.getSize(); i++) {
            System.out.print(allSeminars[i].id());
            if (i < seminars.getSize() - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }


    private String repeatString(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}




/**
 * Represents an empty node in a binary tree, implementing
 * the BinTreeNode interface. It signifies the absence of
 * elements and is used for handling empty tree branches.
 * 
 * @author Pranay Dhalwani
 * @version 09.02.2023
 */
class EmptyNode implements BinTreeNode {
    private static final EmptyNode INSTANCE = new EmptyNode();

    private EmptyNode() {
    }


    /**
     * Get the singleton instance of EmptyNode.
     *
     * @return The singleton EmptyNode instance.
     */
    public static EmptyNode getInstance() {
        return INSTANCE;
    }


    /**
     * Inserts a Seminar into an EmptyNode, creating a new LeafNode.
     *
     * @param sem
     *            The Seminar to insert.
     * @param depth
     *            The current depth in the tree.
     * @param xMin
     *            Min of x.
     *            
     * @param xMax
     *            Max of x.
     *            
     * @param yMin
     *            Min of y.
     *            
     * @param yMax
     *            Max of y.
     *            
     * @return A new LeafNode with the inserted Seminar.
     */
    @Override
    public BinTreeNode insert(
        Seminar sem,
        int depth,
        int xMin,
        int xMax,
        int yMin,
        int yMax) {
        return new LeafNode(sem);
    }


    /**
     * Deletes a Seminar from an EmptyNode, which has no effect.
     *
     * @param sem
     *            The Seminar to delete.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     * @return The EmptyNode itself since deletion has no effect.
     */
    @Override
    public BinTreeNode delete(Seminar sem, int depth, int xRange, int yRange) {
        return this;
    }


    /**
     * Search operation in an EmptyNode, which increments the visited nodes
     * count.
     *
     * @param x
     *            The x-coordinate of the center of the search area.
     * @param y
     *            The y-coordinate of the center of the search area.
     * @param radius
     *            The radius within which to search for Seminars.
     * @param depth
     *            The current depth in the tree.
     * @param xRange
     *            The range of x-coordinate values.
     * @param yRange
     *            The range of y-coordinate values.
     */
    @Override
    public void search(
        int x,
        int y,
        int radius,
        int depth,
        int xRange,
        int yRange) {
        BinTree.incrementNodesVisited();
    }


    /**
     * Print an EmptyNode with proper indentation.
     *
     * @param depth
     *            The current depth in the tree (used for indentation).
     */
    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("E");
    }
}




/**
 * Represents a binary tree data structure designed for efficient
 * storage and management of Seminar records. It provides methods
 * for insertion, deletion, searching, and printing of Seminar records,
 * as well as functionality to track the number of visited nodes
 * during searches.
 * 
 * @author Pranay Dhalwani
 * @version 09.02.2023
 */

public class BinTree {
    private BinTreeNode root;
    private static int nodesVisited;
    private int worldSize;

    /**
     * Resets the count of nodes visited during searches to zero.
     */
    public static void resetNodesVisited() {
        nodesVisited = 0;
    }


    /**
     * Gets the count of nodes visited during searches.
     *
     * @return The number of nodes visited.
     */
    public static int getNodesVisited() {
        return nodesVisited;
    }


    /**
     * Increments the count of nodes visited during searches.
     */
    public static void incrementNodesVisited() {
        nodesVisited++;
    }


    /**
     * Constructor for BinTree class, initializes the root and world size.
     *
     * @param worldSize
     *            The size of the world (range of coordinates).
     */
    public BinTree(int worldSize) {
        root = EmptyNode.getInstance();
        this.worldSize = worldSize;
    }


    /**
     * Inserts a Seminar into the binary tree.
     *
     * @param sem
     *            The Seminar to insert.
     */
    public void insert(Seminar sem) {
        root = root.insert(sem, 0, 0, worldSize, 0, worldSize);
    }


    /**
     * Searches for Seminars within a specified radius of a given point and
     * prints the results.
     *
     * @param x
     *            The x-coordinate of the center of the search area.
     * @param y
     *            The y-coordinate of the center of the search area.
     * @param radius
     *            The radius within which to search for Seminars.
     */
    public void search(int x, int y, int radius) {
        resetNodesVisited();
        System.out.println("Seminars within " + radius + " units of " + x + ", "
            + y + ":");
        root.search(x, y, radius, 0, worldSize, worldSize);
        System.out.println(getNodesVisited() + " nodes visited in this search");
    }


    /**
     * Prints the structure of the binary tree, starting from the root.
     */
    public void print() {
        root.print(0);
    }


    /**
     * Deletes a Seminar from the binary tree.
     *
     * @param sem
     *            The Seminar to delete.
     */
    public void delete(Seminar sem) {
        root = root.delete(sem, 0, worldSize, worldSize);
    }
}
