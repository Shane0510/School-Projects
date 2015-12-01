package waitlist;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BoundedWaitList<E> extends WaitList<E> {
	private int capacity;
	
	public BoundedWaitList(int capacity){
	    this.content = new ConcurrentLinkedQueue<>();		
	    this.capacity = capacity;
	}
	
	public BoundedWaitList(Collection<E> c){
		super(c);
	}

	public int getCapacity() {
		return capacity;
	}
	
	@Override
	public void add(E element){
		if (capacity > 1){
			super.add(element);
			capacity -= 1;	
		}
	}
	
	@Override
	public E remove(){
		super.remove();
		capacity += 1;
		return super.content.poll();
		}
	
	
	@Override
	public String toString(){
		return super.toString();
	}
}
