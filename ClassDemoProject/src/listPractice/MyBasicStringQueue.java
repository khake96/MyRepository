package listPractice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

// Implementation using array

public class MyBasicStringQueue implements Queue<String>{
	
	private static int pointer=0;
	private static int capacity=25;
	private static String queue[]=new String[capacity];
	private static StringBuilder sb = new StringBuilder();
	

	@Override
	public boolean addAll(Collection<? extends String> c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		for(int i =0; i<pointer;i++) {
		sb = sb.append(queue[i]+", ");
		}
		return sb.subSequence(0,sb.length()-2).toString(); 	// remove last comma and return result
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(pointer==0) {
			System.out.println("Queue is empty.");
			return true;
		} else return false;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
        // check if queue is empty 
        if (pointer == 0) { 
            System.out.println("Queue is empty"); 
            return false; 
        } 
   

        else { 
        		queue[pointer] = null; 
             
                pointer--; 
        } 
        return true; 
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(String e) {
        // check if the queue is full
        if (pointer==capacity-1) { 
            System.out.println("Queue is full"); 
            return false; 
        } 
   
        // insert element at the rear 
        else { 
            queue[pointer] = e; 
            pointer++; 
        } 
        return true; 
	}

	@Override
	public String element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(String e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remove() {
		// TODO Auto-generated method stub
		return null;
	}

}
