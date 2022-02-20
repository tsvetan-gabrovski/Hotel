package serialization.com;

import reservation.com.Reservation;
import reservation.com.ReservationSystem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class Loader implements FilesHandling {
    @Override
    public void process(ReservationSystem reservationSystem) throws IOException, ClassNotFoundException {
        if( !new File(FilesHandling.FILENAME).exists() ) {
            return;
        }

        FileInputStream fileInputStream = new FileInputStream(FilesHandling.FILENAME);
        ObjectInputStream objectStream = new ObjectInputStream(fileInputStream);

        ArrayList<Reservation> reservations = null;

        try{
            reservations = (ArrayList<Reservation>) objectStream.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        if(reservations == null)
            return;


        for(Reservation res : reservations){
            if( !res.getEndDate().isBefore(LocalDate.now()) )
                reservationSystem.makeReservation(res);
        }
    }
}
