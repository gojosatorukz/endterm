package com.example.busticketbooking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="commuters")
public class Commuter extends BaseEntity {
    private String name;
    private String ticketNumber;

    // Constructors
    public Commuter() {}

    public Commuter(String name, String ticketNumber) {
        this.name = name;
        this.ticketNumber = ticketNumber;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commuter commuter = (Commuter) o;
        return Objects.equals(name, commuter.name) &&
                Objects.equals(ticketNumber, commuter.ticketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ticketNumber);
    }
    @Override
    public String toString() {
        return "Commuter{id=" + id + ", name='" + name + "', ticketNumber='" + ticketNumber + "'}";
    }
}
