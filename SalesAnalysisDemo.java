import java.io.*;
import java.util.Scanner;
/**
 * Program Desc:
 * Application to run weekly months/sales numbers
 * 
 * @author Tobey Agapito
 * @version 1.0
 * @since 11/21/2026
 */
public class SalesAnalysisDemo
{
    /**
     * The main method gets the file name from the user, verifies that the
     * file exists, and then processes the file and displays the output.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException When the input file is not found.
     */
    public static void main(String[] args) throws IOException {
        String fileName;

        //Get user input from user
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the path to the SalesData.txt file: ");
        fileName = keyboard.nextLine();

        //Ensure that file path exists
        File file = new File(fileName);
        while (!file.exists()) {
            System.out.println("The file " + fileName + " was not found.");
            System.out.print("Enter the path to the SalesData.txt file: ");
            fileName = keyboard.nextLine();
            file = new File(fileName);
        }

        SalesAnalysis numbers = new SalesAnalysis(fileName);
        numbers.processFile();
        numbers.writeOutput();

        keyboard.close();
    }
}