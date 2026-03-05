// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import student.TestCase;

/**
 * Tests the methods from the BST class.
 * 
 * @author Pranay Dhalwani
 * @version 09.02.2023
 *
 */

public class BSTTest extends TestCase {

    /**
     * Tests insert() for empty tree.
     * 
     * @throws IOException
     */
    public void testInsertEmptyTree() throws IOException {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(false);
        String[] stringArr = { "Important", "Aerospace" };
        Seminar sem = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole");
        bst.insert(sem.id(), sem);
        Seminar test = bst.searchExact(sem.id());
        assertEquals(test, sem);
    }


    /**
     * Tests insert() when inserting two seminar objects.
     * 
     * @throws IOException
     */
    public void testInsertTwoValues() throws IOException {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(true);
        String[] stringArr1 = { "Important", "Aerospace" };
        String[] stringArr2 = { "Healthcare", "Essential" };
        Seminar sem = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr1, "The sly fox dug a hole");
        Seminar sem1 = new Seminar(35, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr2, "The sly fox dug a hole");
        bst.insert(sem.id(), sem);
        bst.insert(sem1.id(), sem1);
        Seminar test1 = bst.searchExact(sem.id());
        Seminar test2 = bst.searchExact(sem1.id());
        assertEquals(test1, sem);
        assertEquals(sem1, test2);
    }


    /**
     * Tests insert() when inserting multiple seminar object duplicates
     * with varying ids.
     * 
     * @throws IOException
     */
    public void testInsertMultipleValues() throws IOException {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(false);
        String[] stringArr1 = { "Important", "Aerospace" };

        Seminar sem1 = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr1, "The sly fox dug a hole");
        Seminar sem2 = new Seminar(35, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem3 = new Seminar(36, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem4 = new Seminar(37, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem5 = new Seminar(38, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem6 = new Seminar(39, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem7 = new Seminar(40, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem8 = new Seminar(41, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem9 = new Seminar(42, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");
        Seminar sem10 = new Seminar(43, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr1, "The sly fox dug a hole");

        bst.insert(sem1.id(), sem1);
        bst.insert(sem2.id(), sem2);
        bst.insert(sem3.id(), sem3);
        bst.insert(sem4.id(), sem4);
        bst.insert(sem5.id(), sem5);
        bst.insert(sem6.id(), sem6);
        bst.insert(sem7.id(), sem7);
        bst.insert(sem8.id(), sem8);
        bst.insert(sem9.id(), sem9);
        bst.insert(sem10.id(), sem10);

        Seminar test1 = bst.searchExact(sem1.id());
        Seminar test2 = bst.searchExact(sem2.id());
        Seminar test3 = bst.searchExact(sem3.id());
        Seminar test4 = bst.searchExact(sem4.id());
        Seminar test5 = bst.searchExact(sem5.id());
        Seminar test6 = bst.searchExact(sem6.id());
        Seminar test7 = bst.searchExact(sem7.id());
        Seminar test8 = bst.searchExact(sem8.id());
        Seminar test9 = bst.searchExact(sem9.id());
        Seminar test10 = bst.searchExact(sem10.id());

        assertEquals(test1, sem1);
        assertEquals(test2, sem2);
        assertEquals(test3, sem3);
        assertEquals(test4, sem4);
        assertEquals(test5, sem5);
        assertEquals(test6, sem6);
        assertEquals(test7, sem7);
        assertEquals(test8, sem8);
        assertEquals(test9, sem9);
        assertEquals(test10, sem10);
    }


    /**
     * Tests insert() for inserting date which is a string.
     * 
     * @throws IOException
     */
    public void testInsertDate() throws IOException {
        BST<String, Seminar> bst = new BST<String, Seminar>(true);
        String[] stringArr = { "Important", "Aerospace" };
        Seminar sem1 = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole");
        Seminar sem2 = new Seminar(35, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole");
        bst.insert(sem1.date(), sem1);
        bst.insert(sem2.date(), sem2);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream)) {
            System.setOut(printStream);
            bst.searchRange("0", "1");
            assertEquals(sem2.toString() + "\n" + sem1.toString() + "\n"
                + "5 nodes visited in this search\n", outputStream.toString());
        }
    }


    /**
     * Tests insert() when inserting a null object.
     */
    public void testInsertNull() {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(true);
        bst.insert(null, null);
    }


    /**
     * Tests sample inputs
     * 
     * @throws IOException
     */
    public void testSampleInputs() throws IOException {
        BST<Integer, Seminar> idTree = new BST<Integer, Seminar>(false);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream)) {
            System.setOut(printStream);
            idTree.print();
            assertEquals("This tree is empty\n", outputStream.toString());
        }
    }


    /**
     * Test duplicate keys
     */
    public void testInsertDuplicateKeys() {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(false);
        String[] stringArr1 = { "Important", "Aerospace" };
        String[] stringArr2 = { "Healthcare", "Essential" };
        Seminar sem1 = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr1, "The sly fox dug a hole");
        Seminar sem2 = new Seminar(34, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr2, "The sly fox dug a hole");
        bst.insert(sem1.id(), sem1);
        assertEquals(sem1, bst.searchExact(sem1.id()));
        assertFalse(bst.insert(sem2.id(), sem2));
    }


    /**
     * Test recursive inserts
     */
    public void testInsertRecursive() {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(false);
        String[] stringArr1 = { "Important", "Aerospace" };
        String[] stringArr2 = { "Healthcare", "Essential" };
        Seminar sem1 = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr1, "The sly fox dug a hole");
        Seminar sem2 = new Seminar(35, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr2, "The sly fox dug a hole");
        bst.insert(sem2.id(), sem2);
        bst.insert(sem1.id(), sem1);
        assertEquals(sem1, bst.searchExact(sem1.id()));
        assertEquals(sem2, bst.searchExact(sem2.id()));
    }


    /**
     * Test delete empty tree
     */
    public void testDeleteFromEmptyTree() {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(false);
        bst.delete(34, null);
        assertNull(bst.searchExact(34));
    }


    /**
     * Test existing nodes
     */
    public void testDeleteExistingNode() {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(true);
        String[] stringArr1 = { "Important", "Aerospace" };
        Seminar sem = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr1, "The sly fox dug a hole");
        bst.insert(sem.id(), sem);
        assertEquals(sem, bst.searchExact(sem.id()));
        bst.delete(sem.id(), sem);
        assertNull(bst.searchExact(sem.id()));
    }


    /**
     * Test delete empty nodes
     */
    public void testDeleteNonExistentNode() {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(true);
        String[] stringArr1 = { "Important", "Aerospace" };
        Seminar sem = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr1, "The sly fox dug a hole");
        bst.insert(sem.id(), sem);
        assertEquals(sem, bst.searchExact(sem.id()));
        bst.delete(35, null);
        assertEquals(sem, bst.searchExact(sem.id()));
    }


    /**
     * Test delete nodes with duplicate keys
     */
    public void testDeleteNodeWithDuplicateKeys() {
        BST<Integer, Seminar> bst = new BST<Integer, Seminar>(false);
        String[] stringArr1 = { "Important", "Aerospace" };
        String[] stringArr2 = { "Healthcare", "Essential" };
        Seminar sem1 = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr1, "The sly fox dug a hole");
        Seminar sem2 = new Seminar(35, "Hello", "0612150456", 2, (short)43,
            (short)23, 42, stringArr2, "The sly fox dug a hole");
        bst.insert(sem1.id(), sem1);
        bst.insert(sem2.id(), sem2);
        assertEquals(sem1, bst.searchExact(sem1.id()));
        bst.delete(sem1.id(), sem1);
        assertNull(bst.searchExact(sem1.id()));
    }
}
