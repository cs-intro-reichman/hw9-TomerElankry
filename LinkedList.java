/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		int count=0;
		Node current=first;
		while (count<index) {
			current=current.next;
			count++;
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if(index<0 || index >size) throw new IllegalArgumentException("Not a valid index");
		if(index==0){
			addFirst(block);
			return;
		}
		if(index==size)
		{
			addLast(block);
			return;
		}
		Node newM =new Node(block);
		Node current =first;
		int count=0;
		while (count<index-1) {
			count++;
			current = current.next;
		}
		newM.next = current.next;  
        current.next = newM;
		size++;
		return;
		
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		if(block==null) return;
		Node newL = new Node(block);
		if (size == 0) {
			first = newL;
			last = newL;
		} else {
			last.next = newL;
			last = newL;
		}
		size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		if(block==null) return;
		Node newF = new Node(block);
		if (size == 0) {
			first = newF;
			last = newF;
		} else {
			newF.next = first;
			first = newF;
		}
		this.size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if(index<0 || index >=size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		int count=0;
		Node current = first;
		while (count<index)
		{
			current=current.next;
			count++;
		}
		return current.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		if(block==null) return -1;
		int count=0;
		Node current = first;
		while (current != null) {
			if (current.block.equals(block)) return count;
			count++;
			current = current.next;
		}
		
		  return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (node == null || first == null) return;  
		if (first == node) {  
			first = first.next;
			if (first == null) {  
				last = null;
			}
			size--;
			return;
		}
		Node current = first;
		while (current.next != null && current.next != node) {
			current = current.next;
		}
		if (current.next == node) {  
			current.next = node.next;
			if (node == last) {  
				last = current;
			}
			size--;
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if(index >= size || index < 0)
		{
			throw new IllegalArgumentException("invalid index");
		}
		if(index==0){
			first = first.next;
			size--;
			if (first == null) {
				last = null;
			}
			return;
		}
		if(index==size-1) 
		{
			last=null;
			size--;
			return;
		}
		int count=0;
		Node current = first;
		while (count<index-1) {
			current = current.next;
			count++;
		}
		if (index == size - 1) {
			last = current;  
			current.next = null;
		} else {
			current.next = current.next.next;  
		}
	
		size--;
		return;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if (block == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
	
		if (first == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
	
		if (first.block.equals(block)) {
			first = first.next;
			if (first == null) {  
				last = null;
			}
			size--;
			return;
		}
		Node current = first;
		while (current.next != null && !current.next.block.equals(block)) {
			current = current.next;
		}
		if (current.next == null) {
			throw new IllegalArgumentException("The given memory block is not in this list");
		}
	
		if (current.next == last) {
			last = current;  
		}
		current.next = current.next.next;  
	
		size--;
	}

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		if(first==null) return "";
		Node current = first;
		String s=" ";
		while (current!=null) {
			s+=current.toString();
			current=current.next;
		}
		return s;
	}
}