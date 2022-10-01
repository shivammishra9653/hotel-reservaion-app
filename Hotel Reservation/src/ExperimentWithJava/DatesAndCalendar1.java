package ExperimentWithJava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DatesAndCalendar1 {
    public static void main(String[] args) {

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        System.out.println("Please Enter a date");
//        Scanner sc = new Scanner(System.in);
//        Calendar calendar = Calendar.getInstance();
//
//        String date = sc.nextLine();
//        try{
//            calendar.setTime(simpleDateFormat.parse(date));
//            System.out.println(calendar.getTime());
//        }catch(ParseException ex){
//            System.out.println("Enter a valid date");
//        }

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println("before .add() = " + calendar.getTime());
        calendar.add(Calendar.DATE, 7);
        System.out.println("after .add() = " + calendar.getTime());

        calendar.set(2022, 04, 10);
        System.out.println(calendar.getTime());
        calendar.add(Calendar.DATE, 7);
        System.out.println(calendar.getTime());

    }
}
