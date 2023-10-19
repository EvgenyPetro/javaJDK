public class Employee {

    private int personelNumber;
    private int phoneNumber;
    private String name;
    private int experience;

    public Employee(int personelNumber, int phoneNumber, String name, int experience) {
        this.personelNumber = personelNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getPersonelNumber() {
        return personelNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

}
