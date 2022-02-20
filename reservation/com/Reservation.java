package reservation.com;

import hotel.info.com.Tenant;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private Tenant tenant;
    private short roomNumber;
    private LocalDate startDate;
    private LocalDate endDate;


    public Reservation(Tenant tenant, short roomNumber,
                       LocalDate startDate, LocalDate endDate)throws IllegalArgumentException {

        if(endDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("CAN NOT RESERVE FOR PASSED DATES");
        if( !startDate.isBefore(endDate) )
            throw new IllegalArgumentException("START DATE IS NOT BEFORE END DATE");

        if(roomNumber < 100)
            throw new IllegalArgumentException("");

        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomNumber = roomNumber;
    }

    public Reservation(final Reservation other){
        this.tenant = other.getTenant();
        this.roomNumber = other.getRoomNumber();
        this.startDate = other.getStartDate();
        this.endDate = other.getEndDate();
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setRoomNumber(short roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Tenant getTenant() { return new Tenant(tenant); }

    public short getRoomNumber() { return roomNumber; }

    public LocalDate getStartDate() { return startDate; }

    public LocalDate getEndDate() { return endDate; }

    @Override
    public boolean equals(Object o){
        if( this == o)
            return true;
        if(!(o instanceof Reservation))
            return false;

        return this.tenant.equals(((Reservation) o).tenant) && this.roomNumber == ((Reservation) o).roomNumber &&
               this.endDate.equals(((Reservation) o).endDate) && this.startDate.equals(((Reservation) o).startDate);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d",tenant,startDate.toString(),endDate.toString(),roomNumber);
    }
}
