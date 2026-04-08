import java.io.*;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "test.txt";

        try {
            //Rerouting System.out to the text file
            PrintStream fileOut = new PrintStream(new FileOutputStream(fileName, true));
            PrintStream console = System.out; // Keep a reference to the actual console

            System.out.println("\n--- Banking Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Exit");
            console.print("Choice: "); 
            int choice = sc.nextInt();

            if (choice == 1) {
                console.print("Enter ID (1-20): ");
                int cid = sc.nextInt();
                if (cid < 1 || cid > 20) throw new Exception("Invalid ID Range.");

                console.print("Enter Amount: ");
                double amount = sc.nextDouble();
                if (amount < 1000) throw new Exception("Minimum Rs. 1000 required.");

                
                System.setOut(fileOut);
                System.out.println("Customer ID: " + cid);
                System.out.println("Deposit Amount: " + amount);
                System.out.println("Status: Success");
                System.out.println("--------------------");
                
                
                System.setOut(console);
                System.out.println("Data saved to " + fileName);
            }
            fileOut.close();

            
            System.out.print("\nDo you want to view the saved records? (yes/no): ");
            String view = sc.next();

            if (view.equalsIgnoreCase("yes")) {
                File myFile = new File(fileName);
                Scanner fileReader = new Scanner(myFile);
                System.out.println("\n--- Reading from " + fileName + " ---");
                while (fileReader.hasNextLine()) {
                    System.out.println(fileReader.nextLine());
                }
                fileReader.close();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}