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
import java.io.FileWriter;
import java.io.PrintStream;
import student.TestCase;

/**
 * @author pranaydhalwani
 * @version 09.30.2023
 * 
 *          Tests methods in the SemSearch class.
 */
public class SemSearchTest extends TestCase {

    /**
     * Tests the main method.
     * 
     * @throws Exception
     */
    public void testMainInput() throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream)) {
            System.setOut(printStream);
            try (FileWriter writer = new FileWriter("testMain1.txt")) {
                writer.write("");
            }
            SemSearch.main(new String[] { "128", "testMain1.txt", "hfcjdsns" });
            assertEquals("Usage: java SemSearch {world-size} {command-file}\n",
                outputStream.toString());
        }
    }
    
    /**
     * Tests incorrect input syntax.
     * 
     * @throws Exception
     */
    public void testCp() throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream)) {
            System.setOut(printStream);
            try (FileWriter writer = new FileWriter("testMain1.txt")) {
                writer.write("print ugfm\n"
                    + "search yrjdk\n"
                    + "print like\n"
                    + "search idk\n");
            }
            SemSearch.main(new String[] { "128", "testMain1.txt" });
            assertEquals("Invalid parameter\n"
                + "Invalid parameter\n"
                + "Invalid parameter\n"
                + "Invalid parameter\n",
                outputStream.toString());
        }
    }


    /**
     * Tests the main method.
     * 
     * @throws Exception
     */
    public void testMain1() throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream)) {
            System.setOut(printStream);
            try (FileWriter writer = new FileWriter("testMain1.txt")) {
                writer.write("print location\r\n" + "\r\n"
                    + "              print ID\r\n" + "search ID 1\r\n"
                    + "search keyword VT\r\n" + "\r\n" + "insert 1\r\n"
                    + "Overview of HCI Research at VT\r\n"
                    + "0610051600 90 -1 10 45\r\n"
                    + "HCI Computer_Science VT Virginia_Tech\r\n"
                    + "This seminar will present an overview of HCI research "
                    + "at VT\r\n" + "insert 1\r\n"
                    + "Overview of HCI Research at VT\r\n"
                    + "0610051600 90 10 128 45\r\n"
                    + "HCI Computer_Science VT Virginia_Tech\r\n"
                    + "This seminar will present an overview of HCI research "
                    + "at VT\r\n" + "\r\n" + "\r\n" + "\r\n" + "insert 1\r\n"
                    + "Overview of HCI Research at VT\r\n"
                    + "0610051600 90 10 10 45\r\n"
                    + "HCI Computer_Science VT Virginia_Tech\r\n"
                    + "This seminar will present an overview of HCI research "
                    + "at VT\r\n" + "\r\n" + "\r\n" + "insert 2\r\n"
                    + "Computational Biology and Bioinformatics in CS at "
                    + "Virginia Tech\r\n"
                    + "      0610071600       60     10     10 30\r\n"
                    + "Bioinformatics computation_biology Biology "
                    + "Computer_Science VT Virginia_Tech\r\n"
                    + "    Introduction to   bioinformatics and computation "
                    + "biology\r\n" + "insert 10\r\n"
                    + "Computing Systems Research at VT\r\n"
                    + "0701250830  30 30   10  17\r\n"
                    + "high_performance_computing           grids     VT "
                    + "computer science\r\n"
                    + "  Seminar about the      Computing systems research "
                    + "at      VT\r\n" + "    \r\n" + "insert 3\r\n"
                    + "Overview of HPC and CSE Research at VT\r\n"
                    + "1203301125     35        0  0                     "
                    + "                      25\r\n"
                    + "      HPC      CSE      computer_science \r\n"
                    + "Learn what kind of    research is done on HPC  "
                    + "and CSE at VT\r\n" + "\r\n" + "print ID\r\n"
                    + "print date\r\n"
                    + "                print                   keyword\r\n"
                    + "                \r\n" + "print cost\r\n" + "\r\n"
                    + "  print location\r\n" + "  \r\n" + "insert 10\r\n"
                    + "Overview of HPC and CSE Research at VT\r\n"
                    + "0703301125     35        0  0                          "
                    + "                 25\r\n"
                    + "      HPC      CSE      computer_science \r\n"
                    + "Learn what kind of    research is done on HPC  "
                    + "and CSE at VT\r\n" + "\r\n" + "search keyword VT\r\n"
                    + "search keyword " + "vt\r\n" + "search ID 1\r\n"
                    + "search cost 30 50\r\n" + "\r\n"
                    + "search location -1 0 1\r\n"
                    + "search location -1 0 2000\r\n" + "\r\n"
                    + "search location 10 10 0\r\n"
                    + "search location 11 11 0\r\n"
                    + "search location 10 10 20\r\n" + "search date 0 1\r\n"
                    + "\r\n" + "delete 1\r\n" + "delete 1\r\n" + "\r\n"
                    + "print ID\r\n" + "print location");
            }
            SemSearch.main(new String[] { "128", "testMain1.txt" });
            assertEquals("Location Tree:\r\n" + "E\r\n" + "ID Tree:\r\n"
                + "This tree is empty\r\n"
                + "Search FAILED -- There is no record with ID 1\r\n"
                + "Seminars matching keyword VT:\r\n"
                + "Insert FAILED - Bad x, y coordinates: -1, 10\r\n"
                + "Insert FAILED - Bad x, y coordinates: 10, 128\r\n"
                + "Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of "
                + "HCI research at VT\r\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
                + "Successfully inserted record with ID 2\r\n"
                + "ID: 2, Title: Computational Biology and Bioinformatics "
                + "in CS at Virginia Tech\r\n"
                + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to   "
                + "bioinformatics and computation "
                + "biology\r\n"
                + "Keywords: Bioinformatics, computation_biology, Biology, "
                + "Computer_Science, VT, Virginia_Tech\r\n"
                + "Successfully inserted record with ID 10\r\n"
                + "ID: 10, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the      Computing systems "
                + "research at      VT\r\n"
                + "Keywords: high_performance_computing, grids, VT, computer, "
                + "science\r\n" + "Successfully inserted record with ID 3\r\n"
                + "ID: 3, Title: Overview of HPC and CSE Research at VT\r\n"
                + "Date: 1203301125, Length: 35, X: 0, Y: 0, Cost: 25\r\n"
                + "Description: Learn what kind of    research is done on "
                + "HPC  and CSE at VT\r\n"
                + "Keywords: HPC, CSE, computer_science\r\n" + "ID Tree:\r\n"
                + "      null\r\n" + "    10\r\n" + "        null\r\n"
                + "      3\r\n" + "        null\r\n" + "  2\r\n"
                + "    null\r\n" + "1\r\n" + "  null\r\n"
                + "Number of records: 4\r\n" + "Date Tree:\r\n"
                + "        null\r\n" + "      1203301125\r\n"
                + "        null\r\n" + "    0701250830\r\n" + "      null\r\n"
                + "  0610071600\r\n" + "    null\r\n" + "0610051600\r\n"
                + "  null\r\n" + "Number of records: 4\r\n"
                + "Keyword Tree:\r\n" + "            null\r\n"
                + "          science\r\n" + "            null\r\n"
                + "        high_performance_computing\r\n"
                + "            null\r\n" + "          grids\r\n"
                + "                null\r\n"
                + "              computer_science\r\n"
                + "                null\r\n" + "            computer\r\n"
                + "              null\r\n" + "      computation_biology\r\n"
                + "        null\r\n" + "    Virginia_Tech\r\n"
                + "        null\r\n" + "      Virginia_Tech\r\n"
                + "        null\r\n" + "  VT\r\n" + "      null\r\n"
                + "    VT\r\n" + "        null\r\n" + "      VT\r\n"
                + "          null\r\n" + "        HPC\r\n"
                + "          null\r\n" + "HCI\r\n" + "    null\r\n"
                + "  Computer_Science\r\n" + "          null\r\n"
                + "        Computer_Science\r\n" + "            null\r\n"
                + "          CSE\r\n" + "            null\r\n"
                + "      Biology\r\n" + "        null\r\n"
                + "    Bioinformatics\r\n" + "      null\r\n"
                + "Number of records: 18\r\n" + "Cost Tree:\r\n" + "  null\r\n"
                + "45\r\n" + "    null\r\n" + "  30\r\n" + "        null\r\n"
                + "      25\r\n" + "        null\r\n" + "    17\r\n"
                + "      null\r\n" + "Number of records: 4\r\n"
                + "Location Tree:\r\n" + "I\r\n" + "  I\r\n" + "    I\r\n"
                + "      I\r\n" + "        I\r\n" + "          I\r\n"
                + "            I\r\n"
                + "              Leaf with 1 objects: 3\r\n"
                + "              Leaf with 2 objects: 1 2\r\n"
                + "            E\r\n" + "          Leaf with 1 objects: 10\r\n"
                + "        E\r\n" + "      E\r\n" + "    E\r\n" + "  E\r\n"
                + "Insert FAILED - There is already a record with ID 10\r\n"
                + "Seminars matching keyword VT:\r\n"
                + "ID: 10, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the      "
                + "Computing systems research "
                + "at      VT\r\n"
                + "Keywords: high_performance_computing, grids, VT, computer, "
                + "science\r\n"
                + "ID: 2, Title: Computational Biology and Bioinformatics "
                + "in CS at "
                + "Virginia Tech\r\n"
                + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to   bioinformatics "
                + "and computation "
                + "biology\r\n"
                + "Keywords: Bioinformatics, computation_biology, "
                + "Biology, "
                + "Computer_Science, VT, Virginia_Tech\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an "
                + "overview of HCI research "
                + "at VT\r\n"
                + "Keywords: HCI, Computer_Science, VT, "
                + "Virginia_Tech\r\n"
                + "Seminars matching keyword vt:\r\n"
                + "Found record with ID 1:\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an "
                + "overview of HCI research "
                + "at VT\r\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
                + "Seminars with costs in range 30 to 50:\r\n"
                + "ID: 2, Title: Computational Biology and Bioinformatics "
                + "in CS at "
                + "Virginia Tech\r\n"
                + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to   bioinformatics and "
                + "computation "
                + "biology\r\n"
                + "Keywords: Bioinformatics, computation_biology, Biology, "
                + "Computer_Science, VT, Virginia_Tech\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of "
                + "HCI research "
                + "at VT\r\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
                + "7 nodes visited in this search\r\n"
                + "Seminars within 1 units of -1, 0:\r\n"
                + "Found a record with key value 3 at 0, 0\r\n"
                + "8 nodes visited in this search\r\n"
                + "Seminars within 2000 units of -1, 0:\r\n"
                + "Found a record with key value 3 at 0, 0\r\n"
                + "Found a record with key value 1 at 10, 10\r\n"
                + "Found a record with key value 2 at 10, 10\r\n"
                + "Found a record with key value 10 at 30, 10\r\n"
                + "15 nodes visited in this search\r\n"
                + "Seminars within 0 units of 10, 10:\r\n"
                + "Found a record with key value 1 at 10, 10\r\n"
                + "Found a record with key value 2 at 10, 10\r\n"
                + "8 nodes visited in this search\r\n"
                + "Seminars within 0 units of 11, 11:\r\n"
                + "8 nodes visited in this search\r\n"
                + "Seminars within 20 units of 10, 10:\r\n"
                + "Found a record with key value 3 at 0, 0\r\n"
                + "Found a record with key value 1 at 10, 10\r\n"
                + "Found a record with key value 2 at 10, 10\r\n"
                + "Found a record with key value 10 at 30, 10\r\n"
                + "11 nodes visited in this search\r\n"
                + "Seminars with dates in range 0 to 1:\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview "
                + "of HCI research "
                + "at VT\r\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
                + "ID: 2, Title: Computational Biology and Bioinformatics "
                + "in CS at "
                + "Virginia Tech\r\n"
                + "Date: 0610071600, Length: 60, X: 10, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to   bioinformatics and "
                + "computation "
                + "biology\r\n"
                + "Keywords: Bioinformatics, computation_biology, Biology, "
                + "Computer_Science, VT, Virginia_Tech\r\n"
                + "ID: 10, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the      Computing systems "
                + "research "
                + "at      VT\r\n"
                + "Keywords: high_performance_computing, grids, VT, computer, "
                + "science\r\n" + "8 nodes visited in this search\r\n"
                + "Record with ID 1 successfully deleted from the database\r\n"
                + "Delete FAILED -- There is no record with ID 1\r\n"
                + "ID Tree:\r\n" + "    null\r\n" + "  10\r\n"
                + "      null\r\n" + "    3\r\n" + "      null\r\n" + "2\r\n"
                + "  null\r\n" + "Number of records: 3\r\n"
                + "Location Tree:\r\n" + "I\r\n" + "  I\r\n" + "    I\r\n"
                + "      I\r\n" + "        I\r\n" + "          I\r\n"
                + "            I\r\n"
                + "              Leaf with 1 objects: 3\r\n"
                + "              Leaf with 1 objects: 2\r\n"
                + "            E\r\n" + "          Leaf with 1 objects: 10\r\n"
                + "        E\r\n" + "      E\r\n" + "    E\r\n" + "  E\r\n"
                + "", outputStream.toString());
        }
    }

    /**
     * Test syntax sample input
     * 
     * @throws Exception
     */
    public void testMain2() throws Exception {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream)) {
            System.setOut(printStream);
            try (FileWriter writer = new FileWriter("testMain2.txt")) {
                writer.write("insert 1\r\n"
                    + "Overview of HCI Research at VT\r\n"
                    + "0610051600 90 10 10 45\r\n"
                    + "HCI Computer_Science VT Virginia_Tech\r\n"
                    + "This seminar will present an overview of HCI "
                    + "research at VT\r\n"
                    + "\r\n" + "\r\n" + "insert 2\r\n"
                    + "Computational Biology and Bioinformatics in CS at "
                    + "Virginia Tech\r\n"
                    + " 0610071600 60 20 10 30\r\n"
                    + "Bioinformatics computation_biology Biology "
                    + "Computer_Science VT Virginia_Tech\r\n"
                    + " Introduction to bioinformatics and computation "
                    + "biology\r\n"
                    + "insert 3\r\n" + "Computing Systems Research at VT\r\n"
                    + "0701250830 30 30 10 17\r\n"
                    + "high_performance_computing grids VT computer "
                    + "science\r\n"
                    + " Seminar about the Computing systems research at "
                    + "VT\r\n"
                    + " \r\n" + "insert 10\r\n"
                    + "Overview of HPC and CSE Research at VT\r\n"
                    + "0703301125 35 0 0 25\r\n"
                    + " HPC CSE computer_science \r\n"
                    + "Learn what kind of research is done on HPC and CSE "
                    + "at VT\r\n"
                    + "\r\n" + "insert 20\r\n"
                    + "Overview of HPC and CSE Research at VT\r\n"
                    + "0703301125 35 -1 0 25\r\n"
                    + " HPC CSE computer_science \r\n"
                    + "Learn what kind of research is done on HPC and CSE "
                    + "at VT\r\n"
                    + "\r\n" + "insert 20\r\n"
                    + "Overview of HPC and CSE Research at VT\r\n"
                    + "0703301125 35 0 -1 25\r\n"
                    + " HPC CSE computer_science \r\n"
                    + "Learn what kind of research is done on HPC and CSE "
                    + "at VT\r\n"
                    + "\r\n" + "insert 20\r\n"
                    + "Overview of HPC and CSE Research at VT\r\n"
                    + "0703301125 35 2000 0 25\r\n"
                    + " HPC CSE computer_science \r\n"
                    + "Learn what kind of research is done on HPC and CSE "
                    + "at VT\r\n"
                    + "\r\n" + "\r\n" + "insert 20\r\n"
                    + "Overview of HPC and CSE Research at VT\r\n"
                    + "0703301125 35 0 2000 25\r\n"
                    + " HPC CSE computer_science \r\n"
                    + "Learn what kind of research is done on HPC and CSE "
                    + "at VT\r\n"
                    + "");
            }
            SemSearch.main(new String[] { "128", "testMain2.txt" });
            assertEquals("Successfully inserted record with ID 1\r\n"
                + "ID: 1, Title: Overview of HCI Research at VT\r\n"
                + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
                + "Description: This seminar will present an overview of HCI "
                + "research at VT\r\n"
                + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
                + "Successfully inserted record with ID 2\r\n"
                + "ID: 2, Title: Computational Biology and Bioinformatics in "
                + "CS at Virginia Tech\r\n"
                + "Date: 0610071600, Length: 60, X: 20, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to bioinformatics and computation"
                + " biology\r\n"
                + "Keywords: Bioinformatics, computation_biology, Biology, "
                + "Computer_Science, VT, Virginia_Tech\r\n"
                + "Successfully inserted record with ID 3\r\n"
                + "ID: 3, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the Computing systems research"
                + " at VT\r\n"
                + "Keywords: high_performance_computing, grids, VT, computer,"
                + " science\r\n"
                + "Successfully inserted record with ID 10\r\n"
                + "ID: 10, Title: Overview of HPC and CSE Research at VT\r\n"
                + "Date: 0703301125, Length: 35, X: 0, Y: 0, Cost: 25\r\n"
                + "Description: Learn what kind of research is done on HPC"
                + " and CSE at VT\r\n"
                + "Keywords: HPC, CSE, computer_science\r\n"
                + "Insert FAILED - Bad x, y coordinates: -1, 0\r\n"
                + "Insert FAILED - Bad x, y coordinates: 0, -1\r\n"
                + "Insert FAILED - Bad x, y coordinates: 2000, 0\r\n"
                + "Insert FAILED - Bad x, y coordinates: 0, 2000\n",
                outputStream.toString());
        }
    }
}
