package session06.training.entities;

import session06.training.utils.Validator;

import java.util.Objects;

public class Course {
    private String code;
    private String name;
    private boolean status;
    private short duration;
    private String flag;

    public Course() {};
    public Course(String code, String name, boolean status, short duration, String flag) {
        setCode(code);
        setName(name);
        setStatus(false);
        setDuration(duration);
        setFlag(flag);
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public boolean getStatus() { return status; }
    public  short getDuration() { return duration; }
    public String getFlag() { return flag; }

    public void setCode(String code)  {
        if(Validator.validateCode(code)) {
            this.code = code;
            return;
        }
        System.out.println("Invalid code");
    }
    public void setName(String name) { this.name = name; }
    public void setStatus(boolean status) {
        if(Validator.validateStatus(status)) {
            this.status = status;
            return;
        }
        System.out.println("Invalid status");
    }
    public void setDuration(short duration)  {
        if(Validator.validateDuration(duration)) {
            this.duration = duration;
            return;
        }
        System.out.println("Invalid duration");
    }
    public void setFlag(String flag) {
        if(Validator.validateFlag(flag)) {
            this.flag = flag;
            return;
        }
        System.out.println("Invalid flag");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;
        return Objects.equals(this.code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return String.format("Code: %-6s | Name: %-10s | Status: %-1b | Duration: %-10d | flag: %-10s",
                code, name,  status, duration, flag);
    }
}
