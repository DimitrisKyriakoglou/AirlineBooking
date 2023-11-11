package Models;

import java.time.LocalDateTime;

public class Flight {

    //Flight Fields
    private int id;
    private String flightNo;
    private int capacity;
    private String from;
    private String fromCode;
    private String to;

    private String toCode;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;

    //Constructor
    public Flight(int id, String flightNo, int capacity, String from, String fromCode, String to, String toCode, LocalDateTime departure, LocalDateTime arrival, double price) {

        this.id = id;
        this.flightNo = flightNo;
        this.capacity = capacity;
        this.from = from.toLowerCase().trim();
        this.fromCode = fromCode.toUpperCase().trim();
        this.to = to.toLowerCase().trim();
        this.toCode = toCode.toUpperCase().trim();
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }


    //Methods
    public void displayFlightInfo() {
        System.out.println("Flight " + flightNo + " and capacity " + capacity + " from " + from + "(" + fromCode + ") to " + to + "(" + toCode + ") at " + departure + " and arrival time " + arrival);

    }

    public void displayFlightInfoInTicket() {
        System.out.println("Flight " + flightNo + " and capacity " + capacity + " from " + from + "(" + fromCode + ") to " + to + "(" + toCode + ") at " + departure + " and arrival time " + arrival);
    }

    public void displayFlightInfoEditTicket() {
        System.out.println("Flight " + flightNo + " and capacity " + capacity + " from " + from + "(" + fromCode + ") to " + to + "(" + toCode + ") at " + departure + " and arrival time " + arrival);
    }


    //Booked flight method, when called checks if capacity is greatter than zero and if true decreases capacity by 1 and returns true else returns false
    public Boolean bookedFlight() {
        if (capacity > 0) {
            capacity--;
            return true;
        }

        return false;


    }


    //Cancel flight method , when called increases flight capacity by 1 because a booked flight was cancelled
    public Boolean cancelFlight() {
        capacity++;
        return true;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
