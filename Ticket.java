//Task 10

public class Ticket {
    private final int row_num;
    private final int seat_num;
    private final double price;
    private final Person person;

    public Ticket(int row_num, int seat_num, double price, Person person) {
        this.row_num = row_num;
        this.seat_num = seat_num;
        this.price = price;
        this.person = person;
    }

    //Setting getters
    public int getRow_num() {
        return row_num;
    }
    public int getSeat_num() {
        return seat_num;
    }
    public double getPrice() {
        return price;
    }


    //Task 11
    public void print() {
        System.out.println("Person name: " + person.getName());
        System.out.println("Person surname: " + person.getSurname());
        System.out.println("Person email: " + person.getEmail());
        System.out.println("Row: " + row_num);
        System.out.println("Seat: " + seat_num);
        System.out.println("Price: " + price);
    }
}
