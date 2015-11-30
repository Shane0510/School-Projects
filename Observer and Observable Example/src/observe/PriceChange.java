package observe;

import java.util.Date;

/**
 * An object that records a change in price of a Product.
 * @author GreyWolf
 */
public class PriceChange {
    private Product product; // a Product whose price changed
    private Date date; // the Date when the change occurred

    /**
     * Creates a new PriceChange for the given Product.
     * @param product the Product whose price changed
     */
    public PriceChange(Product product){
        this.product = product;
        this.date = new Date();
    }

    /**
     * Returns the Product whose price changed.
     * @return the Product whose price changed
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Returns the Product of the price change.
     * @return the Date of the price change
     */
    public Date getDate() {
        return this.date;
    }
    
}
