package UIComponenets;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Scanner;

public class AdminMenu {
   private static final AdminResource adminResource = AdminResource.getSingleton();
   private static  final HotelResource hotelResource = HotelResource.getSingleton();

    public static void adminMenu() {
        boolean keepRunning = true;
        try (Scanner sc = new Scanner(System.in)) {
            while (keepRunning) {
                try {
                    System.out.println(
                            "------------------------------------" + "\n" +
                                    "1. See all Customers" + "\n" +
                                    "2. See all Rooms" + "\n" +
                                    "3. See all Reservations" + "\n" +
                                    "4. Add a Room" + "\n" +
                                    "5. Back to Main Menu" + "\n" +
                                    "------------------------------------" + "\n"
                    );

                    int select = Integer.parseInt(sc.nextLine());

                    switch(select){
                        case 1:
                            System.out.println("See all Customer");
                            getAllCustomers();
                            break;
                        case 2:
                            System.out.println("See all Rooms");
                            getAllRooms();
                            break;
                        case 3:
                            System.out.println("See all Reservations");
                            getAllReservations();
                            break;
                        case 4:
                            System.out.println("Add a Room");
                            addRooms();
                            break;
                        case 5:
                            System.out.println("Back to Main Menu");
                            MainMenu.mainMenu();
                            keepRunning = false;
                            break;
                        default:
                            System.out.println("please enter a number between 1 and 5");
                    }


                } catch (Exception ex) {
                    System.out.println("\nError, Invalid Input in Admin menu");
                }
            }
        }

    }


    public static void getAllCustomers(){
//        find a list containing all customers that I can find from customerMap
        Collection<Customer> allCustomers = adminResource.getAllCustomers();

//        Now check if allCustomers List is empty or not if it is empty then simply print there are no customers else print all the customers contains in allCustomers List

        if(allCustomers.isEmpty()){
            System.out.println("allCustomers list has No customers there");
            return;
        }
//        if allCustomers list is not empty then print all customers
        for(Customer customer: allCustomers){
            System.out.println(customer.toString());
        }
    }


    public static void getAllRooms(){
// first make a list containing all Rooms that I can find from roomSet
        Collection<IRoom> allRooms = adminResource.getAllRooms();

//        check if allRooms set is empty or not if it is empty then print there is No rooms
        if(allRooms.isEmpty()){
            System.out.println("No Rooms there");
            return;
        }

        for(IRoom room: allRooms){
            System.out.println(room.toString());
        }
    }

    public static void getAllReservations(){
        adminResource.displayAllReservations();
    }

    public static void addRooms(){
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;

        while(keepRunning){

//            get a valid roomNumber
            String roomNumber = getValidRoomNumber();
//            get valid roomPrice
            double roomPrice = getValidPrice();
//            get valid roomType
            RoomType roomType = getValidRoomType();

//            create room contains roomNumber, roomPrice, roomType
            IRoom createdRoom = new Room(roomNumber, roomPrice, roomType);
//            Add Room in roomSet
            adminResource.addRoom(createdRoom);

            System.out.println("Would you like to add another room?\n Enter y for yes or n for no");
            String selection = sc.nextLine();

            if(!selection.equalsIgnoreCase("y")){
                keepRunning = false;
            }
        }
    }

    public static String getValidRoomNumber(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number: ");
        String roomNumber = sc.nextLine();

        IRoom room = hotelResource.getRoom(roomNumber);

        if(room == null){
            return roomNumber;
        }else{
            System.out.println("That room already exists, try for other");
            getValidRoomNumber();
        }

        return null;
    }


    public static double getValidPrice(){
        Scanner sc = new Scanner(System.in);
        double roomPrice = 0.0;
        try{
            System.out.println("Enter price per night:");
             roomPrice = Double.parseDouble(sc.next());
            if(roomPrice < 0){
                System.out.println("Price must be greater than or equal to 0.00");
                getValidPrice();
            }
        }catch (Exception ex){
            System.out.println("Error, Enter a valid price");
        }

        return roomPrice;
    }

    public static RoomType getValidRoomType(){
        Scanner sc = new Scanner(System.in);
        RoomType roomType = null;
        try{
            System.out.println("Enter RoomType 1 for single bed, 2 for double bed:");
            roomType = RoomType.valueOfLabel(sc.nextLine());
            if(roomType == null){
                System.out.println("Please enter a valid room type");
                getValidRoomType();
            }
        }catch (Exception ex){
            System.out.println("Error, Please enter a valid room type");
        }
        return roomType;
    }

//    let's test our functions
//    public static void main(String[] args) {
//        adminMenu();
//    }
}


