package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String emailRegEx = "^(.+)@(.+).com$";
    private Pattern pattern = Pattern.compile(emailRegEx);
  public Customer(String firstName, String lastName, String email){
      if(!pattern.matcher(email).matches()){
          throw new IllegalArgumentException("Error, enter a valid email");
      }
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
  }

  public String getFirstName(){
      return this.firstName;
  }
  public void setFirstName(String firstName){this.firstName = firstName;}

  public String getLastName(){
      return this.lastName;
  }
  public void setLastName(String firstName){this.lastName = lastName;}

  public String getEmail(){
      return this.email;
  }
  public void setEmail(String email){
      this.email = email;
  }
  @Override
    public String toString(){
      return "firstName = " + this.firstName + "\n" +
              "lastName = " + this.lastName + "\n" +
              "Email = " + this.email + "\n";
  }

  @Override
    public boolean equals(Object obj){
      if(this == obj){
          return true;
      }

      if(obj == null || obj.getClass() != this.getClass()){
          return false;
      }

      Customer customer = (Customer) obj;

      return (customer.firstName.equals(this.firstName) && customer.lastName.equals(this.lastName) && customer.email.equals(this.email));
  }


  @Override
    public int hashCode(){
      return Objects.hash(firstName, lastName, email);
  }
}
