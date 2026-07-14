package jdbc.sales.entities;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String birthdate;
    private int supervisor;

    public Employee() {
    }

    public Employee(int supervisor, String birthdate, String firstName, String lastName) {
        this.supervisor = supervisor;
        this.birthdate = birthdate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%-3d, lastName='%-15s', firstName='%-15s', birthdate='%s', supervisor=%d}",
                id, lastName, firstName, birthdate, supervisor);
    }
}
