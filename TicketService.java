package com.study.service;

import com.study.model.Ticket;
import com.study.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    // Method to book a ticket
    public boolean bookTicket(Ticket ticket) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO tickets (name, age, from_station, to_station, travel_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ticket.getName());
            statement.setInt(2, ticket.getAge());
            statement.setString(3, ticket.getFromStation());
            statement.setString(4, ticket.getToStation());
            statement.setDate(5, new java.sql.Date(ticket.getTravelDate().getTime()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to view all tickets
    public List<Ticket> viewAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM tickets WHERE status = 'Booked'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(resultSet.getInt("ticket_id"));
                ticket.setName(resultSet.getString("name"));
                ticket.setAge(resultSet.getInt("age"));
                ticket.setFromStation(resultSet.getString("from_station"));
                ticket.setToStation(resultSet.getString("to_station"));
                ticket.setTravelDate(resultSet.getDate("travel_date"));
                ticket.setStatus(resultSet.getString("status"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Method to cancel a ticket
    public boolean cancelTicket(int ticketId) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE tickets SET status = 'Cancelled' WHERE ticket_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ticketId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

