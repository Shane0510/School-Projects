package graph;

public class NoSuchNodeException extends Exception {
	
	public NoSuchNodeException(){
	// empty Exception		
	}
	public NoSuchNodeException(String message){
	// constructor with a message
			super(message);
	}
	
}
