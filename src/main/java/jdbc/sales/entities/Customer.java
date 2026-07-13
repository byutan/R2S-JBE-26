package jdbc.sales.entities;

public class Customer {
    private int id;
    private String name;
    private String contact;
    private String address;
    private String city;
    private String postalCode;
    private String country;

    public Customer() {
    }

    public Customer(String name, String contact, String address, String city, String postalCode, String country) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer { id: %-4d | name: %-20s | contact: %-15s | address: %-25s | city: %-15s | zip: %-8s | country: %-10s }",
                id, name, contact, address, city, postalCode, country
        );
    }
}
