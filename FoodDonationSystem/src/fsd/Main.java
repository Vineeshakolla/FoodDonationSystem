package fsd;

import java.util.*;

// Main class acts as Controller of the application.
// Uses Singleton-style pattern via static shared objects (Scanner, donations list)
// Handles menu flow and coordinates between different modules.

public class Main {

    // Singleton-style shared objects (single instance used across application)
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Donation> donations = new ArrayList<>();

    static int idCounter = 1;



    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== FOOD DONATION SYSTEM =====");
            System.out.println("1. Donor");
            System.out.println("2. Volunteer");
            System.out.println("3. Admin");
            System.out.println("4. Awareness");
            System.out.println("0. Exit");

            int choice = safeInt("Enter choice: ");

            switch (choice) {
                case 1: donorMenu(); break;
                case 2: volunteerMenu(); break;
                case 3: adminMenu(); break;
                case 4: awarenessMenu(); break;
                case 0:
                    System.out.println("Data saved. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Utility methods for Exception Handling and Input Validation pattern
    static int safeInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Enter valid number.");
            }
        }
    }

    static String safeString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    // Donor Module
    static void donorMenu() {
        String name = safeString("Enter donor name: ");

        while (true) {
            System.out.println("\n--- Donor Menu ---");
            System.out.println("1. Add Donation");
            System.out.println("2. View My Donations");
            System.out.println("3. Update Donation");
            System.out.println("4. My Contribution");
            System.out.println("0. Back");

            int c = safeInt("Choice: ");

            if (c == 1) addDonation(name);
            else if (c == 2) viewDonor(name);
            else if (c == 3) updateDonation(name);
            else if (c == 4) donorReport(name);
            else if (c == 0) return;
        }
    }

    static void addDonation(String donor) {
        String contact;
        while (true) {
            contact = safeString("Contact Number (10 digits): ");
            if (contact.matches("\\d{10}")) break;
            System.out.println("Invalid contact number.");
        }

        String address = safeString("Full Address: ");
        String location = safeString("Area/Location: ");
        String foodName = safeString("Food Name (eg: Veg Biryani): ");

        System.out.println("1.Tiffin 2.Lunch 3.Snack 4.Dinner 5.Packed Food 6.Fruits 7.Sweets 8.Other");
        int t = safeInt("Food Type: ");
        String foodType = FoodFactory.getFoodType(t);

        int qty = safeInt("Quantity (people): ");
        int hours = safeInt("Safe hours remaining: ");

        Donation d = new Donation(idCounter++, donor, contact, address,
                location, foodName, foodType, qty, hours);

        donations.add(d);
        System.out.println("Donation added successfully.");
        if (hours <= 1) System.out.println("⚠ URGENT: Must be collected quickly!");
    }

    static void viewDonor(String name) {
        for (Donation d : donations)
            if (d.donor.equalsIgnoreCase(name))
                System.out.println(d);
    }

    static void updateDonation(String name) {
        viewDonor(name);
        int id = safeInt("Enter donation ID to update: ");

        for (Donation d : donations) {
            if (d.id == id && d.donor.equalsIgnoreCase(name) && !d.collected) {
                d.foodName = safeString("New Food Name: ");
                d.quantity = safeInt("New Quantity: ");
                System.out.println("Donation updated.");
                return;
            }
        }
        System.out.println("Cannot update.");
    }

    static void donorReport(String name) {
        int total = 0;
        for (Donation d : donations)
            if (d.donor.equalsIgnoreCase(name) && d.collected)
                total += d.quantity;

        System.out.println("You helped feed " + total + " people.");
    }

    // Volunteer Module
    static void volunteerMenu() {
        String name = safeString("Enter volunteer name: ");

        while (true) {
            System.out.println("\n--- Volunteer Menu ---");
            System.out.println("1. View Available Donations");
            System.out.println("2. Filter by Area");
            System.out.println("3. Accept Donation");
            System.out.println("4. Mark Collected + Feedback");
            System.out.println("5. My Contribution");
            System.out.println("0. Back");

            int c = safeInt("Choice: ");

            if (c == 1) viewAvailable();
            else if (c == 2) filterByArea();
            else if (c == 3) acceptDonation(name);
            else if (c == 4) markCollected(name);
            else if (c == 5) volunteerReport(name);
            else if (c == 0) return;
        }
    }

    static void viewAvailable() {
        for (Donation d : donations) {
            if (!d.collected && d.volunteer.equals("")) {
                System.out.println(d.basic());
                System.out.println("📞 " + d.contact + " | 🏠 " + d.address);
                System.out.println("---------------------------");
            }
        }
    }

    static void filterByArea() {
        String area = safeString("Enter area: ");
        for (Donation d : donations)
            if (d.location.equalsIgnoreCase(area) && !d.collected)
                System.out.println(d.basic());
    }

    static void acceptDonation(String v) {
        viewAvailable();
        int id = safeInt("Enter donation ID to accept: ");

        for (Donation d : donations) {
            if (d.id == id && d.volunteer.equals("")) {
                d.volunteer = v;
                System.out.println("Donation accepted successfully.");
                return;
            }
        }
        System.out.println("Invalid ID.");
    }

    static void markCollected(String v) {
        int id = safeInt("Enter donation ID: ");

        for (Donation d : donations) {
            if (d.id == id && d.volunteer.equalsIgnoreCase(v)) {
                d.collected = true;

                System.out.println("Food quality: 1.Good 2.Average");
                d.quality = safeInt("Choice: ") == 1 ? "Good" : "Average";

                System.out.println("Feedback saved. Thank you!");
                return;
            }
        }
        System.out.println("Invalid ID.");
    }

    static void volunteerReport(String v) {
        int total = 0;
        for (Donation d : donations)
            if (d.volunteer.equalsIgnoreCase(v) && d.collected)
                total += d.quantity;

        System.out.println("You helped deliver food to " + total + " people.");
    }

    // Admin Module
    static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Donations");
            System.out.println("2. View Pending Donations");
            System.out.println("3. View Collected Donations");
            System.out.println("4. System Statistics");
            System.out.println("0. Back");

            int c = safeInt("Choice: ");

            if (c == 1) {
                for (Donation d1 : donations) System.out.println(d1);
            } 
            else if (c == 2) {
                for (Donation d2 : donations)
                    if (!d2.collected) System.out.println(d2);
            } 
            else if (c == 3) {
                for (Donation d3 : donations)
                    if (d3.collected) System.out.println(d3);
            } 
            else if (c == 4) {
                int total = donations.size();
                int collectedCount = 0;

                for (Donation d4 : donations)
                    if (d4.collected) collectedCount++;

                System.out.println("Total: " + total);
                System.out.println("Collected: " + collectedCount);
                System.out.println("Pending: " + (total - collectedCount));
            } 
            else if (c == 0) return;
        }
    }

    // Awareness Module
    static void awarenessMenu() {
        System.out.println("1. Global statistics");
        System.out.println("2. India statistics");
        System.out.println("3. How students can reduce waste");
        System.out.println("4. Why food donation matters");

        int c = safeInt("Choice: ");

        if (c == 1) System.out.println("1/3 of food is wasted globally.");
        else if (c == 2) System.out.println("India wastes ~68 million tonnes annually.");
        else if (c == 3) System.out.println("Take only what you need. Donate extra.");
        else if (c == 4) System.out.println("Food donation saves lives and environment.");
    }
}
