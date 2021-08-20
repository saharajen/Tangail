package utilities;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static String returnNextMonth() {
        //Create a data object
        Date dNow = new Date();

        //Create calender object for Gregorian Calender
        Calendar calendar = new GregorianCalendar();

        //Set calender date to current date
        calendar.setTime(dNow);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
        calendar.add(Calendar.MONTH, 1);
        return sdf.format(calendar.getTime());
    }
}
