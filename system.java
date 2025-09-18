package warehouse;
import java.util.ArrayDeque;
import java.util.Scanner;
public class system {
	
    private static ArrayDeque<item> itemStack = new ArrayDeque<>();
    private static ArrayDeque<truck> truckQueue = new ArrayDeque<>();
    
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
    	
        int choice;
        do {
            displayMenu();
            System.out.print("enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
            
                case 1:
                    storeitem();
                    break;
                    
                case 2:
                    viewitem();
                    break;
                    
                case 3:
                    registerArrivingtruck();
                    break;
                    
                case 4:
                    viewWaitingtruck();
                    break;
                    
                case 5:
                    loadNexttruck();
                    break;
                    
                case 0:
                    System.out.println("exiting the program.");
                    break;
                    
                default:
                    System.out.println("Nope. Please try again.");
            }
            System.out.println();
        } while (choice != 0);
        scanner.close();
        
        
    }
    private static void displayMenu() {
        System.out.println("=== Warehouse Loading System ===");
        System.out.println("[1] store item (push)");
        System.out.println("[2] View warehouse stack");
        System.out.println("[3] register arriving truck (enqueue)");
        System.out.println("[4] view waiting trucks");
        System.out.println("[5] Load next truck (pop container + poll ship)");
        System.out.println("[0] Exit");
        
    }
    private static void storeitem() {
        System.out.print("enter item code: ");
        String code = scanner.nextLine();
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter item quantity: ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();

        item item = new item(code, name, quantity);
        itemStack.push(item);
        System.out.println("Stored: " + item);
        
    }
    private static void viewitem() {
        if (itemStack.isEmpty()) {
            System.out.println("No items.");
            return;
        }
        System.out.println("top -> " + itemStack.peek().toString().replace(" |", "     |"));
        itemStack.stream().skip(1).forEach(c -> System.out.println("        " + c));
        System.out.println("<- bottom");
        
        
        
    }
    private static void registerArrivingtruck() {
        System.out.print("Enter plate: ");
        String plate = scanner.nextLine();
        
        System.out.print("Enter driver name: ");
        String driver = scanner.nextLine();

        truck truck = new truck(plate, driver);
        truckQueue.add(truck);
        System.out.println("Registered: " + truck);
        
    }

    private static void viewWaitingtruck() {
        if (truckQueue.isEmpty()) {
            System.out.println("No trucks.");
            return;
        }
        System.out.println("front ->");
        truckQueue.forEach(s -> System.out.println(s));
        System.out.println("<- rear");
        
        
        
    }

    private static void loadNexttruck() {
        if (itemStack.isEmpty()) {
            System.out.println("Error: No items available to load.");
            return;
        }
        if (truckQueue.isEmpty()) {
            System.out.println("Error: No trucks waiting to be loaded.");
            return;
            
        }

        item loadedContainer = itemStack.pop();
        truck loadedShip = truckQueue.poll();
        
        System.out.println("Loaded: " + loadedContainer + " -> " + loadedShip);
        System.out.println("Remaining items: " + itemStack.size());
        System.out.println("Remaining trucks: " + truckQueue.size());
    }

}
