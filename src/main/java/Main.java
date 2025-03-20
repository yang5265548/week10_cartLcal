import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Select a launguage:");
        System.out.println("1. Finnish");
        System.out.println("2. Swedish,");
        System.out.println("3. Japanese");
        System.out.println("4. English");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        Locale locale;

        switch (choice) {
            case 1:
                locale = new Locale("fi", "FI");
                break;
            case 2:
                locale = new Locale("sv", "SE");
                break;
            case 3:
                locale = new Locale("jp", "JP");
                break;
            default:
                System.out.println("Invalid choice. Defaulting to English (EN).");
                locale = new Locale("en", "EN");
                break;
        }

        ResourceBundle rb;
        try{
            rb=ResourceBundle.getBundle("messages",locale);
        } catch (Exception e) {
            System.out.println("Invalid language is not in the list.");
            rb=ResourceBundle.getBundle("messages",new Locale("en","EN"));
        }

        System.out.println(rb.getString("num"));
        int num = sc.nextInt();
        double itemsTotal = 0;
        for (int i = 0; i < num; i++) {
            System.out.println(rb.getString("price"));
            double price = sc.nextDouble();
            System.out.println(rb.getString("quantity"));
            int quantity = sc.nextInt();
            double itemTotal = price * quantity;
            itemsTotal += itemTotal;
            System.out.println(rb.getString("total") + itemTotal);

        }
        System.out.println("the cart total is: ");
        System.out.println(rb.getString("total") + itemsTotal);

    }
}
