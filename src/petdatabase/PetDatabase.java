
package petdatabase;

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PetDatabase {
    
    // scanner for user input
    static Scanner in = new Scanner(System.in);

    // Initialize pet count
    static int petCount=0;

    // Array for pet objects
    static Pet[] pets = new Pet[100];
    
    public static void main(String[] args) {
        
        loadPetData();

        
        // Control main loop
        boolean another = true;
        
        // Main Loop
        while (another==true) {
             
            int choice = getUserChoice();
            
            switch (choice) {
             
             case 1:
                 
                showAllPets();
                break;
                
             case 2:
                 
                addPets();
                break;
            
             case 3:
                
                removePet();
                break;
             
             case 4:
                
                savePetData();

                // Exit the program
                another=false;
                System.out.println("Goodbye");
                break;
             }
         }
    }
    
    
    public static int getUserChoice() {
        // Display menu and get the user's choice
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1.) View all pets");
        System.out.println("2.) Add more pets");
	System.out.println("3.) Remove an existing pet");
        System.out.println("4.) Exit Program");
        System.out.println("Your choice: ");
        int choice = in.nextInt();

        return choice;	
    }
    
    public static void addPets() {
        
        // Method to add new pets 

        int petsAdded=0;
        
        in.nextLine();

        
        while (true) {
            
                System.out.print("add pet (name age): ");
                
                String input = in.nextLine(); 

                if (input.equals("done")) {
                    break;
                }

                // validate the input format
                if (!input.matches("^[a-zA-Z]+\\s\\d+$")) {
                    System.out.println("Error: "+input+" is not a valid input.");
                    continue;
                }

                String[] parts = input.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                 
                // Check for valid age
                if (age < 1 || age > 20) {
                    System.out.println("Error: " + age + " is not a valid age.");
                    continue;
                }
                   
                // Check if database is full max 5
                if (petCount == 5) {
                    System.out.println("Error: Database is full.");
                    break;
                }
                
                pets[petCount++] = new Pet(name, age);
                petsAdded++;
                
         }
            System.out.println(petsAdded + " pets added.");	
        }
    
    public static void showAllPets() {
        // Display all pets
        printTableHeader();
        
        for (int i=0; i<petCount;i++) {
                printTableRow(i,pets[i].getName(),pets[i].getAge());
        }
        printTableFooter();
    }
    
    // Updating a pets information
    public static void updatePet() {
        
        System.out.println();
        showAllPets();
        
        // Prompt user for the ID of the pet they want to update
        System.out.print("Enter the pet ID you want to update: ");
        int petID= in.nextInt();
        
        // Prompt user to enter the updated information
        System.out.print("Enter a new name and age: ");
        
        String newName = in.next();
        int newAge = in.nextInt();
        
        //update the the previous name and age with the new info entered by the user
        pets[petID].setName(newName);
        pets[petID].setAge(newAge);

        System.out.println("Pet updated.");
        System.out.println();
    }
    
    // Removing a pet from the database
    public static void removePet() {
        
        System.out.println();
        showAllPets();
        
        // Prompt user to enter the ID of the pet they want to remove
        System.out.print("Enter the pet ID you want to remove: ");
        try {
            int petID = in.nextInt();
            // Check if ID exists in database
            if (petID < 0 || petID >= petCount) {
                System.out.println("Error: ID " + petID + " does not exist.");
                return;
            }        
            for (int i= petID; i<petCount; i++) {
                    pets[i]=pets[i+1];
            }
            pets[petCount - 1] = null; // Remove the last element
            petCount--;
            System.out.println("Pet removed.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid pet ID.");
            in.nextLine(); 
        }
        
    }
    
    // Searching a pet in the database by name 
    public static void searchPetsByName() {
        
        System.out.println();
        //prompt user to enter the name of the pet
        System.out.println("Enter a pets name to search: ");
        String searchName = in.next();
        printTableHeader();
        
        for (int i=0; i<petCount; i++) {
                if (pets[i].getName().equals(searchName)) {
                    printTableRow(i,pets[i].getName(),pets[i].getAge());
                }
        }
        printTableFooter();
    }
    
    // Searching a pet in the database by its age
    public static void searchPetsByAge() {
        
        System.out.println();
        //prompt user for the age of the pet
        System.out.println("Enter age to search: ");
        int searchAge = in.nextInt();
        printTableHeader();
        
        for (int i=0; i<petCount; i++) {
                if (pets[i].getAge()==searchAge) {
                        printTableRow(i,pets[i].getName(),pets[i].getAge());
                }
        }
        printTableFooter();
    }
    
    // Create table 
    public static void printTableHeader() {
        System.out.println("+----------------------+");
        System.out.printf("%-3s %-10s %-4s", "| ID", "| NAME ", " | AGE |");
        System.out.println();
        System.out.println("+----------------------+");
    }
    
    public static void printTableRow(int id, String name, int age) {
            System.out.printf("|%-3s | %-10s|%-4d |\n", id, name, age);
    }
    
    public static void printTableFooter() {
        System.out.print("+----------------------+");
        System.out.println();	
        int count=0;
        for (int i=0;i<petCount;i++) {
                count++;
        }
        System.out.println(count+" rows in set.");
    }
    
    // Load pet data
    public static void loadPetData() {
        String filePath = "src/petdatabase/petdata.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    pets[petCount++] = new Pet(name, age);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading pet data from file: " + e.getMessage());
        }
    }
    
    // Save pet data
    public static void savePetData() {
        String filePath = "src/petdatabase/petdata.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < petCount; i++) {
                writer.write(pets[i].getName() + "," + pets[i].getAge());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving pet data to file: " + e.getMessage());
        }
    }
   
        
}
    

