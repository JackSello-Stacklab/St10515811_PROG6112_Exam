public class ProductCalculations implements IProductCalculations {
    private final double productPrice;

    public ProductCalculations(double productPrice) {
        this.productPrice = productPrice;
    }
    public double GetVat() {
        return productPrice * 0.15;
    }
    public double GetCommission() {
        return productPrice * 0.05;
    }
    public double GetTotal() {
        return productPrice + GetVat() + GetCommission();
    }
}
