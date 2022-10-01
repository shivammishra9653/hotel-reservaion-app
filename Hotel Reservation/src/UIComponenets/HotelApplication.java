package UIComponenets;

public class HotelApplication {
    public static void main(String[] args) {
        try{
            MainMenu.mainMenu();
        }catch(Exception ex){
            System.out.println("Error, Somthing went wrong");
        }
    }
}
