import java.util.*;
import java.io.*;

public class Theatre {

    private static ArrayList<Ticket> ticketList = new ArrayList<>();  //List that can hold objects of type Ticket.

    //Task 1
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");

        int[][] seats = new int[3][]; // create 3 arrays for each row
        seats[0] = new int[12]; // row 1 has 12 seats
        seats[1] = new int[16]; // row 2 has 16 seats
        seats[2] = new int[20]; // row 3 has 20 seats

        // converting free seats to 0
        for (int i = 0; i < seats.length; i++) {
            Arrays.fill(seats[i], 0);                          //https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
        }
        Scanner scanner = new Scanner(System.in);

        //Task 2 (Menu)
        while (true) {
            System.out.println("---------------------------------------------------");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("---------------------------------------------------");
            System.out.println("Please select an option:");

            int selection;

            // menu selections
            if(scanner.hasNextInt()){                 //Validation (to receive only numbers)
                selection= scanner.nextInt();
            switch (selection) {
                case 1:
                    buy_ticket(scanner, seats);
                    break;
                case 2:
                    print_seating_area(seats);
                    break;
                case 3:
                    cancel_ticket(seats,scanner);
                    break;
                case 4:
                    show_available(seats);
                    break;
                case 5:
                    save(seats);
                    break;
                case 6:
                    load(scanner,seats);
                    break;
                case 7:
                    show_tickets_info();
                    break;
                case 8:
                    sort_tickets();
                    break;
                case 0:
                    System.out.println("Thank You, Have a good day !!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection,please try again.");
                    break;
            }
            }else {
                System.out.println("Please enter a number. ");
                scanner.next();
                continue;
            }
        }
    }

    //Task 3 & Task 12(Extended)
    public static void buy_ticket(Scanner scanner, int[][] seats) {
        System.out.println("Enter person name:");
        String name = scanner.next();
        if (!name.matches("^[a-zA-Z]+$")) {                   //https://stackoverflow.com/questions/28750927/string-validation-help-in-java
            System.out.println("Please enter only letters.");       //Validation to only receive letters
            return;
        }

        System.out.println("Enter person surname:");
        String surname = scanner.next();
        if (!surname.matches("^[a-zA-Z]+$")) {                //https://stackoverflow.com/questions/28750927/string-validation-help-in-java
            System.out.println("Please enter only letters.");
            return;
        }

        System.out.println("Enter person email:");
        String email = scanner.next();

        System.out.println("Please enter the row number: ");
        int row_num;
        if (scanner.hasNextInt()) {                           //validation (to receive integers only)
            row_num = scanner.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }

        if ((row_num < 1) || (row_num > 3)) {
            System.out.println("Invalid row number,please try again.");
            return;
        }

        System.out.println("Please enter the seat number: ");
        int seat_num;
        if (scanner.hasNextInt()) {                           //validation (to receive integers only)
            seat_num = scanner.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }

        if ((seat_num < 1) || (seat_num > seats[row_num - 1].length)) {
            System.out.println("Invalid seat number,please try again.");
            return;
        }

        if (seats[row_num - 1][seat_num - 1] == 1) {
            System.out.println("This seat is occupied,please select another seat,Thank you.");
            return;
        }

        System.out.println("Row 1 seats price: £10\nRow 2 seats price: £20\nRow 3 seats price: £30");
        System.out.println("Enter ticket price:");
        double price = scanner.nextDouble();

        // validation for ticket prices
        double requiredPrice;
        if (row_num == 1) requiredPrice = 10;
        else if (row_num == 2) requiredPrice = 20;
        else {
            requiredPrice = 30;}

        if (price != requiredPrice) {
            System.out.println("Invalid price, please try again.");
            return;
        }

        Person person = new Person(name, surname, email);                      //creating a new 'Person' object
        Ticket ticket = new Ticket(row_num, seat_num, price, person);          //Using person object to create a new Ticket object
        ticketList.add(ticket);
        System.out.println("seat " + seat_num + " in row " + row_num + " is occupied for you");
        seats[row_num - 1][seat_num - 1] = 1;
    }

    //Task 4
    public static void print_seating_area(int[][] seats) {
        System.out.println("      ***********");
        System.out.println("       * STAGE *");
        System.out.println("      ***********");

        int len = 5;
        String spaces = " ";
        for (int r=0;r<3;r++){                                                 // Adding a padding for alignment
            if (r==0) System.out.print(spaces.repeat(len));
            else if (r==1) System.out.print(spaces.repeat((len)-2));
            else System.out.print(spaces.repeat((len)-4));

            for (int s=0;s<seats[r].length;s++){
                if (s==(seats[r].length/2)) System.out.print(" ");
                if (seats[r][s]==0){
                    System.out.print("0");
                }
                else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    //Task 5 & Task 12(Extended)
    public static void cancel_ticket(int[][] seats,Scanner scanner) {
        System.out.println("Please enter the row number: ");
        int row_num;
        if (scanner.hasNextInt()) {                           //validation (to receive integers only)
            row_num = scanner.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }
        if ((row_num < 1) || (row_num > 3)) {
            System.out.println("Invalid row number,please try again.");
            return;
        }

        System.out.println("Please enter the seat number: ");
        int seat_num;
        if (scanner.hasNextInt()) {                           //validation (to receive integers only)
            seat_num = scanner.nextInt();
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }
        if ((seat_num < 1) || (seat_num > seats[row_num - 1].length)) {
            System.out.println("Invalid seat number,please try again.");
            return;
        }

        if (seats[row_num - 1][seat_num - 1] == 1) {
            seats[row_num - 1][seat_num - 1] = 0;                     //Converting booked seat to free
            System.out.println("Ticket cancelled successfully.");
            for (int i = 0; i < ticketList.size(); i++) {
                Ticket ticket = ticketList.get(i);
                if (ticket.getRow_num() == row_num && ticket.getSeat_num() == seat_num) {
                    ticketList.remove(i);                             // Removing booked seat from List
                    break;
                }
            }
        }else{                                       //Validation
            System.out.println("Seat was not booked yet");
        }
    }

    //Task 6
    public static void show_available(int[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Seats available in row " + (i + 1) + ": ");
            List<Integer> available_Seats = new ArrayList<>();      //Creating a new list to store available seats
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    available_Seats.add(j + 1);
                }
            }
            System.out.println(available_Seats);
        }
    }

    //Task 7
    public static void save(int[][] seats) {
        try {
            FileWriter writer = new FileWriter("seats_info.txt");
            int i = 0;
            while (i < seats.length) {
                for (int j = 0; j < seats[i].length; j++) {
                    writer.write( String.valueOf(seats[i][j]));        //converting to a string & writing to the file
                    if (j < seats[i].length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
                i++;
            }
            writer.close();
            System.out.println("Seats data saved to file: seats_info.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the seats data: " + e.getMessage());    //Used to retrieve the error message
                                                                                                       //https://www.geeksforgeeks.org/throwable-getmessage-method-in-java-with-examples/
        }
    }

    //Task 8
    public static void load(Scanner scanner, int[][] seats) {
        try {
            File file = new File("seats_info.txt");
            scanner = new Scanner(file);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String reader = sc.nextLine();                   //Retrieving saved file's data & printing
                System.out.println(reader);
            }

            for (int i = 0; i < seats.length; i++) {             //Restoring arrays using saved file's data
                String[] values = scanner.nextLine().split(",");
                for (int j = 0; j < values.length; j++) {
                    seats[i][j] = Integer.parseInt(values[j]);
                }
            }

            scanner.close();
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. No data loaded.");
        }
    }

    //Task 13
    public static void show_tickets_info() {
        double total_price = 0.0;
        for (int i = 0; i < ticketList.size(); i++) {
            System.out.println("Ticket " + (i+1) + " info:");
            ticketList.get(i).print();
            System.out.println("-------------");
            total_price += ticketList.get(i).getPrice();
        }
        System.out.print("Total price of all tickets: £" + total_price);
        System.out.println();
    }

    //Task 14
    public static void sort_tickets() {
        //Sorting data using Selection Sort

        int index_no = 0 ;
        ArrayList <Ticket> sortedList = new ArrayList<>();
        ListIterator<Ticket> iterator = ticketList.listIterator();
        while (iterator.hasNext()) {                                  //https://www.geeksforgeeks.org/selection-sort/
            Ticket ticket = iterator.next();
            int length = sortedList.size();
            if (length == 0){
                sortedList.add(ticket);
            }
            else {
                for (int i = 0;i <length;i++){

                    if (sortedList.get(i).getPrice() < ticket.getPrice()){
                        index_no = i+1;
                    }
                }
                sortedList.add(index_no,ticket);
            }
        }
        //Printing sorted data
        if (sortedList.size()>0){
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println("Ticket " + (i+1) + " info:");
            sortedList.get(i).print();
            System.out.println("-------------");
        }}
        else {
            System.out.println("No tickets available to sort");
        }
    }
}


