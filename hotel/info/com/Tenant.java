package hotel.info.com;

import java.io.Serializable;

public class Tenant implements Serializable {
    private String name;
    private String email;
    private String phone;

    public Tenant (final Tenant other)
    {
        this.name = other.name;
        this.email = other.email;
        this.phone = other.phone;
    }

    public Tenant(String name, String email, String phone) {
        if( name == null || !name.matches("([A-Z][a-z]+\\s)+[A-Z][a-z]+") )
            throw new IllegalArgumentException("Illegal argument name");

        if( email == null || !email.matches("([a-zA-Z]+[0-9]*)*[\\.\\_]?([a-zA-Z]*[0-9]*)*@[a-z]{1,5}\\.[a-z]{1,4}"))
            throw new IllegalArgumentException("Illegal argument email");

        if(phone == null || !phone.matches("[0][8][0-9]{8}") )
            throw new IllegalArgumentException("Illegal argument phone");

        this.phone = phone;
        this.name = name;
        this.email = email;
    }

    public void setTotalPeople(short totalPeople) throws IllegalArgumentException{
        if(totalPeople < 1)
            throw new IllegalArgumentException("Illegal argument totalPeople");
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public String toString() {
        return String.format("%s,%s,%s,\n",name,email,phone);
    }

    @Override
    public boolean equals(Object o){
        if( this == o)
            return true;
        if(!(o instanceof Tenant))
            return false;

        return this.name.equals(((Tenant) o).name) && this.email.equals(((Tenant) o).email) &&
                this.phone.equals(((Tenant) o).phone);

    }
}
