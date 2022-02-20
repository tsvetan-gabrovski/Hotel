package hotel.info.com;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Hotel{

    private class Room {
        //DATA FIELDS OF CLASS ROOM
        private final double pricePerNight;
        private final short roomNumber;
        private final short numberBeds;

        //CONSTRUCTOR WITH PARAMETERS
        public Room(double pricePerNight, short roomNumber, short numberBeds) {
            this.pricePerNight = pricePerNight;
            this.roomNumber = roomNumber;
            this.numberBeds = numberBeds;
        }

        //PUBLIC GETTER TO RETURN PRICE_PER_NIGHT
        public double getPricePerNight() {
            return pricePerNight;
        }

        //PUBLIC GETTER TO RETURN ROOM_NUMBER
        public short getRoomNumber() {
            return roomNumber;
        }

        //PUBLIC GETTER FOR NUMBER_BEDS
        public short getNumberBeds() {
            return numberBeds;
        }

        @Override
        public String toString() {
            return String.format("Room number: %d Price: %.2f Beds Number: %d",roomNumber,pricePerNight,numberBeds);
        }
    }

    //maybe not final
    private final Room[] rooms;

    public Hotel()
    {
        rooms = new Room[90];
        short currentRoomNumber = 100;
        int index = 0;
        //HOTEL HAS 90 ROOMS SO ITERATE TO 90
        for (int i = 0; i < 9; i++) {
            rooms[index++] = new Room(30,currentRoomNumber,(short)1);

            rooms[index++] = new Room(50, (short) (currentRoomNumber+1),(short)2);
            rooms[index++] = new Room(50, (short) (currentRoomNumber+2),(short)2);
            rooms[index++] = new Room(50, (short) (currentRoomNumber+3),(short)2);


            rooms[index++] = new Room(70, (short) (currentRoomNumber+4),(short)3);
            rooms[index++] = new Room(70, (short) (currentRoomNumber+5),(short)3);
            rooms[index++] = new Room(70, (short) (currentRoomNumber+6),(short)3);

            rooms[index++] = new Room(90, (short) (currentRoomNumber+7),(short)4);
            rooms[index++] = new Room(90, (short) (currentRoomNumber+8),(short)4);

            rooms[index++] = new Room(110, (short) (currentRoomNumber+9),(short)5);
            currentRoomNumber += 100;
        }
    }

    public double getPricePerNight(int i) throws ArrayIndexOutOfBoundsException{
        if(i < 0 || i >= rooms.length)
            throw new ArrayIndexOutOfBoundsException("Invalid index i = " + i);

        return rooms[i].getPricePerNight();
    }

    public double getRoomNumber(int i) throws ArrayIndexOutOfBoundsException{
        if(i < 0 || i >= rooms.length)
            throw new ArrayIndexOutOfBoundsException("Invalid index i = " + i);

        return rooms[i].getRoomNumber();
    }

    public double getBedsNumber(int i) throws ArrayIndexOutOfBoundsException{
        if(i < 0 || i >= rooms.length)
            throw new ArrayIndexOutOfBoundsException("Invalid index i = " + i);

        return rooms[i].getNumberBeds();
    }

    public boolean hasRoom(short roomNumber){
        String roomNumberStr = String.format("%d",roomNumber);
        if(roomNumberStr.length() != 3 || roomNumberStr.charAt(1) != '0')
            return false;
        return true;
    }

    public int capacity(){
        return 9+(6*9)+(9*9)+(9*8)+(9*10);
    }

    public int numberRooms(){
        return rooms.length;
    }

    public void roomInfo(short roomNumber){
        if( !hasRoom(roomNumber) ){
            System.out.println("NO SUCH ROOM");
            return;
        }

        for (Room r : rooms){
            if(r.getRoomNumber() == roomNumber) {
                System.out.println(r);
                return;
            }
        }
    }

}
