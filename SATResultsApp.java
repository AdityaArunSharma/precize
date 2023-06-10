import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SATResultsApp {
    private static List<SATResult> data = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Insert data");
            System.out.println("2. View all data");
            System.out.println("3. Get rank");
            System.out.println("4. Update score");
            System.out.println("5. Delete one record");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    insertData();
                    break;
                case 2:
                    viewAllData();
                    break;
                case 3:
                    getRank();
                    break;
                case 4:
                    updateScore();
                    break;
                case 5:
                    deleteRecord();
                    break;
                case 6:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    private static void insertData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        for (SATResult result : data) {
            if (result.getName().equalsIgnoreCase(name)) {
                System.out.println("Name already exists!");
                return;
            }
            
        }
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        System.out.print("Enter pincode: ");
        String pincode = scanner.nextLine();
        System.out.print("Enter SAT score: ");
        double satScore = scanner.nextDouble();
        if(satScore<0 || satScore>100){
            System.out.println("Invalid SAT score");
            return;
        }
        scanner.nextLine(); // Consume newline character

        String passed = satScore > 30 ? "Pass" : "Fail";

        SATResult result = new SATResult(name, address, city, country, pincode, satScore, passed);
        int index = 0;
        while (index < data.size() && data.get(index).getSatScore() > satScore) {
            index++;
        }

        data.add(index, result);


        System.out.println("Data inserted successfully!");
    }

    private static void viewAllData() {
        System.out.println("All records ["+data.size()+"] :-");
        for (SATResult result : data) {
            System.out.println(result.toString());
        }
    }

    private static void getRank() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        int rank = 1;
        boolean found = false;

        for (SATResult result : data) {
            if (result.getName().equalsIgnoreCase(name)) {
                found = true;
                break;
            }
            rank++;
        }

        if (found) {
            System.out.println("Rank for " + name + ": " + rank);
        } else {
            System.out.println("No record found for " + name);
        }
    }

    private static void updateScore() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        boolean found = false;

        for (SATResult result : data) {
            if (result.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new SAT score: ");
                double newScore = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                result.setSatScore(newScore);
                result.setPassed(newScore > 30 ? "Pass" : "Fail");
                
                // Remove and re-insert the updated record at the correct position
                data.remove(result);
                int index = 0;
                while (index < data.size() && data.get(index).getSatScore() > newScore) {
                    index++;
                }
                data.add(index, result);

                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Score updated successfully!");
        } else {
            System.out.println("No record found for " + name);
        }
    }

    private static void deleteRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Iterator<SATResult> iterator = data.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            SATResult result = iterator.next();
            if (result.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Record deleted successfully!");
        } else {
            System.out.println("No record found for " + name);
        }
    }
}

class SATResult {
    private String name;
    private String address;
    private String city;
    private String country;
    private String pincode;
    private double satScore;
    private String passed;

    public SATResult(String name, String address, String city, String country, String pincode, double satScore, String passed) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
        this.satScore = satScore;
        this.passed = passed;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPincode() {
        return pincode;
    }

    public double getSatScore() {
        return satScore;
    }

    public String getPassed() {
        return passed;
    }

    public void setSatScore(double satScore) {
        this.satScore = satScore;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nAddress: " + address +
                "\nCity: " + city +
                "\nCountry: " + country +
                "\nPincode: " + pincode +
                "\nSAT Score: " + satScore +
                "\nPassed: " + passed +
                "\n";
    }
}
