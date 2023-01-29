package andliz1;

import java.io.Serializable;

/**
 * Klass för att göra en reservation
 * @author Andreas Linder, andliz-1
 */
public class Reservation implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String time;


    /**
     * Constructor
     * @param time tid på bokning
     */
    public Reservation(String time)
    {
        this.time = time;
    }

    /**
     * Metod för att printa ut tiden
     */
    public String toString()
    {
        return time;
    }

    /**
     * Metod för att jämföra tiden 
     */
    public boolean compareTime(String time) 
    {
        if (time.equals(toString()))
            return true;
        else
            return false;
    }    
}
