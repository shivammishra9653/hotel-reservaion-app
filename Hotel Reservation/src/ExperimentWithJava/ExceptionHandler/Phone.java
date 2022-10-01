package ExperimentWithJava.ExceptionHandler;

public class Phone {
    private String phoneType;
    private String phoneNumber;

    public Phone(String phoneType, String phoneNumber){

        if(phoneType == null || phoneNumber == null){
            throw new IllegalArgumentException("The type and number cannot be null");

        }
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneType(String phoneType){
        this.phoneType = phoneType;
    }

    public String getPhoneType(){
        return this.phoneType;
    }


    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    @Override
    public  String toString(){
        return "phoneType = " + phoneType + " phoneNumber = " + phoneNumber;
    }
}
