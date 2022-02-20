package reservation.com;

import hotel.info.com.Hotel;
import serialization.com.FilesHandling;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class ReservationSystem  {
    final Hotel hotel;
    ArrayList<Reservation> reservations;

    public ReservationSystem() {
        this.hotel = new Hotel();
        reservations = new ArrayList<>();
    }

    public ArrayList<Reservation> getReservations(){
        return new ArrayList<Reservation>(reservations);
    }

    public void accept(FilesHandling filesHandling) throws IOException, ClassNotFoundException{
        filesHandling.process(this);
    }

    private boolean isOccupied(Reservation r, LocalDate date1, LocalDate date2){
        return !( (date1.isEqual(r.getEndDate()) || date1.isAfter(r.getEndDate())) ||
                ( date2.isBefore(r.getStartDate()) || date2.isEqual(r.getStartDate()) ) );
    }

    public void freeRoomsAtDates(LocalDate date1, LocalDate date2){
        if( date1 == null || date2 == null || !date1.isBefore(date2) )
            throw new IllegalArgumentException("Invalid dates for reservation");

        ArrayList<Short> busy = new ArrayList<>();
        for (Reservation r: reservations) {
            if(isOccupied(r,date1,date2))
                busy.add(r.getRoomNumber());
        }

        boolean flag = true;
        for (int i = 0; i < hotel.numberRooms(); i++) {
            flag = true;
            for (int j = 0; j < busy.size(); j++) {
                if(hotel.getRoomNumber(i) == busy.get(j)) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                System.out.println(hotel.getRoomNumber(i));
        }
    }

    public double calculatePrice(){
        Reservation r = ReservationFactory.makeReservation();

        int index = -1;
        for (int i = 0; i < hotel.numberRooms(); i++) {
            if(r.getRoomNumber() == hotel.getRoomNumber(i)) {
                index = i;
                break;
            }
        }

        if(index >= 0){
            long daysBetween = ChronoUnit.DAYS.between(r.getStartDate(), r.getEndDate());
            double price = hotel.getPricePerNight(index);
            return price * daysBetween;
        }

        return 0;
    }

    private void addReservation(Reservation r){
        for (Reservation res : reservations) {
            if(res.getRoomNumber() == r.getRoomNumber()){
                if(!( (res.getStartDate().isEqual(r.getEndDate()) || res.getStartDate().isAfter(r.getEndDate())) ||
                        ( res.getEndDate().isBefore(r.getStartDate()) || res.getEndDate().isEqual(r.getStartDate())) ))
                {
                    System.out.printf("\nRoom: %d is unavailable\n", r.getRoomNumber());
                    return;
                }
            }
        }

        reservations.add(r);
    }

    public void makeReservation(){
        Reservation r = ReservationFactory.makeReservation();
        addReservation(r);
    }

    public void makeReservation(Reservation r){
        addReservation(r);
    }

    public void cancelReservation(){
        if(reservations.size() == 0){
            System.out.println("NO RESERVATIONS");
            return;
        }

        Reservation r = ReservationFactory.makeReservation();
        int i = 0;
        for(Reservation res : reservations) {
            if(r.equals(res))
                break;
            i++;
        }
        if(i < reservations.size())
            reservations.remove(i);
    }

    public void listReservations(){
        for(Reservation r : reservations){
            System.out.println(r.toString());
        }
    }
}
