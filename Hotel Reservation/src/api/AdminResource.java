package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource singleton;
    private AdminResource(){};
    public static AdminResource getSingleton(){
        if(singleton == null){
            singleton = new AdminResource();
        }
        return singleton;
    }
    CustomerService customerService = CustomerService.getSingletonInstance();
    ReservationService reservationService = ReservationService.getsingletonInstance();

    public Customer getCustomer(String email){
//        this will go and search customer in customerMap map by this email and return that customer if available in customerMap
        return customerService.getCustomer(email);
    }

    public void addRoom(IRoom room){

            reservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }


}
