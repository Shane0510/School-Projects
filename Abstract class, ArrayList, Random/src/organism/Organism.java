package organism;

/**
 * An Organism in a tide pool.
 */
public abstract class Organism {
    
    /** 
     * An Organism can move in one of these directions.
     */
    public static final String [] VALID_DIRECTIONS = 
        {"north", "south", "east", "west"};
    
    protected String name;
    protected int xCoord;   // the x coordinate
    protected int yCoord;   // the y coordinate
    protected int speed;    // # of units of distance it moves in a unit of time 
    protected String direction; // one of VALID_DIRECTIONS
    
    /**
     * Creates a new Organism with the given name, x and y coordinates, 
     * speed, and direction.
     * @param name the name of the new Organism
     * @param xCoord the x coordinate of the new Organism
     * @param yCoord the y coordinate of the new Organism
     * @param direction the direction of the new Organism,
     *        must be one of VALID_DIRECTIONS
     * @param speed the speed of the new Organism
     */
    public Organism(String name, int xCoord, int yCoord, String direction,
        int speed) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.speed = speed;
        this.direction = direction;
    }
    
    /**
     * Changes the position of this Organism according its current 
     * speed and direction: move for one unit of time.
     */
    public void move() {
        switch (direction) {
        case "north": yCoord += speed; break;
        case "south": yCoord -= speed; break;
        case "east": xCoord += speed; break;
        case "west": xCoord -= speed;
        }
    }
    /**
     * Return the name of this Organism 
     * name: the name of the Organism
     * @return 	    the name as a String object of this Organism
     */
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public static String[] getValidDirections() {
		return VALID_DIRECTIONS;
	}

	@Override
	public String toString() {
		return "Organism: name=" + name + ", xCoord=" + xCoord + ", yCoord=" + yCoord;
	}

	
    
}
