package sprites;

/**
 * A abstract class that create the necessary instants for a child class.
 * This class is responsible for 
 */
public abstract class Sprite {
	protected char symbol;
	protected int row;
	protected int column;


	public Sprite(char symbol, int row, int column){
		
		// the symbol for the specific Sprite
		this.symbol = symbol;
		
		// the row which the Sprite resides
		this.row = row;
		
		// the column which the Sprite resides
		this.column = column;
		}
	
	public char getSymbol() {
		return symbol;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public String toString(){
		return "" + symbol; 
	}
}
