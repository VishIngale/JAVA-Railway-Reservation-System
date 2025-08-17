package com.study;

import com.study.model.Ticket;
import com.study.service.TicketService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class RailwayReservationSystem {

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        TicketService ticketService = new TicketService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\n---- Railway Reservation System ----");
            System.out.println("1. Book Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Book Ticket
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter From Station: ");
                    String fromStation = scanner.nextLine();
                    System.out.print("Enter To Station: ");
                    String toStation = scanner.nextLine();
                    System.out.print("Enter Travel Date (yyyy-mm-dd): ");
                    String travelDateStr = scanner.nextLine();
                    Ticket ticket = new Ticket();
                    ticket.setName(name);
                    ticket.setAge(age);
                    ticket.setFromStation(fromStation);
                    ticket.setToStation(toStation);
                    ticket.setTravelDate(dateFormat.parse(travelDateStr));

                    if (ticketService.bookTicket(ticket)) {
                        System.out.println("Ticket booked successfully!");
                    } else {
                        System.out.println("Error booking ticket!");
                    }
                    break;

                case 2:
                    // View All Tickets
                    List<Ticket> tickets = ticketService.viewAllTickets();
                    if (tickets.isEmpty()) {
                        System.out.println("No booked tickets found.");
                    } else {
                        for (Ticket t : tickets) {
                            System.out.println(t.getTicketId() + " | " + t.getName() + " | " + t.getFromStation() + " to " + t.getToStation() + " | " + t.getTravelDate() + " | Status: " + t.getStatus());
                        }
                    }
                    break;

                case 3:
                    // Cancel Ticket
                    System.out.print("Enter Ticket ID to cancel: ");
                    int ticketId = scanner.nextInt();
                    if (ticketService.cancelTicket(ticketId)) {
                        System.out.println("Ticket cancelled successfully!");
                    } else {
                        System.out.println("Error cancelling ticket!");
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

