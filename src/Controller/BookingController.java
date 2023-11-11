package Controller;

import Models.Flight;
import Models.Passenger;
import Models.Ticket;
import Tools.Helper;
import Tools.Validation;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookingController {

    //Initiate an arraylist of flights
    ArrayList<Flight> flights = new ArrayList<Flight>();
    //Initiate an arraylist of tickets
    ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private int maxFlightId;
    private int maxTicketId;


    public BookingController() {

    }

    //Add a flight method  - ADD A FLIGHT TO THE LIST
    public void addFlight() {

        System.out.println("Please insert flight code ");
        Helper.scan.nextLine();
        String flightNo = Helper.scan.nextLine().toUpperCase().trim();

        System.out.println("Please insert capacity");

        int capacity = Helper.scan.nextInt();

        System.out.println("Please insert Departure City");
        Helper.scan.nextLine();
        String from = Helper.scan.nextLine();

        System.out.println("Please insert Departure City Code");
        String fromCode = Helper.scan.nextLine().toUpperCase().trim();

        System.out.println("Please insert Arrival City");
        String to = Helper.scan.nextLine();

        System.out.println("Please insert ArrivalCity Code");
        String toCode = Helper.scan.nextLine();

        System.out.println("Please insert Departure Date and Time");
        String departureDate = Helper.scan.nextLine();
        LocalDateTime departure = Helper.StringToDateTimeFormatter(Validation.validateDateTime(departureDate));

        System.out.println("Please insert Arrival Date and Time");
        String arrivalDate = Helper.scan.nextLine();
        LocalDateTime arrival = Helper.StringToDateTimeFormatter(Validation.validateDateTime(arrivalDate));

        System.out.println("Please insert the price of the ticket");
        double ticketPrice = Helper.scan.nextDouble();


        int nextId = maxFlightId + 1;

        flights.add(new Flight(nextId, flightNo, capacity, from, fromCode, to, toCode, departure, arrival, ticketPrice));

        maxFlightId = nextId;

    }

    public void displayFlightsOverHundred() {
        ArrayList<Flight> overHundred = new ArrayList<>();
        overHundred = (ArrayList<Flight>) flights.stream().filter(flight -> flight.getPrice() >= 100).collect(Collectors.toList());
        overHundred.forEach(flight -> flight.displayFlightInfo());

    }

    public void displayFlightsUnderHundred() {
        ArrayList<Flight> underHundred = new ArrayList<>();
        underHundred = (ArrayList<Flight>) flights.stream().filter(flight -> flight.getPrice() <= 100).collect(Collectors.toList());
        underHundred.forEach(flight -> flight.displayFlightInfo());
    }

    //Loop through flights and display their info  - DISPLAY FLIGHTS LIST
    public void displayFlightDetails() {
        //BECAUSE INDEX OF LISTS STARTS FROM 0 WE GIVE A VARIABLE = 1 FOR THE FIRST ELEMENT
        int index = 1;

        for (Flight f : flights
        ) {

            System.out.print("Flight " + index + " : ");
            f.displayFlightInfo();
            index++;

        }
    }

    // Add a passenger to a flight   - ADD A PASSENGER - THIS OBJECT DOES NOT HAVE A LIST BUT IS INVOKED TO CRETE THIS OBJECT AT RUNTIME
    @org.jetbrains.annotations.NotNull
    private Passenger addPassenger() {
        System.out.println("Please insert passenger's passport number ");
        Helper.scan.nextLine();
        String passportNo = Helper.scan.nextLine();

        System.out.println("Please insert passenger's full name ");
        String fullName = Helper.scan.nextLine();

        System.out.println("Please insert passenger's age ");
        int age = Helper.scan.nextInt();

        System.out.println("Please insert passenger's nationallity ");
        Helper.scan.nextLine();
        String nationallity = Helper.scan.nextLine();

        Passenger passenger1 = new Passenger(passportNo, fullName, age, nationallity);
        passenger1.showPassengerInfo();

        return passenger1;


    }

    // Booked ticket check if there are available seats if yes add passengers info to the ticket if no display no seats
    public void bookedTicket() {
        //Display all Flight details
        displayFlightDetails();

        //Enter flight unique id(index)
        System.out.println("Please insert index of flight ");
        int index = Helper.scan.nextInt();

        //if booked flight returns false (no capacity), print no seats
        if (!flights.get(index - 1).bookedFlight()) {
            System.out.println("no seats");

        }
        //Print passenger Details added to the ticket
        System.out.println("Passenger details:");
        var passenger = addPassenger();

        int nextId = maxTicketId + 1;

        tickets.add(new Ticket(nextId, passenger, flights.get(index - 1)));

        maxTicketId = nextId;


    }

    //Display all tickets
    public void displayTickets() {
        int index = 1;

        for (Ticket t : tickets
        ) {

            System.out.println("Ticket " + index + " : ");
            t.displayTicket();
            index++;

        }
    }

    //Get flight from unique id(index in array)
    public int getFlightIndexbyID(int input) {
        int index = -1;

        for (Flight f : flights
        ) {
            var id = f.getId();
            if (id == input) {
                index = flights.indexOf(f);


            }
        }
        return index;

    }

    // Cancel a ticket
    public void canceledTicket() {
        //Display tickets
        displayTickets();

        //Give tht index of the ticket you want to delete
        System.out.println("Please insert index of ticket to cancel ");
        int index = Helper.scan.nextInt();

        //Match the flight by index
        var delete = getFlightIndexbyID(tickets.get(index - 1).getId());//??????

        //Remove from Array List ticket
        var deleted = tickets.remove(index - 1);

        //Display deleted Ticket
        deleted.displayTicket();

        //Calls cancel booked flight to increase the flight's  capacity by 1
        flights.get(delete).cancelFlight();


    }

    //Delete Flight
    public void deleteFlight() {
        //Display all flights
        displayFlightDetails();

        //Give tht index of the flight you want to delete
        System.out.println("Please insert index of flight to cancel ");
        int index = Helper.scan.nextInt();

        //Match the flight by index
        var delete = flights.get(index - 1);

        //Remove from Array List flight
        var deleted = flights.remove(index - 1);

        //Display deleted flight
        deleted.displayFlightInfo();


    }

    public void searchFlight() throws ParseException {

        //Give the info of the flight you want to search
        System.out.println("Please insert your departure City or departure city code ");
        Helper.scan.nextLine();
        String from = Helper.scan.nextLine();

        System.out.println("Please insert your arrival City ");
        Helper.scan.nextLine();
        String to = Helper.scan.nextLine();

        System.out.println("Please insert Departure Date ");
        String departureDate = Helper.scan.nextLine();
        LocalDate departure = Helper.StringToDateFormatter(Validation.validateDate(departureDate));

        boolean flagError = true;

        for (Flight f : flights) {
            if ((f.getFrom().equals(from.toLowerCase().trim()) &&
                    f.getTo().equals(to.toLowerCase().trim()) &&
                    f.getDeparture().toLocalDate().equals(departure)) ||

                    (f.getFromCode().equals(from.toUpperCase().trim()) &&
                            f.getToCode().equals(to.toUpperCase().trim()) &&
                            f.getDeparture().toLocalDate().equals(departure)) ||

                    (f.getFrom().equals(from.toLowerCase().trim()) &&
                            f.getToCode().equals(to.toUpperCase().trim()) &&
                            f.getDeparture().toLocalDate().equals(departure)) ||

                    (f.getFromCode().equals(from.toUpperCase().trim()) &&
                            f.getTo().equals(to.toLowerCase().trim()) &&
                            f.getDeparture().toLocalDate().equals(departure))) {
                f.displayFlightInfo();

                flagError = false;
            }

        }

        if (flagError) {

            System.out.println("Flight not found");
        }


    }

    public void editFlight() {
        //Display flights
        displayFlightDetails();

        //Give tht index of the flight you want to delete
        System.out.println("Please insert index of flight to edit ");
        int index = Helper.scan.nextInt();


        //Index starts from 0
        index = index - 1;

        //Match the flight by index
        var edit = flights.get(index);

        System.out.println("Press Enter to exit or write Edit to edit flight number ");
        System.out.println(flights.get(index).getFlightNo());

        Helper.scan.nextLine();
        String editFlightNo = Helper.scan.nextLine();

        editFlightNo = editFlightNo.toLowerCase().trim();

        if (editFlightNo.equals("edit")) {

            System.out.println("Please enter a new flight code ");
            String newFlightNo = Helper.scan.nextLine();
            flights.get(index).setFlightNo(newFlightNo);
            System.out.println("Flight number changed");


        }

        //Change Departure City
        System.out.println("Press Enter to exit or write Edit to edit departure City ");
        System.out.println(flights.get(index).getFrom());

        Helper.scan.nextLine();
        String editFrom = Helper.scan.nextLine();

        editFrom = editFrom.toLowerCase().trim();

        if (editFrom.equals("edit")) {

            System.out.println("Please enter a new departure city  ");
            String newFrom = Helper.scan.nextLine();
            flights.get(index).setFrom(newFrom);
            System.out.println("Departure City changed");


        }
        //Change Departure City Code
        System.out.println("Press Enter to exit or write Edit to edit departure City Code");
        System.out.println(flights.get(index).getFromCode());

        Helper.scan.nextLine();
        String editFromCode = Helper.scan.nextLine();

        editFromCode = editFromCode.toLowerCase().trim();

        if (editFromCode.equals("edit")) {

            System.out.println("Please enter a new departure city  ");
            String newFromCode = Helper.scan.nextLine();
            flights.get(index).setFromCode(newFromCode);
            System.out.println("Departure City changed");


        }
        //Change Arrival City
        System.out.println("Press Enter to exit or write Edit to edit arrival City ");
        System.out.println(flights.get(index).getTo());

        Helper.scan.nextLine();
        String ediTo = Helper.scan.nextLine();

        ediTo = ediTo.toLowerCase().trim();

        if (ediTo.equals("edit")) {

            System.out.println("Please enter a new arrival city  ");
            String newTo = Helper.scan.nextLine();
            flights.get(index).setTo(newTo);
            System.out.println("Arrival City changed");


        }
        //Change Arrival City Code
        System.out.println("Press Enter to exit or write Edit to edit arrival City Code");
        System.out.println(flights.get(index).getToCode());

        Helper.scan.nextLine();
        String ediToCode = Helper.scan.nextLine();

        ediToCode = ediToCode.toLowerCase().trim();

        if (ediToCode.equals("edit")) {

            System.out.println("Please enter a new arrival city  ");
            String newToCode = Helper.scan.nextLine();
            flights.get(index).setToCode(newToCode);
            System.out.println("Arrival City changed");


        }


        //Change Departure Date
        System.out.println("Press Enter to exit or write Edit to edit Departure date");
        System.out.println(flights.get(index).getDeparture());

        Helper.scan.nextLine();
        String editDepartureDate = Helper.scan.nextLine();

        editDepartureDate = editDepartureDate.toLowerCase().trim();

        if (editDepartureDate.equals("edit")) {

            System.out.println("Please enter a new departure Date  ");
            String newDeparture = Helper.scan.nextLine();
            LocalDateTime newDepartureDate = Helper.StringToDateTimeFormatter(Validation.validateDateTime(newDeparture));
            flights.get(index).setDeparture(newDepartureDate);
            System.out.println("Departure date changed");


        }

        //Change Arrival Date
        System.out.println("Press Enter to exit or write Edit to edit Arrival Date");
        System.out.println(flights.get(index).getArrival());

        Helper.scan.nextLine();
        String editArrivalDate = Helper.scan.nextLine();

        editArrivalDate = editArrivalDate.toLowerCase().trim();

        if (editArrivalDate.equals("edit")) {

            System.out.println("Please enter a new Arrival Date  ");
            String newArrival = Helper.scan.nextLine();
            LocalDateTime newArrivalDate = Helper.StringToDateTimeFormatter(Validation.validateDateTime(newArrival));
            flights.get(index).setArrival(newArrivalDate);
            System.out.println("Arrival Date changed");


        }
        /* System.out.println("Please insert the price of the ticket");
        double ticketPrice = Helper.scan.nextDouble();*/

        //Change Flight Price
        System.out.println("Press Enter to exit or write Edit to edit Flight Price ");
        System.out.println(flights.get(index).getPrice());

        Helper.scan.nextLine();
        String editPrice = Helper.scan.nextLine();

        editPrice = editPrice.toLowerCase().trim();

        if (editPrice.equals("edit")) {

            System.out.println("Please enter a new Flight Price  ");
            double newPrice = Helper.scan.nextDouble();
            flights.get(index).setPrice(newPrice);
            System.out.println("Flight Price changed");


        }


    }

    public void editTicket() {


        displayTickets();
        System.out.println("Enter the number of the ticket");
        int index = Helper.scan.nextInt();
        index = index - 1;
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Write (edit) if You want edit or click Enter button if  don't need to edit");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("-------------------------------------");
        System.out.println("   Passenger details: ");
        System.out.println("-------------------------------------");

        System.out.println("Full name: " + tickets.get(index).passenger.getFullName());
        Helper.scan.nextLine();
        String nameCheck = Helper.scan.nextLine();
        nameCheck = nameCheck.toLowerCase().trim();
        if (nameCheck.equals("edit")) {
            System.out.println("Please Enter the new name");
            String name = Helper.scan.nextLine();
            tickets.get(index).passenger.setFullName(name);
            System.out.println("Done...");
        }

        System.out.println("Passport No: " + tickets.get(index).passenger.getPassportNo());
        String passportNoCheck = Helper.scan.nextLine();
        passportNoCheck = passportNoCheck.toLowerCase().trim();
        if (passportNoCheck.equals("edit")) {
            System.out.println("Please Enter the new Passport number");
            String passportNo = Helper.scan.nextLine();
            tickets.get(index).passenger.setPassportNo(passportNo);
            System.out.println("Done...");
        }

        System.out.println("Nationality: " + tickets.get(index).passenger.getNationality());
        String nationalityCheck = Helper.scan.nextLine();
        nationalityCheck = nationalityCheck.toLowerCase().trim();
        if (nationalityCheck.equals("edit")) {
            System.out.println("Please Enter the new nationality");
            String nationality = Helper.scan.nextLine();
            tickets.get(index).passenger.setNationality(nationality);
            System.out.println("Done...");
        }

        System.out.println("Age: " + tickets.get(index).passenger.getAge());
        String ageCheck = Helper.scan.nextLine();
        ageCheck = ageCheck.toLowerCase().trim();
        if (ageCheck.equals("edit")) {
            System.out.println("Please Enter the new age");
            int age = Helper.scan.nextInt();
            tickets.get(index).passenger.setAge(age);
            System.out.println("Done...");
        }

        System.out.println("-------------------------------------");
        System.out.println("   Flight details: ");
        System.out.println("-------------------------------------");

        tickets.get(index).flight.displayFlightInfoInTicket();
        Helper.scan.nextLine();
        String flightCheck = Helper.scan.nextLine();
        flightCheck = flightCheck.toLowerCase().trim();
        if (flightCheck.equals("edit")) {
            displayFlightDetails();
            System.out.println("Please Enter the new flight number");
            int flightNo = Helper.scan.nextInt();
            tickets.get(index).setFlight(flights.get(flightNo - 1));
            System.out.println("Done...");
        }

    }

    public void printTicket() {

        displayTickets();
        System.out.println("Enter the number of the ticket");
        int index = Helper.scan.nextInt();
        index = index - 1;

        var print = getFlightIndexbyID(tickets.get(index).flight.getId());

        tickets.get(print).printTicketPdf();


    }

    public Object displayTicketByIndex() {
        displayTickets();
        System.out.println("Enter the number of the ticket");
        int index = Helper.scan.nextInt();
        index = index - 1;

        return tickets.get(index);
    }

    public int getMaxFlightId() {
        return maxFlightId;
    }

    public void setMaxFlightId(int maxFlightId) {
        this.maxFlightId = maxFlightId;
    }

    public int getMaxTicketId() {
        return maxTicketId;
    }

    public void setMaxTicketId(int maxTicketId) {
        this.maxTicketId = maxTicketId;
    }
}












