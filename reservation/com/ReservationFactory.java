package reservation.com;

import hotel.info.com.Tenant;
import reservation.com.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReservationFactory {
    private static Tenant makeBookingTenant(){
        Scanner input = new Scanner(System.in);
        System.out.println("ENTER NAME FOR THE RESERVATION: ");
        String name = input.nextLine();

        System.out.println("ENTER EMAIL: ");
        String email = input.nextLine();

        System.out.println("ENTER PHONE: ");
        String phone = input.nextLine();


        return new Tenant(name,email,phone);
    }

    private static short makeBookingRoomNumber(){
        Scanner input = new Scanner(System.in);
        short n;
        try{
            n = input.nextShort();
        }catch (InputMismatchException e){
            return -1;
        }

        String str = String.format("%d",n);
        if(str.length() != 3 || str.charAt(1) != '0')
            return -1;

        return n;
    }
    private static LocalDate makeBookingDate(){
        DateTimeFormatter datesPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner input = new Scanner(System.in);
        String firstDate = input.next();

        try{
            LocalDate.parse(firstDate,datesPattern);
        }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            return null;
        }

        return LocalDate.parse(firstDate,datesPattern);
    }

    public static Reservation makeReservation() throws IllegalArgumentException{
        Tenant t = makeBookingTenant();
        if(t == null)
            throw new IllegalArgumentException("Error in tenant");

        System.out.println("ENTER DATE OF OCCUPATION: ");
        LocalDate startDate = makeBookingDate();
        if(startDate == null)
            throw new IllegalArgumentException("Error in start date");

        System.out.println("ENTER DATE OF LEAVING: ");
        LocalDate endDate = makeBookingDate();
        if(endDate == null)
            throw new IllegalArgumentException("Error in end date");


        System.out.println("ENTER ROOM NUMBER: ");
        short roomNumber = makeBookingRoomNumber();
        if(roomNumber == -1)
            throw new IllegalArgumentException("Error in room number");

        return new Reservation(t,roomNumber, startDate, endDate);
    }
}
