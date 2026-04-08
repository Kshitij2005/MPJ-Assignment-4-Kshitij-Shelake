import java.io.*;
import java.util.*;

// User-defined Exceptions
class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) { super(msg); }
}

class InvalidCIDException extends Exception {
    InvalidCIDException(String msg) { super(msg); }
}

class BankSystem {
    static final String FILE = "data.txt";

    // Create Account
    static void create(Scanner sc) {
        try {
            System.out.print("CID (1-20): ");
            int cid = sc.nextInt();
            if (cid < 1 || cid > 20)
                throw new InvalidCIDException("CID must be 1-20");

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Amount: ");
            double amt = sc.nextDouble();
            if (amt < 1000)
                throw new InvalidAmountException("Min Rs.1000 required");

            FileWriter fw = new FileWriter(FILE, true);
            fw.write(cid + " " + name + " " + amt + "\n");
            fw.close();

            System.out.println("Account Created!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Display
    static void display() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
            br.close();
        } catch (Exception e) {
            System.out.println("File Error");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;

        do {
            System.out.println("\n1.Create  2.Display  3.Exit");
            ch = sc.nextInt();

            switch (ch) {
                case 1: create(sc); break;
                case 2: display(); break;
            }
        } while (ch != 3);
    }
}
