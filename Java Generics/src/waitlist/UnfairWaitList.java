package waitlist;

import java.util.Collection;

public class UnfairWaitList<E> extends WaitList<E> {

	public UnfairWaitList() {
		super();
	}

	public UnfairWaitList(Collection<E> c) {
		super(c);
	}
	
	public void remove(E element){
		this.content.remove(element);
	}
	
	public void moveToBack(E element){
		this.content.add(element);
	}
	

}
