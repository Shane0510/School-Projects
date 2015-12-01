package organism;
import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {
		Organism lobster = new Arthropod("Homarus gammarus", 0, 0, "north", 2);
		Organism seahorse = new Arthropod("balama", 0, 0, "south", 1);
		ArrayList<Organism> tracker = new ArrayList<Organism>();
		TidePool creatures = new TidePool(tracker);
		creatures.add_new(lobster);
		creatures.add_new(seahorse);
/*		Arthropod lobster = new Arthropod("Homarus gammarus", 0, 0, "north", 2, 10);	
  		for(int i = 0; i<20; i++){
		System.out.println(lobster);
		lobster.move();
		}*/
	}

}
