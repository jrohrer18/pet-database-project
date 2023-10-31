
package petdatabase;

import java.util.Scanner;

public class PetDatabase {
    
    // scanner for user input
    static Scanner in = new Scanner(System.in);

    // Initialize pet count
    static int petCount=0;

    // Array for pet objects
    static Pet[] pets = new Pet[100];
    
    public static void main(String[] args) {
        
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
        System.out.println("3.) Exit Program");
        System.out.println("Your choice: ");
        int choice = in.nextInt();

        return choice;	
    }
    
    public static void addPets() {
        
        // Method to add new pets 

        int petsAdded=0;
        
        while (true) {
            
                System.out.print("add pet (name, age): ");
                String name = in.next();
                
                // Exit the loop when "done" is entered
                if (name.equals("done")) {
                        break;
                }
                int age = in.nextInt();
                
                // Check if the maximum pet limit is reached
                if (petCount==100) {
                        System.out.println("You cannot add more than 100 pets to the database");
                        break;
                }
                
                pets[petCount++]= new Pet(name, age);
                
                petsAdded++;
        }
        System.out.println(petsAdded + " pets added.");	
        return;
    }
    
    public static void showAllPets() {
        // Display all pets
        printTableHeader();
        
        for (int i=0; i<petCount;i++) {
                printTableRow(i,pets[i].getName(),pets[i].getAge());
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
        
}
    

