public class Product implements IProducts {
    private final String[] productNames;
    private final int[][] monthlySales;

    public Product(String[] productNames, int[][] monthlySales) {
        if (productNames == null || monthlySales == null
                || productNames.length == 0 || monthlySales.length != productNames.length) {
         throw new IllegalArgumentException(
            "Product names and monthly sales must contain the same number of products.");
        }

        this.productNames = productNames.clone();
        this.monthlySales = new int[monthlySales.length][];

        for (int i = 0; i < monthlySales.length; i++) {
            if (monthlySales[i] == null || monthlySales[i].length != 3) {
                throw new IllegalArgumentException(
                 "Each product must have sales data for exactly three months.");
            }

            this.monthlySales[i] = monthlySales[i].clone();
        }
    }
    public int getTotalProductsSold() {
        int total = 0;

        for (int[] sales : monthlySales) {
            for (int unitsSold : sales) {
                total += unitsSold;
            }
        }

        return total;
    }
    public double getAverageProductsSold() {
        return (double) getTotalProductsSold() / productNames.length;
    }
    public String getMostPopularProduct() {
        int popularIndex = 0;
        int highestTotal = getProductTotal(0);

        for (int i = 1; i < productNames.length; i++) {
            int productTotal = getProductTotal(i);
            if (productTotal > highestTotal) {
                highestTotal = productTotal;
                popularIndex = i;
            }
        }

        return productNames[popularIndex];
    }
    private int getProductTotal(int productIndex) {
        int total = 0;

        for (int unitsSold : monthlySales[productIndex]) {
            total += unitsSold;
        }
        return total;
    }
    public int getProductTotalByName(String productName) {
        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i].equals(productName)) {
                return getProductTotal(i);
            }
        }

        throw new IllegalArgumentException("Unknown product: " + productName);
    }
    public String[] getProductNames() {
        return productNames.clone();
    }
    public int[][] getMonthlySales() {
        int[][] copy = new int[monthlySales.length][];
        for (int i = 0; i < monthlySales.length; i++) {
            copy[i] = monthlySales[i].clone();
        }
        return copy;
    }
}
