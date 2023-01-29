package andliz1;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Klass som hanterar logiken och sköter metoderna mot kunden
 * @author Andreas Linder, andliz-1
 */
public class Logic implements Serializable
{
    private static final long serialVersionUID = 1L;

    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private ReservationList times = new ReservationList();

     /**
     * Metod för att söka igenom arrayen customerList för att hitta en specifik kund med asvseende på pNo
     * Loopar igenom customList och om pNo stämmer överrens med den vi letar efter så skapar vi ett referensobjekt till objektet
     * @param existingCustomer Referensobjekt till objektet vi söker efter med ett visst pNo
     * @return Referensobjektet
     */ 
    public Customer searchExistingCustomer (String pNo)
    {
        for (Customer customer: customerList)
        {
            if (customer.comparePNo(pNo))
            {
                return customer;
            }
        }
        return null;
    }

    /**
     * Metod för att söka igenom arrayen customerList för att hitta en specifik kund med asvseende på pNo
     * Loopar igenom customList och om pNo stämmer överrens med den vi letar efter så skapar vi ett referensobjekt till objektet
     * @param existingCustomer Referensobjekt till objektet vi söker efter med ett visst pNo
     * @return Referensobjektet
     */ 
    public boolean addCustomer(String name, String surname, String pNo)
    {

        if (searchExistingCustomer(pNo) == null)
        {
            Customer customer = new Customer(name, surname, pNo);
            customerList.add(customer);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Metod för att ta bort kund
     * När kund tas bort så tas också dess bokade tider bort
     * @param customer Kunden vi tar bort
     * @return true om det gick att ta bort, annars false
     */ 
    public boolean deleteCustomer(int index)
    {
        Customer customer = customerList.get(index);

        if (customer != null)
        {
            ArrayList<String> timesToRemove = customer.removeAllReservations();

            for (String string : timesToRemove) 
            {
                times.setTrue(string);    
            }
            customerList.remove(customer);   
            return true;                                      
        }
        else
        {
            return false;
        }

    } 
    
    /**
    * Metod för att få längden på kundlistan
    * Används i Menuklass för att inte gå ArrayIndexOutOfBonds
    */
    public int customerSize()
    {
        return customerList.size();
    }

    /**
    * Metod för att ändra förnamn
    */
    public boolean changeFirstName(int index, String firstName)
    {
        Customer customer = customerList.get(index);

        if (customer != null)
        {
            if (firstName.equals(""))
                return false;
            else
            {
                customer.setFirstName(firstName);
                return true;
            }
        }
        else
            return false;
    }

    /**
    * Metod för att ändra efternamn
    */
    public boolean changeLastName(int index, String LastName)
    {
        Customer customer = customerList.get(index);

        if (customer != null)
        {
            if (LastName.equals(""))
                return false;
            else
            {
                customer.setLastName(LastName);
                return true;
            }
        }
        else
            return false;
    }


    /**
    * Metod för att få information om kundens namn
    */
    public String getNameInformation(int arrayIndex)
    {
        Customer customer = customerList.get(arrayIndex);

        String names = (customer.getFirstName() + " " + customer.getLastName());

        return names;
    }
    
    /**
    * Metod för att printa ut alla kunder
    */
    public void printAllCustomers()
    {
        int i = 1;

        for (Customer customer: customerList)
        {
            System.out.println(i + ". " + customer.toString());
            i++;
        }
    }

    /**
    * Metod för att printa ut alla lediga bokade tider
    */
    public void printTimes()
    {
        times.printAvailableTimes();
    }

    /**
    * Metod för att göra en ny bokning av tid
    */
    public boolean newAppointment(int customerIndex, int resevationIndex)
    {
        if(times.status(resevationIndex) == false)
            return false;
        else
        {
            String time = times.toString(resevationIndex);
            Customer customer = customerList.get(customerIndex);
            customer.createReservation(time);
            times.setFalse(resevationIndex);
            return true;
        }

    }

    /**
    * Metod för att ta bort en bokad tid
    */
    public void removeAppointment(int customerIndex, int resevationIndex)
    {
        Customer customer = customerList.get(customerIndex);
        Reservation reservation = customer.searchExistingReservationIndex(resevationIndex);
        String time = reservation.toString();
        times.setTrue(time);

        customer.removeReservation(time);
    }

    /**
    * Metod för att printa ut reserverade tider
    */
    public void printReservations(int customerIndex)
    {
        Customer customer = customerList.get(customerIndex);

        customer.printReservations();
    }

    /**
    * Metod för att printa ut all information om kund och dess reserverade tider
    */
    public void printAllInformation()
    {
        for (Customer customer : customerList) 
        {
            System.out.println(customer.toString());
            customer.printReservations();
        
        }
    }

}
