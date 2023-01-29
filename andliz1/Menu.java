package andliz1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Scanner;


/**
 * Klass som visar upp menyn och tar emot inläsning från användaren
 * @author Andreas Linder, andliz-1
 */
public class Menu
{
    Logic logic = new Logic();

    /**
    * Metod för att starta Main meny som fortsätter tills användaren vill avsluta
    */
    public void mainMenu()
    {
        do 
        {
            printMenu();

            switch(input())
            {
                case 1:
                    addCustomerMenu();
                    break;
                case 2:
                    mangeCustomerMenu();
                    break;
                case 3:
                    newAppointment();
                    break;
                case 4:
                    manageAppointment();
                    break;
                case 5:
                    showInformation();
                    break;
                case 6:
                    readFromFile();
                    break;
                case 7:
                    saveToFile();
                    break;               
                case 8:
                System.exit(0);
                    break;
                default:
                    System.out.println("Vänligen välj ett nummer mellan 1-8"); // Om det är en int som inte finns i menyn talar programmet om det
                    break;
            }
            
        } while (true);
    }

    /**
    * Metod för att visa information om bokade tider för en viss kund
    */
    private void showInformation() 
    {
        int arrayIndex = getCustomerIndex();

        System.out.println("Bokade tider för " + logic.getNameInformation(arrayIndex) + ": ");

        logic.printReservations(arrayIndex);
    }

    /**
    * Metod för att hantera en bokning
    */
    private void manageAppointment() 
    {
        int arrayIndex = getCustomerIndex();

        System.out.println("Vad vill du göra idag?");
        System.out.println("1. Boka om tid");
        System.out.println("2. Radera bokning");

        switch(input())
        {
            case 1:
                System.out.println("Vilken bokning vill du boka om?");
                try 
                {
                    logic.printReservations(arrayIndex);
                    int manageAppointmentIndex = input() - 1;
                    logic.removeAppointment(arrayIndex, manageAppointmentIndex);
                    logic.printTimes();
                    System.out.println("vilken tid vill du boka? Välj ett nr från listan ovan: ");
                    int timeChoice = input() - 1;
                    logic.newAppointment(arrayIndex, timeChoice);

                } catch (IndexOutOfBoundsException e) 
                {
                    System.out.println("Du måste välja ett nr från listan!");
                }


                break;
            case 2:
                System.out.println("Vilken bokning?");
                try
                {
                    logic.printReservations(arrayIndex);
                    int reservsationIndex = input() - 1;
                    logic.removeAppointment(arrayIndex, reservsationIndex);
                } catch (IndexOutOfBoundsException e) 
                {
                    System.out.println("Välj ett nr från listan!");
                }


                break;
            default:
                System.out.println("Välj ett nr mellan 1 och 2.");
                break;
        }
    }

    /**
    * Metod för att läsa in från fil -> Jag valde att spara och läsa in hela objektet logic
    */
    private void readFromFile() 
    {
        try 
            {
                FileInputStream file = new FileInputStream("BookingSystem.dat");
                ObjectInputStream input = new ObjectInputStream(file);
                logic = (Logic) input.readObject();

                file.close();
                input.close();
            } 
            
            catch (FileNotFoundException e)
            {
                System.out.println("The file you are trying to open does not exist");
            } 

            catch(StreamCorruptedException e)
            {
                System.out.println("The file you are trying to open is in wrong format");
            }
            
            catch (IOException e) {
                e.printStackTrace();
            } 
            
            catch (ClassNotFoundException e)
            {
                e.printStackTrace(); 
            } 
    }

    /**
    * Metod för att spara till fil -> Jag valde att spara och läsa in hela objektet logic
    */
    public void saveToFile()
    {
        try 
        {
            FileOutputStream file = new FileOutputStream("BookingSystem.dat");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(logic);
            output.close();
            file.close();
        }

        catch(FileNotFoundException e)
        {
            System.out.println("You do not have permisson to save this file, try with another name");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
    * För att navigera mellan kunder så valde jag att printa ut alla i en lista
    * Användaren får sedan skriva in det nr som namnet man vill hantera är på
    * Jag tyckte det var ett smidigt sätt att hantera kunderna på 
    */
    public int getCustomerIndex()
    {
        final Scanner sc = new Scanner(System.in);

        System.out.println("\nVilken kund? Skriv numret från listan nedan. Välj mellan 1-" + logic.customerSize());
        logic.printAllCustomers();

        int userChoice;

        do 
        {   
        userChoice = input();
            
        } while (userChoice < 0 || userChoice > logic.customerSize());
        

        return userChoice - 1;
    }

    /**
    * Metod för att hantera kunden
    */
    public void mangeCustomerMenu() 
    {
        final Scanner sc = new Scanner(System.in);

        int arrayIndex = getCustomerIndex();


        System.out.println("Vad vill du göra idag?");
        System.out.println("1. Byt förnamn");
        System.out.println("2. Byt efternamn");
        System.out.println("3. Radera kund");
        System.out.println("4. Avbryt");

        switch(input())
        {
            case 1:
                System.out.print("Nytt förnamn:");
                String newFirstName = sc.nextLine();
                if (logic.changeFirstName(arrayIndex, newFirstName))
                    System.out.println("Förnamn ändrat!");
                else
                    System.out.println("Namn inte ändrat");
                break;
            case 2:
                System.out.print("Nytt efternamn:");
                String newLastName = sc.nextLine();
                if (logic.changeLastName(arrayIndex, newLastName))
                    System.out.println("Efternamn ändrat!");
                else
                    System.out.println("Namn inte ändrat");
                break;
            case 3:
                if (logic.deleteCustomer(arrayIndex))
                    System.out.println("Kund raderad!");
                else
                    System.out.println("kund inte raderad!");
                break;
            case 4:
                break;
            default:
                System.out.println("Vänligen välj ett nummer mellan 1-4"); // Om det är en int som inte finns i menyn talar programmet om det
                break;
        }         
    }

    /**
    * Metod för att göra en ny bokning
    */
    public void newAppointment()
    {
        final Scanner sc = new Scanner(System.in);

        int arrayIndex = getCustomerIndex();

        logic.printTimes();

        System.out.println("vilken tid vill du boka? Välj ett nr från listan ovan: ");

        int timeChoice = input() - 1;

        try 
        {
            if(logic.newAppointment(arrayIndex, timeChoice))
                System.out.println("Tid bokad!");
            else
                System.out.println("Tid är upptagen!");
            
        } catch (IndexOutOfBoundsException e) 
        {
            System.out.println("Du måste välja ett nr från listan!");
        }


    }

    /**
    * Metod för att printa menyn
    */
    public void printMenu()
    {
        System.out.println("\n1. Lägg till kund");
        System.out.println("2. Hantera kund");
        System.out.println("3. Gör ny bokning");
        System.out.println("4. Hantera befintlig bokning");
        System.out.println("5. Få information om kund");
        System.out.println("6. Läs in fil");
        System.out.println("7. Spara fil");
        System.out.println("8. Avsluta");
    }

    /**
    * Metod för att ta emot input via menyerna
    * Metoden ser till så att programmet inte kraschar ifall användaren skriver in något annat än siffror
    */
    public int input()
    {
        final Scanner sc = new Scanner(System.in);
        int userInput = 0;       

        while(true)
        {
            if (sc.hasNextInt())
            {
                userInput = sc.nextInt(); 
                sc.nextLine();   
                break;
            }

            else
            {
                sc.nextLine();
            }
        }
        
        return userInput;
    }

    /**
    * Metod för lägga till kund
    */
    public void addCustomerMenu()
    {
        final Scanner sc = new Scanner(System.in);
        String firstName;
        String lastName;
        String pNo;

        System.out.print("Förnamn: ");
        firstName = sc.nextLine();
        System.out.print("Efternamn: ");
        lastName = sc.nextLine();
        System.out.print("Personnummer: ");
        pNo = sc.nextLine();

        if (logic.addCustomer(firstName, lastName, pNo))
        System.out.println("Kund tillagd!");

        else
            System.out.println("Kund finns redan!");
    }
}