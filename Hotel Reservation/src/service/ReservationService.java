package service;

import model.*;

import java.util.*;

public class ReservationService {
    Map<String, Collection<Reservation>> reservationMap = new HashMap<>();
//    here I am using Set Collection to store all room so that same room can not be store again and in rubric it is recommended
    Set<IRoom> roomSet = new HashSet<>();

    private static ReservationService singleton;
    private ReservationService(){}

    public static ReservationService getsingletonInstance(){
        if(singleton == null){
            singleton = new ReservationService();
        }
        return singleton;
    }

public Collection<Reservation> getCustomersReservation(String customerEmail){
//        this will return a list of all the booking done by customer whose email is customerEmail
        return reservationMap.get(customerEmail);
}
    public void addRoom(IRoom room){
        roomSet.add(room);
    }

    public IRoom getARoom(String roomId){
        for(IRoom room : roomSet){
            if(room.getRoomNumber().equals(roomId)){
               return room;
            }
        }

        return null;
    }


//    this function returns a Set containing all rooms
    public Collection<IRoom> getAllRooms(){
        return roomSet;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){

//        find all reservations and store in a list
        Collection<Reservation> allReservations = new ArrayList<>();
        for(Collection<Reservation> reservation: reservationMap.values()){
            allReservations.addAll(reservation);
        }


//        now make a list which contains all the reserved room between checkInDate and checkOutDate
       final Collection<IRoom> reservedRoom = new ArrayList<>();
        for(Reservation reservation : allReservations){
            if(checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate())){
                reservedRoom.add(reservation.getRoom());
            }
        }

//        if room is reserved for given checkInDate and CheckOutDate then return null
        if(reservedRoom.contains(room)){
            return null;
        }


// if room is not reserved within given checkInDate and checkOutDate then create reservation and add that reservation in a list
        final Reservation reserVation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservations = reservationMap.get(customer.getEmail());
        if(customerReservations == null){
            customerReservations = new HashSet<>();
        }
           customerReservations.add(reserVation);
           reservationMap.put(customer.getEmail(), customerReservations);

        return reserVation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
//make a list containing all reservations
        Collection<Reservation> allReservations = new ArrayList<>();
        for(Collection<Reservation> reservation: reservationMap.values()){
            allReservations.addAll(reservation);
        }

//        make a list containing all reserved room within checkInDate and checkOutDate

        final Collection<IRoom> reservedRoom = new ArrayList<>();
        for(Reservation reservation : allReservations){
            if(checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate())){
                reservedRoom.add(reservation.getRoom());
            }
        }


//        make a list containing all available rooms within checkInDate and checkOutDate
        Collection<IRoom> availableRooms = new ArrayList<>();
        for(IRoom room : roomSet){
//            now check if room available in roomSet is not reserved then add in availableRooms list
            if(!(reservedRoom.contains(room))){
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    public void printAllReservation(){

//        reservationMap.values() will return a list containing all reservations
//        using forEach loop iterate over collection and print each element of collection
        if(reservationMap.values().isEmpty()){
            System.out.println("There are no reservations");
        }
        for(Collection<Reservation> reservations : reservationMap.values()){
            System.out.println(reservations);
        }
    }
}
