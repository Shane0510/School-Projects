package organism;

import java.util.Random;

public class Arthropod extends Organism{
	public int legs;
	public Random jeans = new Random();
	public Arthropod(String name, int xCoord, int yCoord, String direction, int speed) {
		super(name, xCoord, yCoord, direction, speed);
		// TODO Auto-generated constructor stub
	}

	public Arthropod(String name, int xCoord, int yCoord, String direction, int speed, int legs) {
		super(name, xCoord, yCoord, direction, speed);
		this.legs = legs;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	@Override
	public String toString() {
		return super.toString()+ ", Legs=" + this.legs;
	}
	@Override
	public void move(){
		int water = jeans.nextInt(4);
		setDirection(VALID_DIRECTIONS[water]);
		super.move();
	}
	
	

}

