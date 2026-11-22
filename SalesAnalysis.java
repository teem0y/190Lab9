import java.io.*;
import java.util.Scanner;
/**
 * Program Desc:
 * Reads a file of daily sales where each line holds one week's seven
 * comma-separated daily sales. Totals the sales for each week, then displays
 * each week's total and average daily sales, the total and average weekly
 * sales for all weeks, and the weeks with the highest and lowest sales.
 * @author Tobey Agapito
 * @version 1.0
 * @since 11/21/2026
 */
public class SalesAnalysis {
    /**
     * Number of weeks of sales data in a month 
     */
    public static final int WEEKS_IN_MONTH = 4;
    
    private double[] weeklyTotal;
    private String inputFile;
    private int lineNumber = 0;

    /**
     * Constructor that sets the input file name and creates the array
     * that holds the total sales for each week.
     *
     * @param fileName The name/path of the sales data file.
     */
    public SalesAnalysis(String fileName) {
        inputFile = fileName;
        weeklyTotal = new double[WEEKS_IN_MONTH];
    }

    /**
     * The processFile method opens the sales data file, reads it one line
     * at a time, splits each line into its daily sales values, and passes
     * those values to setArrayElement to be totaled.
     *
     * @throws IOException When the input file is not found.
     */
    public void processFile() throws IOException {
        String line;
        String[] dailySales;

        File file = new File(inputFile);
        Scanner inputFileScanner = new Scanner(file);

        while (inputFileScanner.hasNextLine()) {
            line = inputFileScanner.nextLine();
            dailySales = line.split(",");
            setArrayElement(dailySales);
        }

        inputFileScanner.close();
    }

    /**
     * The setArrayElement method converts each daily sales value to a
     * double, totals them, and stores the total in the weeklyTotal array
     * for the current week.
     *
     * @param inArray An array of daily sales values for one week.
     */
    private void setArrayElement(String[] inArray) {
        double total = 0.0;

        for (String value : inArray) {
            total += Double.parseDouble(value);
        }

        weeklyTotal[lineNumber] = total;
        lineNumber++;
    }

    /**
     * The writeOutput method displays the total sales and average daily
     * sales for each week, followed by the total sales for all weeks, the
     * average weekly sales, and the weeks with the highest and lowest
     * sales.
     */
    public void writeOutput() {
        final int DAYS_IN_WEEK = 7;   // Days of sales in each week
        double totalSales = 0.0;      // Accumulator for all weeks
        double dailySales;            // Average daily sales for a week
        double max = weeklyTotal[0];  // Highest weekly total
        double min = weeklyTotal[0];  // Lowest weekly total
        int maxWeek = 0;              // Index of the highest week
        int minWeek = 0;              // Index of the lowest week

        for (int index = 0; index < weeklyTotal.length; index++) {
            totalSales += weeklyTotal[index];
            dailySales = weeklyTotal[index] / DAYS_IN_WEEK;

            if (weeklyTotal[index] > max) {
                max = weeklyTotal[index];
                maxWeek = index;
            }
            if (weeklyTotal[index] < min) {
                min = weeklyTotal[index];
                minWeek = index;
            }

            System.out.println("\nWeek" + (index + 1) + " Info");
            System.out.printf("Total Sales: $%,.2f\n", weeklyTotal[index]);
            System.out.printf("Avg Daily Sales for Week: $%,.2f\n", dailySales);
        }

        System.out.printf("\nTotal Sales for all Weeks: $%,.2f\n", totalSales);
        System.out.printf("Avg Weekly Sales: $%,.2f\n",
                          totalSales / WEEKS_IN_MONTH);
        System.out.println("Week" + (maxWeek + 1) +
                           " had the highest amount of sales");
        System.out.println("Week" + (minWeek + 1) +
                           " had the lowest amount of sales");
    }
}