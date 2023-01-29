package andliz1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klass som hanterar kunden och som håller koll kundens bokade tider
 * @param accountList Array som sparar objekt som skapas. 
 * @author Andreas Linder, andliz-1
 */
public class Customer implements Serializable
{
    private static final long serialVersionUID = 1L;
    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

    private String firstName;
    private String lastName;
    private String pNo;


    /**
     * Constructor
     * @param firstName Förnamn
     * @param lastName Efternamn
     * @param pNo personnr
     * Varje gång en kund skapas så måste förnamn, efternamn och personnr ges som info
     */
    public Customer(String firstName, String lastName, String pNo)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pNo = pNo;
    }

    /**
    * Metod för att jämföra personNr på konton
    */
    public Boolean comparePNo(String pNo)
    {
        if (pNo.equals(getpNo()))
            return true;
        else
            return false;
    }

    /**
    * Metod för att leta efter en reserverad tid med avseende på tid
    @return Reservationsobjekt på den reserverade tiden
    */
    public Reservation searchExistingReservation (String time)
    {
        for (Reservation reservation: reservationList)
        {
            if (reservation.compareTime(time))
            {
                return reservation;
            }
        }
        return null;
    }

    /**
    * Metod för att söka efter reserverad tid med avssende på index i arraylist
    */
    public Reservation searchExistingReservationIndex (int resevationIndex)
    {
        Reservation reservation = reservationList.get(resevationIndex);
        return reservation;
    }

    /**
    * Metod för att skapa en reservation
    */
    public void createReservation(String time)
    {
        Reservation reservation = new Reservation(time);
        reservationList.add(reservation);
    }

    /**
    * Metod för att ta bort reservation
    */
    public void removeReservation(String time)
    {
        Reservation reservation = searchExistingReservation(time);
        reservationList.remove(reservation);
    }

    /**
    * Metod för returnera alla tider som en kund har som man vill ta bort
    * @return ArrayList med alla tider som en kund har som man vill ta bort
    */
    public ArrayList<String> removeAllReservations()
    {
        ArrayList<String> times = new ArrayList<>();

        for (Reservation reservation : reservationList) 
        {
            times.add(reservation.toString());
        }

        return times;
    }

    // ------------ Setters -----------

    /**
     * Metod för att byta namn hos kund
     * @return nytt namn
     */ 
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Metod för att byta efternamn hos kund
     * @return nytt efternamnnamn
     */ 
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    // ------------ Getters -----------

    /**
     * Metod för att få namn
     * @return namn
     */ 
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Metod för att få efternamn
     * @return efternamn
     */ 
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Metod för att få personNr
     * @return personNr
     */ 
    public String getpNo()
    {
        return pNo;
    }

    /**
     * Metod för att få info om kund (Namn Efternamn)
     * @return info om kund
     */ 
    public String toString()
	{
		String info = getFirstName()  + " " + getLastName();
        return info;
	}

    /**
    * Metod för att printa reservationer
    */
    public void printReservations()
    {
        int i = 1;
        for (Reservation reservation: reservationList)
        {
            System.out.println(i + ". " + reservation.toString());
            i++;
        }
    }


}
