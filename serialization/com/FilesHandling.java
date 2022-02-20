package serialization.com;

import reservation.com.ReservationSystem;

import java.io.IOException;

public interface FilesHandling {
    static final String FILENAME = "RESERVATIONS.TXT";
    void process(ReservationSystem reservationSystem) throws IOException, ClassNotFoundException;
}
