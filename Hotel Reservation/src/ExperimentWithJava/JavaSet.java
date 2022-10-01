package ExperimentWithJava;

import java.util.Collection;
import java.util.HashSet;

class Customer{
    private String firstName;
    private String lastName;
    private String email;
    public Customer(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}

public class JavaSet {
    public static void main(String[] args) {
        Collection<Customer> customer = new HashSet<>();
    }
}
