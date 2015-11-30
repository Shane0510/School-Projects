package observe;

import java.util.Observable;
import java.util.Observer;

/**
 * An object that observes the price change in a Product.
 * @author GreyWolf
 */
public class PriceWatchWebsite extends Observable implements Observer {
	private String url;

    /**
     * Creates a new PriceWatchWebsite with the given URL.
     * @param url the URL of the new PriceWatchWebsite
     */
    public PriceWatchWebsite(String url) {
		this.url = url;
	}
    
    /**
     * Returns the URL of this PriceWatchWebsite.
     * @return the URL of this PriceWatchWebsite
     */
    // getUrl
	public String getUrl() {
		return url;
	}    

    /**
     * Prints a message about a price change.
     * Notifies all observers of the change in price.
     */
    // update
	@Override
	public void update(Observable Product, Object product) {
    	String priceFormatted = String.format("You are subscribed to %s.%n"
    			+ "It was notified about a price change "
    			+ "of %s at %s to %.2f.", this.getUrl(), ((PriceChange) product
    					).getProduct().getName(), ((PriceChange) product)
    					.getProduct().getStore(), 
    			((PriceChange) product).getProduct().getPrice());
		System.out.println(priceFormatted);
		setChanged();
		notifyObservers(product);
	}






}