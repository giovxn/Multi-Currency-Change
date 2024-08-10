package ChangeC;

/**
 *
 * @author Gio
 */

//class for holding individual data (name and amount) and denomination of currency
public class ChangeC{
    //private instance variables that Encapsulates data---
    private String name;    
    private int amount;
    private int denomination1, denomination2, denomination3, denomination4, denomination5, denomination6, denomination7, denomination8; //declaring private instance variable for all denominations (this can be an array of denominations)
    
    //default constructor
    //sets the default values instance variables should have in order for the program to work.
    public ChangeC(){
        name = "No name";
        amount = 0;
        denomination1 = 5;
        denomination2 = 10;
        denomination3 = 20;
        denomination4 = 50;
        denomination5 = 100;
        denomination6 = 200;
        denomination7 = 500;
        denomination8 = 1000;
    }

    //constructor that takes name and amount as a paramater and provides default denomination for hard code
    public ChangeC(String name, int amount){
        setName(name);
        setAmount(amount);
        denomination1 = 5;
        denomination2 = 10;
        denomination3 = 20;
        denomination4 = 50;
        denomination5 = 100;
        denomination6 = 200;
        denomination7 = 500;
        denomination8 = 1000;
    }

    //constructor that takes manually inputted denominations 
    public ChangeC(int[] denominations){
        setDenominations(denominations);
    }

    //constructor for setting data for user inputted currency denominations
    public ChangeC(String name, int amount, int[] denominations){
        setName(name);
        setAmount(amount);
        setDenominations(denominations);
    }

    //getter and setter method---

    //setters:
    //encapsulates name - takes name from main and stores here privately
    public void setName(String name){
        this.name = name;
    }

    //encapsulates amount - takes name from main and stores here privately, also validates that amount is a multiple of 5, if not make amount 0
    public void setAmount(int amount){
        if(amount%5 != 0){
            this.amount = 0;
        }
        else{
            this.amount = amount;
        }
    }

    public void setDenominations(int[] denominations){
        this.denomination8 = denominations[7];
        this.denomination7 = denominations[6];
        this.denomination6 = denominations[5];
        this.denomination5 = denominations[4];
        this.denomination4 = denominations[3];
        this.denomination3 = denominations[2];
        this.denomination2 = denominations[1];
        this.denomination1 = denominations[0];
    }

    //getters:
    //gets private instance variable and returns it to main
    public String getName(){
        return name;
    }
    public int getAmount(){
        return amount;
    }

    //method for calculating and displaying change in set denominations of dirhams
    public int[] getChange(boolean displayData, boolean modifyDenom, String currencyName){
        int[] denominations = {denomination8, denomination7, denomination6, denomination5, denomination4, denomination3, denomination2, denomination1};
        int[] counts = new int[denominations.length];
        int sum = amount;
        int change = 0;
    
        for (int i=0; i<denominations.length; i++){
            int denomination = denominations[i];
            while (sum >= denomination){
                sum = sum - denomination;   
                change++;
                counts[i]++;
            }
            //checks if denomination used is default (Dirhams) or user-defined
            if (change > 0 && displayData){
                if(modifyDenom == true){
                    System.out.printf("%d %s: %d\n", denomination, currencyName, change);
                }
                if(modifyDenom == false){
                    System.out.printf("%d Dirhams: %d\n", denomination, change);
                }
            }
            change = 0;
        }
        return counts;
    }
    
}
