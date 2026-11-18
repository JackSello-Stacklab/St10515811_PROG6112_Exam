public class PrintProduct {
    public void printTotalProductSales(IProducts product) {
        System.out.println("Total products sold: " + product.getTotalProductsSold());
    }
    public void printAverageProductSales(IProducts product) {
        System.out.printf("Average products sold per product: %.2f%n",
                product.getAverageProductsSold());
    }
    public void printMostPopularProduct(IProducts product) {
        System.out.println("Most popular product: " + product.getMostPopularProduct());
    }
    public void printReport(String[] productNames, int[][] monthlySales,
       IProducts product) {
        System.out.println("MATTRESS SALES REPORT");
        System.out.println("Product\tMonth 1\tMonth 2\tMonth 3\tTotal");
        System.out.println("----------------------------------");

     for (int i = 0; i < productNames.length; i++) {
     int productTotal = 0;
         for (int unitsSold : monthlySales[i]) {
             productTotal += unitsSold;
            }

     System.out.printf("%s\t%d\t%d\t%d\t%d%n",
         productNames[i],
         monthlySales[i][0],
        monthlySales[i][1],
        monthlySales[i][2],
        productTotal);
        }
        System.out.println();
        printTotalProductSales(product);
        printAverageProductSales(product);
        printMostPopularProduct(product);
    }
}
