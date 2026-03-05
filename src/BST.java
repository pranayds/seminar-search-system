/**
 * Copyright (c) 2023 Pranay Dhalwani. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Pranay Dhalwani.
 * You shall not disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Pranay Dhalwani.
 * 
 * Binary Search Tree (BST) class designed to manage and organize
 * seminar data using four separate binary trees for seminar IDs,
 * dates, costs, and keywords. The class provides methods for
 * insertion, search, deletion, and traversal of the binary trees,
 * as well as options to control duplicate key entries.
 * 
 * @author Pranay Dhalwani
 * @version 09.02.2023
 * 
 * @param <K> the type of keys maintained by this node
 * @param <V> the type of values maintained by this node
 * 
 */

class BSTNode<K extends Comparable<K>, V> {
    /**
     * Key (K) value of the key value pair
     */
    private K key;

    /**
     * Value (V) value of the key value pair
     */
    private V value;

    /**
     * K, V Types
     * Left node pointer
     */
    private BSTNode<K, V> left;

    /**
     * K, V Types
     * Right node pointer
     */
    private BSTNode<K, V> right;

    /**
     * Constructs a new BSTNode with the given key and value.
     * 
     * @param key
     *            The key associated with the node.
     * @param value
     *            The value associated with the key.
     */
    public BSTNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key of this node.
     *
     * @return the key of this node
     */
    public K getKey() {
        return key;
    }
    
    /**
     * Updates the key of this node.
     *
     * @param val the new key for this node
     */
    public void updateKey(K val) {
        this.key = val;
    }

    /**
     * Returns the value of this node.
     *
     * @return the value of this node
     */
    public V getVal() {
        return value;
    }

    /**
     * Updates the value of this node.
     *
     * @param val the new value for this node
     */
    public void updateVal(V val) {
        this.value = val;
    }

    /**
     * Returns the right child node of this node.
     *
     * @return the right child node of this node
     */
    public BSTNode<K, V> getRight() {
        return right;
    }

    /**
     * Returns the left child node of this node.
     *
     * @return the left child node of this node
     */
    public BSTNode<K, V> getLeft() {
        return left;
    }

    /**
     * Updates the right child node of this node.
     *
     * @param n the new right child node for this node
     */
    public void updateRight(BSTNode<K, V> n) {
        this.right = n;
    }

    /**
     * Updates the left child node of this node.
     *
     * @param n the new left child node for this node
     */
    public void updateLeft(BSTNode<K, V> n) {
        this.left = n;
    }
}


/**
 * Binary Search Tree (BST) class responsible for managing and
 * organizing data using four binary trees to handle seminars
 * by ID, date, cost, and keyword.
 * 
 * @author Pranay Dhalwani
 * 
 * @version 10.16.2023
 * 
 * @param <K>
 *            The type of the key used in the tree.
 * @param <V>
 *            The type of value associated with the key.
 */

public class BST<K extends Comparable<K>, V> {

    private BSTNode<K, V> root;
    private int nodesVisited;
    private final boolean allowDuplicates;

    /**
     * Constructor for the binary search tree.
     * 
     * @param allowDup
     *            A boolean flag to allow or disallow
     *            duplicate keys.
     */
    public BST(boolean allowDup) {
        this.allowDuplicates = allowDup;
    }


    /**
     * Inserts a new node with a specific key and value into
     * the binary search tree.
     * 
     * @param key
     *            The key of the new node.
     * @param value
     *            The value associated with the key.
     * @return True if the insertion was successful,
     *         false if it fails.
     */
    public boolean insert(K key, V value) {
        int originalSize = size();
        root = insertRecursive(root, key, value);
        return size() > originalSize;
    }


    /**
     * Inserts a new node into the binary search tree.
     * 
     * @param key
     *            Key of the node.
     * @param value
     *            Value associated with the key.
     * @return True if the insertion was successful,
     *         false if it fails.
     */
    private BSTNode<K, V> insertRecursive(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            return new BSTNode<K, V>(key, value);
        }

        int cmp = key.compareTo(node.getKey());

        if (cmp < 0 || (cmp == 0 && allowDuplicates)) {
            node.updateLeft(insertRecursive(node.getLeft(), key, value));
        }
        else if (cmp > 0) {
            node.updateRight(insertRecursive(node.getRight(), key, value));
        }
        else if (cmp == 0 && !allowDuplicates) {
            return node;
        }

        return node;
    }
    
    /**
     * Searches for a node with the exact key in the tree.
     * 
     * @param key
     *            The key to search for.
     * @return The value associated with the key if found,
     *         otherwise null.
     */
    public V searchExact(K key) {
        return searchExactRecursive(root, key);
    }


    /**
     * Searches for nodes with a specific keyword.
     * 
     * @param keyword
     *            The keyword to search for.
     */
    public void searchExactKeyword(K keyword) {
        searchRangeRecursive(root, keyword, keyword);
    }


    /**
     * Recursively searches for a node with the exact key in
     * the binary search tree.
     * 
     * @param node
     *            The root of the current subtree.
     * @param key
     *            The key to search for.
     * @return The value associated with the key if found,
     *         otherwise null.
     */
    private V searchExactRecursive(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.getKey());

        if (cmp < 0) {
            return searchExactRecursive(node.getLeft(), key);
        }
        else if (cmp == 0) {
            return node.getVal();
        }
        else {
            return searchExactRecursive(node.getRight(), key);
        }
    }

    /**
     * Searches for nodes within a specific key range.
     * 
     * @param low
     *            The lower bound of the key range.
     * @param high
     *            The upper bound of the key range.
     */
    public void searchRange(K low, K high) {
        nodesVisited = 0;
        searchRangeRecursive(root, low, high);
        System.out.println(nodesVisited + " nodes visited in this search");
    }

    /**
     * Recursively searches for nodes within a specific
     * key range in the binary search tree.
     * 
     * @param node
     *            The root of the current subtree.
     * @param low
     *            The lower bound of the key range.
     * @param high
     *            The upper bound of the key range.
     */
    private void searchRangeRecursive(BSTNode<K, V> node, K low, K high) {
        nodesVisited++;

        if (node == null) {
            return;
        }

        int cmpLow = node.getKey().compareTo(low);
        int cmpHigh = node.getKey().compareTo(high);

        if (cmpLow < 0) {
            searchRangeRecursive(node.getRight(), low, high);
        }
        else if (cmpHigh > 0) {
            searchRangeRecursive(node.getLeft(), low, high);
        }
        else {
            searchRangeRecursive(node.getLeft(), low, high);
            System.out.println(node.getVal());
            if (cmpHigh != 0) {
                searchRangeRecursive(node.getRight(), low, high);
            }
        }
    }

    /**
     * Deletes a node with a specific key and value.
     * 
     * @param key
     *            The key to delete.
     * @param val
     *            The value to delete.
     */
    public void delete(K key, V val) {
        root = deleteRecursive(root, key, val);
    }


    /**
     * Recursively deletes a node with a specific key
     * and value from the binary search tree.
     * 
     * @param node
     *            The root of the current subtree.
     * @param key
     *            The key to delete.
     * @param val
     *            The value to delete.
     * @return The modified tree after deletion.
     */
    private BSTNode<K, V> deleteRecursive(BSTNode<K, V> node, K key, V val) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.getKey());

        if (cmp < 0) {
            node.updateLeft(deleteRecursive(node.getLeft(), key, val));
        }
        else if (cmp > 0) {
            node.updateRight(deleteRecursive(node.getRight(), key, val));
        }
        else {
            if (!allowDuplicates || (node.getVal().equals(val))) {
                if (node.getLeft() != null) {
                    node.updateKey(maxKey(node.getLeft()));
                    node.updateVal(getNodeValue(node.getLeft(), node.getKey()));
                    node.updateLeft(deleteRecursive(node.getLeft(), node
                        .getKey(), node.getVal()));
                }
                else if (node.getRight() != null) {
                    node = node.getRight();
                }
                else {
                    node = null;
                }
                return node;
            }
            else {
                node.updateLeft(deleteRecursive(node.getLeft(), key, val));
            }
        }
        return node;
    }


    /**
     * Finds the minimum key in a given binary search tree.
     * 
     * @param node
     *            The root of the current subtree.
     * @return The minimum key in the subtree.
     */
    private K maxKey(BSTNode<K, V> node) {
        K maxK = node.getKey();
        while (node.getRight() != null) {
            maxK = node.getRight().getKey();
            node = node.getRight();
        }
        return maxK;
    }


    /**
     * Retrieves the value associated with a specific
     * key in the binary search tree.
     * 
     * @param node
     *            The root of the current subtree.
     * @param key
     *            The key to search for.
     * @return The value associated with the key,
     *         or null if not found.
     */
    private V getNodeValue(BSTNode<K, V> node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.getKey());
            if (cmp < 0) {
                node = node.getLeft();
            }
            else if (cmp > 0) {
                node = node.getRight();
            }
            else {
                return node.getVal();
            }
        }
        return null;
    }


    /**
     * Returns the number of nodes in the binary search tree.
     * 
     * @return The number of nodes in the tree.
     */
    public int size() {
        return sizeRecursive(root);
    }


    /**
     * Recursive helper method to calculate the size of the
     * subtree rooted at 'node'.
     * 
     * @param node
     *            The root of the current subtree.
     * @return The number of nodes in the subtree.
     */
    private int sizeRecursive(BSTNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.getLeft()) + sizeRecursive(node
            .getRight());
    }


    /**
     * Prints the binary search tree in a reverse in-order
     * traversal.
     */
    public void print() {
        if (root == null) {
            System.out.println("This tree is empty");
            return;
        }
        printReverseInorder(root, 0);
        System.out.println("Number of records: " + countNodes(root));
    }


    /**
     * Recursively prints the binary search tree in a reverse
     * in-order traversal.
     * 
     * @param node
     *            The root of the current subtree.
     * @param lvl
     *            The current level of the node in the tree.
     */
    public void printReverseInorder(BSTNode<K, V> node, int lvl) {
        // Print the null node's correct indentations
        if (node == null) {
            for (int i = 0; i < lvl; i++) {
                System.out.print("  ");
            }

            System.out.println("null");
            return;
        }

        // Call to the right subtree first
        printReverseInorder(node.getRight(), lvl + 1);

        for (int i = 0; i < lvl; i++) {
            System.out.print("  ");
        }

        System.out.println(node.getKey());

        // Call to the left subtree
        printReverseInorder(node.getLeft(), lvl + 1);
    }


    private int countNodes(BSTNode<K, V> node) {
        return sizeRecursive(node);
    }
}