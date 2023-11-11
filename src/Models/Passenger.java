package Models;

public class Passenger {


    //Passenger fields
    private int id;
    private String passportNo;
    private String fullName;
    private int age;
    private String nationality;

    //Constructor
    // public Passenger(){};
    public Passenger(String passportNo, String fullName, int age, String nationality) {

        this.passportNo = passportNo;
        this.fullName = fullName;
        this.age = age;
        this.nationality = nationality;
    }

    //Methods


    //When called Prints out passenger info
    public void showPassengerInfo() {
        System.out.println("Name " + fullName + " passport number " + passportNo + " age " + age + " nationality " + nationality);
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
