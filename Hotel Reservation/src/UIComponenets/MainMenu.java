package UIComponenets;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    private static HotelResource hotelResource = HotelResource.getSingleton();
    public static void mainMenu(){
        boolean keepRunning = true;
        try(Scanner sc = new Scanner(System.in)) {
            while(keepRunning) {
                try {

                    System.out.println("Welcome to the Hotel Reservation Application\n");
                    System.out.println(
                            "---------------------------------" + "\n" +
                                    "1. Find and reserve a room" + "\n" +
                                    "2. See my reservations" + "\n" +
                                    "3. Create an account" + "\n" +
                                    "4. Admin" + "\n" +
                                    "5. Exit" + "\n" +
                                    "----------------------------------" + "\n"
                    );
                    System.out.println("Please select a number for the menu option\n");
                    int selection = Integer.parseInt(sc.nextLine());
                    switch(selection){
                        case 1:
                            System.out.println("Find and reserve a Room");
                            findAndReserveRoom();
                            break;
                        case 2:
                            System.out.println("see my reservations");
                            getMyReservation();
                            break;
                        case 3:
                            System.out.println("create an account");
                            createAnAccout();
                            break;
                        case 4:
                            System.out.println("admin");
                            AdminMenu.adminMenu();
                            keepRunning = false;
                            break;
                        case 5:
                            System.out.println("Exit");
                            keepRunning = false;
                            break;

                        default:
                            System.out.println("Please enter a number between 1 and 5\n");

                    }
                }catch(Exception ex){
                    System.out.println("\nError, Invalid Input, in main Menu\n");
                }
            }
        }

    }

    public static void findAndReserveRoom(){
        Scanner sc = new Scanner(System.in);
//        here we are going to find valid checkInDate and CheckOutDate
        Date checkInDate = getCheckInDate();

        Date checkOutDate = getCheckOutDate(checkInDate);


//        find all avaialable Rooms within checkInDate and checkOutDate
        Collection<IRoom> availableRooms = findAvailableRooms(checkInDate, checkOutDate);

//       Now first check room is available or not within checkInDate and checkOutDate
        if(availableRooms.isEmpty()){
//            If availableRooms list is empty then check room availability for next 7 days
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkInDate);
            calendar.add(Calendar.DATE, 7);
            Date alternateCheckInDate = calendar.getTime();

            calendar.setTime(checkOutDate);
            calendar.add(Calendar.DATE, 7);
            Date alternateCheckOutDate = calendar.getTime();


            availableRooms = findAvailableRooms(alternateCheckInDate, alternateCheckOutDate);

            if(availableRooms.isEmpty()){
                System.out.println("No available Rooms for those days");
            }else{
                for(IRoom room: availableRooms){
                    System.out.println(room.toString());
                }

                System.out.println("\nNo Rooms available for those days\n " +
                        "Do you wanted to book within checkInDate " + alternateCheckInDate + " checkOutDate " + alternateCheckOutDate +"\n" +
                        "Enter y/n"
                );

                String option = sc.nextLine();

//                check if user select y
                if(option.equalsIgnoreCase("y")){
                    checkInDate = alternateCheckInDate;
                    checkOutDate = alternateCheckOutDate;
                    bookRoom(checkInDate, checkOutDate, availableRooms);
                    return;
                }else{
                    return;
                }


            }

        }else{
            System.out.println("Available rooms within checkInDate "+ checkInDate + " and checkOutDate "+ checkOutDate);
            for(IRoom room: availableRooms){
                System.out.println(room.toString());
            }
            System.out.println("Do you wanted to book within checkInDate " + checkInDate + " checkOutDate " + checkOutDate +"\n" +
                    "Enter y/n");
            String option = sc.nextLine();
            if(option.equalsIgnoreCase("y")){
                bookRoom(checkInDate, checkOutDate, availableRooms);
            }else{
                return;
            }
        }
    }

    public static void bookRoom(Date checkInDate, Date checkOutDate, Collection<IRoom> availableRooms){
        String email = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you have already created your account? Enter y/n");
        String option = sc.nextLine();
        if(option.equalsIgnoreCase("y")){
            System.out.println("Please enter your Email example: name@domain.com");
            email = sc.nextLine();
        }else{
            email = createAnAccout();
        }

        Customer customer = hotelResource.getCustomer(email);
        if(customer == null){
            System.out.println("Sorry, no account exists for that email");
            return;
        }

        IRoom room = findRoomForReservation(availableRooms);

        Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);

        System.out.println("Your room has booked successfully!\n Thank you!");
        System.out.println(reservation);

    }

    public static IRoom findRoomForReservation(Collection<IRoom> availableRooms){
        Scanner sc = new Scanner(System.in);
        IRoom room = null;
        String roomNumber = null;
        System.out.println("Enter the room number: ");
        roomNumber = sc.nextLine();
        room = hotelResource.getRoom(roomNumber);
        if(room == null){
            System.out.println("This room doesn't exists:");
            findRoomForReservation(availableRooms);
        }else{
            if(availableRooms.contains(room)){
                return room;
            }else{
                System.out.println("That room is not available");
                findRoomForReservation(availableRooms);
            }
        }
        return null;
    }

    public static Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate){
        return hotelResource.findARoom(checkInDate, checkOutDate);
    }

    public static Date getCheckInDate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CheckInDate MM/dd/yyyy example 02/01/2020");
        String dt = sc.nextLine();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try{
            Date today = new Date();
            if(simpleDateFormat.parse(dt).before(today)){
                System.out.println("Check-In Date cannot be in the past");
                getCheckInDate();
            }else{
                calendar.setTime(simpleDateFormat.parse(dt));
                return  calendar.getTime();
            }

        }catch (ParseException ex){
            System.out.println("Invalid date, Enter a valid date format MM/dd/yyyy");
            getCheckInDate();
        }
        return null;
    }

    public static Date getCheckOutDate(Date checkInDate){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CheckOutDate MM/dd/yyyy example 02/01/2020");
        String dt = sc.nextLine();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try{

            if(simpleDateFormat.parse(dt).before(checkInDate)){
                System.out.println("The checkOutDate cannot be before checkInDate");
                getCheckOutDate(checkInDate);
            }else{
                calendar.setTime(simpleDateFormat.parse(dt));
                return calendar.getTime();
            }

        }catch (ParseException ex){
            System.out.println("Invalid date, Enter a valid date format MM/dd/yyyy");
            getCheckOutDate(checkInDate);
        }
        return null;
    }

    public static String createAnAccout(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Email format: name@domain.com");
        String customerEmail = sc.nextLine();
        System.out.println("First Name:- ");
        String firstName = sc.nextLine();
        System.out.println("Last Name:- ");
        String lastName = sc.nextLine();

        try{
            hotelResource.createACustomer(customerEmail, firstName, lastName);
            mainMenu();
        }catch(Exception ex){
            System.out.println(ex.getLocalizedMessage());
            createAnAccout();
        }
         return customerEmail;
    }


   public static void getMyReservation(){
        Scanner sc = new Scanner(System.in);
       System.out.println("Enter your Email example: name@domain.com: ");
       String email = sc.nextLine();
       Customer customer = hotelResource.getCustomer(email);

       if(customer == null){
           System.out.println("No account fond Please create your account then only you can book room");
           return;
       }

       printReservation(email);
   }

   public static void printReservation(String email){
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        if(reservations.isEmpty()){
            System.out.println("No reservation available for this "+ email);
            return;
        }else{
            Iterator<Reservation> it = reservations.iterator();

            while(it.hasNext()){
                System.out.println(it.next().toString());
            }
        }
   }


    public static void main(String[] args) {
        mainMenu();
    }

}
