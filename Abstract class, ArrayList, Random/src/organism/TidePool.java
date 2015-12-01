package organism;

import java.util.ArrayList;

public class TidePool{
	ArrayList<Organism> tracker = new ArrayList<Organism>();
	
	public TidePool(ArrayList<Organism> tracker){
		this.tracker = tracker;
	}
	
	/**
	 * Add new Organism object to the TidePool
	 * 
	 * @param bla the variable referring to the Organism object to be added
	 * 
	 */
	public void add_new(Organism bla) {
			tracker.add(bla); 	
	}
	/**
	 * Using the move method in Class Organism to move all the 
	 * creatures inside the TidePool
	 * 
	 * @param fin the variable referring to the TidePool that we are 
	 * trying to implement move
	 * 
	 */
	public void move_all(TidePool fin){
			for(int x = 0; x < tracker.size(); x++){
				tracker.get(x).move();
			} 
	}
	public String toString(){
			return tracker.toString();
	}

}
