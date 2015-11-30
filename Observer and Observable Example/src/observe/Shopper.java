package observe;

import java.util.Observable;
import java.util.Observer;

public class Shopper implements Observer {
	// instance
	private String name;
	
    /**
     * Creates a new Shopper with the given name.
     * @param name name of the new Shopper
     */
    // constructor
	public Shopper(String name) {
		this.name = name;
	}
	
    /**
     * Returns the name of this Shopper.
     * @return the name of this Shopper
     */
    // getName
    public String getName() {
		return name;
	}

	/**
     * Prints a message about a price change.
     */
    // update
	@Override
    public void update(Observable Product, Object product) {
    	String priceFormatted = String.format("%s was notified about a price "
    			+ "change of %s at %s to %.2f.", this.name, 
    			((PriceChange) product).getProduct().getName(), ((PriceChange) 
    					product).getProduct().getStore(), 
    			 ((PriceChange) product).getProduct().getPrice());
		System.out.println(priceFormatted);
    }
	
}
