import student.TestCase;

/**
 * This class tests all methods in the SortedLinkedList class. 
 * @author Pranay Dhalwani
 * @version 10.11.2023
 *
 */
public class SortedLinkedListTest extends TestCase {
    private SortedLinkedList list;
    private Seminar sem1;
    private Seminar sem2;
    private Seminar sem3;
    private Seminar sem4;
    private Seminar sem5;
    private Seminar sem6;
    private Seminar sem7;
    private Seminar sem8;
    private Seminar sem9;

    
    /**
     * Set-up for all tests to be performed. 
     */
    public void setUp() {
        list = new SortedLinkedList();
        String[] stringArr = { "Important", "Aerospace" };
        sem1 = new Seminar(34, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole");
        sem2 = new Seminar(35, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole");
        sem3 = new Seminar(36, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole"); 
        sem4 = new Seminar(37, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole"); 
        sem5 = new Seminar(38, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole"); 
        sem6 = new Seminar(33, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole"); 
        sem7 = new Seminar(32, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole"); 
        sem8 = new Seminar(31, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole"); 
        sem9 = new Seminar(30, "Hello", "0510230436", 2, (short)43,
            (short)23, 65, stringArr, "The sly fox dug a hole"); 
        
    }
    
    /**
     * Tests insert()
     */
    public void testInsert()
    {
        list.insert(sem1);
        assertEquals(1, list.getSize());
        assertEquals(sem1, list.get(34));
        
        list.insert(sem2);
        assertEquals(2, list.getSize());
        assertEquals(sem2, list.get(35));
        
        list.insert(sem3);
        assertEquals(3, list.getSize());
        assertEquals(sem3, list.get(36));
        
        list.insert(sem4);
        assertEquals(4, list.getSize());
        assertEquals(sem4, list.get(37));
        
        list.insert(sem5);
        assertEquals(5, list.getSize());
        assertEquals(sem5, list.get(38));
        
        list.insert(sem6);
        assertEquals(6, list.getSize());
        assertEquals(sem6, list.get(33));   
        
        list.insert(sem7);
        assertEquals(7, list.getSize());
        assertEquals(sem7, list.get(32));
        
        list.insert(sem8);
        assertEquals(8, list.getSize());
        assertEquals(sem8, list.get(31));
        
        list.insert(sem9);
        assertEquals(9, list.getSize());
        assertEquals(sem9, list.get(30));    
        
        list.insert(sem2);
        assertEquals(10, list.getSize());
        assertEquals(sem2, list.get(35));
        
        SortedLinkedList list2 = new SortedLinkedList();
        list2.insert(sem1);
        assertEquals(sem1, list2.get(34));
        
        list2 = new SortedLinkedList();
        list2.insert(sem2);
        assertEquals(sem2, list2.get(35));
        
        list2 = new SortedLinkedList();
        list2.insert(sem3);
        assertEquals(sem3, list2.get(36));
        
        
        list2.delete(34);
        assertEquals(0, list2.getSize());
        list2.insert(sem2);
        assertEquals(sem2, list2.get(35));
        list2.delete(35);
        assertEquals(0, list2.getSize());
    }
    
    /**
     * Tests insert()
     */
    public void testInsert2()
    {
        list.insert(sem5);
        assertEquals(sem5, list.get(38));
        list.insert(sem4);
        assertEquals(sem4, list.get(37));
        list.insert(sem3);
        assertEquals(sem3, list.get(36));
        list.insert(sem2);
        assertEquals(sem2, list.get(35));
        list.insert(sem1);
        assertEquals(sem1, list.get(34));

        StringBuilder strB = new StringBuilder();
        
        for (Seminar sem : list.getAllSeminars()) {
            strB.append(sem.id() + " ");
        }
        assertEquals("34 35 36 37 38 ", strB.toString());
        
        SortedLinkedList list2 = new SortedLinkedList();
        
        list2.insert(sem1);
        assertEquals(sem1, list.get(34));
        list2.insert(sem2);
        assertEquals(sem2, list.get(35));
        list2.insert(sem3);
        assertEquals(sem3, list.get(36));
        list2.insert(sem4);
        assertEquals(sem4, list.get(37));
        list2.insert(sem5);
        assertEquals(sem5, list.get(38));
        
        strB = new StringBuilder();
        
        for (Seminar sem : list2.getAllSeminars()) {
            strB.append(sem.id() + " ");
        }
        
        assertEquals("34 35 36 37 38 ", strB.toString());
    }
    
    /**
     * Tests delete()
     */
    public void testDelete()
    {
        list.delete(1);
        assertEquals(0, list.getSize());
        
        list.insert(sem1);
        list.insert(sem2);
        list.insert(sem3);
        
        list.delete(35);
        assertEquals(2, list.getSize());
        
        list.delete(36);
        assertEquals(1, list.getSize());
        
        list.delete(34);
        assertEquals(0, list.getSize());
    }
    
    /**
     * Tests get()
     */
    public void testGet()
    {
        assertNull(list.get(1));
        list.insert(sem1);
        
        // Non-existing ID
        assertNull(list.get(35));
        
        // Existing ID
        assertEquals(sem1, list.get(34));
    }
    
    /**
     * Tests getFirst()
     */
    public void testGetFirst()
    {
        assertNull(list.getFirst());
        list.insert(sem1);
        assertEquals(sem1, list.getFirst());
        
        list.delete(34);
        assertNull(list.getFirst());
    }
}