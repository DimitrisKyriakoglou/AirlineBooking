package Models;

import Tools.Helper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Ticket {


    public Passenger passenger;
    public Flight flight;
    //Ticket fields
    private int id;

    //Constructor
    public Ticket(int id, Passenger passenger, Flight flight) {

        this.id = id;
        this.passenger = passenger;
        this.flight = flight;
    }


    //Methods

    //When called displays ticket info. method also use passengers method of showing passenger info and flights method of showing flight info
    public void displayTicket() {
        System.out.println("Passenger details:");
        passenger.showPassengerInfo();
        System.out.println("Flight details:");
        flight.displayFlightInfo();

    }

    public void printTicketPdf() {
        try {
            LocalDateTime date = LocalDateTime.now();
            String line = "-----------------------------------------------------";
            PDDocument document = new PDDocument();
            PDPage blankPage = new PDPage();
            document.addPage(blankPage);
            PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\dimit\\IdeaProjects\\Airline Booking\\src\\Logo\\images.jpg", document);
            PDPage page = document.getPage(0);
            PDPageContentStream contents = new PDPageContentStream(document, page);
            contents.drawImage(pdImage, 220, 660);
            PDDocumentInformation pdd = document.getDocumentInformation();
            pdd.setAuthor("Travels");
            Calendar pdfDate = Calendar.getInstance();
            pdd.setTitle("Ticket " + passenger.getFullName());
            pdd.setCreationDate(pdfDate);
            pdd.setCreator("Travels company");
            contents.beginText();
            contents.setFont(PDType1Font.COURIER_BOLD, 18);
            contents.newLineAtOffset(20, 660);
            contents.setLeading(23.5f);
            contents.newLine();
            contents.newLine();
            contents.showText("Date: " + Helper.dateToStringFormatter(date) + "         Ticket ID :" + this.id);
            contents.newLine();
            contents.showText(line);
            contents.newLine();
            contents.showText("Name: " + " " + passenger.getFullName());
            contents.newLine();
            contents.showText("Password Number: " + " " + passenger.getPassportNo());
            contents.newLine();
            contents.showText("Age: " + passenger.getAge());
            contents.newLine();
            contents.showText("Nationality :" + " " + passenger.getNationality());
            contents.newLine();
            contents.showText("Flight Number :" + " " + flight.getFlightNo());
            contents.newLine();
            contents.showText("From :" + " " + this.flight.getFrom() + " " + this.flight.getFromCode());
            contents.newLine();
            contents.showText("To :" + " " + this.flight.getTo() + " " + this.flight.getToCode());
            contents.newLine();
            contents.showText("Price :" + " " + this.flight.getPrice());
            contents.newLine();
            contents.showText("Time of the arrival :" + " " + Helper.dateToStringFormatter(this.flight.getArrival()));
            contents.newLine();
            contents.showText("Time of the departure :" + " " + Helper.dateToStringFormatter(this.flight.getDeparture()));
            contents.newLine();
            contents.showText(line);
            contents.newLine();
            contents.setFont(PDType1Font.COURIER_BOLD, 22);
            contents.showText("Important information :");
            contents.setFont(PDType1Font.COURIER_BOLD, 18);
            contents.newLine();
            contents.showText("Baggage:");
            contents.setFont(PDType1Font.COURIER_BOLD, 16);
            contents.newLine();
            contents.showText("- Cabin Baggage Allowance Domestic: Hand/ Cabin baggage of");
            contents.newLine();
            contents.showText("maximum 7 kg. ");
            contents.setFont(PDType1Font.COURIER_BOLD, 18);
            contents.newLine();
            contents.showText("Check-In:");
            contents.setFont(PDType1Font.COURIER_BOLD, 16);
            contents.newLine();
            contents.showText("- Airport check-in counters will open two hours prior to the");
            contents.newLine();
            contents.showText("scheduled departure time. Passengers are encouraged to report");
            contents.newLine();
            contents.showText("at the Airport between 1-2 hours prior to the scheduled");
            contents.newLine();
            contents.showText("departure time.");
            contents.setFont(PDType1Font.COURIER_BOLD, 18);
            contents.newLine();
            contents.showText("Cancellations and Rescheduling: ");
            contents.setFont(PDType1Font.COURIER_BOLD, 16);
            contents.newLine();
            contents.showText("- Changes/cancellation in the bookings can be made only up to");
            contents.newLine();
            contents.showText("2 hours prior to scheduled departure time (4 hours in case of ");
            contents.newLine();
            contents.showText("international travel).");
            contents.endText();
            contents.close();
            document.save("C:\\Users\\dimit\\IdeaProjects\\Airline Booking\\src\\Logo\\" + passenger.getFullName() + "_" + this.id + ".pdf");
            System.out.println("Your ticket saved at" + "Tickets/" + passenger.getFullName() + "_" + this.id + ".pdf");
            document.close();


        } catch (Exception e) {

            System.out.println("The ticket couldn't be printed due: ");
            System.out.println(e.getMessage());
        }


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
