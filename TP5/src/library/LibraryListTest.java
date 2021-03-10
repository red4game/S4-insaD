/**
 * TP5 : Collections
 */
package library;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit tests for the Library class
 * @author Manuel Bouillon
 * @version 2015.02.27
 */
public class LibraryListTest {

	/**
	 * Test method for {@link Library#Library()}.
	 */
	@Test
	public void testLibrary() {
		LibraryList library = new LibraryList();
		assertNotNull(library);
	}

	/**
	 * Test method for {@link LibraryList#LibraryList(Collection)}.
	 */
	@Test
	public void testLibraryCollectionOfDocument() {
		Collection<Document> c = new LinkedList<Document>();
		List<String> l = new ArrayList<String>();
		l.add("Test");
		c.add(new Document(1, "Un", l));
		c.add(new Document(2, "Deux", l));
		c.add(new Document(3, "Trois", l));
		LibraryList library = new LibraryList(c);
		assertTrue(library.size() == 3);
	}

	/**
	 * Test method for {@link LibraryList#toString()}.
	 */
	@Test
	public void testToString() {
		Collection<Document> c = new LinkedList<Document>();
		List<String> l = new ArrayList<String>();
		l.add("Test");
		c.add(new Document(1, "Un", l));
		c.add(new Document(2, "Deux", l));
		c.add(new Document(3, "Trois", l));
		LibraryList library = new LibraryList(c);
		assertFalse(library.toString().equals(""));
	}

	/**
	 * Test method for {@link LibraryList#add(Document)}.
	 */
	@Test
	public void testAdd() {
		List<String> l = new ArrayList<String>();
		l.add("Test");
		LibraryList library = new LibraryList();
		library.add(new Document(1, "Un", l));
		library.add(new Document(2, "Deux", l));
		library.add(new Document(3, "Trois", l));
		assertTrue(library.size() == 3);
	}

	/**
	 * Test method for {@link LibraryList#borrow(int)}.
	 */
	@Test
	public void testBorrow() {
		List<String> l = new ArrayList<String>();
		l.add("Test");
		LibraryList library = new LibraryList();
		library.add(new Document(1, "Un", l));
		library.add(new Document(2, "Deux", l));
		library.add(new Document(3, "Trois", l));
		Document doc = library.borrow(2);
		assertTrue(doc.getBarCode() == 2);
		assertTrue(doc.getTitle().equals("Deux"));
		assertFalse(doc.isAvailable());
		assertNotNull(library.borrow(2));
		assertNull(library.borrow(-3));
	}

	/**
	 * Test method for {@link LibraryList#borrow(int)} efficiency.
	 */
	@Test
	public void testBorrowEfficiency() {
		LibraryList library = new LibraryList(Document.loadFromFile("relire-full.csv"));
		long begin = System.currentTimeMillis();
		library.borrow(123456789);
		library.borrow(234567891);
		library.borrow(345678912);
		library.borrow(456789123);
		library.borrow(567891234);
		library.borrow(678912345);
		library.borrow(789123456);
		library.borrow(891234567);
		library.borrow(912345678);
		library.borrow(123456789);
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin)) / 1000f;
		System.out.println("Time : " + time);
		assertTrue(time < 0.01);
	}

	/**
	 * Test method for {@link LibraryList#takeBack(int)}.
	 */
	@Test
	public void testTakeBack() {
		List<String> l = new ArrayList<String>();
		l.add("Test");
		LibraryList library = new LibraryList();
		library.add(new Document(1, "Un", l));
		library.add(new Document(2, "Deux", l));
		library.add(new Document(3, "Trois", l));
		Document doc = library.borrow(2);
		library.takeBack(2);
		assertTrue(doc.isAvailable());
	}

}
