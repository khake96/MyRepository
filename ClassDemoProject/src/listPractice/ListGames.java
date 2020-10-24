package listPractice;

import java.util.PriorityQueue;
import java.util.Queue;

public class ListGames {

	public static void main(String[] args) {

//		/* PriorityQueue natural ordering (why String objects
//		are not alphabetical in natural ordering?)
//		*/
//		Queue<Integer> q = new PriorityQueue();
//		q.add(4);
//		q.add(2);
//		q.add(3);
//		q.add(1);
//		q.add(5);
//		q.add(6);
//		// See what is at the head
//		System.out.println(q.element());
//		System.out.println("\nQueueElements: "+ q.toString());
//		q.remove();
//		System.out.println("QueueElements after 1 removal: "+q.toString());
//		q.add(81);
//		System.out.println("Queue after add " + q.toString());
//		Integer n = 100;
//		if(q.contains(n)) {
//			System.out.println("I found "+n+" in the PriorityQueue.");
//		} else System.out.println(n+" not found.");
		
		MyBasicStringQueue sq = new MyBasicStringQueue();
		
		System.out.println("SQ isEmpty result: "+sq.isEmpty());
		//Add intial elements
		sq.add("First");
		sq.add("Second");
		sq.add("Third");
		sq.add("Fourth");
		System.out.println(sq.toString());  // print queue
		System.out.println("SQ is Empty result: " + sq.isEmpty()); // check if empty
		sq.remove("Extra"); // remove is currently implemented as pull should be but not returning the string
		sq.remove("Extra"); 
		sq.remove("Extra"); 
		sq.remove("Extra"); 
		System.out.println(sq.toString());


	}

}
