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

import student.TestCase;

/**
 * Tests the methods in the SeminarDB class.
 * 
 * @author Pranay Dhalwani
 * @version 09.02.2023
 *
 */

public class SeminarDBTest extends TestCase {
    /**
     * Tests the SeminarDB methods using the sample input. 
     */
    public void testSampleInput() {
        SeminarDB semDb = new SeminarDB(128);
        semDb.print("location");
        semDb.print("ID");
        semDb.searchExact("ID", "1");
        semDb.searchExact("keyword", "VT");
        String[] strArr = { "HCI", "Computer_Science", "VT", "Virginia_Tech" };
        Seminar sem1 = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, strArr,
            "This seminar will present an overview of HCI research at VT");
        semDb.insert(sem1);
        strArr = new String[] { "Bioinformatics", "computation_biology",
            "Biology", "Computer_Science", "VT", "Virginia_Tech" };
        Seminar sem2 = new Seminar(2,
            "Computational Biology and Bioinformatics in CS at Virginia Tech",
            "0610071600", 60, (short)10, (short)10, 30, strArr,
            "Introduction to bioinformatics and computation biology");
        semDb.insert(sem2);
        strArr = new String[] { "high_performance_computing", "grids", "VT",
            "computer", "science" };
        Seminar sem10 = new Seminar(10, "Computing Systems Research at VT",
            "0701250830", 30, (short)30, (short)10, 17, strArr,
            "Seminar about the Computing systems research at VT");
        semDb.insert(sem10);
        strArr = new String[] { "HPC", "CSE", "computer_science" };
        Seminar sem3 = new Seminar(3, "Overview of HPC and CSE Research at VT",
            "1203301125", 35, (short)0, (short)0, 25, strArr,
            "Learn what kind of research is done on HPC and CSE at VT");
        semDb.insert(sem3);
        semDb.print("ID");
        semDb.print("date");
        semDb.print("keyword");
        semDb.print("cost");
        semDb.print("location");
        strArr = new String[] { "HPC", "CSE", "computer_science" };
        Seminar sem11 = new Seminar(10,
            "Overview of HPC and CSE research at VT", "0703301125", 35,
            (short)0, (short)0, 25, strArr,
            "Learn what kind of research is done on HPC and CSE at VT");
        semDb.insert(sem11);
        semDb.searchExact("keyword", "VT");
        semDb.searchExact("keyword", "vt");
        semDb.searchExact("ID", "1");
        semDb.searchRange("cost", "30", "50");
        semDb.searchLocation(-1, 0, 1);
        semDb.searchLocation(-1, 0, 2000);
        semDb.searchLocation(10, 10, 0);
        semDb.searchLocation(11, 11, 0);
        semDb.searchLocation(10, 10, 20);
        semDb.searchRange("date", "0", "1");
        semDb.delete(1);
        semDb.delete(1);
        semDb.print("ID");
        semDb.print("location");
    }
    
}