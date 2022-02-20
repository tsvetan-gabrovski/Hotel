package serialization.com;

import reservation.com.Reservation;
import reservation.com.ReservationSystem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Saver implements FilesHandling {

    @Override
    public void process(ReservationSystem reservationSystem) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(FilesHandling.FILENAME);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileOutputStream);

        ArrayList<Reservation> allReservations = reservationSystem.getReservations();
        objectStream.writeObject(allReservations);

        objectStream.close();
        fileOutputStream.close();
    }
}
