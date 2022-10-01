package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class HotelResource {
    private static HotelResource singleton;
    private HotelResource(){}
    public static HotelResource getSingleton(){
        if(singleton == null){
            singleton = new HotelResource();
        }

        return singleton;
    }
//    create Object of class CustomerService
    CustomerService customerService = CustomerService.getSingletonInstance();

//    create Object of class ReservationService
    ReservationService reservationService = ReservationService.getsingletonInstance();

    public Customer getCustomer(String email){
//        by this email find customer in customerMap
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
//        this will add the customer in customerMap Collection
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
//        this will search Room by roomNumber in roomSet Collection and if fond then that room will get return else null will be return
          return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){

        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
//        first check this customer is available in customerMap or not
        Customer customer = customerService.getCustomer(customerEmail);

//        if customer is not available in customerMap then return a emptyList
        if(customer == null){
            return Collections.emptyList();
        }

//        if customer is found then return all the reservations of that customer
        return reservationService.getCustomersReservation(customerEmail);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
//        this will return all available room
        return reservationService.findRooms(checkIn, checkOut);
    }


}
