package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;


/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {


	// a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;
    
    
    // String contains symbols of sprites that DustBall can move onto
	private static final String dustballS= "" + Constants.CLEAN + Constants.DIRT + 
			Constants.DUST_BALL;
	
	// String contains symbols of sprites that Vacuum can move onto
	private static final String vacuumS = "" + Constants.CLEAN + Constants.DIRT + 
					Constants.DUST_BALL + Constants.DUMPSTER;
	
	// the number of rows in the grid
	private int numRows;
	
	// the number of columns in the grid
	private int numCols;
	
	// vacuum1's valid inputs order matters
	private static final String v1moves = "" + Constants.P1_DOWN + 
			Constants.P1_UP + Constants.P1_RIGHT + Constants.P1_LEFT;
	
	// vacuum1's valid inputs order matters
	private static final String v2moves = "" + Constants.P2_DOWN + Constants.P2_UP + Constants.P2_RIGHT + 
			Constants.P2_LEFT;
	//flag for DustBall
	private boolean flag = true;
	
	// for loop ConcurrentModificationException counter variables
	private List<DustBall> counter;
	


    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     */
    //the starter code assign List<> to instance variable then define it as arraylist in methods
    public VacuumGame(String layoutFileName) throws IOException {
    	// Assign the dirts type to ArrayList
        this.dirts = new ArrayList<Dirt>();
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        // assign numRows and numCols value
        this.numRows = dimensions[0];
        this.numCols = dimensions[1];
        this.grid = new ArrayGrid<Sprite>(this.numRows, this.numCols);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));
        // initiate nextLine to store each row's input
        String nextLine = "";
        // loop through the rows of the document
        for (int row = 0; row < this.numRows; row++){
        	nextLine = sc.nextLine();
        		// loop through the columns of the document
            	for (int i = 0; i < this.numCols; i++){
            	// create the corresponding sprites class and store to grid
            		if (nextLine.charAt(i)==Constants.CLEAN){
            			this.grid.setCell(row, i, new CleanHallway
            					(Constants.CLEAN, row, i));
            		}else if (nextLine.charAt(i)==Constants.WALL){
            			this.grid.setCell(row, i, new Wall(Constants.WALL, 
            					row, i));
            		}else if (nextLine.charAt(i)==Constants.DIRT){
            			Dirt newdirt = new Dirt(Constants.DIRT, row, i, 
            					Constants.DIRT_SCORE);
            			this.dirts.add(newdirt);
            			this.grid.setCell(row, i, newdirt);
            		}else if (nextLine.charAt(i)==Constants.DUMPSTER){
            			this.grid.setCell(row, i, new Dumpster
            					(Constants.DUMPSTER, row, i));
            		}else if (nextLine.charAt(i)==Constants.DUST_BALL){
            			DustBall newdustball = new DustBall
            					(Constants.DUST_BALL, row, i, 
            							Constants.DUST_BALL_SCORE);
            			this.dirts.add(newdustball);
            			this.grid.setCell(row, i, newdustball);
            		}else if (nextLine.charAt(i)==Constants.P1){
            			this.vacuum1 = new Vacuum(Constants.P1, row, i, 
            					Constants.CAPACITY);
            			this.grid.setCell(row, i, this.getVacuumOne());
            		}else{
            			this.vacuum2 = new Vacuum(Constants.P2, row, i, 
            					Constants.CAPACITY);
            			this.grid.setCell(row, i, this.getVacuumTwo());
            		}
            	}
            
            
        }

	// close scanner

        sc.close();
    }


    /**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    
    /**
     * get the Number of rows of the grid
     * @return a int, the total number of rows of the grid
     */
    public int getNumRows(){
    	return this.grid.getNumRows();
    }
    
    /**
     * get the Number of columns of the grid
     * @return a int, the total number of columns of the grid
     */
    public int getNumColumns(){
    	return this.grid.getNumColumns();
    }    

    /**
     * get and return the sprite in the cell
     * @param i the row number of the sprite we want to get
     * @param j the column number of the sprite we want to get
     * @return corresponding sprite on the grid
     */
    public Sprite getSprite(int i, int j){
    	return this.grid.getCell(i, j);
    }
    
    /**
     * get and return the ArrayGrid
     * @return the ArrayGrid  with Sprites inside
     */
	public Grid<Sprite> getGrid() {
		return this.grid;
	}
	
	/**
	 * get and return vacuum1 in the game
	 * @return player one, a Vacuum
	 */
	public Vacuum getVacuumOne() {
		return this.vacuum1;
	}

	/**
	 * get and return vacuum2 in the game
	 * @return player two, a Vacuum
	 */
	public Vacuum getVacuumTwo() {
		return this.vacuum2;
	}
	
	/**
	 * move the vacuum according to nextMove if it is a valid input
	 * move dustballs randomly
	 * @param nextMove a char Object, the user move input
	 * @return true iff one vacuum moved
	 */
	public boolean move(char nextMove){
		// did any vacuum move
		boolean moved = false;
		int move1 = v1moves.indexOf(nextMove);
		int move2 = v2moves.indexOf(nextMove);
		// for case that under is a DustBall
		Sprite under;
		// vacuum1 moves
		if(!(move1 == -1)){
			moveVacuum(vacuum1, move1);
			moved = true;
			under = vacuum1.getUnder();
			if (under.getSymbol() == Constants.DUST_BALL){
				flag = false;
			}
		// vacuum2 moves
		}else if (!(move2 == -1)){
			moveVacuum(vacuum2, move2);
			moved = true;
			under = vacuum2.getUnder();
			if (under.getSymbol() == Constants.DUST_BALL){
				flag = false;
			}
		}
		// we always moves every DustBall
		// counter for concurrenmodificationexception
		this.counter = new ArrayList<DustBall>();
		for (Dirt ball: dirts){
			if(ball.getSymbol() == Constants.DUST_BALL){
				this.counter.add((DustBall)ball);
			}
		}
		for (DustBall b: counter){
			moveDustBall(b);
		}
		this.grid.setCell(this.getVacuumOne().getRow(), 
				this.getVacuumTwo().getColumn(),this.getVacuumOne());	
		this.grid.setCell(this.getVacuumTwo().getRow(), 
				this.getVacuumTwo().getColumn(), this.getVacuumTwo());
		return moved;
		}
	
	
	

	/**
	 * helper method for move that makes each DustBall move randomly 
	 * on the grid if possible 
	 * @param ball the DustBall we want to move
	 */
	private void moveDustBall(DustBall ball){
		int row = ball.getRow();
		int col = ball.getColumn();
		// maximum size is 4
		List<Integer> moves;
		moves = validMoves(ball);
		int size = moves.size();
		if (!(size == 0)){
		// valid move available
			int direction = random.nextInt(size);
			// move up
			if (moves.get(direction) == 0){
				row += Constants.DOWN;
				moveOnto(ball, row, col);
				addDirt(row - 1, col);
			// move down
			}else if (moves.get(direction) == 1){
				row += Constants.UP;
				moveOnto(ball, row, col);
				addDirt(row + 1, col);
			}else if(moves.get(direction) == 2){
				col += Constants.RIGHT;
				moveOnto(ball, row, col);
				addDirt(row, col - 1);
			}else{
				col += Constants.LEFT;
				moveOnto(ball, row, col);
				addDirt(row, col + 1);
			}
		}
	// else no valid move ball don't move
	}	
	
	/**
	 * move the vacuum according to the user input
	 * @param player the vacuum we are trying to move
	 * @param move the user input
	 */
	private void moveVacuum(Vacuum player, int move){
		int row = player.getRow();
		int col = player.getColumn();	
		// valid move indexes
		List<Integer> vali;
		// valid vacuum1 input & surroundings allow vacuum1 to move
		// set cell before changing vacuum.under
		vali = validMoves(player);
		if (!(vali.size() == 0)){
			this.grid.setCell(row, col, player.getUnder());
			
			if (vali.contains(0) && move == 0){
				row += Constants.DOWN;
			}else if (vali.contains(1) && move == 1){
				row += Constants.UP ;
			}else if (vali.contains(2) && move == 2){
				col += Constants.RIGHT;
			}else if (vali.contains(3) && move == 3){
				col += Constants.LEFT;
			}
			
			moveOnto(player, row, col); 
			
			}this.grid.setCell(row, col, player);
	}
	

	/**
	 * helper method of moveDustBall
	 * necessary steps when moving the Dustball to grid[row][col]
	 * @param item the dustball we trying to move
	 * @param row the row dustball is going
	 * @param col the col dustball is going
	 */
	private void moveOnto(DustBall item, int row, int col){
		Sprite cell = this.getSprite(row, col);
		
		// item is a DustBall
			((DustBall)item).moveTo(row, col);
			// onto a DustBall
			if (cell.getSymbol() == Constants.DUST_BALL){
				this.grid.setCell(row, col, item);
				this.dirts.remove(cell);
			// onto a Dirt
			}else if (cell.getSymbol() == Constants.DIRT){
				this.grid.setCell(row, col, item);		
				this.dirts.remove(cell);
			// onto a CleanHallway
			}else if(cell.getSymbol() == Constants.CLEAN){
				this.grid.setCell(row, col, item);
			}
			}			
		// item is a Vacuum
	/**
	 * same method but it moves Vacuum 
	 * @param item
	 * @param row
	 * @param col
	 */
	private void moveOnto(Vacuum item, int row, int col){
		Sprite cell = this.getSprite(row, col);
	
		// modify the vacuum's instances
		((Vacuum) item).moveTo(row, col);
			// 	onto a DustBall or Dirt and cleans it
		if ((cell.getSymbol() == Constants.DUST_BALL)){
			// DustBall and clean()
			if(((Vacuum) item).clean(Constants.DUST_BALL_SCORE)){
				((Vacuum) item).setUnder(new CleanHallway(Constants.CLEAN, row, col));
				this.dirts.remove(cell);
			}
			// Dirt and clean() 
		}else if(cell.getSymbol() == Constants.DIRT){
			if (((Vacuum) item).clean(Constants.DIRT_SCORE)){
				((Vacuum) item).setUnder(new CleanHallway(Constants.CLEAN, row, col));
				this.dirts.remove(cell);
			}
		}else if (cell.getSymbol() == Constants.DUMPSTER){
			((Vacuum) item).empty();
			((Vacuum) item).setUnder(cell);
		// onto a CleanHallway
		}else if (cell.getSymbol() == Constants.CLEAN){
			((Vacuum) item).setUnder(cell);
		// onto a Dirt or DustBall and didn't clean it
		}else{
			((Vacuum) item).setUnder(cell);
		}

		this.grid.setCell(row, col, item);
	}
	
	
	// takes care of the cell DustBall moved out of
	// create new dirt
	private void addDirt(int row, int col){
			Dirt newDirt = new Dirt(Constants.DIRT, row, col, 
					Constants.DIRT_SCORE);
		if (flag = false){
			// DustBall was under a Vacuum
			((Vacuum)this.getSprite(row, col)).setUnder(newDirt);
			// reset flag
			flag = true;
		}else{
			this.grid.setCell(row, col, newDirt);
			}	this.dirts.add(newDirt);	
	}
	
	
	// list of possible moves for the item DustBall/Vacuum
	// note: not creating a interval but a list of index
	// order matters
	private List<Integer> validMoves(Sprite item){
		List<Integer> result = new ArrayList<Integer>();
		int row = item.getRow();
		int col = item.getColumn();
		if (validCell(item, row + 1, col)){
			result.add(0);
		}if (row > 0 && validCell(item, row - 1, col)){
			result.add(1);
		}if (validCell(item, row, col + 1)){
			result.add(2);
		}if (col > 0 && validCell(item, row, col - 1)){
			result.add(3);
		}
		return result;
	}
	
	// helper method for move that returns a string containing 
	//the symbols of sprites that the item can move onto
	/** Returns true if item can move onto the cell at (row, col)
	 * @param item A DustBall or Vacuum
	 * @param row the row index of the cell we want to move to
	 * @param col the column index of the cell we want to move to
	 * @return boolean true iff we can move the item to the cell
	 *  in fact we will only put DustBall or Vacuum as item
	 */
	private boolean validCell(Sprite item, int row, int col){		
		if (row > this.numRows | col > this.numCols){
			return false;
		} 
		String valid = "";
		if (item.getSymbol() == Constants.DUST_BALL){
			valid = VacuumGame.dustballS;
		}else {
			valid = VacuumGame.vacuumS;
		}
		if (!(valid.indexOf(this.getSprite(row, col).getSymbol()) == -1)){
			return true;
		}else {
			return false;
		}
	}
	
  
	public boolean gameOver(){
		return this.dirts.isEmpty();
	}
	
	public int getWinner(){
		int score1 = vacuum1.getScore();
		int score2 = vacuum2.getScore();
		if (score1 < score2){
			return 2;
		}else {
			return 1;
		}
	}
	// for vacuum under set under game.VacuumGame.getSprite(row, column);

    
}
                          
    
