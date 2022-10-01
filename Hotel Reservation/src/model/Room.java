package model;

import java.util.Objects;

public class Room implements IRoom{
    private String roomNumber;
    private  Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }
    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber){this.roomNumber = roomNumber;}

    public Double getRoomPrice() {
        return this.price;
    }
    public void setRoomPrice(Double price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return this.enumeration;
    }

    public boolean isFree() {
        return this.price == 0.0;
    }


   @Override
   public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        Room room = (Room) obj;

        return (room.roomNumber.equals(this.roomNumber) && room.price.equals(this.price) && room.enumeration.equals(this.enumeration));
   }

   @Override
   public int hashCode(){
        return Objects.hash(roomNumber, price, enumeration);
   }

    @Override
    public  String toString(){
        return "roomNumber = " + roomNumber + "\n" +
                "price = " + price + "\n" +
                "roomType = " + enumeration + "\n";
    }

}
