package game;



public class ArrayGrid<T> implements Grid<T> {
	private int numRows;
	private int numColumns;
    private T[][] cells;

	public ArrayGrid(int numRows, int numColumns){
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.cells = (T[][])new Object[numRows][numColumns];

    }
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	
	public void setCell(int row, int column, T item){
       this.cells[row][column] = item;
	
	}
	
	public T getCell(int row, int column){
        return this.cells[row][column];
	}
    
	public boolean equals(Object other){
		return (other instanceof ArrayGrid && 
				this.toString().equals(other.toString()));
	}

    //which private variable do we use
	public String toString() {
        String result = "";
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numColumns; j++){
            	result += ((T) cells[i][j]).toString();
            }
            result += "\n";
        }
                return result;
	}		
}
