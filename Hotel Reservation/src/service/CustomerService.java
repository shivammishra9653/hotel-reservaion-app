package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private final Map<String, Customer> customerMap = new HashMap<>();
    private static CustomerService singletonInstance;
    private CustomerService(){}
    public static CustomerService getSingletonInstance(){
        if(singletonInstance == null){
            singletonInstance = new CustomerService();
        }
        return singletonInstance;
    }




    public void addCustomer(String email, String firstName, String lastName){
        Customer customers = new Customer(firstName, lastName, email);
        customerMap.put(customers.getEmail(), customers);

    }

    public Customer getCustomer(String customerEmail){

        return customerMap.get(customerEmail);
    }

    public Collection<Customer> getAllCustomer(){
        Collection<Customer> allCustomerList = new ArrayList<>();
        for(Customer customer: customerMap.values()){
            allCustomerList.add(customer);
        }
        return allCustomerList;
    }


//    Let's take a test of all functions
//    public static void main(String[] args) {
//        CustomerService customerService = CustomerService.getSingletonInstance();
////        customerService.customerMap.put("shivam@gmail.com", new Customer("shivam", "mishra", "shivam@gmail.com"));
////        customerService.customerMap.put("sandesh@gmail.com", new Customer("sandesh", "mishra", "sandesh@gmail.com"));
////        customerService.customerMap.put("sundaram@gmail.com", new Customer("sundaram", "mishra", "sundaram@gmail.com"));
////        customerService.customerMap.put("satyam@gmail.com", new Customer("satyam", "mishra", "satyam@gmail.com"));
//
//        customerService.addCustomer("shivam@gmail.com", "shivam", "mishra");
//        customerService.addCustomer("sandesh@gmail.com", "sandesh", "mishra");
//        customerService.addCustomer("sundaram@gmail.com", "sundaram", "mishra");
//
//
//        System.out.println(customerService.getAllCustomer());
//        System.out.println(customerService.getCustomer("shivam@gmail.com"));
//
//    }

}
