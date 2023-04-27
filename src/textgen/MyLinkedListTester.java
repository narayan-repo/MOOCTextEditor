/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;


/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			list1.remove(-5);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			list1.remove(500);
			fail("Check out of bounds");
		}catch(IndexOutOfBoundsException e) {
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        try {
        	longerList.add(null);
        	fail("Check add null element");
        }catch (NullPointerException e) {
        	
        }
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
        	longerList.add(i);
        	assertEquals("Add: check "+ i +" element",(Integer) i, longerList.get(i));
		}      
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: ", 0, emptyList.size());
		assertEquals("Size: ", LONG_LIST_LENGTH, longerList.size());
		
		longerList.add(3);
		assertEquals("Size: ",LONG_LIST_LENGTH + 1,longerList.size());
		
		longerList.remove(3);
		assertEquals("Size: ",LONG_LIST_LENGTH,longerList.size());
		
		longerList.add(3,3);
		assertEquals("Size: ",LONG_LIST_LENGTH+1,longerList.size());
		
		for (int i = 0; i < longerList.size(); i++) {
			assertEquals("Size: ", LONG_LIST_LENGTH + 1 - i, longerList.size());
			longerList.remove(i);
		}
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        int a = 50;
        int b = 60;
        int c = 70;
        
        int bIndex = 5;
        
        longerList.add(0, a);
        longerList.add(bIndex, b);
        longerList.add(longerList.size/2, c);
        
        try {
        	longerList.add(2, null);
        	fail("Check null pointer exception");
        }catch(NullPointerException e) {
        	
        }
        
        try {
        	longerList.add(-1, 4);
        	fail("Check out of bounds");
        	
        }catch(IndexOutOfBoundsException e) {
        	
        }
        
        try {
        	longerList.add(879, 43);
        	fail("Check out of bounds");
        	
        }catch(IndexOutOfBoundsException e) {
        	
        }
        
        assertEquals("Add at Index: Check add at beginning", (Integer)a, longerList.get(0));
        assertEquals("Add at Index: Check add at bIndex", (Integer)b, longerList.get(bIndex));
        assertEquals("Add at Index: Check add at beginning", (Integer)c, longerList.get(longerList.size/2));
        

        longerList.add(longerList.size(), 45);
        assertEquals("Add at Index: Check add at end", (Integer) 45, longerList.get(longerList.size-1));
        
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    longerList.set(1,4);
	    assertEquals("Set :", (Integer) 4, longerList.get(1));
	    assertEquals("Set :", (Integer) 4, longerList.set(1, 1));
	    
	    try {
	    	longerList.set(-5, 5);
	    	fail("Check out of bounds");
	    }catch(IndexOutOfBoundsException e) {
	    	
	    }
	    
	    try {
	    	longerList.set(43, 5);
	    	fail("Check out of bounds");
	    }catch(IndexOutOfBoundsException e) {
	    	
	    }
	    
	    try {
	    	emptyList.set(0, 5);
	    	fail("Check out of bounds");
	    }catch(IndexOutOfBoundsException e) {
	    	
	    }
	    
	}
	
}
