package gt.edu.umg.ingenieria.sistemas.laboratorio1.utils;

import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;

public class Helpers {
    public static boolean finIgualNAnios(Date birtDate, int N) {
        Calendar calendar = getCalendar(new Date());
        Calendar calendar1 = getCalendar(birtDate);
        int diferencia = calendar.get(YEAR) - calendar1.get(YEAR);
        if
        (
                calendar1.get(MONTH) > calendar.get(MONTH) ||
                        (calendar1.get(MONTH) == calendar.get(MONTH) && calendar1.get(DATE) > calendar.get(DATE))
        )
        {
            diferencia--;
        }
        return diferencia >= N;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
