package sprites;

import game.Constants;

public class Vacuum extends Sprite implements Moveable<Sprite> {
	private int score;
	private int capacity;
	private int fullness;
	private Sprite under;
	
	/**
	 * create Vacuum class
	 * @param symbol symbol of Vacuum
	 * @param row this vacuum's row coord
	 * @param column this vacuum's column coord
	 * @param capacity this vacuum's capacity for storing cleaned items
	 */
	public Vacuum(char symbol, int row,
			int column, int capacity) {
		super(symbol, row, column);
		this.capacity = capacity;
		this.under = new CleanHallway(game.Constants.CLEAN, row, column);
		this.score = Constants.INIT_SCORE;
	}
	
	/** 
	 * Clean if fullness after cleaning the sprite is still within 
	 * vacuum's capacity
	 * @param score The possible score change in vacuum's score 
	 * @return true iff the sprite can be cleaned
	 */
	public boolean clean(int score){
		if ((this.fullness + Constants.FULLNESS_INC) <= capacity){
			this.score += score;
			this.fullness += Constants.FULLNESS_INC;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 *  Set the fullness of the vacuum to Constants empty
	 */
	public void empty(){
		this.fullness = game.Constants.EMPTY;
	}

	/**
	 * Vacuum keeps track of what is under it
	 * this method returns the Sprite that is under this vacuum
	 * @return the Sprite that is under the Vacuum
	 */
	public Sprite getUnder() {
		return this.under;
	}

	/**
	 * Set the new Vacuum's under to the parameter under
	 * @param under the new Sprite that is under the  vacuum
	 */
	public void setUnder(Sprite under) {
		this.under = under;
	}
	
	/**
	 *  Get the score of this Vacuum and returns it
	 * @return a int representation of the score of this Vacuum
	 * 
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 *  change the row and the column of the vacuum to row2 and column2
	 *  @param row2 the row coord we are moving the vacuum to
	 *  @param column2 the column coord we are moving the vacuum to
	 */
	public void moveTo(int row2,
			int column2) {
		this.row = row2;
		this.column = column2;
		
	}
	
	
}
