package session02;

public class Trainee {
    private String id;
    private String name;
    private String gender;
    private byte age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id.isEmpty()) {
            System.out.println("ID cannot be empty");
            return;
        } else {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        } else {
            this.name = name;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.equals("Male") || gender.equals("Female")) {
            this.gender = gender;
        } else {
            System.out.println("Gender must be Male or Female");
            return;
        }
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        if(age < 6) {
            System.out.println("Age must be greater or equal to 6");
            return;
        } else {
            this.age = age;
        }
    }
}