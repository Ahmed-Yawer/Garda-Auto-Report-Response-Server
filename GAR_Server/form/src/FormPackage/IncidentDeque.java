package FormPackage;

import FormPackage.EmptyDequeException;

/**this program is implement the linked based queue through implementing the LinkedDeque class with the Node inner class.
 * where each node in the queue consist of three parts (object and two pointers to the next object and to the previous object).
 * all the method to manipulate the stack is implemented 
 */

/**this class implement the linked based queue*/
public class IncidentDeque {	
	
	/**the NodeQ class is an inner class that implement the structure of the node that stored in linked deque.
	 * Although the node class is an inner class so i can used it's instance (element prev and next)
	 * directly, to maintain the object oriented structure i have implemented the setter and getter
	 * method to access them.
	 * */  
	public class NodeQ {
		private Object element;
		private NodeQ next;
		private NodeQ prev;
		/**node default constructor
		 */
		public NodeQ() {
			this.element = null;
			this.next = null;
			this.prev = null;
			}
		/**node constructor with one parameter.
		 * @param element is the element want to be added to the  queue
		 */
		public NodeQ(Object element) {
			this.element = element;
			this.next = null;
			this.prev = null;
			}
		/** the getElement method enable of getting the element value.
		 * @return the element instance
		 */
		public Object getElement (){
			return element;
		}
		/** the getNext method enable of getting the value of the next instance.
		 * @return the next instance
		 */
		public NodeQ getNext (){
			return next;
		}
		/** the getPrev method enable of getting the value of the prev instance.
		 * @return the prev instance
		 */
		public NodeQ getPrev (){
			return prev;
		}
		/** the setElement method setting and element value
		 * @param o is the value want to be stored in the element instance
		 */
		public void setElement(Object o){
			element = o;
		}
		/** the setNext method setting and next value
		 * @param n is the value want to be stored in the next instance 
		 */
		public void setNext (NodeQ n){
			next = n;
		}
		/** the setPrev method setting and prev value
		 * @param p is the value want to be stored in the prev instance 
		 */
		public void setPrev (NodeQ p){
			prev = p;
		}
		/** override the to Sting method to print only the element part of the node object
		 * @return the element instance as string 
		 */
		public String toString() {
			return element.toString();
			}
		}
	/** stack instance*/
	int size;
	NodeQ front;
	NodeQ rear;
	/**default linked deque constructor
	 */
	public IncidentDeque() {
		front = null;
		rear = null;
		size = 0;
		}
	/** isEmpty method show wither the queue is empty of not
	 * @return true if the queue is empty or false if not
	 */
	public boolean isEmpty(){
		if (size == 0)
				return true;
		else
			return false;
	}
	/** this method called to get the current size of the queue 
	 * @return the size of the queue
	 */
	public int size(){
		return size;
	}
	/** front method read the element of the front node in the queue without removing it from the queue 
	 * @return the element of the node that at the front of in the queue
	 */
	public Object getFirst()throws EmptyDequeException {
		if (isEmpty())
			throw new EmptyDequeException();
		else{
		return front.getElement();
		}
	}
	/** rear method read the element of the rear node in the queue without removing it from the queue 
	 * @return the element of the node that at the rear of in the queue
	 */
	public Object getLast()throws EmptyDequeException {
		if (isEmpty())
			throw new EmptyDequeException();
		else{
		return rear.getElement();
		}
	}	
	/** insertFirst method add a new node to the front of the queue
	 * @param o is the element want to me added to the queue 
	 */
	public void insertFirst(Object o){
		NodeQ node = new NodeQ (o);
		node.setNext(front);
		if (front == null)
			rear = node;
		else
			front.setPrev(node);
		front = node;
		size ++;		
	}
	public void insertReprotPriority(Object o){
		//if the queue is empty, insert the report last.
		if (size == 0){
			insertLast(o);
			return;
		}
		//save the report in a node and get its priority.
		NodeQ node = new NodeQ (o);
		int reportPriority =((ReportDetails)node.getElement()).getpriorityLevel();
		//make a temporary node to look through the queue starting from the front.
		NodeQ searchNode = new NodeQ();
		searchNode = front;
		int firstIteration = 0;
		do{
			int currentPriority =((ReportDetails)searchNode.getElement()).getpriorityLevel();
			if (reportPriority >= currentPriority){
				searchNode = searchNode.getNext();
				firstIteration = 1;
			}
			else{
				if (firstIteration == 1){
					searchNode.getPrev().setNext(node);
				}
				node.setPrev(searchNode.getPrev());
				searchNode.setPrev(node);
				node.setNext(searchNode);
				size ++;
				if(firstIteration == 0){
					firstIteration = 1;
					front = node;
				}
				return;
			}
		}while (searchNode!= null);
		// when reaching  the end of the queue insert the report last;
		insertLast(o);
		return;
	}
	/** insertLast method add a new node at the rear of the queue
	 * @param o is the element want to me added to the queue 
	 */
	public void insertLast (Object o){
		NodeQ node = new NodeQ (o);
		node.setPrev(rear);
		if (rear == null)
			front = node;		
		else
			rear.setNext(node);
		rear = node;
		size ++;
	}
	/** removeLast method read the element value of the node that at the rear of the queue and remove it from the queue 
	 * @return return the element of the node that at the rear of the queue
	 */
	public Object removeLast ()throws EmptyDequeException {
		if (isEmpty())
			throw new EmptyDequeException();
		else{
		Object e = rear.getElement();
		if (rear.getPrev() == null)
			front = null;
		else
			rear.getPrev().setNext(null);
		rear = rear.getPrev();
		size --;
		return e;
		}
	}
	/** removeFirst method read the element value of the node that at the front of the queue and remove it from the queue 
	 * @return return the element of the node that at the front of the queue
	 */
	public Object removeFirst() throws EmptyDequeException {
		if (isEmpty())
			throw new EmptyDequeException();
		else{	
		Object e = front.getElement();
		if (front.getNext() ==  null)
			rear = null;
		else
			front.getNext().setPrev(null);
		front = front.getNext();
		size --;
		return e;
		}
	}
	
	/** override the toString method so it print the size of the queue then print out all the queue elements form the front to
	 * the rear
	 * @return string of all the size and all the queue elements  
	 */
	public String toString() {
		String output = "";
		NodeQ node = front;
		while (node != null) {
			output = output + "\tname: "+((ReportDetails)node.getElement()).getName().toString() + " | " 
					+ "Phone number: " + ((ReportDetails)node.getElement()).getPhoneNumber().toString() + " | "
					+ "Address: " + ((ReportDetails)node.getElement()).getHomeAddress().toString() + " | "
					+ "The incident: " + ((ReportDetails)node.getElement()).getTheReport().toString() + " | "
					+ "Latitute: "+ Double.toString(((ReportDetails)node.getElement()).getLat()) + " | "
					+ "Longitute: " +Double.toString(((ReportDetails)node.getElement()).getLng()) + " | "
					+ "piriorty: " + Integer.toString(((ReportDetails)node.getElement()).getpriorityLevel()) + " | "
					+ "\n";
			node = node.getNext();
			}
		return "There are " + size + " report(s) in the queue. \n" + output;
		}
}
