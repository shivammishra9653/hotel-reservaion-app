package ExperimentWithJava;
import java.util.regex.Pattern;

public class RegEx {
    private String email;
    private String emailRegEx = "^(.+)@(.+).com$";
    private Pattern pattern = Pattern.compile(emailRegEx);
    public RegEx(String email){
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, enter a valid email");
        }

        this.email = email;

    }

    public String getEmail(){
        return this.email;
    }

    public static void main(String[] args) {
        RegEx regex = new RegEx("mishra483gmail.com");
        System.out.println(regex.getEmail());
    }
}
