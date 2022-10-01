package ExperimentWithJava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatesAndCalendar {
    public static void displayCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar = " + calendar);

        Date date = new Date();
        System.out.println("date = "+ date);

        calendar.setTime(date);
        System.out.println("calendar after calendar.setTime(date)" + calendar);
        System.out.println("calendar.getTime() = " + calendar.getTime());
    }

    public static void displaySetDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2031, 02, 02);
        Date date = calendar.getTime();
        System.out.println(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String custom_date_format = simpleDateFormat.format(date);
        System.out.println("custom_date_format = " + custom_date_format);

    }
    public static void main(String[] args) {
//        displayCurrentDate();
        displaySetDate();
    }
}
