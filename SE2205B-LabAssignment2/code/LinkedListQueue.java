public class LinkedListQueue<E> implements Queue<E> {

	// initialize a linked list
	private SinglyLinkedList<E> linkedList;

	// constructor
	public LinkedListQueue() {
		linkedList = new SinglyLinkedList<E>();
	}

	// returns the size of the list
	public int size() {
		return linkedList.size();
	}

	// returns whether the linked list is empty or not
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	// returns the first element in the linked list
	public E first() {
		return linkedList.first();
	}

	// adds a new node to the last position in the queue
	public void enqueue(E node) {
		linkedList.addLast(node);
	}

	// removes the first element of the queue
	public E dequeue() {
		return linkedList.removeFirst();
	}
}
