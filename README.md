# Multi-Currency-Change
A Java-based currency change calculator developed for ICT167 - Assignment 1. The program calculates change using specified currency denominations, handling data for multiple individuals, and displays results based on user inputs. It supports both default and user-defined denominations, with a focus on accuracy and efficiency.

This program is made to handle and process the data of ten or more people, each of whom has a name and an amount in dirhams. The program calculates change using the Dirham denominations of 5, 10, 20, 50, 100, 200, 500, and 1000 but only accepts sums that are multiples of 5. But user can manually enter their desired denominations and name of currency.

The primary features consist of: 
Asks user if they would like to use the default currency (Dirhams) or manually enter in their own currency notes.

Entering the names and sums for every person. presenting a menu with choices for: 
•	Enter a name, and the change will be seen for that specific person.
•	Locate the name with the least amount and show it together with the matching change.
•	Locate the name with the largest amount and the matching change, then display it.
•	Determine and show the total amount of cash notes in each denomination. 
•	Compute and show the total for each denomination.
•	Close the software.  

============================================================================================================

How to use the program:
•	Once you are presented with a welcome test, you can either enter 1 or 2 as a choice, don’t forget to click enter when you input your number
o	If you chose 2, you must enter denominations your preferred currency from lowest to highest. At the end you can also name your currency, then press enter to continue.
•	Once you have selected or finished entering your desired denominations, you must enter at least 10 names and amounts where names must be unique from the previous entry you entered and all amounts must be a multiple of 5.
•	After each entry, you are asked if you’d like to enter more entries.
•	After entering names and amounts, you are presented with a menu of what you would like to do with the entered data. Enter a value from 1 to 6 inclusive of what menu choice you would like.
o	If you chose 1, which is search name, you must enter a name existing in your entries. 
•	Ones you are done, press 6 and you have successfully used the program!

How to get preset data (hard coded):
•	Press 1 or 2 for which mode you would like.
•	In the “Enter name: “ section please type “test” (this works for both mode 1 or 2)
•	The list of names are shown in the TestChange class (e.g. Gio, Alex, Max).
