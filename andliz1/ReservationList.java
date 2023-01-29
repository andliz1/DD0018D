package andliz1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Klass som hanterar lediga och uppbokade tider
 * @author Andreas Linder, andliz-1
 */
public class ReservationList implements Serializable
{
    private static final long serialVersionUID = 1L;

    private ArrayList<String> timeList = new ArrayList<>(Arrays.asList("8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"));
    private ArrayList<Boolean> availableTime = new ArrayList<>(Arrays.asList(true, true, true, true, true, true, true, true, true, true));

public ReservationList(){};

/**
 * Metod för att printa ut alla lediga tider
 */
public void printAvailableTimes()
{
    System.out.println("Lediga tider: ");

    for (int i = 0; i < timeList.size(); i++) 
    {
        if(availableTime.get(i) == true)
        {
            System.out.print(i+1+ ". " + timeList.get(i) + "\n");
        }
    }
}

/**
 * Metod för att sätta en tid till false (upptagen tid)
 */
public void setFalse(int reservationIndex)
{
    availableTime.set(reservationIndex, false);
}

public boolean status(int reservationIndex)
{
    return availableTime.get(reservationIndex);
}

/**
 * Metod för att sätta en tid till true (ledig tid)
 */
public void setTrue (String time)
{
    for (int i = 0; i < timeList.size(); i++) 
    {
        if(timeList.get(i).equals(time))
        {
            availableTime.set(i, true);
            break;
        }
    }
}

/**
 * Metod för att få tiden i arraylisten timeList
 */
public String toString(int reservationIndex)
{
    return timeList.get(reservationIndex);
}

}
