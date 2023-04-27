package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.size = 0;
		
		this.head.next = tail;
		this.tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	private boolean isValidIndex(int index) {
		if(size == 0 || (size > 0 && index >= size) || index < 0) {
			return false;
		}
		return true;
	}
	
	public boolean add(E element ) 
	{
		if(element == null) {
			throw new NullPointerException();
		}
		
		LLNode<E> last = head;
		LLNode<E> newNode = new LLNode<E>(element);
		
		while(last.next!=tail) {
			last = last.next;
		}
		
		this.tail.prev = newNode;
		newNode.next = this.tail;
		newNode.prev = last;
		last.next = newNode;
		size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(!isValidIndex(index)) {
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> node = head.next;
		int i = 0;
		while(node!=tail) {
			if(index == i) {
				return node.data;
			}
			i++;
			node = node.next;
		}
		
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element == null) {
			throw new NullPointerException();
		}
		
		LLNode<E> prevNode = head;
		LLNode<E> newNode = new LLNode<E>(element);
		int i = 0;
		
		while(prevNode!=tail) {
			if(index == i) {
				prevNode.next.prev = newNode;
				newNode.next = prevNode.next;
				newNode.prev = prevNode;
				prevNode.next=newNode;
				size++;
				break;
			}else {
				i++;
				prevNode = prevNode.next;
			}
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(!isValidIndex(index)) {
			throw new IndexOutOfBoundsException();
		}
		
		E element = null;
		
		LLNode<E> node = head.next;
		int i = 0;
		while(node!=tail) {
			if(index == i) {
				element = node.data;
				node.prev.next  = node.next;
				node.next.prev = node.prev;
				size--;
				break;
			}
			i++;
			node = node.next;
		}
		
		return element;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
