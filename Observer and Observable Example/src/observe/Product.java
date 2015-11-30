package observe;

import java.util.Observable;

/** A product in a store. **/
public class Product extends Observable {
	// instances
	private String name;
	private double price; 
	private String store;
	
    /**
     * Creates a Product with the given name, price, and store.
     * @param name name of the new Product
     * @param price price of the new Product
     * @param store store of the new Product
     */
    // constructor
	public Product(String name, double price, String store) {
		this.name = name;
		this.price = price;
		this.store = store;
	}

    /**
     * Returns the name of this Product.
     * @return the name of this Product
     */
    // getName
	public String getName() {
		return name;
	}
	
    /**
     * Returns the price of this Product.
     * @return the price of this Product
     */
    // getPrice (returns a double)
	public double getPrice() {
		return price;
	}

    /**
     * Returns the store of this Product.
     * @return the store of this Product
     */
    // getStore
	public String getStore() {
		return store;
	}

	
    /**
     * Changes the price of this Product to newPrice. All
     * observers are notified, if the price is changed.
     * @param newPrice the new price of this Product
     */
    // changePrice
	public void changePrice(double newPrice) {
		this.price = newPrice;
		setChanged();
		notifyObservers(new PriceChange(this));
	}

    // toString()
	@Override
	public String toString() {
		String priceFormatted = String.format("The price of %s at %s is %.2f.", 
				name, store, price);
		return priceFormatted;
	}
	
}
