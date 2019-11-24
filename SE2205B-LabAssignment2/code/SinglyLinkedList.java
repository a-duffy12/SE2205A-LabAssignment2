public class SinglyLinkedList<E> {

	private static class Node<E> {

		// initialize variables for an arbitrary element and an arbitrary next node
		private E element;
		private Node<E> next;

		// constructor
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		// getter for the current element
		public E getElement() {
			return this.element;
		}

		// getter for the next node
		public Node<E> getNext() {
			return this.next;
		}

		// setter for the next node
		public void setNext(Node<E> n) {
			this.next = n;
		}
	}

	// variables for linkled list size, as well as the first and last nodes
	private int size;
	private Node<E> first;
	private Node<E> last;

	// constructor
	public SinglyLinkedList() {
		// set default parameters for a new singly linked list
		size = 0;
		first = null;
		last = null;
	}

	// returns the size of the list
	public int size() {
		return this.size;
	}

	// check to see if the linked list is
	public boolean isEmpty() {
		return size == 0;
	}

	// create the first element
	public E first() {
		return first.getElement();
	}

	// create the last element
	public E last() {
		return last.getElement();
	}

	// add new element to the first slot
	public void addFirst(E element) {
		Node<E> n = new Node<E>(element, first);
		if (isEmpty()) { // when empty, the new node is by default the last node
			last = n;
		}
		first = n; // the current node becomes the first node, and the size gets one larger
		size++;
	}

	// add a new element to the last slot
	public void addLast(E element) {
		Node<E> n = new Node<E>(element, null);
		if (isEmpty()) { // when empty, the new node is by default the first node
			first = n;
		}
		if (last != null) { // if there is already a last element, the last element becomes the next n
			last.setNext(n);
		}
		last = n; // current node becomes the first node, and the size gets one larger
		size++;
	}

	// remove the first element out of the queue
	public E removeFirst() {
		if (!isEmpty()) { // runs if there is at least one element in the queue
			E element = first.getElement(); // gets the element
			first = first.getNext(); // the next element in the queue becomes the first element
			size--; // queue size is reduced by 1
			return element; // returns the removed element
		}
		return null; // returns null if the queue is empty
	}

}