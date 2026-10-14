import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// CO4: MenuItem class with private fields
class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class Projectprogram {

    // CO3: subtotal() method
    public static double subtotal(double price, int qty) {
        return price * qty;
    }

    public static void main(String[] args) {  //main method--------------------

        Scanner sc = new Scanner(System.in);

        // CO6: ArrayList menu
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("PANNER BIRIYANI", 249.0));
        menu.add(new MenuItem("CHICKEN BIRIYANI", 259.0));
        menu.add(new MenuItem("MUTTON BIRIYANI", 269.0));
        menu.add(new MenuItem("MEALS", 279.0));
        menu.add(new MenuItem("PULAO", 199.0));
        menu.add(new MenuItem("DESSERT", 129.0));
        menu.add(new MenuItem("DRINKS", 49.0));
        menu.add(new MenuItem("WATER BOTTLE", 40.0));

        System.out.println("---- WELCOME TO RESTAURANT ----");

        System.out.print("ENTER CUSTOMER NAME: ");
        String name = sc.nextLine();

        System.out.print("ENTER YOUR MOBILE NUMBER: ");
        long mno = sc.nextLong();

        // Arrays to store order details
        String orderedItem[] = new String[10];
        double orderedPrice[] = new double[10];
        int orderedQuantity[] = new int[10];

        int n = 0;
        char more;

        do {
            System.out.println("\n-- MENU --");
            for (int i = 0; i < menu.size(); i++) {
                // CO1: printf output
                System.out.printf("%d. %s - Rs.%.2f\n", (i + 1), menu.get(i).getName(), menu.get(i).getPrice());
            }

            System.out.print("\nEnter item choice (1-8): ");
            int itemChoice = sc.nextInt();

            System.out.print("ENTER QUANTITY OF ITEM: ");
            int quantity = sc.nextInt();

            if (n < 10) {
                // CO2: switch for menu choice
                switch (itemChoice) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        MenuItem selected = menu.get(itemChoice - 1);
                        orderedItem[n] = selected.getName();
                        orderedPrice[n] = selected.getPrice();
                        orderedQuantity[n] = quantity;
                        n++;
                        System.out.println("Item added successfully!");
                        break;

                    default:
                        System.out.println("Invalid item choice!");
                        break;
                }
            } else {
                System.out.println("Order limit reached!");
            }

            System.out.print("\nAdd another item? (y/n): ");
            more = sc.next().charAt(0);

        } while (more == 'y' || more == 'Y');

        if (n == 0) {
            System.out.println("\nNo items ordered.");
            sc.close();
            return;
        }

        System.out.print("\nAre you a member? (true/false): ");
        boolean isMember = sc.nextBoolean();

        // Calculate subtotal using subtotal() method (CO3)
        double total = 0;
        for (int i = 0; i < n; i++) {
            total += subtotal(orderedPrice[i], orderedQuantity[i]);
        }

        // Simple discount calculation
        double discount = 0;
        if (total >= 1000) {
            discount = 0.15;
        } else if (total >= 500) {
            discount = 0.10;
        } else if (isMember) {
            discount = 0.18;
        }

        double discountAmount = total * discount;
        double finalTotal = total - discountAmount;

        // CO5: StringBuilder for bill construction
        StringBuilder bill = new StringBuilder();
        bill.append("\n=================================\n");
        bill.append("          RESTAURANT BILL        \n");
        bill.append("=================================\n");
        bill.append("Customer Name: ").append(name).append("\n");
        bill.append("Customer Mobile: ").append(mno).append("\n");
        bill.append("---------------------------------\n");

        for (int i = 0; i < n; i++) {
            double itemTotal = subtotal(orderedPrice[i], orderedQuantity[i]);
            bill.append(orderedItem[i])
                .append(" x ").append(orderedQuantity[i])
                .append(" = Rs.").append(itemTotal).append("\n");
        }

        bill.append("---------------------------------\n");
        bill.append("TOTAL: Rs.").append(total).append("\n");
        bill.append("DISCOUNT: ").append((int)(discount * 100)).append("%\n");
        bill.append("DISCOUNT AMOUNT: Rs.").append(discountAmount).append("\n");
        bill.append("FINAL TOTAL: Rs.").append(finalTotal).append("\n");
        bill.append("=================================\n");
        bill.append("Thank you for visiting!\n");

        // Display bill
        System.out.print(bill.toString());


        //exception handling for file writing

        try { java.nio.file.Files.writeString( java.nio.file.Paths.get("bill.txt"), bill.toString() );
         System.out.println("\nBill saved successfully to bill.txt"); 
        }
          catch (java.io.IOException e) 
        { 
            System.out.println("\nError while saving bill: " + e.getMessage());
        }

        sc.close();
    }
}

