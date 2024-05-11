//Task 9

public class Person {
    private final String name;     //private- to hide sensitive data
    private final String surname;
    private final String email;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Setting getters

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }

}