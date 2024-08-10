package ChangeC;
/*
ICT167 - Assignment 1
Giovanni Icasiano Jr. (34629097)
June 17, 2024

The program is designed to take atleast 10 individual's data (name and amount), takes the currency denominations
of Dirhams (5, 10, 20, 50, 100, 200, 500, 1000) and gets the change of individual's amount. A menu screen is shown
that showcases the options of what the user can do with the data inputted (or pre-made).

This assignment showcases understandings of implemented concepts, principles and methods learnt during the lectures
of ICT167. 

- When program is executed, user is expected to input name(s) and amount for each individual. 
- User is expected to input letters for name and real numbers for amount that is a multiple of 5
- Program asks user if they want to input more data or not, user is expected to answer Y or N.
- A menu is displayed

*/

import java.util.Scanner;

//class TestChange performs main program, handles user interaction with program
public class TestChange{
    static Scanner kb = new Scanner(System.in);     //keyboard input scanner
    static String yes = "Y";                        
    static String no = "N";
    static boolean modifyDenom = false;             //changes if user chooses to modify their own currency denomination
    static String newCurrencyName = "NULL";         //changes if user chooses to name their preferred currency

    //main method
    public static void main (String[] args){
        ChangeC[] people = new ChangeC[30];         //creating an array of objects with ChangeC data type, 30 individuals maximum
        String name;                                
        int amount;
        int[] newDenomination = new int[8];         //creating an array of denominations incase user decides to manually enter denominations
        int size = 0;                               //size count for how many people user enters data for

        studentInfo();
        System.out.println("Welcome!\n1. Enter names and amounts for Dirhams.\n2. Manually enter other currency notes.");
        System.out.printf("Choice: ");
        if(kb.nextInt() == 2){                      //if user decides to manually enter denominations instead of dirhams, modifyDenom = true and call inputNewDenomination method
            modifyDenom = true;                   
            newDenomination = inputNewDenomination();
            System.out.println("Press enter to continue.");
        }
        kb.nextLine();

        System.out.println("Please enter at least 10 names and amount.\n(or type 'test' for preset names and amount hard coded)");
        
        for(int i=0; i<people.length; i++){         //loop to input names and amounts for each object
            name = inputName(size, people);
            if(name.equalsIgnoreCase("test")){      //for testing program's functinoality, hard coded names and amounts are provided for 12 individuals
                size = hardCode(people, newDenomination);
                break;
            }
            if(modifyDenom == true){                //condition if user decided new denomination values and name
                people[i] = new ChangeC(newDenomination); //modifies denominations in ChangeC based on user's inputs
            }
            if(modifyDenom == false){               //condition if user decided dirhams 
                people[i] = new ChangeC();          //allows for creating new instances of ChangeC (default constructor)
            } 
            people[i].setName(name);                //setting private instance name variable in ChangeC
            amount = inputAmount();                 //calling method for inputting amount, method includes conditions for multiplier of 5
            people[i].setAmount(amount);            //setting private instance amount variable in ChangeC
            size++;                                 //size +1 to measure the size of how many people user inputs

            System.out.printf("Enter more names? (Y/N): "); //asks user if they would like to enter more names
            if(kb.nextLine().toUpperCase().equals(no)){            //condition that has toUpperCase() method to convert lower case to upper so it can read "N" 
                break;
            }
        }
        int menuChoice;                                            
        do{
            System.out.println("\n1. Enter a name and display change to be given for each denomination");
            System.out.println("2. Find the name with the smallest amount and display change to be given for each denomination");
            System.out.println("3. Find the name with the largest amount and display change to be given for each denomination");
            System.out.println("4. Calculate and display the total number of currency note for each denomination");
            System.out.println("5. Calculate and display the total amount for the sum of all denominations");
            System.out.println("6. Exit");
            System.out.printf("\nChoice: ");
            menuChoice = kb.nextInt();
            kb.nextLine();

            switch(menuChoice){                                     //switch case to know what number user inputs
                case 1: 
                    searchName(size, people);
                    break;
                case 2:
                    smallestAmount(size, people);
                    break;
                case 3:
                    largestAmount(size, people);
                    break;
                case 4:
                    calculateTotalCurrencyNotes(size, people, newDenomination);
                    break;
                case 5:
                    totalAmount(size, people);
                    break;
                case 6:
                    System.out.println("========================================\nGoodbye!\n========================================\n");
                    return;
                default:                                            //default constructor incase user doesnt enter a number between 1 and 6 inclusive)
                    System.out.println("ERROR: Invalid input, try again.");
            }
        }while(menuChoice != 6);                                    //keep repeating until user enters 6
    }

    //Encapsulation & Information Hiding---
    //method to input new denominations and name of the currency
    public static int[] inputNewDenomination(){
        int[] newDenom = new int[8];
        System.out.println("\nEnter notes from lowest to highest");
            for(int i=0; i<8; i++){                                 //takes user input for 8 denominations, limitation: user must enter 8, even if currency only has 7 denominations
                System.out.printf("Denomination %d: ", i+1);
                newDenom[i] = kb.nextInt();
            }
            kb.nextLine();
            System.out.printf("Name of currency: ");         //takes name of currency from user (USD, Euro, AUD)
            newCurrencyName = kb.nextLine();
        return newDenom;
    }
    
    //Information Hiding, Encapsulation---
    //method for inputting name and checks for duplicate names
    public static String inputName(int size, ChangeC[] people){
        String name;
        boolean duplicateName;
        do{
            System.out.printf("\nEnter name: ");
            name = kb.nextLine();
            duplicateName = checkDuplicate(name, size, people);
            if(duplicateName){
                System.out.println("ERROR: Name already exists, try again.");
            }
        }while(duplicateName);  //keep asking user to enter name until duplicate name is false
        return name;
    }

    //method for inputting amount and ensures its a multiple of 5
    public static int inputAmount(){
        int amount;
        do{
            System.out.printf("Enter amount: ");
            amount = kb.nextInt();
            kb.nextLine();
            if(amount%5 != 0){
                System.out.println("ERROR: Value is not a multiple of 5, try again.");
            }
        }while(amount%5 != 0);  //keep asking user to enter a value until amount is a multiple of 5
        return amount;
    }

    //Helper---
    //helper method for inputName method to check for duplicate names, returns boolean
    private static boolean checkDuplicate(String name, int size, ChangeC[] people){
        for(int i=0; i<size; i++){
            if(name.equalsIgnoreCase(people[i].getName())){     //scans objects in this class if it equals to the name entered by user
                return true;
            }
        }
        return false;
    }
    
    //method for searching name and displaying its change
    public static void searchName(int size, ChangeC[] people){ 
        System.out.printf("Search name: ");
        String nameSearch = kb.nextLine();
        boolean nameFound = false;
        for(int i=0; i<size; i++){
            if(people[i] != null && people[i].getName().equalsIgnoreCase(nameSearch)){  //also scans the objects in this class if it equals to the name searched by user
                System.out.printf("========================================\nName: %s\nAmount: %d\n", people[i].getName(), people[i].getAmount());
                System.out.println("\nChange:");
                people[i].getChange(true, modifyDenom, newCurrencyName);
                System.out.printf("========================================");
                nameFound = true;
                break;
            }
        }
        if(!nameFound){ //if name not found display this:
            System.out.printf("========================================");
            System.out.printf("\nName: %s\nNot found\n", nameSearch);
            System.out.printf("========================================");
        }
    }

    //method for finding the smallest amount in the database and displaying change for that individual
    public static void smallestAmount(int size, ChangeC[] people){
        int index = 0;  //intitializing the first element of the object 
        for(int i=0; i<size; i++){
            for (int j=i+1; j<size; j++){
                if(people[index].getAmount()>people[j].getAmount()){    //checks if the first element is greater than j, if false, set index as j since that value is smaller than index 
                    index = j;
                }
            }
        }  
        System.out.println("========================================\nThe person with the smallest amount is:");
        System.out.printf("\nName: %s\nAmount: %d\n", people[index].getName(), people[index].getAmount());     
        people[index].getChange(true, modifyDenom, newCurrencyName);
        System.out.printf("========================================");
    }

    //method for finding the largest amount in the database and displaying change for that individual
    public static void largestAmount(int size, ChangeC[] people){
        int index = 0;  //intitializing the first element of the object 
        for(int i=0; i<size; i++){
            for (int j=i+1; j<size; j++){
                if(people[index].getAmount()<people[j].getAmount()){    //checks if the first element is greater than j, if true, set index as j since that value is greater than index 
                    index = j;
                }
            }
        }  
        System.out.println("========================================\nThe person with the largest amount is:");
        System.out.printf("\nName: %s\nAmount: %d\n", people[index].getName(), people[index].getAmount());
        people[index].getChange(true, modifyDenom, newCurrencyName);
        System.out.printf("========================================");
    }

    // method to calculate and display the total number of currency notes for each denomination
    public static void calculateTotalCurrencyNotes(int size, ChangeC[] people, int[] newDenomination){
        int[] denominations = new int[8];
        if(modifyDenom == true){                //check if modifyDenom is true, this is based on what user chose in the beginning
            denominations = newDenomination;    //set the denominatinos as the user-defined denominations
        }
        if(modifyDenom == false){
            denominations = new int[]{1000, 500, 200, 100, 50, 20, 10, 5};  //set the denominations are the default (Dirhams)
        }
        int[] totalNotes = new int[denominations.length];   //initialize total notes with the size of denominations array

        for(int i=0; i<size; i++){
            int[] changeCounts = people[i].getChange(false, modifyDenom, newCurrencyName);  //this basically gets change for all individuals and stores them in changeCounts, getChange parameter is false because we dont want getChange to display the change of users
            for (int j=0; j<changeCounts.length; j++){
                totalNotes[j] += changeCounts[j];   //totalNotes = totalNotes + changeCounts, which basically gets the total times that denomination was used
            }
        }
        System.out.printf("========================================");
        System.out.println("\nThe total number of currency notes for each denomination:");
        for (int j= 0; j<denominations.length; j++){
            if(modifyDenom == true){
                System.out.printf("%d %s: %d\n", denominations[j], newCurrencyName, totalNotes[j]);
            }
            if(modifyDenom == false){
                System.out.printf("%d Dirhams: %d\n", denominations[j], totalNotes[j]);
            }
        }
        System.out.printf("========================================");
    }

    //calculating the sum of all amounts inputted by user in database and displays to user
    public static void totalAmount(int size, ChangeC[] people){
        int total = 0;
        for(int i=0; i<size; i++){
            total = total+people[i].getAmount();    //gets the amount of each individual and adds them up
        }
        System.out.printf("========================================");
        System.out.println("\nThe total amount is " + total);
        System.out.printf("========================================");
    }
    
    public static void studentInfo(){
        System.out.println("\nName: Giovanni Icasiano Jr.");
        System.out.println("Student ID: 34629097");
        System.out.println("Mode of Enrolment: Internal");
        System.out.println("Tutor: Ms. Noor Alkhateeb\n");
    }
    
    //Please type "test" in the Enter name section
    //hard code objects for tutor to test program
    private static int hardCode(ChangeC[] people, int[] denominations){
        //hardcode for default denominations (dirhams)
        people[0] = new ChangeC("Max", 860);
        people[1] = new ChangeC("Charles", 860);
        people[2] = new ChangeC("Lando", 130);
        people[3] = new ChangeC("Kevin", 990);
        people[4] = new ChangeC("Lewis", 450);
        people[5] = new ChangeC("Gio", 720);
        people[6] = new ChangeC("Oscar", 1300);
        people[7] = new ChangeC("Nico", 120);
        people[8] = new ChangeC("Alex", 850);
        people[9] = new ChangeC("Lauren", 380);
        people[10] = new ChangeC("Jake", 5);
        people[11] = new ChangeC("John", 9890);
        //hardcode for customized currency notes
        if(modifyDenom == true){
            people[0] = new ChangeC("Max", 860, denominations);
            people[1] = new ChangeC("Charles", 860, denominations);
            people[2] = new ChangeC("Lando", 130, denominations);
            people[3] = new ChangeC("Kevin", 990, denominations);
            people[4] = new ChangeC("Lewis", 450, denominations);
            people[5] = new ChangeC("Gio", 720, denominations);
            people[6] = new ChangeC("Oscar", 1300, denominations);
            people[7] = new ChangeC("Nico", 120, denominations);
            people[8] = new ChangeC("Alex", 850, denominations);
            people[9] = new ChangeC("Lauren", 380, denominations);
            people[10] = new ChangeC("Jake", 5, denominations);
            people[11] = new ChangeC("John", 9890, denominations);
        }
        return 12;
    }
}
